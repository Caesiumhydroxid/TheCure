package com.jonas.thecuring.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.TextTooltipStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jonas.thecuring.Assets;

public class Styles {
	
	private static Styles instance;
	public TextButtonStyle hugeButton;
	public TextButtonStyle bigButton;
	public TextButtonStyle smallButton;
	public LabelStyle numberLabel;
	public LabelStyle smallLabel;
	public TextTooltipStyle textTooltipStyle;
	public ProgressBarStyle progressBarStyle;
	public WindowStyle windowStyle;

	
	private Styles() 
	{
	}
	public void init()
	{
		bigButton = new TextButtonStyle();
		smallButton = new TextButtonStyle();
		hugeButton = new TextButtonStyle();
		windowStyle = new WindowStyle();
		
		BitmapFont fontNormal = (BitmapFont)Assets.getInstance().get("font");
		fontNormal.getData().setScale(3);
		BitmapFont fontSmall = (BitmapFont)Assets.getInstance().get("font_medium");
		fontSmall.getData().setScale(2);
		
		hugeButton.up = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_huge_up")));
		hugeButton.down = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_huge_down")));
		hugeButton.font = fontNormal;
		
		hugeButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		hugeButton.fontColor = Color.BLACK;
		
		bigButton.up = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_up")));
		bigButton.down = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_down")));
		bigButton.font = fontNormal;
		bigButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		bigButton.disabledFontColor = new Color(0.5f,0.5f,0.5f,1);
		bigButton.disabled = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_down")));
		bigButton.fontColor = Color.BLACK;
		
		
		smallButton.up = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_small_up")));
		smallButton.down = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("button_small_down")));
		smallButton.font = fontNormal;
		smallButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		smallButton.fontColor = Color.BLACK;
		
		numberLabel = new LabelStyle();
		numberLabel.font = fontNormal;
		numberLabel.fontColor = Color.BLACK;
		
		smallLabel = new LabelStyle();
		smallLabel.font = fontSmall;
		smallLabel.fontColor = Color.BLACK;
		
		textTooltipStyle = new TextTooltipStyle();
		textTooltipStyle.label = smallLabel;
		textTooltipStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("tooltip")));
		
		progressBarStyle = new ProgressBarStyle();
		progressBarStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("progressbar")));
		progressBarStyle.knobBefore = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("progressbar_knob")));
		
		windowStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) Assets.getInstance().get("popup")));
		windowStyle.titleFont = fontNormal;
	}
	
	public static Styles getInstance () {
	    if (Styles.instance == null) {
	      Styles.instance = new Styles();
	    }
	    return Styles.instance;
	  }

}
