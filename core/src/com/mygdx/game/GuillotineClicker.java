package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screen.ClickerScreen;

public class GuillotineClicker extends ApplicationAdapter {
	private ClickerScreen clickerScreen;
	private Screen currentScreen;
	@Override
	public void create() {
		clickerScreen = new ClickerScreen();
		currentScreen = clickerScreen;
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	@Override
	public void render() {
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {
		currentScreen.pause();
	}

	@Override
	public void resume() {
		currentScreen.resume();
	}

	@Override
	public void dispose() {
		clickerScreen.dispose();
	}
}
