package Game.geometry;

public class PairPosition {
    private IntPosition depart;
    private IntPosition arrived;

    public PairPosition(IntPosition x, IntPosition y) {
        this.depart = x;
        this.arrived = y;
    }

    public IntPosition getDepart() {
        return depart;
    }

    public IntPosition getArrived() {
        return arrived;
    }
}
