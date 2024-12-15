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
    public int value() {
        return coords.getLevel().getLevelIndex();
    }

    @Override
    public int value(int _v) {
        coords.getLevel().setLevelIndex((byte) _v);
        return _v;
    }
}
