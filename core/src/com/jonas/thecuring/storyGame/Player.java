package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;

public class Player extends AbstractGameObject {
	Animation walkLeft;
	Animation walkRight;
	Animation walkRightBusiness;
	Animation walkLeftBusiness;
	public Animation currentAnimation;
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
	
	public Animation holdSpeech;

	BitmapFont font;
	private GlyphLayout layout;
	private String text;
	private Texture shadow;
	public Vector2 velocity;
	public float elapsedTime;
	private float timeSinceVelocityZero;
	private boolean lastDirectionRight;
	private World world;
	public Integer currentClothing;
	public ArrayList<Action> actionsToRun;
	public ArrayList<Action> actionsToAdd;
	public boolean allowedToChangeAnimations=true;
	public final float walkLeftVelocity = -14 / 0.6f;
	public final float walkRightVelocity = 14 / 0.6f;
	public boolean render = true;

	Player(World world) {
		velocity = new Vector2();
		font = (BitmapFont) Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		shadow = (Texture) Assets.getInstance().get("shadow");
		layout = new GlyphLayout();
		position = new Vector2();
		actionsToRun = new ArrayList<Action>();
		actionsToAdd = new ArrayList<Action>();
		currentClothing = new Integer(0);

		TextureRegion[][] tmp = TextureRegion.split((Texture) Assets.getInstance().get("maincharacter_animation"), 40,40);
		walkLeft = new Animation(0.075f, tmp[1]);
		walkRight = new Animation(0.075f, tmp[0]);
		walkLeftBusiness = new Animation(0.075f, tmp[3]);
		walkRightBusiness = new Animation(0.075f, tmp[2]);
		walkRight.setPlayMode(PlayMode.LOOP);
		walkLeft.setPlayMode(PlayMode.LOOP);
		walkLeftBusiness.setPlayMode(PlayMode.LOOP);
		walkRightBusiness.setPlayMode(PlayMode.LOOP);
		TextureRegion[] speech = new TextureRegion[6*8];
		for(int i=4;i<=9;i++)
		{
			for(int ii=0;ii<8;ii++)
			{
				speech[(i-4)*8+ii] = tmp[i][ii];
			}
		}
		holdSpeech = new Animation(0.25f,speech);
		
		tmp = TextureRegion.split((Texture) Assets.getInstance().get("maincharacter_normal"), 40, 40);
		stillLeft = new Animation(1f, tmp[0][1]);
		stillRight = new Animation(1f, tmp[0][2]);
		stillFront = new Animation(1f, tmp[0][0]);
		tmp = TextureRegion.split((Texture) Assets.getInstance().get("maincharacter_business"), 40, 40);
		stillLeftBusiness = new Animation(1f, tmp[0][1]);
		stillRightBusiness = new Animation(1f, tmp[0][2]);
		stillFrontBusiness = new Animation(1f, tmp[0][0]);

		currentStillFront = stillFront;
		currentStillLeft = stillLeft;
		currentStillRight = stillRight;
		currentWalkLeft = walkLeft;
		currentWalkRight = walkRight;
		
		this.world = world;
		currentAnimation = stillFrontBusiness;
		text = "";
		
	}

	public void dress() {
		currentClothing = (currentClothing.intValue() + 1) % 2;
		if (currentClothing == 0) {
			currentStillFront = stillFront;
			currentStillLeft = stillLeft;
			currentStillRight = stillRight;
			currentWalkLeft = walkLeft;
			currentWalkRight = walkRight;
		} else if (currentClothing.intValue() == 1) {
			currentStillFront = stillFrontBusiness;
			currentStillLeft = stillLeftBusiness;
			currentStillRight = stillRightBusiness;
			currentWalkLeft = walkLeftBusiness;
			currentWalkRight = walkRightBusiness;
		}
	}

	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			velocity.x = walkRightVelocity;
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			velocity.x = walkLeftVelocity;
		}
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void setPosition(Vector2 position) {
		super.setPosition(position);
		boundingRectangle.set(position.x + 15, position.y, 7, 37);
	}

	@Override
	public void update(float delta) {

		if (processInput == true && (actionsToRun.isEmpty())) {
			handleInput();
		}
		if (!actionsToRun.isEmpty()) {
			for (Iterator<Action> itr = actionsToRun.iterator(); itr.hasNext();) {
				Action action = itr.next();
				action.run();
				action.update(delta);
				if (action.toDelete)
					itr.remove();
			}
		}
		actionsToRun.addAll(actionsToAdd);
		
		actionsToAdd.clear();
		
		setPosition(position.add(velocity.scl(delta)));
		elapsedTime += delta;
		for (Rectangle r : world.getCurrentRoom().colliders) {
			if (r.overlaps(boundingRectangle)) {
				setPosition(position.sub(velocity));
				velocity.set(0, 0);
				break;
			}
		}
		if (allowedToChangeAnimations) {
			if (Math.abs(velocity.x) < 0.0000001f) {
				timeSinceVelocityZero += delta;
				if (timeSinceVelocityZero > 2) {
					currentAnimation = currentStillFront;
				} else {
					if (lastDirectionRight)
						currentAnimation = currentStillRight;
					else
						currentAnimation = currentStillLeft;
				}
			} else if (velocity.x > 0) {
				currentAnimation = currentWalkRight;
				lastDirectionRight = true;
				timeSinceVelocityZero = 0;
			} else if (velocity.x < 0) {
				currentAnimation = currentWalkLeft;
				lastDirectionRight = false;
				timeSinceVelocityZero = 0;
			}
		}
		velocity.set(0, 0);
	}

	@Override
	public void render(Batch batch) {
		if(render)
		{
			layout.setText(font, text, Color.BLACK, 40, Align.center, false);
			batch.draw(currentAnimation.getKeyFrame(elapsedTime), position.x, position.y);
			batch.draw(shadow, position.x, position.y - 3);
			font.draw(batch, layout, position.x - 1, position.y + boundingRectangle.height + layout.height + 2);
			
			this.text = "";
		}
	}
}
