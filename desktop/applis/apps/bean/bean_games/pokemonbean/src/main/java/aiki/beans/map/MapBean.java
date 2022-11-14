package aiki.beans.map;

import aiki.beans.CommonBean;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.levels.Level;
import aiki.map.places.City;
import aiki.map.places.Place;
import code.util.CustList;
import code.util.core.IndexConstants;

public class MapBean extends CommonBean {
    private CustList<PlaceIndex> places;

    @Override
    public void beforeDisplaying() {
        getForms().removeKey(CST_INSIDE);
        places = PlaceIndex.places(getDataBase());
    }
    public boolean isMultiLayer(int _index) {
        return layers(_index).size() > IndexConstants.ONE_ELEMENT;
    }
    public CustList<Level> layers(int _index) {
        Place pl_ = places.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    public boolean isCenter(int _indexOne, int _indexTwo) {
        return buildings(_indexOne).get(_indexTwo) instanceof PokemonCenter;
    }
    public boolean isGym(int _indexOne, int _indexTwo) {
        return buildings(_indexOne).get(_indexTwo) instanceof Gym;
    }
    public CustList<Building> buildings(int _index) {
        Place pl_ = places.get(_index).getPlace();
        if (!(pl_ instanceof City)) {
            return new CustList<Building>();
        }
        City c_ = (City) pl_;
        return c_.getBuildings().values();
    }
    public boolean isCity(int _index) {
        return places.get(_index).getPlace() instanceof City;
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        feedForms(_indexOne, _indexTwo, getForms());
        return CST_LEVEL;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }
}