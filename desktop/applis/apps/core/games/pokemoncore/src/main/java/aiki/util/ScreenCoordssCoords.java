package aiki.util;

import aiki.map.util.ScreenCoords;

public final class ScreenCoordssCoords extends ScreenCoordss<Coords> {
    public ScreenCoordssCoords() {
    }
    public ScreenCoordssCoords(CommonMap<ScreenCoords,Coords> _other) {
        super(_other);
    }
    @Override
    protected Coords def() {
        return new Coords();
    }
}
