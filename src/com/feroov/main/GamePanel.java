package com.feroov.main;

import com.feroov.inputs.KeyboardInputs;
import com.feroov.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private int frames = 0;
    private long lastCheck = 0;

    public GamePanel()
    {
        mouseInputs = new MouseInputs(this);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        frames++;
        if(System.currentTimeMillis() - lastCheck >= 1000)
        {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS:" + frames);
            frames = 0;
        }
    }
}

