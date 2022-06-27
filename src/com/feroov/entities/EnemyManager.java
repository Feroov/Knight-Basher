package com.feroov.entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.feroov.gamestates.Playing;
import com.feroov.levels.Level;
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
    }

    public void loadEnemies(Level level) { goblins = level.getGoblins(); }

    public void update(int[][] lvlData, Player player)
    {
        boolean isAnyActive = false;
        for (Goblin c : goblins)
            if (c.isActive())
            {
                c.update(lvlData, player);
                isAnyActive = true;
            }
        if(!isAnyActive)
            playing.setLevelCompleted(true);
    }

    public void draw(Graphics g, int xLvlOffset) { drawGoblins(g, xLvlOffset); }

    private void drawGoblins(Graphics g, int xLvlOffset)
    {
        for (Goblin goblin : goblins)
            if (goblin.isActive())
            {
                g.drawImage(goblinArray[goblin.getEnemyState()][goblin.getAniIndex()],
                        (int) goblin.getHitBox().x - xLvlOffset - GOBLIN_DRAWOFFSET_X + goblin.flipX(), (int) goblin.getHitBox().y - GOBLIN_DRAWOFFSET_Y,
                        GOBLIN_WIDTH * goblin.flipW(), GOBLIN_HEIGHT, null);
//				c.drawHitbox(g, xLvlOffset);
//				c.drawAttackBox(g, xLvlOffset);

            }

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox)
    {
        for (Goblin goblin : goblins)
            if (goblin.isActive())
                if (attackBox.intersects(goblin.getHitBox()))
                {
                    goblin.hurt(10);
                    return;
                }
    }

    public void resetAllEnemies()
    {
        for (Goblin c : goblins)
            c.resetEnemy();
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