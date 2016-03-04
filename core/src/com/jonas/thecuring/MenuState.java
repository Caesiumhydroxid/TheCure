package com.jonas.thecuring;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonas.thecuring.ui.Styles;

public class MenuState implements Screen {
	
	private Stage stage;
	private FitViewport viewport;
	private AssetManager manager;
	public MenuState(AssetManager manager,Styles styles,InputMultiplexer inputMultiplexer) {
		this.manager = manager;
		viewport = new FitViewport(320*4, 180*4);
		stage = new Stage();
		inputMultiplexer.addProcessor(stage);
		stage.addActor(new Image((Texture)manager.get("Background.png")));
		Table table = new Table();
		table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
		stage.addActor(table);
		stage.setDebugAll(false);
		Label label = new Label("The Curing",styles.numberLabel);
		label.setFontScale(4);
		table.add(label).padBottom(100f);
		table.row();
		table.add(new TextButton("Player 1", styles.smallButton)).expandX().center().pad(4*4);
		table.row();
		table.add(new TextButton("Player 2", styles.smallButton)).expandX().center();
	}
	
	@Override
	public void show() {
		

	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
