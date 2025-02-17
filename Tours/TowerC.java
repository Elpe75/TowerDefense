package Tours;

import java.util.*;

import Game.View;
import Game.geometry.IntPosition;
import Game.geometry.Map;
import Java.Enemy;

//Cette Tour tirre sur toutes les cases dans un rayon de shoot =  15 cases 
public class TowerC extends Tower {
    public static final int shoot = 10;
    public static int speed = 2500;
    public static final int cost = 50;
    protected boolean enRoute=false;
    private boolean stop = false;


    public TowerC(IntPosition pos, Map carte) {
        super(shoot, speed, pos, "TTC", cost, carte);
    }

    public void setStop(boolean bool){
        stop = bool;
    }

    @Override

    public void tirer(View view) {// cette tour tire aléatoirement dans son champ de
                                   // tir toutes les secondes
        Timer timer = new Timer();
        enRoute = true;
        // Tâche à effectuer lorsque le timer s'exécute
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                if (stop)cancel();
                enRoute=false;
                for (IntPosition pos : casesAtteignables()) {
                    if (pos != null) {
                        view.setExploseC(pos);

                        Enemy enem = carte.getEnemies().findEnemy(pos.getX(), pos.getY());
                        if (enem != null) {
                            // view.addDrawLine(new PairPosition(getPos(), enem.getPos()));
                            // explose.add(new IntPosition(enem.getX(), enem.getY()));

                            enem.touched(view.getPlayer());
                            enRoute = true;
                            carte.getPlayer().setScore();
                            //System.out.println("Tir de C reussie ");
                        }
                    }
                    if (enRoute)
                        break;
                }

            }
        };
        // Planifiez la tâche pour s'exécuter toutes les 1 secondes (en millisecondes)
        timer.schedule(tache, 1000, speed);
    }
}
