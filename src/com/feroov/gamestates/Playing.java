package com.feroov.gamestates;

import com.feroov.entities.EnemyManager;
import com.feroov.entities.Player;
import com.feroov.levels.LevelManager;
import com.feroov.main.Game;
import com.feroov.ui.PauseOverlay;
import com.feroov.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Playing extends State implements Statemethods
{
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

    private BufferedImage backgroundImg, backgroundImg2, backgroundImg3;
    private int[] smallCloudsPos;
    private Random rnd = new Random();

    public static final int TREE_TWO_WIDTH_DEFAULT = 900;
    public static final int TREE_TWO_HEIGHT_DEFAULT = 448;
    public static final int TREE_THREE_WIDTH_DEFAULT = 448;
    public static final int TREE_THREE_HEIGHT_DEFAULT = 401;

    public static final int MAIN_BG_WIDTH = (int) (TREE_TWO_WIDTH_DEFAULT * Game.SCALE);
    public static final int MAIN_BG_HEIGHT = (int) (TREE_TWO_HEIGHT_DEFAULT * Game.SCALE);
    public static final int SMALL_CLOUD_WIDTH = (int) (TREE_THREE_WIDTH_DEFAULT * Game.SCALE);
    public static final int SMALL_CLOUD_HEIGHT = (int) (TREE_THREE_HEIGHT_DEFAULT * Game.SCALE);

    public Playing(Game game)
    {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        backgroundImg2 = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG2);
        backgroundImg3 = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG3);

    }

    private void initClasses()
    {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(200, 380, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());

        pauseOverlay = new PauseOverlay(this);
    }

    @Override
    public void update()
    {
        if (!paused)
        {
            levelManager.update();
            enemyManager.update();
            player.update();
            checkCloseToBorder();
        } else { pauseOverlay.update(); }
    }

    private void checkCloseToBorder()
    {
        int playerX = (int) player.getHitBox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder)
            xLvlOffset += diff - leftBorder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;
    }

    private void drawTrees(Graphics g)
    {
        for (int i = 0; i < 3; i++)
            g.drawImage(backgroundImg2, i * MAIN_BG_WIDTH - (int) (xLvlOffset * 0.3), 0, MAIN_BG_WIDTH, MAIN_BG_HEIGHT, null);

        for (int i = 0; i < 3; i++)
            g.drawImage(backgroundImg3, i * MAIN_BG_WIDTH - (int) (xLvlOffset * 0.7), 0, MAIN_BG_WIDTH, MAIN_BG_HEIGHT, null);
    }


    @Override
    public void draw(Graphics g)
    {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        drawTrees(g);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);

        if (paused)
        {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> player.setUp(true);
            case KeyEvent.VK_A -> player.setLeft(true);
            case KeyEvent.VK_S -> player.setDown(true);
            case KeyEvent.VK_D -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
            case KeyEvent.VK_SHIFT -> player.setRunning(true);
            case KeyEvent.VK_ESCAPE -> paused = !paused;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER)  { player.setAttacking(true); }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> player.setUp(false);
            case KeyEvent.VK_A -> player.setLeft(false);
            case KeyEvent.VK_S -> player.setDown(false);
            case KeyEvent.VK_D -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
            case KeyEvent.VK_SHIFT -> player.setRunning(false);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (paused)
            pauseOverlay.mouseDragged(e);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (paused)
            pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if (paused)
            pauseOverlay.mouseMoved(e);
    }

    public void unpauseGame()
    {
        paused = false;
    }

    public void windowFocusLost()  { player.resetDirectionBooleans(); }
    public Player getPlayer(){ return player; }
}
