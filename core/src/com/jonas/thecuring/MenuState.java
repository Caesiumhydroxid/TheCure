package com.jonas.thecuring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonas.thecuring.ui.Styles;

class MenuState implements Screen {
	
	private Stage stage;
	private FitViewport viewport;
	
	public MenuState(InputMultiplexer inputMultiplexer) {
		viewport = new FitViewport(320*3, 180*3);
		stage = new Stage(viewport);
		inputMultiplexer.addProcessor(stage);
		stage.addActor(new Image((Texture)Assets.getInstance().get("background")));
		Table table = new Table();
		table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
		stage.addActor(table);
		stage.setDebugAll(false);
		Label label = new Label("Die\n Entscheidung",Styles.getInstance().numberLabel);
		label.setFontScale(14);
		label.setAlignment(Align.center);
		table.add(label).padBottom(100f);
		table.row();
		TextButton player1 = new TextButton("Spieler 1", Styles.getInstance().smallButton);
		table.add(player1).expandX().center().pad(4*4);
		table.row();
		TextButton player2= new TextButton("Spieler 2", Styles.getInstance().smallButton);
		table.add(player2).expandX().center();
		player1.addListener(new ChangeScreenListener(ScreenEnum.CONNECT_SERVER, inputMultiplexer,stage));
		player2.addListener(new ChangeScreenListener(ScreenEnum.CONNECT_CLIENT, inputMultiplexer,stage));
	}
	
	@Override
	public void show() {
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
