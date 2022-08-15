package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AnimatedDrawable;
import com.mygdx.game.leaderboard.GetScoreResponseListener;
import com.mygdx.game.leaderboard.LeaderBoard;
import com.mygdx.game.leaderboard.Score;
import com.mygdx.game.util.Util;

import java.util.ArrayList;

public class LeaderBoardScreen extends ScreenAdapter {
    private final Screen previousScreen;
    private final LeaderBoard leaderBoard;
    private final Game game;
    private final Stage stage;
    private final Skin skin;
    private final Cell<?> mainCell;
    private final Table scoresTable;
    private final Image loading;
    private final Label errorMessage;

    public LeaderBoardScreen(String gameId, Screen previousScreen, Game game) {
        leaderBoard = new LeaderBoard(gameId);
        this.previousScreen = previousScreen;
        this.game = game;

        skin = Util.createSkin();

        Table root = new Table();
        root.background(skin.getDrawable("background"));
        root.setFillParent(true);

        Camera camera = Util.createCamera();
        Viewport viewport = Util.createViewport(camera);

        stage = new Stage(viewport);
        stage.addActor(root);

        scoresTable = new Table(skin);
        scoresTable.background(skin.getDrawable("blackTransparentRectangle"));
        scoresTable.pad(10);

        AnimatedDrawable anim = Util.animation("loading.png", 64, 64, 4, 0.125f);
        anim.loop();
        anim.startAnimation();
        loading = new Image(anim);
        errorMessage = new Label("An unexpected error has occurred", skin);

        Button button = new Button(skin);
        button.add("Back");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(previousScreen);
            }
        });

        mainCell = root.add(loading).expand().center();
        root.row();
        root.add(button).bottom().center().colspan(3);
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
        mainCell.setActor(loading);
        leaderBoard.getScores(new GetScoreResponseListener() {
            @Override
            public void handleHttpResponse(boolean success, ArrayList<Score> scores) {
                if (success) {
                    mainCell.setActor(scoresTable);
                    scoresTable.clear();
                    addActorToTable(new Label("Jogador", skin, "white"));
                    addActorToTable(new Label("Total de cabeças", skin, "white"));
                    scoresTable.row();
                    for (Score score : scores) {
                        for (Actor actor : createActorsLine(score, skin)) {
                            addActorToTable(actor);
                        }
                        scoresTable.row();
                    }
                }
            }

            private void addActorToTable(Actor actor) {
                scoresTable.add(actor).pad(5).fillX().expand();
            }

            @Override
            public void failed(Throwable t) {
                mainCell.setActor(errorMessage);
                t.printStackTrace();
            }

            @Override
            public void cancelled() {
                mainCell.setActor(errorMessage);
            }
        }, 10);
    }

    private Actor[] createActorsLine(Score score, Skin skin) {
        Label scoreLabel = new Label(score.score, skin, "white");
        scoreLabel.setAlignment(Align.left);
        Label userLabel = new Label(score.userId, skin, "white");
        scoreLabel.setAlignment(Align.left);

        return new Actor[] {userLabel, scoreLabel};
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
