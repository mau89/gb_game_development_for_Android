package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.Moving;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Moving moving;
    private Texture bg;
    private Background background;


    @Override
    public void show() {
        super.show();
        bg = new Texture("background.png");
        background = new Background(new TextureRegion(bg));
        img = new Texture("badlogic.jpg");
        moving = new Moving(new TextureRegion(img));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        moving.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        moving.update(delta);
        moving.draw(batch);


        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        moving.touchDown(touch, pointer);
        return false;
    }
}
