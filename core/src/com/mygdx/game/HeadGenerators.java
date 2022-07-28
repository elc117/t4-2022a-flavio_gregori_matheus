package com.mygdx.game;

import java.util.ArrayList;

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

        hunter = new HeadGenerator(1, 0, 10, "Caçador", 10);
        goblin = new HeadGenerator(2, 0, 50, "Goblin", 100);
        lockedHeadGenerators.add(hunter);
        lockedHeadGenerators.add(goblin);

        attGenerators(0);
    }

    public ArrayList<BuyButton> getButtons(){
        ArrayList<BuyButton> buttons = new ArrayList<>();

        for(HeadGenerator gen : unlockedHeadGenerators){
            buttons.add(gen.getButton());
        }

        return buttons;
    }

    public ArrayList<HeadGenerator> getUnlockedGenerators(){
        return unlockedHeadGenerators;
    }

    public ArrayList<HeadGenerator> getLockeHeadGenerators(){
        return lockedHeadGenerators;
    }

    public void attGenerators(long headAmount){
        ArrayList<HeadGenerator> iterator = new ArrayList<>(lockedHeadGenerators);
        System.out.println("att");
        System.out.println("total: " + headAmount);
        for(HeadGenerator gen : iterator){
            if(gen.getAmountToUnlock() <= headAmount){
                System.out.println("desbloqueado" + gen.getName());
                System.out.println("amount: " + gen.getAmountToUnlock());
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
