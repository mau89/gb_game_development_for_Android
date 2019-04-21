package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.EnemyShipPool;

public class Boat extends Sprite {
    private static final int INVALID_POINTER = -1;

    private Rect worldBounds;

    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0.5f, 0);

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV = new Vector2(0f, 0.5f);

    private boolean pressedRight;
    private boolean pressedLeft;

    private int rightPointer = INVALID_POINTER;
    private int leftPointer = INVALID_POINTER;

    private float reloadInterval = 0.5f;
    private float reloadInterval1 = 1f;
    private float reloadTimer;
    private float reloadTimer1;
    private Vector2 pos1;

    private Sound sound;

    private EnemyShipPool enemyShipPool;
    private TextureRegion enemyShipRegion;
    private Vector2 enemyShipV = new Vector2(0f, -0.3f);
    private int width;

    public Boat(TextureAtlas atlas, BulletPool bulletPool, EnemyShipPool enemyShipPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.enemyShipPool = enemyShipPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        width = atlas.findRegion("enemy0").getRegionWidth() / 2;
        this.enemyShipRegion = atlas.findRegion("enemy0");
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    public Boat(TextureRegion region) {
        super(region);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer1 += delta;
        if (reloadTimer1 >= reloadInterval1) {
            reloadTimer1 = 0f;
            shoot1();
        }

        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public void shoot() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        sound.play(0.01f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.015f, worldBounds, 1);
    }

    public void shoot1() {
        EnemyShip enemyShip = enemyShipPool.obtain();
        pos1 = new Vector2(0f, 0.5f);
        enemyShipRegion.setRegionWidth(width);
        enemyShip.set(this, enemyShipRegion, pos1, enemyShipV, 0.15f, worldBounds, 1);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }
}
