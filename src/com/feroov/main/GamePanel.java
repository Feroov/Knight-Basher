package com.feroov.main;

import com.feroov.inputs.KeyboardInputs;
import com.feroov.inputs.MouseInputs;
import static com.feroov.main.Game.GAME_HEIGHT;
import static com.feroov.main.Game.GAME_WIDTH;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game)
    {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void setPanelSize()
    {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void updateGame()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){ return game; }
}

