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
        long generatedThisSecond = generators.getUnlockedGenerators().stream().mapToLong(g -> g.generate(this)).sum();
        currencyInStock += generatedThisSecond;
        totalCurrencyGenerated += generatedThisSecond;
        generators.attGenerators(totalCurrencyGenerated);
    }

    public void setClicable(Clicable clicable) {
        this.clicable = clicable;
    }
    
    public ArrayList<HeadGenerator> getUnlockedGenerators() {
        return generators.getUnlockedGenerators();
    }

    public ArrayList<HeadGenerator> getLockedGenerators() {
        return generators.getLockeHeadGenerators();
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
