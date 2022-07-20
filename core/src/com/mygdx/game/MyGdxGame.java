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
import com.badlogic.gdx.Gdx;


public class MyGdxGame extends ApplicationAdapter {
	private int points;
	private BitmapFont font;

	public Camera camera;
	private Viewport viewport;

	private SpriteBatch batch;

	private Guillotine guillotine;

	@Override
	public void create () {
		points = 0;
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		camera = new OrthographicCamera(400, 400);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
		camera.update();

		batch = new SpriteBatch();

		guillotine = new Guillotine();

		Gdx.input.setInputProcessor(new MyInputProcessor(guillotine, this));
	}

	
	@Override
	public void render () {
		ScreenUtils.clear(0.47f,0.59f,0.77f,1);

		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		guillotine.draw(batch);
		font.draw(batch, getTextPoints(), camera.viewportWidth-100, camera.viewportHeight-10);
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

	public void addPoints(){
		points++;
	}
	
	public String getTextPoints(){
		return "Cabeças: " + points;
	}

	public int XPointsPosition(){
		return (int) camera.viewportWidth - (20);
	}


}
