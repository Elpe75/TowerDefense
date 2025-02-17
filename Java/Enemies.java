package Java;

import Game.Player.Player;

public abstract class Enemies {

    public abstract Enemy findEnemy(int x, int y);

    public abstract void move(Entity[][] map, Player player);

    public abstract boolean enemyWay(int x, int y);

    public abstract void printEnemies();

    public abstract boolean allDead();

    public abstract boolean lifeWay(int x, int y);

    public abstract boolean placeLife(int x, int y);

    public abstract int getIndice(int x, int y);

    public abstract Enemy

            lifeEnemy(int n);
}
