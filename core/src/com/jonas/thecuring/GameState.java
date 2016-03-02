package com.jonas.thecuring;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonas.thecuring.ui.AnimatedImage;
import com.jonas.thecuring.ui.ProgressBarAdvanced;
import com.jonas.thecuring.ui.Styles;
		

public class GameState extends ChangeListener implements Screen,Observer{
	
	private Image background;
	private Stage stage;
	private AssetManager manager;
	private AttackMenu attackMenu;
	private DefenseMenu defenseMenu;
	private HideMenu hideMenu;
	private Model model;
	private float scale;
	private FitViewport view;
	private ProgressBarAdvanced cancerProgress;
	private Styles styles;
	public GameState(OrthographicCamera camera,AssetManager manager)
	{
		scale = 4.f;
		this.manager = manager;
		view = new FitViewport(320*scale, 180*scale);
		stage = new Stage(view);
		Gdx.input.setInputProcessor(stage);
		
		model = new Model();
		model.addObserver(this);
		
		background = new Image((Texture) this.manager.get("Background.png"));
		stage.addActor(background);
		
		styles = new Styles(manager);
		
		TooltipManager tooltipManager = new TooltipManager();
		tooltipManager.offsetX = 2;
		tooltipManager.offsetY =2;
		tooltipManager.animations = true;
		tooltipManager.initialTime = 2f;
		tooltipManager.hideAll();
		tooltipManager.resetTime =2f;
		tooltipManager.subsequentTime = 1.0f;
		
		//Body
		int frame_rows = 2;
		int frame_cols = 4;
		TextureRegion [][] tmp = TextureRegion.split((Texture) manager.get("NewChar_anim.png"),(int) (128*scale), (int)(128*scale));
		TextureRegion[] frames = new TextureRegion[frame_rows*frame_cols];
		int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        
        Animation anim = new Animation(0.300f, frames);
        AnimatedImage body = new AnimatedImage(anim);
        body.setPosition(180*scale, 27*scale);
        stage.addActor(body);
        
        hideMenu = new HideMenu(styles,manager,this);
        hideMenu.setPosition(18*scale, (180-97)*4);
        stage.addActor(hideMenu);
        
        defenseMenu = new DefenseMenu(styles,manager,tooltipManager,this);
        defenseMenu.setPosition((18-171)*scale,(180-130)*scale);
        stage.addActor(defenseMenu);
        
		attackMenu = new AttackMenu(styles, manager,tooltipManager,this);
		attackMenu.setPosition((18-171)*scale, (180-130)*scale);
		stage.addActor(attackMenu);
		
		cancerProgress = new ProgressBarAdvanced(0, 100, 1, false, styles.progressBarStyle);
		cancerProgress.setSize(540, 56);
		cancerProgress.setPosition(27*scale, 27*scale);
		stage.addActor(cancerProgress);
		popupDialog("Test123",stage.getWidth()/2.f,stage.getHeight()/2.f);
		this.update(null, null);
			
	}
	
	private void popupDialog(String message,float x,float y)
	{
		Dialog d = new Dialog("", styles.windowStyle);
		d.setSize(160*scale, 90*scale);
		d.setPosition(x-d.getWidth()/2.f,y-d.getHeight()/2.f);
		d.setOrigin(Align.center);
		TextButton tb = new TextButton("Okay",styles.smallButton);

		tb.setName("Button");
		d.getButtonTable().padTop(10*scale);
		d.getButtonTable().padBottom(8*scale);
		d.button(tb);
		
		Label l = new Label(message, styles.numberLabel);
		l.setAlignment(Align.center);
		
		d.getContentTable().padTop(10*scale);
		d.getContentTable().top();
		d.text(l);
		
		stage.addActor(d);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);

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
	
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		System.out.println(actor.getName());
		if(actor.getName().equals("a_aggressive"))
		{
			attackMenu.setTouchable(Touchable.disabled);
			attackMenu.addAction(Actions.sequence(Actions.sequence(Actions.moveBy(-171*scale,0,1.f,Interpolation.pow2)),Actions.run(new Runnable() {
				@Override
				public void run() {
					defenseMenu.addAction(Actions.sequence(Actions.moveBy(171*scale,0,1.f,Interpolation.pow2)));
					defenseMenu.setTouchable(Touchable.enabled);
				}
			})));
		}
		else if(actor.getName().equals("d_defensive"))
		{
			defenseMenu.setTouchable(Touchable.disabled);
			defenseMenu.addAction(Actions.sequence(Actions.sequence(Actions.moveBy(-171*scale,0,1.f,Interpolation.pow2)),Actions.run(new Runnable() {
				@Override
				public void run() {
					hideMenu.addAction(Actions.sequence(Actions.moveBy(171*scale,0,1.f,Interpolation.pow2)));
					hideMenu.setTouchable(Touchable.enabled);
				}
			})));
		}
		else if(actor.getName().equals("h_hide"))
		{
			hideMenu.setTouchable(Touchable.disabled);
			hideMenu.addAction(Actions.sequence(Actions.moveBy(-171*scale,0,1.f,Interpolation.pow2),Actions.run(new Runnable() {
				@Override
				public void run() {
					
					attackMenu.addAction(Actions.moveBy(171*scale,0,1.f,Interpolation.pow2));
					attackMenu.setTouchable(Touchable.enabled);
				}
			})));
		}
		else
		{
			model.processEvent(actor.getName());
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		attackMenu.a_label_1.label.setText(String.valueOf(model.aMoreBloodvessels));
		attackMenu.a_label_2.label.setText(String.valueOf(model.aMutations));
		attackMenu.a_label_3.label.setText(String.valueOf(model.aFastCellDivision));
		attackMenu.a_label_4.label.setText(String.valueOf(model.aDangerousMutations));
		attackMenu.a_label_5.label.setText(String.valueOf(model.aMetastases));
		attackMenu.a_label_6.label.setText(String.valueOf(model.aAttacksTissue));
		
		defenseMenu.d_label_1.label.setText(String.valueOf(model.dAntiTumorSuppressor));
		defenseMenu.d_label_2.label.setText(String.valueOf(model.dShield));
		defenseMenu.d_label_3.label.setText(String.valueOf(model.dInvincibleForBody));
		defenseMenu.d_label_4.label.setText(String.valueOf(model.dRoubustnessVsMedicine));
		defenseMenu.d_label_5.label.setText(String.valueOf(model.dStrengthVsMedicine));
		defenseMenu.d_label_6.label.setText(String.valueOf(model.dHypoxia));
		
		hideMenu.h_label_1.label.setText(String.valueOf(model.hSimilarityToBodycells));
		hideMenu.h_label_2.label.setText(String.valueOf(model.hDestroyIncomingImunecells));
		
		defenseMenu.kdAntiTumorSuppressor = model.kdAntiTumorSuppressor;
		defenseMenu.kdHypoxia = model.kdHypoxia;
		defenseMenu.kdInvincibleForBody = model.kdInvincibleForBody;
		defenseMenu.kdRoubustnessVsMedicine = model.kdRoubustnessVsMedicine;
		defenseMenu.kdShield = model.kdShield;
		defenseMenu.kdStrengthVsMedicine = model.kdStrengthVsMedicine;
		
	}

}
