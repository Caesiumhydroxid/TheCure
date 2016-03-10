package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class WorkRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_work_working"), world,new Vector2(120,10));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 13, new NextDayAction(world, new ChangeRoomAction(world, RoomEnum.HOME_ROOM,2,5,"Du arbeitest die Ganze Nacht durch um zu verdrängen was du heute erfahren Hast. Wirklich helfen tut das allerdings auch nicht ...",new SetPlayerAction(world, true, true, null)))));
		return room;
	}

}
