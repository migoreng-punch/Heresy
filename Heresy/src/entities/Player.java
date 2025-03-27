	package entities;

import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.GetSpriteAmount;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int animTick, animIndex, animSpeed = 10;
	private int playerAction = RUNNING;
	private boolean left, up, right, down, jump;
	private boolean moving = false, attacking = false ;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 16 * Game.SCALE;
	private float yDrawOffset = 9 * Game.SCALE;
	
	//Gravity / Jumping
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, (int) (17 * Game.SCALE), (int) (26 * Game.SCALE));
	}
	
	public void update() {
		updatePosition();
		updateAnimationTick();	
		setAnimation();
	}
	
	public void render(Graphics g, int lvlOffset) {		
		g.drawImage(animations[playerAction][animIndex],(int) (hitBox.x - xDrawOffset) - lvlOffset, (int) (hitBox.y - yDrawOffset), width, height, null);
		drawHitbox(g, lvlOffset);
	}
	
	private void updateAnimationTick() {
		animTick++;
		
		if(animTick >= animSpeed) {
			animTick = 0;
			animIndex++;
			
			if(animIndex >= GetSpriteAmount(playerAction)) {
				animIndex = 0; 
				attacking = false;
			}
		}
	}
	
	private void setAnimation() {
		// TODO Auto-generated method stub
		int startAnim = playerAction;
		
		
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		
		if(inAir) {
			if(airSpeed < 0) {
				playerAction = JUMP;
			}else {
				playerAction = GROUND;
			}
			
		}
		
		if(attacking) {
			playerAction = ATTACK_1;
		}
		
		if(startAnim != playerAction) {
			resetAniTick();
		}
	}
	
	private void resetAniTick() {
		// TODO Auto-generated method stub
		animTick = 0;
		animIndex = 0;
	}

	private void updatePosition() {
		// TODO Auto-generated method stub
		moving = false;
		
		if(jump) {
			jump();
		}
		
//		if(!left && !right && !inAir) {
//			return;
//		}
		
		if(!inAir) {
			if((!left && !right) || (right && left)) {
				return;
			}
		}
		
		float xSpeed = 0;
		
		if(left) {
			xSpeed -= playerSpeed;
			
		}
		if(right){
			xSpeed += playerSpeed;
			
		}
		
		if(!inAir){
			if(!IsEntityOnFloor(hitBox, lvlData)) {
				inAir = true;
			}
		}
		
		if(inAir) {
			if(CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			}else {
				hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
				if(airSpeed > 0) {
					resetInAir();
				}else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}
		}
		else {
			updateXPos(xSpeed);
		}
		moving = true;
	}
	
	private void jump() {
		// TODO Auto-generated method stub
		if(inAir){
			return;
		}
		
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInAir() {
		// TODO Auto-generated method stub
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {
		// TODO Auto-generated method stub
		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
		}
		else {
			hitBox.x = GetEntityXPosNextToWall(hitBox, xSpeed);
		}
	}

	private void loadAnimations() {	
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[13][7];
			
			for(int i = 0; i < animations.length; i++ ) {
				for(int j = 0; j < animations[i].length; j++) {
					animations[i][j] = img.getSubimage(j * 50, i * 37, 50, 37);
				}
			}
	}
	
	public void loadLevelData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitBox, lvlData)) {
			inAir = true;
		}
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void resetDirBooleans() {
		// TODO Auto-generated method stub
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	
}
