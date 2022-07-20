package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Guillotine {
    private Sprite guillotineSprite;
    private Texture guillotineTexture;

    public Guillotine(){
		guillotineTexture = new Texture("crop/02.png");
		guillotineSprite = new Sprite(guillotineTexture);
        guillotineSprite.setPosition(10, 10);
        guillotineSprite.setOrigin(10, 10);
    }

    public void draw(SpriteBatch batch){
        guillotineSprite.draw(batch);
    }

    public boolean mouseClick(int x, int y){
        if(x > guillotineSprite.getOriginX() && x < guillotineSprite.getOriginX() + guillotineSprite.getWidth() &&
           y > guillotineSprite.getOriginY() && y < guillotineSprite.getOriginY() + guillotineSprite.getHeight() ){
            return true;
        }
        return false;

        

        /*
        if(guillotineSprite.getBoundingRectangle().contains(x,y)){
            return true
        }
         */
    }
}