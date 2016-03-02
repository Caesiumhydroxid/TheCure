package com.jonas.thecuring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonas.thecuring.ui.AnimatedImage;
		

public class GameState extends ChangeListener implements Screen{
	
	private Image background;
	private Stage stage;
	private AssetManager manager;
	private AttackMenu attackMenu;
	public GameState(OrthographicCamera camera,AssetManager manager)
	{
		this.manager = manager;
		
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4));
		Gdx.input.setInputProcessor(stage);
		background = new Image((Texture) this.manager.get("Background.png"));
		stage.addActor(background);
		
		
		Styles styles = new Styles(manager);
		
		int frame_rows = 2;
		int frame_cols = 4;
		TextureRegion [][] tmp = TextureRegion.split((Texture) manager.get("NewChar_anim.png"), 128, 128);
		TextureRegion[] frames = new TextureRegion[frame_rows*frame_cols];
		int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        Animation anim = new Animation(0.300f, frames);
        AnimatedImage body = new AnimatedImage(anim);
        body.setPosition(180, 30);
        stage.addActor(body);
        
		attackMenu = new AttackMenu(styles, manager,this);
		attackMenu.setPosition(18, 180-130);
		attackMenu.setScale(1);
		stage.addActor(attackMenu);
		
	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		if(actor.getName().equals("a_aggressive"))
		{
			MoveByAction action = new MoveByAction();
			action.setDuration(1.f);
			action.setAmountX(-250);
			attackMenu.addAction(action);
			
		}
		
	}

}
