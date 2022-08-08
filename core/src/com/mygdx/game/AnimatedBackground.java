package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class AnimatedBackground extends BaseDrawable {

    private final Animation<TextureRegion> animation;
    private float stateTime = 0;
    private boolean animating = false;

    public AnimatedBackground(Animation<TextureRegion> animation) {

        this.animation = animation;
        TextureRegion key = animation.getKeyFrame(0);

        this.setLeftWidth(key.getRegionWidth() / 2.0f);
        this.setRightWidth(key.getRegionWidth() / 2.0f);
        this.setTopHeight(key.getRegionHeight() / 2.0f);
        this.setBottomHeight(key.getRegionHeight() / 2.0f);
        this.setMinWidth(key.getRegionWidth());
        this.setMinHeight(key.getRegionHeight());

    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (animation.isAnimationFinished(stateTime)) {
            animating = false;
            stateTime = 0;
        } else if (animating) {
            stateTime += Gdx.graphics.getDeltaTime();
        }

        TextureRegion keyFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(keyFrame, x, y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
    }

    public void startAnimation() {
        animating = true;
    }
}
