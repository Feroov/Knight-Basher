package com.feroov.entities;

import com.feroov.main.Game;
import com.feroov.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.feroov.utils.Constants.PlayerConstants.*;
import static com.feroov.utils.HelpMethods.CanMoveHere;

public class Player extends Entity
{

    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 10;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOfset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height)
    {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 20 * Game.SCALE, 35 * Game.SCALE);
    }

    public void update()
    {
        updatePosition();
        updateAnimTick();
        setAnimation();
    }

    public void render(Graphics g)
    {
        g.drawImage(animations[playerAction][animIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOfset), width, height, null);
        drawHitBox(g);
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
        if(!left && !right && !up && !down)
            return;

        float xSpeed = 0, ySpeed = 0;

        if(left && !right) { xSpeed = -playerSpeed; }
        else if(right && !left) { xSpeed = playerSpeed;  }

        if(up && !down){ ySpeed = -playerSpeed;  }
        else if(down && !up) { ySpeed = playerSpeed;  }

        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData))
        {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }
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

    public void loadLevelData(int[][] lvlData)
    {
        this.lvlData = lvlData;
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
