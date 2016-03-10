package com.jonas.thecuring.storyGame;

import java.util.ArrayList;
import java.util.Arrays;

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
	private ArrayList<String> options;
	private Action[] actions;
	private World world;
	private String question;
	private int selectedItem;
	private float width;
	private float height;
	private boolean playerProcessInput;
	ChoiceMenu(String[] options,Action[] actions,World world,boolean bigItem,String question)
	{
		this.question = question;
		this.options = new ArrayList<String>();
		this.options.addAll(Arrays.asList(options));
		this.actions = actions;
		font = (BitmapFont) Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		if(!bigItem)
		{
			middle = (Texture) Assets.getInstance().get("choice_middle");
			top = (Texture) Assets.getInstance().get("choice_top");
			bottom = (Texture) Assets.getInstance().get("choice_bottom");
			item = (Texture) Assets.getInstance().get("choice_item");
			width = 43;
			height = 11;
		}
		else
		{
			middle = (Texture) Assets.getInstance().get("choice_middle_big");
			top = (Texture) Assets.getInstance().get("choice_top_big");
			bottom = (Texture) Assets.getInstance().get("choice_bottom_big");
			item = (Texture) Assets.getInstance().get("choice_item_big");
			width = 120;
			height = 11;
		}
		this.world = world;
		playerProcessInput = world.player.processInput;
		world.player.processInput = false;
		world.getCurrentRoom().fireEvents = false;
	}
	
	@Override
	protected void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.UP))
		{
			if(selectedItem==options.size()-1)
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
				selectedItem = options.size() -1;
			}
			else
			{
				selectedItem--;
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			toDelete = true;
			if(actions[selectedItem]!=null)
				actions[selectedItem].run();
			world.player.processInput = playerProcessInput;
			world.getCurrentRoom().fireEvents = true;
		}
	};
	@Override
	public void update(float delta) 
	{
		super.update(delta);
		
	};
	
	@Override
	public void render(Batch batch) {
		
		batch.draw(bottom, position.x, position.y);
		for(int i=0;i<options.size()*(height+2)+8;i++)
		{
			batch.draw(middle, position.x, position.y+i+2);
		}
		batch.draw(top, position.x, position.y+options.size()*(height+2)+10);
		Color c = Color.BLACK;
		GlyphLayout layout = new GlyphLayout(font, question,c,width-2,Align.center,false);
		font.draw(batch,layout,position.x+3,position.y +1 +options.size()*(height+2)+ layout.height/2f+ item.getHeight()/2f);
		
		for(int i = 0;i<options.size();i++)
		{
			batch.draw(item,position.x+2,position.y+2+i*(height+2));
			c = Color.GRAY;
			if(i==selectedItem)
				c = Color.BLACK;
			layout = new GlyphLayout(font, options.get(i),c,width-2,Align.center,false);
			font.draw(batch, layout, position.x+3, position.y +2 +i*(height+2)+ layout.height/2f+ item.getHeight()/2f);
		}
	}

}
