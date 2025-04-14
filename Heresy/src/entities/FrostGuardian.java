package entities;

import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.EnemyConstants.ATTACK_1;
import static utils.Constants.EnemyConstants.FROST_GUARDIAN;
import static utils.Constants.EnemyConstants.FROST_GUARDIAN_HEIGHT;
import static utils.Constants.EnemyConstants.FROST_GUARDIAN_WIDTH;
import static utils.Constants.EnemyConstants.GetSpriteAmount;
import static utils.Constants.EnemyConstants.HIT;
import static utils.Constants.EnemyConstants.IDLE;
import static utils.Constants.EnemyConstants.RUNNING;
import static utils.HelpMethods.IsFloor;

import java.awt.geom.Rectangle2D;

import gamestates.Playing;
import main.Game;

public class FrostGuardian extends Enemy{
	
	private int attackBoxOffsetX;
	
	public FrostGuardian(float x, float y) {
		super(x, y - 80, FROST_GUARDIAN_WIDTH, FROST_GUARDIAN_HEIGHT, FROST_GUARDIAN);
		initHitbox(79, 85); //61 , 89 prev
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y,(int) (79 * Game.SCALE), (int) (85 * Game.SCALE));
		attackBoxOffsetX = (int)(Game.SCALE * 5);
	}
	
	public void update(int[][] lvlData, Playing playing) {
		updateBehavior(lvlData, playing);
		updateAnimationTick();
		updateAttackBox();
	}
	
	private void updateAttackBox() {
		if(walkDir == RIGHT) {
			attackBox.x = hitBox.x + hitBox.width + (attackBoxOffsetX - 50);
		}else if(walkDir == LEFT) {
			attackBox.x = hitBox.x - hitBox.width - attackBoxOffsetX - (-20);
		}
		attackBox.y = hitBox.y;
	}
	
	public void updateBehavior(int[][] lvlData, Playing playing) {
		if(firstUpdate) {
			firstUpdateCheck(lvlData);
		}
		if(inAir) {
			inAirChecks(lvlData, playing);
		}else {
			switch(state) {
				case IDLE:
					if (IsFloor(hitBox, lvlData))
						newState(RUNNING);
					else
						inAir = true;
					break;
				case RUNNING:
					if (canSeePlayer(lvlData, playing.getPlayer())) {
						turnTowardsPlayer(playing.getPlayer());
						if (isPlayerCloseForAttack(playing.getPlayer()))
							newState(ATTACK_1);
					}
					move(lvlData);
					break;
				case ATTACK_1:
					if(animIndex == 0) {
						attackChecked = false;
					}
					if(animIndex == 6 && !attackChecked) {
						checkEnemyHit(attackBox, playing.getPlayer());
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
			return width;
		}
		else {
			return 0;
		}
	}
	
	public int flipW() {
		 if(walkDir == RIGHT) {
			 return -1;
		 }
		 	else {
			 return 1;
		 }
	}
}
