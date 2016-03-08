package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;

public class ChangeRoomAction extends Action{
	
	private RoomEnum room;
	private float duration;
	private float textDuration;
	private String text;
	public ChangeRoomAction(World world,RoomEnum room) {
		this(world,room,1);
	}
	public ChangeRoomAction(World world,RoomEnum room,float duration) {
		this(world,room,duration,0,"");
	}
	public ChangeRoomAction(World world,RoomEnum room,float duration,float textDuration,String text) {
		super(world,null);
		this.duration = duration;
		this.room = room;
		this.text = text;
		this.textDuration = textDuration;
	}
	
	@Override
	public void run() {
		world.setCurrentRoomTransition(room, text, duration, textDuration);
	}

}
