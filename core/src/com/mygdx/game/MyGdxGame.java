package com.mygdx.game;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.input.InputHandler;
import com.badlogic.gdx.Gdx;


public class MyGdxGame extends ApplicationAdapter {
	private HeadStock stock;
	private float timeSinceLastSecond = 0;

	private BitmapFont font;

	public Camera camera;
	private Viewport viewport;

	private SpriteBatch batch;

	private Guillotine guillotine;

	private InputHandler inputHandler;

	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		camera = new OrthographicCamera(400, 400);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
		camera.update();

		batch = new SpriteBatch();

		stock = new HeadStock();

		guillotine = new Guillotine(stock);
		stock.setClicable(guillotine);


		inputHandler = new InputHandler(this);
		inputHandler.addButton(guillotine);
		inputHandler.keyActions.put('g', guillotine::boost);

		Gdx.input.setInputProcessor(inputHandler);
	}

	private void gameIteration() {
		timeSinceLastSecond += Gdx.graphics.getDeltaTime();
		if (timeSinceLastSecond > 1) {
			timeSinceLastSecond -= 1;
			stock.passSecond();
			inputHandler.attBuyButtons(stock.getHeadGenerators().getButtons());
		}
	}

	
	@Override
	public void render () {
		gameIteration();

		ScreenUtils.clear(0.47f,0.59f,0.77f,1);

		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		guillotine.draw(batch);
		font.draw(batch, getTextPoints(), camera.viewportWidth-100, camera.viewportHeight-10);
		stock.getHeadGenerators().drawButtons(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	public String getTextPoints(){
		return "Cabeças: " + stock.getCurrencyInStock();
	}

	public int XPointsPosition(){
		return (int) camera.viewportWidth - (20);
	}

	public HeadStock getStock() {
		return stock;
	}


}
