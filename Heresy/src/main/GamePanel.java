package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int animTick, animIndex, animSpeed = 10;
	private int playerAction = RUNNING;
	private int playerDir = -1;
	private boolean moving = false;
	
	
	
	public GamePanel(){
		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();
		addKeyListener(new KeyboardInputs(this));
		setPanelSize();
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[13][7];
		
		for(int i = 0; i < animations.length; i++ ) {
			for(int j = 0; j < animations[i].length; j++) {
				animations[i][j] = img.getSubimage(j * 50, i * 37, 50, 37);
			}
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/adventurer_sprites_v1.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 760);
		setPreferredSize(size);
	}
	
	public void setDirection	(int direction) {
		this.playerDir = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void setAnimation() {
		// TODO Auto-generated method stub
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		
	}
	
	private void updatePosition() {
		// TODO Auto-generated method stub
		if(moving) {
			switch(playerDir) {
				case LEFT:
					xDelta -= 5;
					break;
				case UP:
					yDelta -= 5;
					break;
				case RIGHT:
					xDelta += 5;
					break;
				case DOWN:
					yDelta += 5;
					break;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		
		setAnimation();
		updatePosition();
		
		g.drawImage(animations[playerAction][animIndex], (int)xDelta, (int)yDelta, 128, 80, null);
	}

	private void updateAnimationTick() {
		animTick++;
		
		if(animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			
			if(animIndex >= GetSpriteAmount(playerAction)) {
				animIndex = 0; 
			}
		}
	}

	
}
