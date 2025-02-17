package Java;

import Game.Player.Player;

public class EnemyB extends Enemy {

    public int indice;

    public EnemyB(int health) {
        super(health);
        indice = 0;
    }

    public EnemyB(int x, int y, int health) {
        super(x, y, health);
    }

    public void setIndice() {
        indice += 1;
    }

    @Override
    public void touched(Player player) {
        super.touched(player);
        //System.out.println("EnemyB  touché !!");
    }

    public int getIndice() {
        return indice;
    }

    public Enemy placeVie() {
        boolean[] placeLife = EnemiesB.viesB;
        if (placeLife[indice])
            return this;
        return null;
    }

    @Override
    public boolean move(Entity[][] map, Player player) {
        int[][] tab;
        boolean mode = EnemiesB.getModeDeJeu();
        // Tracer le chemin correspondant suivant le mode Choisi
        tab = EnemiesB.getModeDeJeu() ? EnemiesB.posB : EnemiesB.posBis;
        if (super.dead())
            return true;
        else if ((mode && indice < 34) || (!mode && indice < 24)) {
            map[tab[indice][0]][tab[indice][1]] = this;
            map[getX()][getY()] = null;
            setX(tab[indice][0]);
            setY(tab[indice][1]);
            EnemiesB.viesB[indice] = true;
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
