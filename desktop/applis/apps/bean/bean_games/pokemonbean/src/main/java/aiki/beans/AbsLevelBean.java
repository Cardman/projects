package aiki.beans;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.places.*;
import aiki.util.CommonParam;
import aiki.util.Point;
import aiki.util.Points;
import code.images.BaseSixtyFourUtil;
import code.util.core.IndexConstants;

public abstract class AbsLevelBean extends CommonBean {
    private DictionaryComparator<Point,String> tiles;
    private String placeName;
    private int levelIndex;
    private boolean outside;
    private boolean road;
    private boolean pokemonCenter;
    private boolean gym;
    private boolean possibleMultiLayer;

    protected void initTiles() {
        levelIndex = IndexConstants.INDEX_NOT_FOUND_ELT;
        tiles = DictionaryComparatorUtil.buildPointString();
        DataBase data_ = getDataBase();
        possibleMultiLayer = false;
        road = false;
        gym = false;
        pokemonCenter = false;
        outside = false;
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        Place place_ = data_.getMap().getPlace(pl_);
        placeName = place_.getName();
        if (getForms().contains(CST_INSIDE)) {
            Point ptInside_ = getForms().getValPt(CST_INSIDE);
            if (place_ instanceof City) {
                City city_ = (City) place_;
                Building build_ = city_.getBuildings().getVal(ptInside_);
                gym = build_ instanceof Gym;
                pokemonCenter = build_ instanceof PokemonCenter;
            }
            feedImages(data_.getLevelImage((short) pl_, IndexConstants.FIRST_INDEX, ptInside_));
        } else {
            outside = true;
            int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
            if (place_ instanceof League) {
                possibleMultiLayer = true;
            }
            if (place_ instanceof Cave) {
                possibleMultiLayer = true;
            }
            road = place_ instanceof Road;
            levelIndex = lev_;
            feedImages(data_.getLevelImage((short) pl_, (byte) lev_));
        }
    }

    private void feedImages(Points<int[][]> _map) {
        for (CommonParam<Point,int[][]> pt_: _map.entryList()) {
            tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
        }
    }

    public int getMapWidth() {
        int w_ = 0;
        while (tiles.getKey(w_).gety() != IndexConstants.SECOND_INDEX) {
            w_++;
        }
        return w_;
    }
    public boolean isFirstRow(int _index) {
        if (_index == 0) {
            return false;
        }
        Point pt_ = tiles.getKey(_index);
        return pt_.getx() == IndexConstants.FIRST_INDEX;
    }
    public int getLevelIndex() {
        return levelIndex;
    }

    public String getPlaceName() {
        return placeName;
    }

    public boolean getPossibleMultiLayer() {
        return possibleMultiLayer;
    }

    public boolean getOutside() {
        return outside;
    }

    public boolean getRoad() {
        return road;
    }

    public boolean getGym() {
        return gym;
    }

    public boolean getPokemonCenter() {
        return pokemonCenter;
    }

    public DictionaryComparator<Point, String> getTiles() {
        return tiles;
    }
}
