package objects;
import static utils.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject {

	public GameContainer(int x, int y, int objType) {
		super(x, y, objType);
		createHitbox();
	}

	private void createHitbox() {
		if(objType == JAR) {
			initHitbox(15, 15);
			
			xDrawOffset = (int) (26 * Game.SCALE);
			yDrawOffset = (int) (49 * Game.SCALE);
//			hitBox.y += 25;
		}else {
			initHitbox(19 ,21);
			
			xDrawOffset = (int) (24 * Game.SCALE);
			yDrawOffset = (int) (43 * Game.SCALE);
//			hitBox.y += 17;
		}
		hitBox.y += yDrawOffset + (int) (Game.SCALE * -32);
		hitBox.x += xDrawOffset / 2;
	}
	
	
	public void update() {
		if(doAnimation) {
			updateAnimationTick();
		}
	}

}
