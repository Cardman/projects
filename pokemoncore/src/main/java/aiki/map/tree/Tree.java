package aiki.map.tree;
import code.util.CustList;
import aiki.map.DataMap;
import aiki.map.places.Place;
import aiki.util.Coords;

public class Tree {

    private CustList<PlaceArea> places;

    public void initialize(DataMap _dataMap) {
        places = new CustList<PlaceArea>();
        for (Place p: _dataMap.getPlaces().values()) {
            PlaceArea placeArea_ = new PlaceArea();
            placeArea_.initialize(p);
            places.add(placeArea_);
        }
    }

    public boolean isValid(Coords _coords,boolean _accessible) {
        return places.get(_coords.getNumberPlace()).isValid(_coords,_accessible);
    }

    public PlaceArea getPlace(short _pl) {
        return places.get(_pl);
    }

    public CustList<PlaceArea> getPlaces() {
        return places;
    }
}
