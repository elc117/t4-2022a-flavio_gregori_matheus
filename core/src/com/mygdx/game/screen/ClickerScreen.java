package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Dragon;
import com.mygdx.game.Guillotine;
import com.mygdx.game.HeadGeneratorManager;
import com.mygdx.game.HeadStock;
import com.mygdx.game.input.InputHandler;
import com.mygdx.game.util.Util;

public class ClickerScreen extends ScreenAdapter {
    private boolean active = false;
    private HeadStock stock;
    private float timeSinceLastSecond = 0;
    private BitmapFont font;
    public Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Guillotine guillotine;
    private Dragon dragon;
    private Stage stage;

    public ClickerScreen() {
        batch = new SpriteBatch();
        font = createFont();
        Skin skin = createSkin();
        camera = createCamera();
        viewport = createViewport(camera);

        stock = new HeadStock();
        stock.setHeadGeneratorManager(new HeadGeneratorManager(stock, skin));
        guillotine = new Guillotine(stock);
        stock.setClicable(guillotine);
        dragon = new Dragon();

        stage = createStage(skin);

        InputHandler inputHandler = new InputHandler(stage);
        inputHandler.keyActions.put('g', guillotine::boost); /*For testing purposes*/
        Gdx.input.setInputProcessor(inputHandler);
    }

    private Skin createSkin() {
        Skin skin = new Skin();
        Texture circle = new Texture("circle.png");
        Texture rectangle = new Texture("rectangle.png");
        skin.add("whiteCircle", circle);
        skin.add("whiteRectangle", rectangle);
        skin.add("red", new Color(0.827f, 0.137f, 0.047f, 1));
        skin.add("brown", new Color(0.396f, 0.239f, 0.149f, 1));
        skin.add("darkBrown", new Color(0.349f, 0.184f, 0.133f, 1));
        skin.add("gray", new Color(0.49f, 0.471f, 0.384f, 1));
        skin.add("redRectangle", Util.coloredSprite(rectangle, skin.getColor("red")));
        skin.add("brownRectangle", Util.coloredSprite(rectangle, skin.getColor("brown")));
        skin.add("darkBrownRectangle", Util.coloredSprite(rectangle, skin.getColor("darkBrown")));
        skin.add("grayRectangle", Util.coloredSprite(rectangle, skin.getColor("gray")));
        skin.add("woodenBackground", new Texture("wooden_plank.png"));
        skin.add("darkWoodenBackground", new Texture("dark_wooden_plank.png"));
        skin.add("disabledWoodenBackground", new Texture("disabled_wooden_plank.png"));
        skin.add("background", new Texture("background.png"));

        Label.LabelStyle defaultLabelStyle = new Label.LabelStyle();
        defaultLabelStyle.font = font;
        defaultLabelStyle.fontColor = Color.BLACK;

        Button.ButtonStyle defaultButtonStyle = new Button.ButtonStyle();

        skin.add("default", defaultLabelStyle);
        skin.add("default", defaultButtonStyle);

        skin.add("amount", defaultLabelStyle);
        skin.add("price", defaultLabelStyle);
        skin.add("name", defaultLabelStyle);

        Button.ButtonStyle generatorButton = new Button.ButtonStyle(defaultButtonStyle);
        generatorButton.up = skin.getDrawable("woodenBackground");
        generatorButton.down = skin.getDrawable("darkWoodenBackground");
        generatorButton.disabled = skin.getDrawable("disabledWoodenBackground");
        generatorButton.pressedOffsetY = -1;

        skin.add("generator", generatorButton);

        return skin;
    }

    private Camera createCamera() {
        Camera camera = new OrthographicCamera(400, 400);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        return camera;
    }

    private Viewport createViewport(Camera camera) {
        Viewport viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.update();
        return viewport;
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

        Table rightColumn = new Table();
        rightColumn.top();

        rightColumn.add(headsLabel).fillX().spaceBottom(20);
        rightColumn.row();
        rightColumn.add(stock.getHeadGeneratorManager().getButtonGroup());

        rootTable.add(guillotineButton).expand();
        rootTable.add(rightColumn).fillY();
        rootTable.pad(10);
        return stage;
    }

    public BitmapFont createFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        return font;
    }

    private void gameIteration(float deltaTime) {
        stock.passTime(deltaTime);
    }

    @Override
    public void render(float deltaTime) {
//		stage.getRoot().debugAll();
        gameIteration(deltaTime);

        ScreenUtils.clear(0.47f, 0.59f, 0.77f, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.act(deltaTime);
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }
}
