package objects;

import main.Game;

public class CaveSpike extends GameObject{

	public CaveSpike(int x, int y, int objType) {
		super(x, y, objType);
		initHitbox(24, 24);
		xDrawOffset = (int) (Game.SCALE * 24);
		yDrawOffset = (int) (Game.SCALE * 24);		
	}

}
