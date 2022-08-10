package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.game.base.GeneratorManager;

import java.util.List;

public class HeadGeneratorManager extends GeneratorManager<HeadGenerator> {

    private VerticalGroup buttonGroup;
    private Skin buttonSkin;

    public HeadGeneratorManager(HeadStock stock, Skin skin) {
        HeadGenerator hunter = new HeadGenerator(1, 0, 15, 0, stock, "Caçador", "buttons/hunterButton.png");
        HeadGenerator goblin = new HeadGenerator(3, 0, 100, 200, stock, "Goblin", "buttons/hunterButton.png");
        HeadGenerator orc = new HeadGenerator(9, 0, 1100, 1500, stock, "Orc", "buttons/hunterButton.png");
        HeadGenerator sacrifice = new HeadGenerator(25, 0, (long) 12E+3, (long) 15E+3, stock, "Sacrifícios", "buttons/hunterButton.png");
        HeadGenerator feudalAttack = new HeadGenerator(100, 0, (long) 13E+4, (long) 15E+4, stock, "Ataque ao feudo", "buttons/hunterButton.png");
        HeadGenerator hydra = new HeadGenerator(250, 0, (long) 14E+6, (long) 16E+6, stock, "Caça às hidras", "buttons/hunterButton.png");

        lockedGenerators.add(hunter);
        lockedGenerators.add(goblin);
        lockedGenerators.add(orc);
        lockedGenerators.add(sacrifice);
        lockedGenerators.add(feudalAttack);
        lockedGenerators.add(hydra);
        this.buttonSkin = skin;
        buttonGroup = new VerticalGroup().fill();
        buttonGroup.space(5);

        updateUnlockedGenerators(0, 0);
    }

    @Override
    public List<HeadGenerator> updateUnlockedGenerators(long totalCurrencyGenerated, long currencyInStock) {
        List<HeadGenerator> newlyAdded = super.updateUnlockedGenerators(totalCurrencyGenerated, currencyInStock);
        for (HeadGenerator headGenerator : newlyAdded) {
            Button button = headGenerator.generateButton(buttonSkin);
            button.right();
            headGenerator.setButton(button);
            buttonGroup.addActor(button);
        }
        for (HeadGenerator headGenerator : unlockedGenerators) {
            headGenerator.getButton().setDisabled(headGenerator.getBuyPrice() > currencyInStock);
        }
        return newlyAdded;

    }

    public VerticalGroup getButtonGroup() {
        return buttonGroup;
    }
}
