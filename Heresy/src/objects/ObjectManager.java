package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utils.LoadSave;
import static utils.Constants.ObjectConstants.*;

public class ObjectManager {
	
	private Playing playing;
	private BufferedImage[][] potionImgs, containerImgs;
	private ArrayList<Potion> potions;
	private ArrayList<GameContainer> containers;
	
	public ObjectManager(Playing playing) {
		this.playing = playing;
		loadImgs();
	}
	
	public void checkObjectTouched(Rectangle2D.Float hitbox) {
		for(Potion p : potions) {
			if(p.isActive()) {
				if(hitbox.intersects(p.getHitBox())) {
					p.setActive(false);
					applyEffectToPlayer(p);
					
				}
			}
		}
	}
	
	public void applyEffectToPlayer(Potion p) {
		if(p.getObjType() == RED_POTION) {
			playing.getPlayer().changeHealth(RED_POTION_VALUE);
		}
		else {
			playing.getPlayer().changePower(BLUE_POTION_VALUE);
		}
	}
	
	public void checkObjectHit(Rectangle2D.Float attackbox) {
		for(GameContainer gc : containers) {
			if(gc.isActive()) {
				if(gc.getHitBox().intersects(attackbox)) {
					gc.setAnimation(true);
					
					int type = 0;
					if(gc.getObjType() == BARREL) {
						type = 1;
					}
					potions.add(new Potion( 
							(int) (gc.getHitBox().x + gc.getHitBox().width / 4), 
							(int) (gc.getHitBox().y - gc.getHitBox().height / 2), 
							type));
					return; 
				}
			}
		}
	}
	
	public void loadObjects(Level newLevel) {
		potions = newLevel.getPotion();
		containers = newLevel.getGameContainer();
		}

	private void loadImgs() {
		BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
		potionImgs = new BufferedImage[2][7];
		
		for(int i = 0; i < potionImgs.length; i++) {
			for(int j = 0; j < potionImgs[i].length; j++) {
				potionImgs[i][j] = potionSprite.getSubimage(12 * j, 16 * i, 12, 16);
			}
		}
		
		BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
		containerImgs = new BufferedImage[2][7];
		
		for(int i = 0; i < containerImgs.length; i++) {
			for(int j = 0; j < containerImgs[i].length; j++) {
				containerImgs[i][j] = containerSprite.getSubimage(64 * j, 64 * i, 64, 64);
			}
		}
	}
	
	public void update() {
		for(Potion p : potions){
			if(p.isActive())
				p.update();
		}
		
		for(GameContainer gc : containers) {
			if(gc.isActive())
				gc.update();
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawPotions(g, xLvlOffset);
		drawContainers(g, xLvlOffset);
	}
	
	private void drawContainers(Graphics g, int xLvlOffset) {
		for(GameContainer gc : containers) {
			if(gc.isActive()) {
				int type = 0;
				if(gc.getObjType() == BARREL) {
					type = 1;
				}
				g.drawImage(containerImgs[type][gc.getAniIndex()], 
						(int) (gc.getHitBox().x - gc.getxDrawOffset() - xLvlOffset),
						(int) (gc.getHitBox().y - gc.getyDrawOffset()), 
						CONTAINER_WIDTH, 
						CONTAINER_HEIGHT, null);
			}
			gc.drawHitbox(g, xLvlOffset);
		}
	}

	private void drawPotions(Graphics g, int xLvlOffset) {
		for(Potion p : potions){
			if(p.isActive()) {
				int type = 0;
				if(p.getObjType() == RED_POTION) {
					type = 1;
				}
				g.drawImage(potionImgs[type][p.getAniIndex()], 
						(int) (p.getHitBox().x - p.getxDrawOffset() - xLvlOffset),
						(int) (p.getHitBox().y - p.getyDrawOffset()), 
						POTION_WIDTH, 
						POTION_HEIGHT, null);
			}
			p.drawHitbox(g, xLvlOffset);
		}
	}

	public void resetAllObjects() {
		for(GameContainer gc : containers)
			gc.reset();
	}
}
