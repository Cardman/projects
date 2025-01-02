package aiki.map;

import aiki.map.util.*;

public final class ChangeNbPlaceFieldActTileMiniMap implements ChangeNbPlaceFieldAct {

    private final TileMiniMap coords;

    public ChangeNbPlaceFieldActTileMiniMap(TileMiniMap _c) {
        this.coords = _c;
    }

    @Override
    public int place() {
        return coords.getPlace();
    }

    @Override
    public void place(int _p) {
        coords.setPlace(_p);
    }

    @Override
    public boolean matchLevel(int _p, int _l) {
        return false;
    }

    @Override
    public int decr(int _l, int _v) {
        return -1;
    }
}
