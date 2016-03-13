package com.jonas.thecuring;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.jonas.thecuring.ui.Styles;

public class ConnectingScreenServer extends Listener implements Screen {
	Stage stage;
	private Label l;
	private FitViewport viewport;
	private TextButton button;
	private boolean startGame;
	private Server server;
	ConnectingScreenServer(InputMultiplexer inputMultiplexer)
	{
		viewport = new FitViewport(320*3, 180*3);
		stage = new Stage(new FitViewport(320*3, 180*3));
		Image i = new Image((Texture) Assets.getInstance().get("background"));
		inputMultiplexer.addProcessor(stage);
		stage.addActor(i);
		
		server = new Server();
		server.getKryo().register(String.class);
		server.addListener(this);
		try {
			server.bind(48000,48001);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Table table = new Table();
		table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
		l = new Label("Wenn Spieler1 verbunden und bereit, Starten!",Styles.getInstance().numberLabel);
		table.add(l).pad(10);
		table.row();
		button = new TextButton("Starten", Styles.getInstance().bigButton);
		button.addListener(new ButtonClickListener(inputMultiplexer, this,server));
		button.setDisabled(true);
		table.add(button);
		TextButton end = new TextButton("Zurück",Styles.getInstance().bigButton);
		end.addListener(new ChangeScreenListener(ScreenEnum.MAIN_MENU, inputMultiplexer, stage){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				server.close();
				try {
					server.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				super.changed(event, actor);
			}
			
		});
		table.row();
		table.add(end).pad(5*3);
		stage.addActor(table);
	}
	
	class ButtonClickListener extends com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
	{
		private InputMultiplexer multiplexer;
		private Server server;
		private ConnectingScreenServer world;
		public ButtonClickListener(InputMultiplexer multiplexer,ConnectingScreenServer world,Server server) {
			this.server = server;
			this.multiplexer = multiplexer;
			this.world = world;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			server.sendToAllTCP("Begin");
			server.removeListener(world);
			multiplexer.removeProcessor(world.stage);
			ScreenManager.getInstance().show(ScreenEnum.STORY_GAME,multiplexer,server);
		}
		
	}
	@Override
	public void connected(Connection connection) {
		button.setDisabled(false);
		super.connected(connection);
	}

	@Override
	public void disconnected(Connection connection) {
		button.setDisabled(true);
		super.disconnected(connection);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
