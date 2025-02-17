package Java;

import java.util.*;

import Game.Player.Player;;

/// Troisieme type d 'ennemis :  ennemis  de type C 
public class EnemiesC extends Enemies {
    public static final int[][] posC = new int[35][2];
    private LinkedList<EnemyC> cEnemies = new LinkedList<>();
    public static final int[][] posCBis = new int[25][2];
    public static final int[][] posViesC = new int[35][2];
    public static final int[][] posViesCBis = new int[25][2];
    public static boolean[] viesC = new boolean[35];

    private static boolean modeDeJeu;

    // True mode Croisé
    // False Mode linéaire
    /**
     * 
     * @param number
     * @param x
     * @param mode
     * @param difficult
     */
    public EnemiesC(int number, int x, boolean mode, int difficult) {
        modeDeJeu = mode;
        while (number > 0) {
            cEnemies.add(new EnemyC(x, -1, 2 + difficult));
            number--;
        }
        if (modeDeJeu) {
            makeposC();
        } else {
            makeposBis();
            makeViesBis();
        }
    }

    public static boolean getModeDeJeu() {
        return modeDeJeu;
    }

    @Override
    public Enemy findEnemy(int x, int y) {
        for (Enemy enemy : cEnemies) {
            if (enemy.getX() == x && enemy.getY() == y)
                return enemy;
        }
        return null;
    }

    // Chemin par les ennemis mode 2
    public void makeposBis() {
        for (int i = 0; i <= 24; i++) {
            posCBis[i][0] = 16;
            posCBis[i][1] = i;
        }
    }

    public void makeViesBis() {
        for (int i = 0; i <= 24; i++) {
            posViesCBis[i][0] = 15;
            posViesCBis[i][1] = i;
        }
    }

    // Chemin suivi par les ennemis de type C mode 2
    public void makeposC() {
        int num = 0;
        for (int k = 0; k <= 9; k++) {
            posC[num][0] = 16;
            posC[num][1] = k;
            num++;
        }
        for (int i = 17; i <= 21; i++) {
            posC[num][0] = i;
            posC[num][1] = 9;
            num++;
        }
        for (int i = 10; i <= 16; i++) {
            posC[num][0] = 21;
            posC[num][1] = i;
            num++;
        }
        for (int i = 20; i >= 16; i--) {
            posC[num][0] = i;
            posC[num][1] = 16;
            num++;
        }
        for (int j = 17; j <= 24; j++) {
            posC[num][0] = 16;
            posC[num][1] = j;
            num++;
        }
    }

    public void makeViesposC() {
        int num = 0;
        for (int k = 0; k <= 9; k++) {
            posViesC[num][0] = 15;
            posViesC[num][1] = k;
            num++;
        }
        for (int i = 17; i <= 21; i++) {
            posViesC[num][0] = i;
            posViesC[num][1] = 8;
            num++;
        }
        for (int i = 10; i <= 16; i++) {
            posViesC[num][0] = 22;
            posViesC[num][1] = i;
            num++;
        }
        for (int i = 20; i >= 16; i--) {
            posViesC[num][0] = i;
            posViesC[num][1] = 17;
            num++;
        }
        for (int j = 17; j <= 24; j++) {
            posViesC[num][0] = 15;
            posViesC[num][1] = j;
            num++;
        }
    }

    @Override
    public boolean enemyWay(int i, int j) {
        int[][] tab;
        tab = modeDeJeu ? posC : posCBis;
        for (int k = 0; k < tab.length; k++) {
            if (tab[k][0] == i && tab[k][1] == j)
                return true;
        }
        return false;
    }

    @Override
    public void printEnemies() {
        for (Enemy en : cEnemies)
            System.out.print(en.toString() + " ?????? ");
    }

    // Logique de déplacement suivie
    @Override
    public void move(Entity[][] map, Player player) {
        int num = 0;
        while (num < cEnemies.size()) {
            if (cEnemies.get(num).getY() >= 0) {
                if (cEnemies.get(num).dead()) {
                    map[cEnemies.get(num).getX()][cEnemies.get(num).getY()] = null;
                    cEnemies.remove(num);
                } else
                    cEnemies.get(num).move(map, player);
            } else if (cEnemies.get(num).getY() == -1) {
                map[16][0] = cEnemies.get(num);
                cEnemies.get(num).setY(0);
                cEnemies.get(num).setX(16);
                return;
            } else
                cEnemies.get(num).move(map, player);
            num++;
        }
    }

    @Override
    public boolean allDead() {
        return (cEnemies.size() == 0);
    }

    @Override
    public boolean lifeWay(int x, int y) {
        int[][] tab;
        tab = modeDeJeu ? posViesC : posViesCBis;
        for (int k = 0; k < tab.length; k++) {
            if (tab[k][0] == x && tab[k][1] == y)
                return true;
        }
        return false;
    }

    @Override
    public int getIndice(int x, int y) {
        int[][] tab;
        tab = (modeDeJeu) ? posViesC : posViesCBis;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][0] == x && tab[i][1] == y)
                return i;
        }
        return -1;
    }

    @Override

    public Enemy lifeEnemy(int n) {
        for (EnemyC en : cEnemies) {
            if (en.getIndice() == n && viesC[en.getIndice()])
                return en;
        }
        return null;
    }

    @Override
    public boolean placeLife(int x, int y) {
        return false;
    }

}
