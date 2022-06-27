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
}