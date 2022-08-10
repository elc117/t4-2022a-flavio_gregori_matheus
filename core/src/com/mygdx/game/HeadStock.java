package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.base.Stock;

public class HeadStock extends Stock {

    private HeadGeneratorManager headGeneratorManager;
    private Label currentHeadsInStock;

    public HeadStock() {
        super();
    }

    public Label generateHeadsLabel(Skin skin) {
        currentHeadsInStock = new Label(getHeadsLabelText(), skin);
        return currentHeadsInStock;
    }

    private String getHeadsLabelText() {
        return "Cabeças: " + currencyInStock;
    }

    @Override
    public void passSecond() {
        super.passSecond();
        currentHeadsInStock.setText(getHeadsLabelText());
    }

    @Override
    public void click() {
        super.click();
        currentHeadsInStock.setText(getHeadsLabelText());
        headGeneratorManager.updateUnlockedGenerators(totalCurrencyGenerated, currencyInStock);
    }

    @Override
    public void charge(long amount) {
        super.charge(amount);
        currentHeadsInStock.setText(getHeadsLabelText());
    }

    public HeadGeneratorManager getHeadGeneratorManager() {
        return headGeneratorManager;
    }

    public void setHeadGeneratorManager(HeadGeneratorManager headGeneratorManager) {
        super.setGeneratorManager(headGeneratorManager);
        this.headGeneratorManager = headGeneratorManager;
    }
}
