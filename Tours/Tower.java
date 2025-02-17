package Tours;

import java.util.*;

import javax.swing.*;

import Game.View;
import Game.Player.Player;
import Game.geometry.IntPosition;
import Game.geometry.Map;

public abstract class Tower extends Entity1 implements fonctionsTower {

    protected int shootDistance; // distance de tir
    protected int speedShooting; // temps entre chaque tir
    protected Map carte;

    public Tower(int sD, int speedS, IntPosition pos, String app, int cost, Map carte) {
        super(cost, app, pos);
        this.shootDistance = sD;
        this.speedShooting = speedS;
        this.carte = carte;
        // this.carte.getGridTowers()[pos.getX()][pos.getY()] = true;
    }

    public Tower(int x, int y) {
        super(x, y);
    }

    public IntPosition getTowerPos() {
        return getPos();
    }
    public int getTowerX(){
        return getX();
    }
    public int getTowerY(){
        return getY();
    }

    public List<IntPosition> casesAtteignables() {
        List<IntPosition> posAtteignables = new ArrayList<>();
        int x = getTowerPos().getX();
        int y = getTowerPos().getY();
        for (int i = 1; i <= shootDistance; i++) {
            for (int j = 1; j <= shootDistance; j++) {
                /*
                 * la position est éliminée si elle est hors
                 * des limites de la map
                 */
                posAtteignables.add(new IntPosition(x, y - j).estAccessible(carte) ? new IntPosition(x, y - j) : null);
                posAtteignables.add(new IntPosition(x, y + j).estAccessible(carte) ? new IntPosition(x, y + j) : null);
                posAtteignables
                        .add(new IntPosition(x - i, y - j).estAccessible(carte) ? new IntPosition(x - i, y - j) : null);
                posAtteignables.add(new IntPosition(x - i, y).estAccessible(carte) ? new IntPosition(x - i, y) : null);
                posAtteignables
                        .add(new IntPosition(x - i, y + j).estAccessible(carte) ? new IntPosition(x - i, y + j) : null);
                posAtteignables
                        .add(new IntPosition(x + i, y - j).estAccessible(carte) ? new IntPosition(x + i, y - j) : null);
                posAtteignables.add(new IntPosition(x + i, y).estAccessible(carte) ? new IntPosition(x + i, y) : null);
                posAtteignables
                        .add(new IntPosition(x + i, y + j).estAccessible(carte) ? new IntPosition(x + i, y + j) : null);
            }
        }
        return posAtteignables;
    }

    @Override
    public abstract void tirer(View view);

}