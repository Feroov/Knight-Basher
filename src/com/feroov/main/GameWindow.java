package com.feroov.main;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow
{
    private JFrame jFrame;
    private static ImageIcon icon;

    public GameWindow(GamePanel gamePanel)
    {
        jFrame = new JFrame();

        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);

        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.addWindowFocusListener(new WindowFocusListener()
        {
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {

            }
        });

        icon = new ImageIcon("res/logo.png");
        jFrame.setIconImage(icon.getImage());
    }
}
