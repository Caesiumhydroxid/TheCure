package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.TalkToNPCAction;

public class HomeSonRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		NPC npc = null;
		room = new Room((Texture) Assets.getInstance().get("room_home_son"), world,new Vector2(120,10));
		npc = new NPC((Texture)Assets.getInstance().get("son"),new String[]{"Guten Morgen Papa. Ich bin noch so m�de...","... G�hn"},false,new Rectangle(4,0,8,28));
		npc.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, npc, null)));
		npc.setPosition(new Vector2(100,23-2));
		room.add(npc);
		room.colliders.add(new Rectangle(0, 0, 1, 90));
		room.addActionRoom(160, 0, 1, 90, new ChangeRoomAction(world, RoomEnum.HOME_ANTE_ROOM));
		return room;
	}

}
