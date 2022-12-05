package aiki.beans;

import aiki.map.places.Place;
import code.bean.nat.NaNuSt;

public final class PlaceStruct extends NaNuSt {
    private final Place inst;
    public PlaceStruct(Place _wildPk) {
        inst=(_wildPk);
    }

    public Place getWildPk() {
        return inst;
    }
}
