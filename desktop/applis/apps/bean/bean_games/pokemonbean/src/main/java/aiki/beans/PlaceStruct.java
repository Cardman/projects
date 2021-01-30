package aiki.beans;

import aiki.map.places.Place;

public final class PlaceStruct extends ParamNatStruct<Place> {
    public PlaceStruct(Place _wildPk, String _className) {
        super(_wildPk,_className);
    }

    public Place getWildPk() {
        return getInstance();
    }
}
