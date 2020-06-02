package aiki.beans.facade.map.dto;
import aiki.map.places.Place;

public final class PlaceIndex {

    private Place place;
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