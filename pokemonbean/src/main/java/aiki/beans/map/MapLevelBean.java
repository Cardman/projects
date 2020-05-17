package aiki.beans.map;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorDirection;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.comparators.ComparatorPoint;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.db.DataBase;
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
import code.images.BaseSixtyFourUtil;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class MapLevelBean extends CommonBean {
    private TreeMap<Point,String> tiles;
    private boolean proponeLink;
    private boolean proponeTile;
    private boolean seeArea;
    private TreeMap<String, Boolean> dirs;
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
            for (EntryCust<Point, int[][]> pt_: data_.getLevelImage(pl_, lev_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
        }
        proponeLink = (Boolean)getForms().getVal(PROPONE_LINK);
        proponeTile = (Boolean)getForms().getVal(PROPONE_TILE);
        seeArea = (Boolean)getForms().getVal(SEE_AREA);
        dirs = new TreeMap<String, Boolean>(new ComparatorDirection());
        for (String s: getForms().getKeys()) {
            if (!s.startsWith(PROPONE_LINK_VAR)) {
                continue;
            }
            boolean b_ = (Boolean) getForms().getVal(s);
            if (!b_) {
                continue;
            }
            String dirStr_ = s.substring(PROPONE_LINK_VAR.length());
            dirs.put(dirStr_, true);
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
    public String clickTile() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        short plValue_ = pl_;
        byte levValue_ = lev_;
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(plValue_);
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
        }
        //getForms().put(FROM_LIST, false);
        CustList<Place> places_ = data_.getMap().getPlaces();
        int nb_ = places_.size();
        for (short p = 0; p < nb_; p++) {
            Place place_ = data_.getMap().getPlace(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            League l_ = (League) place_;
            Coords access_ = l_.getAccessCoords();
            if (!Numbers.eq(plValue_, access_.getNumberPlace())) {
                continue;
            }
            if (!Numbers.eq(levValue_, access_.getLevel().getLevelIndex())) {
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
            coords_.setNumberPlace(pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(levValue_);
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
            lp_.setLevelIndex(lev_);
            lp_.setPoint(pt_);
            if (c_.getLinksWithOtherPlaces().contains(lp_)) {
                Coords coords_ = c_.getLinksWithOtherPlaces().getVal(lp_).getCoords();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
            LevelCave level_ = (LevelCave) c_.getLevelsMap().getVal(lev_);
            if (level_.getLinksOtherLevels().contains(pt_)) {
                Coords coords_ = level_.getLinksOtherLevels().getVal(pt_).getCoords();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
        }
        if (p_ instanceof League) {
            League l_ = (League) p_;
            if (Point.eq(l_.getRooms().get(lev_).getAccessPoint(), pt_)) {
                if (lev_ < l_.getRooms().size() - 1) {
                    getForms().put(LEVEL_MAP_INDEX, (byte)(lev_ + 1));
                    return LEVEL;
                }
                Coords coords_ = data_.getMap().getBegin();
                getForms().put(LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(PLACE_MAP_INDEX, coords_.getNumberPlace());
                return LEVEL;
            }
            if (Point.eq(l_.getRooms().get(lev_).getTrainerCoords(), pt_)) {
                getForms().put(TRAINER, l_.getRooms().get(lev_).getTrainer());
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
                    if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                        getForms().put(TRAINER, g_.getIndoor().getGymTrainers().getVal(pt_));
                        return TRAINER_ONE_FIGHT;
                    }
                    if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                        getForms().put(TRAINER, g_.getIndoor().getGymLeader());
                        return TRAINER_ONE_FIGHT;
                    }
                }
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (pk_.getIndoor().getGerants().contains(pt_)) {
                        Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
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
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal(lev_);
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
    public String clickDirectedLink(Long _index) {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Direction dir_ = Direction.getDirectionByName(dirs.getKey(_index.intValue()));
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_);
        coords_.getLevel().setPoint(pt_);
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
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

    public boolean isUp(Long _index) {
        return Direction.getDirectionByName(dirs.getKey(_index.intValue())) == Direction.UP;
    }

    public boolean isDown(Long _index) {
        return Direction.getDirectionByName(dirs.getKey(_index.intValue())) == Direction.DOWN;
    }

    public boolean isLeft(Long _index) {
        return Direction.getDirectionByName(dirs.getKey(_index.intValue())) == Direction.LEFT;
    }

    public boolean isRight(Long _index) {
        return Direction.getDirectionByName(dirs.getKey(_index.intValue())) == Direction.RIGHT;
    }
    public String clickLink() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_);
        coords_.getLevel().setPoint(pt_);
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
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
    public String seeArea() {
        Point pt_ = (Point) getForms().getVal(CURRENT_TILE);
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
        }
        Coords current_ = new Coords();
        current_.setNumberPlace(pl_);
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex(lev_);
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        if (!app_.isVirtual()) {
            getForms().put(AREA, app_);
        }
        return AREA;
    }
    public String clickTileOnMap(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        getForms().put(CURRENT_TILE, pt_);
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Coords current_ = new Coords();
        current_.setNumberPlace(pl_);
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex(lev_);
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        boolean seeArea_ = false;
        if (!app_.isVirtual()) {
            getForms().put(AREA, app_);
            seeArea_ = true;
        }
        Place p_ = data_.getMap().getPlace(pl_);
        StringMap<Boolean> booleans_ = new StringMap<Boolean>();
        booleans_.put(SEE_AREA,seeArea_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_);
        coords_.getLevel().setPoint(pt_);
        if (p_ instanceof InitializedPlace) {
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
                    booleans_.put(StringList.concat(PROPONE_LINK_VAR,d.name()), points_.containsObj(d));
                }
            } else {
                for (Direction d: Direction.values()) {
                    booleans_.put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
                }
            }
        } else {
            booleans_.put(PROPONE_LINK,false);
            for (Direction d: Direction.values()) {
                booleans_.put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
            }
        }
        Coords coordsLoc_ = new Coords();
        coordsLoc_.setNumberPlace(pl_);
        coordsLoc_.setLevel(new LevelPoint());
        coordsLoc_.getLevel().setLevelIndex(lev_);
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
            coords_ = new Coords();
            coords_.setNumberPlace(pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex(lev_);
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
    public boolean withoutTitle(Long _index) {
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
    public boolean isAccessibleByBeatingSomeTrainers(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        Byte lev_ = (Byte) getForms().getVal(LEVEL_MAP_INDEX);
        Coords coords_ = new Coords();
        coords_.setNumberPlace(pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(lev_);
        coords_.getLevel().setPoint(pt_);
        DataBase data_ = (DataBase) getDataBase();
        return data_.getMap().getAccessCondition().contains(coords_);
    }
    public boolean isStorage(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (Point.eq(pk_.getIndoor().getStorageCoords(),pt_)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
    public boolean isHealer(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
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
    public boolean isHost(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
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
    public boolean isFossile(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
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
    public boolean isMoveTutors(Long _index) {
        Point pt_ = tiles.getKey(_index.intValue());
        Short pl_ = (Short) getForms().getVal(PLACE_MAP_INDEX);
        DataBase data_ = (DataBase) getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(INSIDE)) {
                Point ptInside_ = (Point) getForms().getVal(INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
                        return false;
                    }
                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
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

    public TreeMap<Point,String> getTiles() {
        return tiles;
    }

    public boolean getProponeTile() {
        return proponeTile;
    }

    public boolean getProponeLink() {
        return proponeLink;
    }

    public boolean getSeeArea() {
        return seeArea;
    }

    public TreeMap<String,Boolean> getDirs() {
        return dirs;
    }
}