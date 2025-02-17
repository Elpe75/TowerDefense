package Java;

import java.util.LinkedList;

import Game.Player.Player;

//Deuxime type d'ennemis :  ennemis de type  B 
public class EnemiesB extends Enemies {

    public static final int[][] posB = new int[35][2];
    public static final int[][] posBis = new int[25][2];
    public static final int[][] posViesBis = new int[25][2];
    public static final int[][] posViesB = new int[35][2];
    public static boolean[] viesB = new boolean[35];
    private static boolean modeDeJeu;
    public int map;
    private LinkedList<EnemyB> bEnemies = new LinkedList<>();

    /**
     * 
     * @param number    nombre d'eenemis
     * @param x         coordonnées
     * @param mode      mode de jeu
     * @param difficult niveau de difficulté du jeu
     */
    public EnemiesB(int number, int x, boolean mode, int difficult) {
        /**
         * // True mode chemin croisé
         * // False mode lineaire
         */
        modeDeJeu = mode;
        while (number > 0) {
            bEnemies.add(new EnemyB(x, -1, 2 + difficult));
            number--;
        }
        if (modeDeJeu) {
            makeposB();
            makeposViesB();
        } else {
            makeposBis();
            makeposViesBis();
        }
    }

    public static boolean getModeDeJeu() {
        return modeDeJeu;
    }

    // Chemin des ennemis mode 2
    public void makeposBis() {
        for (int i = 0; i <= 24; i++) {
            posBis[i][0] = 10;
            posBis[i][1] = i;
        }
    }

    public void makeposViesBis() {
        for (int i = 0; i <= 24; i++) {
            posViesBis[i][0] = 10;
            posViesBis[i][1] = i;
        }
    }

    public void makeposViesB() {
        int num = 0;
        for (int k = 0; k <= 9; k++) {
            posViesB[num][0] = 9;
            posViesB[num][1] = k;
            num++;
        }
        for (int i = 9; i >= 5; i--) {
            posViesB[num][0] = i;
            posViesB[num][1] = 8;
            num++;
        }
        for (int i = 10; i <= 16; i++) {
            posViesB[num][0] = 4;
            posViesB[num][1] = i;
            num++;
        }
        for (int i = 6; i <= 10; i++) {
            posViesB[num][0] = i;
            posViesB[num][1] = 17;
            num++;
        }
        for (int j = 17; j <= 24; j++) {
            posViesB[num][0] = 9;
            posViesB[num][1] = j;
            num++;
        }
    }

    @Override
    public Enemy findEnemy(int x, int y) {
        for (EnemyB enemy : bEnemies) {
            if (enemy.getX() == x && enemy.getY() == y)
                return enemy;
        }
        return null;
    }

    @Override
    public void printEnemies() {
        for (EnemyB en : bEnemies)
            System.out.print(en.toString() + " ?????? ");
    }

    // Chemin suivi par ces ennemis mode 1
    public void makeposB() {
        int num = 0;
        for (int k = 0; k <= 9; k++) {
            posB[num][0] = 10;
            posB[num][1] = k;
            num++;
        }
        for (int i = 9; i >= 5; i--) {
            posB[num][0] = i;
            posB[num][1] = 9;
            num++;
        }
        for (int i = 10; i <= 16; i++) {
            posB[num][0] = 5;
            posB[num][1] = i;
            num++;
        }
        for (int i = 6; i <= 10; i++) {
            posB[num][0] = i;
            posB[num][1] = 16;
            num++;
        }
        for (int j = 17; j <= 24; j++) {
            posB[num][0] = 10;
            posB[num][1] = j;
            num++;
        }
    }

    @Override
    public boolean enemyWay(int i, int j) {
        int[][] tab;
        tab = (modeDeJeu) ? posB : posBis;
        for (int k = 0; k < tab.length; k++) {
            if (tab[k][0] == i && tab[k][1] == j)
                return true;
        }
        return false;
    }

    // Logique de déplacement suivie
    @Override
    public void move(Entity[][] map, Player player) {
        int num = 0;
        while (num < bEnemies.size()) {
            if (bEnemies.get(num).getY() >= 0) {
                if (bEnemies.get(num).dead()) {
                    map[bEnemies.get(num).getX()][bEnemies.get(num).getY()] = null;
                    bEnemies.remove(num);
                } else
                    bEnemies.get(num).move(map, player);
            } else if (bEnemies.get(num).getY() == -1) {
                map[10][0] = bEnemies.get(num);
                bEnemies.get(num).setY(0);
                bEnemies.get(num).setX(10);
                return;
            } else
                bEnemies.get(num).move(map, player);
            num++;
        }
    }

    @Override
    public boolean allDead() {
        return (bEnemies.size() == 0);
    }

    @Override
    public boolean lifeWay(int x, int y) {
        int[][] tab;
        tab = (modeDeJeu) ? posViesB : posViesBis;
        for (int k = 0; k < tab.length; k++) {
            if (tab[k][0] == x && tab[k][1] == y)
                return true;
        }
        return false;
    }

    @Override
    public int getIndice(int x, int y) {
        int[][] tab;
        tab = (modeDeJeu) ? posViesB : posViesBis;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i][0] == x && tab[i][1] == y)
                return i;
        }
        return -1;
    }

    @Override

    public Enemy lifeEnemy(int n) {
        for (EnemyB en : bEnemies) {
            if (en.getIndice() == n && viesB[en.getIndice()])
                return en;
        }
        return null;
    }

    @Override
    public boolean placeLife(int x, int y) {
        return false;
    }
}
