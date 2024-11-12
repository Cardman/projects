package aiki.util;

import aiki.map.util.ScreenCoords;
import code.util.*;

public final class ScreenCoordssCoords extends ScreenCoordss<Coords> {
    public ScreenCoordssCoords() {
    }
    public ScreenCoordssCoords(AbsBasicMap<ScreenCoords,Coords> _other) {
        super(_other);
    }
    @Override
    protected Coords def() {
        return new Coords();
    }
}
