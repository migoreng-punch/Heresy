package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Enemy;
import entities.Player;
import gamestates.Playing;
import levels.Level;
import utils.LoadSave;
import objects.GameObject;
import static utils.Constants.ObjectConstants.*;

public class ObjectManager {
	
	private Playing playing;
	private BufferedImage[][] potionImgs, containerImgs;
	private BufferedImage spikeImg, cSpikeImg;
	private ArrayList<Potion> potions;
	private ArrayList<GameContainer> containers;
	private ArrayList<Spike> spikes;
	private ArrayList<CaveSpike> cSpikes;
	
	private Level currentLevel;

	
	public ObjectManager(Playing playing) {
		this.playing = playing;
		currentLevel = playing.getLevelManager().getCurrentLevel();
		loadImgs();
	}
	
	public void checkSpikesTouched(Player p) {
		for (Spike s : spikes)
			if (s.getHitBox().intersects(p.getHitbox()))
				p.kill();
	}
	
	public void checkCaveSpikesTouched(Player p) {
		for(CaveSpike s : cSpikes)
			if(s.getHitBox().intersects(p.getHitbox()))
				p.kill();
	}
	
	public void checkSpikesTouched(Enemy e) {
		for (Spike s : spikes)
			if (s.getHitBox().intersects(e.getHitbox()))
				e.hurt(200);
	}
	
	public void checkCaveSpikesTouched(Enemy e) {
		for (CaveSpike s : cSpikes)
			if(s.getHitBox().intersects(e.getHitbox()))
				e.hurt(200);
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
			if(gc.isActive() && !gc.doAnimation) {
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
		potions = new ArrayList<>(newLevel.getPotion());
		containers = new ArrayList<>(newLevel.getGameContainer());
		spikes = newLevel.getSpike();
		cSpikes = newLevel.getCSpikes();
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
		
		spikeImg = LoadSave.GetSpriteAtlas(LoadSave.TRAP_ATLAS);
		cSpikeImg = LoadSave.GetSpriteAtlas(LoadSave.CAVE_TRAP_ATLAS);
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
		drawTraps(g, xLvlOffset);
	}
	
	private void drawTraps(Graphics g, int xLvlOffset) {
		for(Spike s : spikes) {
			g.drawImage(spikeImg,
					(int) (s.getHitBox().x - xLvlOffset), 
					(int) (s.getHitBox().y - s.getyDrawOffset()), 
					SPIKE_WIDTH, SPIKE_HEIGHT, null);
			s.drawHitbox(g, xLvlOffset);
		}
		
		for(CaveSpike s : cSpikes) {
			g.drawImage(cSpikeImg, (int) (s.getHitBox().x - xLvlOffset), (int) s.getHitBox().y, CAVE_SPIKE_WIDTH, CAVE_SPIKE_HEIGHT, null);
			s.drawHitbox(g, xLvlOffset);
		}
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
						CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
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
		System.out.println("Size of arrays: " + potions.size() + " - " + containers.size()); 
		
		loadObjects(playing.getLevelManager().getCurrentLevel());
		
		for(Potion p : potions) {
			p.reset();
		}
		
		for(GameContainer gc : containers)
			gc.reset();
		
		System.out.println("Size of arrays: " + potions.size() + " - " + containers.size());
	}
}
