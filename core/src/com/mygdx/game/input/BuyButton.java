package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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

    private void drawTextName(SpriteBatch batch, BitmapFont font){
        GlyphLayout aux = new GlyphLayout();
        aux.setText(font, text);
        
        font.draw(batch, aux, sprite.getOriginX() - aux.width, sprite.getOriginY() + texture.getHeight() - aux.height/2 );
    }

    private void drawPrice(SpriteBatch batch, BitmapFont font){
        GlyphLayout aux = new GlyphLayout();

        font.getData().setScale(0.5f, 0.5f);

        aux.setText(font, String.valueOf(generator.getBuyPrice()));
        font.draw(batch, aux, sprite.getOriginX() - aux.width, sprite.getOriginY() + aux.height );

        font.getData().setScale(1, 1);
    }

    public void draw(SpriteBatch batch, BitmapFont font){
        drawTextName(batch, font);
        drawPrice(batch, font);
       

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
