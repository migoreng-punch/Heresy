package entities;

import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.IsEntityOnFloor;
import static utils.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utils.Constants.Directions.*;
import static utils.HelpMethods.IsFloor;
import static utils.HelpMethods.IsSightClear;

import java.awt.geom.Rectangle2D.Float;

import main.Game;

import static utils.HelpMethods.CanMoveHere;

public abstract class Enemy extends Entity {
	
	protected int aniIndex, enemyState, enemyType;
	protected int aniTick, aniSpeed = 15;
	protected boolean firstUpdate = true, inAir = false;
	protected float fallSpeed, gravity = 0.04f * Game.SCALE;
	protected float walkSpeed = 1.0f * Game.SCALE;
	protected int walkDir = LEFT;
	protected int tileY;
	protected float attackDistance = Game.TILES_SIZE;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
	}
	
	protected void firstUpdateCheck(int[][] lvlData) {
		if(!IsEntityOnFloor(hitBox, lvlData)) {
			inAir = true;
			
		}
		firstUpdate = false;	
	}
	
	protected void updateInAir(int[][] lvlData) {
		if(CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.y += fallSpeed;
			fallSpeed += gravity;
		}else {
			inAir = false;
			hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
			tileY = (int) hitBox.y / Game.TILES_SIZE;
		}
	}
	
	protected void move(int[][] lvlData) {
		float xSpeed = 0;
		
		if(walkDir == LEFT) {
			xSpeed = -walkSpeed;
		}else {
			xSpeed = walkSpeed; 
		}
		
		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
			if(IsFloor(hitBox, xSpeed, lvlData)) {
				hitBox.x += xSpeed;
				return;
			}
		}
			
		changeWalkDir();
	}
	
	protected void turnTowardsPlayer(Player player) {
		if(player.hitBox.x > hitBox.x) {
			walkDir = RIGHT;
		}
		else {
			walkDir = LEFT;
		}
	}
	
	protected boolean canSeePlayer(int[][] lvlData, Player player) {
		int playerTileY = (int) player.getHitbox().y / Game.TILES_SIZE;
		
		if(playerTileY == tileY) {
			if(isPlayerInRange(player)){
				if(IsSightClear(lvlData, hitBox, player.hitBox, tileY)) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected boolean isPlayerInRange(Player player) {
		int absValue =  (int) Math.abs(player.hitBox.x - hitBox.x);
		return absValue <= attackDistance * 5;
	}
	
	protected boolean isPlayerCloseForAttack(Player player) {
		int absValue =  (int) Math.abs(player.hitBox.x - hitBox.x);
		return absValue <= attackDistance;
	}

	protected void newState(int enemyState) {
		this.enemyState = enemyState;
		aniTick = 0;
		aniIndex = 0;
	}
	
	protected void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
				aniIndex = 0;
				if(enemyState == ATTACK_1) {
					enemyState = IDLE;
				}
			}
		}
	}
		
	protected void changeWalkDir() {
		if(walkDir == LEFT) {
			walkDir = RIGHT;
		}else {
			walkDir = LEFT;
		}
	}

	public int getAniIndex() {
		return aniIndex;
	}
	
	public int getEnemyState() {
		return enemyState;
	}

}
