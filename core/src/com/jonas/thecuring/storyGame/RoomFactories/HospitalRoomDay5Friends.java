package com.jonas.thecuring.storyGame.RoomFactories;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;
import com.jonas.thecuring.storyGame.Actions.SendMessageAction;

public class HospitalRoomDay5Friends extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital_friends"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, -1, -1, new SendMessageAction(world, "�rzte haben gesagt, dass die Chancen f�r Heilung gut stehen, wenn der Tumor sich nicht mehr weiterentwickelt. Ansonsten sieht es nicht gut aus.", null));
		String[] messages = new String[]{
				"Hey.. Die �rtzte haben gesagt, dass du die Operation gut �berstanden hast",
				"Du packst das sicher. Nur noch die Chemotherapie",
				"und schon ist es vorbei.",
				"In ein paar Monaten, lachen wir schon wieder dar�ber",
				"Ach Mann.. Immer trifft es die Guten.",
				"Deine Eltern warten schon vor der T�r."
		};
		float[] durations = new float[]{
				5,5,5,5,5,5
		};
		room.addActionRoom(0, 0, -1, -1, new DialogeSceneAction(world, messages, durations, new ChangeRoomAction(world,RoomEnum.HOSPITAL_ROOM_PARENTS)));
		return room;
	}

}
