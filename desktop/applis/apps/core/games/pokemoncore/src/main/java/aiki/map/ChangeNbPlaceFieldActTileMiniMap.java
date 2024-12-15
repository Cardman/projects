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
        coords.setPlace((short) _p);
    }

    @Override
    public int value() {
        return -1;
    }

    @Override
    public int value(int _v) {
        return _v;
    }
}
