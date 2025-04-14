package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.BlackWolf;
import entities.BlueGolem;
import entities.BrownWolf;
import entities.DemonSlime;
import entities.Eyeball;
import entities.FrostGuardian;
import entities.Mimic;
import entities.OrangeGolem;
import entities.Skelly;
import entities.Slime;
import entities.Wolf;
import main.Game;
import objects.CaveSpike;
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
	private ArrayList<CaveSpike> cSpikes = new ArrayList<>();
	private ArrayList<GameContainer> containers  = new ArrayList<>();
	private ArrayList<BlueGolem> bGolems = new ArrayList<>();
	private ArrayList<OrangeGolem> oGolems = new ArrayList<>();
	private ArrayList<BrownWolf> bWolfies = new ArrayList<>();
	private ArrayList<Mimic> mimics = new ArrayList<>();
	private ArrayList<Eyeball> eyeballies = new ArrayList<>();
	private ArrayList<BlackWolf> blWolfies = new ArrayList<>();
	private ArrayList<DemonSlime> demonSlime = new ArrayList<>();
	private ArrayList<FrostGuardian> frostGuardian = new ArrayList<>();
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
		
		System.out.println("number of golems: " + bGolems.size());
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
		case BLUE_GOLEM -> bGolems.add(new BlueGolem(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case ORANGE_GOLEM -> oGolems.add(new OrangeGolem(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case BROWN_WOLF -> bWolfies.add(new BrownWolf(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case MIMIC -> mimics.add(new Mimic(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case EYEBALL -> eyeballies.add(new Eyeball(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case BLACK_WOLF -> blWolfies.add(new BlackWolf(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case DEMON_SLIME -> demonSlime.add(new DemonSlime(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case FROST_GUARDIAN -> frostGuardian.add(new FrostGuardian(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
		case 100 -> playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
		}
	}
	

	private void loadObjects(int blueValue, int x, int y) {
		switch (blueValue) {
		case RED_POTION, BLUE_POTION -> potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case JAR, BARREL -> containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
		case SPIKE -> spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
		case CAVE_SPIKE -> cSpikes.add(new CaveSpike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, CAVE_SPIKE));
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
	
	public ArrayList<BrownWolf> getBrownWolfies(){
		return bWolfies;
	}
	
	public ArrayList<BlueGolem> getBlueGolems(){
		return bGolems;
	}
	
	public ArrayList<OrangeGolem> getOrangeGolems(){
		return oGolems;
	}
	
	public ArrayList<Slime> getSlimes(){
		return slimes;
	}
	
	public ArrayList<Mimic> getMimics(){
		return mimics;
	}
	
	public ArrayList<Eyeball> getEyeballies(){
		return eyeballies;
	}
	
	public ArrayList<BlackWolf> getBlackWolfies(){
		return blWolfies;
	}
	
	public ArrayList<DemonSlime> getDemonSlime(){
		return demonSlime;
	}
	
	public ArrayList<FrostGuardian> getFrostGuardian(){
		return frostGuardian;
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
	
	public ArrayList<CaveSpike> getCSpikes(){
		return cSpikes;
	}
}
