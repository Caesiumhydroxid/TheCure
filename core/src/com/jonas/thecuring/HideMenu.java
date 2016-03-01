package com.jonas.thecuring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jonas.thecuring.ui.BackgroundLabel;

public class HideMenu extends Table {
	private TextButton h_button_0;
	private TextButton h_button_1;
	private TextButton h_button_2;
	private TextButton h_button_3;
	private TextButton h_button_4;
	private TextButton h_button_5;
	private BackgroundLabel h_label_1;
	private BackgroundLabel h_label_2;
	private BackgroundLabel h_label_3;
	private BackgroundLabel h_label_4;
	private BackgroundLabel h_label_5;

	
	HideMenu(Styles styles,AssetManager manager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) manager.get("Label_Number.png");
		
		Image connections = new Image((Texture) manager.get("Connections_Defense.png"));
		this.addActor(connections);
		
		h_button_0 = new TextButton("Vertecken",styles.smallButton);
		h_button_0.setPosition(46, 91-14);
		h_button_0.setName("h_defensive");
		h_button_0.addListener(buttonListener);
		this.addActor(h_button_0);
		
		h_button_1 = new TextButton("Anti Tumor\nsuppressor",styles.bigButton);
		h_button_1.setPosition(5, 119-41);
		h_button_1.setName("h_anti_tumor_suppressor");
		h_button_1.addListener(buttonListener);
		
		h_label_1 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_1.setPosition(57, 0);
		h_label_1.label.setText("0");
		h_button_1.addActor(h_label_1);
		this.addActor(h_button_1);
		
		h_button_2 = new TextButton("Schild",styles.bigButton);
		h_button_2.setPosition(82, 119-41);
		h_button_2.setName("h_Shield");
		h_button_2.addListener(buttonListener);
		
		h_label_2 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_2.setPosition(57, 0);
		h_label_2.label.setText("0");
		h_button_2.addActor(h_label_2);
		this.addActor(h_button_2);
		
		h_button_3 = new TextButton("Sicher\nvs. Körper",styles.bigButton);
		h_button_3.setPosition(46, 119-65);
		h_button_3.setName("h_invisible_for_body");
		h_button_3.addListener(buttonListener);
		
		h_label_3 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_3.setPosition(57, 0);
		h_label_3.label.setText("0");
		h_button_3.addActor(h_label_3);
		this.addActor(h_button_3);
		
		h_button_4 = new TextButton("Robustheit\nvs. Arznei",styles.bigButton);
		h_button_4.setPosition(5, 119-95);
		h_button_4.setName("h_robustness_vs_medicine");
		h_button_4.addListener(buttonListener);
		
		h_label_4 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_4.setPosition(57, 0);
		h_label_4.label.setText("0");
		h_button_4.addActor(h_label_4);
		this.addActor(h_button_4);
	}
}
