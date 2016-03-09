package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class World{
	public Player player;
	private ArrayList<AbstractGameObject> objects;
	private Vector<AbstractGameObject> objectsToPush;
	private Vector<AbstractGameObject> objectsToRemove;
	private Room currentRoom;
	private Pool<DialogueMessage> messagePool;
	public boolean blackedOut;
	private Black black;
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
		
		black = new Black();
		
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
	
	public void makeTextTransition(float transitionTime,float remainAtTextTime,String text,TransitionTextAction action)
	{
		TransitionScreen t = new TransitionScreen(transitionTime, remainAtTextTime, text);
		t.addListener(action);
		push(t);
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
	
	public void displayOptions(String[] options,Action[] actions)
	{
		ChoiceMenu menu  = new ChoiceMenu(options,actions,this);
		menu.position.set(100,30);
		menu.z = 150;
		menu.processInput = true;
		push(menu);
		
	}
}
