package com.mygdx.game.base;

import java.util.ArrayList;

import com.mygdx.game.HeadGenerator;
import com.mygdx.game.HeadGenerators;

public abstract class Stock {
    protected long currencyInStock;
    protected long totalCurrencyGenerated;
    protected Clicable clicable;
    protected HeadGenerators generators;

    public Stock(){
        currencyInStock = 0;
        totalCurrencyGenerated = 0;
        generators = new HeadGenerators();
        generators.init();
    }

    public void click() {
        currencyInStock += clicable.generate(this);
        totalCurrencyGenerated += clicable.generate(this);
    }

    public void passSecond() {
        long generatedThisSecond = generators.getGenerators().stream().mapToLong(g -> g.generate(this)).sum();
        currencyInStock += generatedThisSecond;
        totalCurrencyGenerated += generatedThisSecond;
        generators.attButtons(totalCurrencyGenerated);
    }

    public void setClicable(Clicable clicable) {
        this.clicable = clicable;
    }
    

    public long getCurrencyInStock() {
        return currencyInStock;
    }

    public HeadGenerators getHeadGenerators(){
        return generators;
    }

    public void chargeHeads(long amount){
        currencyInStock -= amount;
    }
}
