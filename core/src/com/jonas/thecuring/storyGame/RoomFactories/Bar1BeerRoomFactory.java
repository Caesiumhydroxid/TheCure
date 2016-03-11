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
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class Bar1BeerRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_bar_1beer"), world,new Vector2(120,10));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 7, new ChangeRoomAction(world, RoomEnum.BAR_6BEER_ROOM,2,5,"Der Abend wurde länger... und länger")));
		return room;
	}

}
