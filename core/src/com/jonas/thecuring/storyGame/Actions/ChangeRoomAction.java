package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;

public class ChangeRoomAction extends Action{
	
	private RoomEnum room;
	public ChangeRoomAction(World world,RoomEnum room) {
		super(world);
		this.room = room;
	}
	@Override
	public void run() {
		world.setCurrentRoomTransition(room);
	}

}
