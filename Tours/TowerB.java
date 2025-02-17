package Tours;

import java.util.*;

import Game.View;
import Game.Player.Player;
import Game.geometry.IntPosition;
import Game.geometry.Map;
import Java.Enemy;

//Cette Tours tire sur une des positions du milieu 
//Cette Tour ture principalement sur les cases du mileu de la grille 
public class TowerB extends Tower {
    public static final int speed = 300;
    public static final int cost = 30;
    public LinkedList<IntPosition> tirs = new LinkedList<>();
    private boolean enRoute = false;
    private boolean stop = false;

    public TowerB(IntPosition pos, Map carte) {
        super(0, speed, pos, "TTB", cost, carte);
        tirs.add(new IntPosition(13, 12));
        tirs.add(new IntPosition(13, 13));
        tirs.add(new IntPosition(13, 14));
        tirs.add(new IntPosition(5, 12));
        tirs.add(new IntPosition(5, 13));
        tirs.add(new IntPosition(5, 14));
        tirs.add(new IntPosition(21, 12));
        tirs.add(new IntPosition(21, 13));
        tirs.add(new IntPosition(21, 14));
    }

    public void setStop(boolean bool) {
        stop = bool;
    }

    @Override
    public void tirer(View view) { // cette tour tire aléatoirement sur la même
                                   // position toutes les secondes

        if (enRoute) {
            return;
        }
        Timer timer = new Timer();
        Random rand = new Random();
        enRoute = true;

        // Tâche à effectuer lorsque le timer s'exécute
        TimerTask tache = new TimerTask() {

            @Override
            public void run() {
                if (stop)
                    cancel();
                int alea1 = rand.nextInt(0, tirs.size());
                IntPosition en = tirs.get(alea1);
                view.setExploseB(en);
                Enemy enem = carte.getEnemies().findEnemy(en.getX(), en.getY());
                if (enem != null) {
                    // view.addDrawLine(new PairPosition(getPos(), enem.getPos()));
                    //explose.add(new IntPosition(enem.getX(), enem.getY()));
                   
                    enem.touched(view.getPlayer());
                    // SCORE DU JOUEUR
                    carte.getPlayer().setScore();
                    //System.out.println("tirs de B reussie ");
                }
                enRoute = false;

            }
        };

        // Planifiez la tâche pour s'exécuter toutes les 0.3 secondes (en millisecondes)
        timer.schedule(tache, 1000, speed);
    }

}
