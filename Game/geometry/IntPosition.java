
package Game.geometry;

public class IntPosition {
    private int x;
    private int y;

    public IntPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public IntPosition(){
        x=0;
        y=0;
    }

    public IntPosition getPos() {
        return (new IntPosition(x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean estAccessible(Map carte) {
        return (x < carte.getLongueur() && y < carte.getLongueur());
    }
}
