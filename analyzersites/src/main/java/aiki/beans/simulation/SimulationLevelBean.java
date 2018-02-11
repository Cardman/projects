package aiki.beans.simulation;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.comparators.ComparatorPoint;
import aiki.beans.facade.map.dto.PlaceIndex;
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

public class SimulationLevelBean extends CommonBean {

    @Accessible
    private TreeMap<Point,String> tiles;

    @Accessible
    private int noFight;

    @Accessible
    private String placeName;

    @Accessible
    private int levelIndex;

    @Accessible
    private boolean outside;

    @Accessible
    private boolean road;

    @Accessible
    private boolean pokemonCenter;

    @Accessible
    private boolean gym;

    @Accessible
    private boolean possibleMultiLayer;

    @Override
    public void beforeDisplaying() {
        levelIndex = CustList.INDEX_NOT_FOUND_ELT;
        tiles = new TreeMap<Point, String>(new ComparatorPoint());
        noFight = (Integer) getForms().getVal(NO_FIGHT);
        CustList<PlaceIndex> places_ = new CustList<PlaceIndex>();
        DataBase data_ = (DataBase) getDataBase();
        for (Short i: data_.getMap().getPlaces().getKeys()) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i);
            pl_.setPlace(data_.getMap().getPlaces().getVal(i));
            places_.add(pl_);
        }
        places_.sortElts(new ComparatorPlaceIndex());
        possibleMultiLayer = false;
        road = false;
        gym = false;
        pokemonCenter = false;
        outside = false;
        if (getForms().contains(INSIDE)) {
            Point ptInside_ = (Point) getForms().getVal(INSIDE);
            Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
            Place place_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
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
            ObjectMap<Point,String> map_ = data_.getLevelImage(pl_.shortValue(), CustList.FIRST_INDEX, ptInside_);
            for (Point pt_: map_.getKeys()) {
                String s_ = map_.getVal(pt_);
                tiles.put(pt_, ConverterBufferedImage.surroundImage(s_));
            }
        } else {
            outside = true;
            Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
            Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
            if (data_.getMap().getPlaces().getVal(pl_.shortValue()) instanceof League) {
                possibleMultiLayer = true;
            }
            if (data_.getMap().getPlaces().getVal(pl_.shortValue()) instanceof Cave) {
                possibleMultiLayer = true;
            }
            if (data_.getMap().getPlaces().getVal(pl_.shortValue()) instanceof Road) {
                road = true;
            }
            placeName = data_.getMap().getPlaces().getVal(pl_.shortValue()).getName();
            levelIndex = lev_.intValue();
            ObjectMap<Point,String> map_ = data_.getLevelImage(pl_.shortValue(), lev_.byteValue());
            for (Point pt_: map_.getKeys()) {
                String s_ = map_.getVal(pt_);
                tiles.put(pt_, ConverterBufferedImage.surroundImage(s_));
            }
        }
    }

    @Accessible
    private boolean isFirstRow(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        return pt_.getx() == CustList.FIRST_INDEX;
    }

    @Accessible
    private static String cancel() {
        return SIMULATION;
    }

    @Accessible
    private String clickTile(Long _index) {
        if (noFight < 0) {
            noFight = 0;
        }
        Point pt_ = tiles.getKey(_index.intValue());
        //Level level_ = (Level) getForms().getVal(LEVEL_MAP);
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        //getForms().put(FROM_LIST, false);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            Point ptInside_ = (Point) getForms().getVal(INSIDE);
            Building b_ = c_.getBuildings().getVal(ptInside_);
            Gym g_ = (Gym) b_;
            if (g_.getLevel().getGymTrainers().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_.shortValue());
                coords_.setLevel(new LevelPoint());
                coords_.setInsideBuilding(new Point(ptInside_));
                coords_.getLevel().setPoint(new Point(pt_));
                getForms().put(COORDS, coords_);
                getForms().put(NO_FIGHT, noFight);
                return SIMULATION;
            }
            if (Point.eq(g_.getLevel().getGymLeaderCoords(), pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_.shortValue());
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
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevels().getVal(lev_.byteValue());
            if (l_.getDualFights().contains(pt_)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(pl_.shortValue());
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex(lev_.byteValue());
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
                    coords_.setNumberPlace(pl_.shortValue());
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex(lev_.byteValue());
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
                    coords_.setNumberPlace(pl_.shortValue());
                    coords_.setLevel(new LevelPoint());
                    coords_.getLevel().setLevelIndex(lev_.byteValue());
                    coords_.getLevel().setPoint(new Point(ptKey_));
                    getForms().put(NO_FIGHT, noFight);
                    getForms().put(COORDS, coords_);
                    return SIMULATION;
                }
            }
        }
        return DataBase.EMPTY_STRING;
    }
}
