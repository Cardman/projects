package aiki.beans;

import aiki.beans.facade.map.dto.PlaceIndex;
import code.bean.nat.NaNuSt;

public final class PlaceIndexStruct extends NaNuSt {
    private final PlaceIndex inst;
    public PlaceIndexStruct(PlaceIndex _placeIndex) {
        inst=(_placeIndex);
    }

    public PlaceIndex getPlaceIndex() {
        return inst;
    }
}
