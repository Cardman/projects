package cards.gui.panels;


import code.util.core.*;

public final class CoordsHands {

    private final int xCoords;

    private final int yCoords;

    public CoordsHands(int _xCoords, int _yCoords) {
        xCoords = _xCoords;
        yCoords = _yCoords;
    }

    public int cmp(CoordsHands _o) {
        int res_ = NumberUtil.compareLg(yCoords, _o.yCoords);
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return NumberUtil.compareLg(xCoords, _o.xCoords);
    }
}
