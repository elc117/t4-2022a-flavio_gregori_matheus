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
    private Sprite sprite;
    private HeadStock headStock;
    private Label priceLabel;
    private Label amountLabel;
    private Button button;

    public HeadGenerator(long headsPerSecond, int amount, long price, long amountToUnlock, HeadStock headStock, String name, String iconPath) {
        super(headsPerSecond, amount, price, amountToUnlock);
        this.name = name;
        Texture texture = new Texture(iconPath);
        sprite = new Sprite(texture);
        this.headStock = headStock;
    }

    public Button generateButton(Skin skin) {
        VerticalGroup verticalGroup = new VerticalGroup();

        amountLabel = new Label(amountText(), skin, "amount");
        amountLabel.setAlignment(Align.left);

        Label nameLabel = new Label(name, skin, "name");
        nameLabel.setAlignment(Align.right);
        verticalGroup.addActor(nameLabel);

        priceLabel = new Label(String.valueOf(buyPrice), skin, "price");
        priceLabel.setAlignment(Align.right);
        verticalGroup.addActor(priceLabel);

        verticalGroup.fill();

        Button button = new Button(skin, "generator");
        button.padLeft(5).padRight(5).padTop(2).padBottom(2);
        button.add(amountLabel).left().expand();
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
        headStock.getHeadGeneratorManager().updateUnlockedGenerators(headStock.getTotalCurrencyGenerated(), headStock.getCurrencyInStock());
    }

    private String buyPriceText() {
        return String.valueOf(buyPrice);
    }

    private String amountText() {
        return amount == 0 ? "" : String.valueOf(amount);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
