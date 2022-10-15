package aiki.beans;

import aiki.beans.facade.map.dto.PlaceIndex;

public final class PlaceIndexStruct extends ParamNatStruct<PlaceIndex> {

    public PlaceIndexStruct(PlaceIndex _placeIndex) {
        super(_placeIndex);
    }

    public PlaceIndex getPlaceIndex() {
        return getInstance();
    }
}
