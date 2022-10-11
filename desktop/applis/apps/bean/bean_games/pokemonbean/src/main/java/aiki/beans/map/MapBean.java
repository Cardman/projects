package aiki.beans.map;

import aiki.beans.CommonBean;
import aiki.beans.StringMapObject;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.enums.Direction;
import aiki.map.levels.Level;
import aiki.map.places.City;
import aiki.map.places.Place;
import code.util.CustList;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

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
        return clickMapLevel(_indexOne, _indexTwo, getForms());
    }

    public static String clickMapLevel(int _indexOne, int _indexTwo, StringMapObject _forms) {
        _forms.removeKey(CST_INSIDE);
        _forms.put(CST_LEVEL_MAP_INDEX, _indexTwo);
        _forms.put(CST_PLACE_MAP_INDEX, _indexOne);
        _forms.put(CST_PROPONE_LINK, false);
        _forms.put(CST_PROPONE_TILE, false);
        _forms.put(CST_SEE_AREA, false);
        for (Direction d: Direction.all()) {
            _forms.putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
        }
        return CST_LEVEL;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }
}