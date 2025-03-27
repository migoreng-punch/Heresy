package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utils.LoadSave;
import static utils.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] skellyArr;
	private ArrayList<Skelly> skellies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		skellies = LoadSave.getSkellies();
		System.out.println("size of skellies: " + skellies.size());
	}

	public void update(int[][] lvlData) {
		for(Skelly s: skellies) {
			s.update(lvlData);
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawSkellies(g, xLvlOffset);
	}

	private void drawSkellies(Graphics g, int xLvlOffset) {
		for(Skelly s: skellies) {
			g.drawImage(skellyArr[s.getEnemyState()][s.getAniIndex()], (int) s.getHitbox().x - xLvlOffset - SKELLY_DRAWOFFSET_X, (int) s.getHitbox().y - SKELLY_DRAWOFFSET_Y, SKELLY_WIDTH, SKELLY_HEIGHT, null);
			s.drawHitbox(g, xLvlOffset);
		}
	}

	private void loadEnemyImgs() {
		skellyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SKELETON_SPRITE);
		
		for(int i = 0; i < skellyArr.length; i++) {
			for(int j = 0; j < skellyArr[i].length; j++) {
				skellyArr[i][j] = temp.getSubimage(j * SKELLY_WIDTH_DEFAULT, i * SKELLY_HEIGHT_DEFAULT, SKELLY_WIDTH_DEFAULT, SKELLY_HEIGHT_DEFAULT);
			}
		}
	}
}
