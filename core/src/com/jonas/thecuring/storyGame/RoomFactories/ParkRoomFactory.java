package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.SendMessageAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class ParkRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		
		room = new Room((Texture) Assets.getInstance().get("room_park"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, -1, -1, new SendMessageAction(world, "Das Subjekt verbringt seinen letzten Tag vor der OP mit seinen Kindern", null));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 7, new NextDayAction(world, 
				new ChangeRoomAction(world, RoomEnum.HOME_ROOM,2,7,"Den restlichen Tag verbringt ihr mit picknicken, Ball spielen und reden. Die Familie fährt zusammen nach Hause. Du hast die gemeinsame Zeit genossen.",new SetPlayerAction(world, true, true, null)))));
		return room;
	}

}
