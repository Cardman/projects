package aiki.beans.map;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorDirection;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.comparators.ComparatorPoint;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.db.DataBase;
import aiki.fight.items.*;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.*;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.*;
import aiki.map.util.PlaceInterConnect;
import aiki.util.*;
import code.images.BaseSixtyFourUtil;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class MapLevelBean extends CommonBean {
    private TreeMap<Point,String> tiles;
    private boolean proponeLink;
    private boolean proponeTile;
    private boolean seeArea;
    private TreeMap<String, BoolVal> dirs;
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
            for (CommonParam<Point,int[][]> pt_: data_.getLevelImage((short) pl_, IndexConstants.FIRST_INDEX, ptInside_).entryList()) {
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
            for (CommonParam<Point,int[][]> pt_: data_.getLevelImage((short) pl_, (byte) lev_).entryList()) {
                tiles.put(pt_.getKey(), BaseSixtyFourUtil.getStringByImage(pt_.getValue()));
            }
        }
        proponeLink = getForms().getValBool(CST_PROPONE_LINK);
        proponeTile = getForms().getValBool(CST_PROPONE_TILE);
        seeArea = getForms().getValBool(CST_SEE_AREA);
        dirs = new TreeMap<String, BoolVal>(new ComparatorDirection());
        for (EntryCust<String, BoolVal> s: getForms().getMapBoolean().entryList()) {
            if (!s.getKey().startsWith(CST_PROPONE_LINK_VAR) || s.getValue() != BoolVal.TRUE) {
                continue;
            }
            String dirStr_ = s.getKey().substring(CST_PROPONE_LINK_VAR.length());
            dirs.put(dirStr_, BoolVal.TRUE);
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
    public String clickTile() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), false);
        }
        //getForms().put(FROM_LIST, false);
        CustList<Place> places_ = data_.getMap().getPlaces();
        int nb_ = places_.size();
        for (int p = 0; p < nb_; p++) {
            Place place_ = data_.getMap().getPlace(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            League l_ = (League) place_;
            Coords access_ = l_.getAccessCoords();
            if (!NumberUtil.eq(pl_, access_.getNumberPlace())) {
                continue;
            }
            if (!NumberUtil.eq(lev_, access_.getLevel().getLevelIndex())) {
                continue;
            }
            if (!Point.eq(pt_, access_.getLevel().getPoint())) {
                continue;
            }
            getForms().put(CST_LEVEL_MAP_INDEX, IndexConstants.FIRST_INDEX);
            getForms().put(CST_PLACE_MAP_INDEX, p);
            return CST_LEVEL;
        }
        if (p_ instanceof InitializedPlace) {
            Coords coords_ = new Coords();
            coords_.setNumberPlace((short) pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex((byte) lev_);
            coords_.getLevel().setPoint(pt_);
            InitializedPlace i_ = (InitializedPlace) p_;
            if (i_.getLinksWithCaves().contains(pt_)) {
                Coords c_ = i_.getLinksWithCaves().getVal(pt_).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                return CST_LEVEL;
            }
        }
        if (p_ instanceof Cave) {
            Cave c_ = (Cave) p_;
            LevelPoint lp_ = new LevelPoint();
            lp_.setLevelIndex((byte) lev_);
            lp_.setPoint(pt_);
            if (c_.getLinksWithOtherPlaces().contains(lp_)) {
                Coords coords_ = c_.getLinksWithOtherPlaces().getVal(lp_).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
                return CST_LEVEL;
            }
            LevelCave level_ = (LevelCave) c_.getLevelsMap().getVal((byte) lev_);
            if (level_.getLinksOtherLevels().contains(pt_)) {
                Coords coords_ = level_.getLinksOtherLevels().getVal(pt_).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
                return CST_LEVEL;
            }
        }
        if (p_ instanceof League) {
            League l_ = (League) p_;
            if (Point.eq(l_.getRooms().get(lev_).getAccessPoint(), pt_)) {
                if (lev_ < l_.getRooms().size() - 1) {
                    getForms().put(CST_LEVEL_MAP_INDEX, lev_ + 1);
                    return CST_LEVEL;
                }
                Coords coords_ = data_.getMap().getBegin();
                getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
                return CST_LEVEL;
            }
            if (Point.eq(l_.getRooms().get(lev_).getTrainerCoords(), pt_)) {
                getForms().put(CST_TRAINER, l_.getRooms().get(lev_).getTrainer());
                return CST_TRAINER_ONE_FIGHT;
            }
        }
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (Point.eq(b_.getExitCity(), pt_)) {
                    getForms().removeKey(CST_INSIDE);
                    return CST_LEVEL;
                }
                if (b_ instanceof Gym) {
                    Gym g_ = (Gym) b_;
                    if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                        getForms().put(CST_TRAINER, g_.getIndoor().getGymTrainers().getVal(pt_));
                        return CST_TRAINER_ONE_FIGHT;
                    }
                    if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                        getForms().put(CST_TRAINER, g_.getIndoor().getGymLeader());
                        return CST_TRAINER_ONE_FIGHT;
                    }
                }
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    if (pk_.getIndoor().getGerants().contains(pt_)) {
                        Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
                        if (pers_ instanceof Seller) {
                            Seller seller_ = (Seller) pers_;
                            if (!seller_.getItems().isEmpty()) {
                                getForms().put(CST_SELLER, seller_);
                                return CST_SELLER;
                            }
                            if (!seller_.getTm().isEmpty()) {
                                getForms().put(CST_SELLER, seller_);
                                return CST_SELLER;
                            }
                        }
                    }
                }
                return DataBase.EMPTY_STRING;
            }
            if (c_.getBuildings().contains(pt_)) {
                getForms().put(CST_INSIDE, pt_);
                return CST_LEVEL;
            }
        }
        if (p_ instanceof Campaign) {
            Campaign c_ = (Campaign) p_;
            LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal((byte) lev_);
            if (l_.getDualFights().contains(pt_)) {
                getForms().put(CST_TRAINER, l_.getDualFights().getVal(pt_).getFoeTrainer());
                getForms().put(CST_ALLY, l_.getDualFights().getVal(pt_).getAlly());
                return CST_DUAL;
            }
            if (l_.getItems().contains(pt_)) {
                String item_ = l_.getItems().getVal(pt_);
                getForms().put(CST_ITEM, item_);
                Item it_ = data_.getItem(item_);
                if (it_ instanceof Ball) {
                    return CST_BALL;
                }
                if (it_ instanceof Berry) {
                    return CST_BERRY;
                }
                if (it_ instanceof Boost) {
                    return CST_BOOST;
                }
                if (it_ instanceof EvolvingItem) {
                    return CST_EVOLVINGITEM;
                }
                if (it_ instanceof EvolvingStone) {
                    return CST_EVOLVINGSTONE;
                }
                if (it_ instanceof Fossil) {
                    return CST_FOSSIL;
                }
                if (it_ instanceof HealingHpStatus) {
                    return CST_HEALINGHPSTATUS;
                }
                if (it_ instanceof HealingStatus) {
                    return CST_HEALINGSTATUS;
                }
                if (it_ instanceof HealingHp) {
                    return CST_HEALINGHP;
                }
                if (it_ instanceof HealingPp) {
                    return CST_HEALINGPP;
                }
                if (it_ instanceof HealingItem) {
                    return CST_HEALINGITEM;
                }
                if (it_ instanceof ItemForBattle) {
                    return CST_ITEMFORBATTLE;
                }
                if (it_ instanceof Repel) {
                    return CST_REPEL;
                }
                if (it_ instanceof SellingItem) {
                    return CST_SELLINGITEM;
                }
                return CST_ITEM;
            }
            if (l_.getTm().contains(pt_)) {
                Short tm_ = l_.getTm().getVal(pt_);
                getForms().put(CST_MOVE, data_.getTm().getVal(tm_));
                return CST_MOVE;
            }
            if (l_.getHm().contains(pt_)) {
                Short tm_ = l_.getHm().getVal(pt_);
                getForms().put(CST_MOVE, data_.getHm().getVal(tm_));
                return CST_MOVE;
            }
            if (l_.getCharacters().contains(pt_)) {
                CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
                if (char_ instanceof TrainerMultiFights) {
                    getForms().put(CST_TRAINER, (TrainerMultiFights)char_);
                    return CST_TRAINER_MULTI_FIGHT;
                }
                if (char_ instanceof DealerItem) {
                    getForms().put(CST_DEALER, (DealerItem)char_);
                    return CST_DEALER;
                }
            }
            for (Point ptKey_: l_.getDualFights().getKeys()) {
                DualFight d_ = l_.getDualFights().getVal(ptKey_);
                if (Point.eq(d_.getPt(), pt_)) {
                    getForms().put(CST_TRAINER, l_.getDualFights().getVal(ptKey_).getFoeTrainer());
                    getForms().put(CST_ALLY, l_.getDualFights().getVal(ptKey_).getAlly());
                    return CST_DUAL;
                }
            }
            if (l_.containsPokemon(pt_)) {
                getForms().put(CST_LEG_PK, l_.getPokemon(pt_));
                return CST_LEG_PK;
            }
        }
        return DataBase.EMPTY_STRING;
    }
    public String clickDirectedLink(int _index) {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        Direction dir_ = Direction.getDirectionByName(dirs.getKey(_index));
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace((short) pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) lev_);
        coords_.getLevel().setPoint(pt_);
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), false);
        }
        InitializedPlace i_ = (InitializedPlace) p_;
        //p_.getLevelByCoords(coords_).get
        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (p.getDir() == dir_&&Point.eq(p.getSource(), pt_)) {
                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                break;
            }
        }
        return CST_LEVEL;
    }

    public boolean isUp(int _index) {
        return Direction.getDirectionByName(dirs.getKey(_index)) == Direction.UP;
    }

    public boolean isDown(int _index) {
        return Direction.getDirectionByName(dirs.getKey(_index)) == Direction.DOWN;
    }

    public boolean isLeft(int _index) {
        return Direction.getDirectionByName(dirs.getKey(_index)) == Direction.LEFT;
    }

    public boolean isRight(int _index) {
        return Direction.getDirectionByName(dirs.getKey(_index)) == Direction.RIGHT;
    }
    public String clickLink() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace((short) pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) lev_);
        coords_.getLevel().setPoint(pt_);
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), false);
        }
        InitializedPlace i_ = (InitializedPlace) p_;
        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (Point.eq(p.getSource(), pt_)) {
                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                break;
            }
        }
        return CST_LEVEL;
    }
    public String seeArea() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), false);
        }
        Coords current_ = new Coords();
        current_.setNumberPlace((short) pl_);
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex((byte) lev_);
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        if (!app_.isVirtual()) {
            getForms().put(CST_AREA, app_);
        }
        return CST_AREA;
    }
    public String clickTileOnMap(int _index) {
        Point pt_ = tiles.getKey(_index);
        getForms().put(CST_CURRENT_TILE, pt_);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Coords current_ = new Coords();
        current_.setNumberPlace((short) pl_);
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex((byte) lev_);
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        BoolVal seeArea_ = BoolVal.FALSE;
        if (!app_.isVirtual()) {
            getForms().put(CST_AREA, app_);
            seeArea_ = BoolVal.TRUE;
        }
        Place p_ = data_.getMap().getPlace(pl_);
        StringMap<BoolVal> booleans_ = new StringMap<BoolVal>();
        booleans_.put(CST_SEE_AREA,seeArea_);
        Coords coords_ = new Coords();
        coords_.setNumberPlace((short) pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) lev_);
        coords_.getLevel().setPoint(pt_);
        if (p_ instanceof InitializedPlace) {
            InitializedPlace i_ = (InitializedPlace) p_;
            IdList<Direction> points_ = new IdList<Direction>();
            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
                if (Point.eq(p.getSource(), pt_)) {
                    points_.add(p.getDir());
                }
            }
            booleans_.put(CST_PROPONE_LINK, ComparatorBoolean.of(!points_.isEmpty()));
            if (points_.size() > DataBase.ONE_POSSIBLE_CHOICE) {
                for (Direction d: Direction.values()) {
                    booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()),ComparatorBoolean.of(points_.containsObj(d)));
                }
            } else {
                for (Direction d: Direction.values()) {
                    booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), BoolVal.FALSE);
                }
            }
        } else {
            booleans_.put(CST_PROPONE_LINK,BoolVal.FALSE);
            for (Direction d: Direction.values()) {
                booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), BoolVal.FALSE);
            }
        }
        Coords coordsLoc_ = new Coords();
        coordsLoc_.setNumberPlace((short) pl_);
        coordsLoc_.setLevel(new LevelPoint());
        coordsLoc_.getLevel().setLevelIndex((byte) lev_);
        coordsLoc_.getLevel().setPoint(pt_);
        booleans_.put(CST_PROPONE_TILE,ComparatorBoolean.of(!data_.getMap().isEmptyForAdding(coordsLoc_)));
        int nbTrue_ = IndexConstants.SIZE_EMPTY;
        for (BoolVal b: booleans_.values()) {
            if (b == BoolVal.TRUE) {
                nbTrue_++;
            }
        }
        if (nbTrue_ > DataBase.ONE_POSSIBLE_CHOICE) {
            for (EntryCust<String, BoolVal> e: booleans_.entryList()) {
                getForms().put(e.getKey(),e.getValue() == BoolVal.TRUE);
            }
            return CST_LEVEL;
        }
        String return_ = clickTile();
        if (!return_.isEmpty()) {
            return return_;
        }
        if (p_ instanceof InitializedPlace && !getForms().contains(CST_INSIDE)) {
            coords_ = new Coords();
            coords_.setNumberPlace((short) pl_);
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex((byte) lev_);
            coords_.getLevel().setPoint(pt_);
            InitializedPlace i_ = (InitializedPlace) p_;
            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
                if (Point.eq(p.getSource(), pt_)) {
                    Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                    getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                    getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                    return CST_LEVEL;
                }
            }
        }
        if (p_ instanceof Campaign) {
            if (!app_.isVirtual()) {
                getForms().put(CST_AREA, app_);
                return CST_AREA;
            }
        }
        return DataBase.EMPTY_STRING;
    }
    public boolean withoutTitle(int _index) {
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
        return !isMoveTutors(_index);
    }
    public boolean isAccessibleByBeatingSomeTrainers(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        Coords coords_ = new Coords();
        coords_.setNumberPlace((short) pl_);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex((byte) lev_);
        coords_.getLevel().setPoint(pt_);
        DataBase data_ = getDataBase();
        return data_.getMap().getAccessCondition().contains(coords_);
    }
    public boolean isStorage(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    PokemonCenter pk_ = (PokemonCenter) b_;
                    return Point.eq(pk_.getIndoor().getStorageCoords(), pt_);
                }
            }
        }
        return false;
    }
    public boolean isHealer(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
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
                    return g_.getGerance() == GeranceType.HEAL;
                }
            }
        }
        return false;
    }
    public boolean isHost(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
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
                    return g_.getGerance() == GeranceType.HOST;
                }
            }
        }
        return false;
    }
    public boolean isFossile(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
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
                    return g_.getGerance() == GeranceType.FOSSILE;
                }
            }
        }
        return false;
    }
    public boolean isMoveTutors(int _index) {
        Point pt_ = tiles.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
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
                    return g_.getSell() == SellType.MOVE;
                }
            }
        }
        return false;
    }

    /*@Accessible
    private String getTrDir(int _index) {
        Direction dir_ = dirs.getKey(_index);

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

    public TreeMap<String,BoolVal> getDirs() {
        return dirs;
    }
}