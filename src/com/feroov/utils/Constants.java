package com.feroov.utils;


import com.feroov.main.Game;

public class Constants
{

    public static class EnemyConstants {
        public static final int GOBLIN = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int GOBLIN_WIDTH_DEFAULT = 72;
        public static final int GOBLIN_HEIGHT_DEFAULT = 32;

        public static final int GOBLIN_WIDTH = (int) (GOBLIN_WIDTH_DEFAULT * Game.SCALE);
        public static final int GOBLIN_HEIGHT = (int) (GOBLIN_HEIGHT_DEFAULT * Game.SCALE);

        public static final int GOBLIN_DRAWOFFSET_X = (int) (26 * Game.SCALE);
        public static final int GOBLIN_DRAWOFFSET_Y = (int) (9 * Game.SCALE);

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {
                case GOBLIN:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }
            return 0;
        }

        public static int GetMaxHealth(int enemy_type)
        {
            switch (enemy_type)
            {
                case GOBLIN:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type)
        {
            switch (enemy_type)
            {
                case GOBLIN:
                    return 15;
                default:
                    return 0;
            }
        }
    }



    public static class Directions
    {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }


    public static class PlayerConstants
    {
        public static final int ATTACK = 0;
        public static final int IDLE = 1;
        public static final int RUNNING = 2;
        public static final int JUMP = 3;
        public static final int FALLING = 4;
        public static final int DYING = 5;

        public static int GetSpriteAmount(int player_action)
        {
            return switch (player_action)
            {
                case ATTACK -> 10;
                case IDLE -> 10;
                case RUNNING -> 10;
                case JUMP -> 1;
                case FALLING -> 1;
                case DYING -> 9;
                default -> 1;
            };
        }
    }

}
