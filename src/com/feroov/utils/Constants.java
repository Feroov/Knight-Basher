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
        public static final int FALLING = 3;
        public static final int DYING = 4;

        public static int GetSpriteAmount(int player_action)
        {
            return switch (player_action)
            {
                case ATTACK -> 10;
                case IDLE -> 10;
                case RUNNING -> 10;
                case FALLING -> 3;
                case DYING -> 9;
                default -> 1;
            };
        }
    }
}