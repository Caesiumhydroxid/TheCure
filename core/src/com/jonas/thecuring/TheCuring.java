package com.jonas.thecuring;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TheCuring extends Game {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	GameState gameState;
	Game game;
	@Override
	public void create () {
		game = this;
		camera = new OrthographicCamera(256,144);
		camera.position.set(camera.viewportWidth/2.f,camera.viewportHeight/2.f,0);
		camera.update();
		gameState = new GameState(camera);
		game.setScreen(gameState);
	}

	@Override
	public void render () {
		super.render();
	}
}
