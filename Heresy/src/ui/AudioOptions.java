package ui;

import static utils.Constants.UI.PauseButtons.SOUND_SIZE;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import gamestates.Gamestate;
import main.Game;

public class AudioOptions {
	
	private SoundButton musicButton, sfxButton;
	
	private Game game;
	
	public AudioOptions(Game game) {
		this.game = game;
		createSoundButtons();
	}
	
	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE);
		int sfxY = (int) (186 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
	}
	
	public void draw(Graphics g) {
		musicButton.draw(g);
		sfxButton.draw(g);
	}
	
	public void mousePressed(MouseEvent e) {
		if(isIn(e, musicButton)) {
			musicButton.setMousePressed(true);
		}else if(isIn(e, sfxButton)) {
			sfxButton.setMousePressed(true);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(isIn(e, musicButton)) {
			if(musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
				game.getAudioPlayer().toggleSongMute();
			}
		}else if(isIn(e, sfxButton)) {
			if(sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
				game.getAudioPlayer().toggleEffectsMute();
			}
		}
		
		
		musicButton.resetBools();
		sfxButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);

		
		if(isIn(e, musicButton)) {
			musicButton.setMouseOver(true);
		}else if(isIn(e, sfxButton)) {
			sfxButton.setMouseOver(true);
		}
	
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
