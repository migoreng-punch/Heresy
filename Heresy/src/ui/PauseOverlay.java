package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;
import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.UrmButtons.*;
import static utils.Constants.UI.VolumeButtons.*;

public class PauseOverlay {
	
	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private AudioOptions audioOptions;
	private UrmButton menuB, replayB, unpauseB;
	private VolumeButton volumeButton;
	
	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createUrmButtons();
		audioOptions = playing.getGame().getAudioOptions();
		createVolumeButton();
	}
	
	
	private void createVolumeButton() {
		int vX = (int) (309 * Game.SCALE);
		int vY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void createUrmButtons() {
		int menuX = (int) (318 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);
		
		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
	}





	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(backgroundImg.getWidth() * Game.SCALE);
		bgH = (int)(backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);
	}

	public void update() {

		menuB.update();
		replayB.update();
		unpauseB.update();
		volumeButton.update();
		audioOptions.update();
	}
	
	public void draw(Graphics g) {
		//Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		//Sound Buttons

		
		//Urm Buttons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
		//Volume Button
//		volumeButton.draw(g);
		
		audioOptions.draw(g);
	}
	
	public void mousePressed(MouseEvent e) {
		if(isIn(e, menuB)) {
			menuB.setMousePressed(true);
		}else if(isIn(e, replayB)) {
			replayB.setMousePressed(true);
		}else if(isIn(e, unpauseB)) {
			unpauseB.setMousePressed(true);
		}else if(isIn(e, volumeButton)) {
			volumeButton.setMousePressed(true);
		}else {
			audioOptions.mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(isIn(e, menuB)) {
			if(menuB.isMousePressed()) {
				playing.setGameState(Gamestate.MENU);
				playing.unpauseGame();
			}
		}else if(isIn(e, replayB)) {
			if(replayB.isMousePressed()) {
				playing.resetAll();
				playing.unpauseGame();
			}
		}else if(isIn(e, unpauseB)) {
			if(unpauseB.isMousePressed()) {
				playing.unpauseGame();
			}
		}else {
			audioOptions.mouseReleased(e);
		}
		

		menuB.resetBools();
		unpauseB.resetBools();
		replayB.resetBools();
		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		replayB.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if(isIn(e, menuB)) {
			menuB.setMouseOver(true);
		}else if(isIn(e, replayB)) {
			replayB.setMouseOver(true);
		}else if(isIn(e, unpauseB)) {
			unpauseB.setMouseOver(true);
		}else if(isIn(e, volumeButton)) {
			volumeButton.setMouseOver(true);
		}else {
			audioOptions.mouseMoved(e);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
