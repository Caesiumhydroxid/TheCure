package com.jonas.thecuring.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class AnimatedImage extends Image
{
    protected Animation animation = null;
    private float stateTime = 0;

    public AnimatedImage(Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }
    
    public AnimatedImage(float frameDuration,Texture texture,int columns,int rows)
    {
    	super();
    	int frame_rows = rows;
		int frame_cols = columns;
		TextureRegion [][] tmp = TextureRegion.split(texture,texture.getWidth()/columns, texture.getHeight()/rows);
		TextureRegion[] frames = new TextureRegion[frame_rows*frame_cols];
		int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        this.animation = new Animation(frameDuration,frames);
        super.setDrawable(new TextureRegionDrawable(animation.getKeyFrame(0)));
        super.setScaling(Scaling.stretch);
        super.setAlign(Align.center);
        super.setSize(getPrefWidth(), getPrefHeight());
    }

    @Override
    public void act(float delta)
    {
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }
}