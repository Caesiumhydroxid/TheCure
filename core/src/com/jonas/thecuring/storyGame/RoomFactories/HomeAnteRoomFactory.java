package com.jonas.thecuring.storyGame.RoomFactories;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.ActionRoom;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.CustomComparator;
import com.jonas.thecuring.storyGame.Actions.DayChangerAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;
import com.jonas.thecuring.storyGame.Actions.SwitchWithComparatorAction;
import com.jonas.thecuring.storyGame.Actions.SwitchWithVariableAction;

public class HomeAnteRoomFactory extends RoomFactory {
	
	public ArrayList<Action> dayActionOutsideDoor;
	
	public HomeAnteRoomFactory() {
		dayActionOutsideDoor = new ArrayList<Action>();
	}
	@Override
	public Room getRoom(World world) {
		Room anteRoom;
		class ClothingComparator implements CustomComparator
		{
			private World world;
			public ClothingComparator(World world) {
				this.world = world;
			}
			@Override
			public boolean compare() {
				return world.player.currentClothing ==1;
			}
		}
		anteRoom = new Room((Texture) Assets.getInstance().get("room_home_anteroom"), world,new Vector2(10,10));
		dayActionOutsideDoor.add(new DisplayActionText(world, "Hinausgehen",new SwitchWithComparatorAction(world, new ClothingComparator(world),
				new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE),
				new DisplayDialogueAction(world, "Willst du wirklich in diesem Gewand in die Arbeit gehen?",null))));
		dayActionOutsideDoor.add(new DisplayActionText(world, "Hinausgehen", new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE)));
		dayActionOutsideDoor.add(new DisplayActionText(world, "Hinausgehen", new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE)));
		dayActionOutsideDoor.add(new DisplayActionText(world, "Hinausgehen", new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE)));
		dayActionOutsideDoor.add(new DisplayActionText(world, "Hinausgehen", new ChangeRoomAction(world, RoomEnum.HOME_OUTSIDE)));
		anteRoom.actionRooms.add(new ActionRoom(new Rectangle(16,0,27,90),new DisplayActionText(world, "Eintreten",new ChangeRoomAction(world, RoomEnum.HOME_DAUGHTER_ROOM))));
		anteRoom.addActionRoom(58, 32, 27, 90, new DisplayActionText(world, "Eintreten",new ChangeRoomAction(world, RoomEnum.HOME_SON_ROOM)));
		anteRoom.actionRooms.add(new ActionRoom(new Rectangle(124,0,27,90),new DayChangerAction(world, dayActionOutsideDoor.toArray(new Action[dayActionOutsideDoor.size()]))));
		anteRoom.addActionRoom(0, 0, 1, 90, new ChangeRoomAction(world,RoomEnum.HOME_ROOM));
		return anteRoom;
	}
	
}
