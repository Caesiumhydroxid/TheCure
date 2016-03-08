package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;

public class ChangeRoomAction extends Action{
	
	private RoomEnum room;
	private float duration;
	public ChangeRoomAction(World world,RoomEnum room) {
		super(world);
		this.room = room;
		this.duration = 1;
	}
	public ChangeRoomAction(World world,RoomEnum room,float duration) {
		super(world);
		this.duration = duration;
		this.room = room;
	}
	@Override
	public void run() {
		world.setCurrentRoomTransition(room,duration);
	}

}
