package com.feroov.entities;


import com.feroov.main.Game;

import static com.feroov.utils.Constants.EnemyConstants.*;

public class Goblin extends Enemy
{

    public Goblin(float x, float y)
    {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        initHitBox(x, y, (int) (22 * Game.SCALE), (int) (21 * Game.SCALE));
    }

    public void update(int[][] lvlData, Player player)
    {
        updateMove(lvlData, player);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData, Player player)
    {
        if (firstUpdate)
            firstUpdateCheck(lvlData);

        if (inAir)
            updateInAir(lvlData);
        else {
            switch (enemyState) {
                case IDLE -> newState(RUNNING);
                case RUNNING -> {
                    if (canSeePlayer(lvlData, player))
                        turnTowardsPlayer(player);
                    if (isPlayerCloseForAttack(player))
                        newState(ATTACK);
                    move(lvlData);
                }
            }
        }

    }
}