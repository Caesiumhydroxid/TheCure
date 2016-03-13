package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
import com.jonas.thecuring.storyGame.Actions.SendMessageAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class WorkRoomPresentationFactory extends RoomFactory{

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_work"), world,new Vector2(-10,10));
		room.addActionRoom(0, 0, -1, -1, new SendMessageAction(world, "Das Subjekt hält heute einen wichtigen Vortrag in seiner Arbeit.", null));
		Action action = 
				new DisplayDialogueAction(world, "Hallo, ich will Ihnen heute mein Projekt vorstellen.",4,50,
						new TimerAction(world, 4, 
								new DisplayDialogueAction(world, "Um eine Verbesserung der Effizienz unseres Gerätes",3,50, 
												new TimerAction(world, 3, 
														new DisplayDialogueAction(world, "zu erreichen, müssen wir definitiv.....",4,50,
																		new TimerAction(world, 3, 
																				new DisplayDialogueAction(world, "Arrghh!",3,50,null)))))));
		
		Animation anim = new Animation(100,world.player.holdSpeech.getKeyFrame(100, false) );
		//room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 0.6f, new SetPlayerAction(world, true, false, null)));
		room.addActionRoom(0,0,0,0,new MovePlayerAction(world, new Vector2(70,10),new ActionStarter(world,action,
				new AnimationPlayerAction(world, world.player.holdSpeech,
						new ActionStarter(world, new ChangeRoomAction(world,RoomEnum.HOSPTIAL_ROOM,5), new AnimationPlayerAction(world, anim, null))))));
	
		return room;
	}
	
}
