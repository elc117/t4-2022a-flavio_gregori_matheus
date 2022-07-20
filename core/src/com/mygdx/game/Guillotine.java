package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class Guillotine {
    private Sprite guillotineSprite;
    private Texture guillotineTexture;

    public Guillotine(){

		guillotineTexture = new Texture("crop/01.png");

		guillotineSprite = new Sprite(guillotineTexture);

        int bottomMargin = (int) (Gdx.graphics.getHeight() - guillotineTexture.getHeight()) / 2;
        guillotineSprite.setPosition(50, bottomMargin);
        guillotineSprite.setOrigin(50, bottomMargin);
    }

    public void draw(SpriteBatch batch){
        guillotineSprite.draw(batch);
    }

    public boolean mouseClick(int x, int y){
        
        if(guillotineSprite.getBoundingRectangle().contains(x,y)){
            return true;
        }
        return false;
    }
}