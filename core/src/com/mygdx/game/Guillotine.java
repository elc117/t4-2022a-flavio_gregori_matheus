package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.base.Clicable;
import com.mygdx.game.input.Button;
import com.badlogic.gdx.Gdx;

public class Guillotine extends Clicable implements Button {
    private Sprite guillotineSprite;
    private Texture guillotineTexture;
    private HeadStock stock;

    public Guillotine(HeadStock headStock){
        super(1);

        stock = headStock;

		guillotineTexture = new Texture("crop/01.png");

		guillotineSprite = new Sprite(guillotineTexture);

        int bottomMargin = (int) (Gdx.graphics.getHeight() - guillotineTexture.getHeight()) / 2;
        guillotineSprite.setPosition(50, bottomMargin);
        guillotineSprite.setOrigin(50, bottomMargin);
    }

    public void draw(SpriteBatch batch){
        guillotineSprite.draw(batch);
    }

    public Sprite getSprite() {
        return guillotineSprite;
    }

    @Override
    public void onClick() {
        stock.click();
    }

    @Override
    public Rectangle getRectangle() {
        return guillotineSprite.getBoundingRectangle();
    }
}