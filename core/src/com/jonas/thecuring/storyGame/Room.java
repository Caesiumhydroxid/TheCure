package com.jonas.thecuring.storyGame;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Room extends AbstractGameObject {
	
	private Texture roomTexture;
	public ArrayList<ActionRoom> eventRooms;
	private World world;
	public boolean debug=true;
	public Vector2 spawnPoint;
	Room(Texture roomTexture,World world)
	{
		super();
		spawnPoint = new Vector2(0,8);
		world.player.position = spawnPoint;
		eventRooms = new ArrayList<ActionRoom>();
		this.roomTexture = roomTexture;
		this.z = 0;
		this.world = world;
	}
	Room(Texture roomTexture,World world,Vector2 spawnPoint)
	{
		super();
		this.spawnPoint = spawnPoint; 
		world.player.position = spawnPoint;
		eventRooms = new ArrayList<ActionRoom>();
		this.roomTexture = roomTexture;
		this.z = 0;
		this.world = world;
	}
	
	@Override
	public void update(float delta) {
		for(ActionRoom eventRoom:eventRooms)
		{
			if(eventRoom.overlaps(world.player.boundingRectangle))
			{
				eventRoom.triggerEvent();
			}
		}
		super.update(delta);
	}


	@Override
	public void render(Batch batch) {
		batch.draw(roomTexture, this.position.x, this.position.y);
		if(debug)
		{
			batch.end();
			ShapeRenderer s = new ShapeRenderer();
			s.setProjectionMatrix(batch.getProjectionMatrix());
			s.setColor(Color.RED);
			s.begin(ShapeType.Line);
			for(ActionRoom eventRoom:eventRooms)
			{
				s.rect(eventRoom.x,eventRoom.y,eventRoom.width,eventRoom.height);
			}
			s.end();
			s.dispose();
			batch.begin();
		}
	}
	
}
