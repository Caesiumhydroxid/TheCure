package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.GameObject;
import com.jonas.thecuring.storyGame.RepeatingGameObject;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.AnimationGameObjectAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.SendMessageAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;

public class ParachuteJumpRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_hospital"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, -1, -1, new SendMessageAction(world, "Das Subjekt geht Fallschirmspringen. Etwas, was es schon immer machen wollte.", null));
		room.addActionRoom(0, 0, -1, -1, new SetPlayerAction(world, false, false, null));
		RepeatingGameObject sky = new RepeatingGameObject((Texture)Assets.getInstance().get("sky_parachute_jump"));
		sky.velocity.set(0,90);
		room.add(sky);
		TextureRegion[][] tmp = new TextureRegion((Texture)Assets.getInstance().get("parachute_animation")).split(160, 90);
		TextureRegion[] spl = new TextureRegion[8];
		for(int i=0;i<2;i++)
		{
			for(int ii=0;ii<4;ii++)
			{
				spl[ii+i*4] = tmp[i][ii];
			}
		}
		Animation animFlying = new Animation(0.25f, spl);
		Animation animOpenParachute = new Animation(0.25f,tmp[2]);
		Animation animPrachute = new Animation(0.4f,tmp[3]);
		GameObject flyer = new GameObject(animFlying);
		room.add(flyer);
		class ChangeSpeedAction extends Action{
			RepeatingGameObject obj;
			Vector2 velocity;
			public ChangeSpeedAction(World world, RepeatingGameObject obj ,Vector2 velocity,Action nextAction) {
				super(world, nextAction);
				this.velocity = velocity;
				this.obj = obj;
			}

			@Override
			public void run() {
				obj.velocity = velocity;
				if(nextAction != null)
				{
					nextAction.run();
				}
				running = true;
				toDelete = true;
			}
			
		}
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 7, new AnimationGameObjectAction(world, flyer, animOpenParachute, null)));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 7.5f, new ChangeSpeedAction(world, sky, new Vector2(0,30),null)));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 7.9f, new AnimationGameObjectAction(world, flyer, animPrachute, null)));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 15, new NextDayAction(world,new ChangeRoomAction(world, RoomEnum.HOME_ROOM,2,4,"",new SetPlayerAction(world, true, true, null)))));
		return room;
	}

}
