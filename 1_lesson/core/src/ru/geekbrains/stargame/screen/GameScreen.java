package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.BaseScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprite.Background;
import ru.geekbrains.stargame.sprite.Boat;
import ru.geekbrains.stargame.sprite.Star;

public class GameScreen extends BaseScreen {

    private TextureAtlas atlas;
    private Star starList[];
    private Texture bg;

    private Background background;

    private Boat boat;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new Star[256];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlas);
        }
        boat=new Boat(atlas);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        boat.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        for (Star star : starList) {
            star.update(delta);
        }
        batch.begin();
        background.draw(batch);
        background.draw(batch);
        for (Star star : starList) {
            star.draw(batch);
        }
        boat.update(delta);
        boat.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();

        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        boat.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {

        return false;
    }
}
