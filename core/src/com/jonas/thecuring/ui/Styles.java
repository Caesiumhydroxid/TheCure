package com.jonas.thecuring.ui;

import com.badlogic.gdx.assets.AssetManager;
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

public class Styles {
	
	public TextButtonStyle hugeButton;
	public TextButtonStyle bigButton;
	public TextButtonStyle smallButton;
	public LabelStyle numberLabel;
	public LabelStyle smallLabel;
	public TextTooltipStyle textTooltipStyle;
	public ProgressBarStyle progressBarStyle;
	public WindowStyle windowStyle;

	
	public Styles(AssetManager manager) 
	{
		bigButton = new TextButtonStyle();
		smallButton = new TextButtonStyle();
		hugeButton = new TextButtonStyle();
		windowStyle = new WindowStyle();
		
		BitmapFont fontNormal = (BitmapFont)manager.get("Font.fnt");
		fontNormal.getData().setScale(3);
		BitmapFont fontSmall = (BitmapFont)manager.get("Fontsmall.fnt");
		fontSmall.getData().setScale(2f);
		
		hugeButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("HugeButton_up.png")));
		hugeButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("HugeButton_down.png")));
		hugeButton.font = fontNormal;
		
		hugeButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		hugeButton.fontColor = Color.BLACK;
		
		bigButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_up.png")));
		bigButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_down.png")));
		bigButton.font = fontNormal;
		bigButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		bigButton.fontColor = Color.BLACK;
		
		
		smallButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_small_up.png")));
		smallButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_small_down.png")));
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
		textTooltipStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Tooltip.png")));
		
		progressBarStyle = new ProgressBarStyle();
		progressBarStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("ProgressBar.png")));
		progressBarStyle.knobBefore = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("ProgressBarKnob.png")));
		
		windowStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("PopupMessage.png")));
		windowStyle.titleFont = fontNormal;
	}


}
