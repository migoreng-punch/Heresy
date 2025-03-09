package utils;

public class Constants {
	
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
