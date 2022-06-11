package com.feroov.main;

import com.feroov.inputs.KeyboardInputs;
import com.feroov.inputs.MouseInputs;
import static com.feroov.utils.Constants.PlayerConstants.*;
import static com.feroov.utils.Constants.Directions.*;

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
    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 10;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel()
    {
        mouseInputs = new MouseInputs(this);

        importImg();
        setPanelSize();
        loadAnimations();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations()
    {
        animations = new BufferedImage[9][10];

        for(int j = 0; j < animations.length; j++)
        {
            for(int i = 0; i < animations[j].length; i++)
            {
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }

    }

    private void importImg()
    {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try { img = ImageIO.read(Objects.requireNonNull(is)); }
        catch (IOException e) { e.printStackTrace(); } finally { try{ is.close(); }
        catch (IOException e) { e.printStackTrace(); }}
    }

    private void setPanelSize()
    {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction)
    {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    private void setAnimation()
    {
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePosition()
    {
        if(moving)
        {
            switch (playerDirection)
            {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    public void updateGame()
    {
        updateAnimTick();
        setAnimation();
        updatePosition();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(animations[playerAction][animIndex], (int)xDelta, (int)yDelta, 128, 80, null);
    }



    private void updateAnimTick()
    {
        animTick++;
        if(animTick >= animSpeed)
        {
            animTick = 0;
            animIndex++;
            if(animIndex >= GetSpriteAmount(playerAction))
            {
                animIndex = 0;
            }
        }
    }
}

