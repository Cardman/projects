package cards.gui.panels;
import code.util.ints.Cmp;


public final class CoordsHands implements Cmp<CoordsHands> {

    private int xCoords;

    private int yCoords;

    public CoordsHands(int _xCoords, int _yCoords) {
        xCoords = _xCoords;
        yCoords = _yCoords;
    }

    public int getxCoords() {
        return xCoords;
    }

    public int getyCoords() {
        return yCoords;
    }

    @Override
    public boolean eq(CoordsHands _obj) {
        return cmp(_obj) == 0;
    }

    @Override
    public int cmp(CoordsHands _o) {
        if (yCoords < _o.yCoords) {
            return -1;
        }
        if (yCoords > _o.yCoords) {
            return 1;
        }
        if (xCoords < _o.xCoords) {
            return -1;
        }
        if (xCoords > _o.xCoords) {
            return 1;
        }
        return 0;
    }
}
