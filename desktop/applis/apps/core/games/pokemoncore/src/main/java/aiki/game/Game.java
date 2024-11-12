package aiki.game;
import aiki.comments.Comment;
import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.facade.SexListInt;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.enums.InterfaceType;
import aiki.game.fight.*;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.game.params.Difficulty;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.Condition;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.*;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Campaign;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.ScreenCoords;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;


public final class Game {

    public static final int NB_HOSTED_POKEMON = 2;

    public static final String BEATEN_TRAINER = "0";
    public static final String BEATEN_TRAINER_FULL = "1";
    public static final String CAUGHT_PK = "2";
    public static final String END_GAME = "3";
    public static final String LOST_MONEY = "4";
    public static final String MISSING_PK = "5";
    public static final String NOT_CAUGHT_PK = "6";
    public static final String NOT_ENOUGH_PLACE = "7";
    public static final String NOT_ENOUGH_PLACE_PARENTS = "8";
    public static final String NO_BEATEN_TRAINER = "9";
    public static final String NO_COMMON_EGG = "10";
    public static final String POSSIBLE_BEAT_LEADER = "11";
    public static final String RECEIVED_EGG = "12";
    public static final String RECEIVED_EGG_PARENTS = "13";
    public static final String RECEIVED_EGG_WITHOUT_PARENT = "14";
    public static final String RECEIVED_PARENTS = "15";
    public static final String REMAINING_TRAINERS_GYM = "16";
    public static final String SAME_GENDER = "17";
    public static final String SAME_PK = "18";
    public static final String WON_MONEY = "19";

    private static final String SEPARATOR_TRAINERS = " ";

    private String zippedRom;

    /**contient les elements modifies par l'player*/
    private Player player;

    /**nombre de dresseurs de la ligue battus (remis a zero une fois la ligue battue)*/
    private byte rankLeague;

    private ShortMap<PointEqList> beatGymTrainer;

    private CoordssBoolVal beatGymLeader;

    /***/
    private NbFightCoordss beatTrainer;

    /***/
    private CoordssBoolVal takenObjects;

    /***/
    private CoordssBoolVal takenPokemon;

    /***/
    private Coords playerCoords;

    /**(0,1);(1,0);(0,-1);(-1,0)*/
    private Direction playerOrientation;

    /***/
    private CoordssHostPokemonDuo hostedPk;

    /***/
    private Fight fight;

    /***/
    private Difficulty difficulty;

    private int indexPeriodFishing;

    private int indexPeriod;

    private int indexStep;

    //private String comment;

    private Comment commentGame = new Comment();

    private boolean reinitInteraction;

    private CoordssBoolVal visitedPlaces;
    private ShortMap<BoolVal> visitedPlacesNb;

    private int nbSteps;

    private boolean placeChanged;

    private InterfaceType interfaceType;

    private boolean showEndGame;

    private StringList partiallyAccessiblePlaces = new StringList();

    private StringList fullAccessiblePlaces = new StringList();

    public Game(){
        difficulty = new Difficulty();
        setBeatGymTrainer(new ShortMap<PointEqList>());
        reinitInteraction=false;
        setInterfaceType(InterfaceType.RIEN);
        setVisitedPlacesNb(new ShortMap<BoolVal>());
        setVisitedPlaces(new CoordssBoolVal());
        setBeatGymLeader(new CoordssBoolVal());
        setTakenObjects(new CoordssBoolVal());
        setTakenPokemon(new CoordssBoolVal());
        setBeatTrainer(new NbFightCoordss());
        setHostedPk(new CoordssHostPokemonDuo());
        setFight(new Fight());
        playerCoords = new Coords();
    }

    public Game(DataBase _d){
        fight = FightFacade.newFight();
        beatGymTrainer = new ShortMap<PointEqList>();
        difficulty = new Difficulty();
        reinitInteraction=false;
        interfaceType=InterfaceType.RIEN;
        DataMap d_=_d.getMap();
        visitedPlaces = new CoordssBoolVal();
        visitedPlacesNb = new ShortMap<BoolVal>();
        playerCoords= new Coords(d_.getBegin());
        playerOrientation=Direction.UP;
        takenPokemon = new CoordssBoolVal();
        for (Coords c: d_.getTakenPokemon()){
            takenPokemon.put(c, BoolVal.FALSE);
        }
        takenObjects = new CoordssBoolVal();
        for (Coords c: d_.getTakenObjects()){
            takenObjects.put(c, BoolVal.FALSE);
        }
        beatGymLeader = new CoordssBoolVal();
        for (Coords c: d_.getBeatGymLeader()){
            beatGymLeader.put(c, BoolVal.FALSE);
        }
        beatTrainer = new NbFightCoordss();
        for (NbFightCoords c: d_.getBeatTrainer()) {
            beatTrainer.put(c, BoolVal.FALSE);
        }
        beatGymTrainer = new ShortMap<PointEqList>();
        for (Short c: d_.getBeatGymTrainer().getKeys()){
            beatGymTrainer.put(c, new PointEqList());
        }
        hostedPk = new CoordssHostPokemonDuo();
        for (Coords c: d_.getHostPokemons()){
            HostPokemonDuo h_ = new HostPokemonDuo();
            h_.setFirstPokemon(new PokemonPlayer());
            h_.setSecondPokemon(new PokemonPlayer());
            hostedPk.put(c, h_);
        }
        visitedPlaces = new CoordssBoolVal();
        visitedPlacesNb = new ShortMap<BoolVal>();
        for (Coords c: d_.getCities()) {
            visitedPlaces.put(c, BoolVal.FALSE);
            visitedPlacesNb.put(c.getNumberPlace(), BoolVal.FALSE);
        }
        indexStep=0;
        indexPeriod=0;
        indexPeriodFishing = 0;
        rankLeague=0;
    }

    public void visitFirstPlaces(DataBase _d) {
        DataMap d_=_d.getMap();
        visitedPlacesNb.clear();
        for (EntryCust<Coords,BoolVal> e: visitedPlaces.entryList()) {
            visitedPlacesNb.put(e.getKey().getNumberPlace(),e.getValue());
        }
        for (Coords c: visitedPlaces.getKeys()) {
            if (d_.getAccessibility().getVal(c).isEmpty()) {
                visitedPlaces.put(c, BoolVal.TRUE);
                visitedPlacesNb.put(c.getNumberPlace(), BoolVal.TRUE);
            }
        }
    }

    public void initUserInteract(String _pseudo,Difficulty _diff,DataBase _import){
        initUserInteract(_pseudo,Sex.NO, _diff, _import);
    }
    public void initUserInteract(String _pseudo,Sex _sexeHeros,Difficulty _diff,DataBase _import){
        initUtilisateur(_pseudo, _sexeHeros, _diff, _import);
        visitFirstPlaces(_import);
        DataMap d_ = _import.getMap();
        directInteraction(d_);
    }

    public void initUtilisateur(String _pseudo,Difficulty _diff,DataBase _import){
        initUtilisateur(_pseudo,Sex.NO,_diff,_import);
    }

    public void initUtilisateur(String _pseudo,Sex _sexeHeros,Difficulty _diff,DataBase _import){
        player=new Player(_pseudo,_sexeHeros,_diff,true,_import);
    }

    public void initUtilisateurSimulation(String _pseudo, Difficulty _diff, DataBase _import){
        player=Player.build(_pseudo,_diff,false,_import);
    }

    public boolean validate(DataBase _data, SexListInt _sexList) {
        visitFirstPlaces(_data);
        if (!player.validate(_data,_sexList)) {
            return false;
        }
        boolean existNonKoNonEggPok_ = existNonKoNonEggPok();
        if (!existNonKoNonEggPok_) {
            return false;
        }
        DataMap map_ = _data.getMap();
        Condition accessCond_ = koVisited(map_);
        if (accessCond_ == null) {
            return false;
        }
        if (koTrainer(map_, accessCond_)) {
            return false;
        }
        if (!Coords.equalsSet(takenObjects.getKeys(), map_.getTakenObjects())) {
            return false;
        }
        if (!Coords.equalsSet(takenPokemon.getKeys(), map_.getTakenPokemon())) {
            return false;
        }
        checkCoords(_data, accessCond_);
        difficulty.validate(_data);
        if (!FightFacade.validate(fight, _data, player, difficulty)) {
            return false;
        }
        if (!fight.getFightType().isWild() && !isFrontOfTrainer(_data)) {
            playerCoords.affect(map_.getBegin());
            rankLeague = 0;
//                return false;
        }
        if (koHost(_data, accessCond_)) {
            return false;
        }
        if (badIndex()) {
            return false;
        }
        Place currentPlace_ = map_.getPlace(playerCoords.getNumberPlace());
        if (currentPlace_ instanceof League) {
            if (rankLeague < playerCoords.getLevel().getLevelIndex()) {
                return false;
            }
            return rankLeague <= playerCoords.getLevel().getLevelIndex() + 1;
        } else {
            return rankLeague == 0;
        }
    }

    private Condition koVisited(DataMap _map) {
        if (!Coords.equalsSet(_map.getCities(), visitedPlaces.getKeys())) {
            return null;
        }
        Condition accessCond_ = accessCond(_map);
//        for (Coords c: visitedPlaces.getKeys(true))
        for (Coords c: getVisited()) {
            Condition cond_ = new Condition();
            for (EntryCust<Coords,Condition> e: _map.getAccessibility().entryList()) {
                if (!Coords.eq(e.getKey(),c)) {
                    continue;
                }
                cond_.addAllElts(e.getValue());
            }
//            cond_.addAllElts(map_.getAccessibility().getVal(c));
            cond_.retainAllElements(accessCond_);
//            if (!beatGymLeader.getKeys(true).containsAllObj(cond_)) {
//                return false;
//            }
            if (!getBeatenGymLeader().containsAllObj(cond_)) {
                return null;
            }
        }
        return accessCond_;
    }

    private Condition accessCond(DataMap _map) {
        Condition accessCond_ = new Condition();
        for (CustList<Coords> l: _map.getAccessCondition().values()) {
            accessCond_.addAllElts(l);
        }
        return accessCond_;
    }

    private boolean koTrainer(DataMap _map, Condition _accessCond) {
        if (!NumberUtil.equalsSetShorts(beatGymTrainer.getKeys(), _map.getBeatGymTrainer().getKeys())) {
            return true;
        }
        for (Short k: beatGymTrainer.getKeys()) {
            if (!_map.getBeatGymTrainer().getVal(k).containsAllObj(beatGymTrainer.getVal(k))) {
                return true;
            }
        }
        if (!NbFightCoords.equalsSet(beatTrainer.getKeys(), _map.getBeatTrainer())) {
            return true;
        }
        if (!Coords.equalsSet(beatGymLeader.getKeys(), _map.getBeatGymLeader())) {
            return true;
        }
//        for (Coords c: beatGymLeader.getKeys(true))
        for (Coords c: getBeatenGymLeader()) {
            Condition cond_ = conditionBeatenGymLeader(_map, _accessCond, c);
//            if (!beatGymLeader.getKeys(true).containsAllObj(cond_)) {
//                return false;
//            }
            if (!getBeatenGymLeader().containsAllObj(cond_)) {
                return true;
            }
        }
//        for (Coords c: beatGymLeader.getKeys(true))
        for (Coords c: getBeatenGymLeader()) {
            if (beatGymTrainer.contains(c.getNumberPlace())) {
                beatGymTrainer.getVal(c.getNumberPlace()).clear();
                beatGymTrainer.getVal(c.getNumberPlace()).addAllElts(_map.getBeatGymTrainer().getVal(c.getNumberPlace()));
            }
        }
        return false;
    }

    private Condition conditionBeatenGymLeader(DataMap _map, Condition _accessCond, Coords _c) {
        Condition cond_ = new Condition();
        Coords coords_ = new Coords(_c);
        if (coords_.isInside()) {
            Point ext_ = new Point(coords_.getInsideBuilding());
            ext_.moveTo(Direction.DOWN);
            //not on the door of the building
            coords_.getLevel().setPoint(ext_);
            coords_.outside();
        }
        for (EntryCust<Coords,Condition> e: _map.getAccessibility().entryList()) {
            if (!Coords.eq(e.getKey(),coords_)) {
                continue;
            }
            cond_.addAllElts(e.getValue());
        }
        cond_.retainAllElements(_accessCond);
        return cond_;
    }

    private void checkCoords(DataBase _data, Condition _accessCond) {
        DataMap map_ = _data.getMap();
        boolean correctCoords_ = correctCoords(_data);
        Coords coords_ = new Coords(playerCoords);
        if (correctCoords_) {
            if (!isEmpty(_data.getMap(), playerCoords)) {
                playerCoords.affect(map_.getBegin());
                rankLeague = 0;
            }
        } else {
            playerCoords.affect(map_.getBegin());
            rankLeague = 0;
        }
        if (coords_.isInside()) {
            Point ext_ = new Point(coords_.getInsideBuilding());
            ext_.moveTo(Direction.DOWN);
            //not on the door of the building
            coords_.getLevel().setPoint(ext_);
            coords_.outside();
        }
        if (!map_.getAccessibility().contains(coords_)) {
            correctCoords_ = false;
        }
        Condition cond_ = new Condition();
        if (correctCoords_) {
            for (EntryCust<Coords,Condition> e: map_.getAccessibility().entryList()) {
                if (!Coords.eq(e.getKey(),coords_)) {
                    continue;
                }
                cond_.addAllElts(e.getValue());
            }
        } else {
//            if (fight.getFightType().isExisting()) {
//                return false;
//            }
            playerCoords.affect(map_.getBegin());
            rankLeague = 0;
        }
        cond_.retainAllElements(_accessCond);
//        if (!beatGymLeader.getKeys(true).containsAllObj(cond_))
        if (!getBeatenGymLeader().containsAllObj(cond_)) {
//            if (fight.getFightType().isExisting()) {
//                return false;
//            }
            playerCoords.affect(map_.getBegin());
            rankLeague = 0;
        }
    }

    private boolean koHost(DataBase _data, Condition _accessCond) {
        DataMap map_ = _data.getMap();
        if (!Coords.equalsSet(hostedPk.getKeys(), map_.getHostPokemons())) {
            return true;
        }
        for (Coords c: hostedPk.getKeys()) {
            if (!hostedPk.getVal(c).validate(_data)) {
                return true;
            }
            if (availableHosting(c)) {
                continue;
            }
            Coords coords_ = new Coords(c);
            Point ext_ = new Point(coords_.getInsideBuilding());
            ext_.moveTo(Direction.DOWN);
            //not on the door of the building
            coords_.getLevel().setPoint(ext_);
            coords_.outside();
//            if (coords_.isInside()) {
//                Point ext_ = new Point(coords_.getInsideBuilding());
//                ext_.moveTo(Direction.DOWN);//not on the door of the building
//                coords_.getLevel().setPoint(ext_);
//                coords_.outside();
//            }
            Condition cond_ = new Condition();
            for (EntryCust<Coords,Condition> e: map_.getAccessibility().entryList()) {
                if (!Coords.eq(e.getKey(),coords_)) {
                    continue;
                }
                cond_.addAllElts(e.getValue());
            }
            cond_.retainAllElements(_accessCond);
//            if (!beatGymLeader.getKeys(true).containsAllObj(cond_)) {
//                return false;
//            }
            if (!getBeatenGymLeader().containsAllObj(cond_)) {
                return true;
            }
        }
        return false;
    }

    private boolean badIndex() {
        return indexStep < 0 || indexPeriod < 0 || indexPeriodFishing < 0;
    }

    private boolean correctCoords(DataBase _data) {
        boolean correctCoords_;
        short numberPlace_ = playerCoords.getNumberPlace();
        Place curPlace_ = null;
        if (_data.getMap().getPlaces().isValidIndex(numberPlace_)) {
            curPlace_ = _data.getMap().getPlace(numberPlace_);
        }
        if (curPlace_ == null) {
            correctCoords_ = false;
        } else if (playerCoords.isInside()) {
            if (curPlace_ instanceof City) {
                Point bIncome_ = playerCoords.getInsideBuilding();
                if (!((City) curPlace_).getBuildings().contains(bIncome_)) {
                    correctCoords_ = false;
                } else {
                    Level lev_ = ((City) curPlace_).getBuildings().getVal(bIncome_).getLevel();
                    correctCoords_ = checkLevel(lev_);
                }
            } else {
                correctCoords_ = false;
            }
        } else if (!curPlace_.getLevelsList().isValidIndex(playerCoords.getLevel().getLevelIndex())) {
            correctCoords_ = false;
        } else {
            Level curLevel_ = curPlace_.getLevelsList().get(playerCoords.getLevel().getLevelIndex());
            correctCoords_ = checkLevel(curLevel_);
        }
        return correctCoords_;
    }

    private boolean existNonKoNonEggPok() {
        boolean existNonKoNonEggPok_ = false;
        for (UsablePokemon u: player.getTeam()) {
            if (u instanceof PokemonPlayer && !((PokemonPlayer) u).isKo()) {
                existNonKoNonEggPok_ = true;
                break;
            }
        }
        return existNonKoNonEggPok_;
    }

    private boolean checkLevel(Level _l) {
        return _l.getEnvBlockByPoint(playerCoords.getLevel().getPoint()).isValid();
    }
    public void calculateImagesFromTiles(DataBase _data, int _dx, int _dy) {
        DataMap map_ = _data.getMap();
        for (ScreenCoords k: map_.getTiles().getKeys()) {
            Coords coords_ = map_.getTiles().getVal(k);
            calculateImagesFromTiles(_data, k, coords_);
        }
        ScreenCoords center_;
        center_ = new ScreenCoords(map_.getSpaceBetweenLeftAndHeros()+_dx,map_.getSpaceBetweenTopAndHeros()+_dy);
//        CustList<int[][]> images_ = map_.getForegroundImages().getVal(center_);
//        images_.add(getMiniHeros(_data));
        map_.setHerosScreen(center_);
        map_.setHerosImages(getMiniHeros(_data));
//        CustList<int[][]> images_ = map_.getHerosImages().getVal(center_);
//        images_.add(getMiniHeros(_data));
    }

    private void calculateImagesFromTiles(DataBase _data, ScreenCoords _k, Coords _coords) {
        DataMap map_ = _data.getMap();
        if (!_coords.isValid()) {
            map_.getForegroundImages().put(_k, new CustList<int[][]>());
            return;
        }
        Place place_ = map_.getPlace(_coords.getNumberPlace());
        Level level_ = map_.getLevelByCoords(_coords);
        CustList<int[][]> images_ = new CustList<int[][]>();
        Point pt_ = _coords.getLevel().getPoint();
        possibleAccessLeague(_data, _coords, images_);
        possibleLevelLeague(_data, level_, images_, pt_);
        possibleLevelWithWildPokemon(_data, _coords, level_, images_);
        possibleInside(_data, _coords, place_, images_);
        possibleLevelIndoorPokemonCenter(_data, level_, images_, pt_);
        possibleLevelIndoorGym(_data, level_, images_, pt_);
        possibleCave(_data, _coords, place_, level_, images_);
        if (place_ instanceof InitializedPlace) {
            InitializedPlace init_ = (InitializedPlace) place_;
            if (init_.getLinksWithCaves().contains(pt_)) {
                Link link_ = init_.getLinksWithCaves().getVal(pt_);
                images_.add(_data.getLink(link_.getFileName()));
            }
        }
        map_.getForegroundImages().put(_k, images_);
    }

    private void possibleCave(DataBase _data, Coords _coords, Place _place, Level _level, CustList<int[][]> _images) {
        Point pt_ = _coords.getLevel().getPoint();
        if (_place instanceof Cave) {
            Cave cave_ = (Cave) _place;
            LevelPoint lPoint_ = _coords.getLevel();
            if (cave_.getLinksWithOtherPlaces().contains(lPoint_)) {
                Link link_ = cave_.getLinksWithOtherPlaces().getVal(lPoint_);
                _images.add(_data.getLink(link_.getFileName()));
            }
        }
        if (_level instanceof LevelCave) {
            LevelCave lv_ = (LevelCave) _level;
            if (lv_.getLinksOtherLevels().contains(pt_)) {
                Link link_ = lv_.getLinksOtherLevels().getVal(pt_);
                _images.add(_data.getLink(link_.getFileName()));
            }
        }
    }

    private void possibleLevelIndoorGym(DataBase _data, Level _level, CustList<int[][]> _images, Point _pt) {
        if (_level instanceof LevelIndoorGym) {
            LevelIndoorGym lv_ = (LevelIndoorGym) _level;
            if (lv_.getGymTrainers().contains(_pt)) {
                Person person_ = lv_.getGymTrainers().getVal(_pt);
                _images.add(_data.getPerson(person_.getImageMiniFileName()));
            }
            if (Point.eq(_pt, lv_.getGymLeaderCoords())) {
                Person person_ = lv_.getGymLeader();
                _images.add(_data.getPerson(person_.getImageMiniFileName()));
            }
        }
    }

    private void possibleLevelIndoorPokemonCenter(DataBase _data, Level _level, CustList<int[][]> _images, Point _pt) {
        if (_level instanceof LevelIndoorPokemonCenter) {
            LevelIndoorPokemonCenter lv_ = (LevelIndoorPokemonCenter) _level;
            if (lv_.getGerants().contains(_pt)) {
                Person person_ = lv_.getGerants().getVal(_pt);
                _images.add(_data.getPerson(person_.getImageMiniFileName()));
            }
            if (Point.eq(lv_.getStorageCoords(), _pt)) {
                _images.add(_data.getStorage().getImage());
            }
        }
    }

    private void possibleInside(DataBase _data, Coords _coords, Place _place, CustList<int[][]> _images) {
        Point pt_ = _coords.getLevel().getPoint();
        if (_place instanceof City&& _coords.isInside()) {
            City city_ = (City) _place;
            Building building_ = city_.getBuildings().getVal(_coords.getInsideBuilding());
            if (Point.eq(building_.getExitCity(), pt_)) {
                _images.add(_data.getLink(building_.getImageFileName()));
            }
        }
    }

    private void possibleLevelWithWildPokemon(DataBase _data, Coords _coords, Level _level, CustList<int[][]> _images) {
        Point pt_ = _coords.getLevel().getPoint();
        DataMap map_ = _data.getMap();
        if (!(_level instanceof LevelWithWildPokemon)) {
            return;
        }
        LevelWithWildPokemon lv_ = (LevelWithWildPokemon) _level;
        if (lv_.getCharacters().contains(pt_)) {
            Person person_ = lv_.getPerson(pt_);
            _images.add(_data.getPerson(person_.getImageMiniFileName()));
        }
        if (lv_.containsPokemon(pt_) && !isEmpty(map_, _coords)) {
            WildPk pk_ = lv_.getPokemon(pt_);
            _images.add(_data.getMiniPk().getVal(pk_.getName()).getImage());
        }
        if (lv_.getItems().contains(pt_) && !isEmpty(map_, _coords)) {
            _images.add(_data.getMiniItems().getVal(lv_.getItems().getVal(pt_)).getImage());
        }
        if (lv_.getTm().contains(pt_) && !isEmpty(map_, _coords)) {
            _images.add(_data.getImageTmHm().getImage());
        }
        if (lv_.getHm().contains(pt_) && !isEmpty(map_, _coords)) {
            _images.add(_data.getImageTmHm().getImage());
        }
        possibleDualFight(_data, _coords, _images, lv_);
    }

    private void possibleDualFight(DataBase _data, Coords _coords, CustList<int[][]> _images, LevelWithWildPokemon _lv) {
        DataMap map_ = _data.getMap();
        Point pt_ = _coords.getLevel().getPoint();
        if (_lv.getDualFights().contains(pt_) && !isEmpty(map_, _coords)) {
            DualFight dual_ = _lv.getDualFights().getVal(pt_);
            TempTrainer tmp_ = dual_.getFoeTrainer();
            _images.add(_data.getPerson(tmp_.getImageMiniFileName()));
        }
        for (DualFight d: _lv.getDualFights().values()) {
            if (Point.eq(d.getPt(), pt_)) {
                if (!isEmpty(map_, _coords)) {
                    TempTrainer tmp_ = d.getFoeTrainer();
                    _images.add(_data.getPerson(tmp_.getImageMiniSecondTrainerFileName()));
                }
                break;
            }
        }
    }

    private void possibleLevelLeague(DataBase _data, Level _level, CustList<int[][]> _images, Point _pt) {
        if (_level instanceof LevelLeague) {
            LevelLeague lv_ = (LevelLeague) _level;
            if (Point.eq(_pt, lv_.getTrainerCoords())) {
                Person person_ = lv_.getTrainer();
                _images.add(_data.getPerson(person_.getImageMiniFileName()));
            }
            if (Point.eq(_pt, lv_.getAccessPoint())) {
                _images.add(_data.getLink(lv_.getFileName()));
            }
        }
    }

    private void possibleAccessLeague(DataBase _data, Coords _coords, CustList<int[][]> _images) {
        DataMap map_ = _data.getMap();
        for (Place p: map_.getPlaces()) {
            if (p instanceof League) {
                Coords access_ = ((League) p).getAccessCoords();
                if (Coords.eq(_coords, access_)) {
                    _images.add(_data.getLink(((League) p).getFileName()));
                    break;
                }
            }
        }
    }

    public int[][] getMiniHeros(DataBase _data) {
        DataMap map_ = _data.getMap();
        EnvironmentType currentEnv_ = map_.currentBlock(playerCoords).getType();
        ImageHeroKey key_;
        key_ = ImageHeroKey.direct(currentEnv_, playerOrientation, player);
        if (_data.getOverWorldHeros().contains(key_)) {
            return _data.getOverWorldHeros().getVal(key_).getImage();
        }
        key_ = ImageHeroKey.direct(EnvironmentType.ROAD, playerOrientation, player);
        return _data.getOverWorldHeros().getVal(key_).getImage();
    }

    public int[][] getBackHeros(DataBase _data) {
        ImageHeroKey key_;
        EnvironmentType currentEnv_ = _data.getMap().currentBlock(playerCoords).getType();
        Sex sex_ = player.getSex();
        key_ = new ImageHeroKey(currentEnv_, sex_);
        if (_data.getBackHeros().contains(key_)) {
            return _data.getBackHeros().getVal(key_).getImage();
        }
        key_ = new ImageHeroKey(EnvironmentType.ROAD, sex_);
        return _data.getBackHeros().getVal(key_).getImage();
    }

    public int[][] getBackHerosSexOpposite(DataBase _data) {
        ImageHeroKey key_;
        EnvironmentType currentEnv_ = _data.getMap().currentBlock(playerCoords).getType();
        key_ = ImageHeroKey.opposite(currentEnv_, player);
        if (_data.getBackHeros().contains(key_)) {
            return _data.getBackHeros().getVal(key_).getImage();
        }
        key_ = ImageHeroKey.opposite(EnvironmentType.ROAD, player);
        return _data.getBackHeros().getVal(key_).getImage();
    }

    public boolean isDualFight() {
        return fight.getFightType() == FightType.TMP_TRAINER;
    }

//    void prendrePokemon(Coords _coords){
//        takenPokemon.put(_coords,true);
//    }

//    boolean elementAbsent(Coords _coords){
//        if(takenPokemon.contains(_coords)){
//            return takenPokemon.getVal(_coords);
//        }
//        if(takenObjects.contains(_coords)){
//            return takenObjects.getVal(_coords);
//        }
//        return false;
//    }

    public boolean availableHosting(Coords _coords){
        return hostedPk.getVal(_coords).isFree();
    }

    public void attemptForStoringPokemonToHost(short _pos1,short _pos2,DataBase _d){
        commentGame.clearMessages();
        DataMap d_=_d.getMap();
        int nb_ = player.getPokemonPlayerList().size();
        reinitInteraction = false;
        if (nb_ > NB_HOSTED_POKEMON) {
            if(canStorePokemonToHost(_pos1,_pos2,_d)){
                Coords voisin_ = closestTile(d_);
                storePokemonToHost(_pos1,_pos2,voisin_);
            } else {
                reinitInteraction = true;
            }
        } else {
            int nbMin_ = NB_HOSTED_POKEMON;
            nbMin_++;
            nbMin_ -= nb_;
            StringMap<String> mess_ = _d.getMessagesGame();
            commentGame.addMessage(mess_.getVal(MISSING_PK), Long.toString(nbMin_));
            reinitInteraction = true;
        }
    }

    boolean canStorePokemonToHost(short _pos1,short _pos2,DataBase _d){
        if(_pos1==_pos2){
            StringMap<String> mess_ = _d.getMessagesGame();
            commentGame.addMessage(mess_.getVal(SAME_PK));
            return false;
        }
        PokemonPlayer pkOne_=(PokemonPlayer) player.getTeam().get(_pos1);
        PokemonPlayer pkTwo_=(PokemonPlayer) player.getTeam().get(_pos2);
        return canStoreThesePokemonToHost(commentGame, pkOne_, pkTwo_, _d);
    }

    static boolean canStoreThesePokemonToHost(Comment _commentGame, PokemonPlayer _pkOne, PokemonPlayer _pkTwo,DataBase _d) {
        PokemonData pkDataOne_=_d.getPokedex().getVal(_pkOne.getName());
        StringList groupsOne_= pkDataOne_.getEggGroups();
        PokemonData pkDataTwo_=_d.getPokedex().getVal(_pkTwo.getName());
        StringList groupsTwo_= pkDataTwo_.getEggGroups();
        StringMap<String> mess_ = _d.getMessagesGame();
        if(Gender.getGendersWithSex().containsObj(_pkOne.getGender())){
            if(Gender.getGendersWithSex().containsObj(_pkTwo.getGender())){
                boolean canStore_ = canStore(_d,_commentGame, _pkOne, _pkTwo);
                if (matchDirectGroup(_d, pkDataOne_, pkDataTwo_)) {
                    return canStore_;
                }
                boolean vide_ = noIntersect(groupsOne_, groupsTwo_);
                if (vide_) {
                    _commentGame.addMessage(mess_.getVal(NO_COMMON_EGG));
                    return false;
                }
                return canStore_;
            }
            if (StringUtil.contains(groupsTwo_, _d.getDefaultEggGroup())) {
                return true;
            }
            _commentGame.addMessage(mess_.getVal(NO_COMMON_EGG));
            return false;
        }
        if(Gender.getGendersWithSex().containsObj(_pkTwo.getGender())){
            if (StringUtil.contains(groupsOne_, _d.getDefaultEggGroup())) {
                return true;
            }
            _commentGame.addMessage(mess_.getVal(NO_COMMON_EGG));
            return false;
        }
        if (atLeastOneDefaultGroup(_d, groupsOne_, groupsTwo_)) {
            return true;
        }
        _commentGame.addMessage(mess_.getVal(NO_COMMON_EGG));
        return false;
    }

    private static boolean atLeastOneDefaultGroup(DataBase _d, StringList _groupsOne, StringList _groupsTwo) {
        return StringUtil.contains(_groupsOne, _d.getDefaultEggGroup()) || StringUtil.contains(_groupsTwo, _d.getDefaultEggGroup());
    }

    private static boolean canStore(DataBase _d, Comment _commentGame, PokemonPlayer _pkOne, PokemonPlayer _pkTwo) {
        StringMap<String> mess_ = _d.getMessagesGame();
        boolean canStore_ = true;
        if (_pkOne.getGender() == _pkTwo.getGender()) {
            canStore_ = false;
            _commentGame.addMessage(mess_.getVal(SAME_GENDER));
        }
        return canStore_;
    }

    private static boolean matchDirectGroup(DataBase _d, PokemonData _pkDataOne, PokemonData _pkDataTwo) {
        StringList groupsOne_= _pkDataOne.getEggGroups();
        StringList groupsTwo_= _pkDataTwo.getEggGroups();
        return StringUtil.contains(groupsOne_, _d.getDefaultEggGroup()) || StringUtil.contains(groupsTwo_, _d.getDefaultEggGroup()) || StringUtil.quickEq(_pkDataOne.getBaseEvo(), _pkDataTwo.getBaseEvo());
    }

    private static boolean noIntersect(StringList _groupsOne, StringList _groupsTwo) {
        boolean vide_=true;
        for(String e: _groupsOne){
            if(StringUtil.contains(_groupsTwo, e)){
                vide_=false;
                break;
            }
        }
        return vide_;
    }

    void storePokemonToHost(short _pos1,short _pos2,Coords _coords){
        HostPokemonDuo valeur_=hostedPk.getVal(_coords);
        valeur_.setFirstPokemon((PokemonPlayer) player.getTeam().get(_pos1));
        valeur_.setSecondPokemon((PokemonPlayer) player.getTeam().get(_pos2));
        valeur_.setNbSteps(0);
        player.reindexAfterStoringToHost(_pos1,_pos2);
    }

    boolean takablePokemonFromHost(DataBase _import){
        int nb_ = player.getTeam().size();
        return nb_ + NB_HOSTED_POKEMON <= _import.getNbMaxTeam();
    }

    void takePokemonFromHost(Coords _coords){
        HostPokemonDuo valeur_=hostedPk.getVal(_coords);
        player.takeHostedPokemon(valeur_.getFirstPokemon(),valeur_.getSecondPokemon());
        valeur_.setFirstPokemon(new PokemonPlayer());
        valeur_.setSecondPokemon(new PokemonPlayer());
        valeur_.setNbSteps(0);
    }

    void incrementStepsToLayEggs(DataBase _d){
        for(Coords c:hostedPk.getKeys()){
            if (availableHosting(c)) {
                continue;
            }
            HostPokemonDuo valeur_=hostedPk.getVal(c);
            int nbSteps_ = valeur_.getNbSteps();
            if (nbSteps_<_d.getNbMaxSteps()) {
                valeur_.setNbSteps(nbSteps_+1);
            }
        }
    }


    public int nbRemainingSteps(Coords _coords,DataBase _d){
        HostPokemonDuo valeur_=hostedPk.getVal(_coords);
        PokemonData pkDataOne_=_d.getPokedex().getVal(valeur_.getFirstPokemon().getName());
        PokemonData pkDataTwo_=_d.getPokedex().getVal(valeur_.getSecondPokemon().getName());
        if(StringUtil.quickEq(pkDataOne_.getBaseEvo(),pkDataTwo_.getBaseEvo())){
            return _d.getNbMaxStepsSameEvoBase()-valeur_.getNbSteps();
        }
        return _d.getNbMaxSteps()-valeur_.getNbSteps();
    }

    boolean canGetEgg(Coords _coords,DataBase _d){
        if(availableHosting(_coords)){
            return false;
        }
        return nbRemainingSteps(_coords, _d) <= 0;
    }

    Egg productedEgg(Coords _coords,DataBase _d){
        LgInt maxRd_ = _d.getMaxRd();
        return new Egg(lawForProductedEgg(_coords, _d).editNumber(maxRd_,_d.getGenerator()));
    }

    MonteCarloString lawForProductedEgg(Coords _coords,DataBase _d) {
        MonteCarloString law_ = new MonteCarloString();
        HostPokemonDuo valeur_=hostedPk.getVal(_coords);
        PokemonPlayer firstPokemon_ = valeur_.getFirstPokemon();
        PokemonPlayer secondPokemon_ = valeur_.getSecondPokemon();
        boolean oppositeSex_ = Gender.getGendersWithSex().containsObj(firstPokemon_.getGender()) && Gender.getGendersWithSex().containsObj(secondPokemon_.getGender());
        //because firstPokemon_ and secondPokemon_ have a sex
        //and pokemon with same sex cannot be stored to host
        //so firstPokemon_ and secondPokemon_ have an opposite sex.
        //i.e. firstPokemon_.getGender() != secondPokemon_.getGender()
        if (oppositeSex_) {
            PokemonData fPk_ = _d.getPokemon(firstPokemon_.getName());
            String event_ = fPk_.getBaseEvo();
            law_.addQuickEvent(event_, DataBase.defElementaryEvent());
            fPk_ = _d.getPokemon(secondPokemon_.getName());
            if (!StringUtil.quickEq(fPk_.getBaseEvo(), event_)) {
                law_.addQuickEvent(fPk_.getBaseEvo(), DataBase.defElementaryEvent());
            }
            return law_;
        }
        PokemonData fPk_ = _d.getPokemon(firstPokemon_.getName());
        if (StringUtil.contains(fPk_.getEggGroups(), _d.getDefaultEggGroup())) {
            fPk_ = _d.getPokemon(secondPokemon_.getName());
        }
        law_.addQuickEvent(fPk_.getBaseEvo(), DataBase.defElementaryEvent());
        return law_;
    }

    void takeProductedEgg(Coords _coords){
        hostedPk.getVal(_coords).setNbSteps(0);
    }

    public void receiveOnlyEgg(DataBase _d) {
        commentGame.clearMessages();
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        reinitInteraction=true;
        int total_=player.getTeam().size();
        if(!canGetEgg(voisin_,_d)){
            reinitInteraction = total_ + NB_HOSTED_POKEMON > _d.getNbMaxTeam();
            return;
        }
        StringMap<String> mess_ = _d.getMessagesGame();
        if(total_<_d.getNbMaxTeam()){
            Egg oeuf_=productedEgg(voisin_,_d);
            player.recupererOeufPensions(oeuf_);
            takeProductedEgg(voisin_);
            String name_ = _d.translatePokemon(oeuf_.getName());
            commentGame.addMessage(mess_.getVal(RECEIVED_EGG), name_);
            reinitInteraction=false;
            //interactions->raz_interaction();
        }else{
            commentGame.addMessage(mess_.getVal(NOT_ENOUGH_PLACE));
        }
    }

    public void receiveEggOrParents(DataBase _d) {
        commentGame.clearMessages();
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        reinitInteraction=false;
        StringMap<String> mess_ = _d.getMessagesGame();
        if(canGetEgg(voisin_,_d)){
            int total_=player.getTeam().size();
            if(total_ + NB_HOSTED_POKEMON < _d.getNbMaxTeam()){
                Egg oeuf_=productedEgg(voisin_,_d);
                takePokemonFromHost(voisin_);
                player.recupererOeufPensions(oeuf_);
                takeProductedEgg(voisin_);
                String name_ = _d.translatePokemon(oeuf_.getName());
                commentGame.addMessage(mess_.getVal(RECEIVED_EGG_PARENTS), name_);
            }else if(total_<_d.getNbMaxTeam()){
                Egg oeuf_=productedEgg(voisin_,_d);
                player.recupererOeufPensions(oeuf_);
                takeProductedEgg(voisin_);
                String name_ = _d.translatePokemon(oeuf_.getName());
                commentGame.addMessage(mess_.getVal(RECEIVED_EGG_WITHOUT_PARENT), name_);
                reinitInteraction = true;
                //interactions->raz_interaction();
            }else{
                commentGame.addMessage(mess_.getVal(NOT_ENOUGH_PLACE));
                reinitInteraction = true;
            }
        }else{
            if(takablePokemonFromHost(_d)){
                takePokemonFromHost(voisin_);
                commentGame.addMessage(mess_.getVal(RECEIVED_PARENTS));
                reinitInteraction = false;
            } else {
                commentGame.addMessage(mess_.getVal(NOT_ENOUGH_PLACE_PARENTS));
                reinitInteraction = true;
            }
        }
    }

    public void takeObject(DataBase _d) {
        DataMap d_=_d.getMap();
        Coords voisin_ = closestTile(d_);
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) d_.getLevelByCoords(voisin_);
        Point pt_ = voisin_.getLevel().getPoint();
        if (l_.getItems().contains(pt_)) {
            String obj_ = l_.getItems().getVal(pt_);
            player.getItem(obj_);
            takenObjects.put(voisin_, BoolVal.TRUE);
            interfaceType = InterfaceType.RIEN;
        } else if (l_.getTm().contains(pt_)) {
            short obj_ = l_.getTm().getVal(pt_);
            player.getTm(obj_);
            takenObjects.put(voisin_, BoolVal.TRUE);
            interfaceType = InterfaceType.RIEN;
        } else if (l_.getHm().contains(pt_)) {
            short obj_ = l_.getHm().getVal(pt_);
            player.getHm(obj_);
            takenObjects.put(voisin_, BoolVal.TRUE);
            interfaceType = InterfaceType.RIEN;
        } else {
            //l_.getCharacters().contains(pt_)
            CharacterInRoadCave person_ = l_.getCharacters().getVal(pt_);
            //person_ instanceof DealerItem
            for (String o: ((DealerItem)person_).getItems()) {
                player.getItem(o);
            }
            for (Short t: ((DealerItem)person_).getTechnicalMoves()) {
                player.getTm(t);
            }
            takenObjects.put(voisin_, BoolVal.TRUE);
            interfaceType = InterfaceType.RIEN;
        }
    }

    public void initTrainerFight(DataBase _d) {
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        Place pl_ = d_.getPlace(voisin_.getNumberPlace());
        if (pl_ instanceof League) {
            TrainerLeague tr_ = ((League)pl_).getRooms().get(voisin_.getLevel().getLevelIndex()).getTrainer();
            FightFacade.initFight(fight,player,difficulty,tr_,_d);
            FightFacade.initTypeEnv(fight,playerCoords,difficulty,_d);
            commentGame.addComment(fight.getComment());
            return;
        }
        if (pl_ instanceof City) {
            //voisin_.isInside()
            Level l_ = pl_.getLevelByCoords(voisin_);
            //l_ instanceof LevelIndoorGym
            LevelIndoorGym gym_ = (LevelIndoorGym) l_;
            if(gym_.getGymTrainers().contains(voisin_.getLevel().getPoint())) {
                GymTrainer gymTr_ = gym_.getGymTrainers().getVal(voisin_.getLevel().getPoint());
                FightFacade.initFight(fight,player,difficulty,gymTr_,_d);
                FightFacade.initTypeEnv(fight,playerCoords,difficulty,_d);
            } else {
                //if(Point.eq(gym_.getGymLeaderCoords(),voisin_.getLevel().getPoint()))
                GymLeader gymTr_ = gym_.getGymLeader();
                FightFacade.initFight(fight,player,difficulty,gymTr_,_d);
                FightFacade.initTypeEnv(fight,playerCoords,difficulty,_d);
            }
            commentGame.addComment(fight.getComment());
            return;
        }
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) pl_.getLevelByCoords(voisin_);
        for (EntryCust<Point,DualFight> e: l_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (!Point.eq(e.getKey(), voisin_.getLevel().getPoint()) && !Point.eq(dual_.getPt(), voisin_.getLevel().getPoint())) {
                continue;
            }
            FightFacade.initFight(fight,player,difficulty,dual_,_d);
            FightFacade.initTypeEnv(fight,playerCoords,difficulty,_d);
            commentGame.addComment(fight.getComment());
            return;
        }
        //l_.getCharacters().contains(voisin_.getLevel().getPoint())
        TrainerMultiFights ch_ = l_.getTrainers().getVal(voisin_.getLevel().getPoint());
        //ch_ instanceof TrainerMultiFights
        int nb_ = nbFights(voisin_);
        if (!ch_.getTeamsRewards().isValidIndex(nb_)) {
            nb_ = ch_.getTeamsRewards().size() - 1;
        }
        FightFacade.initFight(fight,player,difficulty, ch_,nb_,_d);
        FightFacade.initTypeEnv(fight,playerCoords,difficulty,_d);
        commentGame.addComment(fight.getComment());
    }

    boolean isFrontOfTrainer(DataBase _d) {
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        if (!voisin_.isValid()) {
            return false;
        }
        Place pl_ = d_.getPlace(voisin_.getNumberPlace());
        if (pl_ instanceof League) {
            League league_ = (League) pl_;
            LevelLeague level_ = league_.getRooms().get(voisin_.getLevel().getLevelIndex());
            return Point.eq(level_.getTrainerCoords(), voisin_.getLevel().getPoint());
        }
        if (pl_ instanceof City) {
            if (voisin_.isInside()) {
                Level l_ = pl_.getLevelByCoords(voisin_);
                if (l_ instanceof LevelIndoorGym) {
                    LevelIndoorGym gym_ = (LevelIndoorGym) l_;
                    return gym_.getGymTrainers().contains(voisin_.getLevel().getPoint()) || Point.eq(gym_.getGymLeaderCoords(), voisin_.getLevel().getPoint());
                }
            }
            return false;
        }
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) pl_.getLevelByCoords(voisin_);
        for (EntryCust<Point,DualFight> e: l_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (Point.eq(e.getKey(), voisin_.getLevel().getPoint()) || Point.eq(dual_.getPt(), voisin_.getLevel().getPoint())) {
                return true;
            }
        }
        if(l_.getCharacters().contains(voisin_.getLevel().getPoint())) {
            CharacterInRoadCave ch_ = l_.getCharacters().getVal(voisin_.getLevel().getPoint());
            return ch_ instanceof TrainerMultiFights;
        }
        return false;
    }

    public int[][] getTrainerImage(DataBase _d) {
        if (!isFrontOfTrainer(_d)) {
            return new int[0][0];
        }
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        Place pl_ = d_.getPlace(voisin_.getNumberPlace());
        if (pl_ instanceof League) {
            TrainerLeague tr_ = ((League)pl_).getRooms().get(voisin_.getLevel().getLevelIndex()).getTrainer();
            return _d.getTrainer(tr_.getImageMaxiFileName());
        }
        if (pl_ instanceof City) {
            Level l_ = pl_.getLevelByCoords(voisin_);
            LevelIndoorGym gym_ = (LevelIndoorGym) l_;
            if(gym_.getGymTrainers().contains(voisin_.getLevel().getPoint())) {
                GymTrainer gymTr_ = gym_.getGymTrainers().getVal(voisin_.getLevel().getPoint());
                return _d.getTrainer(gymTr_.getImageMaxiFileName());
            }
            //if(Point.eq(gym_.getGymLeaderCoords(),voisin_.getLevel().getPoint()))
            GymLeader gymTr_ = gym_.getGymLeader();
            return _d.getTrainer(gymTr_.getImageMaxiFileName());
        }
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) pl_.getLevelByCoords(voisin_);
        for (EntryCust<Point,DualFight> e: l_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (!Point.eq(e.getKey(), voisin_.getLevel().getPoint()) && !Point.eq(dual_.getPt(), voisin_.getLevel().getPoint())) {
                continue;
            }
            return _d.getTrainer(dual_.getFoeTrainer().getImageMaxiFileName());
        }
        TrainerMultiFights ch_ = l_.getTrainers().getVal(voisin_.getLevel().getPoint());
        return _d.getTrainer(ch_.getImageMaxiFileName());
    }

    public void initFishing(DataBase _d) {
        DataMap d_=_d.getMap();
        Coords voisin_ = closestTile(d_);
        AbsAreaApparition area_ = d_.getAreaByCoords(voisin_);
        if (area_.isVirtual()) {
            return;
        }
        if (area_.getPokemonListLength(false) == IndexConstants.SIZE_EMPTY) {
            return;
        }
        if(!difficulty.getRandomWildFight()){
            newIndex(false, indexPeriodFishing, area_, _d);
            return;
        }
        newRandomPokemon(area_.getWildPokemonRandFishing(), _d);
    }


    public void initLegendaryPokemonFight(DataBase _d){
        DataMap d_=_d.getMap();
        Coords voisin_= closestTile(d_);
        Level level_ = d_.getLevelByCoords(voisin_);
        Point pt_ = voisin_.getLevel().getPoint();
        WildPk pkLeg_ = ((LevelWithWildPokemon)level_).getPokemon(pt_);
        initFight(_d, pkLeg_);
    }

    public void setChosenTeamPokemon(short _pk) {
        player.setChosenTeamPokemon(_pk);
    }

    public String nickname(){
        return player.nickname();
    }

    public void nickname(String _texte, DataBase _import){
        player.nickname(_texte, _import);
    }

    public void chooseFrontFighter(byte _pos, DataBase _import) {
        FightFacade.chooseFrontFighter(fight, _pos,difficulty,_import);
    }

    public void changeAction(ActionType _action, DataBase _import){
        FightFacade.changeAction(fight, _action, _import, difficulty);
    }

    public void chooseBackFighter(byte _pos, DataBase _import) {
        FightFacade.chooseBackFighter(fight, _pos,_import);
    }

    public void chooseSubstituteFighter(byte _pos, DataBase _import) {
        FightFacade.chooseSubstituteFighter(fight, _pos,_import);
    }

    public void chooseMove(String _move, DataBase _import) {
        FightFacade.chooseMove(fight, _move, difficulty, _import);
    }

    public void deselect() {
        FightFacade.deselect(fight);
    }

    public void setFirstChosenMoveFoeTarget(byte _cible){
        FightFacade.setFirstChosenMoveFoeTarget(fight,_cible);
    }

    public void setFirstChosenMovePlayerTarget(byte _cible){
        FightFacade.setFirstChosenMovePlayerTarget(fight,_cible);
    }

    public void setChosenHealingItem(String _objet,DataBase _import){
        FightFacade.setChosenHealingItem(fight,_objet,_import);
    }

    public NatStringTreeMap<BallNumberRate> calculateCatchingRatesSingle(DataBase _import, byte _creatureSauvage, byte _creatureUt) {
        return FightFacade.calculateCatchingRatesSingle(fight, difficulty, player, _import, _creatureSauvage, _creatureUt);
    }

    //in a bean
    public TeamPositionsStringMapTeamPositionsRate
            remainingThrowersTargetsHp(DataBase _import) {
        return FightFacade.remainingThrowersTargetsHp(fight, difficulty, _import);
    }

    //in a bean
    public CustList<MovesListTeamPositionsList>
        sortedFightersBeginRoundWildFight(
            DataBase _data) {
        return FightFacade.sortedFightersBeginRoundWildFight(fight, difficulty, _data);
    }

    //in a bean
    public TeamPositionList
        sortedFightersBeginRound(
            DataBase _data) {
        return FightFacade.sortedFightersBeginRound(fight, _data);
    }

    public void setSubstituteEndRound(byte _remplacant){
        FightFacade.setSubstituteEndRound(fight,_remplacant);
    }

    public void endFight(DataBase _import) {
        boolean existBall_ = player.existBall(_import);
        FightState etat_=FightFacade.fightStatement(fight,existBall_,difficulty);
        if (etat_==FightState.CAPTURE_KO) {
            fight.setState(etat_);
            return;
        }
        //sortie de cette si pk sauvage
        StringMap<String> mess_ = _import.getMessagesGame();
        if (fight.getFightType().isWild()) {
            wildFight(_import);
            return;
        }
        player.affectEndFight(fight,difficulty,_import);
        if (!isFrontOfTrainer(_import)) {
            player.healTeamWithoutUsingObject(_import);
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        DataMap d_=_import.getMap();
        Coords coordsFoe_ = closestTile(d_);
        Place pl_ = d_.getPlace(coordsFoe_.getNumberPlace());
        int sommeNiveau_ = sommeNiveau();
        LgInt money_ = new LgInt(player.getMoney());
        if(FightFacade.win(fight)){
            winFight(_import);
            return;
        }
        if (FightFacade.equality(fight) && pl_ instanceof League && rankLeague + 1 == ((League) pl_).getRooms().size()) {
            Rewardable tr_ = ((League) pl_).getRooms().get(coordsFoe_.getLevel().getLevelIndex()).getTrainer();
            rankLeague++;
            Rate gainArgent_ = gainArgent(sommeNiveau_, tr_);
            player.winMoneyFight(gainArgent_.intPart());
            commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            Coords coords_ = new Coords();
            coords_.setNumberPlace(coordsFoe_.getNumberPlace());
            coords_.setLevel(new LevelPoint());
            coords_.getLevel().setLevelIndex((byte) 0);
            coords_.getLevel().setPoint(((League) pl_).getBegin());
            beatGymLeader.put(coords_, BoolVal.TRUE);
            addBeatenTrainer(coords_, _import);
            player.healTeamWithoutUsingObject(_import);
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        //FightFacade.equality(fight) or FightFacade.loose(fight)
        if (pl_ instanceof League) {
            Rewardable tr_ = ((League)pl_).getRooms().get(coordsFoe_.getLevel().getLevelIndex()).getTrainer();
            Rate gainArgent_ = gainArgent(sommeNiveau_, tr_);
            player.winMoneyFight(gainArgent_.intPart().opposNb());
            commentGame.addMessage(mess_.getVal(LOST_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            FightFacade.endFight(fight);
            //begin of league => just before league
            playerCoords.affect(((League)pl_).getAccessCoords());
            player.healTeamWithoutUsingObject(_import);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        if (pl_ instanceof City) {
            //playerCoords.isInside()
            Level l_ = pl_.getLevelByCoords(coordsFoe_);
            //l_ instanceof LevelIndoorGym
            LevelIndoorGym gym_ = (LevelIndoorGym) l_;
            Rewardable gymTr_ = gymTr(coordsFoe_, gym_);
            Rate gainArgent_ = gainArgent(sommeNiveau_, gymTr_);
            player.winMoneyFight(gainArgent_.intPart().opposNb());
            commentGame.addMessage(mess_.getVal(LOST_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            FightFacade.endFight(fight);
            playerCoords.affect(d_.getBegin());
            player.healTeamWithoutUsingObject(_import);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) pl_.getLevelByCoords(coordsFoe_);
        for (EntryCust<Point,DualFight> e: l_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (notInFrontOfDual(coordsFoe_, e, dual_)) {
                continue;
            }
            Rate gainArgent_ = gainArgent(sommeNiveau_, dual_);
            player.winMoneyFight(gainArgent_.intPart().opposNb());
            commentGame.addMessage(mess_.getVal(LOST_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            FightFacade.endFight(fight);
            //begin of the game
            playerCoords.affect(d_.getBegin());
            player.healTeamWithoutUsingObject(_import);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        TrainerMultiFights ch_ = l_.getTrainers().getVal(coordsFoe_.getLevel().getPoint());
        int nb_ = nbFights(coordsFoe_, ch_);
        Rewardable pokemonTeam_ = ch_.getTeamsRewards().get(nb_);
        Rate gainArgent_ = gainArgent(sommeNiveau_, pokemonTeam_);
        player.winMoneyFight(gainArgent_.intPart().opposNb());
        commentGame.addMessage(mess_.getVal(LOST_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
        FightFacade.endFight(fight);
        //begin of the game
        playerCoords.affect(d_.getBegin());
        player.healTeamWithoutUsingObject(_import);
        directInteraction(closestTile(_import.getMap()), _import.getMap());
    }

    private Rewardable gymTr(Coords _coordsFoe, LevelIndoorGym _gym) {
        Rewardable gymTr_;
        if(_gym.getGymTrainers().contains(_coordsFoe.getLevel().getPoint())) {
            gymTr_ = _gym.getGymTrainers().getVal(_coordsFoe.getLevel().getPoint());
        } else {
            gymTr_ = _gym.getGymLeader();
        }
        return gymTr_;
    }

    private void winFight(DataBase _import) {
        DataMap d_=_import.getMap();
        Coords coordsFoe_ = closestTile(d_);
        Place pl_ = d_.getPlace(coordsFoe_.getNumberPlace());
        int sommeNiveau_ = sommeNiveau();
        LgInt money_ = new LgInt(player.getMoney());
        StringMap<String> mess_ = _import.getMessagesGame();
        if (pl_ instanceof League) {
            Rewardable tr_ = ((League) pl_).getRooms().get(coordsFoe_.getLevel().getLevelIndex()).getTrainer();
            rankLeague++;
            Rate gainArgent_ = gainArgent(sommeNiveau_, tr_);
            player.winMoneyFight(gainArgent_.intPart());
            commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            int nbPlateaux_=((League) pl_).getRooms().size();
            Coords beatenImportantTrainer_ = new Coords();
            if(rankLeague==nbPlateaux_){
                Coords coords_ = new Coords();
                coords_.setNumberPlace(coordsFoe_.getNumberPlace());
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) 0);
                coords_.getLevel().setPoint(((League) pl_).getBegin());
                beatGymLeader.put(coords_, BoolVal.TRUE);
                beatenImportantTrainer_ = coords_;
                player.healTeamWithoutUsingObject(_import);
            }
            addBeatenTrainer(beatenImportantTrainer_, _import);
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        if (pl_ instanceof City) {
            //playerCoords.isInside()
            Level l_ = pl_.getLevelByCoords(coordsFoe_);
            //l_ instanceof LevelIndoorGym
            LevelIndoorGym gym_ = (LevelIndoorGym) l_;
            if(gym_.getGymTrainers().contains(coordsFoe_.getLevel().getPoint())) {
                Rewardable gymTr_ = gym_.getGymTrainers().getVal(coordsFoe_.getLevel().getPoint());
                Rate gainArgent_ = gainArgent(sommeNiveau_, gymTr_);
                player.winMoneyFight(gainArgent_.intPart());
                commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
                beatGymTrainer.getVal(coordsFoe_.getNumberPlace()).add(coordsFoe_.getLevel().getPoint());
                addPossibleBeatLeader(_import);
                FightFacade.endFight(fight);
                directInteraction(closestTile(_import.getMap()), _import.getMap());
                return;
            }
            //if(Point.eq(gym_.getGymLeaderCoords(),coordsFoe_.getLevel().getPoint()))
            Rewardable gymTr_ = gym_.getGymLeader();
            Rate gainArgent_ = gainArgent(sommeNiveau_, gymTr_);
            player.winMoneyFight(gainArgent_.intPart());
            commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            beatGymLeader.put(coordsFoe_, BoolVal.TRUE);
            addBeatenTrainer(coordsFoe_, _import);
            //player.obtentionCs(gymTr_.getCs());
            player.getTm(gym_.getGymLeader().getTm());
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        LevelWithWildPokemon l_ = (LevelWithWildPokemon) pl_.getLevelByCoords(coordsFoe_);
        for (EntryCust<Point,DualFight> e: l_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (notInFrontOfDual(coordsFoe_, e, dual_)) {
                continue;
            }
            Rate gainArgent_ = gainArgent(sommeNiveau_, dual_);
            player.winMoneyFight(gainArgent_.intPart());
            commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            player.healTeamWithoutUsingObject(_import);
            Coords key_ = new Coords(coordsFoe_);
            key_.getLevel().getPoint().affect(e.getKey());
            beatGymLeader.put(key_, BoolVal.TRUE);
            addBeatenTrainer(key_, _import);
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
            return;
        }
        TrainerMultiFights ch_ = l_.getTrainers().getVal(coordsFoe_.getLevel().getPoint());
        int nb_ = nbFights(coordsFoe_, ch_);
        Rewardable pokemonTeam_ = ch_.getTeamsRewards().get(nb_);
        Rate gainArgent_ = gainArgent(sommeNiveau_, pokemonTeam_);
        player.winMoneyFight(gainArgent_.intPart());
        commentGame.addMessage(mess_.getVal(WON_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
        beatTrainer.put(new NbFightCoords(coordsFoe_,nb_), BoolVal.TRUE);
        //player.obtentionCs(gymTr_.getCs());
        FightFacade.endFight(fight);
        directInteraction(closestTile(_import.getMap()), _import.getMap());
    }

    private void wildFight(DataBase _import) {
        StringMap<String> mess_ = _import.getMessagesGame();
        if (FightFacade.loose(fight)) {
            LgInt money_ = new LgInt(player.getMoney());
            player.winMoneyFight(new LgInt(-2000));
            commentGame.addMessage(mess_.getVal(LOST_MONEY), LgInt.minus(money_, player.getMoney()).absNb().toNumberString());
            player.healTeamWithoutUsingObject(_import);
        } else if (FightFacade.win(fight)) {
            player.affectEndFight(fight,difficulty, _import);
        } else {
            player.healTeamWithoutUsingObject(_import);
        }
        FightFacade.endFight(fight);
        directInteraction(closestTile(_import.getMap()), _import.getMap());
    }

    private boolean notInFrontOfDual(Coords _coordsFoe, EntryCust<Point,DualFight> _e, DualFight _dual) {
        return !Point.eq(_e.getKey(), _coordsFoe.getLevel().getPoint()) && !Point.eq(_dual.getPt(), _coordsFoe.getLevel().getPoint());
    }

    private int nbFights(Coords _coordsFoe, TrainerMultiFights _dr) {
        int nb_ = nbFights(_coordsFoe);
        if (!_dr.getTeamsRewards().isValidIndex(nb_)) {
            nb_ = _dr.getTeamsRewards().size() - 1;
        }
        return nb_;
    }

    private int nbFights(Coords _coordsFoe) {
        int nb_=0;
        for(NbFightCoords d:beatTrainer.getKeys()){
            if(Coords.eq(d.getCoords(), _coordsFoe)){
                if(beatTrainer.getVal(new NbFightCoords(d.getCoords(),nb_)) != BoolVal.TRUE){
                    break;
                }
                nb_++;
            }
        }
        return nb_;
    }

    private Rate gainArgent(int _sommeNiveau, DualFight _tr) {
        return gainArgent(_sommeNiveau,_tr.getFoeTrainer());
    }
    private Rate gainArgent(int _sommeNiveau, Rewardable _tr) {
        Rate gainArgent_=new Rate(fight.getWinningMoney());
        gainArgent_.addNb(new Rate(_sommeNiveau * _tr.getReward()*10L));
        gainArgent_.multiplyBy(difficulty.getRateWinMoneyBase());
        return gainArgent_;
    }

    private int sommeNiveau() {
        int sommeNiveau_ = 0;
        Team equipeAdv_=fight.getFoeTeam();
        for(byte c:equipeAdv_.getMembers().getKeys()){
            Fighter creature_=equipeAdv_.getMembers().getVal(c);
            sommeNiveau_ += creature_.getLevel();
        }
        return sommeNiveau_;
    }

    void addBeatenTrainer(Coords _coords, DataBase _import) {
        if (!_coords.isValid()) {
            return;
        }
        DataMap map_ = _import.getMap();
        Condition accessible_ = new Condition();
        Condition inaccessible_ = new Condition();
        Condition beaten_ = getBeatenGymLeader();
        for (Coords c: map_.getAccessibility().getKeys()) {
            if (!map_.getAccessibility().getVal(c).containsObj(_coords)) {
                continue;
            }
            if (beaten_.containsAllObj(map_.getAccessibility().getVal(c))) {
                accessible_.add(c);
            } else {
                inaccessible_.add(c);
            }
        }
        Shorts accessiblePlaces_ = new Shorts();
        for (Coords c: accessible_) {
            accessiblePlaces_.add(c.getNumberPlace());
        }
        accessiblePlaces_.removeDuplicates();
        Shorts inaccessiblePlaces_ = new Shorts();
        for (Coords c: inaccessible_) {
            inaccessiblePlaces_.add(c.getNumberPlace());
        }
        inaccessiblePlaces_.removeDuplicates();
        StringList partiallyAccessiblePlaces_ = new StringList();
        StringList fullAccessiblePlaces_ = new StringList();
        for (Short c: accessiblePlaces_) {
            Place pl_ = map_.getPlace(c);
            if (inaccessiblePlaces_.containsObj(c)) {
                partiallyAccessiblePlaces_.add(pl_.getName());
            } else {
                fullAccessiblePlaces_.add(pl_.getName());
            }
        }
        fullAccessiblePlaces_.sort();
        partiallyAccessiblePlaces_.sort();
        fullAccessiblePlaces = fullAccessiblePlaces_;
        partiallyAccessiblePlaces = partiallyAccessiblePlaces_;
        StringMap<String> mess_ = _import.getMessagesGame();
        if (!fullAccessiblePlaces_.isEmpty()) {
            commentGame.addMessage(mess_.getVal(BEATEN_TRAINER_FULL), StringUtil.join(fullAccessiblePlaces_, SEPARATOR_TRAINERS));
        }
        if (!partiallyAccessiblePlaces_.isEmpty()) {
            commentGame.addMessage(mess_.getVal(BEATEN_TRAINER), StringUtil.join(partiallyAccessiblePlaces_, SEPARATOR_TRAINERS));
        }
    }

    void addPossibleBeatLeader(DataBase _import) {
        Coords next_ = closestTile(_import.getMap());
        City city_ = (City) _import.getMap().getPlace(next_.getNumberPlace());
        Gym building_ = (Gym) city_.getBuildings().getVal(next_.getInsideBuilding());
        LevelIndoorGym lev_ = (LevelIndoorGym) city_.getLevelByCoords(next_);
        boolean canBeat_ = beatGymTrainer.getVal(next_.getNumberPlace()).containsAllObj(building_.getIndoor().getGymTrainers().getKeys());
        if (canBeat_) {
             StringMap<String> mess_ = _import.getMessagesGame();
            commentGame.addMessage(mess_.getVal(POSSIBLE_BEAT_LEADER), lev_.getGymLeader().getName());
        }
    }

    public void roundWhileKoPlayer(DataBase _import, boolean _enableAnimation) {
        commentGame.clearMessages();
        FightFacade.roundWhileKoPlayer(fight, difficulty, player, _import, _enableAnimation);
        commentGame.addComment(fight.getComment());
        if (!_enableAnimation && FightFacade.koTeam(fight)) {
            endFight(_import);
        }
    }

    public void endRoundFightKoUser(DataBase _import) {
        commentGame.clearMessages();
        if (fight.getTemp().isEndRoundFightKoPlayer()) {
            FightFacade.endRoundFightBasic(fight, difficulty, player, _import);
        }
        //fight.setState(FightState.SWITCH_WHILE_KO_USER);
        commentGame.addComment(fight.getComment());
        if(FightFacade.koTeam(fight)){
            endFight(_import);
        }
    }

    public void sendSubstitutes(DataBase _import){
        commentGame.clearMessages();
        FightFacade.sendSubstitutesChooseActions(fight,difficulty, player,_import);
        commentGame.addComment(fight.getComment());
        if (fight.getState() == FightState.APPRENDRE_EVOLUER) {
            return;
        }
        if(FightFacade.koTeam(fight)){
            endFight(_import);
        }
    }

    public void roundAllThrowers(DataBase _import, boolean _enableAnimation){
        //LUTTE est a proposer en ihm
        commentGame.clearMessages();
        FightFacade.regularRoundAllThrowersChooseActionsFoe(fight,difficulty,player,_import, _enableAnimation);
        commentGame.addComment(fight.getComment());
        if (!_enableAnimation) {
            for(String c:fight.getUsedItemsWhileRound().getKeys()){
                short quantite_=fight.getUsedItemsWhileRound().getVal(c);
                for(byte i = IndexConstants.FIRST_INDEX; i<quantite_; i++){
                    player.useInInventory(c);
                }
            }
            if (fight.getState() == FightState.APPRENDRE_EVOLUER) {
                return;
            }
            if (FightFacade.koTeam(fight)) {
                endFight(_import);
            }
        }
    }

    public void roundUser(DataBase _import) {
        commentGame.clearMessages();
        FightFacade.roundUser(fight, difficulty, _import);
        commentGame.addComment(fight.getComment());
    }

    public void endRoundFightBasic(DataBase _import) {
        commentGame.clearMessages();
        FightFacade.endRoundFightBasic(fight, difficulty, player, _import);
        commentGame.addComment(fight.getComment());
        for(String c:fight.getUsedItemsWhileRound().getKeys()){
            short quantite_=fight.getUsedItemsWhileRound().getVal(c);
            for(byte i = IndexConstants.FIRST_INDEX; i<quantite_; i++){
                player.useInInventory(c);
            }
        }
        if (fight.getState() == FightState.APPRENDRE_EVOLUER) {
            return;
        }
        if (FightFacade.koTeam(fight)) {
            endFight(_import);
        }
    }

    public CustList<FighterPosition> getPlayerTeam() {
        return FightFacade.getPlayerTeam(fight);
    }

    public ByteTreeMap<FighterPosition> getFoeFrontTeam() {
        return FightFacade.getFoeFrontTeam(fight);
    }

    public ByteTreeMap<FighterPosition> getUnionFrontTeam() {
        return FightFacade.getUnionFrontTeam(fight);
    }

    public CustList<FighterPosition> getPlayerToCatch() {
        return FightFacade.getPlayerToCatch(fight);
    }

    public FighterPosition getSinglePlayerToCatch(int _index) {
        return FightFacade.getSinglePlayerToCatch(fight,_index);
    }

    public CustList<FighterPosition> getFoeToBeCaught(boolean _caught) {
        return FightFacade.getFoeToBeCaught(fight, _caught);
    }

    public FighterPosition getSingleFoeToBeCaught(boolean _caught, int _index) {
        return FightFacade.getSingleFoeToBeCaught(fight, _caught, _index);
    }

    public CustList<FighterPosition> getFoeToBeCaught() {
        return FightFacade.getFoeToBeCaught(fight);
    }

    public FighterPosition getSingleFoeToBeCaught(int _index) {
        return FightFacade.getSingleFoeToBeCaught(fight, _index);
    }

    public CustList<FighterPosition> getPlayerFrontTeam() {
        return FightFacade.getPlayerFrontTeam(fight);
    }

    public CustList<FighterPosition> getPlayerBackTeam() {
        return FightFacade.getPlayerBackTeam(fight);
    }

//    public CustList<Fighter> getPlayerFrontTeamForSubstituting() {
//        return FightFacade.getPlayerFrontTeam(fight);
//    }

//    public CustList<Fighter> getPlayerBackTeamForSubstituting() {
//        return FightFacade.getPlayerBackTeam(fight);
//    }

    public boolean isChosableForLearningAndEvolving(byte _key) {
        return FightFacade.isChosableForLearningAndEvolving(fight, _key);
    }

    public void choosePokemonForLearningAndEvolving(byte _key, DataBase _d) {
        FightFacade.choosePokemonForLearningAndEvolving(fight, _key, _d);
    }

    public byte getChosenIndex() {
        return fight.getTemp().getChosenIndex();
    }

    public NatStringTreeMap<BoolVal> getMoves() {
        return fight.getTemp().getMoves();
    }

    public EvolutionChoiceMap getEvolutions() {
        return fight.getTemp().getEvolutions();
    }

    public StringList getAbilities() {
        return fight.getTemp().getAbilities();
    }

    public String getAbility() {
        return fight.getTemp().getAbility();
    }

    public void addOrForgetMove(String _move) {
        FightFacade.addOrForgetMove(fight,_move);
    }

    public void setAbility(String _ability) {
        FightFacade.setAbility(fight,_ability);
    }

    public void setEvolution(String _evo) {
        FightFacade.setEvolution(fight,_evo);
    }

    public void learnAndEvolve(DataBase _import) {
        commentGame.clearMessages();
        if (FightFacade.possibleChoices(fight, _import)) {
            FightFacade.learnAndEvolveAttack(fight, difficulty, _import);
            if (FightFacade.koTeam(fight)) {
                endFight(_import);
            }
        }
        commentGame.addComment(fight.getComment());
    }

    public void attemptFlee(DataBase _import, boolean _enableAnimation){
        FightFacade.attemptFlee(fight,difficulty,player,_import, _enableAnimation);
        if (!_enableAnimation) {
            flee(_import);
        }
    }

    public void endRoundFightFlee(DataBase _import) {
        if(fight.getState()!=FightState.REDESSIN_SCENE){
            endRoundFightBasic(_import);
        }
        flee(_import);
    }

    private void flee(DataBase _import) {
        if(fight.getState()==FightState.REDESSIN_SCENE){
            player.affectEndFight(fight,difficulty,_import);
            takeFighters(_import);
            FightFacade.endFight(fight);
            directInteraction(closestTile(_import.getMap()), _import.getMap());
        } else if (FightFacade.koTeam(fight)) {
            endFight(_import);
        }
    }

    public IdMap<UsefulValueLaw,Rate> calculateFleeingRate(DataBase _import) {
        IdMap<UsefulValueLaw,Rate> fuite_=new IdMap<UsefulValueLaw,Rate>();
        MonteCarloNumber mcn_ = FightFacade.calculateFleeingRate(fight, difficulty, _import);
        fuite_.addEntry(UsefulValueLaw.MINI, mcn_.minimum());
        fuite_.addEntry(UsefulValueLaw.MAXI, mcn_.maximum());
        fuite_.addEntry(UsefulValueLaw.MOY, mcn_.getAvg());
        fuite_.addEntry(UsefulValueLaw.VAR, mcn_.getVar());
        return fuite_;
    }

    public boolean enoughBall(DataBase _import){
        return FightFacade.enoughBall(fight, player, _import);
    }
    public IntMap<BallNumberRatePk> calculateCatchingRatesSum(DataBase _import){
        return FightFacade.calculateCatchingRatesSum(fight,player,difficulty,_import);
    }
    public IntMap<CatchingBallFoeAction> attempted(){
        return FightFacade.attempted(fight);
    }
    public IntMap<CatchingBallFoeAction> swallow(IntMap<CatchingBallFoeAction> _att){
        return FightFacade.swallow(fight, _att);
    }
    public void attemptCatchingWildPokemon(DataBase _import, boolean _enableAnimation){
        FightFacade.attemptCatching(fight, difficulty,player,_import, _enableAnimation);
//        player.useInInventory(_ball);
//        StringMap<String> mess_ = _import.getMessagesGame();
        if (!_enableAnimation) {
            endRoundFightSuccessBall(_import);
        }
    }

    public void endRoundFightBall(DataBase _import) {
        endRoundFightBasic(_import);
        endRoundFightSuccessBall(_import);
    }

    public void endRoundFightSuccessBall(DataBase _import) {
        StringMap<String> mess_ = _import.getMessagesGame();
        if (FightFacade.possibleCatch(fight)) {
            commentGame.addMessage(mess_.getVal(CAUGHT_PK));
        } else if (FightFacade.koTeam(fight)) {
            endFight(_import);
        } else {
            commentGame.addMessage(mess_.getVal(NOT_CAUGHT_PK));
        }
    }

    public void notCatchKoWildPokemon(DataBase _import){
        player.affectEndFight(fight,difficulty,_import);
        FightFacade.endFight(fight);
        directInteraction(closestTile(_import.getMap()), _import.getMap());
    }

    public void catchKoWildPokemon(DataBase _import){
        CustList<CatchingBallFoeAction> ls_ = fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int f = 0; f < s_; f++) {
            String catchingBall_ = ls_.get(f).getCatchingBall();
            if (FightFacade.candidate(ls_.get(f))) {
                player.useInInventory(catchingBall_);
            }
        }
        catchWildPokemon(_import);
    }

    public void catchWildPokemon(DataBase _import){
        player.affectEndFight(fight,difficulty,_import);
        takeFighters(_import);
        FightFacade.endFight(fight);
        directInteraction(closestTile(_import.getMap()), _import.getMap());
    }

    private void takeFighters(DataBase _import) {
        CustList<CatchingBallFoeAction> ls_ = fight.getCatchingBalls();
        int s_ = ls_.size();
        for (int f = 0; f < s_; f++) {
            String catchingBall_ = ls_.get(f).getCatchingBall();
            Fighter fighter_ = fight.getFighter(Fight.toFoeFighter((byte) f));
            String pseudo_ = getNicknameOrDefault(ls_.get(f).getNickname(), _import, fighter_);
            if (!StringUtil.quickEq(catchingBall_,DataBase.EMPTY_STRING)) {
                player.catchWildPokemon(fighter_,pseudo_, catchingBall_,difficulty, _import, ls_.get(f).isTeam());
                PokemonData fPk_ = _import.getPokemon(fighter_.getName());
                if (fPk_.getGenderRep() == GenderRepartition.LEGENDARY && nextLegPk(_import)) {
                    Coords n_ = closestTile(_import.getMap());
                    takenPokemon.put(n_, BoolVal.TRUE);
                }
            }
        }
    }

    public String getNicknameOrDefault(String _pseudo, DataBase _import, Fighter _fighter) {
        String pseudo_ = _pseudo;
        if (pseudo_.isEmpty()) {
            pseudo_ = _import.translatePokemon(_fighter
                    .getName());
        }
        return pseudo_;
    }

    boolean nextLegPk(DataBase _import) {
        Coords n_ = closestTile(_import.getMap());
        if (!n_.isValid()) {
            return false;
        }
        Level level_ = _import.getMap().getLevelByCoords(n_);
        Point pt_ = n_.getLevel().getPoint();
        if (level_ instanceof LevelWithWildPokemon) {
            return ((LevelWithWildPokemon) level_).containsPokemon(pt_);
        }
        return false;
    }

    void catchAll(DataBase _d) {
        for(String p:_d.getPokedex().getKeys()){
            player.getCaughtPk().put(p, BoolVal.TRUE);
        }
    }

    boolean endGame(DataBase _d){
        boolean attrape_=true;
        for(String p:_d.getPokedex().getKeys()){
            if (!player.estAttrape(p)) {
                attrape_ = false;
            }
        }
        if (!attrape_ || !getUnBeatenGymLeader().isEmpty()) {
            return false;
        }
        for (NbFightCoords k: beatTrainer.getKeys()) {
            Coords coords_ = k.getCoords();
            Campaign place_ = (Campaign) _d.getMap().getPlace(coords_.getNumberPlace());
            LevelWithWildPokemon level_ = place_.getLevelCompaignByCoords(coords_);
            TrainerMultiFights trainer_ = level_.getTrainers().getVal(coords_.getLevel().getPoint());
            if (k.getNbFight() == trainer_.getTeamsRewards().getLastIndex() && beatTrainer.getVal(k) != BoolVal.TRUE) {
                return false;
            }
        }
        if (!getUnVisited().isEmpty() || !player.getEggsList().isEmpty()) {
            return false;
        }
        return allPkHappyMaxLevel(_d);
    }

    private boolean allPkHappyMaxLevel(DataBase _d) {
        CustList<PokemonPlayer> pks_ = new CustList<PokemonPlayer>();
        pks_.addAllElts(player.getPokemonPlayerList().values());
        for (UsablePokemon u: player.getBox()) {
            if (u instanceof Egg) {
                return false;
            }
            PokemonPlayer pk_ = (PokemonPlayer) u;
            pks_.add(pk_);
        }
        for (PokemonPlayer p: pks_) {
            if (p.getLevel() < _d.getMaxLevel() || p.getHappiness() < _d.getHappinessMax()) {
                return false;
            }
        }
        return true;
    }


    public void simuler(FightSimulationActions _fightSimulationActions,
            int _index,
            DataBase _import){
        //_evolutions for each round: key position before fight, value choices
        FightFacade.simulate(fight,_fightSimulationActions,_index,
                player,difficulty,_import);
    }

    public void healTeamWithoutUsingObject(DataBase _import){
        player.healTeamWithoutUsingObject(_import);
    }

    public void moving(Direction _direction,DataBase _d){
        commentGame.clearMessages();
        placeChanged = false;
        incrementStepsToLayEggs(_d);
        DataMap d_=_d.getMap();
        nbSteps = 0;
        if(_direction != playerOrientation){
            playerOrientation = _direction;
            directInteraction(d_);
            return;
        }
        movingHero(_d);
        if (visitedPlaces.contains(playerCoords)) {
            visitedPlaces.put(playerCoords, BoolVal.TRUE);
            visitedPlacesNb.put(playerCoords.getNumberPlace(),BoolVal.TRUE);
        }
        Coords voisin_ = closestTile(d_);
        player.moveLoop(nbSteps, difficulty, _d);
        commentGame.addComment(player.getCommentGame());
        if (nbSteps > 0) {
            processWalkingAreaApparition(voisin_, _d);
        }
        //show by an other kind the end of game
        //rather than modal dialog box
        showEndGame = !fight.getFightType().isExisting() && endGame(_d);
    }

    void directInteraction(DataMap _d) {
        Coords voisin_ = closestTile(_d);
        if (voisin_.isValid()) {
            directInteraction(voisin_, _d);
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }

    void processWalkingAreaApparition(Coords _coords, DataBase _d) {
        DataMap d_ = _d.getMap();
        if (player.getRepousseActif()) {
            directInteractionValid(_coords, d_);
            return;
        }
        attract(_coords, _d);
    }
    public void attract(DataBase _d) {
        Coords voisin_ = closestTile(_d.getMap());
        attract(voisin_,_d);
    }

    private void attract(Coords _coords, DataBase _d) {
        DataMap d_ = _d.getMap();
        AbsAreaApparition area_ = d_.getAreaByCoords(playerCoords);
        if (area_.isVirtual()) {
            directInteractionValid(_coords, d_);
            return;
        }
        if(!difficulty.getRandomWildFight()){
            incrementPeriod(area_, _d);
        } else {
            indexPeriod=0;
            indexStep=0;
            newRandomPokemon(area_.getWildPokemonRand(), _d);
        }
        if (!fight.getFightType().isExisting()) {
            directInteractionValid(_coords, d_);
        }
    }

    private void directInteractionValid(Coords _coords, DataMap _d) {
        if (_coords.isValid()) {
            directInteraction(_coords, _d);
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }
    void incrementPeriod(AbsAreaApparition _area, DataBase _d) {
        indexStep++;
        if(indexStep >= _area.getAvgNbSteps()){
            newIndex(true, indexPeriod, _area, _d);
            indexStep=0;
        }
    }

    void movingHero(DataBase _db) {
        DataMap map_ = _db.getMap();
        Coords voisin_= closestTile(map_);
        if(!voisin_.isValid()){
            return;
        }
        Place nextPl_ = map_.getPlace(voisin_.getNumberPlace());
        if (nextPl_ instanceof League) {
            movingHeroLeague(map_, voisin_, (League) nextPl_);
            return;
        }
        if(map_.getAccessCondition().contains(voisin_)){
            Condition leaders_ = getBeatenGymLeader();
            if (!leaders_.containsAllObj(map_.getAccessCondition().getVal(voisin_))) {
                noBeatenTrainers(_db, voisin_, leaders_);
                return;
            }
        }
        int nbPlaces_ = map_.getPlaces().size();
        for (short p = IndexConstants.FIRST_INDEX; p<nbPlaces_; p++) {
            Place place_ = map_.getPlace(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            Coords coords_ = ((League)place_).getAccessCoords();
            if (Coords.eq(coords_, voisin_)) {
                rankLeague = 0;
                nbSteps++;
                playerCoords.setNumberPlace(p);
                playerCoords.getLevel().setLevelIndex((byte) 0);
                playerCoords.getLevel().getPoint().affect(((League)place_).getBegin());
                placeChanged = true;
                return;
            }
        }
        movingHeroOtherThanLeague(map_, voisin_, nextPl_);
    }

    private void movingHeroOtherThanLeague(DataMap _map, Coords _voisin, Place _nextPl) {
        Point nextPt_ = _voisin.getLevel().getPoint();
        if(_nextPl instanceof City && !playerCoords.isInside()) {
            Points<Building> buildings_ = ((City) _nextPl).getBuildings();
            if (buildings_.contains(nextPt_)) {
                nbSteps++;
                playerCoords.getLevel().getPoint().affect(buildings_.getVal(nextPt_).getExitCity());
                playerCoords.affectInside(nextPt_);
                playerCoords.getLevel().setLevelIndex((byte) 0);
                placeChanged = true;
                return;
            }
        }
        if (_nextPl instanceof Cave && ((Cave) _nextPl).getLinksWithOtherPlaces().contains(_voisin.getLevel())) {
            nbSteps++;
            playerCoords.affect(((Cave) _nextPl).getLinksWithOtherPlaces().getVal(_voisin.getLevel()).getCoords());
            placeChanged = true;
            return;
        }
        if (_nextPl instanceof InitializedPlace && ((InitializedPlace) _nextPl).getLinksWithCaves().contains(nextPt_)) {
            Link to_ = ((InitializedPlace) _nextPl).getLinksWithCaves().getVal(nextPt_);
            nbSteps++;
            playerCoords.affect(to_.getCoords());
            placeChanged = true;
            return;
        }
        if (_map.currentBlock(_voisin).getType() == EnvironmentType.NOTHING) {
            return;
        }
        if (_nextPl instanceof City && playerCoords.isInside()) {
            movingHeroBuilding(_map, _voisin, (City) _nextPl, nextPt_);
            return;
        }
        if(!(_nextPl instanceof Cave)) {
            if (!isEmpty(_map, _voisin)) {
                return;
            }
            nbSteps++;
            playerCoords.affect(_voisin);
            return;
        }
        //nextPl_ instanceof Cave
        movingHeroCave(_map, _voisin, (Cave) _nextPl, nextPt_);
    }

    private void movingHeroBuilding(DataMap _map, Coords _voisin, City _nextPl, Point _nextPt) {
        Point inside_ = playerCoords.getInsideBuilding();
        Building building_ = _nextPl.getBuildings().getVal(inside_);
        if (Point.eq(building_.getExitCity(), _nextPt)) {
            inside_.moveTo(Direction.DOWN);
            //not on the door of the building
            nbSteps++;
            playerCoords.getLevel().getPoint().affect(inside_);
            playerCoords.getLevel().setLevelIndex((byte) 0);
            playerCoords.outside();
            placeChanged = true;
            return;
        }
        if (!isEmpty(_map, _voisin)) {
            return;
        }
        nbSteps++;
        playerCoords.getLevel().getPoint().affect(_nextPt);
    }

    private void movingHeroCave(DataMap _map, Coords _voisin, Cave _nextPl, Point _nextPt) {
        LevelPoint lPoint_ = _voisin.getLevel();
        LevelCave levelCave_ = (LevelCave) _nextPl.getLevelsMap().getVal(lPoint_.getLevelIndex());
        if (levelCave_.getLinksOtherLevels().contains(lPoint_.getPoint())) {
            nbSteps++;
            playerCoords.affect(levelCave_.getLinksOtherLevels().getVal(lPoint_.getPoint()).getCoords());
            placeChanged = true;
            return;
        }
        if (!isEmpty(_map, _voisin)) {
            return;
        }
        nbSteps++;
        playerCoords.getLevel().getPoint().affect(_nextPt);
    }

    private void noBeatenTrainers(DataBase _db, Coords _voisin, Condition _leaders) {
        StringMap<String> mess_ = _db.getMessagesGame();
        DataMap map_ = _db.getMap();
        Condition noBeaten_ = new Condition(map_.getAccessCondition().getVal(_voisin));
        noBeaten_.removeAllElements(_leaders);
        for (Coords c: noBeaten_) {
            Place pl_ = map_.getPlace(c.getNumberPlace());
            String name_ = map_.getTrainerNameBeat(c);
            commentGame.addMessage(mess_.getVal(NO_BEATEN_TRAINER), name_, pl_.getName());
        }
    }

    private void movingHeroLeague(DataMap _map, Coords _voisin, League _nextPl) {
        if (!isEmpty(_map, _voisin)) {
            return;
        }
        LevelLeague nextLevel_ = _nextPl.getRooms().get(_voisin.getLevel().getLevelIndex());
        if (Point.eq(nextLevel_.getAccessPoint(), _voisin.getLevel().getPoint()) && rankLeague > _voisin.getLevel().getLevelIndex()) {
            placeChanged = true;
            nbSteps++;
            if (rankLeague == _nextPl.getRooms().size()) {
                playerCoords.affect(_map.getBegin());
                rankLeague = 0;
                return;
            }
            playerCoords.getLevel().setLevelIndex((byte) (playerCoords.getLevel().getLevelIndex() + 1));
            playerCoords.getLevel().getPoint().affect(nextLevel_.getNextLevelTarget());
            return;
        }
        nbSteps++;
        playerCoords.affect(_voisin);
    }

    public void directInteraction(Coords _voisin, DataMap _map) {
        if (!_voisin.isValid()) {
            interfaceType=InterfaceType.RIEN;
            return;
        }
        Place pl_ = _map.getPlace(_voisin.getNumberPlace());
        Point pt_ = _voisin.getLevel().getPoint();
        if (pl_ instanceof League) {
            league(_voisin, (League) pl_);
            return;
        }
        if (pl_ instanceof City) {
            if (!_voisin.isInside()) {
                interfaceType=InterfaceType.RIEN;
                return;
            }
            Building building_ = ((City)pl_).getBuildings().getVal(_voisin.getInsideBuilding());
            if (building_ instanceof Gym) {
                gym(_voisin, (Gym) building_);
                return;
            }
            pkCenter(_voisin, (PokemonCenter) building_);
            return;
        }
        //pl_ instanceof Campaign
        Campaign campaign_ = (Campaign) pl_;
        LevelWithWildPokemon level_ = (LevelWithWildPokemon) campaign_.getLevelsMap().getVal(_voisin.getLevel().getLevelIndex());
        if (level_.getCharacters().contains(_voisin.getLevel().getPoint())) {
            character(_voisin, level_);
            return;
        }
        for (EntryCust<Point,DualFight> e: level_.getDualFights().entryList()) {
            DualFight dual_ = e.getValue();
            if (Point.eq(e.getKey(), pt_) || Point.eq(dual_.getPt(), pt_)) {
                Coords coords_ = new Coords(_voisin);
                coords_.getLevel().getPoint().affect(e.getKey());
                if (beatGymLeader.getVal(coords_) != BoolVal.TRUE) {
                    interfaceType = InterfaceType.DRESSEUR;
                    return;
                }
                break;
            }
        }
        itemPkLegFish(_voisin, level_);
    }

    private void itemPkLegFish(Coords _voisin, LevelWithWildPokemon _level) {
        if (_level.getTm().contains(_voisin.getLevel().getPoint()) || _level.getHm().contains(_voisin.getLevel().getPoint()) || _level.getItems().contains(_voisin.getLevel().getPoint())) {
            if (takenObjects.getVal(_voisin) == BoolVal.TRUE) {
                interfaceType = InterfaceType.RIEN;
            } else {
                interfaceType = InterfaceType.OBJ_RAMAS;
            }
            return;
        }
        if (_level.containsPokemon(_voisin.getLevel().getPoint())) {
            WildPk pk_ = _level.getPokemon(_voisin.getLevel().getPoint());
            if (!player.estAttrape(pk_.getName())) {
                interfaceType=InterfaceType.PK_LEG;
            } else {
                interfaceType=InterfaceType.RIEN;
            }
            return;
        }
        Block bl_ = _level.getBlockByPoint(_voisin.getLevel().getPoint());
        if (bl_.isValid() && bl_.getType() == EnvironmentType.WATER) {
            interfaceType=InterfaceType.PECHE;
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }

    private void character(Coords _voisin, LevelWithWildPokemon _level) {
        CharacterInRoadCave char_ = _level.getCharacters().getVal(_voisin.getLevel().getPoint());
        if (char_ instanceof Trainer) {
            interfaceType=InterfaceType.DRESSEUR;
            return;
        }
        //char_ instanceof DealerItem
        if (takenObjects.getVal(_voisin) != BoolVal.TRUE) {
            interfaceType=InterfaceType.DON_OBJET;
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }

    private void pkCenter(Coords _voisin, PokemonCenter _building) {
        Points<Person> gerants_ = _building.getIndoor().getGerants();
        if (!gerants_.contains(_voisin.getLevel().getPoint())) {
            if (Point.eq(_building.getIndoor().getStorageCoords(), _voisin.getLevel().getPoint())) {
                interfaceType = InterfaceType.ECH_BOITE;
                return;
            }
            interfaceType = InterfaceType.RIEN;
            return;
        }
        Person gear_ = gerants_.getVal(_voisin.getLevel().getPoint());
        if (gear_ instanceof Seller) {
            SellType sell_ = ((Seller) gear_).getSell();
            interfaceType = InterfaceType.RIEN;
            if (sell_ == SellType.TM) {
                interfaceType = InterfaceType.ACHATS_CT;
            }
            if (sell_ == SellType.ITEM) {
                interfaceType = InterfaceType.ACHATS;
            }
            if (sell_ == SellType.MOVE) {
                interfaceType = InterfaceType.MOVE_TUTORS;
            }
            return;
        }
        GeranceType gerance_ = ((GerantPokemon) gear_).getGerance();
        interfaceType = InterfaceType.RIEN;
        if (gerance_ == GeranceType.FOSSILE) {
            interfaceType = InterfaceType.FOSSILE;
        }
        if (gerance_ == GeranceType.HEAL) {
            interfaceType = InterfaceType.SOIN_PK;
        }
        if (gerance_ == GeranceType.HOST) {
            interfaceType = InterfaceType.PENSION;
        }
    }

    private void gym(Coords _voisin, Gym _building) {
        if (_building.getIndoor().getGymTrainers().contains(_voisin.getLevel().getPoint())) {
            if (!beatGymTrainer.getVal(_voisin.getNumberPlace()).containsObj(_voisin.getLevel().getPoint())) {
                interfaceType=InterfaceType.DRESSEUR;
                return;
            }
            interfaceType=InterfaceType.RIEN;
            return;
        }
        if (Point.eq(_building.getIndoor().getGymLeaderCoords(), _voisin.getLevel().getPoint())) {
            if (beatGymTrainer.getVal(_voisin.getNumberPlace()).containsAllObj(_building.getIndoor().getGymTrainers().getKeys())) {
                if (beatGymLeader.getVal(_voisin) != BoolVal.TRUE) {
                    interfaceType=InterfaceType.DRESSEUR;
                } else {
                    interfaceType=InterfaceType.RIEN;
                }
            } else {
                interfaceType=InterfaceType.GYM_LEADER;
            }
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }

    private void league(Coords _voisin, League _pl) {
        LevelLeague level_ = _pl.getRooms().get(_voisin.getLevel().getLevelIndex());
        if (Point.eq(level_.getTrainerCoords(), _voisin.getLevel().getPoint())) {
            if (NumberUtil.eq(rankLeague, _voisin.getLevel().getLevelIndex())){
                interfaceType=InterfaceType.DRESSEUR;
            } else {
                interfaceType=InterfaceType.PERSONNAGE;
            }
            return;
        }
        interfaceType=InterfaceType.RIEN;
    }

    public void addMessageGymLeader(DataBase _import) {
        commentGame.clearMessages();
        StringMap<String> mess_ = _import.getMessagesGame();
        commentGame.addMessage(mess_.getVal(REMAINING_TRAINERS_GYM));
    }

    public void clearMessages() {
        commentGame.clearMessages();
    }

    public void newIndex(boolean _walking, int _index, AbsAreaApparition _area, DataBase _d) {
        //CustList<WildPokemon> _pokemon,
//        int nb_=_pokemon.size();
        int nb_= _area.getPokemonListLength(_walking);
        //nb_ > 0
        int tmpIndexPeriod_ = _index;
        if (tmpIndexPeriod_ >= nb_) {
            tmpIndexPeriod_ = nb_ - 1;
        }
        //tmpIndexPeriod_ >= 0
        int i_ = tmpIndexPeriod_;
        //i_ >= 0
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
//            WildPokemon pk_ = _pokemon.get((i + i_) % nb_);
            CustList<WildPk> pk_ = _area.getWildPokemon((i + i_) % nb_, _walking);
            CustList<WildPk> possible_ = filter(pk_, _d);
            if (!possible_.isEmpty()) {
                initFight(_d, possible_);
                interfaceType = InterfaceType.COMBAT_PK_SAUV;
                break;
            }
        }
        if (_walking) {
            indexPeriod = (i_ + 1) % nb_;
        } else {
            indexPeriodFishing = (i_ + 1) % nb_;
        }
    }
    CustList<WildPk> filter(CustList<WildPk> _ls, DataBase _d) {
        CustList<WildPk> wp_ = new CustList<WildPk>();
        for (WildPk w: _ls) {
            boolean already_ = false;
            if (_d.getPokedex().getVal(w.getName()).getGenderRep() == GenderRepartition.LEGENDARY) {
                for (WildPk a: wp_) {
                    if (StringUtil.quickEq(a.getName(), w.getName())) {
                        already_ = true;
                        break;
                    }
                }
            }
            if (!already_ && toBeCaught(_d, w)) {
                wp_.add(w);
            }
        }
        return wp_;
    }

    private boolean toBeCaught(DataBase _d, WildPk _w) {
        return !player.estAttrape(_w.getName()) || _d.getPokedex().getVal(_w.getName()).getGenderRep() != GenderRepartition.LEGENDARY;
    }

    private void initFight(DataBase _d, WildPk _pk) {
        FightFacade.initFight(fight, player, difficulty, _pk, _d);
        FightFacade.initTypeEnv(fight, playerCoords, difficulty, _d);
        commentGame.addComment(fight.getComment());
    }

    private void initFight(DataBase _d, CustList<WildPk> _pk) {
        FightFacade.initFight(fight, player, difficulty, _pk, _d);
        FightFacade.initTypeEnv(fight, playerCoords, difficulty, _d);
        commentGame.addComment(fight.getComment());
    }

    void newRandomPokemon(MonteCarloWilPkList _law, DataBase _d) {
        MonteCarloWilPkList lawCopy_ = lawCopy(_law, _d);
        LgInt maxRd_ = _d.getMaxRd();
        CustList<WildPk> pkAlea_=lawCopy_.editNumber(maxRd_,_d.getGenerator());
        if(pkAlea_.isEmpty()){
            return;
        }
        initFight(_d, pkAlea_);
        interfaceType=InterfaceType.COMBAT_PK_SAUV;
    }


    MonteCarloWilPkList lawCopy(MonteCarloWilPkList _law, DataBase _d) {
        MonteCarloWilPkList lawCopy_ = new MonteCarloWilPkList();
        for(EventFreq<CustList<WildPk>> i:_law.getEvents()){
            CustList<WildPk> e = i.getEvent();
            LgInt f_ = i.getFreq();
            lawCopy_.addQuickEvent(filter(e,_d), f_);
        }
        return lawCopy_;
    }

    public boolean isEmpty(DataMap _map, Coords _coords) {
        Level level_ = _map.getLevelByCoords(_coords);
        Point pt_ = _coords.getLevel().getPoint();
        if (!(level_ instanceof LevelWithWildPokemon)) {
            return level_.isEmpty(pt_);
        }
        LevelWithWildPokemon levelWildPk_ = (LevelWithWildPokemon) level_;
        if (levelWildPk_.containsPokemon(pt_)) {
            WildPk pk_ = levelWildPk_.getPokemon(pt_);
            return player.estAttrape(pk_.getName());
        }
        if (levelWildPk_.getItems().contains(pt_)) {
            return takenObjects.getVal(_coords) == BoolVal.TRUE;
        }
        if (levelWildPk_.getTm().contains(pt_)) {
            return takenObjects.getVal(_coords) == BoolVal.TRUE;
        }
        if (levelWildPk_.getHm().contains(pt_)) {
            return takenObjects.getVal(_coords) == BoolVal.TRUE;
        }
        for (EntryCust<Point,DualFight> e: levelWildPk_.getDualFights().entryList()) {
            if (Point.eq(e.getKey(), pt_)) {
                return beatGymLeader.getVal(_coords) == BoolVal.TRUE;
            }
            if (Point.eq(e.getValue().getPt(), pt_)) {
                Coords coords_ = new Coords(_coords);
                coords_.getLevel().setPoint(e.getKey());
                return beatGymLeader.getVal(coords_) == BoolVal.TRUE;
            }
        }
        return level_.isEmpty(pt_);
    }

    public void initIv(DataBase _data) {
        commentGame = new Comment();
        reinitInteraction = false;
        nbSteps = 0;
        placeChanged = false;
        showEndGame = false;
        getPlayer().initIv(getDifficulty(), _data);
        commentGame.addComment(getPlayer().getCommentGame());
        for (HostPokemonDuo h: hostedPk.values()) {
            if (h.isFree()) {
                continue;
            }
            h.getFirstPokemon().initIv(getDifficulty());
            h.getFirstPokemon().initHp(_data);
            h.getSecondPokemon().initIv(getDifficulty());
            h.getSecondPokemon().initHp(_data);
        }
    }

    public boolean checkAndInitialize(DataBase _data, SexListInt _sexList) {
        initIv(_data);
        FightFacade.initializeFromSavedGame(fight, difficulty, player, _data);
        if (zippedRom == null) {
            zippedRom = DataBase.EMPTY_STRING;
        }
        if (!validate(_data,_sexList)) {
            return false;
        }
        DataMap d_ = _data.getMap();
        interfaceType = InterfaceType.RIEN;
        directInteraction(d_);
        showEndGame = !getFight().getFightType().isExisting() && endGame(_data);
        return true;
    }

    public Coords closestTile(DataMap _map) {
        return _map.closestTile(playerCoords, playerOrientation);
    }

    public void getItem(String _item) {
        player.getItem(_item);
    }

    public CustList<UsablePokemon> getTeam() {
        return player.getTeam();
    }

    public void doRevivingFossil(String _fossilName,DataBase _import){
        player.doRevivingFossil(_fossilName, difficulty, _import);
    }
    public Condition getBeatenGymLeader() {
        Condition k_ = new Condition();
        for (EntryCust<Coords, BoolVal> e: beatGymLeader.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    public Condition getUnBeatenGymLeader() {
        Condition k_ = new Condition();
        for (EntryCust<Coords, BoolVal> e: beatGymLeader.entryList()) {
            if (e.getValue() != BoolVal.TRUE) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    public void beatGymLeader(Coords _coords) {
        beatGymLeader.set(_coords, BoolVal.TRUE);
    }

    public void beatTrainer(NbFightCoords _coords) {
        beatTrainer.set(_coords, BoolVal.TRUE);
    }

    void takenObjects(Coords _coords) {
        takenObjects.set(_coords, BoolVal.TRUE);
    }

    void takenPokemon(Coords _coords) {
        takenPokemon.set(_coords, BoolVal.TRUE);
    }

    public Condition getVisited() {
        Condition k_ = new Condition();
        for (EntryCust<Coords, BoolVal> e: visitedPlaces.entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    public Condition getUnVisited() {
        Condition k_ = new Condition();
        for (EntryCust<Coords, BoolVal> e: visitedPlaces.entryList()) {
            if (e.getValue() != BoolVal.TRUE) {
                k_.add(e.getKey());
            }
        }
        return k_;
    }

    void visitPlace(Coords _coords) {
        visitedPlaces.set(_coords, BoolVal.TRUE);
        visitedPlacesNb.set(_coords.getNumberPlace(), BoolVal.TRUE);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player _player) {
        player = _player;
    }

    public byte getRankLeague() {
        return rankLeague;
    }

    public void setRankLeague(byte _rankLeague) {
        rankLeague = _rankLeague;
    }

    public CoordssBoolVal getBeatGymLeader() {
        return beatGymLeader;
    }

    public void setBeatGymLeader(CoordssBoolVal _beatGymLeader) {
        beatGymLeader = _beatGymLeader;
    }

    public CoordssBoolVal getTakenObjects() {
        return takenObjects;
    }

    public void setTakenObjects(CoordssBoolVal _takenObjects) {
        takenObjects = _takenObjects;
    }

    public CoordssBoolVal getTakenPokemon() {
        return takenPokemon;
    }

    public void setTakenPokemon(CoordssBoolVal _takenPokemon) {
        takenPokemon = _takenPokemon;
    }

    public Coords getPlayerCoords() {
        return playerCoords;
    }

    public void setPlayerCoords(Coords _playerCoords) {
        playerCoords = _playerCoords;
    }

    public CoordssHostPokemonDuo getHostedPk() {
        return hostedPk;
    }

    public void setHostedPk(CoordssHostPokemonDuo _hostedPk) {
        hostedPk = _hostedPk;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight _fight) {
        fight = _fight;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty _difficulty) {
        difficulty = _difficulty;
    }

    public int getIndexPeriodFishing() {
        return indexPeriodFishing;
    }

    public void setIndexPeriodFishing(int _indexPeriodFishing) {
        indexPeriodFishing = _indexPeriodFishing;
    }

    public int getIndexPeriod() {
        return indexPeriod;
    }

    public void setIndexPeriod(int _indexPeriod) {
        indexPeriod = _indexPeriod;
    }

    public int getIndexStep() {
        return indexStep;
    }

    public void setIndexStep(int _indexStep) {
        indexStep = _indexStep;
    }

    public Comment getCommentGame() {
        return commentGame;
    }

    public boolean isReinitInteraction() {
        return reinitInteraction;
    }

    public CoordssBoolVal getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(CoordssBoolVal _visitedPlaces) {
        visitedPlaces = _visitedPlaces;
    }

    public ShortMap<BoolVal> getVisitedPlacesNb() {
        return visitedPlacesNb;
    }

    public void setVisitedPlacesNb(ShortMap<BoolVal> _visitedPlacesNb) {
        visitedPlacesNb = _visitedPlacesNb;
    }

    public int getNbSteps() {
        return nbSteps;
    }

    public boolean isPlaceChanged() {
        return placeChanged;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType _interfaceType) {
        interfaceType = _interfaceType;
    }

    public void setBeatGymTrainer(ShortMap<PointEqList> _beatGymTrainer) {
        beatGymTrainer = _beatGymTrainer;
    }

    public void setBeatTrainer(NbFightCoordss _beatTrainer) {
        beatTrainer = _beatTrainer;
    }

    public void setPlayerOrientation(Direction _playerOrientation) {
        playerOrientation = _playerOrientation;
    }

    public NbFightCoordss getBeatTrainer() {
        return beatTrainer;
    }

    StringList getPartiallyAccessiblePlaces() {
        return partiallyAccessiblePlaces;
    }

    StringList getFullAccessiblePlaces() {
        return fullAccessiblePlaces;
    }

    public ShortMap<PointEqList> getBeatGymTrainer() {
        return beatGymTrainer;
    }

    public Direction getPlayerOrientation() {
        return playerOrientation;
    }

    public boolean isShowEndGame() {
        return showEndGame;
    }

    public String getZippedRom() {
        return zippedRom;
    }

    public void setZippedRom(String _zippedRom) {
        zippedRom = _zippedRom;
    }
}
