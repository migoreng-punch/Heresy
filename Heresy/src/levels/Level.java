package levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Skelly;
import main.Game;
import static utils.HelpMethods.GetLevelData;
import static utils.HelpMethods.*;


public class Level {
	
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Skelly> skellies;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	
	public Level(BufferedImage img) {
		this.img = img;
		creatLevelData();
		createEnemies();
		calcLvlOffsets();
		
	}
	
	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

	private void createEnemies() {
		skellies = getSkellies(img);
	}

	private void creatLevelData() {
		lvlData = GetLevelData(img);
	}

	public int getSpriteindex(int x, int y) {
		return lvlData[y][x];
	}
	
	public int[][] getLevelData(){
		return lvlData;
	}
	
	public int getLvlOffset() {
		return maxLvlOffsetX;
	}
	
	public ArrayList<Skelly> getSkelly(){
		return skellies;
	}
}
