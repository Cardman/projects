package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import aiki.db.DataBase;
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
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class MapLevelBean extends AbsLevelBean {
    private boolean proponeLink;
    private boolean proponeTile;
    private boolean seeArea;
    private IdMap<Direction, BoolVal> dirs;

    @Override
    public void beforeDisplaying() {
        initTiles();
        proponeLink = getForms().getValBool(CST_PROPONE_LINK);
        proponeTile = getForms().getValBool(CST_PROPONE_TILE);
        seeArea = getForms().getValBool(CST_SEE_AREA);
        dirs = new IdMap<Direction, BoolVal>();
        for (EntryCust<String, BoolVal> s: getForms().getMapDirection().entryList()) {
            if (s.getValue() != BoolVal.TRUE) {
                continue;
            }
            String dirStr_ = s.getKey().substring(CST_PROPONE_LINK_VAR.length());
            dirs.put(Direction.getDirectionByName(dirStr_), BoolVal.TRUE);
        }
    }
    public String clickTile() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        reinitForms();
        //getForms().put(FROM_LIST, false);
        CustList<Place> places_ = data_.getMap().getPlaces();
        int nb_ = places_.size();
        for (int p = 0; p < nb_; p++) {
            Place place_ = data_.getMap().getPlace(p);
            if (place_ instanceof League && NumberUtil.eq(pl_, (((League) place_).getAccessCoords()).getNumberPlace()) && NumberUtil.eq(lev_, (((League) place_).getAccessCoords()).getLevel().getLevelIndex()) && Point.eq(pt_, (((League) place_).getAccessCoords()).getLevel().getPoint())) {
                getForms().put(CST_LEVEL_MAP_INDEX, IndexConstants.FIRST_INDEX);
                getForms().put(CST_PLACE_MAP_INDEX, p);
                return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        if (p_ instanceof InitializedPlace) {
            InitializedPlace i_ = (InitializedPlace) p_;
            if (i_.getLinksWithCaves().contains(pt_)) {
                Coords c_ = i_.getLinksWithCaves().getVal(pt_).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        return place(pt_, lev_, p_);
    }

    private String place(Point _pt, int _lev, Place _p) {
        if (_p instanceof Cave) {
            Cave c_ = (Cave) _p;
            LevelPoint lp_ = new LevelPoint();
            lp_.setLevelIndex((byte) _lev);
            lp_.setPoint(_pt);
            if (c_.getLinksWithOtherPlaces().contains(lp_)) {
                Coords coords_ = c_.getLinksWithOtherPlaces().getVal(lp_).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
                return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
            }
            LevelCave level_ = (LevelCave) c_.getLevelsMap().getVal((byte) _lev);
            if (level_.getLinksOtherLevels().contains(_pt)) {
                Coords coords_ = level_.getLinksOtherLevels().getVal(_pt).getCoords();
                getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
                return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        if (_p instanceof League) {
            League l_ = (League) _p;
            if (Point.eq(l_.getRooms().get(_lev).getAccessPoint(), _pt)) {
                return league(_lev, l_);
            }
            if (Point.eq(l_.getRooms().get(_lev).getTrainerCoords(), _pt)) {
                getForms().put(CST_TRAINER, l_.getRooms().get(_lev).getTrainer());
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
        }
        if (_p instanceof City) {
            City c_ = (City) _p;
            if (getForms().contains(CST_INSIDE)) {
                return inside(_pt, c_);
            }
            if (c_.getBuildings().contains(_pt)) {
                getForms().put(CST_INSIDE, _pt);
                return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        return campaign(_pt, (byte) _lev, _p);
    }

    private String league(int _lev, League _l) {
        if (_lev < _l.getRooms().size() - 1) {
            getForms().put(CST_LEVEL_MAP_INDEX, _lev + 1);
        } else {
            DataBase data_ = getDataBase();
            Coords coords_ = data_.getMap().getBegin();
            getForms().put(CST_LEVEL_MAP_INDEX, coords_.getLevel().getLevelIndex());
            getForms().put(CST_PLACE_MAP_INDEX, coords_.getNumberPlace());
        }
        return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
    }

    private String inside(Point _pt, City _c) {
        Point ptInside_ = getForms().getValPt(CST_INSIDE);
        Building b_ = _c.getBuildings().getVal(ptInside_);
        if (Point.eq(b_.getExitCity(), _pt)) {
            getForms().removeKey(CST_INSIDE);
            return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
        }
        if (b_ instanceof Gym) {
            Gym g_ = (Gym) b_;
            if (g_.getIndoor().getGymTrainers().contains(_pt)) {
                getForms().put(CST_TRAINER, g_.getIndoor().getGymTrainers().getVal(_pt));
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), _pt)) {
                getForms().put(CST_TRAINER, g_.getIndoor().getGymLeader());
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
        }
        if (b_ instanceof PokemonCenter) {
            PokemonCenter pk_ = (PokemonCenter) b_;
            if (!pk_.getIndoor().getGerants().contains(_pt)) {
                return DataBase.EMPTY_STRING;
            }
            Person pers_ = pk_.getIndoor().getGerants().getVal(_pt);
            if (!(pers_ instanceof Seller)) {
                return DataBase.EMPTY_STRING;
            }
            Seller seller_ = (Seller) pers_;
            if (!seller_.getItems().isEmpty()) {
                getForms().put(CST_SELLER, seller_);
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_SELLER_HTML;
            }
            if (!seller_.getTm().isEmpty()) {
                getForms().put(CST_SELLER, seller_);
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_SELLER_HTML;
            }
        }
        return DataBase.EMPTY_STRING;
    }

    private String campaign(Point _pt, byte _lev, Place _p) {
        if (!(_p instanceof Campaign)) {
            return DataBase.EMPTY_STRING;
        }
        Campaign c_ = (Campaign) _p;
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal(_lev);
        if (l_.getDualFights().contains(_pt)) {
            getForms().put(CST_TRAINER, l_.getDualFights().getVal(_pt).getFoeTrainer());
            getForms().put(CST_ALLY, l_.getDualFights().getVal(_pt).getAlly());
            return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML;
        }
        DataBase data_ = getDataBase();
        if (l_.getItems().contains(_pt)) {
            String item_ = l_.getItems().getVal(_pt);
            return tryRedirectIt(item_);
//                if (it_ instanceof Ball) {
//                    return CST_BALL;
//                }
//                if (it_ instanceof Berry) {
//                    return CST_BERRY;
//                }
//                if (it_ instanceof Boost) {
//                    return CST_BOOST;
//                }
//                if (it_ instanceof EvolvingItem) {
//                    return CST_EVOLVINGITEM;
//                }
//                if (it_ instanceof EvolvingStone) {
//                    return CST_EVOLVINGSTONE;
//                }
//                if (it_ instanceof Fossil) {
//                    return CST_FOSSIL;
//                }
//                if (it_ instanceof HealingHpStatus) {
//                    return CST_HEALINGHPSTATUS;
//                }
//                if (it_ instanceof HealingStatus) {
//                    return CST_HEALINGSTATUS;
//                }
//                if (it_ instanceof HealingHp) {
//                    return CST_HEALINGHP;
//                }
//                if (it_ instanceof HealingPp) {
//                    return CST_HEALINGPP;
//                }
//                if (it_ instanceof HealingItem) {
//                    return CST_HEALINGITEM;
//                }
//                if (it_ instanceof ItemForBattle) {
//                    return CST_ITEMFORBATTLE;
//                }
//                if (it_ instanceof Repel) {
//                    return CST_REPEL;
//                }
//                if (it_ instanceof SellingItem) {
//                    return CST_SELLINGITEM;
//                }
//                return CST_ITEM;
        }
        if (l_.getTm().contains(_pt)) {
            Short tm_ = l_.getTm().getVal(_pt);
            return tryRedirectMv(data_.getTm().getVal(tm_));
        }
        if (l_.getHm().contains(_pt)) {
            Short tm_ = l_.getHm().getVal(_pt);
            return tryRedirectMv(data_.getHm().getVal(tm_));
        }
        if (l_.getCharacters().contains(_pt)) {
            CharacterInRoadCave char_ = l_.getCharacters().getVal(_pt);
            if (char_ instanceof TrainerMultiFights) {
                getForms().put(CST_TRAINER, (TrainerMultiFights)char_);
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML;
            }
            if (char_ instanceof DealerItem) {
                getForms().put(CST_DEALER, (DealerItem)char_);
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DEALER_HTML;
            }
        }
        for (Point ptKey_: l_.getDualFights().getKeys()) {
            DualFight d_ = l_.getDualFights().getVal(ptKey_);
            if (Point.eq(d_.getPt(), _pt)) {
                getForms().put(CST_TRAINER, l_.getDualFights().getVal(ptKey_).getFoeTrainer());
                getForms().put(CST_ALLY, l_.getDualFights().getVal(ptKey_).getAlly());
                return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML;
            }
        }
        if (l_.containsPokemon(_pt)) {
            getForms().put(CST_LEG_PK, l_.getPokemon(_pt));
            return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML;
        }
        return DataBase.EMPTY_STRING;
    }

    public String clickDirectedLink(int _index) {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        Direction dir_ = dirs.getKey(_index);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        reinitForms();
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
        return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
    }

    private void reinitForms() {
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.all()) {
            getForms().putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
        }
    }

    public boolean isUp(int _index) {
        return dirs.getKey(_index) == Direction.UP;
    }

    public boolean isDown(int _index) {
        return dirs.getKey(_index) == Direction.DOWN;
    }

    public boolean isLeft(int _index) {
        return dirs.getKey(_index) == Direction.LEFT;
    }

    public boolean isRight(int _index) {
        return dirs.getKey(_index) == Direction.RIGHT;
    }
    public String clickLink() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        reinitForms();
        InitializedPlace i_ = (InitializedPlace) p_;
        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (Point.eq(p.getSource(), pt_)) {
                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                break;
            }
        }
        return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
    }
    public String seeArea() {
        Point pt_ = getForms().getValPt(CST_CURRENT_TILE);
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        int lev_ = getForms().getValInt(CST_LEVEL_MAP_INDEX);
        DataBase data_ = getDataBase();
        reinitForms();
        Coords current_ = new Coords();
        current_.setNumberPlace((short) pl_);
        current_.setLevel(new LevelPoint());
        current_.getLevel().setLevelIndex((byte) lev_);
        current_.getLevel().setPoint(pt_);
        AreaApparition app_ = data_.getMap().getAreaByCoords(current_);
        if (!app_.isVirtual()) {
            getForms().put(CST_AREA, app_);
        }
        return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_AREA_HTML;
    }
    public String clickTileOnMap(int _index) {
        Point pt_ = getTiles().getKey(_index);
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
        StringMap<BoolVal> booleansDir_ = new StringMap<BoolVal>();
        StringMap<BoolVal> booleansOthers_ = new StringMap<BoolVal>();
        StringMap<BoolVal> booleans_ = new StringMap<BoolVal>();
        booleans_.put(CST_SEE_AREA,seeArea_);
        booleansOthers_.put(CST_SEE_AREA,seeArea_);
        if (p_ instanceof InitializedPlace) {
            InitializedPlace i_ = (InitializedPlace) p_;
            IdList<Direction> points_ = points(pt_, i_);
            booleans_.put(CST_PROPONE_LINK, ComparatorBoolean.of(!points_.isEmpty()));
            booleansOthers_.put(CST_PROPONE_LINK, ComparatorBoolean.of(!points_.isEmpty()));
            if (points_.size() > DataBase.ONE_POSSIBLE_CHOICE) {
                for (Direction d: Direction.all()) {
                    booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()),ComparatorBoolean.of(points_.containsObj(d)));
                    booleansDir_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()),ComparatorBoolean.of(points_.containsObj(d)));
                }
            } else {
                for (Direction d: Direction.all()) {
                    booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
                    booleansDir_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
                }
            }
        } else {
            booleans_.put(CST_PROPONE_LINK,BoolVal.FALSE);
            booleansOthers_.put(CST_PROPONE_LINK,BoolVal.FALSE);
            for (Direction d: Direction.all()) {
                booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
                booleansDir_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
            }
        }
        Coords coordsLoc_ = new Coords();
        coordsLoc_.setNumberPlace((short) pl_);
        coordsLoc_.setLevel(new LevelPoint());
        coordsLoc_.getLevel().setLevelIndex((byte) lev_);
        coordsLoc_.getLevel().setPoint(pt_);
        booleans_.put(CST_PROPONE_TILE,ComparatorBoolean.of(!data_.getMap().isEmptyForAdding(coordsLoc_)));
        booleansOthers_.put(CST_PROPONE_TILE,ComparatorBoolean.of(!data_.getMap().isEmptyForAdding(coordsLoc_)));
        int nbTrue_ = DataBase.countValues(booleans_.values(), BoolVal.TRUE);
        if (nbTrue_ > DataBase.ONE_POSSIBLE_CHOICE) {
            propone(booleansOthers_);
            putDirs(booleansDir_, BoolVal.FALSE);
            putDirs(booleansDir_, BoolVal.TRUE);
            return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
        }
        return atMostOneDir(pt_, app_, p_);
    }

    private void propone(StringMap<BoolVal> _bools) {
        for (EntryCust<String, BoolVal> e: _bools.entryList()) {
            getForms().put(e.getKey(),e.getValue());
        }
    }

    private String atMostOneDir(Point _pt, AreaApparition _app, Place _p) {
        String return_ = clickTile();
        if (!return_.isEmpty()) {
            return return_;
        }
        return whenNoTile(_pt, _app, _p);
    }

    private String whenNoTile(Point _pt, AreaApparition _app, Place _p) {
        if (_p instanceof InitializedPlace && !getForms().contains(CST_INSIDE)) {
            InitializedPlace i_ = (InitializedPlace) _p;
            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
                if (Point.eq(p.getSource(), _pt)) {
                    Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
                    getForms().put(CST_LEVEL_MAP_INDEX, c_.getLevel().getLevelIndex());
                    getForms().put(CST_PLACE_MAP_INDEX, c_.getNumberPlace());
                    return AikiBeansMapStd.WEB_HTML_MAP_LEVEL_HTML;
                }
            }
        }
        if (_p instanceof Campaign && !_app.isVirtual()) {
            getForms().put(CST_AREA, _app);
            return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_AREA_HTML;
        }
        return DataBase.EMPTY_STRING;
    }

    private IdList<Direction> points(Point _pt, InitializedPlace _i) {
        IdList<Direction> points_ = new IdList<Direction>();
        for (PlaceInterConnect p: _i.getPointsWithCitiesAndOtherRoads().getKeys()) {
            if (Point.eq(p.getSource(), _pt)) {
                points_.add(p.getDir());
            }
        }
        return points_;
    }

    private void putDirs(StringMap<BoolVal> _dirs, BoolVal _fl) {
        for (String e: DataBase.keysWithValue(_dirs, _fl)) {
            getForms().putDir(e, _fl);
        }
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
        Point pt_ = getTiles().getKey(_index);
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
        PokemonCenter pk_ = pkCenter();
        if (pk_ == null) {
            return false;
        }
        Point pt_ = getTiles().getKey(_index);
        return Point.eq(pk_.getIndoor().getStorageCoords(), pt_);
//        Point pt_ = getTiles().getKey(_index);
//        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        if (p_ instanceof City) {
//            City c_ = (City) p_;
//            if (getForms().contains(CST_INSIDE)) {
//                Point ptInside_ = getForms().getValPt(CST_INSIDE);
//                Building b_ = c_.getBuildings().getVal(ptInside_);
//                if (b_ instanceof PokemonCenter) {
//                    PokemonCenter pk_ = (PokemonCenter) b_;
//                    return Point.eq(pk_.getIndoor().getStorageCoords(), pt_);
//                }
//            }
//        }
//        return false;
    }
    public boolean isHealer(int _index) {
        return gerant(_index) == GeranceType.HEAL;
//        Point pt_ = getTiles().getKey(_index);
//        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        if (p_ instanceof City) {
//            City c_ = (City) p_;
//            if (getForms().contains(CST_INSIDE)) {
//                Point ptInside_ = getForms().getValPt(CST_INSIDE);
//                Building b_ = c_.getBuildings().getVal(ptInside_);
//                if (b_ instanceof PokemonCenter) {
//                    PokemonCenter pk_ = (PokemonCenter) b_;
//                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
//                        return false;
//                    }
//                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
//                    if (!(pers_ instanceof GerantPokemon)) {
//                        return false;
//                    }
//                    GerantPokemon g_ = (GerantPokemon) pers_;
//                    return g_.getGerance() == GeranceType.HEAL;
//                }
//            }
//        }
//        return false;
    }
    public boolean isHost(int _index) {
        return gerant(_index) == GeranceType.HOST;
//        Point pt_ = getTiles().getKey(_index);
//        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        if (p_ instanceof City) {
//            City c_ = (City) p_;
//            if (getForms().contains(CST_INSIDE)) {
//                Point ptInside_ = getForms().getValPt(CST_INSIDE);
//                Building b_ = c_.getBuildings().getVal(ptInside_);
//                if (b_ instanceof PokemonCenter) {
//                    PokemonCenter pk_ = (PokemonCenter) b_;
//                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
//                        return false;
//                    }
//                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
//                    if (!(pers_ instanceof GerantPokemon)) {
//                        return false;
//                    }
//                    GerantPokemon g_ = (GerantPokemon) pers_;
//                    return g_.getGerance() == GeranceType.HOST;
//                }
//            }
//        }
//        return false;
    }
    public boolean isFossile(int _index) {
        return gerant(_index) == GeranceType.FOSSILE;
//        Point pt_ = getTiles().getKey(_index);
//        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        if (p_ instanceof City) {
//            City c_ = (City) p_;
//            if (getForms().contains(CST_INSIDE)) {
//                Point ptInside_ = getForms().getValPt(CST_INSIDE);
//                Building b_ = c_.getBuildings().getVal(ptInside_);
//                if (b_ instanceof PokemonCenter) {
//                    PokemonCenter pk_ = (PokemonCenter) b_;
//                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
//                        return false;
//                    }
//                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
//                    if (!(pers_ instanceof GerantPokemon)) {
//                        return false;
//                    }
//                    GerantPokemon g_ = (GerantPokemon) pers_;
//                    return g_.getGerance() == GeranceType.FOSSILE;
//                }
//            }
//        }
//        return false;
    }
    public boolean isMoveTutors(int _index) {
        Person p_ = person(_index);
        if (!(p_ instanceof Seller)) {
            return false;
        }
        return ((Seller)p_).getSell() == SellType.MOVE;
//        Point pt_ = getTiles().getKey(_index);
//        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        if (p_ instanceof City) {
//            City c_ = (City) p_;
//            if (getForms().contains(CST_INSIDE)) {
//                Point ptInside_ = getForms().getValPt(CST_INSIDE);
//                Building b_ = c_.getBuildings().getVal(ptInside_);
//                if (b_ instanceof PokemonCenter) {
//                    PokemonCenter pk_ = (PokemonCenter) b_;
//                    if (!pk_.getIndoor().getGerants().contains(pt_)) {
//                        return false;
//                    }
//                    Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
//                    if (!(pers_ instanceof Seller)) {
//                        return false;
//                    }
//                    Seller g_ = (Seller) pers_;
//                    return g_.getSell() == SellType.MOVE;
//                }
//            }
//        }
//        return false;
    }
    private GeranceType gerant(int _index) {
        Person p_ = person(_index);
        if (!(p_ instanceof GerantPokemon)) {
            return null;
        }
        return ((GerantPokemon)p_).getGerance();
    }
    private Person person(int _index) {
        PokemonCenter pk_ = pkCenter();
        if (pk_ == null) {
            return null;
        }
        Point pt_ = getTiles().getKey(_index);
        if (!pk_.getIndoor().getGerants().contains(pt_)) {
            return null;
        }
        return pk_.getIndoor().getGerants().getVal(pt_);
    }
    private PokemonCenter pkCenter() {
        int pl_ = getForms().getValInt(CST_PLACE_MAP_INDEX);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (getForms().contains(CST_INSIDE)) {
                Point ptInside_ = getForms().getValPt(CST_INSIDE);
                Building b_ = c_.getBuildings().getVal(ptInside_);
                if (b_ instanceof PokemonCenter) {
                    return (PokemonCenter) b_;
                }
            }
        }
        return null;
    }

    /*@Accessible
    private String getTrDir(int _index) {
        Direction dir_ = dirs.getKey(_index);

    }*/

    public boolean getProponeTile() {
        return proponeTile;
    }

    public boolean getProponeLink() {
        return proponeLink;
    }

    public boolean getSeeArea() {
        return seeArea;
    }

    public IdMap<Direction,BoolVal> getDirs() {
        return dirs;
    }
}