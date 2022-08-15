package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.*;
import com.mygdx.game.hurdygurdy.HurdyGurdy;
import com.mygdx.game.input.InputHandler;
import com.mygdx.game.leaderboard.PostScoreResponseListener;
import com.mygdx.game.util.Util;

public class ClickerScreen extends ScreenAdapter {
    private HeadStock stock;
    private BitmapFont font;
    private Camera camera;
    private Viewport viewport;
    private Guillotine guillotine;
    private Dragon dragon;
    HurdyGurdy hurdyGurdy;
    private Stage stage;
    private InputHandler inputHandler;
    private LeaderBoardScreen leaderBoardScreen;
    private Window leaderBoardNamePopup;
    GuillotineClicker guillotineClicker;
    private float timeWithoutPlaying = 0;
    private final Music backgroundMusic;
    private boolean canSubmitScore = true;
    Button submitScoreButton;
    public ClickerScreen(GuillotineClicker guillotineClicker) {
        this.guillotineClicker = guillotineClicker;
        font = Util.createFont();
        Skin skin = Util.createSkin();
        camera = Util.createCamera();
        viewport = Util.createViewport(camera);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Medieval_Song_-_Eric_VINCENT.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        stock = new HeadStock();
        stock.setHeadGeneratorManager(new HeadGeneratorManager(stock, skin));
        guillotine = new Guillotine(stock);
        stock.setClickable(guillotine);
        dragon = new Dragon(stock, skin);
        hurdyGurdy = new HurdyGurdy();
        stock.setHurdyGurdy(hurdyGurdy);

        leaderBoardNamePopup = new Window("", skin);
        TextField textField = new TextField("", skin);
        textField.setMessageText("Qual o seu nome?");
        leaderBoardNamePopup.setFillParent(true);
        leaderBoardNamePopup.add(textField).pad(5);

        Button submitButton = new Button(skin);
        submitButton.add("Enviar");
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                leaderBoardScreen.getLeaderBoard().postScore(textField.getText(), String.valueOf(stock.getTotalCurrencyGenerated()), new PostScoreResponseListener() {
                    @Override
                    public void handleHttpResponse(boolean success, String message) {
                        if (success) {
                            System.out.println(message);
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
                leaderBoardNamePopup.remove();
                hurdyGurdy.setShouldPlay(true);
            }
        });
        leaderBoardNamePopup.row();
        leaderBoardNamePopup.add(submitButton);

        stage = createStage(skin);

        RepeatAction repeat = new RepeatAction();
        repeat.setCount(RepeatAction.FOREVER);
        DelayAction delayAction = new DelayAction(5);
        delayAction.setAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (spawnDragon()) {
                    dragon.addToStage(stage);
                }
                return true;
            }
        });
        repeat.setAction(delayAction);
        stage.addAction(repeat);

        inputHandler = new InputHandler(stage);
        inputHandler.keyActions.put('g', () -> {
            guillotine.permanentBoost();
            canSubmitScore = false;
            if (submitScoreButton != null) {
                submitScoreButton.remove();
            }
        }); /*For testing purposes*/
        inputHandler.addKeyHandler(hurdyGurdy);
    }

    private boolean spawnDragon() {
        return !leaderBoardNamePopup.hasParent();
    }

    private Stage createStage(Skin skin) {
        Stage stage = new Stage();
        stage.setViewport(viewport);

        Table rootTable = new Table();
        rootTable.background(skin.getDrawable("background"));
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        Button guillotineButton = guillotine.generateButton(skin);
        Label headsLabel = stock.generateHeadsLabel(skin);
        headsLabel.setAlignment(Align.right);

        submitScoreButton = new Button(skin);
        submitScoreButton.add("Enviar pontuação");
        submitScoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.addActor(leaderBoardNamePopup);
                hurdyGurdy.setShouldPlay(false);
            }
        });

        Button showLeaderBoardButton = new Button(skin);
        showLeaderBoardButton.add("Mostrar leaderboard");
        showLeaderBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (leaderBoardScreen != null) {
                    guillotineClicker.setScreen(leaderBoardScreen);
                }
            }
        });

        Table rightColumn = new Table();
        rightColumn.top();

        rightColumn.add(headsLabel).fillX().spaceBottom(20);
        rightColumn.row();
        rightColumn.add(stock.getHeadGeneratorManager().getButtonGroup());
        rightColumn.row();
        rightColumn.add(submitScoreButton).expandY().bottom();
        rightColumn.row();
        rightColumn.add(showLeaderBoardButton).bottom();

        rootTable.add(guillotineButton).expand();
        rootTable.add(rightColumn).fillY();
        rootTable.pad(10);
        return stage;
    }

    public void setLeaderBoardScreen(LeaderBoardScreen leaderBoardScreen) {
        this.leaderBoardScreen = leaderBoardScreen;
    }

    private void gameIteration(float deltaTime) {
        stock.passTime(deltaTime);
    }

    @Override
    public void render(float deltaTime) {
        if (hurdyGurdy.isPlaying()) {
            backgroundMusic.stop();
        } else {
            if (!backgroundMusic.isPlaying()) {
                if (timeWithoutPlaying >= 2.5) {
                    backgroundMusic.play();
                    timeWithoutPlaying = 0;
                } else {
                    timeWithoutPlaying += deltaTime;
                }
            }
        }
        gameIteration(deltaTime);

        ScreenUtils.clear(0.47f, 0.59f, 0.77f, 1);
        stage.act(deltaTime);
        stage.draw();
    }

    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void dispose() {
        font.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
