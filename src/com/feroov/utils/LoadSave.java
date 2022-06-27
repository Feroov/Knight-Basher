package com.feroov.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class LoadSave
{
    public static final String PLAYER_ATLAS = "player/player_sprites.png";
    public static final String LEVEL_ATLAS = "tiles/outside_sprites.png";

    public static final String LEVEL_ONE_DATA = "levelsbackground/level_one_data_long.png";
    public static final String MENU_BACKGROUND_IMG = "levelsbackground/background_menu.png";
    public static final String PLAYING_BG_IMG = "levelsbackground/playing_bg_img.png";
    public static final String PLAYING_BG_IMG2 = "levelsbackground/playing_bg_img2.png";
    public static final String PLAYING_BG_IMG3 = "levelsbackground/playing_bg_img3.png";

    public static final String MENU_BUTTONS = "menu/button_atlas.png";
    public static final String MENU_BACKGROUND = "menu/menu_background.png";
    public static final String PAUSE_BACKGROUND = "menu/pause_menu.png";
    public static final String SOUND_BUTTONS = "menu/sound_button.png";
    public static final String URM_BUTTONS = "menu/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "menu/volume_buttons.png";
    public static final String STATUS_BAR = "menu/health_power_bar.png";

    public static final String GOBLIN_SPRITE = "enemies/goblin_sprite.png";

    public static final String COMPLETED_IMG = "menu/completed_sprite.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] GetAllLevels()
    {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[i] = files[j];

            }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }
}
