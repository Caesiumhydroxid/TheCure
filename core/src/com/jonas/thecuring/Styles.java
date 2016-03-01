package com.jonas.thecuring;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.TextTooltipStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Styles {
	
	public TextButtonStyle bigButton;
	public TextButtonStyle smallButton;
	public LabelStyle numberLabel;
	public TextTooltipStyle textTooltipStyle;
	
	public Styles(AssetManager manager) 
	{
		bigButton = new TextButtonStyle();
		smallButton = new TextButtonStyle();
		BitmapFont font = manager.get("Font.fnt");
		
		bigButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button.png")));
		bigButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_Pressed.png")));
		bigButton.font = font;
		bigButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		bigButton.fontColor = Color.BLACK;
		
		smallButton.up = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_small_up.png")));
		smallButton.down = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Button_small_down.png")));
		smallButton.font = font;
		smallButton.downFontColor = new Color(0.5f,0.5f,0.5f,1);
		smallButton.fontColor = Color.BLACK;
		
		numberLabel = new LabelStyle();
		numberLabel.font = font;
		numberLabel.fontColor = Color.BLACK;
		
		textTooltipStyle = new TextTooltipStyle();
		textTooltipStyle.label = numberLabel;
		textTooltipStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) manager.get("Tooltip.png")));
	}

}
