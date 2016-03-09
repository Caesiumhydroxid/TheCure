package com.jonas.thecuring.storyGame;


import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.storyGame.Actions.Action;

public class Room extends AbstractGameObject {
	
	private Texture roomTexture;
	public ArrayList<ActionRoom> actionRooms;
	public ArrayList<ActionRoom> actionRoomsToAdd;
	private World world;
	public boolean debug=true;
	private final Vector2 spawnPoint;
	public boolean fireEvents=true;
	public ArrayList<Rectangle> colliders;
	public ArrayList<NPC> npcs;
	private ArrayList<AbstractGameObject> pushedGameObjects;
	private ArrayList<AbstractGameObject> gameObjects;
	
	Room(Texture roomTexture,World world)
	{
		this(roomTexture,world,new Vector2());
	}
	public Room(Texture roomTexture,World world,Vector2 spawnPoint)
	{
		super();
		this.spawnPoint = new Vector2(spawnPoint); 
		actionRoomsToAdd = new ArrayList<ActionRoom>();
		actionRooms = new ArrayList<ActionRoom>();
		colliders = new ArrayList<Rectangle>();
		pushedGameObjects = new ArrayList<AbstractGameObject>();
		gameObjects = new ArrayList<AbstractGameObject>();
		npcs = new ArrayList<NPC>();
		this.roomTexture = roomTexture;
		this.z = 0;
		this.world = world;
	}
	
	public Vector2 getSpawnPoint() {
		return spawnPoint;
	}
	public void add(NPC npc)
	{
		npcs.add(npc);
		actionRooms.add(npc.getActionRoom());
	}
	
	public void add(AbstractGameObject obj)
	{
		gameObjects.add(obj);
	}
	public void init()
	{
		for(NPC npc:npcs)
		{
			world.push(npc);
			pushedGameObjects.add(npc);
		}
		for(AbstractGameObject obj:gameObjects)
		{
			world.push(obj);
			pushedGameObjects.add(obj);
		}
	}
	public void remove(NPC npc)
	{
		npcs.remove(npc);
		actionRooms.remove(npc.getActionRoom());
	}
	
	@Override
	public void update(float delta) {
		for(Iterator<ActionRoom> itr = actionRooms.iterator();itr.hasNext();)
		{
			ActionRoom eventRoom = itr.next();
			if(eventRoom.overlaps(world.player.boundingRectangle))
			{
				if(fireEvents)
					eventRoom.triggerEvent();
			}
			eventRoom.update(delta);
			if(eventRoom.height==0&&eventRoom.width==0&&!eventRoom.getAction().running)
			{
				eventRoom.triggerEvent();
			}
			if(eventRoom.height==-1&&eventRoom.width==-1)
			{
				eventRoom.triggerEvent();
				itr.remove();
			}
			else if(eventRoom.getAction()!= null && eventRoom.getAction().toDelete)
			{
				itr.remove();
			}
		}
		for(NPC npc:npcs)
		{
			npc.update(delta);
		}
		actionRooms.addAll(actionRoomsToAdd);
		actionRoomsToAdd.clear();
		super.update(delta);
	}

	public void addActionRoom(float x,float y,float width,float height,Action action)
	{
		Rectangle r = new Rectangle(x, y, width, height);
		actionRoomsToAdd.add(new ActionRoom(r, action));
	}
	
	public void removeGameObjects()
	{
		for(AbstractGameObject obj:pushedGameObjects)
		{
			world.remove(obj);
		}
		pushedGameObjects.clear(); 
	}
	
	@Override
	public void render(Batch batch) {
		batch.draw(roomTexture, this.position.x, this.position.y);
		for(NPC npc:npcs)
		{
			npc.render(batch);
		}
		if(debug)
		{
			batch.end();
			ShapeRenderer s = new ShapeRenderer();
			s.setProjectionMatrix(batch.getProjectionMatrix());
			s.setColor(Color.RED);
			s.begin(ShapeType.Line);
			for(ActionRoom eventRoom:actionRooms)
			{
				s.rect(eventRoom.x,eventRoom.y,eventRoom.width,eventRoom.height);
			}
			for(Rectangle collider:colliders)
			{
				s.rect(collider.x,collider.y,collider.width,collider.height);
			}
			s.end();
			s.dispose();
			batch.begin();
		}
	}
	
}
