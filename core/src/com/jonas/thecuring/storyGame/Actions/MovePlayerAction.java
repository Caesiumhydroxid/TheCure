package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.storyGame.World;

public class MovePlayerAction extends Action {
	private Vector2 positionToMove;
	private Action actionWhenFinished;
	private boolean running = false;

	public MovePlayerAction(World world, Vector2 positionToMove, Action actionWhenFinished) {
		super(world);
		this.positionToMove = positionToMove;
		this.actionWhenFinished = actionWhenFinished;
	}

	@Override
	public void update(float delta) {
		if (running) {
			if (Math.abs(positionToMove.x - world.player.getPosition().x) < 1f) {
				this.toDelete = true;
				world.player.processInput = true;
				if(actionWhenFinished!= null)
					world.player.actionsToAdd.add(actionWhenFinished);
			} else if (world.player.getPosition().x < positionToMove.x) {
				world.player.velocity.x = world.player.walkRightVelocity;
			} else if (world.player.getPosition().x > positionToMove.x) {
				world.player.velocity.x = world.player.walkLeftVelocity;
			}
			super.update(delta);
		}
	}

	@Override
	public void run() {
		world.player.processInput = false;
		running = true;
	}

}