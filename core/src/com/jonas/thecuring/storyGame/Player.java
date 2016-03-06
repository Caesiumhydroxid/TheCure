package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;

public class Player extends AbstractGameObject{
	Animation walkLeft;
	Animation walkRight;
	Animation walkRightBusiness;
	Animation walkLeftBusiness;
	Animation currentAnimation;
	Animation stillLeft;
	Animation stillRight;
	Animation stillFront;
	Animation stillLeftBusiness;
	Animation stillRightBusiness;
	Animation stillFrontBusiness;
	
	Animation currentWalkLeft;
	Animation currentWalkRight;
	Animation currentStillFront;
	Animation currentStillRight;
	Animation currentStillLeft;
	
	BitmapFont font;
	private GlyphLayout layout;
	private String text;
	private Texture shadow;
	Vector2 velocity;
	float elapsedTime;
	float timeSinceVelocityZero;
	boolean lastDirectionRight;
	Player()
	{
		velocity = new Vector2();
		font = (BitmapFont) Assets.getInstance().get("font_small");
		shadow = (Texture) Assets.getInstance().get("shadow");
		layout = new GlyphLayout();
		boundingRectangle = new Rectangle();
		TextureRegion [][] tmp = TextureRegion.split((Texture)Assets.getInstance().get("maincharacter_animation"), 40, 40);
		walkLeft = new Animation(0.075f, tmp[1]);
		walkRight = new Animation(0.075f, tmp[0]);
		walkLeftBusiness = new Animation(0.075f, tmp[3]);
		walkRightBusiness = new Animation(0.075f, tmp[2]);
		
		tmp = TextureRegion.split((Texture)Assets.getInstance().get("maincharacter_normal"), 40, 40);
		stillLeft = new Animation(1f,tmp[0][1]);
		stillRight = new Animation(1f,tmp[0][2]);
		stillFront = new Animation(1f,tmp[0][0]);
		tmp = TextureRegion.split((Texture)Assets.getInstance().get("maincharacter_business"), 40, 40);
		stillLeftBusiness = new Animation(1f,tmp[0][1]);
		stillRightBusiness = new Animation(1f,tmp[0][2]);
		stillFrontBusiness = new Animation(1f,tmp[0][0]);
		
		currentStillFront = stillFrontBusiness;
		currentStillLeft = stillLeftBusiness;
		currentStillRight = stillRightBusiness;
		currentWalkLeft = walkLeftBusiness;
		currentWalkRight = walkRightBusiness;
		
		currentAnimation = stillFrontBusiness;
		text = "";
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	@Override
	public void update(float delta) {
		boundingRectangle.set(position.x+15, position.y, 9, 37);
		elapsedTime += delta;
		
		if(Math.abs(velocity.x) < 0.0000001f)
		{
			timeSinceVelocityZero+= delta;
			if(timeSinceVelocityZero>2)
			{
				currentAnimation = currentStillFront;
			}
			else
			{
				if(lastDirectionRight)
					currentAnimation = currentStillRight;
				else
					currentAnimation = currentStillLeft;
			}
		}
		
		else if(velocity.x>0)
		{
			currentAnimation = currentWalkRight;
			lastDirectionRight = true;
			timeSinceVelocityZero = 0;
		}
		else if(velocity.x<0)
		{
			currentAnimation = currentWalkLeft;
			lastDirectionRight = false;
			timeSinceVelocityZero = 0;
		}
		position = position.add(velocity.scl(delta));
		velocity.set(0,0);
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		layout.setText(font, text,Color.BLACK,40,Align.center,false);
		batch.draw(currentAnimation.getKeyFrame(elapsedTime,true),position.x,position.y);
		batch.draw(shadow,position.x,position.y-3);
		font.draw(batch, layout, position.x-1, position.y + boundingRectangle.height+layout.height+2);
		this.text = "";
	}
	
}
