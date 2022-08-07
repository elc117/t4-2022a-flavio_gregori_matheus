package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.base.Generator;
import com.mygdx.game.input.Button;

public class HeadGenerator extends Generator implements Button {
    private String name;
    private Texture texture;
    private Sprite sprite;
    private HeadStock headStock;

    public HeadGenerator(long headsPerSecond, int amount, long price, long amountToUnlock, HeadStock headStock, String name, String iconPath) {
        super(headsPerSecond, amount, price, amountToUnlock);
        this.name = name;
        texture = new Texture(iconPath);
        sprite = new Sprite(texture);
        this.headStock = headStock;
    }

    public String getName(){
        return name;
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
        aux.setText(font, name);

        font.draw(batch, aux, sprite.getOriginX() - aux.width, sprite.getOriginY() + texture.getHeight() - aux.height/2 );
    }

    private void drawPrice(SpriteBatch batch, BitmapFont font){
        GlyphLayout aux = new GlyphLayout();

        font.getData().setScale(0.5f, 0.5f);

        aux.setText(font, String.valueOf(getBuyPrice()));
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
        buy(headStock);
    }
}
