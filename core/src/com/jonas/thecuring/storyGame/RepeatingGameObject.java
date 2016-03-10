package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RepeatingGameObject extends AbstractGameObject {
	Texture texture;
	public Vector2 velocity;
	Rectangle screenRect;
	public RepeatingGameObject(Texture text) {
		this.texture = text;
		this.boundingRectangle.height = text.getHeight();
		this.boundingRectangle.width = text.getWidth();
		this.velocity = new Vector2(0,0);
		screenRect = new Rectangle(0,0,160,90);
	}
	
	@Override
	public void update(float delta) {
		position.add(velocity.x*delta,velocity.y*delta);
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(texture,position.x,position.y);
		if(!screenRect.contains(boundingRectangle))
		{
			batch.draw(texture,position.x+boundingRectangle.width-2,position.y);
			batch.draw(texture,position.x-boundingRectangle.width+2,position.y);
			position.x = position.x % boundingRectangle.width;
		}
		
	}

}
