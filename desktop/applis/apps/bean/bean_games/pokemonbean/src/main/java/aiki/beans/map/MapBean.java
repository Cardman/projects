package aiki.beans.map;

import aiki.beans.CommonBean;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.map.levels.Level;
import aiki.map.places.Place;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.core.IndexConstants;

public class MapBean extends CommonBean {
    private CustList<PlaceIndex> places;

    @Override
    public void beforeDisplaying() {
//        getForms().removeKey(CST_INSIDE);
        places = PlaceIndex.places(getDataBase());
    }
    public boolean isMultiLayer(int _index) {
        return layers(_index).size() > IndexConstants.ONE_ELEMENT;
    }
    public CustList<Level> layers(int _index) {
        Place pl_ = places.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        feedForms(_indexOne, _indexTwo, getForms());
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }
}