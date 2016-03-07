package com.jonas.thecuring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jonas.thecuring.cancerGame.CancerGameState;
import com.jonas.thecuring.ui.Styles;

public class TheCuring extends Game {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	CancerGameState gameState;
	Game game;
	@Override
	public void create () {
		game = this;
		camera = new OrthographicCamera(256,144);
		camera.position.set(camera.viewportWidth/2.f,camera.viewportHeight/2.f,0);
		camera.update();
		Assets.getInstance().init(new AssetManager());
		Assets.getInstance().load("button_up", "Button_up.png",Texture.class);
		Assets.getInstance().load("button_down", "Button_down.png",Texture.class);
		Assets.getInstance().load("button_small_down", "Button_small_down.png",Texture.class);
		Assets.getInstance().load("button_small_up", "Button_small_up.png",Texture.class);
		Assets.getInstance().load("background", "Background.png",Texture.class);
		Assets.getInstance().load("connections_attack", "Connections_Attack.png",Texture.class);
		Assets.getInstance().load("connections_defense", "Connections_Defense.png",Texture.class);
		Assets.getInstance().load("label_number", "Label_Number.png",Texture.class);
		Assets.getInstance().load("tooltip", "Tooltip.png",Texture.class);
		Assets.getInstance().load("anatomic_animation", "NewChar_anim.png",Texture.class);
		Assets.getInstance().load("button_huge_up", "HugeButton_up.png",Texture.class);
		Assets.getInstance().load("button_huge_down", "HugeButton_down.png",Texture.class);
		Assets.getInstance().load("connections_hide", "Connections_Hide.png",Texture.class);
		Assets.getInstance().load("progressbar", "ProgressBar.png",Texture.class);
		Assets.getInstance().load("progressbar_knob", "ProgressBarKnob.png",Texture.class);
		Assets.getInstance().load("popup", "PopupMessage.png",Texture.class);
		Assets.getInstance().load("credit_animation", "Credit.png",Texture.class);
		Assets.getInstance().load("room_home", "RoomHome.png",Texture.class);
		Assets.getInstance().load("room_hospital", "RoomHospital.png",Texture.class);
		Assets.getInstance().load("room_home_anteroom", "RoomHomeAnteroom.png",Texture.class);
		Assets.getInstance().load("room_work", "RoomWork.png",Texture.class);
		Assets.getInstance().load("maincharacter_animation", "MainCharacter.png",Texture.class);
		Assets.getInstance().load("maincharacter_normal", "NormalCharacter.png", Texture.class);
		Assets.getInstance().load("maincharacter_business", "BusinessCharacter.png", Texture.class);
		Assets.getInstance().load("dialogue", "DialogMessage.png", Texture.class);
		Assets.getInstance().load("font", "Font.fnt",BitmapFont.class);
		Assets.getInstance().load("font_small", "Fontsmall.fnt",BitmapFont.class);
		Assets.getInstance().load("shadow", "Shadow.png", Texture.class);
		Assets.getInstance().load("choice_middle", "ChoiceMiddle.png", Texture.class);
		Assets.getInstance().load("choice_bottom", "ChoiceBottom.png", Texture.class);
		Assets.getInstance().load("choice_top", "ChoiceTop.png", Texture.class);
		Assets.getInstance().load("choice_item", "ChoiceItem.png", Texture.class);
		Assets.getInstance().load("room_home_daughter", "RoomDaughter.png", Texture.class);
		Assets.getInstance().load("room_home_outside","RoomOutside.png",Texture.class);
		Assets.getInstance().load("daughter","Daughter.png",Texture.class);
		Assets.getInstance().finishLoading();
		ScreenManager.getInstance().init(this);
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		Styles.getInstance().init();
		ScreenManager.getInstance().show(ScreenEnum.STORY_GAME,inputMultiplexer);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		Assets.getInstance().dispose();
		super.dispose();
	}
	
}
