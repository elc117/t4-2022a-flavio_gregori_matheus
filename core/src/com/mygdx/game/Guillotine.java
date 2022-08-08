package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.base.Clicable;

public class Guillotine extends Clicable {
    private HeadStock stock;
    private AnimatedBackground animatedBackground;

    public Guillotine(HeadStock headStock) {
        super(1);

        stock = headStock;

        TextureRegion[] frames = new TextureRegion[12];
        for (int i = 1; i <= 12; i++) {
            frames[i - 1] = new TextureRegion(new Texture(("guillotine/" + i + ".png")));
        }

        Animation<TextureRegion> animation = new Animation<>(0.07f, frames);
        animatedBackground = new AnimatedBackground(animation);
    }

    public void onClick() {
        animatedBackground.startAnimation();
        stock.click();
    }

    public Button generateButton(Skin skin) {
        Button button = new Button(skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onClick();
            }
        });
        Image image = new Image();
        image.setDrawable(animatedBackground);
        button.add(image);

        return button;
    }
}