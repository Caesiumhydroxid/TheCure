package com.jonas.thecuring.storyGame.RoomFactories;

import java.util.ArrayList;

import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;

public abstract class RoomFactory {
	private ArrayList<Action> dayActions;
	public RoomFactory()
	{
		dayActions = new ArrayList<Action>();
	}
	public abstract Room getRoom(World world);
}
