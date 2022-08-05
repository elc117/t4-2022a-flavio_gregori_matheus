package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.input.BuyButton;

public class HeadGenerators {
    private static ArrayList<BuyButton> unlockedButtons = new ArrayList<>();
    private static ArrayList<BuyButton> lockedButtons = new ArrayList<>();
    private static ArrayList<HeadGenerator> headGenerators = new ArrayList<>();
    
    public void init(){
        HeadGenerator hunter = new HeadGenerator(1, 0, 15, "Caçador", 0);
        HeadGenerator goblin = new HeadGenerator(3, 0, 100, "Goblin", 200);
        HeadGenerator orc = new HeadGenerator(9, 0, 1100, "Orc", 1500);
        HeadGenerator sacrifice = new HeadGenerator(25, 0, (long) 12E+3, "Sacrifícios",(long) 15E+3);
        HeadGenerator feudalAttack = new HeadGenerator(100, 0, (long) 130E+3, "Ataque ao feudo", (long) 150E+3);
        HeadGenerator hydra = new HeadGenerator(250, 0, (long) 14E+6, "Caça às hidras", (long) 16E+6);

        lockedButtons.add(new BuyButton(hunter, "buttons/hunterButton.png"));
        lockedButtons.add(new BuyButton(goblin, "buttons/hunterButton.png"));
        lockedButtons.add(new BuyButton(orc, "buttons/hunterButton.png"));
        lockedButtons.add(new BuyButton(sacrifice, "buttons/hunterButton.png"));
        lockedButtons.add(new BuyButton(feudalAttack, "buttons/hunterButton.png"));
        lockedButtons.add(new BuyButton(hydra, "buttons/hunterButton.png"));

        headGenerators.add(hunter);
        headGenerators.add(goblin);
        headGenerators.add(orc);
        headGenerators.add(sacrifice);
        headGenerators.add(feudalAttack);
        headGenerators.add(hydra);

        attButtons(0);
        disposeButtons();
    }

    public ArrayList<BuyButton> getButtons(){
        return unlockedButtons;
    }

    private ArrayList<BuyButton> getAllButtons(){
        ArrayList<BuyButton> buttons = new ArrayList<>(unlockedButtons);
        buttons.addAll(lockedButtons);
        return buttons;
    }

    public void disposeButtons(){
        Texture t;
        int x;
        int y = Gdx.graphics.getHeight() - 50;
        for(BuyButton b : getAllButtons()){
            t = b.getTexture();
            x = Gdx.graphics.getWidth() - t.getWidth();
            y -= t.getHeight();
            b.adujustButton(x, y);
        }
    }

    public void attButtons(long headAmount){
        ArrayList<BuyButton> iterator = new ArrayList<>(lockedButtons);
        for(BuyButton b : iterator){
            if(b.getHeadGenerator().getAmountToUnlock() <= headAmount){
                lockedButtons.remove(b);
                unlockedButtons.add(b);
            }
        }
    }

    public void drawButtons(SpriteBatch batch, BitmapFont font){
        for(BuyButton b : unlockedButtons){
            b.draw(batch, font);
        }
    }

    public ArrayList<HeadGenerator> getGenerators(){
        return headGenerators;
    }
}
