package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.jonas.thecuring.ScreenEnum;
import com.jonas.thecuring.ScreenManager;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.AddActionToRoom;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class World extends Listener{
	public Player player;
	private ArrayList<AbstractGameObject> objects;
	private Vector<AbstractGameObject> objectsToPush;
	private Vector<AbstractGameObject> objectsToRemove;
	private Room currentRoom;
	private Pool<DialogueMessage> messagePool;
	public boolean blackedOut;
	public boolean wifeKnowsAboutCancer = true;
	public boolean timeWithFamily=true;
	private Black black;
	public boolean willDie=false;
	public InputMultiplexer multiplexer;
	public int day;
	private Server server;
	World(Server server,InputMultiplexer multiplexer)
	{
		this.server = server;
		this.multiplexer = multiplexer;
		server.addListener(this);
		messagePool = new Pool<DialogueMessage>(){

			@Override
			protected DialogueMessage newObject() {
				return new DialogueMessage();
			}
		};
		objects = new ArrayList<AbstractGameObject>();
		objectsToPush = new Vector<AbstractGameObject>();
		objectsToRemove = new Vector<AbstractGameObject>();
		player = new Player(this);
		player.position.y=10;
		player.z = 5;
		
		
		black = new Black();
		day = 0;
		setCurrentRoom(RoomEnum.HOME_ROOM);
		//ChoiceMenu c = new ChoiceMenu(new String[]{"Hallo","Test"}, new Action[]{new ChangeRoomAction(this, RoomEnum.WORK_ROOM),new DisplayDialogueAction("Test",this,false)});
		//c.z = 100;
		//objects.add(c);
		objects.add(player);
	}
	
	public void push(AbstractGameObject object)
	{
		objectsToPush.add(object);
	}
	public void remove(AbstractGameObject object)
	{
		objectsToRemove.add(object);
	}
	
	//Sollte vermutlich nicht gro�artig ge�ndert werden
	public void setCurrentRoom(RoomEnum room)
	{
		if(currentRoom!= null)
		{
			currentRoom.fireEvents = false;
			currentRoom.removeGameObjects();
		}
		blackedOut = false;
		currentRoom = room.getRoom(this);
		currentRoom.init();
		player.setPosition(currentRoom.getSpawnPoint());
		currentRoom.fireEvents = true;
	}
	
	public void setCurrentRoomTransition(RoomEnum room)
	{
		setCurrentRoomTransition(room, 1);
	}
	public void setCurrentRoomTransition(RoomEnum room,float transitionDuration)
	{
		setCurrentRoomTransition(room, "", transitionDuration, 0);
	}
	public void setCurrentRoomTransition(RoomEnum room,String text,float transitionDuration,float textDuration)
	{
		setCurrentRoomTransition(room, text, transitionDuration, textDuration, null);
	}
	public void setCurrentRoomTransition(RoomEnum room,String text,float transitionDuration,float textDuration,Action nextAction)
	{
		if(currentRoom!=null)
		{
			currentRoom.fireEvents = false;
			TransitionScreen t = new TransitionScreen(transitionDuration,textDuration,text);
			t.addListener(new RoomChangeListener(room,this,nextAction));
			for(Iterator<AbstractGameObject> itr = objects.iterator();itr.hasNext();)
			{
				AbstractGameObject obj = (AbstractGameObject) itr.next();
				if(obj  instanceof DialogueMessage)
				{
					obj.toDelete = true;
				}
			}
			push(t);
		}
		else
		{
			currentRoom = room.getRoom(this);
		}
	}
	
	public void makeTextTransition(float transitionTime,float remainAtTextTime,String text,TransitionTextAction action)
	{
		TransitionScreen t = new TransitionScreen(transitionTime, remainAtTextTime, text);
		t.addListener(action);
		push(t);
	}

	private class RoomChangeListener implements TransitionListener{
		private RoomEnum changeRoom;
		private World world;
		private Action nextAction;
		private boolean playerProcessingInput;
		RoomChangeListener(RoomEnum changeRoom,World world,Action nextAction)
		{
			this.changeRoom = changeRoom;
			this.world = world;
			playerProcessingInput = world.player.processInput;
			world.player.processInput=false;
			this.nextAction = nextAction;
		}
		@Override
		public void transitionIsAtMax() {
			world.player.processInput = true;
			world.setCurrentRoom(changeRoom);
			if(nextAction!= null)
			{
				nextAction.run();
			}
		}

		@Override
		public void transitionFinished() {
			if(world.player.processInput == false)
				world.player.processInput = false;
			else if(world.player.processInput == true&& playerProcessingInput == false)
			{
				world.player.processInput = true;
			}
			else
				world.player.processInput = playerProcessingInput;
			
		}
		
	}
	
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	
	void update(float delta)
	{
		currentRoom.update(delta);
		
		for(Iterator<AbstractGameObject> itr = objects.iterator();itr.hasNext();)
		{	
			AbstractGameObject tmp = (AbstractGameObject) itr.next();
			if(tmp.toDelete)
			{
				itr.remove();
				if(tmp instanceof DialogueMessage)
					messagePool.free((DialogueMessage)tmp);
			}
			else
				tmp.update(delta);
		}
		
		objects.addAll(objectsToPush);
		objectsToPush.clear();
		objects.removeAll(objectsToRemove);
		objectsToRemove.clear();
	}
	class Black extends AbstractGameObject{
		Texture tex;
		Black()
		{
			Pixmap map = new Pixmap(160, 90, Format.RGB888);
			map.setColor(Color.BLACK);
			map.fill();
			tex = new Texture(map);
		}
		@Override
		public void render(Batch batch) {
			batch.draw(tex,0,0);
		}
		
	}
	void render(Batch batch)
	{
		currentRoom.render(batch);		
		if(blackedOut)
		{
			black.z = 175;
			objects.add(black);
		}
		Collections.sort(objects, new Comparator<AbstractGameObject>() {
			@Override
			public int compare(AbstractGameObject o1, AbstractGameObject o2) {
				if(o1.z>o2.z)
					return 1;
				else if(o1.z==o2.z)
					return 0;
				else 
					return -1;
			}
		});
		
		for(AbstractGameObject tmp:objects)
		{
			tmp.render(batch);
		}
		if(blackedOut)
			objects.remove(black);
	}
	
	public void displayText(String text,float timeToShow) {
		DialogueMessage message = messagePool.obtain();
		message.position.set(12,4);
		message.reset();
		message.z = 100;
		message.setTimeToShow(timeToShow);
		message.setText(text);
		push(message);
	}
	public void displayText(String text,float timeToShow,float y) {
		DialogueMessage message = messagePool.obtain();
		message.position.set(12,y);
		message.reset();
		message.z = 100;
		message.setTimeToShow(timeToShow);
		message.setText(text);
		push(message);
	}

	public void displayOptions(String[] options,Action[] actions,String question,boolean big)
	{
		ChoiceMenu menu  = new ChoiceMenu(options,actions,this,big,question);
		if(big)
			menu.position.set(20,4);
		else
			menu.position.set(100,30);
		menu.z = 150;
		menu.processInput = true;
		push(menu);
		
	}
	public void sendMessage(String message)
	{
		server.sendToAllTCP(message);
	}
	public void sendMessage(Integer message)
	{
		server.sendToAllTCP(message);
	}

	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof Boolean)
		{
			willDie = ((Boolean) object).booleanValue();
		}
		super.received(connection, object);
	}

	@Override
	public void disconnected(Connection connection) {
		class ChangeScreenAction extends Action
		{

			public ChangeScreenAction(World world, Action nextAction) {
				super(world, nextAction);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void run() {
				ScreenManager.getInstance().show(ScreenEnum.MAIN_MENU, world.multiplexer);
				
			}
		}
		Action a = new AddActionToRoom(this,new TransitionTextAction(this, "Verbindung zu anderem Spieler verloren! Bitte neustarten!", true, new ChangeScreenAction(this, null)));
		a.run();
		super.disconnected(connection);
	}
	
	
}
