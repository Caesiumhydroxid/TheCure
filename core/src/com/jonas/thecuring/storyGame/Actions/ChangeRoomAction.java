package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.sun.xml.internal.ws.api.pipe.NextAction;

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
	public ChangeRoomAction(World world,RoomEnum room,float duration,Action nextAction) {
		this(world,room,duration,0,"",nextAction);
	}
	public ChangeRoomAction(World world,RoomEnum room,float duration,float textDuration,String text) {
		super(world,null);
		this.duration = duration;
		this.room = room;
		this.text = text;
		this.textDuration = textDuration;
	}
	public ChangeRoomAction(World world,RoomEnum room,float duration,float textDuration,String text,Action nextAction) {
		super(world,nextAction);
		this.duration = duration;
		this.room = room;
		this.text = text;
		this.textDuration = textDuration;
	}
	
	@Override
	public void run() {
		if(nextAction == null)
			world.setCurrentRoomTransition(room, text, duration, textDuration);
		else
			world.setCurrentRoomTransition(room, text, duration, textDuration, nextAction);
	}

}
