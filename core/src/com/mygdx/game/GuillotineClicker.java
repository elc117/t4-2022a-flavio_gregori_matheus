package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.mygdx.game.input.InputHandler;

public class GuillotineClicker extends ApplicationAdapter {
	private HeadStock stock;
	private float timeSinceLastSecond = 0;
	private BitmapFont font;
	public Camera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Guillotine guillotine;
	private Dragon dragon;
	private Stage stage;

	private Skin createSkin() {
		Skin skin = new Skin();

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = Color.BLACK;

		Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

		skin.add("default", labelStyle);
		skin.add("default", buttonStyle);
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

	@Override
	public void create() {
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

		InputHandler inputHandler = new InputHandler(this, stage);
		inputHandler.keyActions.put('g', guillotine::boost); /*For testing purposes*/
		Gdx.input.setInputProcessor(inputHandler);
	}

	private void gameIteration() {
		timeSinceLastSecond += Gdx.graphics.getDeltaTime();
		if (timeSinceLastSecond > 1) {
			timeSinceLastSecond -= 1;
			stock.passSecond();
		}
	}

	@Override
	public void render() {
		gameIteration();

		ScreenUtils.clear(0.47f, 0.59f, 0.77f, 1);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		stage.act(Gdx.graphics.getDeltaTime());
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
