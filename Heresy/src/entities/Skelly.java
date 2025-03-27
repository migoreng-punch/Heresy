package entities;

import static utils.Constants.Directions.LEFT;
import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.CanMoveHere;
import static utils.HelpMethods.GetEntityYPosUnderRoofOrAboveFloor;
import static utils.HelpMethods.IsEntityOnFloor;
import static utils.HelpMethods.IsFloor;


import main.Game;

public class Skelly extends Enemy{

	public Skelly(float x, float y) {
		super(x, y, SKELLY_WIDTH, SKELLY_HEIGHT, SKELLY);
		initHitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
	}
	
	public void update(int[][] lvlData, Player player) {
		updateMove(lvlData, player);
		updateAnimationTick();
	}
	
	public void updateMove(int[][] lvlData, Player player) {
		if(firstUpdate) {
			firstUpdateCheck(lvlData);
		}
		if(inAir) {
			updateInAir(lvlData);
		}else {
			switch(enemyState) {
				case IDLE:
					newState(RUNNING);
					break;
				case RUNNING:
					if(canSeePlayer(lvlData, player)) {
						turnTowardsPlayer(player);
					}
					if(isPlayerCloseForAttack(player)) {
						newState(ATTACK_1);
					}
					move(lvlData);
					break;
			}
		}
	}

}
