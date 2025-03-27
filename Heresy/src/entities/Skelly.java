package entities;

import static utils.Constants.EnemyConstants.*;

import main.Game;

public class Skelly extends Enemy{

	public Skelly(float x, float y) {
		super(x, y, SKELLY_WIDTH, SKELLY_HEIGHT, SKELLY);
		initHitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
	}

}
