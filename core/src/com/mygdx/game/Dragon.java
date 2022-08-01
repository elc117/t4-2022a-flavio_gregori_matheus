package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.input.Button;

public class Dragon implements Button {
    private Sprite sprite;
    private TextureRegion[] frames;
    private Texture img;
    private float time;
    private Animation<TextureRegion> animation;

    public Dragon() {
        frames = new TextureRegion[8];
        img = new Texture("dragon_sprite.png");

        TextureRegion[][] tmpframes = TextureRegion.split(img, 32, 32);
        for (int i = 0; i < 8; i++) {
            frames[i] = tmpframes[0][i];
        }

        animation = new Animation<TextureRegion>(0.12f, frames);
        sprite = new Sprite(frames[0]);

        sprite.setPosition(50, 20);
        sprite.setOrigin(50, 20);

    }

    public void draw(SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(time, true));

        sprite.draw(batch);
    }

    @Override
    public Rectangle getRectangle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onClick() {
        // TODO Auto-generated method stub

    }

}
