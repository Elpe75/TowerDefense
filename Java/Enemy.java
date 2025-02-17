package Java;

import java.util.*;

import Game.Player.Player;

public class Enemy extends Entity implements EnemyInterface {
    private int health;
    private static int speed = 1;
    private static int reward = 5;
    private boolean dead;
    public static final int out = 24;;

    public Enemy(int x, int y, int health) {
        super(x, y, "XXX");
        this.health = health;
        dead = false;
    }

    Enemy(int health) {
        super(0, 0, "XXX");
        this.health = health;
        dead = false;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }

    public String toString() {
        return "health: " + health + " - speed: " + speed + " - reward : " + reward + " x :" + getX();
    }

    public String getName() {
        if (dead)
            return "";
        return super.getName();
    }

    // Touché un ennemis
    public void touched(Player player) {
        if (health == 0) {
            dead = true;
            player.vend(5);
            return;
        }
        health--;
        setName();
    }

    // Touché un ennemis , modifier son name
    public void setName() {
        String name = super.getName();
        if (name.equals("XXX"))
            setName("XX-");
        else if (name.equals("XX-"))
            setName("X--");
        else if (name.equals("X--") && health == 0)
            setName("---");
        else
            setName("");
    }
    // Tuer un ennemie

    public boolean getDead() {
        return dead;
    }

    @Override
    public boolean dead() {
        return dead == true;
    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean move(Entity[][] map, Player player) {
        // on laisse les sous classes
        return false;
    }
}
