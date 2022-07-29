package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.HeadGenerator;

public class BuyButton implements Button{
    private HeadGenerator generator;
    private Texture texture;
    private Sprite sprite;
    private String text;

    public BuyButton(HeadGenerator gen, String path){
        generator = gen;
        text = generator.getName();
        texture = new Texture(path);
        sprite = new Sprite(texture);
    }
    
    public void adujustButton(int x, int y){
        sprite.setPosition(x, y);
        sprite.setOrigin(x, y);
    }
    
    public Texture getTexture(){
        return texture;
    }

    public void draw(SpriteBatch batch){
        
        sprite.draw(batch);
    }

    @Override
    public Rectangle getRectangle() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public void onClick() {
        generator.buy();
    }

    public HeadGenerator getHeadGenerator() {
        return generator;
    }
    
}
