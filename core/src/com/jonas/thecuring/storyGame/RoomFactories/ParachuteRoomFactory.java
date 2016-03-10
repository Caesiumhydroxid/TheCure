package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.GameObject;
import com.jonas.thecuring.storyGame.RepeatingGameObject;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;

public class ParachuteRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, -1, -1, new SetPlayerAction(world, false, false, null));
		RepeatingGameObject sky = new RepeatingGameObject((Texture)Assets.getInstance().get("sky_parachute"));
		sky.velocity.set(60,0);
		room.add(sky);
		TextureRegion[][] tmp = new TextureRegion((Texture)Assets.getInstance().get("plane_parachute")).split(160, 90);
		Animation anim = new Animation(0.3f, tmp[0]);
		room.add(new GameObject(anim));
		return room;
	}

}
