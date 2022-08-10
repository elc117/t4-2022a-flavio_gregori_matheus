package com.mygdx.game.base;

import com.mygdx.game.HeadGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneratorManager<T extends Generator> {
    protected ArrayList<T> unlockedGenerators = new ArrayList<>();
    protected ArrayList<T> lockedGenerators = new ArrayList<>();

    public List<T> updateUnlockedGenerators(long totalCurrencyGenerated, long currencyInStock) {
        List<T> newlyUnlocked = lockedGenerators.stream()
                .filter(generator -> generator.getAmountToUnlock() <= totalCurrencyGenerated)
                .collect(Collectors.toList());
        unlockedGenerators.addAll(newlyUnlocked);
        lockedGenerators.removeAll(newlyUnlocked);
        return newlyUnlocked;
    }

    public long generate(Stock stock) {
        return unlockedGenerators.stream().mapToLong(generator -> generator.generate(stock)).sum();
    }
}
