package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class OPRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital_op"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 10, new NextDayAction(world, new ChangeRoomAction(world, RoomEnum.HOSPITAL_ROOM_FRIENDS,1,4,"Nach der OP besuchen dich deine Freunde"))));
		return room;
	}

}
