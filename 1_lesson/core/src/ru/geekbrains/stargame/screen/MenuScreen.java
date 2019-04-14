package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;
    private Vector2 stopImg;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        pos = new Vector2();
        stopImg = new Vector2();
        v = new Vector2(0.3f, 0.3f);
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos.add(v);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
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
