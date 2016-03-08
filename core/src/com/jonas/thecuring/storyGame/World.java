package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;
import com.jonas.thecuring.storyGame.Actions.Action;

public class World{
	public Player player;
	private ArrayList<AbstractGameObject> objects;
	private Vector<AbstractGameObject> objectsToPush;
	private Vector<AbstractGameObject> objectsToRemove;
	private Room currentRoom;
	private Pool<DialogueMessage> messagePool;
	public int day;
	
	World()
	{
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
	
	//Sollte vermutlich nicht groﬂartig ge‰ndert werden
	public void setCurrentRoom(RoomEnum room)
	{
		if(currentRoom!= null)
		{
			currentRoom.fireEvents = false;
		}
		currentRoom = room.getRoom(this);
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
		if(currentRoom!=null)
		{
			currentRoom.fireEvents = false;
			TransitionScreen t = new TransitionScreen(transitionDuration,textDuration,text);
			t.addListener(new RoomChangeListener(room,this));
			player.processInput=false;
			push(t);
		}
		else
		{
			currentRoom = room.getRoom(this);
		}
	}
	
	private class RoomChangeListener implements TransitionListener{
		private RoomEnum changeRoom;
		private World world;
		RoomChangeListener(RoomEnum changeRoom,World world)
		{
			this.changeRoom = changeRoom;
			this.world = world;
		}
		@Override
		public void transitionIsAtMax() {
			world.setCurrentRoom(changeRoom);
		}

		@Override
		public void transitionFinished() {
			world.player.processInput= true;
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
	
	void render(Batch batch)
	{
		currentRoom.render(batch);		
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
		
	}

	public void displayText(String text) {
		DialogueMessage message = messagePool.obtain();
		message.position.set(12,4);
		message.reset();
		message.z = 100;
		message.setText(text);
		System.out.println("text");
		push(message);
	}
	
	public void displayOptions(String[] options,Action[] actions)
	{
		ChoiceMenu menu  = new ChoiceMenu(options,actions,this);
		menu.position.set(100,30);
		menu.z = 200;
		menu.processInput = true;
		push(menu);
		
	}
}
