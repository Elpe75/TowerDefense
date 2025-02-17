package Tours;

import Game.geometry.*;
import Java.Entity;

public class Entity1 extends Entity {
    private int cost; // cout de l'entité
    private String name; // apparence dans le terminal
    private IntPosition position; // position de l'entité

    Entity1(int x, int y) {
        super(x, y, "TTT");
        position = new IntPosition(x, y);
        this.name = "TTT";
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public String getName() {
        return name;
    }

    public Entity1(int c, String app, IntPosition pos) {
        super(pos.getX(), pos.getY(), "TTT");
        this.cost = c;
        this.name = app;
        this.position = pos;
    }

    public IntPosition getPos() {
        return this.position;
    }
}
