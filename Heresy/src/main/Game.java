package main;

import java.awt.Graphics;
import java.io.*;
import java.util.*;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import utils.LoadSave;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 120;
    private Playing playing;
    private Menu menu;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Timer variables
    private long startTime;
    private long elapsedTime;
    private boolean timerRunning;
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startTimer();
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    public void stopTimerAndSave() {
        if (timerRunning) {
            elapsedTime = System.currentTimeMillis() - startTime;
            timerRunning = false;
            saveTimeToFile("Player1", elapsedTime / 1000); // Convert to seconds
        }
    }

    private void saveTimeToFile(String playerName, long time) {
        try (FileWriter writer = new FileWriter(LEADERBOARD_FILE, true)) {
            writer.write(playerName + " - " + time + "s\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadLeaderboard() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
                stopTimerAndSave();
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
    public long getStartTime() {
        return startTime;
    }
}
