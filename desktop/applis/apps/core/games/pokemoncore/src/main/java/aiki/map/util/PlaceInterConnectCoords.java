package aiki.map.util;

import aiki.util.Coords;

public final class PlaceInterConnectCoords {
    private final PlaceInterConnect placeInterConnect;
    private final Coords coords;

    public PlaceInterConnectCoords(PlaceInterConnect _placeInterConnect, Coords _coords) {
        this.placeInterConnect = _placeInterConnect;
        this.coords = _coords;
    }

    public PlaceInterConnect getPlaceInterConnect() {
        return placeInterConnect;
    }

    public Coords getCoords() {
        return coords;
    }
}
