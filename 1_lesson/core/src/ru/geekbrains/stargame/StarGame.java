package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import ru.geekbrains.stargame.screen.MenuScreen;


public class StarGame extends Game {
//	SpriteBatch batch;
//	Texture img;
//	Texture background;
//	static Sprite backSprite;


	@Override
	public void create () {
		setScreen(new MenuScreen());
//		batch = new SpriteBatch();
//		//img = new Texture("badlogic.jpg");
//		background = new Texture(Gdx.files.internal("background.png"));
//		backSprite = new Sprite(background);
//
//		backSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		backSprite.setPosition(0,0f);
	}

//	@Override
//	public void render () {
//
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		backSprite.draw(batch);
//		//batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
