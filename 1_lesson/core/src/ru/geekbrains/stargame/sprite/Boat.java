package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class Boat extends Sprite {
    private Vector2 touch;
    public Boat(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        int width = atlas.findRegion("main_ship").getRegionWidth()/2;
        atlas.findRegion("main_ship").setRegionWidth(width);
        touch = new Vector2();
        pos.set(0f,1.5f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
       pos.set(touch.x,0);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        return false;
    }
}
