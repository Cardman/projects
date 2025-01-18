package aiki.beans;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.levels.Level;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.*;
import aiki.map.util.PlaceInterConnectCoords;
import aiki.util.Coords;
import aiki.util.Point;
import aiki.util.Points;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;

public abstract class AbsLevelBean extends CommonBean {
    private DictionaryComparator<Point,int[][]> tiles;
    private DictionaryComparator<Point,int[][]> whiteTiles;
    private String placeName;
    private int levelIndex;
    private boolean outside;
    private boolean road;
    private boolean pokemonCenter;
    private boolean gym;
    private boolean possibleMultiLayer;
    private CustList<AbsAreaApparition> wildPokemonAreas = new CustList<AbsAreaApparition>();
    private DictionaryComparator<Integer,String> neighbours;

    protected void initTiles(boolean _addBorder) {
        wildPokemonAreas = new CustList<AbsAreaApparition>();
        neighbours = DictionaryComparatorUtil.buildStringPlaces(getDataBase().getMap());
        levelIndex = IndexConstants.INDEX_NOT_FOUND_ELT;
        tiles = DictionaryComparatorUtil.buildPointString();
        whiteTiles = DictionaryComparatorUtil.buildPointString();
        DataBase data_ = getDataBase();
        possibleMultiLayer = false;
        road = false;
        gym = false;
        pokemonCenter = false;
        outside = false;
        Coords co_ = getForms().getValCoords(CST_COORDS);
        int pl_ = co_.getNumberPlace();
        Place place_ = data_.getMap().getPlace(pl_);
        placeName = place_.getName();
        if (place_ instanceof City&&co_.isInside()) {
            City city_ = (City) place_;
            Point ptInside_ = co_.getInsideBuilding();
            Building build_ = city_.getBuildings().getVal(ptInside_);
            gym = build_ instanceof Gym;
            pokemonCenter = build_ instanceof PokemonCenter;
        }
        int sideLength_ = data_.getMap().getSideLength();
        if (co_.isInside()) {
            Point ptInside_ = co_.getInsideBuilding();
            feedImages(data_.getLevelImage(pl_, IndexConstants.FIRST_INDEX, ptInside_), getTiles(),sideLength_, _addBorder);
            feedImages(data_.getBackLevelImage(pl_, IndexConstants.FIRST_INDEX, ptInside_), getWhiteTiles(),sideLength_, _addBorder);
        } else {
            outside = true;
            int lev_ = co_.getLevel().getLevelIndex();
            if (place_ instanceof League) {
                possibleMultiLayer = true;
            }
            if (place_ instanceof Cave) {
                possibleMultiLayer = true;
            }
            road = place_ instanceof Road;
            levelIndex = lev_;
            Level level_ = data_.getMap().getLevelByCoords(co_);
            if (level_ instanceof LevelWithWildPokemon) {
                wildPokemonAreas = ((LevelWithWildPokemon) level_).getWildPokemonAreas();
            }
            feedImages(data_.getLevelImage(pl_, lev_), getTiles(),sideLength_, _addBorder);
            feedImages(data_.getBackLevelImage(pl_, lev_), getWhiteTiles(),sideLength_, _addBorder);
        }
        if (place_ instanceof InitializedPlace) {
            for (PlaceInterConnectCoords n: ((InitializedPlace)place_).getPointsWithCitiesAndOtherRoads().entryList()){
                neighbours.put(n.getCoords().getNumberPlace(),getDataBase().getMap().getPlace(n.getCoords().getNumberPlace()).getName());
            }
        }
    }

    private void feedImages(Points<int[][]> _map, DictionaryComparator<Point, int[][]> _de, int _side, boolean _addBorder) {
        if (_addBorder) {
            DataBase.updateBorders(_map, _side);
        }
        for (EntryCust<Point,int[][]> pt_: _map.entryList()) {
            _de.put(pt_.getKey(), pt_.getValue());
        }
    }
    public String clickArea(int _index) {
        getForms().put(CST_AREA,getWildPokemonAreas().get(_index));
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML;
    }
    public String clickAreaOnMap(int _index) {
        Coords co_ = getForms().getValCoords(CST_COORDS);
        Point pt_ = getTiles().getKey(_index);
        Coords cp_ = new Coords(co_);
        cp_.getLevel().setPoint(pt_);
        getForms().put(CST_COORDS, cp_);
        DataBase data_ = getDataBase();
        AbsAreaApparition app_ = data_.getMap().getAreaByCoords(cp_);
        if (app_.isVirtual()) {
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
        }
        getForms().put(CST_AREA,app_);
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML;
    }
    public String clickNeighbour(int _index) {
        CommonBean.feedForms(neighbours.getKey(_index),0,getForms());
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

    public int getMapWidth() {
        int w_ = 0;
        int y_ = tiles.getKey(w_).gety();
        while (tiles.getKey(w_).gety() != y_+1) {
            w_++;
        }
        return w_;
    }
//    public boolean isFirstRow(int _index) {
//        if (_index == 0) {
//            return false;
//        }
//        Point pt_ = tiles.getKey(_index);
//        return pt_.getx() == IndexConstants.FIRST_INDEX;
//    }
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

    public CustList<AbsAreaApparition> getWildPokemonAreas() {
        return wildPokemonAreas;
    }

    public DictionaryComparator<Point, int[][]> getTiles() {
        return tiles;
    }

    public DictionaryComparator<Point, int[][]> getWhiteTiles() {
        return whiteTiles;
    }

    public DictionaryComparator<Integer, String> getNeighbours() {
        return neighbours;
    }
}
