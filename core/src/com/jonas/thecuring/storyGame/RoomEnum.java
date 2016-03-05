package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayTextAction;

public enum RoomEnum {
	HOME_ROOM{
		Room homeRoom;
		public Room getRoom(Object... params)
		{
			if(homeRoom==null)
			{	
				homeRoom = new Room((Texture) Assets.getInstance().get("room_home"), (World) params[0]);
				homeRoom.eventRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new ChangeRoomAction((World) params[0],HOME_ANTE_ROOM)));
			}
			return homeRoom;
		}
	},
	HOME_ANTE_ROOM{
		Room anteRoom;
		public Room getRoom(Object... params)
		{
			if(anteRoom==null)
			{	
				anteRoom = new Room((Texture) Assets.getInstance().get("room_home_anteroom"), (World) params[0],new Vector2(0,10));
				anteRoom.eventRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new DisplayTextAction("Test",(World)params[0],false)));
			}
			return anteRoom;
		}
	};

	public Room getRoom(Object... params) {
		return null;
	}

}
