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
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;

public class HospitalRoomDay2Factory extends RoomFactory {

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
		doctor.setPosition(new Vector2(110,27));
		room.add(doctor);
		bed.setPosition(new Vector2(113,16));
		room.add(bed);
		String[] messages = new String[]{
			"Aufgrund der Untersuchungen die wir gestern",
			"durchgeführt haben. Muss ich ihnen mitteilen..",
			"...",
			"... ...",
			" dass wir einen Hirn-Tumor festgestellt haben.",
			"wir müssen ihn so bald wie möglich Operieren.",
			"Der früheste Termin ist in 2 Tagen. Bis dorthin",
			"haben sie Zeit um sich ihren beschäftigungen zu widmen.",
			"Wir werden sie nach Hause fahren. Sie sollten alles",
			"mit ihrer Familie klären."
		};
		float[] durations = new float[]{
				5,
				5,
				3,
				3,
				5,
				5,
				5,
				5,
				5,
				5
		};
		room.addActionRoom(0, 0, -1, -1, new DialogeSceneAction(world, messages, durations, 
				new ChangeRoomAction(world, RoomEnum.HOME_ROOM,2,new Action(world,null) {
					@Override
					public void run() {
						world.player.render = true;
						world.player.processInput = true;
						running = true;
					}
				})));
		return room;
	}

}
