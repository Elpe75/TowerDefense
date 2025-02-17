package Game;

import Java.*;
import java.util.*;

import Game.Player.Player;

public class MakeEnemies {
    Random rand = new Random();
    public LinkedList<Enemies> enemies = new LinkedList<>();
    private boolean modeDeJeu;
    private int nombreEnnemis;
    private int difficult;

    public MakeEnemies(boolean mode, int nombre, int difficulte) {
        this.modeDeJeu = mode;
        this.nombreEnnemis = nombre;
        this.difficult = difficulte;
        enemies.add(new EnemiesB(nombre, 10, mode, difficult));
        enemies.add(new EnemiesA(nombre, 13, difficult));
        enemies.add(new EnemiesC(nombre, 16, mode, difficult));
    }

    public boolean allDead() {
        for (Enemies en : enemies)
            if (!en.allDead())
                return false;
        return true;
    }

    public boolean enemyWay(int i, int j) {
        for (Enemies en : enemies) {
            if (en.enemyWay(i, j))
                return true;
        }
        return false;
    }

    public boolean lifeWay(int i, int j) {
        for (Enemies en : enemies) {
            if (en.lifeWay(i, j))
                return true;
        }
        return false;
    }

    public int getIndice(int x, int y) {
        for (Enemies en : enemies) {
            if (en.getIndice(x, y) != -1)
                return en.getIndice(x, y);
        }
        return 1;
    }

    public Enemy lifEnemy(int n) {
        if (n == -1)
            return null;
        for (Enemies en : enemies) {
            if (en.lifeEnemy(n) != null)
                return en.lifeEnemy(n);
        }
        return null;
    }

    // Faire avancer toutes les vagues d'eennemis
    public void move(Entity[][] map, Player player) {
        for (Enemies listEnemies : enemies)
            listEnemies.move(map, player);
    }
    // Envoyer un ennemis touché

    public Enemy findEnemy(int i, int j) {
        for (Enemies en : enemies) {
            if (en.findEnemy(i, j) != null)
                return en.findEnemy(i, j);
        }
        return null;
    }

    public void test() {
        for (Enemies en : enemies) {
            System.out.println("");
            System.out.println("");
            en.printEnemies();
        }
    }

    public void sendEnemies() {
        enemies = (new MakeEnemies(modeDeJeu, nombreEnnemis
                + 1, difficult)).enemies;
    }

    public LinkedList<Enemies> getEnemies() {
        return enemies;
    }

}
