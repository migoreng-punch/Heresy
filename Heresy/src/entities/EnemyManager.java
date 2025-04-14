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
	private BufferedImage[][] skellyArr, slimesArr, wolfyArr, blueGolemArr, orangeGolemArr, 
	brownWolfArr, mimicArr, eyeballArr, blackWolfArr, demonSlimeArr, frostGuardianArr;
//	private ArrayList<Skelly> skellies = new ArrayList<>();
	private Level currentLevel;
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}
	
	public void loadEnemies(Level level) {
		this.currentLevel = level;
//		skellies = level.getSkelly();
//		System.out.println("size of skellies: " + skellies.size());
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
		
		for(BlueGolem b : currentLevel.getBlueGolems()) {
			if(b.isActive()) {
				b.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(OrangeGolem o : currentLevel.getOrangeGolems()) {
			if(o.isActive()) {
				o.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(BrownWolf b : currentLevel.getBrownWolfies()) {
			if(b.isActive()) {
				b.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(Mimic m : currentLevel.getMimics()) {
			if(m.isActive()) {
				m.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(Eyeball e : currentLevel.getEyeballies()) {
			if(e.isActive()) {
				e.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(BlackWolf b : currentLevel.getBlackWolfies()) {
			if(b.isActive()) {
				b.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(DemonSlime d : currentLevel.getDemonSlime()) {
			if(d.isActive()) {
				d.update(lvlData, playing);
				isAnyActive = true;
			}
		}
		
		for(FrostGuardian f : currentLevel.getFrostGuardian()) {
			if(f.isActive()) {
				f.update(lvlData, playing);
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
		drawBlueGolems(g, xLvlOffset);
		drawOrangeGolems(g, xLvlOffset);
		drawBrownWolfies(g, xLvlOffset);
		drawMimics(g, xLvlOffset);
		drawEyeballies(g, xLvlOffset);
		drawBlackWolfies(g, xLvlOffset);
		drawDemonSlime(g, xLvlOffset);
		drawFrostGuardian(g, xLvlOffset);
	}

	private void drawFrostGuardian(Graphics g, int xLvlOffset) {
		for(FrostGuardian f : currentLevel.getFrostGuardian())	 
			if(f.isActive()){
			g.drawImage(frostGuardianArr[f.getState()][f.getAniIndex()], 
					(int) f.getHitbox().x - xLvlOffset - FROST_GUARDIAN_DRAWOFFSET_X + f.flipX() - 8, 
					(int) f.getHitbox().y - FROST_GUARDIAN_DRAWOFFSET_Y, 
					FROST_GUARDIAN_WIDTH * f.flipW(), 
					FROST_GUARDIAN_HEIGHT, null);
			f.drawHitbox(g, xLvlOffset);
			f.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawDemonSlime(Graphics g, int xLvlOffset) {
		for(DemonSlime d : currentLevel.getDemonSlime())	 
			if(d.isActive()){
			g.drawImage(demonSlimeArr[d.getState()][d.getAniIndex()], 
					(int) d.getHitbox().x - xLvlOffset - DEMON_SLIME_DRAWOFFSET_X + d.flipX() - 8, 
					(int) d.getHitbox().y - DEMON_SLIME_DRAWOFFSET_Y, 
					DEMON_SLIME_WIDTH * d.flipW(), 
					DEMON_SLIME_HEIGHT, null);
			d.drawHitbox(g, xLvlOffset);
			d.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawBlackWolfies(Graphics g, int xLvlOffset) {
		for(BlackWolf b : currentLevel.getBlackWolfies())	 
			if(b.isActive()){
			g.drawImage(blackWolfArr[b.getState()][b.getAniIndex()], 
					(int) b.getHitbox().x - xLvlOffset - BLACK_WOLF_DRAWOFFSET_X + b.flipX() - 8, 
					(int) b.getHitbox().y - BLACK_WOLF_DRAWOFFSET_Y, 
					BLACK_WOLF_WIDTH * b.flipW(), 
					BLACK_WOLF_HEIGHT, null);
			b.drawHitbox(g, xLvlOffset);
			b.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawEyeballies(Graphics g, int xLvlOffset) {
		for(Eyeball e : currentLevel.getEyeballies())	 
			if(e.isActive()){
			g.drawImage(eyeballArr[e.getState()][e.getAniIndex()], 
					(int) e.getHitbox().x - xLvlOffset - EYEBALL_DRAWOFFSET_X + e.flipX() - 8, 
					(int) e.getHitbox().y - EYEBALL_DRAWOFFSET_Y, 
					EYEBALL_WIDTH * e.flipW(), 
					EYEBALL_HEIGHT, null);
			e.drawHitbox(g, xLvlOffset);
			e.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawMimics(Graphics g, int xLvlOffset) {
		for(Mimic m : currentLevel.getMimics())	 
			if(m.isActive()){
			g.drawImage(mimicArr[m.getState()][m.getAniIndex()], 
					(int) m.getHitbox().x - xLvlOffset - MIMIC_DRAWOFFSET_X + m.flipX() - 8, 
					(int) m.getHitbox().y - MIMIC_DRAWOFFSET_Y, 
					MIMIC_WIDTH * m.flipW(), 
					MIMIC_HEIGHT, null);
			m.drawHitbox(g, xLvlOffset);
			m.drawAttackBox(g, xLvlOffset);
		}

	}

	private void drawBrownWolfies(Graphics g, int xLvlOffset) {
		for(BrownWolf b : currentLevel.getBrownWolfies())	 
			if(b.isActive()){
			g.drawImage(brownWolfArr[b.getState()][b.getAniIndex()], 
					(int) b.getHitbox().x - xLvlOffset - BROWN_WOLF_DRAWOFFSET_X + b.flipX() - 8, 
					(int) b.getHitbox().y - BROWN_WOLF_DRAWOFFSET_Y, 
					BROWN_WOLF_WIDTH * b.flipW(), 
					BROWN_WOLF_HEIGHT, null);
			b.drawHitbox(g, xLvlOffset);
			b.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawOrangeGolems(Graphics g, int xLvlOffset) {
		for(OrangeGolem o : currentLevel.getOrangeGolems())	 
			if(o.isActive()){
			g.drawImage(orangeGolemArr[o.getState()][o.getAniIndex()], 
					(int) o.getHitbox().x - xLvlOffset - ORANGE_GOLEM_DRAWOFFSET_X + o.flipX() - 8, 
					(int) o.getHitbox().y - ORANGE_GOLEM_DRAWOFFSET_Y, 
					ORANGE_GOLEM_WIDTH * o.flipW(), 
					ORANGE_GOLEM_HEIGHT, null);
			o.drawHitbox(g, xLvlOffset);
			o.drawAttackBox(g, xLvlOffset);
		}
	}

	private void drawBlueGolems(Graphics g, int xLvlOffset) {
		for(BlueGolem b: currentLevel.getBlueGolems())	 
			if(b.isActive()){
			g.drawImage(blueGolemArr[b.getState()][b.getAniIndex()], 
					(int) b.getHitbox().x - xLvlOffset - BLUE_GOLEM_DRAWOFFSET_X + b.flipX() - 8, 
					(int) b.getHitbox().y - BLUE_GOLEM_DRAWOFFSET_Y, 
					BLUE_GOLEM_WIDTH * b.flipW(), 
					BLUE_GOLEM_HEIGHT, null);
			b.drawHitbox(g, xLvlOffset);
			b.drawAttackBox(g, xLvlOffset);
		}
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
		
		for(BlueGolem b : currentLevel.getBlueGolems()) {
			if(b.isActive()) {
				if(b.getCurrentHealth() > 0) {
					if(attackBox.intersects(b.getHitbox())) {
						b.hurt(9);
					}
				}
			}
		}
		
		for(OrangeGolem o : currentLevel.getOrangeGolems()){
			if(o.isActive()) {
				if(o.getCurrentHealth() > 0) {
					if(attackBox.intersects(o.getHitbox())) {
						o.hurt(9);
					}
				}
			}
		}
		
		for(BrownWolf b : currentLevel.getBrownWolfies()) {
			if(b.isActive()) {
				if(b.getCurrentHealth() > 0) {
					if(attackBox.intersects(b.getHitbox())) {
						b.hurt(13);
					}
				}
			}
		}
		
		for(Mimic m : currentLevel.getMimics()) {
			if(m.isActive()) {
				if(m.getCurrentHealth() > 0) {
					if(attackBox.intersects(m.getHitbox())) {
						m.hurt(15);
					}
				}
			}
		}
		
		for(Eyeball e : currentLevel.getEyeballies()) {
			if(e.isActive()) {
				if(e.getCurrentHealth() > 0) {
					if(attackBox.intersects(e.getHitbox())) {
						e.hurt(15);
					}
				}
			}
		}
		
		for(BlackWolf b : currentLevel.getBlackWolfies()) {
			if(b.isActive()) {
				if(b.getCurrentHealth() > 0) {
					if(attackBox.intersects(b.getHitbox())) {
						b.hurt(13);
					}
				}
			}
		}
		
		for(DemonSlime d : currentLevel.getDemonSlime()) {
			if(d.isActive()) {
				if(d.getCurrentHealth() > 0) {
					if(attackBox.intersects(d.getHitbox())) {
						d.hurt(18);
					}
				}
			}
		}
		
		for(FrostGuardian f : currentLevel.getFrostGuardian()) {
			if(f.isActive()) {
				if(f.getCurrentHealth() > 0) {
					if(attackBox.intersects(f.getHitbox())) {
						f.hurt(18);
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
		blueGolemArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BLUE_GOLEM_SPRITE), 13, 5, BLUE_GOLEM_WIDTH_DEFAULT, BLUE_GOLEM_HEIGHT_DEFAULT);
		orangeGolemArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.ORANGE_GOLEM_SPRITE), 13, 5, ORANGE_GOLEM_WIDTH_DEFAULT, ORANGE_GOLEM_HEIGHT_DEFAULT);
		brownWolfArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BROWN_WOLF_SPRITE), 8, 5, BROWN_WOLF_WIDTH_DEFAULT, BROWN_WOLF_HEIGHT_DEFAULT);
		mimicArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.MIMIC_SPRITE), 4, 5, MIMIC_WIDTH_DEFAULT, MIMIC_HEIGHT_DEFAULT);
		eyeballArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.EYEBALL_SPRITE), 4, 5, EYEBALL_WIDTH_DEFAULT, EYEBALL_HEIGHT_DEFAULT);
		blackWolfArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.BLACK_WOLF_SPRITE), 8, 5, BLACK_WOLF_WIDTH_DEFAULT, BLACK_WOLF_HEIGHT_DEFAULT);
		demonSlimeArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.DEMON_SLIME_SPRITE), 22, 5, DEMON_SLIME_WIDTH_DEFAULT, DEMON_SLIME_HEIGHT_DEFAULT);
		frostGuardianArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.FROST_GUARDIAN_SPRITE), 16, 5, FROST_GUARDIAN_WIDTH_DEFAULT, FROST_GUARDIAN_HEIGHT_DEFAULT);
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
		
		for(BlueGolem b : currentLevel.getBlueGolems()) {
			b.resetEnemy();
		}
		
		for(OrangeGolem o : currentLevel.getOrangeGolems()) {
			o.resetEnemy();
		}
		
		for(BrownWolf b : currentLevel.getBrownWolfies()) {
			b.resetEnemy();
		}
		
		for(Mimic m : currentLevel.getMimics()) {
			m.resetEnemy();
		}
		
		for(Eyeball e : currentLevel.getEyeballies()) {
			e.resetEnemy();
		}
		
		for(BlackWolf b : currentLevel.getBlackWolfies()) {
			b.resetEnemy();
		}
		
		for(DemonSlime d : currentLevel.getDemonSlime()) {
			d.resetEnemy();
		}
	}
}
