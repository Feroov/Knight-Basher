package com.feroov.entities;

import com.feroov.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.feroov.utils.Constants.PlayerConstants.*;

public class Player extends Entity
{

    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    public Player(float x, float y)
    {
        super(x, y);
        loadAnimations();
    }

    public void update()
    {
        updatePosition();
        updateAnimTick();
        setAnimation();
    }

    public void render(Graphics g)
    {
        g.drawImage(animations[playerAction][animIndex], (int)x, (int)y, 128, 80, null);

    }

    private void setAnimation()
    {
        int startAnim = playerAction;
        
        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK;
        
        if(startAnim != playerAction)
            resetAnimTick();
    }

    private void resetAnimTick()
    {
        animTick = 0;
        animIndex = 0;
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
                attacking = false;
            }
        }
    }

    private void updatePosition()
    {
        moving = false;

        if(left && !right) { x -= playerSpeed; moving = true; }
        else if(right && !left) { x += playerSpeed; moving = true; }

        if(up && !down){ y -= playerSpeed; moving = true; }
        else if(down && !up) { y += playerSpeed; moving = true; }
    }

    private void loadAnimations()
    {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][10];

        for(int j = 0; j < animations.length; j++)
        {
            for(int i = 0; i < animations[j].length; i++)
            {
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }
    }

    public void resetDirectionBooleans()
    {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking)
    {
        this.attacking = attacking;
    }

    public boolean isLeft() { return left; }
    public void setLeft(boolean left) { this.left = left; }

    public boolean isUp() { return up; }
    public void setUp(boolean up) { this.up = up; }

    public boolean isRight() { return right; }
    public void setRight(boolean right) { this.right = right; }

    public boolean isDown() { return down; }
    public void setDown(boolean down) { this.down = down;}
}