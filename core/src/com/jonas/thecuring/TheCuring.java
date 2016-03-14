package com.jonas.thecuring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Server;
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
		Assets.getInstance().load("font_medium", "Fontmedium.fnt",BitmapFont.class);
		Assets.getInstance().load("font_small", "Fontsmall.fnt",BitmapFont.class);
		Assets.getInstance().load("shadow", "Shadow.png", Texture.class);
		Assets.getInstance().load("choice_middle", "ChoiceMiddle.png", Texture.class);
		Assets.getInstance().load("choice_bottom", "ChoiceBottom.png", Texture.class);
		Assets.getInstance().load("choice_top", "ChoiceTop.png", Texture.class);
		Assets.getInstance().load("choice_item", "ChoiceItem.png", Texture.class);
		Assets.getInstance().load("choice_middle_big", "ChoiceMiddleHuge.png", Texture.class);
		Assets.getInstance().load("choice_bottom_big", "ChoiceBottomHuge.png", Texture.class);
		Assets.getInstance().load("choice_top_big", "ChoiceTopHuge.png", Texture.class);
		Assets.getInstance().load("choice_item_big", "ChoiceItemHuge.png", Texture.class);
		Assets.getInstance().load("room_home_daughter", "RoomDaughter.png", Texture.class);
		Assets.getInstance().load("room_home_outside","RoomOutside.png",Texture.class);
		Assets.getInstance().load("daughter","Daughter.png",Texture.class);
		Assets.getInstance().load("bed_hospital", "HospitalBed.png", Texture.class);
		Assets.getInstance().load("doctor", "Doctor.png", Texture.class);
		Assets.getInstance().load("doctor_anim", "DoctorAnim.png", Texture.class);
		Assets.getInstance().load("room_bar_1beer", "Bar1Bier.png", Texture.class);
		Assets.getInstance().load("room_bar_6beer", "Bar6Bier.png", Texture.class);
		Assets.getInstance().load("sky_parachute", "Sky.png", Texture.class);
		Assets.getInstance().load("plane_parachute", "Flugzeug_anim.png", Texture.class);
		Assets.getInstance().load("room_home_son", "Boyroom.png", Texture.class);
		Assets.getInstance().load("room_work_anteroom", "WorkAnteroom.png", Texture.class);
		Assets.getInstance().load("room_work_working", "RoomWorking.png", Texture.class);
		Assets.getInstance().load("coworker", "Coworker.png", Texture.class);
		Assets.getInstance().load("son", "Son.png", Texture.class);
		Assets.getInstance().load("wife", "Woman.png", Texture.class);
		Assets.getInstance().load("wife_crying", "WifeCrying.png", Texture.class);
		Assets.getInstance().load("memory", "Memory.png", Texture.class);
		Assets.getInstance().load("memory_back", "MemoryBack.png", Texture.class);
		Assets.getInstance().load("memory_selected", "MemorySelected.png", Texture.class);
		Assets.getInstance().load("memory_background", "MemoryBackground.png", Texture.class);
		Assets.getInstance().load("sky_parachute_jump", "SkyParachute.png", Texture.class);
		Assets.getInstance().load("parachute_animation", "ParachuteAnimation.png", Texture.class);
		Assets.getInstance().load("room_hospital_op", "RoomOpSaal.png", Texture.class);
		Assets.getInstance().load("bed_empty", "Bed_empty.png", Texture.class);
		Assets.getInstance().load("room_hospital_friends", "RoomKrankenhaus_Freunde.png", Texture.class);
		Assets.getInstance().load("room_hospital_parents", "RoomKrankenhaus_Eltern.png", Texture.class);
		Assets.getInstance().load("room_hospital_family", "RoomKrankenhaus_Familie.png", Texture.class);
		Assets.getInstance().load("room_park","Park.png",Texture.class);
		Assets.getInstance().load("room_graveyard","Graveyard.png",Texture.class);
		Assets.getInstance().load("room_birthday","Birthday.png",Texture.class);
		Assets.getInstance().finishLoading();
		ScreenManager.getInstance().init(this);
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		Styles.getInstance().init();
		ScreenManager.getInstance().show(ScreenEnum.STORY_GAME,inputMultiplexer,new Server());
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
		super.render();
	}

	@Override
	public void dispose() {
		Assets.getInstance().dispose();
		super.dispose();
	}
	
}
