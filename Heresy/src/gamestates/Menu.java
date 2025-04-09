package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utils.LoadSave;

public class Menu extends State implements StateMethods {
	
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg, menuBackgroundImg;
	private int menuX, menuY, menuWidth, menuHeight;

	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();
		menuBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
	}

	private void loadBackground() {
		// TODO Auto-generated method stub
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = (int)(backgroundImg.getWidth() * Game.SCALE); 
		menuHeight = (int)(backgroundImg.getHeight() * Game.SCALE);
		menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
		menuY = (int) (45 * Game.SCALE);
	}

	private void loadButtons() {
		// TODO Auto-generated method stub
		buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
		buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			mb.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(menuBackgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
		for(MenuButton mb : buttons) {
			mb.draw(g);
		}
		
		
//		g.setColor(Color.black);
//		g.drawString("MENU", Game.GAME_WIDTH / 2, 200);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			if(isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			if(isIn(e, mb)) {
				if(mb.isMousePressed()) {
					mb.applyGamestate();
				}
				if(mb.getState() == Gamestate.PLAYING) {
					game.getAudioPlayer().setChapterSong(game.getPlaying().getLevelManager().getLevelIndex());
				}
				break;
			}
		}
		resetButtons();
	}

	private void resetButtons() {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			mb.resetBools();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MenuButton mb : buttons) {
			mb.setMouseOver(false);
		}
		
		for(MenuButton mb : buttons) {
			if(isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Gamestate.state = Gamestate.PLAYING;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
