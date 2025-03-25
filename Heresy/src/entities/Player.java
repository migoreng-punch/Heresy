package entities;

import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.GetSpriteAmount;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.HelpMethods.CanMoveHere;

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
	private boolean left, up, right, down;
	private boolean moving = false, attacking = false ;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 16 * Game.SCALE;
	private float yDrawOffset = 9 * Game.SCALE;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 17 * Game.SCALE, 27 * Game.SCALE);
	}
	
	public void update() {
		updatePosition();
		updateAnimationTick();	
		setAnimation();
	}
	
	public void render(Graphics g) {		
		g.drawImage(animations[playerAction][animIndex],(int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset), width, height, null);
		drawHitbox(g);
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
		if(!left && !right && !up && !down) {
			return;
		}
		
		float xSpeed = 0, ySpeed = 0;
		
		if(left && !right) {
			xSpeed -= playerSpeed;
			
		}
		else if(right && !left){
			xSpeed += playerSpeed;
			
		}
		
		if(up && !down) {
			ySpeed -= playerSpeed;
			
		}
		else if(down && !up) {
			ySpeed += playerSpeed;
			
		}
		
//		if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
		
		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
			moving = true;
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

	public void resetDirBooleans() {
		// TODO Auto-generated method stub
		left = false;
		right = false;
		down = false;
		up = false;
	}
	
	
}
