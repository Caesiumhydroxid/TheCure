package com.jonas.thecuring.cancerGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.ui.BackgroundLabel;
import com.jonas.thecuring.ui.Styles;

public class AttackMenu extends Table {
	private TextButton a_button_0;
	private TextButton a_button_1;
	private TextButton a_button_2;
	private TextButton a_button_3;
	private TextButton a_button_4;
	private TextButton a_button_5;
	private TextButton a_button_6;
	public BackgroundLabel a_label_1;
	public BackgroundLabel a_label_2;
	public BackgroundLabel a_label_3;
	public BackgroundLabel a_label_4;
	public BackgroundLabel a_label_5;
	public BackgroundLabel a_label_6;
	private TextTooltip a_tooltip_1;
	private TextTooltip a_tooltip_2;
	private TextTooltip a_tooltip_3;
	private TextTooltip a_tooltip_4;
	private TextTooltip a_tooltip_5;
	private TextTooltip a_tooltip_6;
	
	
	public int kaMoreBloodvessels=1;
	public int kaMutations=1;
	public int kaFastCellDivision=2;
	public int kaDangerousMutations=2;
	public int kaMetastases=5;
	public int kaAttacksTissue=2;
	
	public AttackMenu(TooltipManager tooltipManager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) Assets.getInstance().get("label_number");
		
		Image a_connections = new Image((Texture) Assets.getInstance().get("connections_attack"));
		this.addActor(a_connections);
		
		a_button_0 = new TextButton("Aggressiv",Styles.getInstance().smallButton);
		a_button_0.setPosition(46, 105);
		a_button_0.setName("a_aggressive");
		a_button_0.addListener(buttonListener);
		this.addActor(a_button_0);
		
		a_button_1 = new TextButton("Mehr \nBlutgefäße",Styles.getInstance().bigButton);
		a_button_1.setPosition(5, 76);
		a_button_1.setName("a_more_bloodvessels");
		a_button_1.addListener(buttonListener);
		
		a_tooltip_1 = new TextTooltip("Kosten: "+kaMoreBloodvessels,tooltipManager,Styles.getInstance().textTooltipStyle);
		a_button_1.addListener(a_tooltip_1);
		
		a_label_1 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_1.setPosition(57, 0);
		a_label_1.label.setText("5");
		a_button_1.addActor(a_label_1);
		this.addActor(a_button_1);
		
		a_button_2 = new TextButton("Mutationen",Styles.getInstance().bigButton);
		a_button_2.setPosition(82, 76);
		a_button_2.setName("a_mutations");
		a_button_2.addListener(buttonListener);
		
		a_tooltip_2 = new TextTooltip("Kosten: "+kaMutations,tooltipManager,Styles.getInstance().textTooltipStyle);
		a_button_2.addListener(a_tooltip_2);
		
		a_label_2 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_2.setPosition(57, 0);
		a_label_2.label.setText("5");
		a_button_2.addActor(a_label_2);
		
		this.addActor(a_button_2);
		
		a_button_3 = new TextButton("schnelle \nZellteilung",Styles.getInstance().bigButton);
		a_button_3.setPosition(5, 50);
		a_button_3.setName("a_fast_cell_division");
		a_button_3.addListener(buttonListener);
		
		a_tooltip_3 = new TextTooltip("Kosten: "+kaFastCellDivision+"\nbenötigt:\nMehr Blutgefäße 5",tooltipManager,Styles.getInstance().textTooltipStyle);
		a_tooltip_3.getActor().setAlignment(Align.center);
		a_tooltip_3.getContainer().pad(4);
		a_button_3.addListener(a_tooltip_3);
		
		a_label_3 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_3.setPosition(57, 0);
		a_label_3.label.setText("5");
		a_button_3.addActor(a_label_3);
		
		this.addActor(a_button_3);
		
		a_button_4 = new TextButton("gefährliche\nMutationen",Styles.getInstance().bigButton);
		a_button_4.setPosition(82, 50);
		a_button_4.setName("a_dangerous_mutations");
		a_button_4.addListener(buttonListener);
		
		a_tooltip_4 = new TextTooltip("Kosten: "+kaDangerousMutations+"\nbenötigt:\nMutationen 5",tooltipManager,Styles.getInstance().textTooltipStyle);
		a_tooltip_4.getActor().setAlignment(Align.center);
		a_tooltip_4.getContainer().pad(4);
		a_button_4.addListener(a_tooltip_4);
		
		a_label_4 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_4.setPosition(57, 0);
		a_label_4.label.setText("5");
		a_button_4.addActor(a_label_4);
		
		this.addActor(a_button_4);
		
		a_button_5 = new TextButton("Metastasen",Styles.getInstance().bigButton);
		a_button_5.setPosition(46, 25);
		a_button_5.setName("a_metastases");
		a_button_5.addListener(buttonListener);
		
		a_tooltip_5 = new TextTooltip("Kosten: "+kaMetastases+"\nbenötigt:\nschelle Zellteilung 5\ngefähliche Mutationen 5",tooltipManager,Styles.getInstance().textTooltipStyle);
		a_tooltip_5.getActor().setAlignment(Align.center);
		a_tooltip_5.getContainer().pad(4);
		a_button_5.addListener(a_tooltip_5);
		
		a_label_5 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_5.setPosition(57, 0);
		a_label_5.label.setText("5");
		a_button_5.addActor(a_label_5);
		
		this.addActor(a_button_5);
		
		a_button_6 = new TextButton("greift\nGewebe an",Styles.getInstance().bigButton);
		a_button_6.setPosition(46, 0);
		a_button_6.setName("a_attacks_tissue");
		a_button_6.addListener(buttonListener);
		
		a_tooltip_6 = new TextTooltip("Kosten: "+kaAttacksTissue,tooltipManager,Styles.getInstance().textTooltipStyle);
		a_button_6.addListener(a_tooltip_6);
		
		a_label_6 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		a_label_6.setPosition(57, 0);
		a_label_6.label.setText("5");
		a_button_6.addActor(a_label_6);
		
		this.addActor(a_button_6);
	}
}
