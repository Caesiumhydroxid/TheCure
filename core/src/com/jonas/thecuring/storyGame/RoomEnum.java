package com.jonas.thecuring.storyGame;

import com.jonas.thecuring.storyGame.RoomFactories.Bar1BeerRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.Bar6BeerRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeAnteRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeDaughterRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeOutsideFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeRoomDay2Factory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HomeSonRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.HospitalRoomDay2Factory;
import com.jonas.thecuring.storyGame.RoomFactories.HospitalRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.ParachuteRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.RoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.WorkRoomAnteroomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.WorkRoomFactory;
import com.jonas.thecuring.storyGame.RoomFactories.WorkRoomPresentationFactory;

public enum RoomEnum {
	HOME_ROOM{
		Room homeRoom;
		public Room getRoom(Object... params)
		{
			World world = (World) params[0];
			RoomFactory f;
			if(world.day==0)
			{
				f = new HomeRoomFactory();
			}
			else
			{
				f = new HomeRoomDay2Factory();
			}
			homeRoom = f.getRoom(world);
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
	HOME_SON_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				HomeSonRoomFactory f = new HomeSonRoomFactory();
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
	WORK_LECTURE_ROOM{
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
	},
	BAR_1BEER_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				RoomFactory f = new Bar1BeerRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	BAR_6BEER_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				RoomFactory f = new Bar6BeerRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	WORK_ANTE_ROOM{
		Room room;
		public Room getRoom(Object... params)
		{
			if(room==null)
			{	
				World world = (World) params[0];
				RoomFactory f = new WorkRoomAnteroomFactory();
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
				RoomFactory f = new WorkRoomFactory();
				room = f.getRoom(world);
			}
			return room;
		}
	},
	GRAVEYARD_ROOM{
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
	};
	

	public Room getRoom(Object... params) {
		return null;
	}

}
