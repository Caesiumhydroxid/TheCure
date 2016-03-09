package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ActionStarter;
import com.jonas.thecuring.storyGame.Actions.AddActionToRoom;
import com.jonas.thecuring.storyGame.Actions.AnimationPlayerAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;
import com.jonas.thecuring.storyGame.Actions.MovePlayerAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class WorkRoomPresentationFactory extends RoomFactory{

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_work"), world,new Vector2(-10,10));
		Action action = 
				new DisplayDialogueAction(world, "Hallo, ich will ihnen heute mein Projekt vorstellen.",4,50,
						new TimerAction(world, 4, 
								new DisplayDialogueAction(world, "Um eine Verbesserung der Effizienz unseres Gerätes",3,50, 
												new TimerAction(world, 3, 
														new DisplayDialogueAction(world, "zu erhalten müssen wir definitiv",4,50,
																		new TimerAction(world, 3, 
																				new DisplayDialogueAction(world, "Arrghh!",3,50,null)))))));
		
		room.addActionRoom(0,0,0,0,new MovePlayerAction(world, new Vector2(70,10),new ActionStarter(world,action,
				new AnimationPlayerAction(world, world.player.holdSpeech, 
						new TransitionTextAction(world,3,5, "Du kippst um und wirst in ein Krankenhaus gebracht", true,
						new ChangeRoomAction(world,RoomEnum.HOSPTIAL_ROOM,2))))));
		
		return room;
	}
	
}
