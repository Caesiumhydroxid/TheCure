package com.jonas.thecuring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jonas.thecuring.ui.BackgroundLabel;

public class AttackMenu extends Table {
	private TextButton a_button_0;
	private TextButton a_button_1;
	private TextButton a_button_2;
	private TextButton a_button_3;
	private TextButton a_button_4;
	private TextButton a_button_5;
	private TextButton a_button_6;
	private BackgroundLabel a_label_1;
	private BackgroundLabel a_label_2;
	private BackgroundLabel a_label_3;
	private BackgroundLabel a_label_4;
	private BackgroundLabel a_label_5;
	private BackgroundLabel a_label_6;
	
	public AttackMenu(Styles styles, AssetManager manager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) manager.get("Label_Number.png");
		
		Image a_connections = new Image((Texture) manager.get("Connections_Attack.png"));
		this.addActor(a_connections);
		
		a_button_0 = new TextButton("Aggressiv",styles.smallButton);
		a_button_0.setPosition(46, 119-14);
		a_button_0.setName("a_aggressive");
		a_button_0.addListener(buttonListener);
		this.addActor(a_button_0);
		
		a_button_1 = new TextButton("Mehr \nBlutgefäße",styles.bigButton);
		a_button_1.setPosition(5, 119-43);
		a_button_1.setName("a_more_bloodvessels");
		a_button_1.addListener(buttonListener);
		
		a_label_1 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_1.setPosition(57, 0);
		a_label_1.label.setText("5");
		a_button_1.addActor(a_label_1);
		this.addActor(a_button_1);
		
		a_button_2 = new TextButton("Mutationen",styles.bigButton);
		a_button_2.setPosition(82, 119-43);
		a_button_2.setName("a_mutations");
		a_button_2.addListener(buttonListener);
		
		a_label_2 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_2.setPosition(57, 0);
		a_label_2.label.setText("5");
		a_button_2.addActor(a_label_2);
		
		this.addActor(a_button_2);
		
		a_button_3 = new TextButton("schnelle \nZellteilung",styles.bigButton);
		a_button_3.setPosition(5, 119-69);
		a_button_3.setName("a_fast_cell_division");
		a_button_3.addListener(buttonListener);
		
		a_label_3 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_3.setPosition(57, 0);
		a_label_3.label.setText("5");
		a_button_3.addActor(a_label_3);
		
		this.addActor(a_button_3);
		
		a_button_4 = new TextButton("gefährliche\nMutationen",styles.bigButton);
		a_button_4.setPosition(82, 119-69);
		a_button_4.setName("a_dangerous_mutations");
		a_button_4.addListener(buttonListener);
		
		a_label_4 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_4.setPosition(57, 0);
		a_label_4.label.setText("5");
		a_button_4.addActor(a_label_4);
		
		this.addActor(a_button_4);
		
		a_button_5 = new TextButton("Metastasen",styles.bigButton);
		a_button_5.setPosition(46, 119-94);
		a_button_5.setName("a_metastases");
		a_button_5.addListener(buttonListener);
		
		a_label_5 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_5.setPosition(57, 0);
		a_label_5.label.setText("5");
		a_button_5.addActor(a_label_5);
		
		this.addActor(a_button_5);
		
		a_button_6 = new TextButton("greift\nGewebe an",styles.bigButton);
		a_button_6.setPosition(46, 119-119);
		a_button_6.setName("a_attacks_tissue");
		a_button_6.addListener(buttonListener);
		
		a_label_6 = new BackgroundLabel(labelBackground, styles.numberLabel);
		a_label_6.setPosition(57, 0);
		a_label_6.label.setText("5");
		a_button_6.addActor(a_label_6);
		
		this.addActor(a_button_6);
	}
}
