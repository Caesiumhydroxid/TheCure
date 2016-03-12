package com.jonas.thecuring.storyGame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.jonas.thecuring.Assets;
import com.jonas.thecuring.storyGame.Actions.ChangeRoomAction;
import com.jonas.thecuring.storyGame.Actions.NextDayAction;

public class Memory extends Room {

	MemoryToken[][] field;
	private BitmapFont font;
	private int currentTilex;
	private int currentTiley;
	private int amountOpened = 0;
	private int selectedTile1x;
	private int selectedTile1y;
	private int selectedTile2x;
	private int selectedTile2y;
	private float elapsedTime;
	private boolean timeRunning;
	private TextureRegion[] tokens;
	private Texture memorySelected;
	private Texture memoryBack;
	private int randomMovesOpponent;
	private float elapsedOpponentTime;
	private int opponentTile = -1;
	private int opponentLastPosition;
	private int playerScore;
	boolean moveToCorrect = false;
	private int opponentScore;
	private int remainingTiles =16;
	private ArrayList<Integer> seenByOpponent;
	boolean firstOpponent = true;
	private boolean yourMove = true;

	public Memory(Texture texture, World world) {
		super(texture, world);
		field = new MemoryToken[4][4];
		font = (BitmapFont) Assets.getInstance().get("font_small");
		font.getData().setScale(1);
		memorySelected = (Texture) Assets.getInstance().get("memory_selected");
		memoryBack = (Texture) Assets.getInstance().get("memory_back");
		seenByOpponent = new ArrayList<Integer>();
		TextureRegion[][] tmp = new TextureRegion((Texture) Assets.getInstance().get("memory")).split(10, 10);
		tokens = new TextureRegion[8];
		for (int i = 0; i < 2; i++) {
			for (int ii = 0; ii < 4; ii++) {
				tokens[i + 2 * ii] = tmp[ii][i];
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int ii = 0; ii < 4; ii++) {
				field[ii][i] = new MemoryToken();
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int z = 0; z < 2; z++) {
				int x = MathUtils.random(0, 3);
				int y = MathUtils.random(0, 3);
				if (field[x][y].token == -1) {
					field[x][y].token = i;
				} else {
					int position = 0;
					while (field[position % 4][position / 4].token != -1) {
						position++;
					}
					field[position % 4][position / 4].token = i;
				}
			}
		}
	}

	private void getPreviousTile() {
		int position = currentTilex + currentTiley * 4;
		position--;
		if (position < 0) {
			position = 15;
		}
		while (field[position % 4][position / 4].draw == false||field[position % 4][position / 4].open == true) {
			position--;
			if (position < 0) {
				position = 15;
			}
		}
		currentTilex = position % 4;
		currentTiley = position / 4;
	}

	private void getNextTile() {
		int position = currentTilex + currentTiley * 4;
		position++;
		if (position > 15) {
			position = 0;
		}
		while (field[position % 4][position / 4].draw == false || field[position % 4][position / 4].open == true) {
			position++;
			if (position > 15) {
				position = 0;
			}
		}
		currentTilex = position % 4;
		currentTiley = position / 4;
	}

	@Override
	public void init() {
		super.init();
		world.player.processInput = false;
		world.player.render = false;
	}

	private void processSelect() {
		timeRunning = false;
		if (amountOpened == 0) {
			field[currentTilex][currentTiley].open = true;
			selectedTile1x = currentTilex;
			selectedTile1y = currentTiley;
			seenByOpponent.add(currentTilex + currentTiley * 4);
			amountOpened++;
		} else if (amountOpened == 1 && !(selectedTile1x == currentTilex && selectedTile1y == currentTiley)) {
			field[currentTilex][currentTiley].open = true;
			selectedTile2x = currentTilex;
			selectedTile2y = currentTiley;
			seenByOpponent.add(currentTilex + currentTiley * 4);
			amountOpened++;
			timeRunning = true;
		}
		if (seenByOpponent.size() > 15) {
			seenByOpponent.remove(0);
		}
	}

	@Override
	public void render(Batch batch) {
		float offsetx = 54;
		float offsety = 19;
		float sizex = 14;
		float sizey = 14;
		batch.draw(roomTexture, 0, 0);
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, "Spieler: " + playerScore, Color.BLACK, 100, Align.left, false);
		font.draw(batch, layout, 5, 45 + layout.height / 2f);
		layout.setText(font, "Sohn: " + opponentScore, Color.BLACK, 100, Align.left, false);
		font.draw(batch, layout, 120, 45 + layout.height / 2f);
		for (int i = 0; i < 4; i++) {
			for (int ii = 0; ii < 4; ii++) {
				font.setColor(Color.WHITE);
				layout.setText(font, String.valueOf(field[ii][3 - i].token));
				if (field[ii][3 - i].draw) {
					if (currentTilex == ii && currentTiley == 3 - i) {
						batch.draw(memorySelected, offsetx + sizex * ii - 1, offsety + sizey * i - 1);
					}
					if (field[ii][3 - i].open) {
						batch.draw(tokens[field[ii][3 - i].token], offsetx + sizex * ii, offsety + sizey * i);
					} else {
						batch.draw(memoryBack, offsetx + sizex * ii, offsety + sizey * i);
					}

				}
			}
		}

	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if (timeRunning&&playerScore+opponentScore!=8) {
			elapsedTime += delta;
		}
		if (!timeRunning && yourMove&&playerScore+opponentScore!=8) {
			if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
				getPreviousTile();
			} else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
				getNextTile();
			} else if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				processSelect();
			}
		}
		if (!yourMove && !timeRunning&&playerScore+opponentScore!=8) {
			letoppponentMove(delta);
		}
		if (amountOpened >= 2 && elapsedTime > 2&&playerScore+opponentScore!=8) {
			if (field[selectedTile1x][selectedTile1y].token == field[selectedTile2x][selectedTile2y].token) {
				field[selectedTile1x][selectedTile1y].draw = false;
				field[selectedTile2x][selectedTile2y].draw = false;
				remainingTiles--;
				if (yourMove) {
					playerScore++;
				} else {
					opponentScore++;
				}
				
				if(playerScore + opponentScore == 8)
				{
					addActionRoom(0, 0, -1, -1, new NextDayAction(world, new ChangeRoomAction(world, RoomEnum.HOME_ROOM,1,7,"Ihr spielt noch den ganzen Tag gemeinsam und geht spät abends Schlafen")));
				}
				else
				{
					getNextTile();
				}
			} else {
				field[selectedTile1x][selectedTile1y].open = false;
				field[selectedTile2x][selectedTile2y].open = false;
				yourMove = !yourMove;
			}
			timeRunning = false;
			elapsedTime = 0;
			amountOpened = 0;
			randomMovesOpponent = MathUtils.random(1, 6);
		}
	}

	public void letoppponentMove(float delta) {
		elapsedOpponentTime += delta;
		if (firstOpponent) {
			if (elapsedOpponentTime > 0.3) {
				if (randomMovesOpponent >= 0) {
					randomMovesOpponent--;
					getNextTile();
				
					elapsedOpponentTime = 0;
				} else {
					opponentTile = field[currentTilex][currentTiley].token;
					opponentLastPosition = currentTilex + currentTiley + 4;
					int position;
					moveToCorrect = false;
					for (position = 0; position < 16; position++) {
						if (field[position % 4][position / 4].token == opponentTile && position != opponentLastPosition)
							break;
					}
					if (seenByOpponent.contains(position))
						moveToCorrect = true;
					processSelect();
					firstOpponent = false;
					randomMovesOpponent = MathUtils.random(1, 6);
				}
			}
		} else {

			if (elapsedOpponentTime > 0.3) {
				if (!moveToCorrect && randomMovesOpponent >= 0) {
					randomMovesOpponent--;
					getPreviousTile();
					
					elapsedOpponentTime = 0;
				} else if (moveToCorrect && randomMovesOpponent >= 0) {
					getPreviousTile();
					elapsedOpponentTime = 0;
					if (field[currentTilex][currentTiley].token == opponentTile) {
						randomMovesOpponent = -1;
						moveToCorrect = false;
					}
				} else {
					processSelect();
					firstOpponent = true;
					timeRunning = true;
				}
			}
		}
	}

}
