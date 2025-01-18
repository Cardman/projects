package aiki.beans.map;

import aiki.beans.AbsLevelBean;
import aiki.beans.StringMapObject;
import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.*;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.*;
import aiki.map.util.PlaceInterConnectCoords;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;

public class MapLevelBean extends AbsLevelBean {
//    private boolean proponeLink;
//    private boolean proponeTile;
//    private boolean seeArea;
//    private IdList<Direction> dirs;

    @Override
    public void beforeDisplaying() {
        initTiles(true);
//        proponeLink = getForms().getValBool(CST_PROPONE_LINK);
//        proponeTile = getForms().getValBool(CST_PROPONE_TILE);
//        seeArea = getForms().getValBool(CST_SEE_AREA);
//        dirs = new IdList<Direction>();
//        for (EntryCust<String, BoolVal> s: getForms().getMapDirection().entryList()) {
//            if (s.getValue() != BoolVal.TRUE) {
//                continue;
//            }
//            String dirStr_ = s.getKey().substring(CST_PROPONE_LINK_VAR.length());
//            dirs.add(Direction.getDirectionByName(dirStr_));
//        }
    }
    public String clickTile() {
        Coords co_ = getForms().getValCoords(CST_COORDS);
        Point pt_ = co_.getLevel().getPoint();
//        SelectedPlaceLevelIndexes sel_ = getForms().getValPlacesLevels(CST_LEVEL_MAP);
        int pl_ = co_.getNumberPlace();
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(pl_);
//        reinitForms();
        //getForms().put(FROM_LIST, false);
        CustList<Place> places_ = data_.getMap().getPlaces();
        int nb_ = places_.size();
        for (int p = 0; p < nb_; p++) {
            Place place_ = data_.getMap().getPlace(p);
            if (place_ instanceof League && Coords.eq(co_, (((League) place_).getAccessCoords()))) {
                getForms().put(CST_COORDS,p,IndexConstants.FIRST_INDEX);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        if (p_ instanceof InitializedPlace) {
            InitializedPlace i_ = (InitializedPlace) p_;
            if (i_.getLinksWithCaves().contains(pt_)) {
                Coords c_ = i_.getLinksWithCaves().getVal(pt_).getCoords();
                getForms().putPlaceLevel(CST_COORDS,c_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        return place(co_, p_);
    }

    private String place(Coords _co, Place _p) {
        if (_p instanceof Cave) {
            Cave c_ = (Cave) _p;
            LevelPoint lp_ = _co.getLevel();
            if (c_.getLinksWithOtherPlaces().contains(lp_)) {
                Coords coords_ = c_.getLinksWithOtherPlaces().getVal(lp_).getCoords();
                getForms().putPlaceLevel(CST_COORDS,coords_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
            }
            LevelCave level_ = (LevelCave) c_.getLevelsMap().getVal(lp_.getLevelIndex());
            Point pt_ = lp_.getPoint();
            if (level_.getLinksOtherLevels().contains(pt_)) {
                Coords coords_ = level_.getLinksOtherLevels().getVal(pt_).getCoords();
                getForms().putPlaceLevel(CST_COORDS,coords_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        if (_p instanceof League) {
            League l_ = (League) _p;
            LevelPoint lp_ = _co.getLevel();
            int lev_ = lp_.getLevelIndex();
            Point pt_ = lp_.getPoint();
            if (Point.eq(l_.getRooms().get(lev_).getAccessPoint(), pt_)) {
                return league(_co,l_);
            }
            if (Point.eq(l_.getRooms().get(lev_).getTrainerCoords(), pt_)) {
                getForms().put(CST_PERSON, l_.getRooms().get(lev_).getTrainer());
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
        }
        if (_p instanceof City) {
            City c_ = (City) _p;
            LevelPoint lp_ = _co.getLevel();
            Point pt_ = lp_.getPoint();
            if (_co.isInside()) {
                return inside(_co, c_);
            }
            if (c_.getBuildings().contains(pt_)) {
                Coords inside_ = new Coords(_co);
                inside_.affectInside(pt_);
                getForms().put(CST_COORDS, inside_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
            }
        }
        return campaign(_co,_p);
    }

    private String league(Coords _co, League _l) {
        int lev_ = _co.getLevel().getLevelIndex();
        if (lev_ < _l.getRooms().size() - 1) {
            getForms().put(CST_COORDS,_co.getNumberPlace(), lev_ + 1);
        } else {
            DataBase data_ = getDataBase();
            Coords coords_ = data_.getMap().getBegin();
            getForms().putPlaceLevel(CST_COORDS,coords_);
        }
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

    private String inside(Coords _co, City _c) {
        Point ptInside_ = _co.getInsideBuilding();
        LevelPoint lp_ = _co.getLevel();
        Point pt_ = lp_.getPoint();
        Building b_ = _c.getBuildings().getVal(ptInside_);
        if (Point.eq(b_.getExitCity(), pt_)) {
            Coords outside_ = new Coords(_co);
            outside_.outside();
            getForms().put(CST_COORDS, outside_);
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
        }
        if (b_ instanceof Gym) {
            Gym g_ = (Gym) b_;
            if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                getForms().put(CST_PERSON, g_.getIndoor().getGymTrainers().getVal(pt_));
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
            if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)) {
                getForms().put(CST_PERSON, g_.getIndoor().getGymLeader());
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML;
            }
        }
        if (b_ instanceof PokemonCenter) {
            PokemonCenter pk_ = (PokemonCenter) b_;
            if (!pk_.getIndoor().getGerants().contains(pt_)) {
                return DataBase.EMPTY_STRING;
            }
            Person pers_ = pk_.getIndoor().getGerants().getVal(pt_);
            if (!(pers_ instanceof Seller)) {
                return DataBase.EMPTY_STRING;
            }
            Seller seller_ = (Seller) pers_;
            if (!seller_.getItems().isEmpty()) {
                getForms().put(CST_PERSON, seller_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML;
            }
            if (!seller_.getTm().isEmpty()) {
                getForms().put(CST_PERSON, seller_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML;
            }
        }
        return DataBase.EMPTY_STRING;
    }

    private String campaign(Coords _co, Place _p) {
        if (!(_p instanceof Campaign)) {
            return DataBase.EMPTY_STRING;
        }
        LevelPoint lev_ = _co.getLevel();
        Point pt_ = lev_.getPoint();
        Campaign c_ = (Campaign) _p;
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) c_.getLevelsMap().getVal(lev_.getLevelIndex());
        if (l_.getDualFights().contains(pt_)) {
            getForms().put(CST_PERSON, l_.getDualFights().getVal(pt_).getFoeTrainer());
            getForms().put(CST_ALLY, l_.getDualFights().getVal(pt_).getAlly());
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML;
        }
        DataBase data_ = getDataBase();
        if (l_.getItems().contains(pt_)) {
            String item_ = l_.getItems().getVal(pt_);
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
        if (l_.getTm().contains(pt_)) {
            Integer tm_ = l_.getTm().getVal(pt_);
            return tryRedirectMv(data_.getTm().getVal(tm_));
        }
        if (l_.getHm().contains(pt_)) {
            Integer tm_ = l_.getHm().getVal(pt_);
            return tryRedirectMv(data_.getHm().getVal(tm_));
        }
        if (l_.getCharacters().contains(pt_)) {
            CharacterInRoadCave char_ = l_.getCharacters().getVal(pt_);
            if (char_ instanceof TrainerMultiFights) {
                getForms().put(CST_PERSON, (TrainerMultiFights)char_);
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML;
            }
            getForms().put(CST_PERSON, (DealerItem)char_);
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML;
        }
        for (Point ptKey_: l_.getDualFights().getKeys()) {
            DualFight d_ = l_.getDualFights().getVal(ptKey_);
            if (Point.eq(d_.getPt(), pt_)) {
                getForms().put(CST_PERSON, l_.getDualFights().getVal(ptKey_).getFoeTrainer());
                getForms().put(CST_ALLY, l_.getDualFights().getVal(ptKey_).getAlly());
                return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML;
            }
        }
        if (l_.containsPokemon(pt_)) {
            getForms().put(CST_LEG_PK, l_.getPokemon(pt_));
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML;
        }
        return DataBase.EMPTY_STRING;
    }

//    public String clickDirectedLink(int _index) {
//        Coords co_ = getForms().getValCoords(CST_COORDS);
//        Point pt_ = co_.getLevel().getPoint();
//        Direction dir_ = dirs.get(_index);
////        SelectedPlaceLevelIndexes sel_ = getForms().getValPlacesLevels(CST_LEVEL_MAP);
//        int pl_ = co_.getNumberPlace();
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        reinitForms();
//        InitializedPlace i_ = (InitializedPlace) p_;
//        //p_.getLevelByCoords(coords_).get
//        feedForm(pt_, dir_, i_, getForms());
//        return AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML;
//    }

//    static void feedForm(Point _pt, Direction _dir, InitializedPlace _i, StringMapObject _map) {
//        for (PlaceInterConnect p: _i.getPointsWithCitiesAndOtherRoads().getKeys()) {
//            if (p.getDir() == _dir &&Point.eq(p.getSource(), _pt)) {
//                Coords c_ = _i.getPointsWithCitiesAndOtherRoads().getVal(p);
//                _map.put(CST_COORDS,c_);
//                break;
//            }
//        }
//    }

//    private void reinitForms() {
//        getForms().put(CST_PROPONE_LINK, false);
//        getForms().put(CST_PROPONE_TILE, false);
//        getForms().put(CST_SEE_AREA, false);
//        for (Direction d: Direction.all()) {
//            getForms().putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//        }
//    }

//    public boolean isUp(int _index) {
//        return dirs.get(_index) == Direction.UP;
//    }
//
//    public boolean isDown(int _index) {
//        return dirs.get(_index) == Direction.DOWN;
//    }
//
//    public boolean isLeft(int _index) {
//        return dirs.get(_index) == Direction.LEFT;
//    }
//
//    public boolean isRight(int _index) {
//        return dirs.get(_index) == Direction.RIGHT;
//    }
//    public String clickLink() {
//        Coords co_ = getForms().getValCoords(CST_COORDS);
//        Point pt_ = co_.getLevel().getPoint();
//        int pl_ = co_.getNumberPlace();
//        DataBase data_ = getDataBase();
//        Place p_ = data_.getMap().getPlace(pl_);
//        reinitForms();
//        InitializedPlace i_ = (InitializedPlace) p_;
//        for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
//            if (Point.eq(p.getSource(), pt_)) {
//                Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
//                getForms().putPlaceLevel(CST_COORDS,c_);
//                break;
//            }
//        }
//        return AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML;
//    }
//    public String seeArea() {
//        Coords co_ = getForms().getValCoords(CST_COORDS);
//        DataBase data_ = getDataBase();
//        reinitForms();
//        AreaApparition app_ = data_.getMap().getAreaByCoords(co_);
//        if (!app_.isVirtual()) {
//            getForms().put(CST_AREA, app_);
//        }
//        return AikiBeansMapElementsStd.WEB_HTML_MAP_ELEMENTS_AREA_HTML;
//    }
    public String clickTileOnMap(int _index) {
        Coords co_ = getForms().getValCoords(CST_COORDS);
        Point pt_ = getTiles().getKey(_index);
        Coords cp_ = new Coords(co_);
        cp_.getLevel().setPoint(pt_);
        getForms().put(CST_COORDS, cp_);
        int pl_ = co_.getNumberPlace();
        DataBase data_ = getDataBase();
        AbsAreaApparition app_ = data_.getMap().getAreaByCoords(cp_);
//        BoolVal seeArea_ = BoolVal.FALSE;
//        if (!app_.isVirtual()) {
//            getForms().put(CST_AREA, app_);
//            seeArea_ = BoolVal.TRUE;
//        }
        Place p_ = data_.getMap().getPlace(pl_);
//        StringMap<BoolVal> booleansDir_ = new StringMap<BoolVal>();
//        StringMap<BoolVal> booleansOthers_ = new StringMap<BoolVal>();
//        StringMap<BoolVal> booleans_ = new StringMap<BoolVal>();
//        booleans_.put(CST_SEE_AREA,seeArea_);
//        booleansOthers_.put(CST_SEE_AREA,seeArea_);
//        if (p_ instanceof InitializedPlace) {
//            InitializedPlace i_ = (InitializedPlace) p_;
//            IdList<Direction> points_ = points(pt_, i_);
//            booleans_.put(CST_PROPONE_LINK, ComparatorBoolean.of(!points_.isEmpty()));
//            booleansOthers_.put(CST_PROPONE_LINK, ComparatorBoolean.of(!points_.isEmpty()));
//            for (Direction d: Direction.all()) {
//                booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()),ComparatorBoolean.of(points_.containsObj(d)));
//                booleansDir_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()),ComparatorBoolean.of(points_.containsObj(d)));
//            }
//        } else {
//            booleans_.put(CST_PROPONE_LINK,BoolVal.FALSE);
//            booleansOthers_.put(CST_PROPONE_LINK,BoolVal.FALSE);
//            for (Direction d: Direction.all()) {
//                booleans_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//                booleansDir_.put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//            }
//        }
//        booleans_.put(CST_PROPONE_TILE,ComparatorBoolean.of(!data_.getMap().isEmptyForAdding(cp_)));
//        booleansOthers_.put(CST_PROPONE_TILE,ComparatorBoolean.of(!data_.getMap().isEmptyForAdding(cp_)));
//        int nbTrue_ = DataBase.countValues(booleans_.values(), BoolVal.TRUE);
//        if (nbTrue_ > DataBase.ONE_POSSIBLE_CHOICE) {
//            propone(booleansOthers_);
//            putDirs(booleansDir_, BoolVal.FALSE);
//            putDirs(booleansDir_, BoolVal.TRUE);
//            return AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML;
//        }
        return atMostOneDir(pt_, app_, p_);
    }

//    private void propone(StringMap<BoolVal> _bools) {
//        for (EntryCust<String, BoolVal> e: _bools.entryList()) {
//            getForms().put(e.getKey(),e.getValue());
//        }
//    }

//    public String clickForeGround(int _index) {
//        Coords co_ = getForms().getValCoords(CST_COORDS);
//        Point pt_ = getTiles().getKey(_index);
//        Coords cp_ = new Coords(co_);
//        cp_.getLevel().setPoint(pt_);
//        getForms().put(CST_COORDS, cp_);
//        return clickTile();
//    }
    private String atMostOneDir(Point _pt, AbsAreaApparition _app, Place _p) {
        String return_ = clickTile();
        if (!return_.isEmpty()) {
            return return_;
        }
        return whenNoTile(_pt, _app, _p);
    }

    private String whenNoTile(Point _pt, AbsAreaApparition _app, Place _p) {
        if (_p instanceof Campaign && !_app.isVirtual()) {
            getForms().put(CST_AREA, _app);
            return PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML;
        }
        whenNoTile(_pt,_p, getForms());
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }
    static void whenNoTile(Point _pt, Place _p, StringMapObject _map) {
        Coords co_ = _map.getValCoords(CST_COORDS);
        if (_p instanceof InitializedPlace && !co_.isInside()) {
            InitializedPlace i_ = (InitializedPlace) _p;
            for (Direction d: Direction.all()) {
                Point moved_ = new Point(_pt);
                moved_.moveTo(d);
                for (PlaceInterConnectCoords p: i_.getPointsWithCitiesAndOtherRoads().entryList()) {
                    if (Point.eq(moved_,p.getPlaceInterConnect().getSource()) && d == p.getPlaceInterConnect().getDir().getOpposite()) {
                        Coords c_ = p.getCoords();
                        _map.put(CST_COORDS,c_);
                        return;
                    }
                }
            }
//            for (PlaceInterConnect p: i_.getPointsWithCitiesAndOtherRoads().getKeys()) {
//                if (Point.eq(p.getSource(), _pt)) {
//                    Coords c_ = i_.getPointsWithCitiesAndOtherRoads().getVal(p);
//                    _map.put(CST_COORDS,c_);
//                    return AikiBeansMapElementsStd.WEB_HTML_MAP_LEVEL_HTML;
//                }
//            }
        }
    }

//    private IdList<Direction> points(Point _pt, InitializedPlace _i) {
//        IdList<Direction> points_ = new IdList<Direction>();
//        for (PlaceInterConnect p: _i.getPointsWithCitiesAndOtherRoads().getKeys()) {
//            if (Point.eq(p.getSource(), _pt)) {
//                points_.add(p.getDir());
//            }
//        }
//        return points_;
//    }
//
//    private void putDirs(StringMap<BoolVal> _dirs, BoolVal _fl) {
//        for (String e: DataBase.keysWithValue(_dirs, _fl)) {
//            getForms().putDir(e, _fl);
//        }
//    }

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
        Coords co_ = getForms().getValCoords(CST_COORDS);
        Coords cp_ = new Coords(co_);
        cp_.getLevel().setPoint(pt_);
        DataBase data_ = getDataBase();
        return data_.getMap().getAccessCondition().contains(cp_);
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
        Coords co_ = getForms().getValCoords(CST_COORDS);
        DataBase data_ = getDataBase();
        Place p_ = data_.getMap().getPlace(co_.getNumberPlace());
        if (p_ instanceof City) {
            City c_ = (City) p_;
            if (co_.isInside()) {
                Point ptInside_ = co_.getInsideBuilding();
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
//
//    public boolean getProponeTile() {
//        return proponeTile;
//    }
//
//    public boolean getProponeLink() {
//        return proponeLink;
//    }
//
//    public boolean getSeeArea() {
//        return seeArea;
//    }

//    public IdList<Direction> getDirs() {
//        return dirs;
//    }
}