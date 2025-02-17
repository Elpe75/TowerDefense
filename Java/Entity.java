package Java;

import java.awt.image.BufferedImage;

import Game.geometry.IntPosition;

public class Entity extends IntPosition {
    private String name;
    private BufferedImage image;

    public Entity(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }

    public Entity(int x, int y) {
        super(x, y);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
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
}
