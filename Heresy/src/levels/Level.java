package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Skelly;
import entities.Slime;
import entities.Wolf;
import main.Game;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utils.HelpMethods;

//import static utils.HelpMethods.GetLevelData;
//import static utils.HelpMethods.GetPlayerSpawn;
//import static utils.HelpMethods.getSkellies;
import static utils.Constants.EnemyConstants.*;
import static utils.Constants.ObjectConstants.*;



public class Level {
	
	private BufferedImage img;
	private int[][] lvlData;
	private ArrayList<Skelly> skellies = new ArrayList<>();
	private ArrayList<Wolf> wolfies = new ArrayList<>();
	private ArrayList<Slime> slimes = new ArrayList<>();
	private ArrayList<Potion> potions = new ArrayList<>();
	private ArrayList<Spike> spikes = new ArrayList<>();
	private ArrayList<GameContainer> containers  = new ArrayList<>();
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;
	
	public Level(BufferedImage img) {
		this.img = img;
//		creatLevelData();
//		createEnemies();
//		createPotions();
//		createContainers();
//		createSpikes();
//		calcPlayerSpawn();
		lvlData = new int[img.getHeight()][img.getWidth()];
		loadLevel();
		calcLvlOffsets();
	}
	

	private void loadLevel() {

		// Looping through the image colors just once. Instead of one per
		// object/enemy/etc..
		// Removed many methods in HelpMethods class.

		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();

				loadLevelData(red, x, y);
				loadEntities(green, x, y);
				loadObjects(blue, x, y);
			}
	}
	
	private void loadLevelData(int redValue, int x, int y) {
		if (redValue >= 247)
			lvlData[y][x] = 0;
		else
			lvlData[y][x] = redValue;
	}
	

	private void loadEntities(int greenValue, int x, int y) {
		switch (greenValue) {
		case SKELLY -> skellies.add(new Skelly(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case SLIME -> slimes.add(new Slime(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case WOLF -> wolfies.add(new Wolf(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case 100 -> playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
		}
	}
	

	private void loadObjects(int blueValue, int x, int y) {
		switch (blueValue) {
		case RED_POTION, BLUE_POTION -> potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case JAR, BARREL -> containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case SPIKE -> spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
		}
	}

//	private void createSpikes() {
//		spikes = HelpMethods.GetSpikes(img);
//	}
//
//	private void createContainers() {
//		containers = HelpMethods.GetContainers(img);
//	}
//
//	private void createPotions() {
//		potions = HelpMethods.GetPotions(img);
//	}
//
//	private void calcPlayerSpawn() {
//		playerSpawn = GetPlayerSpawn(img);
//	}

	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}

//	private void createEnemies() {
//		skellies = getSkellies(img);
//	}

//	private void creatLevelData() {
//		lvlData = GetLevelData(img);
//	}

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
	
	public ArrayList<Wolf> getWolfies(){
		return wolfies;
	}
	
	public ArrayList<Slime> getSlimes(){
		return slimes;
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
