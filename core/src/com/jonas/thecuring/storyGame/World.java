package com.jonas.thecuring.storyGame;

import java.util.Vector;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.jonas.thecuring.Assets;

public class World {
	Player player;
	private Vector<AbstractGameObject> objects;
	private Room currentRoom;
	
	
	World()
	{
		objects = new Vector<AbstractGameObject>();
		player = new Player((Texture)Assets.getInstance().get("maincharacter_animation"));
		player.position.y=10;
		
		setCurrentRoom(RoomEnum.HOME_ROOM);
		objects.add(player);
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
	void update(float delta)
	{
		currentRoom.update(delta);
		for(AbstractGameObject tmp:objects)
		{
			tmp.update(delta);
		}
	}
	
	void render(Batch batch)
	{
		currentRoom.render(batch);
		for(AbstractGameObject tmp:objects)
		{
			tmp.render(batch);
		}
		
	}

	public void displayText(String text) {
		System.out.println("Displaying Text:"+text);	
		
	}
}
