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
		levelSprite = new BufferedImage[315];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 21; j++) {
				int index = i * 21 + j; 
				levelSprite[index] = img.getSubimage(j * 24, i * 24, 24, 24);
			}
		}
	}

	public void draw(Graphics g) {
		for(int i = 0; i < game.TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < game.TILES_IN_WIDTH; j++) {
				int index = levelTemp.getSpriteindex(j, i);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * j, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);	
			}
		}
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelTemp;
	}
}
