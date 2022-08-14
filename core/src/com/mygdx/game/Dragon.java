package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.base.FlyingBoost;
import com.mygdx.game.screen.ClickerScreen;

public class Dragon extends FlyingBoost {
    private HeadStock headStock;
    private AnimatedBackground animatedBackground;
    private Button flyingButton;
    private Table overlay;
    private Vector2 position = new Vector2(0, 0);
    private Vector2 speed = new Vector2(50, 50);
    private Stage stage;
    Action moveAction;

    public Dragon(HeadStock headStock, Skin skin) {
        super(2);
        this.headStock = new HeadStock();
        TextureRegion[] frames = new TextureRegion[8];
        Texture img = new Texture("dragon_sprite.png");

        TextureRegion[][] tmpframes = TextureRegion.split(img, 32, 32);
        System.arraycopy(tmpframes[0], 0, frames, 0, 8);

        Animation<TextureRegion> animation = new Animation<>(0.12f, frames);
        animatedBackground = new AnimatedBackground(animation);
        animatedBackground.loop();
        animatedBackground.startAnimation();

        flyingButton = new Button(skin);
        moveAction = new Action() {
            @Override
            public boolean act(float delta) {
                return move(delta);
            }
        };
        flyingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dragon.this.applyBoost(headStock);
                flyingButton.clearActions();
                position.setZero();
                flyingButton.setPosition(0, 0);
                DelayAction delay = new DelayAction(5);
                delay.setAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        Dragon.this.removeBoost(headStock);
                        System.out.println("Fim boost");
                        Dragon.this.removeFromStage(stage);
                        flyingButton.clearActions();
                        return true;
                    }
                });
                flyingButton.addAction(delay);
            }
        });
        flyingButton.addAction(moveAction);
        flyingButton.setPosition(0, 0);
        flyingButton.add(new Image(animatedBackground));

        overlay = new Table(skin);
        overlay.setFillParent(true);
        overlay.add(flyingButton);
    }

    public void addToStage(Stage stage) {
        if (this.stage != stage) {
            this.stage = stage;
            stage.addActor(overlay);
            flyingButton.clearActions();
            flyingButton.addAction(moveAction);
        }
    }

    public void removeFromStage(Stage stage) {
        this.stage = null;
        overlay.remove();
    }

    private boolean move(float delta) {
        position.add(speed.cpy().scl(delta));
        flyingButton.setPosition(position.x, position.y);
        if ((position.x > stage.getCamera().viewportWidth) || (position.y > stage.getCamera().viewportHeight)) {
            position.setZero();
            removeFromStage(stage);
            return true;
        }
        return false;
    }
}
