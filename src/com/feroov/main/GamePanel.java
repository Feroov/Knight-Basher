package com.feroov.main;

import com.feroov.inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    public GamePanel()
    {
        addKeyListener(new KeyboardInputs());
    }

    public void paintComponent(Graphics g) { super.paintComponent(g); }
}
