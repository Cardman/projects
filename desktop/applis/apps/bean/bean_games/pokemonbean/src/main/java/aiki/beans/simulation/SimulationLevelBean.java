package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.comparators.ComparatorPoint;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DualFight;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.*;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import aiki.util.PointParam;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.TreeMap;
import code.util.core.IndexConstants;

public class SimulationLevelBean extends CommonBean {
    private TreeMap<Point,String> tiles;
    private int noFight;
    private String placeName;
    private int levelIndex;
    private boolean outside;
    private boolean road;
    private boolean pokemonCenter;
    private boolean gym;
    private boolean possibleMultiLayer;

    @Override
    public void beforeDisplaying() {
        levelIndex = IndexConstants.INDEX_NOT_FOUND_ELT;
        tiles = new TreeMap<Point, String>(new ComparatorPoint());
        noFight = getForms().getValInt(CST_NO_FIGHT);
        CustList<PlaceIndex> places_ = new CustList<PlaceIndex>();
        DataBase data_ = getDataBase();
        short i_ = 0;
        for (Place p: data_.getMap().getPlaces()) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i_);
            pl_.setPlace(p);
            places_.add(pl_);
            i_++;
        }
        places_.sortElts(new ComparatorPlaceIndex());
        possibleMultiLayer = false;
        road = false;
        gym = false;
        pokemonCenter = false;
        outside = false;
        if (getForms().contains(CST_INSIDE)) {
            Point ptInside_ = getForms().getValPt(CST_INSIDE);
            int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
            Place place_ = data_.getMap().getPlace(pl_);
            if (place_ instanceof City) {
                City city_ = (City) place_;
                if (city_.getBuildings().getVal(ptInside_) instanceof Gym) {
                    gym = true;
                }
                if (city_.getBuildings().getVal(ptInside_) instanceof PokemonCenter) {
                    pokemonCenter = true;
                }
            }
            placeName = place_.getName();
            for (PointParam<int[][]> pt_: data_.getLevelImage((short) pl_, IndexConstants.FIRST_INDEX, ptInside_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
        } else {
            outside = true;
            int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
            int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
            if (data_.getMap().getPlace(pl_) instanceof League) {
                possibleMultiLayer = true;
            }
            if (data_.getMap().getPlace(pl_) instanceof Cave) {
                possibleMultiLayer = true;
            }
            if (data_.getMap().getPlace(pl_) instanceof Road) {
                road = true;
            }
            placeName = data_.getMap().getPlace(pl_).getName();
            levelIndex = lev_;
            for (PointParam<int[][]> pt_: data_.getLevelImage((short) pl_, (byte) lev_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
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
    public static String cancel() {
        return CST_SIMULATION;
    }
    public String clickTile(int _index) {
        if (noFight < 0) {
            noFight = 0;
        }
        Point pt_ = tiles.getKey(_index);
        //Level level_ = (Level) getForms().getVal(LEVEL_MAP);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        //getForms().put(FROM_LIST, false);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            Point ptInside_ = getForms().getValPt(CST_INSIDE);
            Building b_ = c_.getBuildings().getVal(ptInside_);
            Gym g_ = (Gym) b_;
            if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace((short) pl_);
                coords_.setLevel(new LevelPoint());
                coords_.setInsideBuilding(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                getForms().put(CST_NO_FIGHT, noFight);
                return CST_SIMULATION;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace((short) pl_);
                coords_.setLevel(new LevelPoint());
                coords_.setInsideBuilding(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                getForms().put(CST_NO_FIGHT, noFight);
                return CST_SIMULATION;
            }
            return DataBase.EMPTY_STRING;
        }
        if (p_ instanceof Campaign) {
            Campaign c_ = (Campaign) p_;
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal((byte) lev_);
            if (l_.getDualFights().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace((short) pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) lev_);
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(CST_COORDS, coords_);
                getForms().put(CST_NO_FIGHT, noFight);
                return CST_SIMULATION;
            }
            if (l_.getCharacters().contains(pt_)) {
                CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
                if (char_ instanceof TrainerMultiFights) {
                    TrainerMultiFights tr_ = (TrainerMultiFights) char_;
                    if (tr_.getTeamsRewards().size() <= noFight) {
                        noFight = tr_.getTeamsRewards().size();
                        noFight--;
                    }
                    getForms().put(CST_NO_FIGHT, noFight);
                    Coords coords_ = new Coords();
                    coords_.setNumberPlace((short) pl_);
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex((byte) lev_);
                    coords_.getLevel().setPoint(new Point(pt_));
                    getForms().put(CST_COORDS, coords_);
                    //noFight
                    return CST_SIMULATION;
                }
            }
            for (Point ptKey_: l_.getDualFights().getKeys()) {
                DualFight d_ = l_.getDualFights().getVal(ptKey_);
                if (Point.eq(d_.getPt(), pt_)) {
                    Coords coords_ = new Coords();
                    coords_.setNumberPlace((short) pl_);
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex((byte) lev_);
                    coords_.getLevel().setPoint(new Point(ptKey_));
                    getForms().put(CST_NO_FIGHT, noFight);
                    getForms().put(CST_COORDS, coords_);
                    return CST_SIMULATION;
                }
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public boolean getPossibleMultiLayer() {
        return possibleMultiLayer;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getLevelIndex() {
        return levelIndex;
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

    public int getNoFight() {
        return noFight;
    }

    public void setNoFight(int _n) {
        this.noFight = _n;
    }

    public TreeMap<Point,String> getTiles() {
        return tiles;
    }
}