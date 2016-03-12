package com.jonas.thecuring.storyGame.RoomFactories;

import org.luaj.vm2.ast.Str;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;

public class HospitalRoomDay5Friends extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital_friends"), world,new Vector2(10,10));
		String[] messages = new String[]{
				"Hey.. Die Ärtzte haben gesagt,dass du die Operation gut überstanden hast",
				"Du packst das sicher. Jetzt noch den Rest mit der Chemo-Therapie",
				"und schon ist es vorbei.",
				"In ein paar Monaten lachen wir schon wieder darüber",
				"Ach Mann.. Immer trifft es die Guten.",
				"Deine Eltern warten schon vor der Tür."
		};
		float[] durations = new float[]{
				5,5,5,5,5,5
		};
		room.addActionRoom(0, 0, -1, -1, new DialogeSceneAction(world, messages, durations, new ChangeRoomAction(world,RoomEnum.HOSPITAL_ROOM_PARENTS)));
		return room;
	}

}
