package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.*;

public enum RoomEnum {
	HOME_ROOM{
		Room homeRoom;
		public Room getRoom(Object... params)
		{
			if(homeRoom==null)
			{	
				World world = (World) params[0];
				homeRoom = new Room((Texture) Assets.getInstance().get("room_home"), (World) params[0],new Vector2(10,10));
				homeRoom.actionRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new ChangeRoomAction((World) params[0],HOME_ANTE_ROOM)));
				class ChangeClothesAction extends Action
				{
					public ChangeClothesAction(World world)
					{
						super(world);
					}

					@Override
					public void run() {
						TransitionScreen transition = new TransitionScreen(1);
						transition.addListener(new TransitionListener() {
							
							@Override
							public void transitionIsAtMax() {
								world.player.dress();
							}
							
							@Override
							public void transitionFinished() {
							}
						});
						world.push(transition);
					}
					
				};
				homeRoom.addActionRoom(110, 17, 34, 45, new DisplayActionText(world, "Umziehen", new ChangeClothesAction(world)));
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
				anteRoom = new Room((Texture) Assets.getInstance().get("room_home_anteroom"), world,new Vector2(50,10));
				anteRoom.actionRooms.add(new ActionRoom(new Rectangle(16,0,27,90), new DisplayActionText(world, "Eintreten",new ChangeRoomAction(world, HOME_DAUGHTER_ROOM))));
				anteRoom.actionRooms.add(new ActionRoom(new Rectangle(124,0,27,90), new DisplayActionText(world, "Hinausgehen",new ChangeRoomAction(world, HOME_OUTSIDE))));
				anteRoom.addActionRoom(0, 0, 1, 90, new ChangeRoomAction(world,HOME_ROOM));
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
				room = new Room((Texture) Assets.getInstance().get("room_home_daughter"), world,new Vector2(120,10));
				NPC npc = new NPC((Texture)Assets.getInstance().get("daughter"),
						new DisplayActionText(world, "Sprechen", new DisplayDialogueAction("Hallo Papa, viel Gl�ck Heute",world,true)), new Rectangle(17,2,8,28));
				npc.setPosition(new Vector2(85-17,23-2));
				room.add(npc);
				room.colliders.add(new Rectangle(10, 5, 40, 40));
				room.addActionRoom(160, 0, 1, 90, new ChangeRoomAction(world, HOME_ANTE_ROOM));
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
				room = new Room((Texture) Assets.getInstance().get("room_home_outside"), world,new Vector2(0,10));
				room.colliders.add(new Rectangle(107, 10, 45, 40));
				room.addActionRoom(0, 0, 1, 90, new ChangeRoomAction(world, HOME_ANTE_ROOM));
				room.addActionRoom(100, 0, 7, 90, new DisplayActionText(world, "Fahre",
						new MenuAction(world, new String[]{"Lol","Test"}, 
						new Action[]{
								new ChangeRoomAction(world, HOME_DAUGHTER_ROOM),
								new ChangeRoomAction(world, HOME_ANTE_ROOM)})
						));
			}
			return room;
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
