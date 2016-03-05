package com.jonas.thecuring;

import java.io.IOException;
import java.net.InetAddress;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import com.jonas.thecuring.ui.Styles;

public class ConnectingScreen implements Screen{
	Stage stage;
	ConnectingScreen(AssetManager manager,Styles styles,InputMultiplexer inputMultiplexer,String player)
	{
		stage = new Stage(new FitViewport(320*3, 180*3));
		Image i = new Image((Texture) manager.get("Background.png"));
		stage.addActor(i);
		Label l = new Label(player,styles.numberLabel);
		stage.addActor(l);
		Server server = new Server();
		if(player.equals("Player 1"))
		{
			try {
				server.bind(48000,48001);
				server.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			l.setText(searchForOpponent(48003).toString());
		}
		else if(player.equals("Player 2"))
		{
			try {
				server.bind(48002,48003);
				server.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			l.setText(searchForOpponent(48000).toString());
		}
	}
	private InetAddress searchForOpponent(int port)
	{
		Client client = new Client();
		client.start();
		return client.discoverHost(port, 5000);
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
