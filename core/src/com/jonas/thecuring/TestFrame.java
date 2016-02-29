package com.jonas.thecuring;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TestFrame extends Actor{
	
	Texture img;
	public TestFrame()
	{
		img = new Texture("TestGame.png");
		setBounds(0, 0, img.getWidth(), img.getHeight());

		addListener(new InputListener() {
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		        System.out.println("down"+x+" "+y);
		        return true;
		    }

		    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		        System.out.println("up");
		    }
		});
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(img,0.f,0.f);
		super.draw(batch, parentAlpha);
	}
	

}
