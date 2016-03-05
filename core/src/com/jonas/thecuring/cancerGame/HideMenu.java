package com.jonas.thecuring.cancerGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.ui.BackgroundLabel;
import com.jonas.thecuring.ui.Styles;

public class HideMenu extends Table {
	private TextButton h_button_0;
	private TextButton h_button_1;
	private TextButton h_button_2;
	public BackgroundLabel h_label_1;
	public BackgroundLabel h_label_2;
	private TextTooltip h_tooltip_1;
	private TextTooltip h_tooltip_2;
	
	public int khSimilarityToBodycells=2;
	public int khDestroyIncomingImunecells=3;

	
	HideMenu(TooltipManager tooltipManager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) Assets.getInstance().get("label_number");
		
		Image connections = new Image((Texture) Assets.getInstance().get("connections_hide"));
		this.addActor(connections);
		
		h_button_0 = new TextButton("Verstecken",Styles.getInstance().smallButton);
		h_button_0.setPosition(46, 72);
		h_button_0.setName("h_hide");
		h_button_0.addListener(buttonListener);
		this.addActor(h_button_0);
		
		h_button_1 = new TextButton("�hnlichkeit\nzu K�rperzellen",Styles.getInstance().hugeButton);
		h_button_1.setPosition(38, 37);
		h_button_1.setName("h_similarity_to_bodycells");
		h_button_1.addListener(buttonListener);
		
		h_tooltip_1 = new TextTooltip("Kosten: "+khSimilarityToBodycells,tooltipManager,Styles.getInstance().textTooltipStyle);
		h_button_1.addListener(h_tooltip_1);
		
		h_label_1 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		h_label_1.setPosition(72, 3);
		h_label_1.label.setText("0");
		h_button_1.addActor(h_label_1);
		this.addActor(h_button_1);
		
		h_button_2 = new TextButton("Einkommende\nImunzellen\nzerst�ren",Styles.getInstance().hugeButton);
		h_button_2.setPosition(38, 0);
		h_button_2.setName("h_destroy_incoming_imunecells");
		h_button_2.addListener(buttonListener);
		
		h_tooltip_2 = new TextTooltip("Kosten: "+khDestroyIncomingImunecells,tooltipManager,Styles.getInstance().textTooltipStyle);
		h_button_2.addListener(h_tooltip_2);
		
		h_label_2 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		h_label_2.setPosition(72, 3);
		h_label_2.label.setText("0");
		h_button_2.addActor(h_label_2);
		this.addActor(h_button_2);
		
	}
}
