package Java;

import Game.Player.Player;

public class EnemyC extends Enemy {

    public int indice;

    EnemyC(int health) {
        super(health);
        indice = 0;
    }

    public EnemyC(int x, int y, int health) {
        super(x, y, health);
    }

    public void setIndice() {
        indice += 1;
    }

    @Override
    public void touched(Player player) {
        super.touched(player);
        //System.out.println("EnemyC  touché !!");
    }

    public int getIndice() {
        return indice;
    }

    public Enemy placeVie() {
        boolean[] placeLife = EnemiesC.viesC;
        if (placeLife[indice])
            return this;
        return null;
    }

    @Override
    public boolean move(Entity[][] map, Player player) {
        int[][] tab;
        boolean mode = EnemiesC.getModeDeJeu();
        // Tracer le chemin correspondant suivant le mode Choisi
        tab = EnemiesC.getModeDeJeu() ? EnemiesC.posC : EnemiesC.posCBis;
        if (super.dead())
            return true;
        else if ((mode && indice < 34) || (!mode && indice < 24)) {
            map[tab[indice][0]][tab[indice][1]] = this;
            map[getX()][getY()] = null;
            setX(tab[indice][0]);
            setY(tab[indice][1]);
            EnemiesC.viesC[indice] = true;
            setIndice();
        } else if ((mode && indice == 34) || (!mode && indice == 24)) {
            player.touched();
            die();
            map[getX()][getY()] = null;
            return true;
        }
        return false;
    }
}
