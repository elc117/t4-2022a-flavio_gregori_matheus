package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screen.ClickerScreen;
import com.mygdx.game.screen.LeaderBoardScreen;

import java.util.HashMap;

public class GuillotineClicker extends Game {
	private ClickerScreen clickerScreen;
	private LeaderBoardScreen leaderBoardScreen;
	private final HashMap<Screen, Float> inactiveScreens = new HashMap<>();
	@Override
	public void create() {
		clickerScreen = new ClickerScreen(this);
		leaderBoardScreen = new LeaderBoardScreen("testGame", clickerScreen, this);
		clickerScreen.setLeaderBoardScreen(leaderBoardScreen);
		setScreen(clickerScreen);
	}

	@Override
	public void setScreen(Screen screen) {
		inactiveScreens.put(this.screen, 0f);
		super.setScreen(screen);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		inactiveScreens.replaceAll((s, time) -> time + deltaTime);
		if (screen != null) {
			Float inactiveTime = inactiveScreens.get(screen);
			if (inactiveTime == null) {
				screen.render(deltaTime);
			} else {
				screen.render(inactiveTime);
				inactiveScreens.remove(screen);
			}
		}
	}

	@Override
	public void dispose() {
		clickerScreen.dispose();
		leaderBoardScreen.dispose();
	}
}
