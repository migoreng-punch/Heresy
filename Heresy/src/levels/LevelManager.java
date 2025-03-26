package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelTemp;
	
	public LevelManager(Game game) {
		this.game = game;
//		levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSprites();
		levelTemp = new Level(LoadSave.GetLevelData());
	}
	
	private void importOutsideSprites() {
		// TODO Auto-generated method stub
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 12; j++) {
				int index = i * 12 + j; 
				levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
			}
		}
	}
	
	
//	private void importOutsideSprites() {
//		// TODO Auto-generated method stub
//		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
//		levelSprite = new BufferedImage[315];
//		for(int i = 0; i < 15; i++) {
//			for(int j = 0; j < 21; j++) {
//				int index = i * 21 + j; 
//				levelSprite[index] = img.getSubimage(j * 24, i * 24, 24, 24);
//			}
//		}
//	}

	public void draw(Graphics g, int lvlOffset) {
		for(int i = 0; i < game.TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < levelTemp.getLevelData()[0].length; j++) {
				int index = levelTemp.getSpriteindex(j, i);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * j - lvlOffset, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);	
			}
		}
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelTemp;
	}
}
