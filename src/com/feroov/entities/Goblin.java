package com.feroov.entities;


import static com.feroov.utils.Constants.EnemyConstants.*;

public class Goblin extends Enemy
{

    public Goblin(float x, float y)
    {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
    }
}