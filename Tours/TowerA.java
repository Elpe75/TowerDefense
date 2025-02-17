package Tours;

import java.util.*;

import Game.View;
import Game.Player.Player;
import Game.geometry.IntPosition;
import Game.geometry.Map;
import Java.Enemy;

public class TowerA extends Tower { // cette tour vise les ennemis sur lesquels elle tire, toutes les 2 secondes, à
                                    // une distance de 7 cases
    public static final int speed = 1500;
    public static final int shoot = 7;
    public static final int cost = 80;
    protected boolean enRoute = false;
    private boolean stop = false;

    public TowerA(IntPosition pos, Map carte) {
        super(shoot, speed, pos, "TTA", cost, carte);
    }


    public boolean getEnRoute() {
        return enRoute;
    }

    public void setStop(boolean bool) {
        stop = bool;
    }

    @Override
    public void tirer(View view) {
        //System.out.println(enRoute);
            Timer timer = new Timer();
           
            // Tâche à effectuer lorsque le timer s'exécute
            TimerTask tache = new TimerTask() {
                @Override
                public void run() {
                    if (stop)cancel(); 
                    //System.out.println(enRoute);
                     
                    enRoute=false;

                for (IntPosition pos : casesAtteignables()) {
                    if (pos != null) {
                        Enemy en = carte.getEnemies().findEnemy(pos.getX(), pos.getY());
                        if (en != null) {
                            // view.addDrawLine(new PairPosition(getPos(), en.getPos()));
                            // explose.add(new IntPosition(en.getX(), en.getY()));
                            view.setExploseA(pos);
                            
                            en.touched(view.getPlayer());
                            enRoute=true;
                            //System.out.println("Tir de A reusie ");
                        }
                    }
                    if (enRoute)
                        break;
                }

            }

        };
        timer.schedule(tache, 1000, speed);
    }

}
