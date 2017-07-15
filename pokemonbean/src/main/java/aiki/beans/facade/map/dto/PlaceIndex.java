package aiki.beans.facade.map.dto;
import code.bean.Accessible;
import aiki.map.places.Place;

public class PlaceIndex {

    private Place place;

    @Accessible
    private int index;

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
