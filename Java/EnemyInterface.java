package Java;

import Game.Player.Player;

public interface EnemyInterface {

    // methode pour bouger
    public boolean move(Entity[][] map, Player player);

    // tuer un enemy
    public boolean dead();

    public void die();
}
