package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.GameObject;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.MenuAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TalkToNPCAction;

public class HospitalRoomDay4Factory extends RoomFactory{
	Room room;

	@Override
	public Room getRoom(World world) {
		room = new Room((Texture) Assets.getInstance().get("room_hospital"), world,new Vector2(10,10));
		NPC npc;
		npc = new NPC((Texture)Assets.getInstance().get("son"),new String[]{"Bis nacher!","Was passiert jetzt mit dir?","Ich habe dich lieb."},true,new Rectangle(4,0,8,28));
		npc.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, npc, null)));
		npc.setPosition(new Vector2(35,23));
		room.add(npc);
		
		NPC daughter;
		daughter = new NPC((Texture)Assets.getInstance().get("daughter"),new String[]{"Hey Papa, du schaffst das.", "Ich habe dich lieb."},true,new Rectangle(17,2,8,28));
		daughter.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, daughter, null)));
		daughter.setPosition(new Vector2(85-17,23-2));
		room.add(daughter);
		
		NPC wife = new NPC((Texture)Assets.getInstance().get("wife"),new String[]{"Das wird schon Schatz","Ich liebe dich."},true,new Rectangle(5,0,8,28));
		wife.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, wife, null)));
		wife.setPosition(new Vector2(50,19));
		room.add(wife);
		
		NPC doctor;
		Action op = new MenuAction(world,true, new String[]{"Nein","Ja"}, "Sind sie bereit?", new Action[]{
						null,
						new SetPlayerAction(world, false, false, new ChangeRoomAction(world, RoomEnum.HOSPITAL_OP_ROOM))
				});
		doctor = new NPC((Texture)Assets.getInstance().get("doctor"),new Rectangle(4,0,8,28),new DisplayActionText(world, "Sprechen", op));
		doctor.setPosition(new Vector2(105,23-2));
		doctor.z = 2;
		room.colliders.add(new Rectangle(113,16,10,90));
		room.add(doctor);
		
		GameObject bed = new GameObject((Texture)Assets.getInstance().get("bed_empty"));
		bed.z = 3;
		bed.setPosition(new Vector2(113,16));
		room.add(bed);
		room.colliders.add(new Rectangle(0, 0, 1, 90));
		
		
		return room;
	}
	
}
