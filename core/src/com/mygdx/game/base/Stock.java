package com.mygdx.game.base;

import java.util.ArrayList;

public abstract class Stock {
    protected long currencyInStock;
    protected float actualCurrencyInStock;
    protected long totalCurrencyGenerated;
    protected float actualCurrencyGenerated;
    protected Clickable clickable;
    protected GeneratorManager<?> generatorManager;
    protected final ArrayList<Float> boosts = new ArrayList<>();
    protected float boostModifier = 1;

    public Stock() {
        actualCurrencyInStock = 0;
        actualCurrencyGenerated = 0;
        updateCurrency();
    }

    protected void updateCurrency() {
        currencyInStock = (long) actualCurrencyInStock;
        totalCurrencyGenerated = (long) actualCurrencyGenerated;
    }

    public void click() {
        long generated = clickable.generate(this);
        generated *= boostModifier;
        actualCurrencyInStock += generated;
        actualCurrencyGenerated += generated;
        updateCurrency();
    }

    public void passTime(float deltaTime) {
        long generatedInASecond = generatorManager.generate(this);
        float generated = (generatedInASecond * deltaTime);
        generated *= boostModifier;
        actualCurrencyGenerated += generated;
        actualCurrencyInStock += generated;
        updateCurrency();
        generatorManager.updateUnlockedGenerators(totalCurrencyGenerated, currencyInStock);
    }

    public void addBoost(float boost) {
        boosts.add(boost);
        updateBoostModifier();
    }

    public void removeBoost(float boost) {
        boosts.remove(boost);
        updateBoostModifier();
    }

    protected void updateBoostModifier() {
        boostModifier = boosts.stream().reduce(1f, (a, b) -> a * b);
    }

    public void setClickable(Clickable clickable) {
        this.clickable = clickable;
    }

    public Clickable getClickable() {
        return clickable;
    }

    public void setGeneratorManager(GeneratorManager<?> generatorManager) {
        this.generatorManager = generatorManager;
    }

    public long getCurrencyInStock() {
        return currencyInStock;
    }

    public long getTotalCurrencyGenerated() {
        return totalCurrencyGenerated;
    }

    public void charge(long amount) {
        actualCurrencyInStock -= amount;
        updateCurrency();
    }
}
