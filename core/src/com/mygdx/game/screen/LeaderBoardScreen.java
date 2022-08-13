package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.leaderboard.GetScoreResponseListener;
import com.mygdx.game.leaderboard.LeaderBoard;
import com.mygdx.game.leaderboard.Score;
import com.mygdx.game.util.Util;

import java.util.ArrayList;

public class LeaderBoardScreen extends ScreenAdapter {
    private Screen previousScreen;
    private LeaderBoard leaderBoard;
    private Game game;
    private Stage stage;
    private Skin skin;
    private Camera camera;
    private VerticalGroup scoresGroup;

    public LeaderBoardScreen(String gameId, Screen previousScreen, Game game) {
        leaderBoard = new LeaderBoard(gameId);
        this.previousScreen = previousScreen;
        this.game = game;

        skin = Util.createSkin();

        Table root = new Table();
        root.setFillParent(true);

        camera = Util.createCamera();
        Viewport viewport = Util.createViewport(camera);

        stage = new Stage(viewport);
        stage.addActor(root);

        scoresGroup = new VerticalGroup();

        Button button = new Button(skin);
        button.add("Back");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(previousScreen);
            }
        });

        root.add(scoresGroup);
        root.row();
        root.add(button).expandY().bottom();
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.47f, 0.59f, 0.77f, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        leaderBoard.getScores(new GetScoreResponseListener() {
            @Override
            public void handleHttpResponse(boolean success, ArrayList<Score> scores) {
                if (success) {
                    scoresGroup.clear();
                    for (Score score : scores) {
                        Label scoreLabel = new Label(score.userId + ": " + score.score, skin);
                        scoresGroup.addActor(scoreLabel);
                    }
                }
            }

            @Override
            public void failed(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void cancelled() {

            }
        });
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
