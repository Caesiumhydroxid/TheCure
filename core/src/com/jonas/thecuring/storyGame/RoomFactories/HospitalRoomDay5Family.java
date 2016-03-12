package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;

public class HospitalRoomDay5Family extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital_family"), world,new Vector2(10,10));
		String[] messages = new String[]{
				"Papa! Du bist wieder da!",
				"Wie geht es dir?",
				"... Ich bin so froh das die OP gut verlaufen ist!",
				"Jede Sekunde die du da drinnen warst kamen mir",
				"vor als wären es mehrere Tage.",
				"Die Ärtzte haben gesagt, dass du jetzt Ruhe brauchst.",
				"Kommt Kinder, wir gehen!"
		};
		float[] durations = new float[]{
				4,3,5,5,5,5,5
		};
		float[] positions = new float[]{
				55,4,4,4,4,4,4
		};
		room.addActionRoom(0, 0, -1, -1, new DialogeSceneAction(world, messages, durations, positions,null));
		return room;
	}

}
