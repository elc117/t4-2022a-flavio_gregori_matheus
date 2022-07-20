package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector3;

public class MyInputProcessor implements InputProcessor{

    private Guillotine guillotine;
    private MyGdxGame game;

    public MyInputProcessor(Guillotine gui, MyGdxGame gam){
        game = gam;
        guillotine = gui;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Buttons.LEFT){
            Vector3 gamePosition = game.camera.unproject(new Vector3(screenX, screenY, 0));
            if(guillotine.mouseClick((int)gamePosition.x, (int) gamePosition.y)){

                game.addPoints();

            }
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
