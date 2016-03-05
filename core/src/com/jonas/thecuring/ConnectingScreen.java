package com.jonas.thecuring;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private List<InetAddress> addresses;
	private Label l;
	ConnectingScreen(AssetManager manager,Styles styles,InputMultiplexer inputMultiplexer,String player)
	{
		stage = new Stage(new FitViewport(320*3, 180*3));
		Image i = new Image((Texture) manager.get("Background.png"));
		stage.addActor(i);
		l = new Label(player,styles.numberLabel);
		stage.addActor(l);
		addresses = Collections.synchronizedList(new ArrayList<InetAddress>());
		Server server = new Server();
		if(player.equals("Player 1"))
		{
			try {
				server.bind(48000,48001);
				server.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(player.equals("Player 2"))
		{
			new Thread(new searchForOpponent(48001, addresses)).start();
		}
	}
	
	private class searchForOpponent implements Runnable
	{
		private int port;
		private List<InetAddress> addresses;
		public searchForOpponent(int port,List addresses) {
			this.addresses = addresses;
		}
		@Override
		synchronized public void run() {
			Client client = new Client();
			client.start();
			addresses.addAll(client.discoverHosts(port, 5000));
			client.stop();
			try {
				client.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(addresses.get(0)!=null)
		l.setText(addresses.get(0).toString());
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
