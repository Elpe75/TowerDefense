package Game.geometry;

import java.util.*;

import Game.MakeEnemies;
import Game.Player.Player;
import Tours.*;
import Java.*;
import java.util.Timer;
import java.util.TimerTask;

public class Map {
    private LinkedList<Tower> tours = new LinkedList<>();
    private MakeEnemies enemies;
    private int longueur;
    private Entity[][] map;
    private Player player;

    public Map(int number, int ligne, Player player, boolean modeView, boolean modeDeJeu, int difficulte) {
        this.player = player;
        this.longueur = number;
        map = new Entity[longueur][longueur];
        enemies = new MakeEnemies(modeView, ligne, difficulte);
        // placeTower();
    }

    public boolean allDead() {
        return enemies.allDead();
    }

    public void sendEnemies() {
        enemies.sendEnemies();
    }

    public void add(int x, int y, Entity en) {
        map[x][y] = en;
    }

    public LinkedList<Tower> getTours() {
        return tours;
    }

    public MakeEnemies getEnemies() {
        return enemies;
    }

    // Placer des tours sur la map
    public void placeTower() {
        // Coordonnes des postions des diffresnts tours sur la map
        int[][] tab = { { 7, 3 }, { 7, 4 }, { 8, 12 }, { 18, 13 }, { 17, 20 }, { 17, 21 }, { 19, 5 }, { 19, 6 },
                { 18, 12 }, { 18, 13 }, { 19, 18 }, { 19, 19 } };
        // TowerB tower = new TowerB(new IntPosition(8, 12), this);
        // TowerA towerA = new TowerA(new IntPosition(18, 12), this);
        TowerC towerC = new TowerC(new IntPosition(19, 18), this);
        // tours.add(tower);
        // tours.add(towerA);
        tours.add(towerC);
    }

    public Player getPlayer() {
        return player;
    }

    public void moveEnemies() {
        enemies.move(map, player);
    }

    public boolean enemyWay(int i, int j) {
        return enemies.enemyWay(i, j);
    }

    public boolean lifeWay(int i, int j) {
        return enemies.lifeWay(i, j);
    }

    public Enemy lifEnemy(int x, int y) {
        return enemies.lifEnemy(enemies.getIndice(x, y));
    }

    public void animate() {
        // // Les Tours commencent à tirer
        Timer timer = new Timer();
        // for (Tower towers : tours) {
        // towers.tirer();
        // }
        // Tâche à effectuer lorsque le timer s'exécute
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                moveEnemies();
            }
        };

        // Planifiez la tâche pour s'exécuter toutes les 5 secondes (en millisecondes)
        timer.schedule(tache, 0, 3000);

        // Arrêtez le timer après un certain délai (par exemple, après 30 secondes)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Arrêt du timer après 30 secondes.");
                timer.cancel(); // Arrête le timer
            }
        }, 60000);
    }

    public String printLine() {
        String result = "";
        for (int i = 0; i < map.length; i++)
            result += "____";
        return result;
    }

    public String printLineBis() {
        String result = "";
        for (int i = 0; i < map.length; i++)
            result += "~~~~";
        return result;
    }

    // Imprimer la map suur le terminal
    public void printMap() {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                /// Incomplet pour le moment
            }
        }
    }

    public Entity[][] getMap() {
        return map;
    }

    // On envoie aleatoirement deux coordonnes x et y:
    public int[] randonNumbers(int x1, int x2, int y1, int y2) {
        Random r = new Random();
        int rx = r.nextInt(x1, x2);
        int ry = r.nextInt(y1, y2);
        int[] tab = { rx, ry };
        return tab;
    }

    public int getLongueur() {
        return longueur;
    }

    public boolean[][] getGridTowers() {
        return null;
    }

    public void test() {
        enemies.test();
    }
}
