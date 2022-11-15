package aiki.beans;

import aiki.beans.map.AikiBeansMapStd;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Level;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.*;
import aiki.map.util.PlaceInterConnectCoords;
import aiki.util.CommonParam;
import aiki.util.Coords;
import aiki.util.Point;
import aiki.util.Points;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class AbsLevelBean extends CommonBean {
    private DictionaryComparator<Point,String> tiles;
    private DictionaryComparator<Point,String> whiteTiles;
    private String placeName;
    private int levelIndex;
    private boolean outside;
    private boolean road;
    private boolean pokemonCenter;
    private boolean gym;
    private boolean possibleMultiLayer;
    private CustList<AreaApparition> wildPokemonAreas = new CustList<AreaApparition>();
    private DictionaryComparator<Short,String> neighbours;

    protected void initTiles() {
        wildPokemonAreas = new CustList<AreaApparition>();
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
        if (co_.isInside()) {
            Point ptInside_ = co_.getInsideBuilding();
            if (place_ instanceof City) {
                City city_ = (City) place_;
                Building build_ = city_.getBuildings().getVal(ptInside_);
                gym = build_ instanceof Gym;
                pokemonCenter = build_ instanceof PokemonCenter;
            }
            feedImages(data_.getLevelImage((short) pl_, IndexConstants.FIRST_INDEX, ptInside_), getTiles());
            feedImages(data_.getWhiteLevelImage((short) pl_, IndexConstants.FIRST_INDEX, ptInside_), getWhiteTiles());
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
            Coords c_ = new Coords();
            c_.setNumberPlace((short) pl_);
            c_.getLevel().setLevelIndex((byte) lev_);
            Level level_ = data_.getMap().getLevelByCoords(c_);
            if (level_ instanceof LevelWithWildPokemon) {
                wildPokemonAreas = ((LevelWithWildPokemon) level_).getWildPokemonAreas();
            }
            feedImages(data_.getLevelImage((short) pl_, (byte) lev_), getTiles());
            feedImages(data_.getWhiteLevelImage((short) pl_, (byte) lev_), getWhiteTiles());
        }
        if (place_ instanceof InitializedPlace) {
            for (PlaceInterConnectCoords n: ((InitializedPlace)place_).getPointsWithCitiesAndOtherRoads().entryList()){
                neighbours.put(n.getCoords().getNumberPlace(),getDataBase().getMap().getPlace(n.getCoords().getNumberPlace()).getName());
            }
        }
    }

    private static void feedImages(Points<int[][]> _map, DictionaryComparator<Point, String> _de) {
        for (CommonParam<Point,int[][]> pt_: _map.entryList()) {
            _de.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
        }
    }
    public String clickArea(int _index) {
        getForms().put(CST_AREA,getWildPokemonAreas().get(_index));
        return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_AREA_HTML;
    }
    public String clickNeighbour(int _index) {
        CommonBean.feedForms(neighbours.getKey(_index),0,getForms());
        return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
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

    public CustList<AreaApparition> getWildPokemonAreas() {
        return wildPokemonAreas;
    }

    public DictionaryComparator<Point, String> getTiles() {
        return tiles;
    }

    public DictionaryComparator<Point, String> getWhiteTiles() {
        return whiteTiles;
    }

    public DictionaryComparator<Short, String> getNeighbours() {
        return neighbours;
    }
}
