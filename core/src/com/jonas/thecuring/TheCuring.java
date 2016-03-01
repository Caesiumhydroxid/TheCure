package com.jonas.thecuring;

import java.awt.Font;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TheCuring extends Game {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	GameState gameState;
	AssetManager assetManager;
	Game game;
	@Override
	public void create () {
		game = this;
		camera = new OrthographicCamera(256,144);
		camera.position.set(camera.viewportWidth/2.f,camera.viewportHeight/2.f,0);
		camera.update();
		assetManager = new AssetManager();
		assetManager.load("Button_Pressed.png", Texture.class);
		assetManager.load("Button.png",Texture.class);
		assetManager.load("TestGame.png",Texture.class);
		assetManager.load("Font.fnt",BitmapFont.class);
		assetManager.finishLoading();
		gameState = new GameState(camera,assetManager);
		game.setScreen(gameState);
	}

	@Override
	public void render () {
		super.render();
	}
}
