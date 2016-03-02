package com.jonas.thecuring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jonas.thecuring.ui.BackgroundLabel;
import com.jonas.thecuring.ui.Styles;

public class HideMenu extends Table {
	private TextButton h_button_0;
	private TextButton h_button_1;
	private TextButton h_button_2;
	public BackgroundLabel h_label_1;
	public BackgroundLabel h_label_2;
	private float scale = 4.f;

	
	HideMenu(Styles styles,AssetManager manager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) manager.get("Label_Number.png");
		
		Image connections = new Image((Texture) manager.get("Connections_Hide.png"));
		this.addActor(connections);
		
		h_button_0 = new TextButton("Verstecken",styles.smallButton);
		h_button_0.setPosition(46*scale, 72*scale);
		h_button_0.setName("h_hide");
		h_button_0.addListener(buttonListener);
		this.addActor(h_button_0);
		
		h_button_1 = new TextButton("Ähnlichkeit\nzu Körperzellen",styles.hugeButton);
		h_button_1.setPosition(38*scale, 37*scale);
		h_button_1.setName("h_similarity_to_bodycells");
		h_button_1.addListener(buttonListener);
		
		h_label_1 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_1.setPosition(72*scale, 3*scale);
		h_label_1.label.setText("0");
		h_button_1.addActor(h_label_1);
		this.addActor(h_button_1);
		
		h_button_2 = new TextButton("Einkommende\nImunzellen\nzerstören",styles.hugeButton);
		h_button_2.setPosition(38*scale, 0*scale);
		h_button_2.setName("h_destroy_incoming_imunecells");
		h_button_2.addListener(buttonListener);
		
		h_label_2 = new BackgroundLabel(labelBackground, styles.numberLabel);
		h_label_2.setPosition(72*scale, 3*scale);
		h_label_2.label.setText("0");
		h_button_2.addActor(h_label_2);
		this.addActor(h_button_2);
		
	}
}
