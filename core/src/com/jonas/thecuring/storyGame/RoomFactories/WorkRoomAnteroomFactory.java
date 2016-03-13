package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.MenuAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TalkToNPCAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class WorkRoomAnteroomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_work_anteroom"), world,new Vector2(0,10));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 0.55f, new SetPlayerAction(world, true, true, null)));
		NPC coworker= new NPC((Texture) Assets.getInstance().get("coworker"),new String[]{"Hallo. Was ist denn gestern passiert?","Oh... Und du bist dir sicher, dass du heute arbeiten willst?"},false,new Rectangle(5,0,12,33));
		coworker.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, coworker, null)));
		coworker.setPosition(new Vector2(80, 26));
		room.add(coworker);
		room.addActionRoom(0, 0, 1, 90, new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE));
		room.colliders.add(new Rectangle(160,0,1,90));
		room.addActionRoom(120, 33, 29, 44,new DisplayActionText(world, "Eintreten", new MenuAction(world, new String[]{"Nein","Ja"},"Arbeiten?", new Action[]{null, new ChangeRoomAction(world, RoomEnum.WORK_ROOM,3,new SetPlayerAction(world, false, false, null))})));
		return room;
	}

}
