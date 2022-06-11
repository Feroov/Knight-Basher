package com.feroov.inputs;

import com.feroov.main.GamePanel;
import static com.feroov.utils.Constants.Directions.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener
{
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(true);
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(true);
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(true);
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            gamePanel.getGame().getPlayer().setAttacking(true);
        }
    }
}
