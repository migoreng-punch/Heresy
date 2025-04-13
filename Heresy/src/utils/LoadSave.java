package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Skelly;
import static utils.Constants.EnemyConstants.SKELLY;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "adventurer_sprites_v1.png";
	public static final String LEVEL_ATLAS = "heresy_tilesheet.png";
//	public static final String LEVEL_TEMP_DATA = "level_temp_v1.png";
	public static final String MENU_BUTTONS = "menu_buttons_heresy.png";
	public static final String MENU_BACKGROUND = "menu_backdrop_heresy.png";
	public static final String PAUSE_BACKGROUND = "menu_anotha_one.png";
	public static final String SOUND_BUTTONS = "new_sound_btns.png";
	public static final String URM_BUTTONS = "new_urm_btns.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "background_Town1.png";
	
	public static final String OAKWOOD_BACKGROUND_IMG = "oakwood_background.png";
	public static final String CAVES_BACKGROUND_IMG = "caves_background.png";
	
	public static final String SKELETON_SPRITE = "Skeleton_warrior.png";
	public static final String SLIME_SPRITE = "slime_spritesheet.png";
	public static final String WOLF_SPRITE = "grey_wolf_spritesheet.png";
	
	public static final String STATUS_BAR = "Player_healthbar.png";
	public static final String COMPLETED_IMG = "menu_level_complete.png";
	
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "destructibles_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String DEATH_SCREEN = "death_screen_heresy.png";
	public static final String OPTIONS_MENU = "options_background_heresy.png";

	
	public static BufferedImage GetSpriteAtlas(String fileName) { 
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		
		try {
			img = ImageIO.read(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;
		
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];
		
		for(int i = 0; i < filesSorted.length; i++) {
			for(int j = 0; j < filesSorted.length; j++) {
				if(files[j].getName().equals((i + 1) + ".png")) {
					filesSorted[i] = files[j];
				}
			}
		}
		
		BufferedImage[] imgs = new BufferedImage[filesSorted.length];
		
		for(int i = 0; i < imgs.length; i++) {
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return imgs;
	}
	
//	public static int[][] GetLevelData(){
//		BufferedImage img = GetSpriteAtlas(LEVEL_TEMP_DATA);
//		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
//		
//		for(int i = 0; i < img.getHeight(); i++) {
//			for(int j = 0; j < img.getWidth(); j++) {
//				Color color = new Color(img.getRGB(j, i));
//				int value = color.getRed(); 
//				if(value >= 315) {
//					value = 0;
//				}
//				lvlData[i][j] = value;
//			}
//		}

}
