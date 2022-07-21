package com.mygdx.game;

import java.util.ArrayList;

public abstract class Stock {
    protected long currencyInStock;
    protected long totalCurrencyGenerated;
    protected ArrayList<Generator> lockedGenerators;
    protected ArrayList<Generator> unlockedGenerators;
}
