package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Skelly;
import static utils.Constants.EnemyConstants.SKELLY;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "adventurer_sprites_v1.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
//	public static final String LEVEL_TEMP_DATA = "level_temp_v1.png";
	public static final String LEVEL_TEMP_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "Sound_Button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "background_Town1.png";
	public static final String OAKWOOD_BACKGROUND_IMG = "oakwood_background.png";
	public static final String SKELETON_SPRITE = "crabby_sprite.png";
	public static final String STATUS_BAR = "Player_healthbar.png";
	
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
	
	public static ArrayList<Skelly> getSkellies(){
		BufferedImage img = GetSpriteAtlas(LEVEL_TEMP_DATA);
		ArrayList<Skelly> list = new ArrayList<>();
		
		for(int i = 0; i < img.getHeight(); i++) {
			for(int j = 0; j < img.getWidth(); j++) {
				Color color = new Color(img.getRGB(j, i));
				int value = color.getGreen(); 
				if(value == SKELLY) {
					list.add(new Skelly(j * Game.TILES_SIZE, i * Game.TILES_SIZE));
					float pos1 = j * Game.TILES_SIZE;
					float pos2 = i * Game.TILES_SIZE;
					System.out.println("Position" + " " + pos1 + " " + pos2);
				}
			}		
		}
		return list;
	}
	
	public static int[][] GetLevelData(){
		BufferedImage img = GetSpriteAtlas(LEVEL_TEMP_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		
		for(int i = 0; i < img.getHeight(); i++) {
			for(int j = 0; j < img.getWidth(); j++) {
				Color color = new Color(img.getRGB(j, i));
				int value = color.getRed(); 
				if(value >= 48) {
					value = 0;
				}
				lvlData[i][j] = value;
			}
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
		
		return lvlData;
	}
}
