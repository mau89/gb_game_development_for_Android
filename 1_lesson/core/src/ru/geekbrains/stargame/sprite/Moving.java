package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Moving extends Sprite {
    private Vector2 touch;
    private Vector2 v;
    private Vector2 bufV;
    private Vector2 stopImg;


    public Moving(TextureRegion region) {
        super(region);
        setHeightProportion(0.3f);
        touch = new Vector2();
        stopImg = new Vector2();
        bufV = new Vector2();
        v = new Vector2();


    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.3f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stopImg.set(touch);
        bufV.set(v);
        bufV.scl(delta);
        if (stopImg.sub(pos).len() < bufV.len()) {
            pos.set(touch);
        } else {
            pos.mulAdd(v, delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        System.out.println("dist: " + v.set(touch.cpy().sub(pos)).setLength(0.05f));
        return false;
    }
}
