package ru.geekbrains.stargame.pool;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

        @Override
        protected EnemyShip newObject() {
        return new EnemyShip();
    }
}
