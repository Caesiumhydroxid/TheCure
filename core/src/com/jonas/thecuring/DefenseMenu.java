package com.jonas.thecuring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jonas.thecuring.ui.BackgroundLabel;

public class DefenseMenu extends Table {
	private TextButton d_button_0;
	private TextButton d_button_1;
	private TextButton d_button_2;
	private TextButton d_button_3;
	private TextButton d_button_4;
	private TextButton d_button_5;
	private TextButton d_button_6;
	private BackgroundLabel d_label_1;
	private BackgroundLabel d_label_2;
	private BackgroundLabel d_label_3;
	private BackgroundLabel d_label_4;
	private BackgroundLabel d_label_5;
	private BackgroundLabel d_label_6;
	
	DefenseMenu(Styles styles,AssetManager manager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) manager.get("Label_Number.png");
		
		TooltipManager tMana = new TooltipManager();
		tMana.offsetX = 2;
		tMana.offsetY =2;
		tMana.animations = true;
		tMana.initialTime = 0.2f;
		tMana.hideAll();
		tMana.resetTime =0.5f;
		tMana.subsequentTime = 1.0f;
		
		TextTooltip t = new TextTooltip("Kosten: 2",tMana,styles.textTooltipStyle);
		Image connections = new Image((Texture) manager.get("Connections_Defense.png"));
		this.addActor(connections);
		
		d_button_0 = new TextButton("Defensiv",styles.smallButton);
		d_button_0.setPosition(46, 119-14);
		d_button_0.setName("d_defensive");
		d_button_0.addListener(buttonListener);
		d_button_0.addListener(t);
		this.addActor(d_button_0);
		
		d_button_1 = new TextButton("Anti Tumor\nsuppressor",styles.bigButton);
		d_button_1.setPosition(5, 119-41);
		d_button_1.setName("d_anti_tumor_suppressor");
		d_button_1.addListener(buttonListener);
		
		d_label_1 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_1.setPosition(57, 0);
		d_label_1.label.setText("0");
		d_button_1.addActor(d_label_1);
		this.addActor(d_button_1);
		
		d_button_2 = new TextButton("Schild",styles.bigButton);
		d_button_2.setPosition(82, 119-41);
		d_button_2.setName("d_Shield");
		d_button_2.addListener(buttonListener);
		
		d_label_2 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_2.setPosition(57, 0);
		d_label_2.label.setText("0");
		d_button_2.addActor(d_label_2);
		this.addActor(d_button_2);
		
		d_button_3 = new TextButton("Sicher\nvs. Körper",styles.bigButton);
		d_button_3.setPosition(46, 119-65);
		d_button_3.setName("d_invisible_for_body");
		d_button_3.addListener(buttonListener);
		
		d_label_3 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_3.setPosition(57, 0);
		d_label_3.label.setText("0");
		d_button_3.addActor(d_label_3);
		this.addActor(d_button_3);
		
		d_button_4 = new TextButton("Robustheit\nvs. Arznei",styles.bigButton);
		d_button_4.setPosition(5, 119-95);
		d_button_4.setName("d_robustness_vs_medicine");
		d_button_4.addListener(buttonListener);
		
		d_label_4 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_4.setPosition(57, 0);
		d_label_4.label.setText("0");
		d_button_4.addActor(d_label_4);
		this.addActor(d_button_4);
		
		d_button_5 = new TextButton("Stärke\nvs. Arznei",styles.bigButton);
		d_button_5.setPosition(82, 119-95);
		d_button_5.setName("d_strength_vs_medicine");
		d_button_5.addListener(buttonListener);
		
		d_label_5 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_5.setPosition(57, 0);
		d_label_5.label.setText("0");
		d_button_5.addActor(d_label_5);
		this.addActor(d_button_5);
		
		d_button_6 = new TextButton("Hypoxie\nin Umgebung",styles.bigButton);
		d_button_6.setPosition(46, 119-119);
		d_button_6.setName("d_hypoxia");
		d_button_6.addListener(buttonListener);
		
		d_label_6 = new BackgroundLabel(labelBackground, styles.numberLabel);
		d_label_6.setPosition(57, 0);
		d_label_6.label.setText("0");
		d_button_6.addActor(d_label_6);
		this.addActor(d_button_6);
	}

}
