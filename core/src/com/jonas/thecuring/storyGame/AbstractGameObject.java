package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGameObject {
	
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Rectangle boundingRectangle;
	public boolean toDelete;
	public float z;
	public AbstractGameObject () {
		toDelete = false;
		
		position = new Vector2();
		dimension = new Vector2(1, 1);
		origin = new Vector2();
		boundingRectangle = new Rectangle();
	}
	
	public void update(float delta){
	}

	public abstract void render(Batch batch);
	
}
