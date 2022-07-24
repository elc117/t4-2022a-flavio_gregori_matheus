package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.base.Clicable;
import com.mygdx.game.input.Button;
import com.badlogic.gdx.Gdx;

public class Guillotine extends Clicable implements Button {
    private Sprite sprite;
    private HeadStock stock;
    private TextureRegion[] frames;
    private float time;
    private Animation<TextureRegion> animation;
    private boolean animationState;

    public Guillotine(HeadStock headStock){
        super(1);

        stock = headStock;
        time = 0;
        animationState = false;

        frames = new TextureRegion[12];
        for(int i = 0; i < 12; i++){
            frames[i] = new TextureRegion( new Texture("guillotine/" + String.format("%02d", i+1) + ".png") );
        }

        animation = new Animation<TextureRegion>(0.07f, frames);

		sprite = new Sprite(frames[0]);

        int bottomMargin = (int) (Gdx.graphics.getHeight() - frames[0].getRegionHeight()) / 2;
        sprite.setPosition(50, bottomMargin);
        sprite.setOrigin(50, bottomMargin);
    }

    public void draw(SpriteBatch batch){
        if(animationState == true){
            time += Gdx.graphics.getDeltaTime();
            sprite.setRegion(animation.getKeyFrame(time, false));

            if(time >= animation.getAnimationDuration()){
                animationState = false;
            }

        }else{
            time = 0f;
        }

        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void onClick() {
        animationState = true;
        stock.click();
    }

    @Override
    public Rectangle getRectangle() {
        return sprite.getBoundingRectangle();
    }
}