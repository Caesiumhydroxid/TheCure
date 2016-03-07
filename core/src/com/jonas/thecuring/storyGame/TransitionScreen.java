package com.jonas.thecuring.storyGame;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;

public class TransitionScreen extends AbstractGameObject{
	
	private ArrayList<TransitionListener> listeners;
	private float transitionTime = 2;
	private float elapsedTime;
	private float percentage;
	private Pixmap background;
	private Texture texture;
	private boolean wasAtHalf;
	private BitmapFont font;
	private float alpha;
	private GlyphLayout layout;
	private float remainAtTextTime=2;
	private float elapsedTextTime;
	private String text;
	private boolean waiting;
	public TransitionScreen()
	{
		this(2f);
	}
	public TransitionScreen(float transitionTime)
	{
		this(transitionTime,0,"");
	}
	public TransitionScreen(float transitionTime,float remainAtTextTime,String text)
	{
		listeners = new ArrayList<TransitionListener>();
		background = new Pixmap(160,90,Format.RGB888);
		background.setColor(Color.BLACK);
		background.fill();
		texture = new Texture(background);
		this.transitionTime = transitionTime;
		font = (BitmapFont) Assets.getInstance().get("font_small");
		layout = new GlyphLayout();
		this.text = text;
		this.remainAtTextTime = remainAtTextTime;
		z = 200;
	}
	public void addListener(TransitionListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeListener(TransitionListener listener)
	{
		listeners.remove(listener);
	}
	
	@Override
	public void update(float delta) {
		if(!waiting)
			elapsedTime += delta;
		else
		{
			elapsedTextTime += delta;
			if(elapsedTextTime>remainAtTextTime)
			{
				waiting= false;
			}
		}
		percentage = elapsedTime / transitionTime;
		if(percentage > 0.5&&wasAtHalf == false)
		{
			wasAtHalf = true;
			for(TransitionListener l:listeners)
			{
				l.transitionIsAtMax();
			}
			waiting = true;
		}
		if(percentage >0.99)
		{
			for(TransitionListener l:listeners)
			{
				l.transitionFinished();
			}
			toDelete = true;
		}
		alpha = (float)Math.sin(Math.PI*percentage);
		super.update(delta);
	}

	@Override
	public void render(Batch batch) {
		batch.setColor(1, 1, 1, alpha);
		batch.draw(texture,0,0);
		batch.setColor(1,1,1, alpha);
		layout.setText(font, text, new Color(1, 1, 1, alpha*alpha),120, Align.center, true);
		font.draw(batch,layout,(160-120)/2f,45+layout.height/2f);
		batch.setColor(Color.WHITE);
		
	}
	
}
