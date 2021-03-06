package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Server;

public class StoryGameState implements Screen {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private World world;
	private ShaderProgram shader;
	private FitViewport viewport;
	private int a;
	private int u_time;
	private int u_noise;
	private float elapsedTime;
	public StoryGameState(InputMultiplexer inputMultiplexer,Server server) {
		server.getKryo().register(String.class);
		server.getKryo().register(Integer.class);
		server.getKryo().register(Boolean.class);
		world = new World(server,inputMultiplexer);
		batch = new SpriteBatch();
		camera = new OrthographicCamera(160, 90);
		camera.setToOrtho(false, 160, 90);
		viewport = new FitViewport(160, 90,camera);
		String vertexShader = Gdx.files.internal("shaders/vertex.glsl").readString();
		String fragmentShader = Gdx.files.internal("shaders/fragment.glsl").readString();
		shader = new ShaderProgram(vertexShader, fragmentShader);
		shader.begin();
		shader.end();
		System.out.println(shader.getLog());
		a = shader.getUniformLocation("u_grey");
		u_time = shader.getUniformLocation("u_time");
		u_noise = shader.getUniformLocation("u_noise");
		batch.setShader(shader);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		elapsedTime+=delta;
		world.update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float noise = world.day*0.045f;
		float saturation = 1-world.day*0.10f;
		shader.begin();
		shader.setUniformf(a, saturation);
		shader.setUniformf(u_time,elapsedTime*MathUtils.random());
		shader.setUniformf(u_noise,noise);
		shader.end();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		world.render(batch);
		batch.end();

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
