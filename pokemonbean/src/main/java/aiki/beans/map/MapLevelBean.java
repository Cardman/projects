package aiki.beans.map;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorDirection;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.comparators.ComparatorPoint;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.util.PlaceInterConnect;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;

public class MapLevelBean extends CommonBean {

    @Accessible
    private TreeMap<Point,String> tiles;

    @Accessible
    private boolean proponeLink;

    @Accessible
    private boolean proponeTile;

    @Accessible
    private boolean seeArea;

    @Accessible
    private TreeMap<Direction, Boolean> dirs;

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
            ObjectMap<Point,String> map_ = data_.getLevelImage(pl_.shortValue(), (byte) CustList.FIRST_INDEX, ptInside_);
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
        proponeLink = (Boolean)getForms().getVal(PROPONE_LINK);
        proponeTile = (Boolean)getForms().getVal(PROPONE_TILE);
        seeArea = (Boolean)getForms().getVal(SEE_AREA);
        dirs = new TreeMap<Direction, Boolean>(new ComparatorDirection());
        for (String s: getForms().getKeys()) {
            if (!s.startsWith(PROPONE_LINK_VAR)) {
                continue;
            }
            boolean b_ = (Boolean) getForms().getVal(s);
            if (!b_) {
                continue;
            }
            String dirStr_ = s.substring(PROPONE_LINK_VAR.length());
            Direction dir_ = Direction.valueOf(dirStr_);
            dirs.put(dir_, b_);
        }
    }

    @Accessible
    private boolean isFirstRow(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        return pt_.getx() == CustList.FIRST_INDEX;
    }

    @Accessible
    private String clickTile() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(PROPONE_LINK_VAR+d, false);
        }
        //getForms().put(FROM_LIST, false);
        for (Short p: data_.getMap().getPlaces().getKeys()) {
            Place place_ = data_.getMap().getPlaces().getVal(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            League l_ = (League) place_;
            Coords access_ = l_.getAccessCoords();
            if (!Numbers.eq(pl_, access_.getNumberPlace())) {
                continue;
            }
            if (!Numbers.eq(lev_, access_.getLevel().getLevelIndex())) {
                continue;
            }
            if (!Point.eq(pt_, access_.getLevel().getPoint())) {
                continue;
            }
            getForms().put(LEVEL_MAP_INDEX, CustList.FIRST_INDEX);
            getForms().put(PLACE_MAP_INDEX, p);
            return LEVEL;
        }
        if (p_ instanceof InitializedPlace) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(pl_.shortValue());
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(lev_.byteValue());
            coords_.getLevel().setPoint(pt_);
            InitializedPlace i_ = (InitializedPlace) p_;
            if (i_.getLinksWithCaves().contains(pt_)) {
                Coords c_ = i_.getLinksWithCaves().getVal(pt_).getCoords();
                getForms().put(LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, c_.getNumberPlace());
                return LEVEL;
            }
        }
        if (p_ instanceof Cave) {
            Cave c_ = (Cave) p_;
            LevelPoint lp_ = new LevelPoint();
            lp_.setLevelIndex(lev_.byteValue());
            lp_.setPoint(pt_);
            if (c_.getLinksWithOtherPlaces().contains(lp_)) {
                Coords coords_ = c_.getLinksWithOtherPlaces().getVal(lp_).getCoords();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
            LevelCave level_ = (LevelCave) c_.getLevels().getVal(lev_.byteValue());
            if (level_.getLinksOtherLevels().contains(pt_)) {
                Coords coords_ = level_.getLinksOtherLevels().getVal(pt_).getCoords();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
        }
        if (p_ instanceof League) {
            League l_ = (League) p_;
            if (Point.eq(l_.getRooms().get(lev_.byteValue()).getAccessPoint(), pt_)) {
                if (lev_.byteValue() < l_.getRooms().size() - 1) {
                    getForms().put(LEVEL_MAP_INDEX, lev_.byteValue() + 1);
                    return LEVEL;
                }
                Coords coords_ = data_.getMap().getBegin();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
            if (Point.eq(l_.getRooms().get(lev_.byteValue()).getTrainerCoords(), pt_)) {
                getForms().put(TRAINER, l_.getRooms().get(lev_.byteValue()).getTrainer());
                return TRAINER_ONE_FIGHT;
            }
        }
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (Point.eq(b_.getExitCity(), pt_)) {
                    getForms().removeKey(INSIDE);
                    return LEVEL;
                }
                if (b_ instanceof Gym) {
                    Gym g_ = (Gym) b_;
                    if (g_.getLevel().getGymTrainers().contains(pt_)) {
                        getForms().put(TRAINER, g_.getLevel().getGymTrainers().getVal(pt_));
                        return TRAINER_ONE_FIGHT;
                    }
                    if (Point.eq(g_.getLevel().getGymLeaderCoords(), pt_)) {
                        getForms().put(TRAINER, g_.getLevel().getGymLeader());
                        return TRAINER_ONE_FIGHT;
                    }
                }
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (pk_.getLevel().getGerants().contains(pt_)) {
                        Person pers_ = pk_.getLevel().getGerants().getVal(pt_);
                        if (pers_ instanceof Seller) {
                            Seller seller_ = (Seller) pers_;
                            if (!seller_.getItems().isEmpty()) {
                                getForms().put(SELLER, seller_);
                                return SELLER;
                            }
                            if (!seller_.getTm().isEmpty()) {
                                getForms().put(SELLER, seller_);
                                return SELLER;
                            }
                        }
                    }
                }
                return DataBase.EMPTY_STRING;
            }
            if (c_.getBuildings().contains(pt_)) {
                getForms().put(INSIDE, pt_);
                return LEVEL;
            }
        }
        if (p_ instanceof Campaign) {
            Campaign c_ = (Campaign) p_;
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevels().getVal(lev_.byteValue());
            if (l_.getDualFights().contains(pt_)) {
                getForms().put(TRAINER, l_.getDualFights().getVal(pt_).getFoeTrainer());
                getForms().put(ALLY, l_.getDualFights().getVal(pt_).getAlly());
                return DUAL;
            }
            if (l_.getItems().contains(pt_)) {
                String item_ = l_.getItems().getVal(pt_);
                getForms().put(ITEM, item_);
                Item it_ = data_.getItem(item_);
                if (it_ instanceof Ball) {
                    return BALL;
                }
                if (it_ instanceof Berry) {
                    return BERRY;
                }
                if (it_ instanceof Boost) {
                    return BOOST;
                }
                if (it_ instanceof EvolvingItem) {
                    return EVOLVINGITEM;
                }
                if (it_ instanceof EvolvingStone) {
                    return EVOLVINGSTONE;
                }
                if (it_ instanceof Fossil) {
                    return FOSSIL;
                }
                if (it_ instanceof HealingHpStatus) {
                    return HEALINGHPSTATUS;
                }
                if (it_ instanceof HealingStatus) {
                    return HEALINGSTATUS;
                }
                if (it_ instanceof HealingHp) {
                    return HEALINGHP;
                }
                if (it_ instanceof HealingPp) {
                    return HEALINGPP;
                }
                if (it_ instanceof HealingItem) {
                    return HEALINGITEM;
                }
                if (it_ instanceof ItemForBattle) {
                    return ITEMFORBATTLE;
                }
                if (it_ instanceof Repel) {
                    return REPEL;
                }
                if (it_ instanceof SellingItem) {
                    return SELLINGITEM;
                }
                return ITEM;
            }
            if (l_.getTm().contains(pt_)) {
                Short tm_ = l_.getTm().getVal(pt_);
                getForms().put(MOVE, data_.getTm().getVal(tm_));
                return MOVE;
            }
            if (l_.getHm().contains(pt_)) {
                Short tm_ = l_.getHm().getVal(pt_);
                getForms().put(MOVE, data_.getHm().getVal(tm_));
                return MOVE;
            }
            if (l_.getCharacters().contains(pt_)) {
                CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
                if (char_ instanceof TrainerMultiFights) {
                    getForms().put(TRAINER, char_);
                    return TRAINER_MULTI_FIGHT;
                }
                if (char_ instanceof DealerItem) {
                    getForms().put(DEALER, char_);
                    return DEALER;
                }
            }
            for (Point ptKey_: l_.getDualFights().getKeys()) {
                DualFight d_ = l_.getDualFights().getVal(ptKey_);
                if (Point.eq(d_.getPt(), pt_)) {
                    getForms().put(TRAINER, l_.getDualFights().getVal(ptKey_).getFoeTrainer());
                    getForms().put(ALLY, l_.getDualFights().getVal(ptKey_).getAlly());
                    return DUAL;
                }
            }
            if (l_.containsPokemon(pt_)) {
                getForms().put(LEG_PK, l_.getPokemon(pt_));
                return LEG_PK;
            }
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private String clickLink(Long _index) {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Direction dir_ = dirs.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_.shortValue());
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_.byteValue());
        coords_.getLevel().setPoint(pt_);
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(PROPONE_LINK_VAR+d, false);
        }
        InitializedPlace i_ = (InitializedPlace) p_;
        //p_.getLevelByCoords(coords_).get
        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (p.getDir() != dir_) {
                continue;
            }
            if (Point.eq(p.getSource(), pt_)) {
                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                getForms().put(LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, c_.getNumberPlace());
                return LEVEL;
            }
        }
        return LEVEL;
    }

    @Accessible
    private String clickLink() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_.shortValue());
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_.byteValue());
        coords_.getLevel().setPoint(pt_);
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(PROPONE_LINK_VAR+d, false);
        }
        InitializedPlace i_ = (InitializedPlace) p_;
        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (Point.eq(p.getSource(), pt_)) {
                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                getForms().put(LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, c_.getNumberPlace());
                return LEVEL;
            }
        }
        return LEVEL;
    }

    @Accessible
    private String seeArea() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(PROPONE_LINK_VAR+d, false);
        }
        Coords current_ = new Coords();
        current_.setNumberPlace(pl_.shortValue());
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex(lev_.byteValue());
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        if (!app_.isVirtual()) {
            getForms().put(AREA, app_);
        }
        return AREA;
    }

    @Accessible
    private String clickTile(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        getForms().put(CURRENT_TILE, pt_);
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Coords current_ = new Coords();
        current_.setNumberPlace(pl_.shortValue());
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex(lev_.byteValue());
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        boolean seeArea_ = false;
        if (!app_.isVirtual()) {
            getForms().put(AREA, app_);
            seeArea_ = true;
        }
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        StringMap<Boolean> booleans_ = new StringMap<Boolean>();
        booleans_.put(SEE_AREA,seeArea_);
        try {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(pl_.shortValue());
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(lev_.byteValue());
            coords_.getLevel().setPoint(pt_);
            InitializedPlace i_ = (InitializedPlace) p_;
            EnumList<Direction> points_ = new EnumList<Direction>();
            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
                if (Point.eq(p.getSource(), pt_)) {
                    points_.add(p.getDir());
                }
            }
            booleans_.put(PROPONE_LINK,!points_.isEmpty());
            if (points_.size() > DataBase.ONE_POSSIBLE_CHOICE) {
                for (Direction d: Direction.values()) {
                    booleans_.put(PROPONE_LINK_VAR+d, points_.containsObj(d));
                }
            } else {
                for (Direction d: Direction.values()) {
                    booleans_.put(PROPONE_LINK_VAR+d, false);
                }
            }
        } catch (RuntimeException _0) {
            booleans_.put(PROPONE_LINK,false);
            for (Direction d: Direction.values()) {
                booleans_.put(PROPONE_LINK_VAR+d, false);
            }
        }
        Coords coordsLoc_ = new Coords();
        coordsLoc_.setNumberPlace(pl_.shortValue());
        coordsLoc_.setLevel(new LevelPoint());
        coordsLoc_.getLevel().setLevelIndex(lev_.byteValue());
        coordsLoc_.getLevel().setPoint(pt_);
        booleans_.put(PROPONE_TILE,!data_.getMap().isEmptyForAdding(coordsLoc_));
        int nbTrue_ = CustList.SIZE_EMPTY;
        for (boolean b: booleans_.values()) {
            if (b) {
                nbTrue_++;
            }
        }
        if (nbTrue_ > DataBase.ONE_POSSIBLE_CHOICE) {
            for (EntryCust<String, Boolean> e: booleans_.entryList()) {
                getForms().put(e.getKey(), e.getValue());
            }
            return LEVEL;
        }
        String return_ = clickTile();
        if (!return_.isEmpty()) {
            return return_;
        }
        if (p_ instanceof InitializedPlace && !getForms().contains(INSIDE)) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace(pl_.shortValue());
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(lev_.byteValue());
            coords_.getLevel().setPoint(pt_);
            InitializedPlace i_ = (InitializedPlace) p_;
            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
                if (Point.eq(p.getSource(), pt_)) {
                    Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                    getForms().put(LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                    getForms().put(PLACE_MAP_INDEX, c_.getNumberPlace());
                    return LEVEL;
                }
            }
        }
        if (p_ instanceof Campaign) {
            if (!app_.isVirtual()) {
                getForms().put(AREA, app_);
                return AREA;
            }
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private boolean withoutTitle(Long _index) {
        if (isStorage(_index)) {
            return false;
        }
        if (isHealer(_index)) {
            return false;
        }
        if (isFossile(_index)) {
            return false;
        }
        if (isHost(_index)) {
            return false;
        }
        if (isMoveTutors(_index)) {
            return false;
        }
        return true;
    }

    @Accessible
    private boolean isAccessibleByBeatingSomeTrainers(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        Number lev_ = (Number) getForms().getVal(LEVEL_MAP_INDEX);
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_.shortValue());
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_.byteValue());
        coords_.getLevel().setPoint(pt_);
        DataBase data_ = (DataBase) getDataBase();
        return data_.getMap().getAccessCondition().contains(coords_);
    }

    @Accessible
    private boolean isStorage(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (Point.eq(pk_.getLevel().getStorageCoords(),pt_)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Accessible
    private boolean isHealer(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getLevel().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getLevel().getGerants().getVal(pt_);
                    if (!(pers_ instanceof GerantPokemon)) {
                        return false;
                    }
                    GerantPokemon g_ = (GerantPokemon) pers_;
                    if (g_.getGerance() == GeranceType.HEAL) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Accessible
    private boolean isHost(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getLevel().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getLevel().getGerants().getVal(pt_);
                    if (!(pers_ instanceof GerantPokemon)) {
                        return false;
                    }
                    GerantPokemon g_ = (GerantPokemon) pers_;
                    if (g_.getGerance() == GeranceType.HOST) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Accessible
    private boolean isFossile(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getLevel().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getLevel().getGerants().getVal(pt_);
                    if (!(pers_ instanceof GerantPokemon)) {
                        return false;
                    }
                    GerantPokemon g_ = (GerantPokemon) pers_;
                    if (g_.getGerance() == GeranceType.FOSSILE) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    @Accessible
    private boolean isMoveTutors(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Number pl_ = (Number) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlaces().getVal(pl_.shortValue());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getLevel().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getLevel().getGerants().getVal(pt_);
                    if (!(pers_ instanceof Seller)) {
                        return false;
                    }
                    Seller g_ = (Seller) pers_;
                    if (g_.getSell() == SellType.MOVE) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    /*@Accessible
    private String getTrDir(Long _index) {
        Direction dir_ = dirs.getKey(_index.intValue());

    }*/
}
