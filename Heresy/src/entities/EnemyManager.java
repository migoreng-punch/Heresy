package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utils.LoadSave;
import static utils.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] skellyArr, slimesArr, wolfyArr;
	private ArrayList<Skelly> skellies = new ArrayList<>();
	private Level currentLevel;
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}
	
	public void loadEnemies(Level level) {
		this.currentLevel = level;
//		skellies = level.getSkelly();
		System.out.println("size of skellies: " + skellies.size());
	}

	public void update(int[][] lvlData) {
		boolean isAnyActive = false;
		for(Skelly s: currentLevel.getSkelly()) {
			if(s.isActive()) {
				s.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(Slime s: currentLevel.getSlimes()) {
			if(s.isActive()) {
				s.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(Wolf w : currentLevel.getWolfies()) {
			if(w.isActive()) {
				w.update(lvlData, playing);
				isAnyActive = true;
			}
		}

		if(!isAnyActive) {
			playing.setLevelCompleted(true);
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawSkellies(g, xLvlOffset);
		drawSlimes(g, xLvlOffset);
		drawWoflies(g, xLvlOffset);
	}

	private void drawWoflies(Graphics g, int xLvlOffset) {
		for(Wolf w: currentLevel.getWolfies())	 
			if(w.isActive()){
			g.drawImage(wolfyArr[w.getState()][w.getAniIndex()], 
					(int) w.getHitbox().x - xLvlOffset - WOLF_DRAWOFFSET_X + w.flipX() - 8, 
					(int) w.getHitbox().y - WOLF_DRAWOFFSET_Y, 
					WOLF_WIDTH * w.flipW(), 
					WOLF_HEIGHT, null);
			w.drawHitbox(g, xLvlOffset);
			w.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawSkellies(Graphics g, int xLvlOffset) {
		for(Skelly s: currentLevel.getSkelly())	 
			if(s.isActive()){
			g.drawImage(skellyArr[s.getState()][s.getAniIndex()], 
					(int) s.getHitbox().x - xLvlOffset - SKELLY_DRAWOFFSET_X + s.flipX() - 8, 
					(int) s.getHitbox().y - SKELLY_DRAWOFFSET_Y, 
					SKELLY_WIDTH * s.flipW(), 
					SKELLY_HEIGHT, null);
			s.drawHitbox(g, xLvlOffset);
			s.drawAttackBox(g, xLvlOffset);
		}
	}
	
	private void drawSlimes(Graphics g, int xLvlOffset) {
		for(Slime s: currentLevel.getSlimes())	 
			if(s.isActive()){
			g.drawImage(slimesArr[s.getState()][s.getAniIndex()], 
					(int) s.getHitbox().x - xLvlOffset - SLIME_DRAWOFFSET_X + s.flipX() - 8, 
					(int) s.getHitbox().y - SLIME_DRAWOFFSET_Y, 
					SLIME_WIDTH * s.flipW(), 
					SLIME_HEIGHT, null);
			s.drawHitbox(g, xLvlOffset);
			s.drawAttackBox(g, xLvlOffset);
		}
	}
	
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for(Skelly s: currentLevel.getSkelly()) 
			if(s.isActive()){
				if(s.getCurrentHealth() > 0)
					if(attackBox.intersects(s.getHitbox())) {
						s.hurt(10);
						return;
					}
			}
		
		for(Slime s : currentLevel.getSlimes()) {
			if(s.isActive()) {
				if(s.getCurrentHealth() > 0) {
					if(attackBox.intersects(s.getHitbox())) {
						s.hurt(15);
						return;
					}
				}
			}
		}
		
		for(Wolf w : currentLevel.getWolfies()) {
			if(w.isActive()) {
				if(w.getCurrentHealth() > 0) {
					if(attackBox.intersects(w.getHitbox())) {
						w.hurt(13);
						return;
					}
				}
			}
		}
	}

	private void loadEnemyImgs() {
//		skellyArr = new BufferedImage[5][13];
//		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SKELETON_SPRITE);
//		
//		for(int i = 0; i < skellyArr.length; i++) {
//			for(int j = 0; j < skellyArr[i].length; j++) {
//				skellyArr[i][j] = temp.getSubimage(j * SKELLY_WIDTH_DEFAULT, i * SKELLY_HEIGHT_DEFAULT, SKELLY_WIDTH_DEFAULT, SKELLY_HEIGHT_DEFAULT);
//			}
//		}
		skellyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SKELETON_SPRITE), 13, 5, SKELLY_WIDTH_DEFAULT, SKELLY_HEIGHT_DEFAULT);
		slimesArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SLIME_SPRITE), 5, 5, SLIME_WIDTH_DEFAULT, SLIME_HEIGHT_DEFAULT);
		wolfyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.WOLF_SPRITE), 8 , 5, WOLF_WIDTH_DEFAULT, WOLF_HEIGHT_DEFAULT);
	}
	
	private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
		BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
		for (int j = 0; j < tempArr.length; j++)
			for (int i = 0; i < tempArr[j].length; i++)
				tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
		return tempArr;
	}

	public void resetAllEnemies() {
		for(Skelly s : currentLevel.getSkelly()) {
			s.resetEnemy();
		}
		
		for(Slime s : currentLevel.getSlimes()) {
			s.resetEnemy();
		}
		
		for(Wolf w : currentLevel.getWolfies()) {
			w.resetEnemy();
		}
	}
}
