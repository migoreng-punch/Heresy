package entities;

import static utils.Constants.Directions.LEFT;
import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.CanMoveHere;
import static utils.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utils.HelpMethods.IsEntityOnFloor;
import static utils.HelpMethods.IsFloor;
import static utils.Constants.EnemyConstants.GetSpriteAmount;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utils.Constants.Directions.*;


import main.Game;

public class Skelly extends Enemy{
	
	//AttackBox
	private int attackBoxOffsetX;
	
	public Skelly(float x, float y) {
		super(x, y, SKELLY_WIDTH, SKELLY_HEIGHT, SKELLY);
		initHitbox(12 ,29 ); //Resize Hitbox
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y,(int) (10 * Game.SCALE), (int) (29 * Game.SCALE));
		attackBoxOffsetX = (int)(Game.SCALE * 5);
	}

	public void update(int[][] lvlData, Player player) {
		updateBehavior(lvlData, player);
		updateAnimationTick();
		updateAttackBox();
	}
	
	private void updateAttackBox() {
		if(walkDir == RIGHT) {
			attackBox.x = hitBox.x + hitBox.width + attackBoxOffsetX;
		}else if(walkDir == LEFT) {
			attackBox.x = hitBox.x - hitBox.width - (attackBoxOffsetX - 1);
		}
		attackBox.y = hitBox.y;
	}

	public void updateBehavior(int[][] lvlData, Player player) {
		if(firstUpdate) {
			firstUpdateCheck(lvlData);
		}
		if(inAir) {
			updateInAir(lvlData);
		}else {
			switch(state) {
				case IDLE:
					newState(RUNNING);
					break;
				case RUNNING:
					if(canSeePlayer(lvlData, player)) {
						turnTowardsPlayer(player);
					if(isPlayerCloseForAttack(player)) 
						newState(ATTACK_1);
					}
					move(lvlData);
					break;
				case ATTACK_1:
					if(animIndex == 0) {
						attackChecked = false;
					}
					if(animIndex == 7 && !attackChecked) {
						checkEnemyHit(attackBox, player);
					}
					break;
				case HIT:
					if(animIndex <= GetSpriteAmount(enemyType, state) - 2)
						pushBack(pushBackDir, lvlData, 2f);
					updatePushBackDrawOffset();
					break;	
			}
		}
	}
		
	
	public int flipX() {
		if(walkDir == RIGHT) {
			return 0;
		}
		else {
			return width;
		}
	}
	
	public int flipW() {
		 if(walkDir == RIGHT) {
			 return 1;
		 }
		 	else {
			 return -1;
		 }
	}

}
