package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.base.GeneratorManager;
import com.mygdx.game.base.Stock;

public class HeadGeneratorManager extends GeneratorManager<HeadGenerator> {

    private ArrayList<HeadGenerator> allButtons;

    public HeadGeneratorManager(HeadStock stock) {
        HeadGenerator hunter = new HeadGenerator(1, 0, 15, 0, stock, "Caçador", "buttons/hunterButton.png");
        HeadGenerator goblin = new HeadGenerator(3, 0, 100, 200, stock, "Goblin", "buttons/hunterButton.png");
        HeadGenerator orc = new HeadGenerator(9, 0, 1100, 1500, stock, "Orc", "buttons/hunterButton.png");
        HeadGenerator sacrifice = new HeadGenerator(25, 0, (long) 12E+3, (long) 15E+3, stock, "Sacrifícios", "buttons/hunterButton.png");
        HeadGenerator feudalAttack = new HeadGenerator(100, 0, (long) 13E+4, (long) 15E+4, stock, "Ataque ao feudo", "buttons/hunterButton.png");
        HeadGenerator hydra = new HeadGenerator(250, 0, (long) 14E+6, (long) 16E+6, stock, "Caça às hidras", "buttons/hunterButton.png");

        lockedGenerators.add(hunter);
        lockedGenerators.add(goblin);
        lockedGenerators.add(orc);
        lockedGenerators.add(sacrifice);
        lockedGenerators.add(feudalAttack);
        lockedGenerators.add(hydra);

        allButtons = new ArrayList<>(lockedGenerators);

        updateUnlockedGenerators(0);
        adjustButtons();
    }

    public ArrayList<HeadGenerator> getUnlockedButtons() {
        return unlockedGenerators;
    }

    private ArrayList<HeadGenerator> getAllButtons() {
        return allButtons;
    }

    public void adjustButtons() {
        Texture t;
        int x;
        int y = Gdx.graphics.getHeight() - 50;
        for (HeadGenerator headGenerator : getAllButtons()) {
            t = headGenerator.getTexture();
            x = Gdx.graphics.getWidth() - t.getWidth();
            y -= t.getHeight();
            headGenerator.adujustButton(x, y);
        }
    }

    public void drawButtons(SpriteBatch batch, BitmapFont font) {
        unlockedGenerators.forEach(headGenerator -> headGenerator.draw(batch, font));
    }
}
