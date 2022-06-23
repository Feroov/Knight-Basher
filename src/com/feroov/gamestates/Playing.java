package com.feroov.gamestates;

import com.feroov.entities.Player;
import com.feroov.levels.LevelManager;
import com.feroov.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Playing extends State implements Statemethods
{
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) { super(game); initClasses(); }

    private void initClasses()
    {
        levelManager = new LevelManager(game);
        player = new Player(200, 380, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    @Override
    public void update()
    {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
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
            case KeyEvent.VK_ESCAPE -> Gamestate.state = Gamestate.MENU;
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void windowFocusLost()  { player.resetDirectionBooleans(); }
    public Player getPlayer(){ return player; }
}
