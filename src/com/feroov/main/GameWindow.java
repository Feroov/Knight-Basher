package com.feroov.main;


import javax.swing.*;

public class GameWindow
{
    private JFrame jFrame;
    private static ImageIcon icon;

    public GameWindow(GamePanel gamePanel)
    {
        jFrame = new JFrame();

        jFrame.setResizable(false);
        jFrame.setSize(400,400);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);

        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);

        icon = new ImageIcon("res/logo.png");
        jFrame.setIconImage(icon.getImage());
    }
}
