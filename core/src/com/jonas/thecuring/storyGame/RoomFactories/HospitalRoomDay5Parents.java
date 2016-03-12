package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;

public class HospitalRoomDay5Parents extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital_parents"), world,new Vector2(10,10));
		String[] messages = new String[]{
				"Hallo...",
				"Wir... wir wollten dir nur sagen, dass wir",
				"dich lieben und wir fest daran glauben, dass",
				"du wieder gesund wirst. Deine Schwester konnte",
				"heute leider nicht kommen. Sie wird dich aber so",
				"bald es geht besuchen."
		};
		float[] durations = new float[]{
				5,5,5,5,5,5
		};
		room.addActionRoom(0, 0, -1, -1, new DialogeSceneAction(world, messages, durations, new ChangeRoomAction(world,RoomEnum.HOSPITAL_ROOM_FAMILY)));
		return room;
	}

}
