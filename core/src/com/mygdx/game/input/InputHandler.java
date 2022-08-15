package com.mygdx.game.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.util.Action;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class InputHandler implements InputProcessor {
    private final Stage stage;
    public final HashMap<Character, Action> keyActions = new HashMap<>();
    private final Set<KeyHandler> keyHandlers = new LinkedHashSet<>();
    private final Set<Button> buttons = new LinkedHashSet<>();

    public InputHandler(Stage stage){
        this.stage = stage;
    }

    @Override
    public boolean keyDown(int keycode) {
        keyHandlers.forEach(keyHandler -> keyHandler.keyDown(keycode));
        return stage.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        keyHandlers.forEach(keyHandler -> keyHandler.keyUp(keycode));
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

    public <T extends KeyHandler> void addKeyHandler(T keyHandler) {
        keyHandlers.add(keyHandler);
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
