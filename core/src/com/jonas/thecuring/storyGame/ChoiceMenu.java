package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.Action;

public class ChoiceMenu extends AbstractGameObject {
	
	private Texture middle;
	private Texture top;
	private Texture bottom;
	private Texture item;
	private BitmapFont font;
	private String[] options;
	private Action[] actions;
	
	private int selectedItem;
	ChoiceMenu(String[] options,Action[] actions)
	{
		this.options = options;
		this.actions = actions;
		font = (BitmapFont) Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		middle = (Texture) Assets.getInstance().get("choice_middle");
		top = (Texture) Assets.getInstance().get("choice_top");
		bottom = (Texture) Assets.getInstance().get("choice_bottom");
		item = (Texture) Assets.getInstance().get("choice_item");
	}
	
	@Override
	public void update(float delta) 
	{
		if(Gdx.input.isKeyJustPressed(Keys.UP))
		{
			if(selectedItem==options.length-1)
			{
				selectedItem = 0;
			}
			else
			{
				selectedItem++;
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
		{
			if(selectedItem==0)
			{
				selectedItem = options.length -1;
			}
			else
			{
				selectedItem--;
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			actions[selectedItem].run();
		}
	};
	
	@Override
	public void render(Batch batch) {
		
		batch.draw(bottom, position.x, position.y);
		for(int i=0;i<options.length*(11+2)+8;i++)
		{
			batch.draw(middle, position.x, position.y+i+2);
		}
		batch.draw(top, position.x, position.y+options.length*(11+2)+10);
		Color c = Color.BLACK;
		GlyphLayout layout = new GlyphLayout(font, "Wohin?",c,41,Align.center,false);
		font.draw(batch,layout,position.x+3,position.y+ layout.height/2f + item.getHeight()*(options.length+1)-2);
		
		for(int i = 0;i<options.length;i++)
		{
			batch.draw(item,position.x+2,position.y+2+i*13);
			c = Color.BLACK;
			if(i==selectedItem)
				c = Color.GRAY;
			layout = new GlyphLayout(font, options[i],c,41,Align.center,false);
			font.draw(batch, layout, position.x+3, position.y +2 +i*13+ layout.height/2f+ item.getHeight()/2f);
		}
	}

}
