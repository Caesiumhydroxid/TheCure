package com.jonas.thecuring.storyGame;

import com.jonas.thecuring.storyGame.RoomFactories.*;

public enum RoomEnum {
	HOME_ROOM{
		Room homeRoom;
		public Room getRoom(Object... params)
		{
			if(homeRoom==null)
			{	
				World world = (World) params[0];
				HomeRoomFactory f = new HomeRoomFactory();
				homeRoom = f.getRoom(world);
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
				HomeAnteRoomFactory f = new HomeAnteRoomFactory();
				anteRoom = f.getRoom(world);
			}
			return anteRoom;
		}
	},
	HOME_DAUGHTER_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				HomeDaughterRoomFactory f = new HomeDaughterRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	HOME_OUTSIDE{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				HomeOutsideFactory f = new HomeOutsideFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	WORK_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				WorkRoomPresentationFactory f = new WorkRoomPresentationFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	HOSPTIAL_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				HospitalRoomFactory f = new HospitalRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	HOSPITAL_ROOM_DAY2{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				HospitalRoomDay2Factory f = new HospitalRoomDay2Factory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	PARACHUTE_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				RoomFactory f = new ParachuteRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	};

	public Room getRoom(Object... params) {
		return null;
	}

}
