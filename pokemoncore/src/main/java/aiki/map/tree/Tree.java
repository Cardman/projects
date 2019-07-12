package aiki.map.tree;

import aiki.map.DataMap;
import aiki.map.places.Place;
import aiki.util.Coords;
import code.util.CustList;

public class Tree {

    private CustList<PlaceArea> places;

    public void initialize(DataMap _dataMap) {
        places = new CustList<PlaceArea>();
        for (Place p : _dataMap.getPlaces().values()) {
            PlaceArea placeArea_ = new PlaceArea();
            placeArea_.initialize(p);
            places.add(placeArea_);
        }
    }

    public boolean isValid(Coords _coords, boolean _accessible) {
        if (!places.isValidIndex(_coords.getNumberPlace())) {
            return false;
        }
        return places.get(_coords.getNumberPlace()).isValid(_coords,
                _accessible);
    }

    public PlaceArea getPlace(short _pl) {
        return places.get(_pl);
    }

    public CustList<PlaceArea> getPlaces() {
        return places;
    }
}
