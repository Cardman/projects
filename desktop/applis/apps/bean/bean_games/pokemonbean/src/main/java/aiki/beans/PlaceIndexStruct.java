package aiki.beans;

import aiki.beans.facade.map.dto.PlaceIndex;

public final class PlaceIndexStruct extends ParamNatStruct<PlaceIndex> {

    protected PlaceIndexStruct(PlaceIndex _placeIndex,String _className) {
        super(_placeIndex,_className);
    }

    public PlaceIndex getPlaceIndex() {
        return getInstance();
    }
}
