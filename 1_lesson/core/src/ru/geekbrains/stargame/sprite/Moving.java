package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Moving extends Sprite {


    public Moving(TextureRegion region) {
        super(region);

    }

    @Override
    public void resize(Rect worldBounds) {
                setHeightProportion(worldBounds.getTop());
        pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {

    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        //touch.set(, Gdx.graphics.getHeight() - int screenY);


       // System.out.println("dist: " + v.set(touch.cpy().sub(pos)).setLength(0.3f));
        System.out.println("touchDown dfgfdgdgdsgdgdfgdfg touch.x = " + touch.x + " touch.y = " + touch.y);
        return super.touchDown(touch, pointer);
    }
}
