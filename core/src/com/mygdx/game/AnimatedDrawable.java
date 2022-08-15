package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class AnimatedDrawable extends BaseDrawable {

    private final Animation<TextureRegion> animation;
    private float animationTime = 0;
    private boolean animating = false;
    private boolean loop = false;
    private float width;
    private float height;

    private float scale;

    public AnimatedDrawable(Animation<TextureRegion> animation) {

        this.animation = animation;
        TextureRegion key = animation.getKeyFrame(0);

        width = key.getRegionWidth();
        height = key.getRegionHeight();
        setScale(1);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (!loop && animation.isAnimationFinished(animationTime)) {
            animating = false;
            animationTime = 0;
        } else if (animating) {
            animationTime += Gdx.graphics.getDeltaTime();
        }

        TextureRegion keyFrame = animation.getKeyFrame(animationTime, true);

        batch.draw(keyFrame, x, y, keyFrame.getRegionWidth() * scale, keyFrame.getRegionHeight() * scale);
    }

    public void startAnimation() {
        animating = true;
    }

    public boolean isAnimating() {
        return animating;
    }

    public void loop() {
        loop = true;
    }

    public void setScale(float scale) {
        this.scale = scale;
        float drawWidth = width * scale;
        float drawHeight = height * scale;

        this.setLeftWidth(drawWidth / 2.0f);
        this.setRightWidth(drawWidth / 2.0f);
        this.setTopHeight(drawHeight / 2.0f);
        this.setBottomHeight(drawHeight / 2.0f);
        this.setMinWidth(drawWidth);
        this.setMinHeight(drawHeight);
    }
}
