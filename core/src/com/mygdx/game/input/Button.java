package com.mygdx.game.input;

import java.util.function.Function;
import java.util.function.Supplier;

import com.badlogic.gdx.math.Rectangle;

public interface Button {
    public Rectangle getRectangle();
    public void onClick(); 
}
