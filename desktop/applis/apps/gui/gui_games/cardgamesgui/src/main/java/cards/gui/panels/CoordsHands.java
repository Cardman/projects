package cards.gui.panels;


public final class CoordsHands {

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
