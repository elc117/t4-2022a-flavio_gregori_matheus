package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.base.Generator;

public class HeadGenerator extends Generator {
    private String name;
    public Sprite sprite;
    private HeadStock headStock;
    private Label priceLabel;
    private Label amountLabel;

    public HeadGenerator(long headsPerSecond, int amount, long price, long amountToUnlock, HeadStock headStock, String name, String iconPath) {
        super(headsPerSecond, amount, price, amountToUnlock);
        this.name = name;
        Texture texture = new Texture(iconPath);
        sprite = new Sprite(texture);
        this.headStock = headStock;
    }

    public Button generateButton(Skin skin) {
        VerticalGroup verticalGroup = new VerticalGroup();

        amountLabel = new Label(amountText(), skin);

        Label nameLabel = new Label(name, skin);
        nameLabel.setAlignment(Align.right);
        verticalGroup.addActor(nameLabel);

        priceLabel = new Label(String.valueOf(buyPrice), skin);
        priceLabel.setAlignment(Align.right);
        verticalGroup.addActor(priceLabel);

        verticalGroup.fill();

        Button button = new Button(skin);
        button.add(amountLabel).spaceRight(5);
        button.add(verticalGroup);
        button.add(new Image(sprite));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onClick();
            }
        });
        return button;
    }

    public void onClick() {
        buy(headStock);
        priceLabel.setText(buyPriceText());
        amountLabel.setText(amountText());
    }

    private String buyPriceText() {
        return String.valueOf(buyPrice);
    }

    private String amountText() {
        return "x" + amount;
    }
}
