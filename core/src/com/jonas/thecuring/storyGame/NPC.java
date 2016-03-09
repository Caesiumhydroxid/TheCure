package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.storyGame.Actions.Action;

public class NPC extends AbstractGameObject {
	
	Animation currentAnimation;
	float elapsedTime;
	private ActionRoom actionRoom;
	private Rectangle boundingRectangle;
	private Vector2 offset;
	public Vector2 velocity;
	public NPC(Animation anim,Action action,Rectangle boundingRectangle)
	{
		currentAnimation = anim;
		actionRoom = new ActionRoom();
		actionRoom.setAction(action);
		this.boundingRectangle = boundingRectangle;
		actionRoom.set(boundingRectangle);
		offset = new Vector2();
		offset.x = this.boundingRectangle.x;
		offset.y = this.boundingRectangle.y;
	}
	public NPC(Texture texture,Action action,Rectangle boundingRectangle)
	{
		currentAnimation = new Animation(1,new TextureRegion[]{new TextureRegion(texture)}); 
		actionRoom = new ActionRoom();
		actionRoom.setAction(action);
		this.boundingRectangle = boundingRectangle;
		actionRoom.set(boundingRectangle);
		offset = new Vector2();
		offset.x = this.boundingRectangle.x;
		offset.y = this.boundingRectangle.y;
	}
	public ActionRoom getActionRoom() {
		return actionRoom;
	}
	
	@Override
	public void setPosition(Vector2 position) {
		super.setPosition(position);
		boundingRectangle.setPosition(position.x+offset.x, position.y+offset.y);
		actionRoom.setPosition(position.x+offset.x,position.y+offset.y);
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
