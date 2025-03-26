package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

public class Playing extends State implements StateMethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200 , 200, (int) (50 * Game.SCALE), (int) (37 * Game.SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!paused) {
			levelManager.update();
			player.update();
		}else {
			pauseOverlay.update();
		}	
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		levelManager.draw(g);
		player.render(g);
		
		if(paused) {
			pauseOverlay.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
//		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(true);
//			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;	
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
//		case KeyEvent.VK_S:
//			gamePanel.getGame().getPlayer().setDown(false);
//			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(paused) {
			pauseOverlay.mouseDragged(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			player.setAttack(true);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused) {
			pauseOverlay.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused) {
			pauseOverlay.mouseReleased(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(paused) {
			pauseOverlay.mouseMoved(e);
		}
	}
	
	public void unpauseGame() {
		paused = false;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		// TODO Auto-generated method stub
		player.resetDirBooleans();
	}

}
