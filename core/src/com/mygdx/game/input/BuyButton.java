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

    public BuyButton(HeadGenerator gen, String path){
        generator = gen;

        texture = new Texture(path);
        sprite = new Sprite(texture);

        int bottomMargin = (int) (Gdx.graphics.getHeight() - texture.getHeight()) / 2;

        sprite.setPosition(0, bottomMargin);
        sprite.setOrigin(0, bottomMargin);
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
