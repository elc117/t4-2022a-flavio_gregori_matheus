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
    private static HeadGenerator hunter, goblin, jonas;
    private static BuyButton goblinButton, hunterButton, jonasButton;
    private static ArrayList<BuyButton> unlockedButtons = new ArrayList<>();
    private static ArrayList<BuyButton> lockedButtons = new ArrayList<>();
    private static ArrayList<HeadGenerator> headGenerators = new ArrayList<>();

    public void init(){
        hunter = new HeadGenerator(1, 0, 10, "Caçador", 0);
        jonas = new HeadGenerator(200, 0, 50, "Jonas", 100);
        goblin = new HeadGenerator(200, 0, 50, "Goblin", 100);
        
        hunterButton = new BuyButton(hunter, "buttons/hunterButton.png");
        goblinButton = new BuyButton(goblin, "buttons/hunterButton.png");
        jonasButton = new BuyButton(jonas, "buttons/hunterButton.png");

        lockedButtons.add(hunterButton);
        lockedButtons.add(goblinButton);
        lockedButtons.add(jonasButton);

        headGenerators.add(hunter);
        headGenerators.add(goblin);
        headGenerators.add(jonas);

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
