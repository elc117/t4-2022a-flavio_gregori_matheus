package com.mygdx.game;

import com.mygdx.game.base.Stock;

public class HeadStock extends Stock {

    private HeadGeneratorManager headGeneratorManager;

    public HeadStock() {
        super();
    }

    public HeadGeneratorManager getHeadGeneratorManager() {
        return headGeneratorManager;
    }

    public void setHeadGeneratorManager(HeadGeneratorManager headGeneratorManager) {
        super.setGeneratorManager(headGeneratorManager);
        this.headGeneratorManager = headGeneratorManager;
    }
}
