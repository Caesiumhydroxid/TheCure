package com.jonas.thecuring;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Client;
import com.jonas.thecuring.ui.Styles;

public class ConnectingScreenClient implements Screen{
	Stage stage;
	private List<InetAddress> addresses;
	private final ExecutorService exec;
	private Label l;
	private FitViewport viewport;
	private TextButton button;
	private InetAddress address;
	private boolean found;
	private Client client;
	private final CompletionService<List<InetAddress>> ecs;
	private Future<List<InetAddress>> searchingOpponent;
	ConnectingScreenClient(InputMultiplexer inputMultiplexer)
	{
		viewport = new FitViewport(320*3, 180*3);
		stage = new Stage(new FitViewport(320*3, 180*3));
		inputMultiplexer.addProcessor(stage);
		client = new Client();
		Image i = new Image((Texture) Assets.getInstance().get("background"));
		stage.addActor(i);
		l = new Label("Player 2",Styles.getInstance().numberLabel);
		stage.addActor(l);
		addresses = Collections.synchronizedList(new ArrayList<InetAddress>());
		exec = Executors.newFixedThreadPool(10);
		ecs = new ExecutorCompletionService<List<InetAddress>>(exec);
		searchingOpponent = ecs.submit(new searchForOpponent(48001));
		
		Table table = new Table();
		table.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
		button = new TextButton("Verbinden", Styles.getInstance().bigButton);
		button.setDisabled(true);
		
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				registerToServer(address);
			}
		});
		table.add(button);
		stage.addActor(table);
	}
	
	private void registerToServer(InetAddress addr)
	{
		try {
			client.start();
			client.connect(1000, addr, 48000,48001);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void show() {
		
	}

	private void update() 
	{
		if(searchingOpponent.isDone()&&!found)
		{
			try {
				List<InetAddress> list = searchingOpponent.get();
				if(list.isEmpty())
				{
					searchingOpponent = ecs.submit(new searchForOpponent(48001));
				}
				else
				{
					found = true;
					button.setDisabled(false);
					address = list.get(0);
					list.clear();
				}
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
			} catch (ExecutionException e) {
				System.out.println("Exec");
			}
		}
	}
	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}
	private class searchForOpponent implements Callable<List<InetAddress>>
	{
		private int port;
		private List<InetAddress> addresses;
		public searchForOpponent(int port) {
			this.port = port;
		}
		@Override
		public List<InetAddress> call() {
			Client client = new Client();
			client.start();
			List<InetAddress> list = client.discoverHosts(port, 1000);
			client.stop();
			try {
				client.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
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
