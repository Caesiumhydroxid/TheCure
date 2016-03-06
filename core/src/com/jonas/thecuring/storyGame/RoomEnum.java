package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;

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
				World world = (World) params[0];
				anteRoom = new Room((Texture) Assets.getInstance().get("room_home_anteroom"), world,new Vector2(0,10));
				anteRoom.eventRooms.add(new ActionRoom(new Rectangle(0,0,20,90), new DisplayDialogueAction("Test",world,false)));
				anteRoom.eventRooms.add(new ActionRoom(new Rectangle(16,0,27,90), new DisplayActionText(world, "Eintreten",new ChangeRoomAction(world, WORK_ROOM))));
			}
			return anteRoom;
		}
	},
	WORK_ROOM{
		Room workRoom;
		public Room getRoom(Object... params)
		{
			if(workRoom==null)
			{	
				World world = (World) params[0];
				workRoom = new Room((Texture) Assets.getInstance().get("room_work"), world,new Vector2(0,10));
			}
			return workRoom;
		}
	};

	public Room getRoom(Object... params) {
		return null;
	}

}
