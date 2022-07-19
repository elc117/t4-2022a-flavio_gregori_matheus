package com.mygdx.game;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.Gdx;


public class MyGdxGame extends ApplicationAdapter {
	private int points;
	private BitmapFont font;

	private SpriteBatch batch;

	private Guillotine guillotine;

	@Override
	public void create () {
		points = 0;
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		batch = new SpriteBatch();

		guillotine = new Guillotine();

		Gdx.input.setInputProcessor(new MyInputProcessor(guillotine, this));
	}

	
	@Override
	public void render () {
		ScreenUtils.clear(0.47f,0.59f,0.77f,1);
		
		batch.begin();
		guillotine.draw(batch);
		font.draw(batch, getTextPoints(), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-10);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}


	public void addPoints(){
		points++;
	}
	
	public String getTextPoints(){
		return "Cabeças: " + points;
	}
}
