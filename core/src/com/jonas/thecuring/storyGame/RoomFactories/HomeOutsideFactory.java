package com.jonas.thecuring.storyGame.RoomFactories;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DayChangerAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.MenuAction;

public class HomeOutsideFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_home_outside"), world,new Vector2(0,10));
		room.colliders.add(new Rectangle(107, 10, 45, 40));
		room.addActionRoom(0, 0, 1, 90, new ChangeRoomAction(world, RoomEnum.HOME_ANTE_ROOM));
		ArrayList<Action> carActions = new ArrayList<Action>();
		carActions.add(new DisplayActionText(world, "Fahre in Arbeit", new ChangeRoomAction(world, RoomEnum.WORK_ROOM)));
		room.addActionRoom(100, 0, 7, 90, new DayChangerAction(world, carActions));
				/*new DisplayActionText(world, "Fahre",
				new MenuAction(world, new String[]{"Lol","Test"}, 
				new Action[]{
						new ChangeRoomAction(world, RoomEnum.HOME_DAUGHTER_ROOM),
						new ChangeRoomAction(world, RoomEnum.HOME_ANTE_ROOM)})
				));*/
		return room;
	}

}
