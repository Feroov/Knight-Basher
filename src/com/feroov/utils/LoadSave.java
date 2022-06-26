package com.feroov.utils;

import com.feroov.main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class LoadSave
{
    public static final String PLAYER_ATLAS = "player/player_sprites.png";
    public static final String LEVEL_ATLAS = "tiles/outside_sprites.png";

    public static final String LEVEL_ONE_DATA = "levels/level_one_data_long.png";
    public static final String MENU_BACKGROUND_IMG = "levels/background_menu.png";
    public static final String PLAYING_BG_IMG = "levels/playing_bg_img.png";
    public static final String PLAYING_BG_IMG2 = "levels/playing_bg_img2.png";
    public static final String PLAYING_BG_IMG3 = "levels/playing_bg_img3.png";

    public static final String MENU_BUTTONS = "menu/button_atlas.png";
    public static final String MENU_BACKGROUND = "menu/menu_background.png";
    public static final String PAUSE_BACKGROUND = "menu/pause_menu.png";
    public static final String SOUND_BUTTONS = "menu/sound_button.png";
    public static final String URM_BUTTONS = "menu/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "menu/volume_buttons.png";

    public static BufferedImage GetSpriteAtlas(String fileName)
    {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try { img = ImageIO.read(Objects.requireNonNull(is)); }
        catch (IOException e) { e.printStackTrace(); } finally { try{ is.close(); }
        catch (IOException e) { e.printStackTrace(); }}

        return img;
    }

    public static int[][] GetLevelData()
    {
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for(int j = 0; j < img.getHeight(); j++)
            for(int i = 0; i < img.getWidth(); i++)
            {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[j][i] =  value;
            }
        return lvlData;
    }
}
