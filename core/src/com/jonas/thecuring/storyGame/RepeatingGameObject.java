package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class RepeatingGameObject extends AbstractGameObject {
	Texture texture;
	public Vector2 velocity;
	public RepeatingGameObject(Texture text) {
		this.texture = text;
		this.boundingRectangle.height = text.getHeight();
		this.boundingRectangle.width = text.getWidth();
		this.velocity = new Vector2(0,0);
	}
	
	@Override
	public void update(float delta) {
		position.add(velocity.x*delta,velocity.y*delta);
		position.x = position.x % boundingRectangle.width;
		position.y = position.y % boundingRectangle.height;
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(texture,position.x,position.y);
		batch.draw(texture,position.x+boundingRectangle.width,position.y);
		batch.draw(texture,position.x-boundingRectangle.width,position.y);
		batch.draw(texture,position.x,position.y+boundingRectangle.height);
		batch.draw(texture,position.x,position.y-boundingRectangle.height);
	}

}
