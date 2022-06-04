package com.feroov.main;

import com.feroov.inputs.KeyboardInputs;
import com.feroov.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;

    public GamePanel()
    {
        mouseInputs = new MouseInputs(this);

        importImg();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg()
    {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try { img = ImageIO.read(Objects.requireNonNull(is)); } catch (IOException e) { e.printStackTrace(); }
    }

    private void setPanelSize()
    {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value)
    {
        this.xDelta += value;
    }

    public void changeYDelta(int value)
    {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y)
    {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(img.getSubimage(0, 0, 39, 43), (int)xDelta, (int)yDelta, 130, 130, null);
    }
}

