package Java;

import java.util.LinkedList;

import Game.Player.Player;

///Premier type d'ennemis : enneis de type A 
public class EnemiesA extends Enemies {
    public static final int[][] pos = new int[25][2];
    public static final int[][] posVies = new int[25][2];
    public static boolean[] viesA = new boolean[25];
    private LinkedList<EnemyA> enemies = new LinkedList<>();

    public EnemiesA(int number, int x, int difficult) {
        while (number > 0) {
            enemies.add(new EnemyA(x, -1, 2 + difficult));
            number--;
        }
        makePos();
        makePosLife();
    }

    // Tracer le chemin des ennemis

    public void makePos() {
        for (int i = 0; i <= 24; i++) {
            pos[i][0] = 13;
            pos[i][1] = i;
        }
    }

    public void makePosLife() {
        for (int i = 0; i <= 24; i++) {
            posVies[i][0] = 12;
            posVies[i][1] = i;
        }
    }

    public int getNumber() {
        return enemies.size();
    }

    @Override
    public void printEnemies() {
        for (EnemyA en : enemies)
            System.out.print(en.toString() + " ?????? ");
    }

    @Override
    public boolean enemyWay(int i, int j) {
        for (int k = 0; k < pos.length; k++) {
            if (pos[k][0] == i && pos[k][1] == j)
                return true;
        }
        return false;
    }

    @Override
    public boolean allDead() {
        return enemies.size() == 0;
    }

    @Override
    public void move(Entity[][] map, Player player) {
        int num = 0;
        while (num < enemies.size()) {
            if (enemies.get(num).getY() >= 0) {
                if (enemies.get(num).dead()) {
                    map[enemies.get(num).getX()][enemies.get(num).getY()] = null;
                    enemies.remove(num);
                } else
                    enemies.get(num).move(map, player);
            } else if (enemies.get(num).getY() == -1) {
                map[13][0] = enemies.get(num);
                enemies.get(num).setY(0);
                enemies.get(num).setX(13);
                return;
            } else
                enemies.get(num).move(map, player);
            num++;
        }
    }

    @Override

    public int getIndice(int x, int y) {
        for (int i = 0; i < posVies.length; i++) {
            if (posVies[i][0] == x && posVies[i][1] == y)
                return i;
        }
        return -1;
    }

    @Override

    public Enemy lifeEnemy(int n) {
        for (EnemyA en : enemies) {
            if (en.getIndice() == n && viesA[en.getIndice()])
                return en;
        }
        return null;
    }

    @Override
    public Enemy findEnemy(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y)
                return enemy;
        }
        return null;
    }

    @Override
    public boolean lifeWay(int x, int y) {
        for (int i = 0; i < posVies.length; i++) {
            if (posVies[i][0] == x && posVies[i][1] == y && placeLife(x, y))
                return true;
        }
        return false;
    }

    @Override
    public boolean placeLife(int x, int y) {
        return false;
    }
}
