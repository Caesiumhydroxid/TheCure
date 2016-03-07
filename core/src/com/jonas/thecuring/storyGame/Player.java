package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	private float timeSinceVelocityZero;
	private boolean lastDirectionRight;
	private World world;
	public int currentClothing;
	Player(World world)
	{
		velocity = new Vector2();
		font = (BitmapFont) Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		shadow = (Texture) Assets.getInstance().get("shadow");
		layout = new GlyphLayout();
		position = new Vector2();
		
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
		
		currentStillFront = stillFront;
		currentStillLeft = stillLeft;
		currentStillRight = stillRight;
		currentWalkLeft = walkLeft;
		currentWalkRight = walkRight;
		
		this.world = world;
		currentAnimation = stillFrontBusiness;
		text = "";
	}
	
	public void dress()
	{
		currentClothing = (currentClothing+1)%2;
		if(currentClothing==0)
		{
			currentStillFront = stillFront;
			currentStillLeft = stillLeft;
			currentStillRight = stillRight;
			currentWalkLeft = walkLeft;
			currentWalkRight = walkRight;
		}
		else if(currentClothing ==1)
		{
			currentStillFront = stillFrontBusiness;
			currentStillLeft = stillLeftBusiness;
			currentStillRight = stillRightBusiness;
			currentWalkLeft = walkLeftBusiness;
			currentWalkRight = walkRightBusiness;
		}
	}
	@Override
	protected void handleInput()
	{
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			velocity.x = 14/0.6f;
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			velocity.x = -14/0.6f;
		}
	}
	
	public Vector2 getBoundingRectangle() {
		return position;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	@Override
	public void setPosition(Vector2 position) {
		super.setPosition(position);
		boundingRectangle.set(position.x+15, position.y, 7, 37);
	}
	@Override
	public void update(float delta) {
		super.update(delta);
		setPosition(position.add(velocity.scl(delta)));
		elapsedTime += delta;
		for(Rectangle r:world.getCurrentRoom().colliders)
		{
			if(r.overlaps(boundingRectangle))
			{
				setPosition(position.sub(velocity));
				velocity.set(0,0);
				break;
			}
		}
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
		velocity.set(0,0);
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
