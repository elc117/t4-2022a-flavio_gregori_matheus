package com.mygdx.game.base;

import java.util.ArrayList;

public abstract class Stock {
    protected long currencyInStock;
    protected long totalCurrencyGenerated;
    protected Clicable clicable;
    protected final ArrayList<Generator> lockedGenerators = new ArrayList<>();
    protected final ArrayList<Generator> unlockedGenerators = new ArrayList<>();

    public void click() {
        currencyInStock += clicable.generate(this);
    }

    public void passSecond() {
        long generatedThisSecond = unlockedGenerators.stream().mapToLong(g -> g.generate(this)).sum();
        currencyInStock += generatedThisSecond;
        totalCurrencyGenerated += generatedThisSecond;
    }

    public void setClicable(Clicable clicable) {
        this.clicable = clicable;
    }
    
    public ArrayList<Generator> getUnlockedGenerators() {
        return unlockedGenerators;
    }

    public ArrayList<Generator> getLockedGenerators() {
        return lockedGenerators;
    }

    public long getCurrencyInStock() {
        return currencyInStock;
    }
}
