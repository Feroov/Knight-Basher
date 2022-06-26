package com.feroov.utils;


public class Constants
{


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
