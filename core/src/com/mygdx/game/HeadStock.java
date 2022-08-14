package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.base.Stock;
import com.mygdx.game.hurdygurdy.HurdyGurdy;

public class HeadStock extends Stock {

    private HeadGeneratorManager headGeneratorManager;
    private HurdyGurdy hurdyGurdy;
    private boolean hurdyGurdyIsBoosting = false;
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
    public void passTime(float deltaTime) {
        if (hurdyGurdy != null) {
            if (hurdyGurdy.isPlaying() != hurdyGurdyIsBoosting) {
                hurdyGurdyIsBoosting = hurdyGurdy.isPlaying();
                if (hurdyGurdyIsBoosting) {
                    addBoost(2);
                } else {
                    removeBoost(2);
                }
            }
        }
        super.passTime(deltaTime);
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

    public HurdyGurdy getHurdyGurdy() {
        return hurdyGurdy;
    }

    public void setHurdyGurdy(HurdyGurdy hurdyGurdy) {
        this.hurdyGurdy = hurdyGurdy;
    }
}
