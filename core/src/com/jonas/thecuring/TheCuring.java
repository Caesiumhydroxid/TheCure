package com.jonas.thecuring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jonas.thecuring.ui.Styles;

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
		assetManager.load("Button_up.png",Texture.class);
		assetManager.load("Button_down.png",Texture.class);
		assetManager.load("Background.png",Texture.class);
		assetManager.load("Font.fnt",BitmapFont.class);
		assetManager.load("Fontsmall.fnt",BitmapFont.class);
		assetManager.load("Button_small_down.png",Texture.class);
		assetManager.load("Button_small_up.png",Texture.class);
		assetManager.load("Connections_Attack.png",Texture.class);
		assetManager.load("Connections_Defense.png",Texture.class);
		assetManager.load("Label_Number.png",Texture.class);
		assetManager.load("Tooltip.png",Texture.class);
		assetManager.load("NewChar_anim.png",Texture.class);
		assetManager.load("HugeButton_up.png",Texture.class);
		assetManager.load("HugeButton_down.png",Texture.class);
		assetManager.load("Connections_Hide.png",Texture.class);
		assetManager.load("ProgressBar.png",Texture.class);
		assetManager.load("ProgressBarKnob.png",Texture.class);
		assetManager.load("PopupMessage.png",Texture.class);
		assetManager.load("Credit.png",Texture.class);
		
		assetManager.finishLoading();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		Styles styles = new Styles(assetManager);
		
		gameState = new GameState(assetManager,styles,inputMultiplexer);
		MenuState menuState = new MenuState(assetManager,styles,inputMultiplexer );
		game.setScreen(menuState);
	}

	@Override
	public void render () {
		super.render();
	}
}
