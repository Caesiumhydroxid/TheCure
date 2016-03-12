package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.GameObject;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.AddActionToRoom;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class HospitalRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital"), world,new Vector2(10,10));
		
		room.addActionRoom(0, 0, -1, -1, new Action(world, null) {
			@Override
			public void run() {
				world.player.render = false;
				world.player.processInput = false;
			}
		});
		GameObject bed = new GameObject((Texture)Assets.getInstance().get("bed_hospital"));
		bed.z = 10;
		TextureRegion[][] reg = new TextureRegion((Texture) Assets.getInstance().get("doctor_anim")).split(40, 40);
		Animation doctorAnim = new Animation(0.3f,reg[0]); 
		doctorAnim.setPlayMode(PlayMode.LOOP);
		NPC doctor = new NPC(doctorAnim, new Action(world, null) {
			@Override
			public void run() {
			}
		}, new Rectangle(-2,-2,-2,-2));
		doctor.z =1;
		doctor.setPosition(new Vector2(110,27));
		room.add(doctor);
		bed.setPosition(new Vector2(113,16));
		room.add(bed);
		room.addActionRoom(0, 0, -1, -1, new AddActionToRoom(world,
				new DisplayDialogueAction(world, "Wir wissen leider noch nicht genau was passiert ist", 7, 
						new TimerAction(world, 5, 
								new DisplayDialogueAction(world, "Wir müssen noch untersuchungen durchführen.",7, 
										new TimerAction(world, 5,
												new DisplayDialogueAction(world, "Morgen sollten wir genaueres wissen", 7,
														new TimerAction(world, 7, 
																new DisplayDialogueAction(world, "vermutlich war es der Stress... Oh.. er schläft schon wieder",
																		new TimerAction(world, 5,
																				new TransitionTextAction(world, "Du Schläfst bis zum nächsten Tag durch und wachst im Krankenhaus auf", true, 
																						new NextDayAction(world, 
																								new ChangeRoomAction(world, RoomEnum.HOSPITAL_ROOM_DAY2)))))))))))));
		return room;
	}

}
