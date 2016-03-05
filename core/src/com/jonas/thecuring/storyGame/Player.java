package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends AbstractGameObject{
	Animation walkLeft;
	Animation walkRight;
	Animation walkRightBusiness;
	Animation walkLeftBusiness;
	Animation currentAnimation;
	
	float elapsedTime;
	Player(Texture animationSheet)
	{
		boundingRectangle = new Rectangle();
		TextureRegion [][] tmp = TextureRegion.split(animationSheet, 40, 40);
		walkLeft = new Animation(0.1f, tmp[1]);
		walkRight = new Animation(0.1f, tmp[0]);
		walkLeftBusiness = new Animation(0.1f, tmp[3]);
		walkRightBusiness = new Animation(0.1f, tmp[2]);
		currentAnimation = walkRight;
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
	@Override
	public void update(float delta) {
		boundingRectangle.set(position.x+15, position.y, 9, 37);
		elapsedTime += delta;
		position.x += 13/0.8f*delta;
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(currentAnimation.getKeyFrame(elapsedTime,true),position.x,position.y);
		batch.end();
		batch.begin();
	}
	
}
