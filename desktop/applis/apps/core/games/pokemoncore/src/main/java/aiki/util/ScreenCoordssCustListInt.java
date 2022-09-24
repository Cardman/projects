package aiki.util;

import aiki.map.util.ScreenCoords;
import code.util.CustList;

public final class ScreenCoordssCustListInt extends ScreenCoordss<CustList<int[][]>> {
    public static CustList<ScreenCoords> emptyTiles(ScreenCoordssCustListInt _foreGround) {
        CustList<ScreenCoords> k_;
        k_ = new CustList<ScreenCoords>();
        for (CommonParam<ScreenCoords, CustList<int[][]>> e: _foreGround.entryList()) {
            if (e.getValue().isEmpty()) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    @Override
    protected CustList<int[][]> def() {
        return new CustList<int[][]>();
    }
}
