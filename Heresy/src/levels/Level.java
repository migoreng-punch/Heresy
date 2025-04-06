package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Skelly;
import main.Game;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utils.HelpMethods;

import static utils.HelpMethods.GetLevelData;
import static utils.HelpMethods.GetPlayerSpawn;
import static utils.HelpMethods.getSkellies;


public class Level {
	
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Skelly> skellies;
	private ArrayList<Potion> potions;
	private ArrayList<Spike> spikes;
	private ArrayList<GameContainer> containers;
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;
	
	public Level(BufferedImage img) {
		this.img = img;
		creatLevelData();
		createEnemies();
		createPotions();
		createContainers();
		createSpikes();
		calcLvlOffsets();
		calcPlayerSpawn();
	}
	
	private void createSpikes() {
		spikes = HelpMethods.GetSpikes(img);
	}

	private void createContainers() {
		containers = HelpMethods.GetContainers(img);
	}

	private void createPotions() {
		potions = HelpMethods.GetPotions(img);
	}

	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
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
	
	public Point getPlayerSpawn() {
		return playerSpawn;
	}
	
	public ArrayList<Potion> getPotion(){
		return potions;
	}
	
	public ArrayList<GameContainer> getGameContainer(){
		return containers;
	}
	
	public ArrayList<Spike> getSpike(){
		return spikes;
	}
}
