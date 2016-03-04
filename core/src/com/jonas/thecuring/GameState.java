package com.jonas.thecuring;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jonas.thecuring.ui.AnimatedImage;
import com.jonas.thecuring.ui.ProgressBarAdvanced;
import com.jonas.thecuring.ui.Styles;
import com.jonas.thecuring.util.Observable;
import com.jonas.thecuring.util.Observer;
		

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
	private Pool<AnimatedImage> credits;
	private Animation creditAnimation;
	private Label creditLabel;
	Group body;
	private Array<Vector2> creditPositions;
	private float elapsedTime;
	
	public class ScaleAction extends  Action
	{

		@Override
		public boolean act(float delta) {
			
			if(getActor() instanceof Group)
			{
				Group g = (Group) getActor();
				for(Actor a:g.getChildren())
				{
					a.setPosition(a.getX()*4, a.getY()*4);
					a.addAction(new ScaleAction());
				}
			}
			return true;
		}
		
	}
	
	public GameState(AssetManager manager,Styles styles,InputMultiplexer inputMultiplexer)
	{
		this.styles = styles;
		scale = 4.f;
		this.manager = manager;
		view = new FitViewport(320*scale , 180*scale );
		
		stage = new Stage(view);
		inputMultiplexer.addProcessor(stage);
		
		creditPositions = new Array<Vector2>(new Vector2[]{new Vector2(58,46),new Vector2(57,61),new Vector2(61,114),new Vector2(63,68),new Vector2(35,73)});
		
		this.creditAnimation = createAnimationFromTexture(0.2f, (Texture)manager.get("Credit.png"), 4, 1);
		credits = new Pool<AnimatedImage>() {
			@Override
			protected AnimatedImage newObject() {
				return new AnimatedImage(creditAnimation);
			}
		};
		
		model = new Model();
		model.addObserver(this);
		
		background = new Image((Texture) this.manager.get("Background.png"));
		stage.addActor(background);
		
		
		TooltipManager tooltipManager = new TooltipManager()
		{

			@Override
			public void hide(Tooltip tooltip) {
				// TODO Auto-generated method stub
				super.hide(tooltip);
			}
			
		};
		tooltipManager.offsetX = 2;
		tooltipManager.offsetY =2;
		tooltipManager.animations = true;
		tooltipManager.initialTime = 0.2f;
		tooltipManager.hideAll();
		tooltipManager.resetTime =0.5f;
		tooltipManager.subsequentTime = 2.0f;
		
		
		//Body
		body = new Group();
        AnimatedImage bodyImage = new AnimatedImage(0.3f,(Texture)manager.get("NewChar_anim.png"),4,2);
        body.addActor(bodyImage);
        body.setPosition(180 , 27 );
        stage.addActor(body);
        
        creditLabel = new Label("Credits: 0",styles.numberLabel);
        creditLabel.setPosition(222 , 163 );
        stage.addActor(creditLabel);
     
        hideMenu = new HideMenu(styles,manager,tooltipManager,this);
        hideMenu.setPosition(18 , (180-97));
        stage.addActor(hideMenu);
        
        defenseMenu = new DefenseMenu(styles,manager,tooltipManager,this);
        defenseMenu.setPosition((18-171) ,(180-130) );
        stage.addActor(defenseMenu);
        
		attackMenu = new AttackMenu(styles, manager,tooltipManager,this);
		attackMenu.setPosition((18-171) , (180-130) );
		stage.addActor(attackMenu);
		
		cancerProgress = new ProgressBarAdvanced(0, 100, 1, false, styles.progressBarStyle);
		cancerProgress.setSize(540, 56);
		cancerProgress.setPosition(27 , 27 );
		cancerProgress.setAnimateInterpolation(Interpolation.pow2);
		cancerProgress.setAnimateDuration(0.5f);
		stage.addActor(cancerProgress);
		
		//popupDialog("Test123",stage.getWidth()/2.f,stage.getHeight()/2.f);

		stage.addAction(new ScaleAction());
		this.update(null, null);
			
	}
	private Animation createAnimationFromTexture(float frameDuration,Texture texture,int columns,int rows)
	{
		int frame_rows = rows;
		int frame_cols = columns;
		TextureRegion [][] tmp = TextureRegion.split(texture,texture.getWidth()/columns, texture.getHeight()/rows);
		TextureRegion[] frames = new TextureRegion[frame_rows*frame_cols];
		int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
       return new Animation(frameDuration,frames);
	}
	
	class freeCredit implements Runnable
	{

		Pool<AnimatedImage> p;
		AnimatedImage imag;
		freeCredit(Pool<AnimatedImage> p,AnimatedImage imag)
		{
			this.imag = imag;
			this.p = p;
		}
		@Override
		public void run() {
			imag.clear();
			imag.remove();
		
			p.free(imag);	
		}
	}
	private void createRandomCredit()
	{
		AnimatedImage credit = credits.obtain();
		credit.setColor(1, 1, 1, 1);
		credit.setTouchable(Touchable.enabled);
		Vector2 tmp =creditPositions.random();
		credit.setPosition(tmp.x *scale,tmp.y *scale);
		
		credit.addAction(Actions.sequence(Actions.delay(5), Actions.fadeOut(0.5f),Actions.run(new freeCredit(credits,credit))));
        credit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				event.getListenerActor().clearActions();
				event.getListenerActor().setTouchable(Touchable.disabled);
				event.getListenerActor().addAction(Actions.sequence(Actions.fadeOut(0.5f),Actions.run(new freeCredit(credits,(AnimatedImage)event.getListenerActor()))));
				model.addCredit();
				super.clicked(event, x, y);
			}
        	
        });
        body.addActor(credit);
		
	}
	private void popupDialog(String message,float x,float y)
	{
		Dialog d = new Dialog("", styles.windowStyle);
		d.setSize(160 * scale, 90 *scale);
		d.setPosition(x-d.getWidth()/2.f,y-d.getHeight()/2.f);
		d.setOrigin(Align.center);
		TextButton tb = new TextButton("Okay",styles.smallButton);

		tb.setName("Button");
		d.getButtonTable().padTop(10 *scale);
		d.getButtonTable().padBottom(8 *scale);
		d.button(tb);
		
		Label l = new Label(message, styles.numberLabel);
		l.setAlignment(Align.center);
		
		d.getContentTable().padTop(10 *scale);
		d.getContentTable().top();
		d.text(l);
		
		stage.addActor(d);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	int cnt;
	@Override
	public void render(float delta) {
		stage.act(delta);
		elapsedTime += delta;
		if(elapsedTime>5)
		{
			model.tick(1);
			createRandomCredit();
			elapsedTime = 0;
		}
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
			attackMenu.addAction(Actions.sequence(Actions.sequence(Actions.moveBy(-171 *scale,0,1.f,Interpolation.pow2)),Actions.run(new Runnable() {
				@Override
				public void run() {
					defenseMenu.addAction(Actions.sequence(Actions.moveBy(171 *scale,0,1.f,Interpolation.pow2)));
					defenseMenu.setTouchable(Touchable.enabled);
				}
			})));
		}
		else if(actor.getName().equals("d_defensive"))
		{
			defenseMenu.setTouchable(Touchable.disabled);
			defenseMenu.addAction(Actions.sequence(Actions.sequence(Actions.moveBy(-171 *scale,0,1.f,Interpolation.pow2)),Actions.run(new Runnable() {
				@Override
				public void run() {
					hideMenu.addAction(Actions.sequence(Actions.moveBy(171 *scale,0,1.f,Interpolation.pow2)));
					hideMenu.setTouchable(Touchable.enabled);
				}
			})));
		}
		else if(actor.getName().equals("h_hide"))
		{
			hideMenu.setTouchable(Touchable.disabled);
			hideMenu.addAction(Actions.sequence(Actions.moveBy(-171 *scale,0,1.f,Interpolation.pow2),Actions.run(new Runnable() {
				@Override
				public void run() {
					
					attackMenu.addAction(Actions.moveBy(171 *scale,0,1.f,Interpolation.pow2));
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
		if(arg1!=null && arg1.getClass().equals(String.class))
		{
			popupDialog((String) arg1, stage.getWidth()/2.f,stage.getHeight()/2.f);
		}
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
		
		creditLabel.setText("Credits: " + model.credits);
		cancerProgress.setValue(model.progress*100);
		
	}

}
