package com.feroov.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.feroov.gamestates.Playing;
import com.feroov.utils.LoadSave;
import static com.feroov.utils.Constants.EnemyConstants.*;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] goblinArray;
    private ArrayList<Goblin> goblins = new ArrayList<>();

    public EnemyManager(Playing playing)
    {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies()
    {
        goblins = LoadSave.getGoblins();
    }

    public void update(int[][] lvlData)
    {
        for (Goblin c : goblins)
            c.update(lvlData);
    }

    public void draw(Graphics g, int xLvlOffset) { drawGoblins(g, xLvlOffset); }

    private void drawGoblins(Graphics g, int xLvlOffset)
    {
        for (Goblin goblin : goblins)
        {
            g.drawImage(goblinArray[goblin.getEnemyState()][goblin.getAniIndex()],
                    (int) goblin.hitbox.x - xLvlOffset - GOBLIN_DRAWOFFSET_X, (int) goblin.hitbox.y - GOBLIN_DRAWOFFSET_Y, GOBLIN_WIDTH,
                    GOBLIN_HEIGHT, null);
//            goblin.drawHitBox(g, xLvlOffset);
        }

    }

    private void loadEnemyImgs()
    {

        goblinArray = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.GOBLIN_SPRITE);
        for (int j = 0; j < goblinArray.length; j++)
            for (int i = 0; i < goblinArray[j].length; i++)
                goblinArray[j][i] = temp.getSubimage(i * GOBLIN_WIDTH_DEFAULT, j * GOBLIN_HEIGHT_DEFAULT, GOBLIN_WIDTH_DEFAULT, GOBLIN_HEIGHT_DEFAULT);
    }
}