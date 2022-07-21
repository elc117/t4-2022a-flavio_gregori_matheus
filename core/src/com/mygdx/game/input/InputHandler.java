package com.mygdx.game.input;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class InputHandler implements InputProcessor {
    private MyGdxGame game;

    private final ArrayList<Button> buttons = new ArrayList<>();
    public final HashMap<Character, Action> keyActions = new HashMap<>();

    public InputHandler(MyGdxGame gam){
        game = gam;
    }

    @Override
    public boolean keyDown(int keycode) {
        
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        Action action = keyActions.get(character);
        if (action != null) {
            action.act();
        }
        return false;
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Buttons.LEFT){
            Vector3 gamePosition3D = game.camera.unproject(new Vector3(screenX, screenY, 0));
            buttons.stream().filter(b -> b.getRectangle().contains(gamePosition3D.x, gamePosition3D.y)).forEach(Button::onClick);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
