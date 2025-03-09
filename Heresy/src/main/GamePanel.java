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

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int animTick, animIndex, animSpeed = 10;
	
	
	
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
		animations = new BufferedImage[16][7];
		
		for(int i = 0; i < animations.length; i++ ) {
			for(int j = 0; j < animations[i].length; j++) {
				animations[i][j] = img.getSubimage(j * 50, i * 37, 50, 37);
			}
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/adventurer_sprites.png");
		
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

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		
		g.drawImage(animations[1][animIndex], (int)xDelta, (int)yDelta, 128, 80, null);
	}

	private void updateAnimationTick() {
		animTick++;
		
		if(animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			
			if(animIndex >= 6) {
				animIndex = 0; 
			}
		}
	}

	
}
