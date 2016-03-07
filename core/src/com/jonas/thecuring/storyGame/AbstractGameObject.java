package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
	
	protected Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Rectangle boundingRectangle;
	public boolean toDelete;
	public boolean processInput=true;
	public float z;
	public AbstractGameObject () {
		toDelete = false;
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		boundingRectangle = new Rectangle();
		position = new Vector2();
	}
	
	public void update(float delta){
		if(processInput)
			handleInput();
	}
	
	protected void handleInput()
	{
		
	}
	public void setPosition(Vector2 position) {
		this.position.set(position.x,position.y);
	}
	public Vector2 getPosition() {
		return new Vector2(position);
	}
	public abstract void render(Batch batch);
	
}
