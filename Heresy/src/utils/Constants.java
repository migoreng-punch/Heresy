package utils;

import main.Game;

public class Constants {
	
	public static class EnemyConstants{
		public static final int SKELLY = 0;
		
		
		
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
			}
			return 0;
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
