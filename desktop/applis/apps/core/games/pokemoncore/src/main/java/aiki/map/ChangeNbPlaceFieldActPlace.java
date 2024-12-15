package aiki.map;

import aiki.util.*;

public final class ChangeNbPlaceFieldActPlace implements ChangeNbPlaceFieldAct {

    private final Coords coords;

    public ChangeNbPlaceFieldActPlace(Coords _c) {
        this.coords = _c;
    }

    @Override
    public int place() {
        return coords.getNumberPlace();
    }

    @Override
    public void place(int _p) {
        coords.setNumberPlace((short) _p);
    }

    @Override
    public boolean matchLevel(int _p, int _l) {
        return place() == _p && value() == _l;
    }

    private int value() {
        return coords.getLevel().getLevelIndex();
    }

    private void value(int _v) {
        coords.getLevel().setLevelIndex((byte) _v);
    }

    @Override
    public int decr(int _l, int _v) {
        if (place() == _l && value() > _v) {
            value(value() - 1);
        }
        return 0;
    }
}
