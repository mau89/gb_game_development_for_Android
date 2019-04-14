package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.Moving;

public class MenuScreen extends BaseScreen {

    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;
    private Moving moving;
    private Texture bg;
    private Background background;
    private Vector2 stopImg;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        pos = new Vector2();
        stopImg = new Vector2();
        v = new Vector2(0.3f, 0.3f);
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
        batch.end();

        pos.add(v);
        batch.begin();
        moving.draw(batch);

        batch.end();

        stopImg.set(touch);
        if (stopImg.sub(pos).len() > 0.5f) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        img.dispose();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);

        System.out.println("dist: " + v.set(touch.cpy().sub(pos)).setLength(0.3f));
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

}
