package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.base.Clickable;
import com.mygdx.game.util.Util;

public class Guillotine extends Clickable {
    private HeadStock stock;
    private AnimatedDrawable animatedBackground;
    private Texture head;

    public Guillotine(HeadStock headStock) {
        super(1);

        stock = headStock;
        head = new Texture("head.png");

        animatedBackground = Util.animation("guillotine/guillotine_frame_gory_sheet.png", 44, 96, 12, 0.07f);
        animatedBackground.setScale(3);
    }

    public void onClick(float x, float y) {
        animatedBackground.startAnimation();
        stock.click();
    }

    public Button generateButton(Skin skin) {
        Button button = new Button(skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick(x, y);
                button.addActor(createHeadActor(head, skin, x, y));
            }
        });
        Image image = new Image();
        image.setDrawable(animatedBackground);
        button.add(image);

        return button;
    }

    private Actor createHeadActor(Texture head, Skin skin, float x, float y) {
        Image headImage = new Image(head);
        headImage.setPosition(x - (head.getWidth() * 0.5f), y - (head.getHeight() * 0.5f));

        Label label = new Label("+" + stock.getPreviouslyGeneratedByClick(), skin, "white");
        label.setFontScale(0.75f);
        label.setPosition(x + head.getWidth() * 0.5f, y - head.getHeight() * 0.5f);

        Group group = new Group();
        group.addActor(headImage);
        group.addActor(label);

        MoveByAction moveByAction = new MoveByAction();
        moveByAction.setAmountY(1);

        RepeatAction repeatAction = new RepeatAction();
        repeatAction.setAction(moveByAction);
        repeatAction.setCount(head.getHeight());

        RemoveActorAction removeActorAction = new RemoveActorAction();
        removeActorAction.setTarget(group);

        SequenceAction sequenceAction = new SequenceAction(repeatAction, removeActorAction);

        group.addAction(sequenceAction);

        return group;
    }
}