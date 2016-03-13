package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.ScreenEnum;
import com.jonas.thecuring.ScreenManager;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;
import com.jonas.thecuring.storyGame.Actions.TimerAction;
import com.jonas.thecuring.storyGame.Actions.TransitionTextAction;

public class GraveyardRoomFactory extends RoomFactory {

	@Override
	public Room getRoom(World world) {
		Room room;
		room = new Room((Texture) Assets.getInstance().get("room_graveyard"), world,new Vector2(10,10));
		room.addActionRoom(0, 0, 0, 0, new TimerAction(world, 15, new NextDayAction(world, 
				new TransitionTextAction(world, "Du bist gestorben.", true, new TransitionTextAction(world, "Rede mit deinem Mitspieler darüber.", true, new ChangeScreenAction(world, null))))));
		return room;
	}
	class ChangeScreenAction extends Action
	{

		public ChangeScreenAction(World world, Action nextAction) {
			super(world, nextAction);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			ScreenManager.getInstance().show(ScreenEnum.MAIN_MENU, world.multiplexer);
			
		}
		
	}
}
