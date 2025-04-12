package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import static utils.Constants.Directions.UP;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.HelpMethods.CanMoveHere;

import main.Game;

public abstract class Entity {
	
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitBox;
	protected int animTick, animIndex;
	protected int state;
	protected float airSpeed;
	protected boolean inAir = false;
	protected int maxHealth;
	protected int currentHealth;
	protected Rectangle2D.Float attackBox;
	protected float walkSpeed = 1.0f * Game.SCALE;
	
	protected int pushBackDir;
	protected float pushDrawOffset;
	protected int pushBackOffsetDir = UP;

	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	protected void updatePushBackDrawOffset() {
		float speed = 0.95f;
		float limit = -30f;

		if (pushBackOffsetDir == UP) {
			pushDrawOffset -= speed;
			if (pushDrawOffset <= limit)
				pushBackOffsetDir = DOWN;
		} else {
			pushDrawOffset += speed;
			if (pushDrawOffset >= 0)
				pushDrawOffset = 0;
		}
	}
	
	
	protected void pushBack(int pushBackDir, int[][] lvlData, float speedMulti) {
		float xSpeed = 0;
		if (pushBackDir == LEFT)
			xSpeed = -walkSpeed;
		else
			xSpeed = walkSpeed;

		if (CanMoveHere(hitBox.x + xSpeed * speedMulti, hitBox.y, hitBox.width, hitBox.height, lvlData))
			hitBox.x += xSpeed * speedMulti;
	}
	
	
	protected void drawAttackBox(Graphics g, int xLvlOffset) {
		g.setColor(Color.red);
		g.drawRect(((int)attackBox.x - xLvlOffset),(int) attackBox.y,(int) attackBox.width,(int) attackBox.height);
	}
	
	protected void drawHitbox(Graphics g, int xLvlOffset) {
		// for debugging hitbox
		g.setColor(Color.PINK);
		g.drawRect((int)hitBox.x - xLvlOffset, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
	}

	protected void initHitbox(int width, int height) {
		hitBox = new Rectangle2D.Float( x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
	}
	
//	protected void updateHitbox() {
//		hitBox.x = (int) x;
//		hitBox.y = (int) y;
//	}
	
	public Rectangle2D.Float getHitbox() {
		return hitBox;
	}
	
	public int getState() {
		return state;
	}
	
	public int getAniIndex() {
		return animIndex;
	}
	 
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	

	protected void newState(int state) {
		this.state = state;
		animTick = 0;
		animIndex = 0;
	}
}
