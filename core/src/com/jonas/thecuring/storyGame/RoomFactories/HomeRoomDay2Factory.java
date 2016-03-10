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
import com.jonas.thecuring.storyGame.Actions.Action;
import com.jonas.thecuring.storyGame.Actions.ChangeClothesAction;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.DisplayActionText;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueAction;
import com.jonas.thecuring.storyGame.Actions.DisplayDialogueEndAction;
import com.jonas.thecuring.storyGame.Actions.MenuAction;
import com.jonas.thecuring.storyGame.Actions.SetPlayerAction;
import com.jonas.thecuring.storyGame.Actions.SetWorldVariableAction;
import com.jonas.thecuring.storyGame.Actions.SwitchWithComparatorAction;
import com.jonas.thecuring.storyGame.Actions.VariableSetter;
import com.jonas.thecuring.storyGame.Actions.WorldComperator;

public class HomeRoomDay2Factory extends RoomFactory{

	@Override
	public Room getRoom(World world) {
		Room homeRoom;
		homeRoom = new Room((Texture) Assets.getInstance().get("room_home"), world,new Vector2(70,10));

		class CompareWomanKnowsCancer extends WorldComperator
		{
			public CompareWomanKnowsCancer(World world) {
				super(world);
			}

			@Override
			public boolean compare() {
				return world.wifeKnowsAboutCancer;
			}
		}
		
		NPC npc = new NPC((Texture)Assets.getInstance().get("wife"),new Rectangle(5,0,8,28),null);
		npc.setAction(new DisplayActionText(world, "Sprechen", new SwitchWithComparatorAction(world, new CompareWomanKnowsCancer(world), 
				new DisplayDialogueAction(world, "Wir müssen jetzt stark sein...", null), new DisplayDialogueAction(world, "Warum erzählst du mir nichts?", null))));
		npc.setPosition(new Vector2(68,19));
	
		
		Action setProcessingInput = new SetPlayerAction(world, true, true, null);
		Action wasSollenWirTun = new MenuAction(world, true,new String[]{"Operation in  2 Tagen"}, "Antwort", new Action[]{new DisplayDialogueEndAction(world, "Ich habe in 2 Tagen eine Operation. Dann müssen wir weitersehen.", 
				new DisplayDialogueEndAction(world, "Okay...", new DisplayDialogueEndAction(world, "...",3, new DisplayDialogueEndAction(world, "Ich werde dich so gut es geht unterstützen",5, setProcessingInput))))});
		
		Action tumorDiagnostiziert = new MenuAction(world,true, new String[]{"Tumor diagnostiziert.","Aach.. nicht so wichtig.." }, "Antwort:", new Action[]{
				new DisplayDialogueEndAction(world,"Ich... ich habe",4,new DisplayDialogueEndAction(world,"einen Gehirntumor.",4,new DisplayDialogueEndAction(world,"Oh mein Gott!?...",3,new DisplayDialogueEndAction(world,".....",3,new DisplayDialogueEndAction(world,"W.. was müssen wir tun?",3,wasSollenWirTun))))),
				new DisplayDialogueEndAction(world,"Okay wenn du mir nichts sagen willst..",4,new SetWorldVariableAction(world, new VariableSetter(world) {
					@Override
					public void setVariable() {
						world.wifeKnowsAboutCancer = false;
					}
				}, setProcessingInput))});
		Action erstHeute = new MenuAction(world,true, new String[]{"Ich war im Krankenhaus."}, "Antwort", new Action[]{new DisplayDialogueEndAction(world,"Wieso das?!",4,tumorDiagnostiziert)});
		
		homeRoom.addActionRoom(0, 0, -1, -1, new SetPlayerAction(world, true, false, new DisplayDialogueEndAction(world, "Was ist passiert? Wo warst du?",4, new MenuAction(world, true,new String[]{"Im Krankenhaus","Was soll gewesen sein?"}, "Antwort:",
				new Action[]{new DisplayDialogueEndAction(world,"Oh! Was ist passiert?",3, tumorDiagnostiziert),
						new DisplayDialogueEndAction(world,"Warum bist du erst heute heimgekommen?",4, erstHeute)}))));
		
		
		homeRoom.add(npc);
		homeRoom.actionRooms.add(new ActionRoom(new Rectangle(160,0,20,90), new ChangeRoomAction(world,RoomEnum.HOME_ANTE_ROOM)));
		homeRoom.addActionRoom(110, 17, 34, 45, new DisplayActionText(world, "Umziehen", new ChangeClothesAction(world)));
		homeRoom.colliders.add(new Rectangle(0,0,1,90));
		return homeRoom;
	}
	
}
