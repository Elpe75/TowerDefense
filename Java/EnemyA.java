package Java;

import Game.Player.Player;

public class EnemyA extends Enemy {

    public int indice;

    public EnemyA(int health) {
        super(health);
        indice = 0;
    }

    public EnemyA(int x, int y, int health) {
        super(x, y, health);
    }

    public void setIndice() {
        indice += 1;
    }

    @Override
    public void touched(Player player) {
        super.touched(player);
        //System.out.println("EnemyA touché !!");
    }

    public int getIndice() {
        return indice;
    }

    public Enemy placeVie() {
        boolean[] placeLife = EnemiesA.viesA;
        if (placeLife[indice])
            return this;
        return null;
    }

    @Override
    public boolean move(Entity[][] map, Player player) {
        int[][] tab = EnemiesA.pos;
        boolean[] placeLife = EnemiesA.viesA;
        if (dead())
            return true;
        else if (indice < 24) {
            map[tab[indice][0]][tab[indice][1]] = this;
            map[getX()][getY()] = null;
            // placeLife[indice][0][indice][1] = true;
            setX(tab[indice][0]);
            setY(tab[indice][1]);
            placeLife[indice] = true;
            EnemiesA.viesA[indice] = true;
            setIndice();
        } else if (indice == 24) {
            player.touched();
            die();
            map[getX()][getY()] = null;
            return true;
        }
        return false;
    }

}
