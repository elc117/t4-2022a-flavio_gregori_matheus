package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Dragon {
    private Sprite sprite;
    private Texture img;
    private float time;
    private Animation<TextureRegion> animation;

    public Dragon() {
        TextureRegion[] frames = new TextureRegion[8];
        img = new Texture("dragon_sprite.png");

        TextureRegion[][] tmpframes = TextureRegion.split(img, 32, 32);
        System.arraycopy(tmpframes[0], 0, frames, 0, 8);

        animation = new Animation<>(0.12f, frames);
        sprite = new Sprite(frames[0]);

        sprite.setPosition(50, 20);
        sprite.setOrigin(50, 20);

    }

    public void draw(SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(time, true));

        sprite.draw(batch);
    }
}
