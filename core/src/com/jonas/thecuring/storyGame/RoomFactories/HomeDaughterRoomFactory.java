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
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;

public class HomeDaughterRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_home_daughter"), world,new Vector2(120,10));
		NPC npc = new NPC((Texture)Assets.getInstance().get("daughter"),
				new DisplayActionText(world, "Sprechen", new DisplayDialogueAction(world,"Hallo Papa, viel Glück Heute",true)), new Rectangle(17,2,8,28));
		npc.setPosition(new Vector2(85-17,23-2));
		room.add(npc);
		room.colliders.add(new Rectangle(10, 5, 40, 40));
		room.addActionRoom(160, 0, 1, 90, new ChangeRoomAction(world, RoomEnum.HOME_ANTE_ROOM));
		return room;
	}

}
