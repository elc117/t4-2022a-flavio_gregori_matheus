package com.mygdx.game.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Pools;
import com.mygdx.game.GuillotineClicker;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class InputHandler implements InputProcessor {
    private Stage stage;
    public final HashMap<Character, Action> keyActions = new HashMap<>();
    private final Set<Button> buttons = new LinkedHashSet<>();

    public InputHandler(Stage stage){
        this.stage = stage;
    }

    @Override
    public boolean keyDown(int keycode) {
        return stage.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return stage.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        Action action = keyActions.get(character);
        if (action != null) {
            action.act();
        }
        return stage.keyTyped(character);
    }

    public <T extends Button> void addButton(T button) {
        stage.getRoot().addActor(button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return stage.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return stage.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return stage.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return stage.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return stage.scrolled(amountX, amountY);
    }
}
