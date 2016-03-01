package com.jonas.thecuring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
		

public class GameState implements Screen {
	
	private Image background;
	private Stage stage;
	private AssetManager manager;

	public GameState(OrthographicCamera camera,AssetManager manager)
	{
		this.manager = manager;
		
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4));
		Gdx.input.setInputProcessor(stage);
		background = new Image((Texture) this.manager.get("Background.png"));
		stage.addActor(background);
		
		
		Styles styles = new Styles(manager);
		
		DefenseMenu attackMenu = new DefenseMenu(styles, manager,new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(actor.getName());
				
			}
		});
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

}
