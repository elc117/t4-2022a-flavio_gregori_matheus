package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.input.BuyButton;

public class HeadGenerators {
    private static HeadGenerator hunter, goblin;
    private static ArrayList<HeadGenerator> unlockedHeadGenerators = new ArrayList<>();
    private static ArrayList<HeadGenerator> lockedHeadGenerators = new ArrayList<>();

    public void init(){
        unlockedHeadGenerators = new ArrayList<>();
        lockedHeadGenerators = new ArrayList<>();

        hunter = new HeadGenerator(1, 0, 10, "Caçador", 10, "buttons/hunterButton.png");
        goblin = new HeadGenerator(200, 0, 50, "Goblin", 100, "head.png");
        lockedHeadGenerators.add(hunter);
        lockedHeadGenerators.add(goblin);

        attGenerators(0);
        disposeButtons();
    }

    public ArrayList<BuyButton> getButtons(){
        ArrayList<BuyButton> buttons = new ArrayList<>();

        for(HeadGenerator gen : unlockedHeadGenerators){
            buttons.add(gen.getButton());
        }

        return buttons;
    }

    private ArrayList<BuyButton> getAllButtons(){
        ArrayList<BuyButton> buttons = new ArrayList<>();

        for(HeadGenerator gen : unlockedHeadGenerators){
            buttons.add(gen.getButton());
        }

        for(HeadGenerator gen : lockedHeadGenerators){
            buttons.add(gen.getButton());
        }

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

    public ArrayList<HeadGenerator> getUnlockedGenerators(){
        return unlockedHeadGenerators;
    }

    public ArrayList<HeadGenerator> getLockeHeadGenerators(){
        return lockedHeadGenerators;
    }

    public void attGenerators(long headAmount){
        ArrayList<HeadGenerator> iterator = new ArrayList<>(lockedHeadGenerators);
        for(HeadGenerator gen : iterator){
            if(gen.getAmountToUnlock() <= headAmount){
                lockedHeadGenerators.remove(gen);
                unlockedHeadGenerators.add(gen);
            }
        }
    }

    public void drawButtons(SpriteBatch batch){
        for(BuyButton b : getButtons()){
            b.draw(batch);
        }
    }
}
