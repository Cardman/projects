package aiki.beans.map;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.places.City;
import aiki.map.places.Place;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Listable;

public class MapBean extends CommonBean {
    private CustList<PlaceIndex> places;

    @Override
    public void beforeDisplaying() {
        getForms().removeKey(INSIDE);
        places = new CustList<PlaceIndex>();
        DataBase data_ = (DataBase) getDataBase();
        for (Short i: data_.getMap().getPlaces().getKeys()) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i);
            pl_.setPlace(data_.getMap().getPlaces().getVal(i));
            places.add(pl_);
        }
        places.sortElts(new ComparatorPlaceIndex());
    }
    public boolean isMultiLayer(Long _index) {
        return layers(_index).size() > CustList.ONE_ELEMENT;
    }
    public CustList<Level> layers(Long _index) {
        Place pl_ = places.get(_index.intValue()).getPlace();
        return pl_.getLevelsList();
    }
    public boolean isCenter(Long _indexOne, Long _indexTwo) {
        return buildings(_indexOne).get(_indexTwo.intValue()) instanceof PokemonCenter;
    }
    public boolean isGym(Long _indexOne, Long _indexTwo) {
        return buildings(_indexOne).get(_indexTwo.intValue()) instanceof Gym;
    }
    public CustList<Building> buildings(Long _index) {
        Place pl_ = places.get(_index.intValue()).getPlace();
        if (!(pl_ instanceof City)) {
            return new CustList<Building>();
        }
        City c_ = (City) pl_;
        return c_.getBuildings().values();
    }
    public boolean isCity(Long _index) {
        return places.get(_index.intValue()).getPlace() instanceof City;
    }
    public String clickLevel(Long _indexOne, Long _indexTwo) {
        getForms().removeKey(INSIDE);
        getForms().put(LEVEL_MAP_INDEX, _indexTwo.byteValue());
        getForms().put(PLACE_MAP_INDEX, _indexOne.shortValue());
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
        }
        return LEVEL;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }
}