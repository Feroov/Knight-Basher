package com.feroov.entities;


import com.feroov.main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static com.feroov.utils.Constants.EnemyConstants.*;
import static com.feroov.utils.Constants.Directions.*;

public class Goblin extends Enemy
{

    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;


    public Goblin(float x, float y)
    {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        initHitBox(x, y, (int) (22 * Game.SCALE), (int) (21 * Game.SCALE));
        initAttackBox();
    }
    private void initAttackBox()
    {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player)
    {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();

    }

    private void updateAttackBox()
    {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    private void updateBehavior(int[][] lvlData, Player player)
    {
        if (firstUpdate)
            firstUpdateCheck(lvlData);

        if (inAir)
            updateInAir(lvlData);
        else
        {
            switch (enemyState)
            {
                case IDLE:
                    newState(RUNNING);
                    break;
                case RUNNING:
                    if (canSeePlayer(lvlData, player))
                    {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player))
                            newState(ATTACK);
                    }

                    move(lvlData);
                    break;
                case ATTACK:
                    if (aniIndex == 0)
                        attackChecked = false;

                    if (aniIndex == 3 && !attackChecked)
                        checkPlayerHit(attackBox, player);

                    break;
                case HIT:
                    break;
            }
        }

    }

    public void drawAttackBox(Graphics g, int xLvlOffset)
    {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    public int flipX()
    {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }

    public int flipW()
    {
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;

    }

}