package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;

public class DialogueMessage extends AbstractGameObject {
	private Texture background;
	private BitmapFont font;
	private String text;
	private float elapsedTime;
	DialogueMessage()
	{	
		this.background = (Texture)Assets.getInstance().get("dialogue");
		this.font = (BitmapFont)Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		this.boundingRectangle = new Rectangle(0,0,background.getWidth(),background.getHeight());
	}
	DialogueMessage(String text)
	{	
		this.background = (Texture)Assets.getInstance().get("dialogue");
		this.font = (BitmapFont)Assets.getInstance().get("font");
		this.boundingRectangle = new Rectangle(0,0,background.getWidth(),background.getHeight());
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public void update(float delta) {
		elapsedTime+= delta;
		if(elapsedTime>5)
			this.toDelete = true;
		super.update(delta);
	}
	public void reset()
	{
		elapsedTime = 0;
		toDelete = false;
	}
	@Override
	public void render(Batch batch) {
		batch.draw(background, position.x, position.y);
		GlyphLayout glyphLayout = new GlyphLayout(font, text);
		glyphLayout.setText(font, text, 0, text.length(), Color.BLACK, (boundingRectangle.width-4), Align.center, true, null);
		float y = position.y + boundingRectangle.height/2f + glyphLayout.height/2f;
		font.draw(batch, glyphLayout, position.x, y );

		
	}
	
}
