package utils;

import main.Game;

public class Constants {
	
	public static final float GRAVITY = 0.04f * Game.SCALE;
	public static final int ANI_SPEED = 12;
	
	public static class ObjectConstants{
		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int JAR = 3;
		public static final int SPIKE = 4;
		public static final int CAVE_SPIKE = 5;

		
		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 10;
		
		public static final int CONTAINER_WIDTH_DEFAULT = 64;
		public static final int CONTAINER_HEIGHT_DEFAULT = 64;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);
		
		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int	POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);
		
		public static final int SPIKE_WIDTH_DEFAULT = 32;
		public static final int	SPIKE_HEIGHT_DEFAULT = 32;
		public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
		public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);
		
		public static final int CAVE_SPIKE_WIDTH_DEFAULT = 24;
		public static final int CAVE_SPIKE_HEIGHT_DEFAULT = 24;
		public static final int CAVE_SPIKE_WIDTH = (int) (Game.SCALE * CAVE_SPIKE_WIDTH_DEFAULT);
		public static final int CAVE_SPIKE_HEIGHT = (int) (Game.SCALE * CAVE_SPIKE_HEIGHT_DEFAULT);
		 
		public static int GetSpriteAmount(int object_type) {
			switch(object_type) {
			case RED_POTION, BLUE_POTION, BARREL, JAR:
				return 7;
			}
			return 1;
		}
	}
	
	
	public static class EnemyConstants{
		public static final int SKELLY = 0;
		public static final int SLIME = 1;
		public static final int WOLF = 2;
		public static final int BLUE_GOLEM = 3;
		public static final int ORANGE_GOLEM = 4;
		public static final int BROWN_WOLF = 5;
		public static final int MIMIC = 6;
		public static final int EYEBALL = 7;
		public static final int BLACK_WOLF = 8;	
		public static final int DEMON_SLIME = 9;
		public static final int FROST_GUARDIAN = 10;
		
		// 288 x 160 Demon Boss;
		
		
		public static final int IDLE = 0;
		public static final int RUNNING = 2;
		public static final int ATTACK_1 = 1;
		public static final int HIT = 3;
		public static final int DEAD = 4;
		
		public static final int SKELLY_WIDTH_DEFAULT = 48;
		public static final int SKELLY_HEIGHT_DEFAULT = 48;
		
		public static final int SKELLY_WIDTH = (int) (SKELLY_WIDTH_DEFAULT * Game.SCALE);
		public static final int SKELLY_HEIGHT = (int) (SKELLY_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int SKELLY_DRAWOFFSET_X = (int) (13 * Game.SCALE);
		public static final int SKELLY_DRAWOFFSET_Y = (int) (14 * Game.SCALE);
		
		public static final int SLIME_WIDTH_DEFAULT = 32;
		public static final int SLIME_HEIGHT_DEFAULT = 25;
		
		public static final int SLIME_WIDTH = (int) (SLIME_WIDTH_DEFAULT * Game.SCALE);
		public static final int SLIME_HEIGHT = (int) (SLIME_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int SLIME_DRAWOFFSET_X = (int) (2 * Game.SCALE);
		public static final int SLIME_DRAWOFFSET_Y = (int) (10 * Game.SCALE);
		
		public static final int WOLF_WIDTH_DEFAULT = 48;
		public static final int WOLF_HEIGHT_DEFAULT = 32;
		
		public static final int WOLF_WIDTH = (int) (WOLF_WIDTH_DEFAULT * Game.SCALE);
		public static final int WOLF_HEIGHT = (int) (WOLF_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int WOLF_DRAWOFFSET_X = (int) (4 * Game.SCALE);
		public static final int WOLF_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		
		public static final int BLUE_GOLEM_WIDTH_DEFAULT = 90;
		public static final int BLUE_GOLEM_HEIGHT_DEFAULT = 64;
		
		public static final int BLUE_GOLEM_WIDTH = (int) (BLUE_GOLEM_WIDTH_DEFAULT * Game.SCALE);
		public static final int BLUE_GOLEM_HEIGHT = (int) (BLUE_GOLEM_HEIGHT_DEFAULT * Game.SCALE);
		 
		public static final int BLUE_GOLEM_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int BLUE_GOLEM_DRAWOFFSET_Y = (int) (34 * Game.SCALE);
		
		public static final int ORANGE_GOLEM_WIDTH_DEFAULT = 90;
		public static final int ORANGE_GOLEM_HEIGHT_DEFAULT = 64;
		
		public static final int ORANGE_GOLEM_WIDTH = (int) (ORANGE_GOLEM_WIDTH_DEFAULT * Game.SCALE);
		public static final int ORANGE_GOLEM_HEIGHT = (int) (ORANGE_GOLEM_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int ORANGE_GOLEM_DRAWOFFSET_X = (int) (26 * Game.SCALE);
		public static final int ORANGE_GOLEM_DRAWOFFSET_Y = (int) (34 * Game.SCALE);
		
		public static final int BROWN_WOLF_WIDTH_DEFAULT = 48;
		public static final int BROWN_WOLF_HEIGHT_DEFAULT = 32;
		
		public static final int BROWN_WOLF_WIDTH = (int) (BROWN_WOLF_WIDTH_DEFAULT * Game.SCALE);
		public static final int BROWN_WOLF_HEIGHT = (int) (BROWN_WOLF_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int BROWN_WOLF_DRAWOFFSET_X = (int) (4 * Game.SCALE);
		public static final int BROWN_WOLF_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		
		public static final int MIMIC_WIDTH_DEFAULT = 108;
		public static final int MIMIC_HEIGHT_DEFAULT = 108;
		
		public static final int MIMIC_WIDTH = (int) (MIMIC_WIDTH_DEFAULT * Game.SCALE);
		public static final int MIMIC_HEIGHT = (int) (MIMIC_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int MIMIC_DRAWOFFSET_X = (int) (41 * Game.SCALE);
		public static final int MIMIC_DRAWOFFSET_Y = (int) (47 * Game.SCALE);
		
		public static final int EYEBALL_WIDTH_DEFAULT = 32;
		public static final int EYEBALL_HEIGHT_DEFAULT = 32;
		
		public static final int EYEBALL_WIDTH = (int) (EYEBALL_WIDTH_DEFAULT * Game.SCALE);
		public static final int EYEBALL_HEIGHT = (int) (EYEBALL_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int EYEBALL_DRAWOFFSET_X = (int) (0 * Game.SCALE);
		public static final int EYEBALL_DRAWOFFSET_Y = (int) (1 * Game.SCALE);
				
		public static final int BLACK_WOLF_WIDTH_DEFAULT = 48;
		public static final int BLACK_WOLF_HEIGHT_DEFAULT = 32;
		
		public static final int BLACK_WOLF_WIDTH = (int) (WOLF_WIDTH_DEFAULT * Game.SCALE);
		public static final int BLACK_WOLF_HEIGHT = (int) (WOLF_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int BLACK_WOLF_DRAWOFFSET_X = (int) (4 * Game.SCALE);
		public static final int BLACK_WOLF_DRAWOFFSET_Y = (int) (9 * Game.SCALE);
		
		public static final int DEMON_SLIME_WIDTH_DEFAULT = 288;
		public static final int DEMON_SLIME_HEIGHT_DEFAULT = 160;
		
		public static final int DEMON_SLIME_WIDTH = (int) (DEMON_SLIME_WIDTH_DEFAULT * Game.SCALE);
		public static final int DEMON_SLIME_HEIGHT = (int) (DEMON_SLIME_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int DEMON_SLIME_DRAWOFFSET_X = (int) (119 * Game.SCALE); //119 prev
		public static final int DEMON_SLIME_DRAWOFFSET_Y = (int) (75 * Game.SCALE); //70 prev
		
		public static final int FROST_GUARDIAN_WIDTH_DEFAULT = 192;
		public static final int FROST_GUARDIAN_HEIGHT_DEFAULT = 128;
		
		public static final int FROST_GUARDIAN_WIDTH = (int) (FROST_GUARDIAN_WIDTH_DEFAULT * Game.SCALE);
		public static final int FROST_GUARDIAN_HEIGHT = (int) (FROST_GUARDIAN_HEIGHT_DEFAULT * Game.SCALE);
		
		public static final int FROST_GUARDIAN_DRAWOFFSET_X = (int) (56 * Game.SCALE);
		public static final int FROST_GUARDIAN_DRAWOFFSET_Y = (int) (25 * Game.SCALE); 
		
		// 61 x 89;
		
		
		// 79 x 92 hitbox fg
		
				//24 x 29 hitbox
		
		
	
		
		
		
//		public static final int IDLE = 0;
//		public static final int RUNNING = 1;
//		public static final int ATTACK_1 = 2;
//		public static final int ATTACK_2 = 3;
//		public static final int HIT = 4;
//		public static final int DEAD = 5;
//		
//		public static final int SKELLY_WIDTH_DEFAULT = 96;
//		public static final int SKELLY_HEIGHT_DEFAULT = 64;
//		
//		public static final int SKELLY_WIDTH = (int) (SKELLY_WIDTH_DEFAULT * Game.SCALE);
//		public static final int SKELLY_HEIGHT = (int) (SKELLY_HEIGHT_DEFAULT * Game.SCALE);
//		
//		public static final int SKELLY_DRAWOFFSET_X = (int) (40 * Game.SCALE);
//		public static final int SKELLY_DRAWOFFSET_Y = (int) (0 * Game.SCALE);
		
		public static int GetSpriteAmount(int enemy_type, int enemy_state) {
			switch(enemy_type) {
			case SKELLY:
				switch(enemy_state) {
					case IDLE:
						return 8;
					case RUNNING:
						return 6;
					case ATTACK_1:
						return 13;
					case HIT:
						return 5;
					case DEAD:
						return 12;
//					case IDLE:
//						return 8;
//					case RUNNING:
//					case ATTACK_1:
//						return 10;
//					case ATTACK_2:
//						return 9;
//					case HIT:
//						return 5;
//					case DEAD:
//						return 13;
				}
			case SLIME:
				switch(enemy_state) {
					case IDLE:
					case RUNNING:
					case HIT:
					case DEAD:
						return 4;
					case ATTACK_1:
						return 5;
				}
			case WOLF:
			case BROWN_WOLF:
			case BLACK_WOLF:
				switch(enemy_state) {
					case IDLE:
					case HIT:
						return 4;
					case RUNNING:
						return 6;
					case DEAD:
						return 8;
					case ATTACK_1:
						return 7;
				}
			case BLUE_GOLEM:
			case ORANGE_GOLEM:
				switch(enemy_state) {
					case IDLE:
						return 8;
					case HIT:
						return 4;
					case RUNNING:
						return 10;
					case DEAD:
						return 13;
					case ATTACK_1:
						return 11;
				}
			case MIMIC:
				switch(enemy_state) {
					case IDLE:
					case ATTACK_1:
					case DEAD:
					case RUNNING:
						return 4;
					case HIT:
						return 1;
				}
			case EYEBALL:
				switch(enemy_state) {
					case IDLE:
					case ATTACK_1:
					case DEAD:
					case RUNNING:
						return 4;
					case HIT:
						return 2;
				}
			case DEMON_SLIME:
				switch(enemy_state) {
					case IDLE:
						return 6;
					case ATTACK_1:
						return 15;
					case DEAD:
						return 22;
					case RUNNING:
						return 12;
					case HIT:
						return 5;
				}
			case FROST_GUARDIAN:
				switch(enemy_state) {
					case IDLE:
						return 6;
					case ATTACK_1:
						return 14;
					case DEAD:
						return 16;
					case RUNNING:
						return 10;
					case HIT:
						return 7;
				}
		
			}
			return 0;
		}
		
		public static int GetMaxHealth(int enemy_Type) {
			switch(enemy_Type) {
			case SKELLY:
				return 40;
			case SLIME:
				return 35;
			case WOLF:
			case BROWN_WOLF:
			case BLACK_WOLF:
				return 40;
			case BLUE_GOLEM:
			case ORANGE_GOLEM:
				return 50;
			case MIMIC:
				return 40;
			case EYEBALL:
				return 40;
			case DEMON_SLIME:
				return 80;
			case FROST_GUARDIAN:
				return 80;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_Type) {
			switch(enemy_Type) {
			case SKELLY:
				return 15;
			case SLIME:
				return 10;
			case WOLF:
			case BROWN_WOLF:
			case BLACK_WOLF:
				return 13;
			case BLUE_GOLEM:
			case ORANGE_GOLEM:
				return 20;
			case MIMIC:
				return 13;
			case EYEBALL:
				return 11;
			case DEMON_SLIME:
				return 23;
			case FROST_GUARDIAN:
				return 23;
			default:
				return 0;
			}
		}
	}
	
	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
		
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
			
		}
		
		public static class UrmButtons{
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
		}
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}
	
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int CROUCH = 2;
		public static final int SLIDE = 3;
		public static final int JUMP = 4;
		public static final int GROUND = 5;
		public static final int HURT = 6;
		public static final int DIE = 7;
		public static final int ATTACK_1 = 8;
		public static final int ATTACK_2 = 9;
		public static final int ATTACK_3 = 10;
		public static final int JUMP_ATTACK = 11;
		public static final int USE_ITEM = 12;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
				case IDLE:
				case JUMP:
				case CROUCH:
				case JUMP_ATTACK:
					return 4;
				case RUNNING:
				case ATTACK_2:
				case ATTACK_3:
					return	6;
				case SLIDE:
				case ATTACK_1:
					return 5;
				case GROUND:
					return 2;	
				case HURT:
				case USE_ITEM:
					return 3;
				case DIE:
					return 7;
				default:
					return 1;
			}
			
		}
	}
}
