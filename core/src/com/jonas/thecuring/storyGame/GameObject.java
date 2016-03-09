package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GameObject extends AbstractGameObject {
	
	private Texture texture;
	public GameObject(Texture texture) {
		super();
		this.texture = texture;
	}
	
	@Override
	public void render(Batch batch) {
		batch.draw(texture,position.x,position.y);
	}
	
}
