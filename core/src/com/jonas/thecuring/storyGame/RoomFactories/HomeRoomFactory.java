package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.ActionRoom;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.ChangeClothesAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.SendMessageAction;
import com.jonas.thecuring.storyGame.Actions.SetBlackAction;
import com.jonas.thecuring.storyGame.Actions.TalkToNPCAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class HomeRoomFactory extends RoomFactory {
	@Override
	public Room getRoom(World world) {
		Room homeRoom;
		homeRoom = new Room((Texture) Assets.getInstance().get("room_home"), world,new Vector2(70,10));
		homeRoom.addActionRoom(0, 0, 0, 0, new TimerAction(world, 28,new SendMessageAction(world, "Subjekt:\nName: ???\nAlter:36\nGeschlecht: Männlich\n", null)));
		homeRoom.addActionRoom(0, 0, -1, -1, new SetBlackAction(world, true,
				new TransitionTextAction(world,2,6,"Du kannst dich mit den Pfeiltasten bewegen. Mit der Leertaste bestätigst du Aktionen" , true, 
						new TransitionTextAction(world,2,5, "Heute ist dein großer Tag. Das Meeting, auf das du schon so lange hingefiebert hast, steht bevor.", true, 
								new TransitionTextAction(world,2,5, "Viel Glück.", false,null)))));

		NPC npc = new NPC((Texture)Assets.getInstance().get("wife"),new String[]{"Ich wünsche dir viel Erfolg bei deiner Präsentation","Vergiss nichts."},true,new Rectangle(5,0,8,28));
		npc.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, npc, null)));
		npc.setPosition(new Vector2(68,19));
		homeRoom.add(npc);
		homeRoom.actionRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new ChangeRoomAction(world,RoomEnum.HOME_ANTE_ROOM)));
		homeRoom.addActionRoom(110, 17, 34, 45, new DisplayActionText(world, "Umziehen", new ChangeClothesAction(world)));
		homeRoom.colliders.add(new Rectangle(0,0,1,90));
		return homeRoom;
	}
}
