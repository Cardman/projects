package aiki.game.fight;

public final class GroundPlaceKey {
    private int ground;
    private int key;
    public GroundPlaceKey(int _f, int _r) {
        ground = _f;
        key = _r;
    }
    public int getGround() {
        return ground;
    }

    public int getKey() {
        return key;
    }

}
