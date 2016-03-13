package com.jonas.thecuring.cancerGame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.ScreenEnum;
import com.jonas.thecuring.ScreenManager;
import com.jonas.thecuring.ui.AnimatedImage;
import com.jonas.thecuring.ui.ProgressBarAdvanced;
import com.jonas.thecuring.ui.Styles;
import com.jonas.thecuring.util.Observable;
import com.jonas.thecuring.util.Observer;
		

public class CancerGameState extends ChangeListener implements Screen,Observer{
	
	private Image background;
	private Stage stage;
	private AttackMenu attackMenu;
	private DefenseMenu defenseMenu;
	private Model model;
	private float scale=3;
	private FitViewport view;
	private ProgressBarAdvanced cancerProgress;
	private Pool<AnimatedImage> credits;
	private Animation creditAnimation;
	private Label creditLabel;
	Group body;
	private boolean showingEndScreen=false;
	private Array<Vector2> creditPositions;
	private float elapsedTime;
	private Client client;
	private Texture texture;
	private InputMultiplexer mulitplexer;
	class ClientListener extends Listener
	{
		private CancerGameState world;
		public ClientListener(CancerGameState world) {
			this.world = world;
		}
		@Override
		public void connected(Connection connection) {
			// TODO Auto-generated method stub
			super.connected(connection);
		}

		@Override
		public void disconnected(Connection connection) {
			world.popupDialog("Verbindung zu anderem Spieler verloren! Bitte neustarten",320*scale /2f, 180*scale /2f);
			super.disconnected(connection);
		}

		@Override
		public void received(Connection connection, Object object) {
			if(object instanceof String)
				world.popupDialog((String)object,stage.getWidth()/2.f,stage.getHeight()/2.f);
			if(object instanceof Integer)
			{
				
				if(((Integer)object).intValue()==1000)
				{
					client.sendTCP(model.willDie);
					model.sentMessage = true;
					System.out.println(model.willDie);
				}
				else
				{
					model.day = ((Integer)object).intValue();
					System.out.println(model.day);
				}
			}
			super.received(connection, object);
		}
		
	}
	public class ScaleAction extends  Action
	{

		@Override
		public boolean act(float delta) {
			
			if(getActor() instanceof Group)
			{
				Group g = (Group) getActor();
				for(Actor a:g.getChildren())
				{
					a.setPosition(a.getX()*scale, a.getY()*scale);
					a.addAction(new ScaleAction());
				}
			}
			return true;
		}
		
	}
	
	public CancerGameState(InputMultiplexer inputMultiplexer,Client client)
	{
		scale = 3.f;
		view = new FitViewport(320*scale , 180*scale );
		this.mulitplexer = inputMultiplexer;
		this.client = client;
		ClientListener listener = new ClientListener(this);
		client.addListener(listener);
		client.getKryo().register(String.class);
		client.getKryo().register(Integer.class);
		client.getKryo().register(Boolean.class);
		stage = new Stage(view);
		inputMultiplexer.addProcessor(stage);
		Pixmap map = new Pixmap((int)(320*scale),(int) (180*scale),Format.RGBA8888);
		map.setColor(Color.BLACK);
		map.fill();
		texture = new Texture(map);
		creditPositions = new Array<Vector2>(new Vector2[]{
				new Vector2(58,46),new Vector2(57,61),new Vector2(61,114),new Vector2(63,68),new Vector2(35,73),new Vector2(46,13),new Vector2(98,74),new Vector2(62,95)
				});
		
		this.creditAnimation = createAnimationFromTexture(0.2f, (Texture)Assets.getInstance().get("credit_animation"), 4, 1);
		credits = new Pool<AnimatedImage>() {
			@Override
			protected AnimatedImage newObject() {
				return new AnimatedImage(creditAnimation);
			}
		};
		
		model = new Model();
		model.addObserver(this);
		background = new Image((Texture) Assets.getInstance().get("background"));
		stage.addActor(background);
		
		TooltipManager tooltipManager = new TooltipManager();
		tooltipManager.offsetX = 2;
		tooltipManager.offsetY =2;
		tooltipManager.animations = true;
		tooltipManager.initialTime = 0.5f;
		tooltipManager.hideAll();
		tooltipManager.resetTime =0.5f;
		tooltipManager.subsequentTime = 1.0f;
		
		//Body
		body = new Group();
        AnimatedImage bodyImage = new AnimatedImage(0.3f,(Texture)Assets.getInstance().get("anatomic_animation"),4,2);
        body.addActor(bodyImage);
        body.setPosition(180 , 27 );
        stage.addActor(body);
        
        creditLabel = new Label("Credits: 0",Styles.getInstance().numberLabel);
        creditLabel.setPosition(222 , 163 );
        stage.addActor(creditLabel);
     

        
        defenseMenu = new DefenseMenu(tooltipManager,this);
        defenseMenu.setPosition((18-171) ,(180-130) );
        stage.addActor(defenseMenu);
        
		attackMenu = new AttackMenu(tooltipManager,this);
		attackMenu.setPosition((18) , (180-130) );
		stage.addActor(attackMenu);
		
		cancerProgress = new ProgressBarAdvanced(0, 100, 1, false, Styles.getInstance().progressBarStyle);
		cancerProgress.setSize(135*scale, 14*scale);
		cancerProgress.setPosition(27 , 27 );
		cancerProgress.setAnimateInterpolation(Interpolation.pow2);
		cancerProgress.setAnimateDuration(0.5f);
		stage.addActor(cancerProgress);
		
		stage.addAction(new ScaleAction());
		
		Table table = new Table();
		table.setSize(view.getWorldWidth(), view.getWorldHeight());
		Image im = new Image(texture);
		table.addActor(im);
		
		Label label = new Label("",Styles.getInstance().whiteLabel);
		label.setFontScale(6);
		label.setAlignment(Align.center);
		label.setWrap(true);
		String krebs = "Deine Aufgabe ist es, das Subjekt möglichst schnell zu töten. Du bist ein Gehirntumor und kannst deine Fähigkeiten verbessern.";
		String beschr = "Dies machst du mit Credits, welche du einsammeln musst. (Sie erscheinen am Körper)\n"
				+ "Manche Fähigkeiten können erst mit gewissen Anforderungen aufgelevelt werden. ";
		String beschr2 =	"Diese siehst du, "
				+ "wenn du mit der Maus über die Fähigkeit fährst. Am obersten Knopf wechselst du zwischen Defensiven und Aggresiven Fähigkeiten";
		String beschr3 = "Viel Erfolg!";
		label.setText(krebs);
		table.row();
		table.add(label).width(320*scale-10*scale);
		table.addAction(Actions.sequence(Actions.delay(7),Actions.alpha(0, 0.7f,Interpolation.sineIn),
				new ChangeTextAction(beschr, label),Actions.alpha(1, 0.7f,Interpolation.sineIn),
				Actions.delay(7),Actions.alpha(0, 0.7f,Interpolation.sineIn),
				new ChangeTextAction(beschr2, label),Actions.alpha(1, 0.7f,Interpolation.sineIn),
				Actions.delay(7),Actions.alpha(0, 0.7f,Interpolation.sineIn),
				new ChangeTextAction(beschr3, label),Actions.alpha(1, 0.7f,Interpolation.sineIn),
				Actions.delay(7),Actions.alpha(0, 0.7f,Interpolation.sineIn),Actions.removeActor()));
		Image imag = new Image(texture);
		imag.addAction(Actions.sequence(Actions.delay(30),Actions.removeActor()));
		stage.addActor(imag);
		stage.addActor(table);
		//popupDialog("Test123",stage.getWidth()/2.f,stage.getHeight()/2.f);

		this.update(null, null);
			
	}
	class ChangeTextAction extends Action
	{
		String string;
		Label label;
		ChangeTextAction(String string,Label label)
		{
			this.string = string;
			this.label = label;
		}
		@Override
		public boolean act(float delta) {
			label.setText(string);
			return true;
		}
		
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
	public void popupDialog(String message,float x,float y)
	{
		Dialog d = new Dialog("", Styles.getInstance().windowStyle);
		d.setSize(160 * scale, 90 *scale);
		
		d.setPosition(x-d.getWidth()/2.f,y-d.getHeight()/2.f);
		d.setOrigin(Align.center);
		TextButton tb = new TextButton("Okay",Styles.getInstance().smallButton);

		tb.setName("Button");
		d.getButtonTable().padTop(10 *scale);
		d.getButtonTable().padBottom(8 *scale);
		d.button(tb);
		
		Label l = new Label(message, Styles.getInstance().numberLabel);
		l.setAlignment(Align.center);
		l.setWidth(10);
		l.setWrap(true);
		l.setBounds(0, 0, 160*scale-10*scale, 90*scale-10*scale);
		
		d.getContentTable().padTop(10 *scale);
		d.getContentTable().top();
		d.getContentTable().add(l).width(160*scale-10*scale);
		
		stage.addActor(d);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	private void showEndScreen(boolean willDie)
	{
		Table table = new Table();
		table.setColor(1, 1, 1, 0);
		Label label = new Label("",Styles.getInstance().whiteLabel);
		table.setSize(view.getWorldWidth(), view.getWorldHeight());
		if(willDie)
		{
			Image im = new Image((Texture)Assets.getInstance().get("room_graveyard"));
			label.setText("Das Subjekt ist gestorben!");
			im.setSize(view.getWorldWidth(), view.getWorldHeight());
			table.addActor(im);
		}
		else
		{
			Image im = new Image((Texture)Assets.getInstance().get("room_birthday"));
			label.setText("Das Subjekt hat überlebt.");
			im.setSize(view.getWorldWidth(), view.getWorldHeight());
			table.addActor(im);
		}
		Table tab = new Table();
		tab.setSize(view.getWorldWidth(), view.getWorldHeight());
		Image black = new Image(texture);
		tab.addActor(black);
		tab.setColor(1, 1, 1, 0);
		tab.addAction(Actions.alpha(1, 2, Interpolation.sineIn));
		label.setFontScale(6);
		label.setAlignment(Align.center);
		label.setWrap(true);
		tab.add(label);
		label.addAction(Actions.sequence(Actions.delay(7),Actions.alpha(0,1),Actions.delay(4),new ChangeTextAction("Berede dies mit deinem Gegenüber", label),Actions.alpha(1, 1,Interpolation.sine),Actions.delay(5),new Action() {
			
			@Override
			public boolean act(float delta) {
				mulitplexer.removeProcessor(stage);
				ScreenManager.getInstance().show(ScreenEnum.MAIN_MENU, mulitplexer);
				return false;
			}
		}));
		table.addAction(Actions.sequence(Actions.delay(7),Actions.alpha(1, 1,Interpolation.sine),Actions.delay(4),Actions.alpha(0, 1,Interpolation.sine)));
		stage.addActor(tab);
		stage.addActor(table);
	}
	int cnt;
	@Override
	public void render(float delta) {
		stage.act(delta);
		elapsedTime += delta;
		if(model.day==5)
		{
			if(!showingEndScreen)
			{
				showEndScreen(model.willDie);
				showingEndScreen = true;
			}
		}
		if(elapsedTime>5)
		{
			model.tick(1);
			createRandomCredit();
			elapsedTime = 0;
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
			defenseMenu.addAction(Actions.sequence(Actions.moveBy(-171 *scale,0,1.f,Interpolation.pow2),Actions.run(new Runnable() {
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
