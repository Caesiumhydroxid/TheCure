package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.storyGame.World;

public class MovePlayerAction extends Action {
	private Vector2 positionToMove;
	private boolean startedEvent=false;
	public MovePlayerAction(World world, Vector2 positionToMove, Action actionWhenFinished) {
		super(world,actionWhenFinished);
		this.positionToMove = positionToMove;
	}

	@Override
	public void update(float delta) {
		if (running) {
			if (Math.abs(positionToMove.x - world.player.getPosition().x) < 1f) {
				
				world.player.processInput = true;
				if(nextAction!= null&&!startedEvent)
				{
					startedEvent = true;
					nextAction.run();
				}
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