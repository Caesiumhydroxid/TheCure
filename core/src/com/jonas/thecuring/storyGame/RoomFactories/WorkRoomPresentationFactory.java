package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.AnimationPlayerAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.MovePlayerAction;

public class WorkRoomPresentationFactory extends RoomFactory{

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_work"), world,new Vector2(-10,10));
		world.player.actionsToAdd.add(new MovePlayerAction(world, new Vector2(70,10), new AnimationPlayerAction(world, world.player.holdSpeech, new ChangeRoomAction(world,RoomEnum.HOME_ROOM,2 ))));
		return room;
	}
	
}
