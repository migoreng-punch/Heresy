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
import static utils.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	
	private boolean left, up, right, down, jump;
	private boolean moving = false, attacking = false ;
	private int[][] lvlData;
	private float xDrawOffset = 16 * Game.SCALE;
	private float yDrawOffset = 9 * Game.SCALE;
	
	//Gravity / Jumping
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	
	
	//Status Bar UI
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int) (200 * Game.SCALE);
	private int statusBarHeight = (int) (76 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);
	
	private int healthBarWidth = (int) (109 * Game.SCALE);
	private int healthBarHeight = (int) (6 * Game.SCALE);
	private int healthBarXStart = (int) (67 * Game.SCALE);
	private int healthBarYStart = (int) (26 * Game.SCALE);
	
//	private int maxHealth = 100;
//	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	
	//AttackBox	
	private int flipX = 0;
	private int flipW = 1;
	
	private boolean attackChecked;
	private Playing playing;
	
	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		this.state = IDLE;
		this.maxHealth = 100;
		this.currentHealth = 35;
		this.walkSpeed = Game.SCALE * 1.0f;
		loadAnimations();
		initHitbox(17, 26); // Resize HitBox
		initAttackBox();
	}
	
	public void setSpawn(Point spawn) {
		this.x = spawn.x;
		this.y = spawn.y;
		hitBox.x = x;
		hitBox.y = y;
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (15 * Game.SCALE), (int) (27 * Game.SCALE) );
	}

	public void update() {
		updateHealthBar();
		if(currentHealth <= 0) {
			if(state != DIE) {
				state = DIE;
				animTick = 0;
				animIndex = 0;
				playing.setPlayerDying(true);
				playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
			}else if(animIndex == GetSpriteAmount(DIE) - 1 && animTick >= ANI_SPEED - 1 ) {
				playing.setGameOver(true);
				playing.getGame().getAudioPlayer().stopSong();
				playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
			}else {
				updateAnimationTick();
				
			}
//			playing.setGameOver(true);
			return;
		}
		updateAttackBox();
		updatePosition();
		if(moving) {
			checkPotionTouched();
			checkSpikesTouched();
		}
		if(attacking) {
			checkAttack();
		}
		updateAnimationTick();	
		setAnimation();
	}
	
	private void checkSpikesTouched() {
		playing.checkSpikesTouched(this);
	}

	private void checkPotionTouched() {
		playing.checkPotionTouched(hitBox);
	}

	private void checkAttack() {
		if(attackChecked || animIndex != 2) {
			return;
		}
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		playing.checkObjectHit(attackBox);
		playing.getGame().getAudioPlayer().playAttackSound();
	}

	private void updateAttackBox() {
		if(right) {
			attackBox.x = hitBox.x + hitBox.width + (int) (Game.SCALE * 6);
		}else if(left) {
			attackBox.x = hitBox.x - hitBox.width - (int) (Game.SCALE * 5);
		}
		attackBox.y = hitBox.y + (int) (Game.SCALE * 0); // Change Y offset of Attackbox
	}

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
	}

	public void render(Graphics g, int lvlOffset) {		
		g.drawImage(animations[state][animIndex],
				(int) (hitBox.x - xDrawOffset) - lvlOffset + flipX,
				(int) (hitBox.y - yDrawOffset),
				width * flipW, height, null);
		drawHitbox(g, lvlOffset);
		drawAttackBox(g, lvlOffset);
		drawUI(g);
	}
	

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
	}

	private void updateAnimationTick() {
		animTick++;
		
		if(animTick >= ANI_SPEED) {
			animTick = 0;
			animIndex++;
			
			if(animIndex >= GetSpriteAmount(state)) {
				animIndex = 0; 
				attacking = false;
				attackChecked = false;
			}
		}
	}
	
	private void setAnimation() {
		// TODO Auto-generated method stub
		int startAnim = state;
		
		
		if(moving) {
			state = RUNNING;
		}
		else {
			state = IDLE;
		}
		
		if(inAir) {
			if(airSpeed < 0) {
				state = JUMP;
			}else {
				state = GROUND;
			}
			
		}
		
		if(attacking) {
			state = ATTACK_1;
			if(startAnim != ATTACK_1) {
				animIndex = 1;
				animTick = 0;
				return;
			}
		}
		
		if(startAnim != state) {
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
			xSpeed -= walkSpeed;
			flipX = width;
			flipW = -1;
		}
		if(right){
			xSpeed += walkSpeed;
			flipX = 0;
			flipW = 1;
		}
		
		if(!inAir){
			if(!IsEntityOnFloor(hitBox, lvlData)) {
				inAir = true;
			}
		}
		
		if(inAir) {
			if(CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
				hitBox.y += airSpeed;
				airSpeed += GRAVITY;
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
		playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);
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
	
	public void changeHealth(int value) {
		currentHealth += value;
		if(currentHealth <= 0) {
			currentHealth = 0;
			//gameOver();
		}else if(currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	
	public void kill() {
		currentHealth = 0;
	}
	
	public void changePower(int power) {
		System.out.println("Added Power!");
	}

	private void loadAnimations() {	
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[13][7];
			
			for(int i = 0; i < animations.length; i++ ) {
				for(int j = 0; j < animations[i].length; j++) {
					animations[i][j] = img.getSubimage(j * 50, i * 37, 50, 37);
				}
			}
			
			statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
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

	public void resetAll() {
		// TODO Auto-generated method stub
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		state = IDLE;
		currentHealth = maxHealth;
		
		hitBox.x = x;
		hitBox.y = y;
		
		if(!IsEntityOnFloor(hitBox, lvlData)) {
			inAir = true;
		}
	}
}
