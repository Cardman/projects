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
import aiki.map.places.Campaign;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.TreeMap;

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
        levelIndex = CustList.INDEX_NOT_FOUND_ELT;
        tiles = new TreeMap<Point, String>(new ComparatorPoint());
        noFight = (Integer) getForms().getVal(NO_FIGHT);
        CustList<PlaceIndex> places_ = new CustList<PlaceIndex>();
        DataBase data_ = (DataBase) getDataBase();
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
        if (getForms().contains(INSIDE)) {
            Point ptInside_ = (Point) getForms().getVal(INSIDE);
            Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
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
            for (EntryCust<Point,int[][]> pt_: data_.getLevelImage(pl_, CustList.FIRST_INDEX, ptInside_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
        } else {
            outside = true;
            Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
            Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
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
            levelIndex = lev_.intValue();
            for (EntryCust<Point,int[][]> pt_: data_.getLevelImage(pl_, lev_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
        }
    }
    public int getMapWidth() {
        int w_ = 0;
        while (tiles.getKey(w_).gety() != CustList.SECOND_INDEX) {
            w_++;
        }
        return w_;
    }
    public boolean isFirstRow(Long _index) {
        if (_index.intValue() == 0) {
            return false;
        }
        Point pt_ = tiles.getKey(_index.intValue());
        return pt_.getx() == CustList.FIRST_INDEX;
    }
    public static String cancel() {
        return SIMULATION;
    }
    public String clickTile(Long _index) {
        if (noFight < 0) {
            noFight = 0;
        }
        Point pt_ = tiles.getKey(_index.intValue());
        //Level level_ = (Level) getForms().getVal(LEVEL_MAP);
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        //getForms().put(FROM_LIST, false);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            Point ptInside_ = (Point) getForms().getVal(INSIDE);
            Building b_ = c_.getBuildings().getVal(ptInside_);
            Gym g_ = (Gym) b_;
            if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.setInsideBuilding(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(COORDS, coords_);
                getForms().put(NO_FIGHT, noFight);
                return SIMULATION;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.setInsideBuilding(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(COORDS, coords_);
                getForms().put(NO_FIGHT, noFight);
                return SIMULATION;
            }
            return DataBase.EMPTY_STRING;
        }
        if (p_ instanceof Campaign) {
            Campaign c_ = (Campaign) p_;
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal(lev_);
            if (l_.getDualFights().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex(lev_);
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(COORDS, coords_);
                getForms().put(NO_FIGHT, noFight);
                return SIMULATION;
            }
            if (l_.getCharacters().contains(pt_)) {
                CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
                if (char_ instanceof TrainerMultiFights) {
                    TrainerMultiFights tr_ = (TrainerMultiFights) char_;
                    if (tr_.getTeamsRewards().size() <= noFight) {
                        noFight = tr_.getTeamsRewards().size();
                        noFight--;
                    }
                    getForms().put(NO_FIGHT, noFight);
                    Coords coords_ = new Coords();
                    coords_.setNumberPlace(pl_);
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex(lev_);
                    coords_.getLevel().setPoint(new Point(pt_));
                    getForms().put(COORDS, coords_);
                    //noFight
                    return SIMULATION;
                }
            }
            for (Point ptKey_: l_.getDualFights().getKeys()) {
                DualFight d_ = l_.getDualFights().getVal(ptKey_);
                if (Point.eq(d_.getPt(), pt_)) {
                    Coords coords_ = new Coords();
                    coords_.setNumberPlace(pl_);
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex(lev_);
                    coords_.getLevel().setPoint(new Point(ptKey_));
                    getForms().put(NO_FIGHT, noFight);
                    getForms().put(COORDS, coords_);
                    return SIMULATION;
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

    public TreeMap<Point,String> getTiles() {
        return tiles;
    }
}