package com.jonas.thecuring;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
		

public class GameState implements Screen {
	
	private TestFrame frame;
	private Stage stage;
	private AssetManager manager;
	private TextButtonStyle smallButton;
	private TextButtonStyle bigButton;
	private TextButton a_button_0;
	private TextButton a_button_1;
	private TextButton a_button_2;
	private TextButton a_button_3;
	private TextButton a_button_4;
	private TextButton a_button_5;
	private TextButton a_button_6;
	private Group attack;
	public GameState(OrthographicCamera camera,AssetManager manager)
	{
		this.manager = manager;
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4));
		Gdx.input.setInputProcessor(stage);
		attack = new Group();
		frame = new TestFrame();
		stage.addActor(frame);
		TextButtonStyle bigButton = new TextButtonStyle();
		
		BitmapFont font = manager.get("Font.fnt");
		bigButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button.png")));
		bigButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_Pressed.png")));
		bigButton.font = font;
		bigButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		bigButton.fontColor = Color.BLACK;
		
		a_button_0 = new TextButton("Aggressiv",bigButton);
		a_button_0.setPosition(46, 119-13);
		attack.addActor(a_button_0);
		
		a_button_1 = new TextButton("Mehr \nBlutk�rper",bigButton);
		a_button_1.setPosition(5, 119-43);
		
		attack.addActor(a_button_1);
		
		a_button_2 = new TextButton("Mutationen",bigButton);
		a_button_2.setPosition(82, 119-43);
		attack.addActor(a_button_2);
		
		a_button_3 = new TextButton("schnelle \nZellteilung",bigButton);
		a_button_3.setPosition(5, 119-69);
		attack.addActor(a_button_3);
		
		a_button_4 = new TextButton("gef�hrliche\nMutationen",bigButton);
		a_button_4.setPosition(82, 119-69);
		attack.addActor(a_button_4);
		
		a_button_5 = new TextButton("Metastasen",bigButton);
		a_button_5.setPosition(44, 119-94);
		attack.addActor(a_button_5);
		
		a_button_6 = new TextButton("greift\nGewebe an",bigButton);
		a_button_6.setPosition(44, 119-119);
		attack.addActor(a_button_6);
		
		attack.setPosition(18, 180-143);
		stage.addActor(attack);
		
	
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void render(float delta) {
		attack.act(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
