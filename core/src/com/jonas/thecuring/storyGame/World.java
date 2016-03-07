package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;

public class World{
	public Player player;
	private ArrayList<AbstractGameObject> objects;
	private Vector<AbstractGameObject> objectsToPush;
	private Vector<AbstractGameObject> objectsToRemove;
	private Room currentRoom;
	private Pool<DialogueMessage> messagePool;
	
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
		player = new Player();
		player.position.y=10;
		player.z = 5;

		setCurrentRoom(RoomEnum.HOME_ROOM);
		//ChoiceMenu c = new ChoiceMenu(new String[]{"Hallo","Test"}, new Action[]{new ChangeRoomAction(this, RoomEnum.WORK_ROOM),new DisplayDialogueAction("Test",this,false)});
		//c.z = 100;
		displayText("Test");
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
	
	public void setCurrentRoom(RoomEnum room)
	{
		if(currentRoom!=null)
		{
			currentRoom.spawnPoint = player.position;
		}
		currentRoom = room.getRoom(this);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	void handleInput()
	{
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			player.velocity.x = 14/0.6f;
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			player.velocity.x = -14/0.6f;
		}
	}
	
	void update(float delta)
	{
		handleInput();
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
}
