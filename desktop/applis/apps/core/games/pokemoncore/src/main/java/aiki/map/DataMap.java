package aiki.map;

import aiki.comparators.ComparatorMiniMapCoords;
import aiki.db.DataBase;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Item;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.util.LevelMove;
import aiki.game.NbFightCoords;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DealerItem;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Campaign;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.LevelArea;
import aiki.map.tree.PlaceArea;
import aiki.map.tree.Tree;
import aiki.map.util.*;
import aiki.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.*;
import code.util.Ints;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;


public final class DataMap {

    private static final String SPACE = " ";

    private CustList<Place> places;

    private CoordsLists accessCondition;

    private MiniMapCoordsList miniMap;

    private String unlockedCity;

    private Coords begin;

    private WildPk firstPokemon;

    private int screenWidth = 9;

    private int screenHeight = 9;

    private int spaceBetweenLeftAndHeros = 4;

    private int spaceBetweenTopAndHeros = 4;

    private int sideLength = 32;

    private boolean error;

    private Tree tree = new Tree();

    private CoordssCondition accessibility = new CoordssCondition();

    private final Condition cities = new Condition();

    private Condition leagues = new Condition();

    private CustList<NbFightCoords> beatTrainer = new CustList<NbFightCoords>();

    private Condition beatGymLeader = new Condition();

    private ShortMap< PointEqList> beatGymTrainer = new ShortMap< PointEqList>();

    private Condition hostPokemons = new Condition();

    private Condition takenPokemon = new Condition();

    private Condition takenObjects = new Condition();

    private ScreenCoordssCoords tiles = new ScreenCoordssCoords();

    private final ScreenCoordssInt backgroundImages = new ScreenCoordssInt();

    private final ScreenCoordssCustListInt foregroundImages = new ScreenCoordssCustListInt();

    public void validate(DataBase _d) {
        if (screenWidth < 0 || screenHeight < 0 || spaceBetweenLeftAndHeros <= 0 || spaceBetweenTopAndHeros <= 0 || screenWidth <= spaceBetweenLeftAndHeros + 1
                || screenHeight <= spaceBetweenTopAndHeros + 1 || sideLength <= 0 || places.isEmpty()) {
            _d.setError(true);
            return;
        }
        initInteractiveElements();
        firstPokemon.validateAsNpc(_d);

        initializeTree();
        if (!existCoords(begin) || !getLevelByCoords(begin).isEmptyForAdding(begin.getLevel().getPoint())) {
            _d.setError(true);
            return;
        }
        Shorts placesNumbers_ = placesNumbersValid(_d);
        basicValidateLinks(_d);
        accessConditionAdjust();
        accessConditionCheck(_d);
        initializeAccessibility();
        if(error) {
            _d.setError(true);
        }
        CoordssCondition filterAccessibility_ = filterAccessibility();
        moveTutorBallCheck(_d, filterAccessibility_);
        StringList evoObjects_ = evoObjects(filterAccessibility_);
        StringList movesTmHm_ = movesTmHm(_d, filterAccessibility_);
        PlaceLevelsInts wildPokemonBeforeFirstLeague_ = wildPokemonBeforeFirstLeague(filterAccessibility_);

        evoObjectsCheck(_d, evoObjects_);
        checkEvolutionMove(_d, movesTmHm_);
        checkEvolutionMoveType(_d, movesTmHm_);
        StringMap<EnumList<Gender>> directCatchPk_ = directCatchPk(wildPokemonBeforeFirstLeague_);

        checkCatchBaseEvos(_d, directCatchPk_);
        existPkDefaultEggCheck(_d);
        legPkCheck(_d);
        CustList<MiniMapCoords> list_ = miniMap.getKeys();
        checkDims(_d, list_);
        Shorts placesMiniMap_ = placesMiniMap(list_);
        CustList<CustList<int[][]>> images_ = allImages(_d, list_);
        imagesCheck(_d, images_);
        if (!NumberUtil.equalsSetShorts(placesMiniMap_, placesNumbers_)) {
            _d.setError(true);
        }
        if (_d.getMiniMap(getUnlockedCity()).length == 0) {
            error = true;
            _d.setError(true);
        }
        checkCitiesAccess(_d);
        for (Coords c : beatGymLeader) {
            if (c.isInside()) {
                Coords coordsExt_ = new Coords();
                coordsExt_.setNumberPlace(c.getNumberPlace());
                coordsExt_.setLevel(new LevelPoint());
                Point exitBuilding_ = new Point(c.getInsideBuilding());
                exitBuilding_.moveTo(Direction.DOWN);
                coordsExt_.getLevel().setPoint(exitBuilding_);
                checkFighterAccess(_d, coordsExt_);
                continue;
            }
            Place plVal_ = places.get(c.getNumberPlace());
            if (plVal_ instanceof League) {
                League league_ = (League) plVal_;
                Coords accessLeague_ = league_.getAccessCoords();
                checkFighterAccess(_d, accessLeague_);
            } else {
                checkFighterAccess(_d, c);
            }
        }
        for (NbFightCoords c : beatTrainer) {
            Coords fightAccess_ = c.getCoords();
            checkFighterAccess(_d, fightAccess_);
        }
    }

    private void checkFighterAccess(DataBase _d, Coords _acc) {
        boolean existAccess_ = existAccess(_acc);
        if (!existAccess_) {
            _d.setError(true);
        }
    }

    private void checkCitiesAccess(DataBase _d) {
        boolean firstCities_ = false;
        for (Coords c : cities) {
            if (accessibility.contains(c)) {
                if (accessibility.getVal(c).isEmpty()) {
                    firstCities_ = true;
                }
            } else {
                _d.setError(true);
            }
        }
        if (!firstCities_) {
            _d.setError(true);
        }
    }

    private void imagesCheck(DataBase _d, CustList<CustList<int[][]>> _images) {
        int size_ = _images.size();
        for (int i = IndexConstants.FIRST_INDEX; i < size_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < size_; j++) {
                if (i == j) {
                    continue;
                }
                imagesCheckCross(_d, _images, i, j);
            }
        }
    }

    private void imagesCheckCross(DataBase _d, CustList<CustList<int[][]>> _images, int _i, int _j) {
        for (int[][] k : _images.get(_i)) {
            int height_ = k.length;
            if (height_ == 0) {
                continue;
            }
            int width_ = k[0].length;
            for (int[][] l : _images.get(_j)) {
                if (height_ != l.length || width_ != l[0].length) {
                    continue;
                }
                boolean eq_ = eqSameDims(k, height_, width_, l);
                if (eq_) {
                    _d.setError(true);
                }
            }
        }
    }

    private boolean eqSameDims(int[][] _k, int _height, int _width, int[][] _l) {
        boolean eq_ = true;
        for (int m = 0; m < _height; m++) {
            for (int n = 0; n < _width; n++) {
                if (_l[m][n] != _k[m][n]) {
                    eq_ = false;
                    break;
                }
            }
            if (!eq_) {
                break;
            }
        }
        return eq_;
    }

    private CustList<CustList<int[][]>> allImages(DataBase _d, CustList<MiniMapCoords> _list) {
        CustList<int[][]> imagesCities_;
        CustList<int[][]> imagesRoads_;
        CustList<int[][]> imagesCaves_;
        CustList<int[][]> imagesLeagues_;
        CustList<int[][]> imagesOutside_;
        CustList<int[][]> imageUnlockedCity_ = new CustList<int[][]>(
                _d.getMiniMap(unlockedCity));
        imagesCities_ = new CustList<int[][]>();
        imagesRoads_ = new CustList<int[][]>();
        imagesCaves_ = new CustList<int[][]>();
        imagesLeagues_ = new CustList<int[][]>();
        imagesOutside_ = new CustList<int[][]>();
        for (MiniMapCoords m : _list) {
            TileMiniMap tile_ = miniMap.getVal(m);
            int[][] image_ = _d.getMiniMap(tile_.getFile());
            short place_ = tile_.getPlace();
            if (NumberUtil.eq(place_, IndexConstants.INDEX_NOT_FOUND_ELT)) {
                imagesOutside_.add(image_);
                continue;
            }
            if (places.isValidIndex(place_)) {
                Place pl_ = places.get(place_);
                if (pl_ instanceof City) {
                    imagesCities_.add(image_);
                } else if (pl_ instanceof Road) {
                    imagesRoads_.add(image_);
                } else if (pl_ instanceof Cave) {
                    imagesCaves_.add(image_);
                } else {
                    imagesLeagues_.add(image_);
                }
            }
        }
        CustList<CustList<int[][]>> images_ = new CustList<CustList<int[][]>>();
        images_.add(imagesOutside_);
        images_.add(imagesCities_);
        images_.add(imagesRoads_);
        images_.add(imagesCaves_);
        images_.add(imagesLeagues_);
        images_.add(imageUnlockedCity_);
        return images_;
    }

    private Shorts placesMiniMap(CustList<MiniMapCoords> _list) {
        Shorts placesMiniMap_ = new Shorts();
        for (MiniMapCoords m : _list) {
            TileMiniMap tile_ = miniMap.getVal(m);
            if (NumberUtil.eq(tile_.getPlace(), IndexConstants.INDEX_NOT_FOUND_ELT)) {
                continue;
            }
            placesMiniMap_.add(tile_.getPlace());
        }
        return placesMiniMap_;
    }

    private void checkDims(DataBase _d, CustList<MiniMapCoords> _list) {
        int maxWidth_ = 0;
        int maxHeight_ = 0;
        for (MiniMapCoords m : _list) {
            if (m.getXcoords() < 0) {
                _d.setError(true);
            }
            if (m.getYcoords() < 0) {
                _d.setError(true);
            }
            maxWidth_ = Math.max(maxWidth_,m.getXcoords());
            maxHeight_ = Math.max(maxHeight_,m.getYcoords());
        }
        if (_list.size() != (maxWidth_ + 1) * (maxHeight_ + 1)) {
            _d.setError(true);
        }
    }

    private void legPkCheck(DataBase _d) {
        StringList legPk_ = legPk(_d);
        StringList wildPk_ = wildPkAccess();
        if (!wildPk_.containsAllObj(legPk_)) {
            _d.setError(true);
        }
    }

    private StringList wildPkAccess() {
        StringList wildPk_ = new StringList();
        for (Coords c : accessibility.getKeys()) {
            Place pl_ = places.get(c.getNumberPlace());
            if (!(pl_ instanceof Campaign)) {
                continue;
            }
            ByteMap< Level> levels_;
            levels_ = pl_.getLevelsMap();
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) levels_
                    .getVal(c.getLevel().getLevelIndex());
            AreaApparition area_ = level_.getAreaByPoint(c.getLevel()
                    .getPoint());
            if (!area_.isVirtual()) {
                for (WildPk pk_ : area_.getWildPokemon()) {
                    wildPk_.add(pk_.getName());
                }
                for (WildPk pk_ : area_.getWildPokemonFishing()) {
                    wildPk_.add(pk_.getName());
                }
            }
            if (level_.containsPokemon(c.getLevel().getPoint())) {
                WildPk pk_ = level_.getPokemon(c.getLevel().getPoint());
                wildPk_.add(pk_.getName());
            }
        }
        return wildPk_;
    }

    private StringList legPk(DataBase _d) {
        StringList legPk_ = new StringList();
        for (String n : _d.getPokedex().getKeys()) {
            if (!StringUtil.contains(_d.getLegPks(),n)) {
                continue;
            }
            legPk_.add(n);
        }
        return legPk_;
    }

    private void existPkDefaultEggCheck(DataBase _d) {
        boolean existPkDefaultEgg_ = false;
        for (String n : _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(n);
            if (pk_.getGenderRep() == GenderRepartition.NO_GENDER && StringUtil.contains(pk_.getEggGroups(), _d.getDefaultEggGroup())) {
                existPkDefaultEgg_ = true;
                break;
            }
        }
        if (!existPkDefaultEgg_) {
            _d.setError(true);
        }
    }

    private void checkCatchBaseEvos(DataBase _d, StringMap<EnumList<Gender>> _directCatchPk) {
        StringList baseEvos_ = baseEvos(_d);
        if (!_directCatchPk.containsAllAsKeys(baseEvos_)) {
            _d.setError(true);
        }
        for (String n : baseEvos_) {
            PokemonData fPk_ = _d.getPokemon(n);
            EnumList<Gender> val_ = _directCatchPk.getVal(n);
            if (val_ == null) {
                _d.setError(true);
                continue;
            }
            if (!val_.containsAllObj(
                    fPk_.getGenderRep().getPossibleGenders())) {
                _d.setError(true);
            }
        }
    }

    private StringList baseEvos(DataBase _d) {
        StringList baseEvos_ = new StringList();
        for (String n : _d.getPokedex().getKeys()) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (fPk_.getGenderRep() == GenderRepartition.LEGENDARY) {
                continue;
            }
            baseEvos_.add(fPk_.getBaseEvo());
        }
        return baseEvos_;
    }

    private StringMap<EnumList<Gender>> directCatchPk(PlaceLevelsInts _wildPokemonBeforeFirstLeague) {
        StringMap<EnumList<Gender>> directCatchPk_ = new StringMap<EnumList<Gender>>();
        int nbPlaces_ = places.size();
        for (short p = IndexConstants.FIRST_INDEX; p < nbPlaces_; p++) {
            Place pl_ = places.get(p);
            for (byte l : pl_.getLevelsMap().getKeys()) {
                Level level_ = pl_.getLevelsMap().getVal(l);
                if (!(level_ instanceof LevelWithWildPokemon)) {
                    continue;
                }
                LevelWithWildPokemon levelWild_ = (LevelWithWildPokemon) level_;
                directCatchPkLevel(_wildPokemonBeforeFirstLeague, directCatchPk_, p, l, levelWild_);
            }
        }
        return directCatchPk_;
    }

    private void directCatchPkLevel(PlaceLevelsInts _wildPokemonBeforeFirstLeague, StringMap<EnumList<Gender>> _directCatchPk, short _p, byte _l, LevelWithWildPokemon _levelWild) {
        PlaceLevel keyPlaceLevel_ = new PlaceLevel(_p, _l);
        if (!_wildPokemonBeforeFirstLeague.contains(keyPlaceLevel_)) {
            return;
        }
        Ints levelPokemon_;
        levelPokemon_ = _wildPokemonBeforeFirstLeague
                .getVal(keyPlaceLevel_);
        for (int index_ : levelPokemon_) {
            CustList<AreaApparition> wildPokemonAreas_ = _levelWild.getWildPokemonAreas();
            if (wildPokemonAreas_.isValidIndex(index_)) {
                AreaApparition areaApparition_ = wildPokemonAreas_
                        .get(index_);
                CustList<WildPk> wildPokemon_ = areaApparition_.getWildPokemon();
                feedDirectCatch(_directCatchPk, wildPokemon_);
                CustList<WildPk> wildPokemonFishing_ = areaApparition_.getWildPokemonFishing();
                feedDirectCatch(_directCatchPk, wildPokemonFishing_);
            }
        }
    }

    private void checkEvolutionMoveType(DataBase _d, StringList _movesTmHm) {
        StringList availableTypesTm_ = new StringList();
        for (String m : _movesTmHm) {
            availableTypesTm_.addAllElts(_d.getMove(m).getTypes());
        }
        for (String p : _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(p);
            StringList types_ = new StringList();
            for (Evolution e : pk_.getEvolutions().values()) {
                if (!(e instanceof EvolutionMoveType)) {
                    continue;
                }
                types_.add(((EvolutionMoveType) e).getType());
            }
            if (types_.isEmpty()) {
                continue;
            }
            StringList typesRetr_ = new StringList();
            for (String m : pk_.getMoveTutors()) {
                typesRetr_.addAllElts(_d.getMove(m).getTypes());
            }
            for (LevelMove l : pk_.getLevMoves()) {
                typesRetr_.addAllElts(_d.getMove(l.getMove()).getTypes());
            }
            typesRetr_.addAllElts(availableTypesTm_);
            if (!typesRetr_.containsAllObj(types_)) {
                _d.setError(true);
            }
        }
    }

    private void checkEvolutionMove(DataBase _d, StringList _movesTmHm) {
        for (String p : _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(p);
            StringList moves_ = movesForEvos(pk_);
            if (moves_.isEmpty()) {
                continue;
            }
            StringList movesRetr_ = new StringList();
            for (String m : pk_.getMoveTutors()) {
                movesRetr_.add(m);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                movesRetr_.add(l.getMove());
            }
            movesRetr_.addAllElts(_movesTmHm);
            for (String m : moves_) {
                if (!StringUtil.contains(movesRetr_, m)) {
                    _d.setError(true);
                }
            }
        }
    }

    private StringList movesForEvos(PokemonData _pk) {
        StringList moves_ = new StringList();
        for (Evolution e : _pk.getEvolutions().values()) {
            if (!(e instanceof EvolutionMove)) {
                continue;
            }
            moves_.add(((EvolutionMove) e).getMove());
        }
        return moves_;
    }

    private void evoObjectsCheck(DataBase _d, StringList _evoObjects) {
        for (String o : _d.getItems().getKeys()) {
            Item o_ = _d.getItems().getVal(o);
            if (o_ instanceof EvolvingStone && !StringUtil.contains(_evoObjects, o)) {
                _d.setError(true);
            }
            if (o_ instanceof EvolvingItem && !StringUtil.contains(_evoObjects, o)) {
                _d.setError(true);
            }
        }
    }

    private PlaceLevelsInts wildPokemonBeforeFirstLeague(CoordssCondition _filterAccessibility) {
        PlaceLevelsInts wildPokemonBeforeFirstLeague_ = new PlaceLevelsInts();
        for (Coords c : _filterAccessibility.getKeys()) {
            PlaceArea pl_ = tree.getPlace(c.getNumberPlace());
            LevelArea level_ = pl_.getLevel(c.getLevel().getLevelIndex());
            PlaceLevel keyPlaceLevel_ = new PlaceLevel(c.getNumberPlace(), c
                    .getLevel().getLevelIndex());
            int index_ = level_.getIndex(c.getLevel().getPoint());
            if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
                continue;
            }
            if (wildPokemonBeforeFirstLeague_.contains(keyPlaceLevel_)) {
                wildPokemonBeforeFirstLeague_.getVal(keyPlaceLevel_).add(index_);
                wildPokemonBeforeFirstLeague_.getVal(keyPlaceLevel_)
                        .removeDuplicates();
            } else {
                wildPokemonBeforeFirstLeague_.put(keyPlaceLevel_,
                        Ints.newList(index_));
            }
        }
        return wildPokemonBeforeFirstLeague_;
    }

    private StringList movesTmHm(DataBase _d, CoordssCondition _filterAccessibility) {
        StringList movesTmHm_ = new StringList();
        for (Coords c : _filterAccessibility.getKeys()) {
            Level l_ = getLevelByCoords(c);
            if (l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                        .values()) {
                    if (p instanceof Seller) {
                        for (short s : ((Seller) p).getTm()) {
                            movesTmHm_.add(_d.getTm().getVal(s));
                        }
                    }
                }
            }
        }
        return movesTmHm_;
    }

    private StringList evoObjects(CoordssCondition _filterAccessibility) {
        StringList evoObjects_ = new StringList();
        for (Coords c : _filterAccessibility.getKeys()) {
            Level l_ = getLevelByCoords(c);
            if (l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                        .values()) {
                    if (p instanceof Seller && ((Seller) p).getSell() == SellType.ITEM) {
                        evoObjects_.addAllElts(((Seller) p).getItems());
                    }
                }
            }
        }
        return evoObjects_;
    }

    private void moveTutorBallCheck(DataBase _d, CoordssCondition _filterAccessibility) {
        moveTutorCheck(_d, _filterAccessibility);
        boolean ball_ = false;
        for (Coords c : _filterAccessibility.getKeys()) {
            Level l_ = getLevelByCoords(c);
            if (accessibility.getVal(c).isEmpty() && l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                        .values()) {

                    if (p instanceof Seller && StringUtil.contains(((Seller) p).getItems(), _d.getBallDef())) {
                        ball_ = true;
                    }
                }
            }
        }
        if (!ball_) {
            _d.setError(true);
        }
    }

    private void moveTutorCheck(DataBase _d, CoordssCondition _filterAccessibility) {
        boolean moveTutor_ = false;
        for (Coords c : _filterAccessibility.getKeys()) {
            Level l_ = getLevelByCoords(c);
            if (accessibility.getVal(c).isEmpty() && l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                        .values()) {
                    if (p instanceof Seller && ((Seller) p).getSell() == SellType.MOVE) {
                        moveTutor_ = true;
                    }
                }
            }
        }
        if (!moveTutor_) {
            _d.setError(true);
        }
    }

    private CoordssCondition filterAccessibility() {
        Condition coords_ = coordsPossibleLeague();
        CoordssCondition filterAccessibility_ = new CoordssCondition();
        for (CommonParam<Coords, Condition> c : accessibility.entryList()) {
            if (coords_.containsAllObj(c.getValue())) {
                filterAccessibility_.addEntry(c.getKey(), c.getValue());
            }
        }
        return filterAccessibility_;
    }

    private Condition coordsPossibleLeague() {
        Condition coords_ = new Condition();
        if (!leagues.isEmpty()) {
            League firstLeague_ = (League) places.get(leagues.first()
                    .getNumberPlace());
            coords_ = accessibility
                    .getVal(firstLeague_.getAccessCoords());
        }
        return coords_;
    }

    private void accessConditionCheck(DataBase _d) {
        for (Place p : places) {
            if (!(p instanceof League)) {
                continue;
            }
            League league_ = (League) p;
            if (!accessCondition.contains(league_.getAccessCoords())) {
                _d.setError(true);
            }
        }
        for (Coords c : accessCondition.getKeys()) {
            for (Coords c2_ : accessCondition.getVal(c)) {
                if (!tree.isValid(c2_, true)) {
                    _d.setError(true);
                }
            }
        }
    }

    private void accessConditionAdjust() {
        for (Coords c : accessCondition.getKeys()) {
            Condition invalidCoords_ = new Condition();
            Condition addedCoords_ = new Condition();
            for (Coords c2_ : accessCondition.getVal(c)) {
                Place pl_ = places.get(c2_.getNumberPlace());
                if (pl_ instanceof League) {
                    if (!beatGymLeader.containsObj(c2_)) {
                        invalidCoords_.add(c2_);
                        Coords c_ = new Coords();
                        c_.setNumberPlace(c2_.getNumberPlace());
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex((byte) 0);
                        c_.getLevel().setPoint(((League) pl_).getBegin());
                        addedCoords_.add(c_);
                    }
                } else {
                    if (!beatGymLeader.containsObj(c2_)) {
                        invalidCoords_.add(c2_);
                    }
                }
            }
            accessCondition.getVal(c).removeAllElements(invalidCoords_);
            accessCondition.getVal(c).addAllElts(addedCoords_);
        }
        accessConditionUniq();
        accessConditionDeleteInvalid();
    }

    private void accessConditionUniq() {
        for (Coords c : accessCondition.getKeys()) {
            accessCondition.getVal(c).removeDuplicates();
        }
    }

    private void accessConditionDeleteInvalid() {
        for (Coords c : accessCondition.getKeys()) {
            if (accessCondition.getVal(c).isEmpty()) {
                accessCondition.removeKey(c);
            } else if (c.isInside()) {
                accessCondition.removeKey(c);
            } else {
                Place p_ = places.get(c.getNumberPlace());
                if (p_ instanceof League) {
                    accessCondition.removeKey(c);
                }
            }
        }
    }

    private void basicValidateLinks(DataBase _d) {
        int nbPlaces_ = places.size();
        for (short p = IndexConstants.FIRST_INDEX; p < nbPlaces_; p++) {
            if (places.get(p) instanceof InitializedPlace) {
                InitializedPlace place_ = (InitializedPlace) places.get(p);
                Points< Link> links_;
                links_ = place_.getLinksWithCaves();
                for (Point pt_ : links_.getKeys()) {

                    basicCheckLinksWithCave(_d, p, links_, pt_);

                }
                continue;
            }
            if (places.get(p) instanceof Cave) {
                Cave place_ = (Cave) places.get(p);
                LevelPoints links_;
                links_ = place_.getLinksWithOtherPlaces();
                for (LevelPoint l : links_.getKeys()) {

                    basicCheckLinksOtherPlaces(_d, p, links_, l);

                }
            }
        }
    }

    private void basicCheckLinksOtherPlaces(DataBase _d, short _p, LevelPoints _links, LevelPoint _l) {
        Coords link_ = closestTile(_links.getVal(_l));
        if (!tree.isValid(_links.getVal(_l).getCoords(), true)) {
            _d.setError(true);
        }
        short numberPlace_ = link_.getNumberPlace();
        if (!places.isValidIndex(numberPlace_)) {
            _d.setError(true);
        } else {
            Place t_ = places.get(numberPlace_);
            if (!(t_ instanceof InitializedPlace)) {
                _d.setError(true);
            } else {
                InitializedPlace cave_ = (InitializedPlace) t_;
                Point point_ = link_.getLevel().getPoint();
                if (!cave_.getLinksWithCaves().contains(point_)) {
                    _d.setError(true);
                } else {

                    Coords other_ = closestTile(cave_.getLinksWithCaves()
                            .getVal(point_));
                    Coords current_ = new Coords();
                    current_.setNumberPlace(_p);
                    current_.setLevel(_l);
                    checkCoordsEq(_d, other_, current_);
                }
            }
        }
    }

    private void basicCheckLinksWithCave(DataBase _d, short _p, Points<Link> _links, Point _pt) {
        Coords link_ = closestTile(_links.getVal(_pt));
        if (!tree.isValid(_links.getVal(_pt).getCoords(), true)) {
            _d.setError(true);
        }
        short numberPlace_ = link_.getNumberPlace();
        if (!places.isValidIndex(numberPlace_)) {
            _d.setError(true);
        } else {
            Place t_ = places.get(numberPlace_);
            if (!(t_ instanceof Cave)) {
                _d.setError(true);
            } else {
                Cave cave_ = (Cave) t_;
                LevelPoint lPoint_ = link_.getLevel();
                if (!cave_.getLinksWithOtherPlaces().contains(lPoint_)) {
                    _d.setError(true);
                } else {

                    Coords other_ = closestTile(cave_.getLinksWithOtherPlaces()
                            .getVal(lPoint_));
                    Coords current_ = new Coords();
                    current_.setNumberPlace(_p);
                    current_.setLevel(new LevelPoint());
                    current_.getLevel().setLevelIndex((byte) 0);
                    current_.getLevel().setPoint(_pt);
                    checkCoordsEq(_d, other_, current_);
                }
            }
        }
    }

    private void checkCoordsEq(DataBase _d, Coords _other, Coords _current) {
        if (!Coords.eq(_other, _current)) {
            _d.setError(true);
        }
    }

    private Shorts placesNumbersValid(DataBase _d) {
        Shorts placesNumbers_ = new Shorts();
        int nbPlaces_ = places.size();
        for (short p = IndexConstants.FIRST_INDEX; p < nbPlaces_; p++) {
            placesNumbers_.add(p);
            places.get(p).validate(_d, tree.getPlace(p));
            if (!(places.get(p).validLinks(p, tree))) {
                _d.setError(true);
            }
        }
        return placesNumbers_;
    }

    void feedDirectCatch(StringMap<EnumList<Gender>> _directCatchPk, CustList<WildPk> _wildPokemon) {
        for (WildPk p : _wildPokemon) {
            if (!_directCatchPk.contains(p.getName())) {
                _directCatchPk.put(p.getName(),
                        new EnumList<Gender>(p.getGender()));
            } else {
                _directCatchPk.getVal(p.getName()).add(
                        p.getGender());
            }
        }
    }

    boolean existAccess(Coords _coords) {
        boolean existAccess_ = false;
        for (Direction d : Direction.all()) {
            if (accessibility.contains(closestTile(
                    _coords, d))) {
                existAccess_ = true;
                break;
            }
        }
        return existAccess_;
    }

    public boolean existCoords(Coords _c) {
        if (!existLevel(_c)) {
            return false;
        }
        Place plBegin_ = places.get(_c.getNumberPlace());
        boolean correctCoords_;
        if (plBegin_ instanceof City&&_c.isInside()) {
            Point bIncome_ = _c.getInsideBuilding();
            Level lev_ = ((City) plBegin_).getBuildings().getVal(bIncome_).getLevel();
            correctCoords_ = checkLevel(lev_,_c);
        } else {
            Level curLevel_ = plBegin_.getLevelsList().get(_c.getLevel().getLevelIndex());
            correctCoords_ = checkLevel(curLevel_,_c);
        }
        return correctCoords_;
    }

    private boolean existLevel(Coords _c) {
        short numberPlace_ = _c.getNumberPlace();
        if (!places.isValidIndex(numberPlace_)) {
            return false;
        }
        Place plBegin_ = places.get(numberPlace_);
        boolean correctCoords_;
        if (_c.isInside()) {
            if (plBegin_ instanceof City) {
                Point bIncome_ = _c.getInsideBuilding();
                correctCoords_ = ((City) plBegin_).getBuildings().contains(bIncome_);
            } else {
                correctCoords_ = false;
            }
        } else {
            correctCoords_ = plBegin_.getLevelsList().isValidIndex(_c.getLevel().getLevelIndex());
        }
        return correctCoords_;
    }
    private static boolean checkLevel(Level _l, Coords _c) {
        return _l.getEnvBlockByPoint(_c.getLevel().getPoint()).isValid();
    }
    public AreaApparition getAreaByCoords(Coords _coords) {
        if (!_coords.isValid()) {
            return new AreaApparition();
        }
        Level l_ = getLevelByCoords(_coords);
        if (!(l_ instanceof LevelWithWildPokemon)) {
            return new AreaApparition();
        }
        LevelWithWildPokemon lv_ = (LevelWithWildPokemon) l_;
        return lv_.getAreaByPoint(_coords.getLevel().getPoint());
    }

    public void initializeTree() {
        tree = new Tree();
        tree.initialize(this);
    }

    public void initInteractiveElements() {
        beatTrainer = new CustList<NbFightCoords>();
        beatGymLeader = new Condition();
        beatGymTrainer = new ShortMap< PointEqList>();
        hostPokemons = new Condition();
        takenPokemon = new Condition();
        takenObjects = new Condition();
        int nbPlaces_ = places.size();
        for (short s = IndexConstants.FIRST_INDEX; s < nbPlaces_; s++) {
            Place place_ = places.get(s);
            if (place_ instanceof City) {
                gymTrainersLeader(s, (City) place_);
                utilBuildings(s, (City) place_);
            }
            if (place_ instanceof Campaign) {
                campaign(s, place_);
            }
            if (place_ instanceof League) {
                Coords c_ = new Coords();
                c_.setNumberPlace(s);
                c_.setLevel(new LevelPoint());
                c_.getLevel().setLevelIndex((byte) 0);
                c_.getLevel().setPoint(((League) place_).getBegin());
                beatGymLeader.add(c_);
            }
        }
    }

    private void campaign(short _s, Place _place) {
        for (byte k : _place.getLevelsMap().getKeys()) {
            LevelWithWildPokemon levelCave_ = (LevelWithWildPokemon) _place
                    .getLevelsMap().getVal(k);
            for (Point p : levelCave_.getLegendaryPks().getKeys()) {
                Coords c_ = new Coords();
                c_.setNumberPlace(_s);
                c_.setLevel(new LevelPoint());
                c_.getLevel().setLevelIndex(k);
                c_.getLevel().setPoint(p);
                takenPokemon.add(c_);
            }
            takenLookup(_s, k, levelCave_);
            for (Point p : levelCave_.getDualFights().getKeys()) {
                Coords c_ = new Coords();
                c_.setNumberPlace(_s);
                c_.setLevel(new LevelPoint());
                c_.getLevel().setLevelIndex(k);
                c_.getLevel().setPoint(p);
                beatGymLeader.add(c_);
            }
        }
        normalTrainers(_s, _place);
    }

    private void normalTrainers(short _s, Place _place) {
        ByteMap< Level> levels_ = _place
                .getLevelsMap();
        for (EntryCust<Byte, Level> e : levels_.entryList()) {
            LevelWithWildPokemon wild_ = (LevelWithWildPokemon) e
                    .getValue();
            for (CommonParam<Point,CharacterInRoadCave> c : wild_
                    .getCharacters().entryList()) {
                if (!(c.getValue() instanceof TrainerMultiFights)) {
                    continue;
                }
                Coords c_ = new Coords();
                c_.setNumberPlace(_s);
                c_.setLevel(new LevelPoint());
                c_.getLevel().setLevelIndex(e.getKey());
                c_.getLevel().setPoint(c.getKey());
                TrainerMultiFights tr_ = (TrainerMultiFights) c
                        .getValue();
                int nb_ = tr_.getTeamsRewards().size();
                for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
                    beatTrainer.add(new NbFightCoords(c_, i));
                }
            }
        }
    }

    private void utilBuildings(short _s, City _place) {
        for (CommonParam<Point,Building> b : _place
                .getBuildings().entryList()) {
            if (b.getValue() instanceof PokemonCenter) {
                Coords coordsCity_ = new Coords();
                coordsCity_.setNumberPlace(_s);
                coordsCity_.setLevel(new LevelPoint());
                coordsCity_.getLevel().setLevelIndex((byte) 0);
                coordsCity_.outside();
                coordsCity_.getLevel().setPoint(new Point(b.getKey()));
                coordsCity_.getLevel().getPoint()
                        .moveTo(Direction.DOWN);
                cities.add(coordsCity_);
                hosts(_s, b);
            }
        }
    }

    private void takenLookup(short _s, byte _k, LevelWithWildPokemon _levelCave) {
        for (Point p : _levelCave.getCharacters().getKeys()) {
            if (!(_levelCave.getCharacters().getVal(p) instanceof DealerItem)) {
                continue;
            }
            Coords c_ = new Coords();
            c_.setNumberPlace(_s);
            c_.setLevel(new LevelPoint());
            c_.getLevel().setLevelIndex(_k);
            c_.getLevel().setPoint(p);
            takenObjects.add(c_);
        }
        for (Point p : _levelCave.getItems().getKeys()) {
            Coords c_ = new Coords();
            c_.setNumberPlace(_s);
            c_.setLevel(new LevelPoint());
            c_.getLevel().setLevelIndex(_k);
            c_.getLevel().setPoint(p);
            takenObjects.add(c_);
        }
        for (Point p : _levelCave.getTm().getKeys()) {
            Coords c_ = new Coords();
            c_.setNumberPlace(_s);
            c_.setLevel(new LevelPoint());
            c_.getLevel().setLevelIndex(_k);
            c_.getLevel().setPoint(p);
            takenObjects.add(c_);
        }
        for (Point p : _levelCave.getHm().getKeys()) {
            Coords c_ = new Coords();
            c_.setNumberPlace(_s);
            c_.setLevel(new LevelPoint());
            c_.getLevel().setLevelIndex(_k);
            c_.getLevel().setPoint(p);
            takenObjects.add(c_);
        }
    }

    private void hosts(short _s, CommonParam<Point, Building> _b) {
        for (CommonParam<Point,Person> g : ((PokemonCenter) _b
                .getValue()).getIndoor().getGerants()
                .entryList()) {
            if (g.getValue() instanceof GerantPokemon && ((GerantPokemon) g.getValue()).getGerance() == GeranceType.HOST) {
                Coords c_ = new Coords();
                c_.setNumberPlace(_s);
                c_.setLevel(new LevelPoint());
                c_.affectInside(_b.getKey());
                c_.getLevel().setLevelIndex((byte) 0);
                c_.getLevel().setPoint(g.getKey());
                hostPokemons.add(c_);
                break;
            }
        }
    }

    private void gymTrainersLeader(short _s, City _place) {
        for (CommonParam<Point,Building> b : _place
                .getBuildings().entryList()) {
            if (b.getValue() instanceof Gym) {
                Coords c_ = new Coords();
                c_.setNumberPlace(_s);
                c_.setLevel(new LevelPoint());
                c_.affectInside(b.getKey());
                c_.getLevel().setLevelIndex((byte) 0);
                c_.getLevel().setPoint(
                        ((Gym) b.getValue()).getIndoor()
                                .getGymLeaderCoords());
                beatGymLeader.add(c_);
                beatGymTrainer
                        .put(_s, new PointEqList(((Gym) b.getValue())
                                .getIndoor().getGymTrainers().getKeys()));
                break;
            }
        }
    }

    public void initializeWildPokemon() {
        for (Place p : places) {
            if (p instanceof Campaign) {
                ((Campaign) p).initializeWildPokemon();
            }
        }
    }

    public MiniMapCoordsTileInts getImages(DataBase _data) {
        MiniMapCoordsTileInts map_ = new MiniMapCoordsTileInts(
                new ComparatorMiniMapCoords());
        for (MiniMapCoords m_ : miniMap.getKeys()) {
            int[][] image_ = _data.getMiniMap(miniMap.getVal(m_).getFile());

            map_.put(m_, image_);
        }
        return map_;
    }

    public Coords getCity(MiniMapCoords _m) {
        if (miniMap.contains(_m)) {
            short place_ = miniMap.getVal(_m).getPlace();
            for (Coords c : cities) {
                if (NumberUtil.eq(c.getNumberPlace(), place_)) {
                    return c;
                }
            }
        }
        return new Coords();
    }

    public String getName(int _x, int _y) {
        MiniMapCoords m_ = new MiniMapCoords((byte) _x, (byte) _y);
        if (miniMap.contains(m_)) {
            short place_ = miniMap.getVal(m_).getPlace();
            if (!NumberUtil.eq(place_, IndexConstants.INDEX_NOT_FOUND_ELT)) {
                return places.get(place_).getName();
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public int[][] getImage(DataBase _data, int _x, int _y) {
        MiniMapCoords m_ = new MiniMapCoords((byte) _x, (byte) _y);
        if (miniMap.contains(m_)) {
            String place_ = miniMap.getVal(m_).getFile();
            return _data.getMiniMap(place_);
        }
        return new int[0][0];
    }

    public int getMapWidth() {
        int maxWidth_ = 0;
        CustList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m : list_) {
            if (maxWidth_ < m.getXcoords()) {
                maxWidth_ = m.getXcoords();
            }
        }
        return maxWidth_ + 1;
    }

    public int getMapHeight() {
        int maxHeight_ = 0;
        CustList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m : list_) {
            if (maxHeight_ < m.getYcoords()) {
                maxHeight_ = m.getYcoords();
            }
        }
        return maxHeight_ + 1;
    }

    public Solution getSolution() {
        return new Solution(accessibility, places, tree);
    }

    public void initializeAccessibility() {
        Condition leaders_ = new Condition();
        CoordssCondition newElts_ = new CoordssCondition();
        newElts_.put(new Coords(begin), new Condition());
        CoordssCondition coordsCond_ = new CoordssCondition(
                newElts_);
        CoordssCondition coordsCondBis_ = new CoordssCondition(
                newElts_);
        CoordssCondition allTiles_ = new CoordssCondition(
                newElts_);
        leagues = new Condition();
        while (true) {
            CoordssCondition neigh_ = possibleNeighbours(allTiles_,
                    coordsCond_);
            CustList<Coords> diff_ = neigh_.getKeys();
            if (diff_.isEmpty()) {
                break;
            }
            for (CommonParam<Coords, Condition> e : neigh_.entryList()) {
                Coords c_ = e.getKey();
                if (accessConditionToBeTreated(c_, allTiles_)) {
                    coordsCondBis_.put(c_, e.getValue());
                }
            }
            Condition inext_ = new Condition();
            Condition ext_ = new Condition();
            feedAllTiles(coordsCond_, allTiles_, neigh_, inext_, ext_);
            if (!validConditions(inext_, neigh_)) {
                error = true;
                return;
            }
            diff_ = ext_;

            CustList<Coords> accessibleLeaders_ = accessibleLeaders(coordsCondBis_, allTiles_, diff_);
            feedLeagues(accessibleLeaders_);
            leaders_.addAllElts(accessibleLeaders_);
            coordsCondReset(leaders_, coordsCond_, coordsCondBis_, allTiles_);
        }
        leagues.removeDuplicates();
        accessibility = allTiles_;

        accessConditionNotLeagueCheck();

    }

    private void feedAllTiles(CoordssCondition _coordsCond, CoordssCondition _allTiles, CoordssCondition _neigh, Condition _inext, Condition _ext) {
        for (CommonParam<Coords, Condition> e : _neigh.entryList()) {
            Coords c_ = e.getKey();
            if (accessConditionToBeTreated(c_, _coordsCond)) {
                _inext.add(c_);
                continue;
            }
            _ext.add(c_);
            _allTiles.put(c_, e.getValue());
        }
    }

    private CustList<Coords> accessibleLeaders(CoordssCondition _coordsCondBis, CoordssCondition _allTiles, CustList<Coords> _diff) {
        CoordssCoords newLeaders_ = leaders(_diff);
        CustList<Coords> accessibleLeaders_ = newLeaders_.getKeys();
        for (Coords c : _coordsCondBis.getKeys()) {
            if (_allTiles.contains(c)) {
                continue;
            }
            for (Coords a : newLeaders_.getKeys()) {
                if (!accessCondition.getVal(c).containsObj(a)) {
                    continue;
                }
                Coords c_ = newLeaders_.getVal(a);
                _coordsCondBis.getVal(c).addAllElts(_allTiles.getVal(c_));

            }
            _coordsCondBis.getVal(c).removeDuplicates();
        }
        return accessibleLeaders_;
    }

    private void feedLeagues(CustList<Coords> _accessibleLeaders) {
        for (Coords c : _accessibleLeaders) {
            Place pl_ = places.get(c.getNumberPlace());
            if (!(pl_ instanceof League)) {
                continue;
            }
            leagues.add(c);
        }
    }

    private void coordsCondReset(Condition _leaders, CoordssCondition _coordsCond, CoordssCondition _coordsCondBis, CoordssCondition _allTiles) {
        _coordsCond.clear();
        Condition initCond_ = new Condition();
        for (CommonParam<Coords, Condition> e : _coordsCondBis.entryList()) {
            Coords c_ = e.getKey();
            if (accessConditionToBeTreated(c_, _allTiles)) {
                Condition cond_ = e.getValue();
                if (initCond_.isEmpty() && !cond_.exists(_leaders)) {
                    initCond_ = cond_;
                    _coordsCond.put(c_, cond_);
                } else if (Coords.equalsSet(cond_, initCond_)) {
                    _coordsCond.put(c_, cond_);
                }
            }

        }
    }

    private void accessConditionNotLeagueCheck() {
        Condition accessLeagues_ = new Condition();
        int nbPlaces_ = places.size();
        for (int p = 0; p <nbPlaces_;p++) {
            Place pl_ = places.get(p);
            if (pl_ instanceof League) {
                League l_ = (League) pl_;
                accessLeagues_.add(l_.getAccessCoords());
            }
        }
        for (Coords c : accessCondition.getKeys()) {
            if (!accessibility.contains(c) || accessLeagues_.containsObj(c)) {
                continue;
            }
            CoordssCondition conditions_ = getNext(c,
                    accessibility.getVal(c));
            boolean contained_ = intersect(accessLeagues_, c, conditions_);
            if (!contained_) {
                error = true;
                return;
            }
        }
    }

    private boolean intersect(Condition _accessLeagues, Coords _c, CoordssCondition _conditions) {
        boolean contained_ = false;
        Condition elts_ = accessCondition.getVal(_c);
        for (Coords n : _conditions.getKeys()) {
            if (accessCondition.contains(n)
                    && !_accessLeagues.containsObj(n) && Coords.equalsSet(accessCondition.getVal(n), elts_)) {
                continue;
            }
            Condition condition_ = accessibility.getVal(n);
            for (Coords a : condition_) {
                if (elts_.containsObj(a)) {
                    contained_ = true;
                }
            }
        }
        return contained_;
    }

    public CoordssCondition possibleNeighbours(
            CoordssCondition _visitedGl,
            CoordssCondition _previousVisited) {
        CoordssCondition visitedTiles_ = new CoordssCondition(
                _previousVisited);
        CustList<Coords> currentTiles_ = _previousVisited.getKeys();
        while (true) {
            Condition newPlaces_ = new Condition();
            for (Coords i : currentTiles_) {
                if (accessConditionToBeTreated(i, _previousVisited)) {
                    continue;
                }
                neighbours(_visitedGl, visitedTiles_, newPlaces_, i);
            }
            if (newPlaces_.isEmpty()) {
                break;
            }
            currentTiles_ = newPlaces_;
        }
        return visitedTiles_;
    }

    private void neighbours(CoordssCondition _visitedGl, CoordssCondition _visitedTiles, Condition _newPlaces, Coords _i) {
        CoordssCondition neighbours_ = getNext(_i,
                _visitedTiles.getVal(_i));
        for (CommonParam<Coords, Condition> e : neighbours_.entryList()) {
            Coords n_ = e.getKey();
            if (!_visitedTiles.contains(n_) && !_visitedGl.contains(n_)) {
                _visitedTiles.put(n_, e.getValue());
                _newPlaces.add(n_);
            }
        }
    }

    private boolean accessConditionToBeTreated(Coords _i, CoordssCondition _prev) {
        return accessCondition.contains(_i) && !_prev.contains(_i);
    }

    public CoordssCoords leaders(Listable<Coords> _accessibleCoords) {
        CoordssCoords list_ = new CoordssCoords();
        for (Coords c : _accessibleCoords) {
            Place place_ = getPlace(c.getNumberPlace());
            if (place_ instanceof League) {
                League league_ = (League) place_;
                byte ind_ = c.getLevel().getLevelIndex();
                if (NumberUtil.eq(ind_ + 1L, league_.getRooms().size())) {
                    Coords coords_ = new Coords(c);

                    coords_.getLevel().setLevelIndex((byte) 0);
                    coords_.getLevel().setPoint(new Point(league_.getBegin()));
                    list_.put(coords_, league_.getAccessCoords());
                }
                continue;
            }
            Level l_ = getLevelByCoords(
                    c);
            if (l_ instanceof LevelWithWildPokemon && ((LevelWithWildPokemon) l_).getDualFights().contains(
                    c.getLevel().getPoint())) {
                list_.put(c, c);
            }
            if (l_ instanceof LevelIndoorGym) {
                Coords coords_ = new Coords(c);
                coords_.getLevel().setPoint(
                        ((LevelIndoorGym) l_).getGymLeaderCoords());
                City city_ = (City) place_;
                Coords coordsValue_ = new Coords(c);
                coordsValue_.getLevel().setPoint(
                        city_.getBuildings().getVal(c.getInsideBuilding())
                                .getExitCity());
                list_.put(coords_, coordsValue_);
            }
        }
        return list_;
    }

    public CoordssCondition getNext(Coords _id, Condition _condition) {
        CoordssCondition return_ = new CoordssCondition();
        Place place_ = places.get(_id.getNumberPlace());
        Point pt_ = _id.getLevel().getPoint();
        enterLeague(_id, _condition, return_);
        if (place_ instanceof InitializedPlace) {
            InitializedPlace pl_ = (InitializedPlace) place_;
            enterLevelCave(_condition, pt_, return_, pl_.getLinksWithCaves());
            if (_id.isInside()) {
                return return_;
            }
            Level level_ = place_.getLevelByCoords(_id);
            for (Direction d : Direction.all()) {
                expandDirInitializedPlace(_id, _condition, return_, pl_, level_, d);
            }
            return return_;
        }
        if (place_ instanceof Cave) {
            Cave cave_ = (Cave) place_;
            enterInitializedPlace(_id, _condition, return_, cave_);
            LevelCave level_ = (LevelCave) cave_.getLevelsMap().getVal(
                    _id.getLevel().getLevelIndex());
            enterLevelCave(_condition, _id.getLevel().getPoint(), return_, level_.getLinksOtherLevels());
            for (Direction d : Direction.all()) {
                expandDirCave(_id, _condition, return_, pt_, cave_, level_, d);
            }
            return return_;
        }

        League league_ = (League) place_;
        byte levelIndex_ = _id.getLevel().getLevelIndex();
        LevelLeague level_ = league_.getRooms().get(levelIndex_);
        nextRoom(_id, _condition, return_, league_, level_);
        for (Direction d : Direction.all()) {
            Point ptNext_ = new Point(pt_);
            ptNext_.moveTo(d);
            if (level_.isEmpty(ptNext_) && level_.getEnvBlockByPoint(ptNext_).isValid()) {
                Coords coords_ = new Coords(_id);
                coords_.getLevel().setPoint(ptNext_);
                Condition cond_ = initCondition(coords_, _condition);
                return_.put(coords_, cond_);
            }
        }
        return return_;
    }

    private void nextRoom(Coords _id, Condition _condition, CoordssCondition _ret, League _league, LevelLeague _level) {
        byte levelIndex_ = _id.getLevel().getLevelIndex();
        if (Point.eq(_level.getAccessPoint(), _id.getLevel().getPoint()) && _league.getRooms().isValidIndex(levelIndex_ + 1)) {
            Coords coords_ = new Coords(_id);
            coords_.getLevel().setLevelIndex((byte) (levelIndex_ + 1));
            coords_.getLevel().setPoint(_level.getNextLevelTarget());
            Condition cond_ = initCondition(coords_, _condition);
            _ret.put(coords_, cond_);
        }
    }

    private void expandDirCave(Coords _id, Condition _condition, CoordssCondition _ret, Point _pt, Cave _cave, LevelCave _level, Direction _d) {
        Point ptNext_ = new Point(_pt);
        ptNext_.moveTo(_d);
        if (!_level.isEmpty(ptNext_)) {
            return;
        }
        Block block_ = _level.getBlockByPoint(ptNext_);
        if (block_.isValid()) {
            if (block_.getType() == EnvironmentType.NOTHING) {
                Coords coords_ = new Coords(_id);
                coords_.getLevel().setPoint(ptNext_);
                if (_cave.getLinksWithOtherPlaces().contains(
                        coords_.getLevel())) {
                    Condition cond_ = initCondition(_cave.getLinksWithOtherPlaces().getVal(coords_.getLevel()).getCoords(), _condition);
//                            Condition cond_ = initCondition(coords_, _condition);
                    _ret.put(coords_, cond_);
                }
            } else {
                Coords coords_ = new Coords(_id);
                coords_.getLevel().setPoint(ptNext_);
                Condition cond_ = initCondition(coords_, _condition);
                _ret.put(coords_, cond_);
            }
        }
    }

    private void expandDirInitializedPlace(Coords _id, Condition _condition, CoordssCondition _ret, InitializedPlace _pl, Level _level, Direction _d) {
        PlaceInterConnects links_ = _pl
                .getPointsWithCitiesAndOtherRoads();
        Point pt_ = _id.getLevel().getPoint();
        Point ptNext_ = new Point(pt_);
        ptNext_.moveTo(_d);
        if (!_level.isEmpty(ptNext_)) {
            return;
        }
        Block block_ = _level.getBlockByPoint(ptNext_);
        if (!block_.isValid()) {
            enterOtherInitializedPlace(_condition, _ret, links_, new PlaceInterConnect(pt_, _d));
        } else if (block_.getType() == EnvironmentType.NOTHING) {
            if (_pl instanceof City && ((City) _pl).getBuildings().contains(ptNext_)) {
                Building building_ = ((City) _pl)
                        .getBuildings().getVal(ptNext_);
                Coords coords_ = new Coords(_id);
                coords_.affectInside(ptNext_);
                coords_.getLevel().setPoint(
                        building_.getExitCity());
                Condition cond_ = initCondition(coords_,
                        _condition);
                _ret.put(coords_, cond_);
            } else {
                enterLevelCave(_condition, ptNext_, _ret, _pl.getLinksWithCaves());
            }
//                    if (pl_.getLinksWithCaves().contains(ptNext_)) {
//                        Coords coords_ = new Coords(_id);
//                        coords_.getLevel().setPoint(ptNext_);
//                        Condition cond_ = initCondition(coords_, _condition);
//                        return_.put(coords_, cond_);
//                    }
        } else {
            Coords coords_ = new Coords(_id);
            coords_.getLevel().setPoint(ptNext_);
            Condition cond_ = initCondition(coords_, _condition);
            _ret.put(coords_, cond_);
        }
    }

    private void enterInitializedPlace(Coords _id, Condition _condition, CoordssCondition _ret, Cave _cave) {
        if (_cave.getLinksWithOtherPlaces().contains(_id.getLevel())) {
            Link link_ = _cave.getLinksWithOtherPlaces().getVal(
                    _id.getLevel());
            Coords coords_ = link_.getCoords();
            Condition cond_ = initCondition(coords_, _condition);
            _ret.put(coords_, cond_);
        }
    }

    private void enterOtherInitializedPlace(Condition _condition, CoordssCondition _ret, PlaceInterConnects _links, PlaceInterConnect _plInter) {
        if (_links.contains(_plInter)) {
            Coords coords_ = _links
                    .getVal(_plInter);
            InitializedPlace plNext_ = (InitializedPlace) places
                    .get(coords_.getNumberPlace());
            Level levelNext_ = plNext_.getLevel();
            Point newPoint_ = coords_.getLevel().getPoint();
            if (levelNext_.getEnvBlockByPoint(newPoint_)
                    .isValid()) {
                Condition cond_ = initCondition(coords_,
                        _condition);
                _ret.put(coords_, cond_);
            }
        }
    }

    private void enterLevelCave(Condition _condition, Point _pt, CoordssCondition _ret, Points<Link> _links) {
        if (_links.contains(_pt)) {
            Link link_ = _links.getVal(_pt);
            Coords coords_ = link_.getCoords();
            Condition cond_ = initCondition(coords_, _condition);
            _ret.put(coords_, cond_);
        }
    }

    private void enterLeague(Coords _id, Condition _condition, CoordssCondition _ret) {
        int nbPlaces_ = places.size();
        for (short p = 0; p < nbPlaces_; p++) {
            Place pl_ = places.get(p);
            if (pl_ instanceof League && Coords.eq(((League) pl_).getAccessCoords(), _id)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(p);
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) 0);
                coords_.getLevel().setPoint(
                        new Point(((League) pl_).getBegin()));
                Condition condition_ = initCondition(coords_, _condition);
                _ret.put(coords_, condition_);
                break;
            }
        }
    }

    Condition initCondition(Coords _coords, Condition _gymCondition) {
        if (!accessCondition.contains(_coords)) {
            return _gymCondition;
        }
        Condition condition_ = new Condition();
        condition_.addAllElts(_gymCondition);
        condition_.addAllElts(accessCondition.getVal(_coords));
        return condition_;
    }

    boolean validConditions(Condition _accessCoords,
            CoordssCondition _condition) {
        CoordssCondition groups_ = new CoordssCondition();
        Condition defaultCondition_ = new Condition();
        for (Coords c : _accessCoords) {
            boolean continue_ = alreadyInGroups(groups_, c);
            if (continue_) {
                continue;
            }
            Condition eq_ = eqs(_accessCoords, defaultCondition_, c);
            groups_.put(c, eq_);
        }
        for (CommonParam<Coords, Condition> e : groups_.entryList()) {
            Condition cond_ = _condition.getVal(e.getKey());
            for (Coords c2_ : e.getValue()) {
                Condition condLoc_ = _condition.getVal(c2_);
                if (!Condition.equalsSet(cond_, condLoc_)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean alreadyInGroups(CoordssCondition _groups, Coords _c) {
        boolean continue_ = false;
        for (Condition l : _groups.values()) {
            if (l.containsObj(_c)) {
                continue_ = true;
                break;
            }
        }
        return continue_;
    }

    private Condition eqs(Condition _accessCoords, Condition _defaultCondition, Coords _c) {
        Condition eq_ = new Condition();
        eq_.add(_c);
        Condition currentElts_ = new Condition(eq_);
        while (true) {
            Condition newElts_ = new Condition();
            for (Coords c2_ : currentElts_) {
                CoordssCondition next_ = getNext(c2_,
                        _defaultCondition);
                for (Coords n : next_.getKeys()) {
                    if (_accessCoords.containsObj(n) && !eq_.containsObj(n)) {
                        newElts_.add(n);
                        eq_.add(n);
                    }
                }
            }
            if (newElts_.isEmpty()) {
                break;
            }
            currentElts_ = newElts_;
        }
        return eq_;
    }

    public String getTrainerName(Coords _coords) {
        Place pl_ = places.get(_coords.getNumberPlace());
        Level level_ = getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym g_ = (LevelIndoorGym) level_;
            return g_.getGymLeader().getName();
        }
        if (pl_ instanceof League) {
            League league_ = (League) pl_;
            LevelLeague l_ = league_.getRooms().last();
            return l_.getTrainer().getName();
        }
        if (level_ instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
            if (w_.getDualFights().contains(_coords.getLevel().getPoint())) {
                return StringUtil.join(w_.getDualFights().getVal(_coords.getLevel().getPoint())
                        .getNames(), SPACE);
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public boolean validSavedLink() {
        int nbPlaces_ = places.size();
        boolean valid_ = true;
        for (short i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            InitializedPlace place_ = asInitializedPlace(i);
            if (place_ == null) {
                continue;
            }
            Level level_ = place_.getLevel();
            Limits limits_ = level_.limits();
//            Point leftTopPoint_ = limits_.getTopLeft();
//            Point rightBottomPoint_ = limits_.getBottomRight();
            for (PlaceInterConnect k : place_.getSavedlinks().getKeys()) {
                if (!validSingleLevelPlaceInterConnect(i,place_,limits_,k)) {
                    valid_ = false;
                }
//                if (k.getDir() == Direction.UP && !NumberUtil.eq(leftTopPoint_.gety(), k.getSource().gety())) {
//                    valid_ = false;
//                }
//                if (k.getDir() == Direction.DOWN && !NumberUtil.eq(rightBottomPoint_.gety(), k.getSource()
//                        .gety())) {
//                    valid_ = false;
//                }
//                if (k.getDir() == Direction.LEFT && !NumberUtil.eq(leftTopPoint_.getx(), k.getSource().getx())) {
//                    valid_ = false;
//                }
//                if (k.getDir() == Direction.RIGHT && !NumberUtil.eq(rightBottomPoint_.getx(), k.getSource()
//                        .getx())) {
//                    valid_ = false;
//                }
//                Coords coords_ = pl_.getSavedlinks().getVal(k);
//                short numberPlace_ = coords_.getNumberPlace();
//                if (!places.isValidIndex(numberPlace_)) {
//                    valid_ = false;
//                    continue;
//                }
//                InitializedPlace other_ = (InitializedPlace) places
//                        .get(numberPlace_);
//                PlaceInterConnect key_ = new PlaceInterConnect(coords_
//                        .getLevel().getPoint(), k.getDir().getOpposite());
//                if (!other_.getSavedlinks().contains(key_)) {
//                    valid_ = false;
//                    continue;
//                }
//                Coords otherCoords_ = other_.getSavedlinks().getVal(key_);
//                if (!NumberUtil.eq(otherCoords_.getNumberPlace(), i)) {
//                    valid_ = false;
//                }
//                if (!Point
//                        .eq(k.getSource(), otherCoords_.getLevel().getPoint())) {
//                    valid_ = false;
//                }
            }
        }
        return valid_;
    }
    private boolean validSingleLevelPlaceInterConnect(short _i,InitializedPlace _pl,Limits _limits,PlaceInterConnect _k) {
        Point leftTopPoint_ = _limits.getTopLeft();
        Point rightBottomPoint_ = _limits.getBottomRight();
        boolean valid_ = _k.getDir() != Direction.UP || NumberUtil.eq(leftTopPoint_.gety(), _k.getSource().gety());
        if (_k.getDir() == Direction.DOWN && !NumberUtil.eq(rightBottomPoint_.gety(), _k.getSource()
                .gety())) {
            valid_ = false;
        }
        if (_k.getDir() == Direction.LEFT && !NumberUtil.eq(leftTopPoint_.getx(), _k.getSource().getx())) {
            valid_ = false;
        }
        if (_k.getDir() == Direction.RIGHT && !NumberUtil.eq(rightBottomPoint_.getx(), _k.getSource()
                .getx())) {
            valid_ = false;
        }
        Coords coords_ = _pl.getSavedlinks().getVal(_k);
        short numberPlace_ = coords_.getNumberPlace();
        InitializedPlace other_ = asInitializedPlace(numberPlace_);
        if (other_ == null) {
            return false;
        }
        PlaceInterConnect key_ = new PlaceInterConnect(coords_
                .getLevel().getPoint(), _k.getDir().getOpposite());
        if (!other_.getSavedlinks().contains(key_)) {
            return false;
        }
        Coords otherCoords_ = other_.getSavedlinks().getVal(key_);
        if (!NumberUtil.eq(otherCoords_.getNumberPlace(), _i)) {
            valid_ = false;
        }
        if (!Point
                .eq(_k.getSource(), otherCoords_.getLevel().getPoint())) {
            valid_ = false;
        }
        return valid_;
    }
    private InitializedPlace asInitializedPlace(short _i) {
        if (!places.isValidIndex(_i)) {
            return null;
        }
        Place place_ = places.get(_i);
        if (!(place_ instanceof InitializedPlace)) {
            return null;
        }
        return (InitializedPlace) place_;
    }

    public void initializeLinks() {
        ShortMap< CustList<PlaceInterConnect>> visited_ = new ShortMap< CustList<PlaceInterConnect>>();
        int nbPlaces_ = places.size();
        for (short i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            Place place_ = places.get(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            pl_.setPointsWithCitiesAndOtherRoads(new PlaceInterConnects());
        }
        for (short i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            Place place_ = places.get(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            for (PlaceInterConnect k : pl_.getSavedlinks().getKeys()) {
                if (visited_.contains(i) && PlaceInterConnects.contains(visited_.getVal(i),k)) {
                    continue;
                }
                Coords coords_ = pl_.getSavedlinks().getVal(k);
                Point pt_ = coords_.getLevel().getPoint();
                short i_ = coords_.getNumberPlace();
                merge(visited_, k, pt_, i_);
                join(i, coords_.getNumberPlace(), k.getSource(), pt_,
                        k.getDir());
            }
        }
    }

    public static void merge(ShortMap<CustList<PlaceInterConnect>> _visited, PlaceInterConnect _k, Point _pt, short _i) {
        if (!_visited.contains(_i)) {
            CustList<PlaceInterConnect> v_ = new CustList<PlaceInterConnect>();
            v_.add(new PlaceInterConnect(_pt, _k.getDir().getOpposite()));
            _visited.put(_i, v_);
        } else {
            CustList<PlaceInterConnect> v_ = _visited.getVal(_i);
            v_.add(new PlaceInterConnect(_pt, _k.getDir().getOpposite()));
        }
    }

    public void join(short _pl1, short _pl2, Point _p1, Point _p2,
            Direction _dir1) {
        Place place1_ = places.get(_pl1);
        Place place2_ = places.get(_pl2);
        Level l1_ = ((InitializedPlace) place1_).getLevel();
        Level l2_ = ((InitializedPlace) place2_).getLevel();
        CustList<PlaceInterConnect> keys1_ = new CustList<PlaceInterConnect>();
        CustList<PlaceInterConnect> keys2_ = new CustList<PlaceInterConnect>();
        Limits limits1_ = l1_.limits();
        Limits limits2_ = l2_.limits();
        Point leftTopPointOne_ = limits1_.getTopLeft();
        Point rightBottomPointOne_ = limits1_.getBottomRight();
        Point leftTopPointTwo_ = limits2_.getTopLeft();
        Point rightBottomPointTwo_ = limits2_.getBottomRight();
        Point p1_ = new Point(_p1);
        Point p2_ = new Point(_p2);
        int length_ = 0;
        if (_dir1.getx() == 0) {
            if (_dir1 == Direction.DOWN) {
                p1_.sety(rightBottomPointOne_.gety());
                p2_.sety(leftTopPointTwo_.gety());
            }
            if (_dir1 == Direction.UP) {
                p1_.sety(leftTopPointOne_.gety());
                p2_.sety(rightBottomPointTwo_.gety());
            }
            int u_ = Math.min(p1_.getx() - leftTopPointOne_.getx(), p2_.getx()
                    - leftTopPointTwo_.getx());
            int xtop1_ = p1_.getx() - u_;
            int xtop2_ = p2_.getx() - u_;
            int d_ = Math.min(rightBottomPointOne_.getx() - p1_.getx(),
                    rightBottomPointTwo_.getx() - p2_.getx());
            length_ = u_ + d_ + 1;
            for (short i = IndexConstants.FIRST_INDEX; i < length_; i++) {
                keys1_.add(new PlaceInterConnect(new Point(
                        (short) (xtop1_ + i), p1_.gety()), _dir1));
                keys2_.add(new PlaceInterConnect(new Point(
                        (short) (xtop2_ + i), p2_.gety()), _dir1.getOpposite()));
            }
        } else {
            if (_dir1 == Direction.LEFT) {
                p1_.setx(leftTopPointOne_.getx());
                p2_.setx(rightBottomPointTwo_.getx());
            }
            if (_dir1 == Direction.RIGHT) {
                p1_.setx(rightBottomPointOne_.getx());
                p2_.setx(leftTopPointTwo_.getx());
            }
            int u_ = Math.min(p1_.gety() - leftTopPointOne_.gety(), p2_.gety()
                    - leftTopPointTwo_.gety());
            int ytop1_ = p1_.gety() - u_;
            int ytop2_ = p2_.gety() - u_;
            int d_ = Math.min(rightBottomPointOne_.gety() - p1_.gety(),
                    rightBottomPointTwo_.gety() - p2_.gety());
            length_ = u_ + d_ + 1;
            for (short i = IndexConstants.FIRST_INDEX; i < length_; i++) {
                keys1_.add(new PlaceInterConnect(new Point(p1_.getx(),
                        (short) (ytop1_ + i)), _dir1));
                keys2_.add(new PlaceInterConnect(new Point(p2_.getx(),
                        (short) (ytop2_ + i)), _dir1.getOpposite()));
            }
        }
        Coords coords1_ = new Coords();
        coords1_.setNumberPlace(_pl2);
        coords1_.setLevel(new LevelPoint());
        coords1_.getLevel().setLevelIndex((byte) 0);
        coords1_.getLevel().setPoint(new Point(p2_));
        ((InitializedPlace) place1_).addSavedLink(new PlaceInterConnect(p1_,
                _dir1), coords1_);
        Coords coords2_ = new Coords();
        coords2_.setNumberPlace(_pl1);
        coords2_.setLevel(new LevelPoint());
        coords2_.getLevel().setLevelIndex((byte) 0);
        coords2_.getLevel().setPoint(new Point(p1_));
        ((InitializedPlace) place2_).addSavedLink(new PlaceInterConnect(p2_,
                _dir1.getOpposite()), coords2_);
        PlaceInterConnects join1_ = new PlaceInterConnects();
        PlaceInterConnects join2_ = new PlaceInterConnects();
        for (short i = IndexConstants.FIRST_INDEX; i < length_; i++) {
            Coords c1_ = new Coords();
            c1_.setNumberPlace(_pl1);
            LevelPoint lp1_ = new LevelPoint();
            lp1_.setLevelIndex((byte) 0);
            lp1_.setPoint(keys1_.get(i).getSource());
            c1_.setLevel(lp1_);
            Coords c2_ = new Coords();
            c2_.setNumberPlace(_pl2);
            LevelPoint lp2_ = new LevelPoint();
            lp2_.setLevelIndex((byte) 0);
            lp2_.setPoint(keys2_.get(i).getSource());
            c2_.setLevel(lp2_);
            join1_.put(keys1_.get(i), c2_);
            join2_.put(keys2_.get(i), c1_);
        }
        ((InitializedPlace) place1_).getPointsWithCitiesAndOtherRoads()
                .putAllMap(join1_);
        ((InitializedPlace) place2_).getPointsWithCitiesAndOtherRoads()
                .putAllMap(join2_);
    }

    public boolean isEmptyForAdding(Coords _coords) {
        Place place_ = places.get(_coords.getNumberPlace());
        if (!place_.isEmptyForAdding(_coords)) {
            return false;
        }
        Point pt_ = _coords.getLevel().getPoint();
        if (_coords.isInside()) {
            Building building_ = ((City) place_).getBuildings().getVal(
                    _coords.getInsideBuilding());
            return !Point.eq(pt_, building_.getExitCity());
        }
        if (place_ instanceof League && filledForAddingInLeague(_coords, (League) place_, pt_)) {
            return false;
        }
        if (place_ instanceof City && (((City) place_).getBuildings().contains(pt_) || ((City) place_).getLinksWithCaves().contains(pt_))) {
            return false;
        }
        if (place_ instanceof Road && ((Road) place_).getLinksWithCaves().contains(pt_)) {
            return false;
        }
        if (place_ instanceof Cave) {
            LevelPoint lPoint_ = _coords.getLevel();
            return !((LevelCave) place_.getLevelsMap()
                    .getVal(lPoint_.getLevelIndex())).getLinksOtherLevels().contains(pt_) && !((Cave) place_).getLinksWithOtherPlaces().contains(lPoint_);
        }
        for (Place p : places) {
            if (p instanceof League && Coords.eq(((League) p).getAccessCoords(), _coords)) {
                return false;
            }
        }
        return true;
    }

    private boolean filledForAddingInLeague(Coords _coords, League _place, Point _pt) {
        LevelPoint lPoint_ = _coords.getLevel();
        LevelLeague levelLeague_ = (LevelLeague) _place
                .getLevelsMap().getVal(lPoint_.getLevelIndex());
        if (lPoint_.getLevelIndex() == IndexConstants.FIRST_INDEX) {
            if (Point.eq(_place.getBegin(), _pt)) {
                return true;
            }
        } else {
            LevelLeague levelLeagueBack_ = (LevelLeague) _place
                    .getLevelsMap().getVal(
                            (byte) (lPoint_.getLevelIndex() - 1));
            if (Point.eq(levelLeagueBack_.getNextLevelTarget(), _pt)) {
                return true;
            }
        }
        return Point.eq(levelLeague_.getAccessPoint(), _pt);
    }

    public void addLeague(String _fileName, Coords _accessCoords) {
        League league_ = new League();
        league_.setFileName(_fileName);
        league_.setAccessCoords(_accessCoords);
        league_.setRooms(new CustList<LevelLeague>());
        league_.setBegin(new Point());
        LevelLeague level_ = levelLeague();
        league_.getRooms().add(level_);
        addPlace(league_);
    }

    public void addLevelLeague(short _league) {
        League league_ = (League) places.get(_league);
        LevelLeague level_ = levelLeague();
        league_.getRooms().add(level_);
    }

    private LevelLeague levelLeague() {
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new PointsBlock());
        level_.setAccessPoint(new Point());
        level_.setNextLevelTarget(new Point());
        level_.setTrainerCoords(new Point());
        TrainerLeague trainer_ = new TrainerLeague();
        trainer_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        trainer_.setImageMiniFileName(DataBase.EMPTY_STRING);
        trainer_.setMultiplicityFight((byte) 1);
        trainer_.setTeam(new CustList<PkTrainer>());
        level_.setTrainer(trainer_);
        return level_;
    }

    public void addCity(String _cityName) {
        City city_ = new City();
        city_.setName(_cityName);
        city_.setBuildings(new PointsBuilding());
        city_.setLinksWithCaves(new PointsLink());
        city_.setSavedlinks(new PlaceInterConnects());
        LevelOutdoor level_ = new LevelOutdoor();
        level_.setBlocks(new PointsBlock());
        city_.setLevel(level_);
        addPlace(city_);
    }

    public void addRoad() {
        Road road_ = new Road();
        road_.setLinksWithCaves(new PointsLink());
        road_.setSavedlinks(new PlaceInterConnects());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        level_.setCharacters(new PointsCharacterInRoadCave());
        level_.setDualFights(new PointsDualFight());
        level_.setHm(new PointsShort());
        level_.setTm(new PointsShort());
        level_.setItems(new PointsString());
        level_.setLegendaryPks(new PointsWildPk());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        road_.setLevel(level_);
        addPlace(road_);
    }

    public void addPlace(Place _place) {
        places.add(_place);
    }

    public void moveCamera(Direction _direction) {
        if (_direction == Direction.DOWN) {
            moveCameraDown(_direction);
        } else if (_direction == Direction.UP) {
            moveCameraUp(_direction);
        } else if (_direction == Direction.RIGHT) {
            moveCameraRight(_direction);
        } else {
            moveCameraLeft(_direction);
        }
    }

    private void moveCameraLeft(Direction _direction) {
        for (int j = IndexConstants.FIRST_INDEX; j < screenHeight; j++) {
            int maxWidth_ = screenWidth - 1;
            for (int i = maxWidth_; i > IndexConstants.FIRST_INDEX; i--) {
                tiles.put(new ScreenCoords(i, j),
                        tiles.getVal(new ScreenCoords(i - 1, j)));
            }

            ScreenCoords key_ = new ScreenCoords(0, j);
            Coords coords_ = tiles.getVal(key_);
            putCoordsIfValid(_direction, key_, coords_);
        }
    }

    private void moveCameraRight(Direction _direction) {
        for (int j = IndexConstants.FIRST_INDEX; j < screenHeight; j++) {
            int maxWidth_ = screenWidth - 1;
            for (int i = IndexConstants.FIRST_INDEX; i < maxWidth_; i++) {
                tiles.put(new ScreenCoords(i, j),
                        tiles.getVal(new ScreenCoords(i + 1, j)));
            }

            ScreenCoords key_ = new ScreenCoords(screenWidth - 1, j);
            Coords coords_ = tiles.getVal(key_);
            putCoordsIfValid(_direction, key_, coords_);
        }
    }

    private void moveCameraUp(Direction _direction) {
        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            int maxHeight_ = screenHeight - 1;
            for (int j = maxHeight_; j > IndexConstants.FIRST_INDEX; j--) {
                tiles.put(new ScreenCoords(i, j),
                        tiles.getVal(new ScreenCoords(i, j - 1)));
            }

            ScreenCoords key_ = new ScreenCoords(i, 0);
            Coords coords_ = tiles.getVal(key_);
            putCoordsIfValid(_direction, key_, coords_);
        }
    }

    private void moveCameraDown(Direction _direction) {
        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            int maxHeight_ = screenHeight - 1;
            for (int j = IndexConstants.FIRST_INDEX; j < maxHeight_; j++) {
                tiles.put(new ScreenCoords(i, j),
                        tiles.getVal(new ScreenCoords(i, j + 1)));
            }

            ScreenCoords key_ = new ScreenCoords(i, screenHeight - 1);
            Coords coords_ = tiles.getVal(key_);
            putCoordsIfValid(_direction, key_, coords_);
        }
    }

    private void putCoordsIfValid(Direction _direction, ScreenCoords _key, Coords _coords) {
        if (_coords.isValid()) {
            tiles.put(_key, closestTile(_coords, _direction));
        } else {
            tiles.put(_key, _coords);
        }
    }

    public void calculateIntersectWithScreen(Coords _coords) {
        tiles = intersectWithScreen(_coords);
    }

    ScreenCoordssCoords intersectWithScreen(Coords _coords) {
        ScreenCoordssCoords liste_ = new ScreenCoordssCoords();

        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < screenHeight; j++) {
                liste_.put(new ScreenCoords(i, j), new Coords());
            }
        }
        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros), _coords);
        return walkTree(liste_);
    }

    public void calculateIntersectWithScreenDirection(Coords _coords) {
        ScreenCoordssCoords liste_ = new ScreenCoordssCoords();

        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < screenHeight; j++) {
                liste_.put(new ScreenCoords(i, j), new Coords());
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            liste_.put(new ScreenCoords(i, -1), new Coords());
        }
        for (int i = IndexConstants.FIRST_INDEX; i < screenWidth; i++) {
            liste_.put(new ScreenCoords(i, screenHeight), new Coords());
        }
        for (int i = IndexConstants.FIRST_INDEX; i < screenHeight; i++) {
            liste_.put(new ScreenCoords(screenWidth, i), new Coords());
        }
        for (int i = IndexConstants.FIRST_INDEX; i < screenHeight; i++) {
            liste_.put(new ScreenCoords(-1, i), new Coords());
        }
        liste_.put(new ScreenCoords(screenWidth, -1), new Coords());
        liste_.put(new ScreenCoords(-1, -1), new Coords());
        liste_.put(new ScreenCoords(-1, screenHeight), new Coords());
        liste_.put(new ScreenCoords(screenWidth, screenHeight), new Coords());

        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros), _coords);
        tiles = walkTree(liste_);
    }

    private ScreenCoordssCoords walkTree(ScreenCoordssCoords _liste) {
        ScreenCoordssCoords oldList_ = new ScreenCoordssCoords(_liste);
        CustList<ScreenCoords> currentElements_ = new CustList<ScreenCoords>();
        currentElements_.add(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros));
        while (true) {
            CustList<ScreenCoords> newElements_ = new CustList<ScreenCoords>();
            for (ScreenCoords p : currentElements_) {
                Coords coords_ = _liste.getVal(p);
                if (!coords_.isValid()) {
                    continue;
                }
                for (Direction d : Direction.all()) {
                    ScreenCoords cle_ = new ScreenCoords(d.getx()
                            + p.getXcoords(), d.gety() + p.getYcoords());
                    if (addToList(_liste, oldList_, cle_)) {
                        _liste.put(cle_, closestTile(coords_, d));
                        newElements_.add(cle_);
                    }
                }
            }
            if (newElements_.isEmpty()) {
                break;
            }
            currentElements_ = newElements_;
        }
        return _liste;
    }

    private boolean addToList(ScreenCoordssCoords _liste, ScreenCoordssCoords _oldList, ScreenCoords _cle) {
        return _oldList.contains(_cle) && !_liste.getVal(_cle).isValid();
    }

    public void calculateBackgroundImagesFromTiles(DataBase _data) {
        for (ScreenCoords k : tiles.getKeys()) {
            Coords coords_ = tiles.getVal(k);
            if (!coords_.isValid()) {
                continue;
            }
            Level level_ = getLevelByCoords(coords_);
            Point pt_ = coords_.getLevel().getPoint();
            Block bl_ = currentBlock(coords_);
            ScreenCoords c_ = level_.getScreenCoordsByPoint(pt_);
            String file_ = bl_.getTileFileName();
            int[][] img_ = _data.getImageTile(file_, c_);
            backgroundImages.put(k, img_);
        }
    }

    void setTree(Tree _tree) {
        tree = _tree;
    }

    public Coords closestTile(Link _link) {
        if (!_link.isValidDir()) {
            return new Coords(_link.getCoords());
        }
        return closestTile(_link.getCoords(), _link.getDir());
    }

    public Coords closestTile(Coords _currentCoords, Direction _direction) {
        if (!existLevel(_currentCoords)) {
            return new Coords();
        }
        if (!nextBlock(_currentCoords,_direction).isValid()) {
            Place currentPlace_ = places.get(_currentCoords.getNumberPlace());
            if (currentPlace_ instanceof InitializedPlace && !_currentCoords.isInside()) {
                PlaceInterConnects rc_ = ((InitializedPlace) currentPlace_)
                        .getPointsWithCitiesAndOtherRoads();
                PlaceInterConnect key_ = new PlaceInterConnect(
                        _currentCoords.getLevel().getPoint(), _direction);
                if (rc_.contains(key_)) {
                    return new Coords(rc_.getVal(key_));
                }
            }
            return new Coords();
        }
        return closest(_currentCoords,_direction);
    }

    public Block nextBlock(Coords _currentCoords, Direction _direction) {
        Coords closestCoords_ = closest(_currentCoords, _direction);
        return currentBlock(closestCoords_);
    }

    public Coords closest(Coords _currentCoords, Direction _direction) {
        Coords closestCoords_ = new Coords(_currentCoords);
        Point closestPoint_ = closestCoords_.getLevel().getPoint();
        closestPoint_.moveTo(_direction);
        return closestCoords_;
    }

    public Block currentBlock(Coords _currentCoords) {
        Level currentLevel_ = getLevelByCoords(_currentCoords);
        Coords closestCoords_ = new Coords(_currentCoords);
        Point closestPoint_ = closestCoords_.getLevel().getPoint();
        return currentLevel_.getBlockByPoint(closestPoint_);
    }
    public Level getLevelByCoords(Coords _coords) {
        return getLevelByCoords(getPlaces(),_coords);
    }

    public static Level getLevelByCoords(CustList<Place> _places,Coords _coords) {
        Place pl_ = _places.get(_coords.getNumberPlace());
        return pl_.getLevelByCoords(_coords);
    }

    public CoordssCondition getAccessibility() {
        return accessibility;
    }

    public Condition getCities() {
        return cities;
    }

    public CustList<NbFightCoords> getBeatTrainer() {
        return beatTrainer;
    }

    public Condition getBeatGymLeader() {
        return beatGymLeader;
    }

    public ShortMap< PointEqList> getBeatGymTrainer() {
        return beatGymTrainer;
    }

    public Condition getHostPokemons() {
        return hostPokemons;
    }

    public Condition getTakenPokemon() {
        return takenPokemon;
    }

    public Condition getTakenObjects() {
        return takenObjects;
    }

    public ScreenCoordssCoords getTiles() {
        return tiles;
    }

    public ScreenCoordssInt getBackgroundImages() {
        return backgroundImages;
    }

    public ScreenCoordssCustListInt getForegroundImages() {
        return foregroundImages;
    }

    public Place getPlace(int _place) {
        return places.get(_place);
    }

    public CustList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(CustList< Place> _places) {
        places = _places;
    }

    public CoordsLists getAccessCondition() {
        return accessCondition;
    }

    public void setAccessCondition(
            CoordsLists _accessCondition) {
        accessCondition = _accessCondition;
    }

    public MiniMapCoordsList getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMapCoordsList _miniMap) {
        miniMap = _miniMap;
    }

    public String getUnlockedCity() {
        return unlockedCity;
    }

    public void setUnlockedCity(String _unlockedCity) {
        unlockedCity = _unlockedCity;
    }

    public Coords getBegin() {
        return begin;
    }

    public void setBegin(Coords _begin) {
        begin = _begin;
    }

    public WildPk getFirstPokemon() {
        return firstPokemon;
    }

    public void setFirstPokemon(WildPk _firstPokemon) {
        firstPokemon = _firstPokemon;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int _screenWidth) {
        screenWidth = _screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int _screenHeight) {
        screenHeight = _screenHeight;
    }

    public int getSpaceBetweenLeftAndHeros() {
        return spaceBetweenLeftAndHeros;
    }

    public void setSpaceBetweenLeftAndHeros(int _spaceBetweenLeftAndHeros) {
        spaceBetweenLeftAndHeros = _spaceBetweenLeftAndHeros;
    }

    public int getSpaceBetweenTopAndHeros() {
        return spaceBetweenTopAndHeros;
    }

    public void setSpaceBetweenTopAndHeros(int _spaceBetweenTopAndHeros) {
        spaceBetweenTopAndHeros = _spaceBetweenTopAndHeros;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int _sideLength) {
        sideLength = _sideLength;
    }

    public boolean isError() {
        return error;
    }

}
