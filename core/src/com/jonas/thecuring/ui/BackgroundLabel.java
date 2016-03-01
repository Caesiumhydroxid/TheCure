package com.jonas.thecuring.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class BackgroundLabel extends Table {
	public Label label;
	public Image image;
	
	public BackgroundLabel(Texture background,LabelStyle style)
	{
		image = new Image(background);
		label = new Label("",style);
		label.setAlignment(Align.center);
		this.setTouchable(Touchable.disabled);
		this.addActor(image);
		this.addActor(label);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		float height = image.getImageHeight();
		float width = image.getImageWidth();
		label.setPosition( width/2.f , height/2.f  );
		super.draw(batch, parentAlpha);
	}
	
}
