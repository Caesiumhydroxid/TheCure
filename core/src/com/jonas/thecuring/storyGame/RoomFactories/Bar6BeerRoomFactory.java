package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.SetWorldVariableAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;
import com.jonas.thecuring.storyGame.Actions.VariableSetter;

public class Bar6BeerRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_bar_6beer"), world,new Vector2(120,10));
		Action setTimeWithFamily = new SetWorldVariableAction(world, new VariableSetter(world) {
			@Override
			public void setVariable() {
				world.timeWithFamily = false;
			}
		}, null);
		room.addActionRoom(0, 0, -1, -1, setTimeWithFamily);
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 13, new NextDayAction(world, new ChangeRoomAction(world, RoomEnum.HOME_ROOM,2,5,"Spät in der Nacht kommst du wieder nach Hause und legst dich ins Bett",
				new SetPlayerAction(world, true, true, null)))));
		return room;
	}

}
