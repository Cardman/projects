package aiki.beans;

import aiki.map.places.Place;

public final class PlaceStruct extends ParamNatStruct<Place> {
    public PlaceStruct(Place _wildPk) {
        super(_wildPk);
    }

    public Place getWildPk() {
        return getInstance();
    }
}
