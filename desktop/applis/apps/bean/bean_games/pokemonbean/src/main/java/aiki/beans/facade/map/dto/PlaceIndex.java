package aiki.beans.facade.map.dto;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.db.DataBase;
import aiki.map.places.Place;
import code.util.CustList;

public final class PlaceIndex {

    private Place place;
    private int index;

    public static CustList<PlaceIndex> places(DataBase _data) {
        CustList<PlaceIndex> places_ = new CustList<PlaceIndex>();
        CustList<Place> pls_ = _data.getMap().getPlaces();
        int s_ = pls_.size();
        for (int i = 0; i < s_; i++) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i);
            pl_.setPlace(pls_.get(i));
            places_.add(pl_);
        }
        places_.sortElts(new ComparatorPlaceIndex());
        return places_;
    }
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place _place) {
        place = _place;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}