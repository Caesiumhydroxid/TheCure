package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GameObject extends AbstractGameObject {
	
	private Animation currentAnimation;
	private float elapsedTime;
	public GameObject(Texture texture) {
		super();
		currentAnimation = new Animation(1,new TextureRegion[]{new TextureRegion(texture)}); 
	}
	
	public GameObject(Animation animation) {
		currentAnimation = animation;
	}
	
	@Override
	public void update(float delta) {
		elapsedTime += delta;
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.draw(currentAnimation.getKeyFrame(elapsedTime,true),position.x,position.y);
	}
	
}
