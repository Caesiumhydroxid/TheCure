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

public class DefenseMenu extends Table {
	private TextButton d_button_0;
	private TextButton d_button_1;
	private TextButton d_button_2;
	private TextButton d_button_3;
	private TextButton d_button_4;
	private TextButton d_button_5;
	private TextButton d_button_6;
	public BackgroundLabel d_label_1;
	public BackgroundLabel d_label_2;
	public BackgroundLabel d_label_3;
	public BackgroundLabel d_label_4;
	public BackgroundLabel d_label_5;
	public BackgroundLabel d_label_6;
	private TextTooltip d_tooltip_1;
	private TextTooltip d_tooltip_2;
	private TextTooltip d_tooltip_3;
	private TextTooltip d_tooltip_4;
	private TextTooltip d_tooltip_5;
	private TextTooltip d_tooltip_6;
	public int kdAntiTumorSuppressor=1;
	public int kdShield=1;
	public int kdInvincibleForBody=5;
	public int kdRoubustnessVsMedicine=2;
	public int kdStrengthVsMedicine=4;
	public int kdHypoxia=2;
	
	
	DefenseMenu(TooltipManager tooltipManager,ChangeListener buttonListener)
	{
		Texture labelBackground = (Texture) Assets.getInstance().get("label_number");	
		
		Image connections = new Image((Texture) Assets.getInstance().get("connections_defense"));
		this.addActor(connections);
		
		
		d_button_0 = new TextButton("Defensiv",Styles.getInstance().smallButton);
		d_button_0.setPosition(46, 105);
		d_button_0.setName("d_defensive");
		d_button_0.addListener(buttonListener);
		this.addActor(d_button_0);
		
		d_button_1 = new TextButton("Anti Tumor\nsuppressor",Styles.getInstance().bigButton);
		d_button_1.setPosition(5, 78);
		d_button_1.setName("d_anti_tumor_suppressor");
		d_button_1.addListener(buttonListener);
		
		d_tooltip_1 = new TextTooltip("Kosten: "+kdAntiTumorSuppressor,tooltipManager,Styles.getInstance().textTooltipStyle);
		d_button_1.addListener(d_tooltip_1);
		
		d_label_1 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_1.setPosition(57, 0);
		d_label_1.label.setText("0");
		d_button_1.addActor(d_label_1);
		this.addActor(d_button_1);
		
		d_button_2 = new TextButton("Schild",Styles.getInstance().bigButton);
		d_button_2.setPosition(82, 78);
		d_button_2.setName("d_Shield");
		d_button_2.addListener(buttonListener);
		
		d_tooltip_2 = new TextTooltip("Kosten: "+kdShield,tooltipManager,Styles.getInstance().textTooltipStyle);
		d_button_2.addListener(d_tooltip_2);
		
		d_label_2 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_2.setPosition(57, 0);
		d_label_2.label.setText("0");
		d_button_2.addActor(d_label_2);
		this.addActor(d_button_2);
		
		d_button_3 = new TextButton("Sicher\nvs. K�rper",Styles.getInstance().bigButton);
		d_button_3.setPosition(46, 54);
		d_button_3.setName("d_invincible_for_body");
		d_button_3.addListener(buttonListener);
		
		d_tooltip_3 = new TextTooltip("Kosten: "+kdInvincibleForBody+"\nben�tigt:\nSchild 5\nAnti Tumorsupressor 5",tooltipManager,Styles.getInstance().textTooltipStyle);
		d_tooltip_3.getActor().setAlignment(Align.center);
		d_tooltip_3.getContainer().pad(4);
		d_button_3.addListener(d_tooltip_3);
		
		d_label_3 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_3.setPosition(57, 0);
		d_label_3.label.setText("0");
		d_button_3.addActor(d_label_3);
		this.addActor(d_button_3);
		
		d_button_4 = new TextButton("Robustheit\nvs. Arznei",Styles.getInstance().bigButton);
		d_button_4.setPosition(5, 24);
		d_button_4.setName("d_robustness_vs_medicine");
		d_button_4.addListener(buttonListener);
		
		d_tooltip_4 = new TextTooltip("Kosten: "+kdRoubustnessVsMedicine,tooltipManager,Styles.getInstance().textTooltipStyle);
		d_button_4.addListener(d_tooltip_4);
		
		d_label_4 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_4.setPosition(57, 0);
		d_label_4.label.setText("0");
		d_button_4.addActor(d_label_4);
		this.addActor(d_button_4);
		
		d_button_5 = new TextButton("St�rke\nvs. Arznei",Styles.getInstance().bigButton);
		d_button_5.setPosition(82, 24);
		d_button_5.setName("d_strength_vs_medicine");
		d_button_5.addListener(buttonListener);
		
		d_tooltip_5 = new TextTooltip("Kosten: "+kdStrengthVsMedicine+"\nben�tigt:\nRobustheit vs. Arznei 5",tooltipManager,Styles.getInstance().textTooltipStyle);
		d_tooltip_5.getContainer().pad(4);
		d_tooltip_5.getActor().setAlignment(Align.center);
		d_button_5.addListener(d_tooltip_5);
		
		d_label_5 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_5.setPosition(57, 0);
		d_label_5.label.setText("0");
		d_button_5.addActor(d_label_5);
		this.addActor(d_button_5);
		
		d_button_6 = new TextButton("Hypoxie\nin Umgebung",Styles.getInstance().bigButton);
		d_button_6.setPosition(46, 0);
		d_button_6.setName("d_hypoxia");
		d_button_6.addListener(buttonListener);
		
		d_tooltip_6 = new TextTooltip("Kosten: "+kdHypoxia,tooltipManager,Styles.getInstance().textTooltipStyle);
		d_button_6.addListener(d_tooltip_6);
		
		d_label_6 = new BackgroundLabel(labelBackground, Styles.getInstance().numberLabel);
		d_label_6.setPosition(57, 0);
		d_label_6.label.setText("0");
		d_button_6.addActor(d_label_6);
		this.addActor(d_button_6);
	}

}
