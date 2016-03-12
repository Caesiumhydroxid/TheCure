package com.jonas.thecuring.storyGame.RoomFactories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.ActionRoom;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.Room;
import com.jonas.thecuring.storyGame.RoomEnum;
import com.jonas.thecuring.storyGame.World;
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.AnimationNPCAction;
import com.jonas.thecuring.storyGame.Actions.ChangeClothesAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DialogeSceneAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.MenuAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.SwitchWithComparatorAction;
import com.jonas.thecuring.storyGame.Actions.SwitchWithVariableAction;
import com.jonas.thecuring.storyGame.Actions.TalkToNPCAction;
import com.jonas.thecuring.storyGame.Actions.WorldComperator;

public class HomeRoomDay3Factory extends RoomFactory{
	@Override
	public Room getRoom(World world) {
		Room homeRoom;
		homeRoom = new Room((Texture) Assets.getInstance().get("room_home"), world,new Vector2(70,10));

		class CompareWifeKnowsCancer extends WorldComperator
		{
			public CompareWifeKnowsCancer(World world) {
				super(world);
			}

			@Override
			public boolean compare() {
				return world.wifeKnowsAboutCancer;
			}
		}
		NPC npc = new NPC((Texture)Assets.getInstance().get("wife"),new String[]{"Heute ist dein letzter Tag vor der OP","Mach einfach das, was du am besten findest"},true,new Rectangle(5,0,8,28));
		npc.setAction(new DisplayActionText(world, "Sprechen",new TalkToNPCAction(world, npc, null)));
		npc.setPosition(new Vector2(68,19));
		Animation wifeNotCrying = npc.getCurrentAnimation();
		TextureRegion[][] tmp = new TextureRegion((Texture)Assets.getInstance().get("wife_crying")).split(40, 40);
		Animation wifeCrying = new Animation(0.5f,tmp[0]);

		String[] wifeKnowsAboutCancerMessages = new String[]
			{
					"Guten Morgen!",
					"Morgen steht deine Operation an.",
					"Ich hoffe du nützt deinen Tag heute."
						
		};
		float[] wifeKnowsAboutDurations = new float[]{
				10,5,5
		};
		String[] wifeKnowsAboutCancerNotMessages = new String[]
				{
						"Wieso warst du gestern so lange weg?",
						"Naja. Gut das du wieder da bist. Willst du den",
						"Tag heute villeicht mit uns verbringen?",
						"Morgen ist ja schon die Operation!"
			};
			float[] wifeKnowsAboutNotDurations = new float[]{
					10,5,5,5
			};
		Action wifeKnowsAboutCancer=new SwitchWithVariableAction(world,new Boolean(world.timeWithFamily),new Boolean(true),
				new DialogeSceneAction(world, wifeKnowsAboutCancerMessages, wifeKnowsAboutDurations, new SetPlayerAction(world, true, true, null)), 
				new DialogeSceneAction(world, wifeKnowsAboutCancerNotMessages, wifeKnowsAboutNotDurations, new SetPlayerAction(world, true, true, null)));
		
		String[] messages = new String[]{
				"Ich bin heute als du noch geschlafen hast",
				"vom Krankenhaus angerufen worden.",
				"Wieso hast du mir nicht erzählt was los ist?"
		};
		
		float[] durations = new float[]{
				10,5,5
		};
		String[] keineAngstMachen = new String[]{
				"Ich wollte dir keine Angst machen...",
				"Naja.. irgendwann musste es ja raus..",
				"Ich verstehe nicht warum du ",
				"es mir nicht gleich sagen konntest.",
				"...",
				"Nun.. Du hast morgen die Operation. Gemeinsam werden wir",
				"das durchstehen. N.. Nutze den Tag heute einfach gut."
		};
		float[] keineAngstMachenDurations = new float[]{
				5,5,5,5,3,5,5
		};
		float[] keineAngstMachenPositions = new float[]{
				55,55,4,4,4,4,4
		};
		String[] weisNicht = new String[]{
				"Wie du weißt nicht? Das ist nicht dein Ernst!",
				"Ich sollte soetwas doch von dir erfahren!",
				"Naja.. irgendwann musste es ja raus..",
				"Ich verstehe nicht warum du ",
				"es mir nicht gleich sagen konntest.",
				"...",
				"Nun.. Du hast morgen die Operation. Gemeinsam werden wir",
				"das durchstehen. N.. Nutze den Tag heute einfach gut."
		};
		float[] weisNichtDurations = new float[]{
				5,5,5,5,5,3,5,5
		};
		float[] weisNichtPositions = new float[]{
				4,4,4,4,4,4,4,4
		};
		Action wifeDoesntKnowAboutCancer = new AnimationNPCAction(world, npc, wifeCrying, new DialogeSceneAction(world, messages, durations, new MenuAction(world,true, new String[]{"Weiß nicht","Keine Angst machen"}, "Antwort:", 
				new Action[]{
						new DialogeSceneAction(world, weisNicht, weisNichtDurations,weisNichtPositions, new SetPlayerAction(world, true, true, new AnimationNPCAction(world, npc, wifeNotCrying,null))),
						new DialogeSceneAction(world, keineAngstMachen, keineAngstMachenDurations,keineAngstMachenPositions, new SetPlayerAction(world, true, true, new AnimationNPCAction(world, npc, wifeNotCrying,null)))
				})));
		
		homeRoom.addActionRoom(0, 0, -1, -1, new SetPlayerAction(world, true, false, new SwitchWithComparatorAction(world, new CompareWifeKnowsCancer(world), wifeKnowsAboutCancer, wifeDoesntKnowAboutCancer)));
		homeRoom.add(npc);
		homeRoom.actionRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new ChangeRoomAction(world,RoomEnum.HOME_ANTE_ROOM)));
		homeRoom.addActionRoom(110, 17, 34, 45, new DisplayActionText(world, "Umziehen", new ChangeClothesAction(world)));
		homeRoom.colliders.add(new Rectangle(0,0,1,90));
		return homeRoom;
	}
}
