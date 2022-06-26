package com.feroov.gamestates;

import com.feroov.main.Game;
import com.feroov.ui.MenuButton;


import java.awt.event.MouseEvent;

public class State
{
    public Game game;

    public State(Game game)
    {
        this.game = game;
    }

    public State() {
    }

    public Game getGame(){ return game; }

    public boolean isIn(MouseEvent e, MenuButton mb)
    {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
}
