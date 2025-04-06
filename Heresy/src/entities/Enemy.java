package entities;

import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.IsEntityOnFloor;
import static utils.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utils.Constants.Directions.*;
import static utils.HelpMethods.IsFloor;
import static utils.HelpMethods.IsSightClear;
import static utils.Constants.*;


import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import main.Game;

import static utils.HelpMethods.CanMoveHere;

public abstract class Enemy extends Entity {
	
	protected int enemyType;
	protected boolean firstUpdate = true;
	protected int walkDir = LEFT;
	protected int tileY;
	protected float attackDistance = Game.TILES_SIZE;
//	protected int maxHealth;
//	protected int currentHealth;
	protected boolean active = true;
	protected boolean attackChecked;
	
	public Enemy(float x, float y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		maxHealth = GetMaxHealth(enemyType);
		currentHealth = maxHealth;
		walkSpeed = Game.SCALE * 0.7f;
	}
	
	protected void firstUpdateCheck(int[][] lvlData) {
		if(!IsEntityOnFloor(hitBox, lvlData)) {
			inAir = true;
			
		}
		firstUpdate = false;	
	}
	
	protected void updateInAir(int[][] lvlData) {
		if(CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.y += airSpeed;
			airSpeed += GRAVITY;
		}else {
			inAir = false;
			hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
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
		this.state = enemyState;
		animTick = 0;
		animIndex = 0;
	}
	
	public void hurt(int amount) {
		currentHealth -= amount;
		if(currentHealth <= 0) {
			newState(DEAD);
		}else {
			newState(HIT);
		}
	}
	
	protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
		if(attackBox.intersects(player.hitBox)) {
			player.changeHealth(-GetEnemyDmg(enemyType));
		}
		attackChecked = true;
	}
	
	protected void updateAnimationTick() {
		animTick++;
		if(animTick >= ANI_SPEED) {
			animTick = 0;
			animIndex++;
			if(animIndex >= GetSpriteAmount(enemyType, state)) {
				animIndex = 0;
				
				switch(state) {
				case ATTACK_1, HIT -> state = IDLE;
				case DEAD -> active = false;				
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
	
	public void resetEnemy() {
		hitBox.x = x;
		hitBox.y = y;
		firstUpdate = true;
		currentHealth = maxHealth;
		newState(IDLE);
		active = true;
		airSpeed = 0;
	}


	
	public boolean isActive() {
		return active;
	}

}
