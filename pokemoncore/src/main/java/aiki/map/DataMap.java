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
import aiki.map.characters.DualFight;
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
import aiki.map.util.Limits;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.PlaceLevel;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EqList;
import code.util.*;
import code.util.Ints;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;


public final class DataMap {

    private static final String SPACE = " ";

    private ShortMap< Place> places;

    private ObjectMap<Coords, EqList<Coords>> accessCondition;

    private ObjectMap<MiniMapCoords, TileMiniMap> miniMap;

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

    private ObjectMap<Coords, Condition> accessibility = new ObjectMap<Coords, Condition>();

    private EqList<Coords> cities = new EqList<Coords>();

    private EqList<Coords> leagues = new EqList<Coords>();

    private EqList<NbFightCoords> beatTrainer = new EqList<NbFightCoords>();

    private EqList<Coords> beatGymLeader = new EqList<Coords>();

    private ShortMap< EqList<Point>> beatGymTrainer = new ShortMap< EqList<Point>>();

    private EqList<Coords> hostPokemons = new EqList<Coords>();

    private EqList<Coords> takenPokemon = new EqList<Coords>();

    private EqList<Coords> takenObjects = new EqList<Coords>();

    private ObjectMap<PlaceLevel, Ints> wildPokemonBeforeFirstLeague = new ObjectMap<PlaceLevel, Ints>();

    private ObjectMap<ScreenCoords, Coords> tiles = new ObjectMap<ScreenCoords, Coords>();

    private ObjectMap<ScreenCoords, int[][]> backgroundImages = new ObjectMap<ScreenCoords, int[][]>();

    private ObjectMap<ScreenCoords, CustList<int[][]>> foregroundImages = new ObjectMap<ScreenCoords, CustList<int[][]>>();

    public void validate(DataBase _d) {
        if (screenWidth < 0 || screenHeight < 0) {
            error = true;
            return;
        }
        if (spaceBetweenLeftAndHeros <= 0 || spaceBetweenTopAndHeros <= 0) {
            error = true;
            return;
        }
        if (screenWidth <= spaceBetweenLeftAndHeros + 1
                || screenHeight <= spaceBetweenTopAndHeros + 1) {
            error = true;
            return;
        }
        if (sideLength <= 0) {
            error = true;
            return;
        }
        if (places.isEmpty()) {
            error = true;
            return;
        }
        initInteractiveElements();
        firstPokemon.validateAsNpc(_d);

        tree = new Tree();
        tree.initialize(this);
        if (!existCoords(begin)) {
            error = true;
            return;
        }
        Place plBegin_ = places.getVal(begin.getNumberPlace());
        Level lBegin_ = plBegin_.getLevelByCoords(begin);
        if (!lBegin_.isEmptyForAdding(begin.getLevel().getPoint())) {
            error = true;
            return;
        }
        wildPokemonBeforeFirstLeague = new ObjectMap<PlaceLevel, Ints>();
        int nbPlaces_ = places.size();
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            places.getVal(p).validate(_d, tree.getPlace(p));
            if (!(places.getVal(p).validLinks(p, tree))) {
                error = true;
                _d.setError(true);
            }
        }
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            if (places.getVal(p) instanceof InitializedPlace) {
                InitializedPlace place_ = (InitializedPlace) places.getVal(p);
                ObjectMap<Point, Link> links_;
                links_ = place_.getLinksWithCaves();
                for (Point pt_ : links_.getKeys()) {

                    Coords link_ = closestTile(links_.getVal(pt_));
                    if (!tree.isValid(links_.getVal(pt_).getCoords(), true)) {
                        error = true;
                    }
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof Cave)) {
                        error = true;
                        continue;
                    }
                    Cave cave_ = (Cave) t_;
                    LevelPoint lPoint_ = link_.getLevel();
                    if (!cave_.getLinksWithOtherPlaces().contains(lPoint_)) {
                        error = true;
                        continue;
                    }

                    Coords other_ = closestTile(cave_.getLinksWithOtherPlaces()
                            .getVal(lPoint_));
                    Coords current_ = new Coords();
                    current_.setNumberPlace(p);
                    current_.setLevel(new LevelPoint());
                    current_.getLevel().setLevelIndex((byte) 0);
                    current_.getLevel().setPoint(pt_);
                    if (!Coords.eq(other_,current_)) {
                        error = true;
                    }

                }
                continue;
            }
            if (places.getVal(p) instanceof Cave) {
                Cave place_ = (Cave) places.getVal(p);
                ObjectMap<LevelPoint, Link> links_;
                links_ = place_.getLinksWithOtherPlaces();
                for (LevelPoint l : links_.getKeys()) {

                    Coords link_ = closestTile(links_.getVal(l));
                    if (!tree.isValid(links_.getVal(l).getCoords(), true)) {
                        error = true;
                    }
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof InitializedPlace)) {
                        error = true;
                        continue;
                    }
                    InitializedPlace cave_ = (InitializedPlace) t_;
                    Point point_ = link_.getLevel().getPoint();
                    if (!cave_.getLinksWithCaves().contains(point_)) {
                        error = true;
                        continue;
                    }

                    Coords other_ = closestTile(cave_.getLinksWithCaves()
                            .getVal(point_));
                    Coords current_ = new Coords();
                    current_.setNumberPlace(p);
                    current_.setLevel(l);
                    if (!Coords.eq(other_,current_)) {
                        error = true;
                    }

                }
            }
        }
        for (Coords c : accessCondition.getKeys()) {
            EqList<Coords> invalidCoords_ = new EqList<Coords>();
            EqList<Coords> addedCoords_ = new EqList<Coords>();
            for (Coords c2_ : accessCondition.getVal(c)) {
                Place pl_ = places.getVal(c2_.getNumberPlace());
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
        for (Coords c : accessCondition.getKeys()) {
            accessCondition.getVal(c).removeDuplicates();
        }
        for (Coords c : accessCondition.getKeys()) {
            if (accessCondition.getVal(c).isEmpty()) {
                accessCondition.removeKey(c);
            } else if (c.isInside()) {
                accessCondition.removeKey(c);
            } else {
                Place p_ = places.getVal(c.getNumberPlace());
                if (p_ instanceof League) {
                    accessCondition.removeKey(c);
                }
            }
        }
        for (Place p : places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            League league_ = (League) p;
            if (!accessCondition.contains(league_.getAccessCoords())) {
                error = true;
            }
        }
        for (Coords c : accessCondition.getKeys()) {
            for (Coords c2_ : accessCondition.getVal(c)) {
                if (!tree.isValid(c2_, true)) {
                    error = true;
                }
            }
        }
        initializeAccessibility();
        Condition coords_ = new Condition();
        if (!leagues.isEmpty()) {
            League firstLeague_ = (League) places.getVal(leagues.first()
                    .getNumberPlace());
            coords_ = accessibility
                    .getVal(firstLeague_.getAccessCoords());
        }
        StringList evoObjects_ = new StringList();
        StringList movesTmHm_ = new StringList();
        boolean moveTutor_ = false;
        boolean ball_ = false;
        for (Coords c : accessibility.getKeys()) {
            if (!coords_.containsAllObj(accessibility.getVal(c))) {
                continue;
            }
            Place place_ = places.getVal(c.getNumberPlace());
            Level l_ = place_.getLevelByCoords(c);
            if (accessibility.getVal(c).isEmpty()) {
                if (l_ instanceof LevelIndoorPokemonCenter) {
                    for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                            .values()) {
                        if (p instanceof Seller) {
                            if (StringList.contains(((Seller) p).getItems(), _d.getDefaultBall())) {
                                ball_ = true;
                            }
                            if (((Seller) p).getSell() == SellType.MOVE) {
                                moveTutor_ = true;
                            }
                        }
                    }
                }
            }
            if (l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p : ((LevelIndoorPokemonCenter) l_).getGerants()
                        .values()) {
                    if (p instanceof Seller) {
                        if (((Seller) p).getSell() == SellType.ITEM) {
                            evoObjects_.addAllElts(((Seller) p).getItems());
                        }
                        for (short s : ((Seller) p).getTm()) {
                            movesTmHm_.add(_d.getTm().getVal(s));
                        }
                    }
                }
            }
            PlaceArea pl_ = tree.getPlace(c.getNumberPlace());
            LevelArea level_ = pl_.getLevel(c.getLevel().getLevelIndex());
            PlaceLevel keyPlaceLevel_ = new PlaceLevel(c.getNumberPlace(), c
                    .getLevel().getLevelIndex());
            int index_ = level_.getIndex(c.getLevel().getPoint());
            if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
                continue;
            }
            if (wildPokemonBeforeFirstLeague.contains(keyPlaceLevel_)) {
                wildPokemonBeforeFirstLeague.getVal(keyPlaceLevel_).add(index_);
                wildPokemonBeforeFirstLeague.getVal(keyPlaceLevel_)
                        .removeDuplicates();
            } else {
                wildPokemonBeforeFirstLeague.put(keyPlaceLevel_,
                        new Ints(index_));
            }
        }
        if (!ball_) {
            error = true;
        }
        if (!moveTutor_) {
            error = true;
        }

        evoObjects_.removeDuplicates();
        for (String o : _d.getItems().getKeys()) {
            Item o_ = _d.getItems().getVal(o);
            if (o_ instanceof EvolvingStone) {
                if (!StringList.contains(evoObjects_, o)) {
                    error = true;
                }
            }
            if (o_ instanceof EvolvingItem) {
                if (!StringList.contains(evoObjects_, o)) {
                    error = true;
                }
            }
        }
        for (String p : _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(p);
            StringList moves_ = new StringList();
            for (Evolution e : pk_.getEvolutions().values()) {
                if (!(e instanceof EvolutionMove)) {
                    continue;
                }
                moves_.add(((EvolutionMove) e).getMove());
            }
            if (moves_.isEmpty()) {
                continue;
            }
            moves_.removeDuplicates();
            StringList movesRetr_ = new StringList();
            for (String m : pk_.getMoveTutors()) {
                movesRetr_.add(m);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                movesRetr_.add(l.getMove());
            }
            movesRetr_.addAllElts(movesTmHm_);
            for (String m : moves_) {
                if (!StringList.contains(movesRetr_, m)) {
                    error = true;
                }
            }
        }
        StringList availableTypesTm_ = new StringList();
        for (String m : movesTmHm_) {
            availableTypesTm_.addAllElts(_d.getMove(m).getTypes());
        }
        availableTypesTm_.removeDuplicates();
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
            types_.removeDuplicates();
            StringList typesRetr_ = new StringList();
            for (String m : pk_.getMoveTutors()) {
                typesRetr_.addAllElts(_d.getMove(m).getTypes());
            }
            for (LevelMove l : pk_.getLevMoves()) {
                typesRetr_.addAllElts(_d.getMove(l.getMove()).getTypes());
            }
            typesRetr_.addAllElts(availableTypesTm_);
            if (!typesRetr_.containsAllObj(types_)) {
                error = true;
            }
        }
        StringMap<EnumList<Gender>> directCatchPk_ = new StringMap<EnumList<Gender>>();
        for (short p_ : places.getKeys()) {
            Place pl_ = places.getVal(p_);
            for (byte l : pl_.getLevelsMap().getKeys()) {
                Level level_ = pl_.getLevelsMap().getVal(l);
                if (!(level_ instanceof LevelWithWildPokemon)) {
                    continue;
                }
                LevelWithWildPokemon levelWild_ = (LevelWithWildPokemon) level_;
                PlaceLevel keyPlaceLevel_ = new PlaceLevel(p_, l);
                if (!wildPokemonBeforeFirstLeague.contains(keyPlaceLevel_)) {
                    continue;
                }
                Ints levelPokemon_;
                levelPokemon_ = wildPokemonBeforeFirstLeague
                        .getVal(keyPlaceLevel_);
                for (int index_ : levelPokemon_) {
                    CustList<AreaApparition> wildPokemonAreas_ = levelWild_.getWildPokemonAreas();
                    if (!wildPokemonAreas_.isValidIndex(index_)) {
                        continue;
                    }
                    AreaApparition areaApparition_ = wildPokemonAreas_
                            .get(index_);
                    EqList<WildPk> wildPokemon_ = areaApparition_.getWildPokemon();
                    feedDirectCatch(directCatchPk_, wildPokemon_);
                    EqList<WildPk> wildPokemonFishing_ = areaApparition_.getWildPokemonFishing();
                    feedDirectCatch(directCatchPk_, wildPokemonFishing_);
                }
            }
        }

        StringList baseEvos_ = new StringList();
        for (String n : _d.getPokedex().getKeys()) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (fPk_.getGenderRep() == GenderRepartition.LEGENDARY) {
                continue;
            }
            baseEvos_.add(fPk_.getBaseEvo());
        }
        baseEvos_.removeDuplicates();
        if (!directCatchPk_.containsAllAsKeys(baseEvos_)) {
            error = true;
        }
        for (String n : baseEvos_) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (!directCatchPk_.getVal(n).containsAllObj(
                    fPk_.getGenderRep().getPossibleGenders())) {
                error = true;
            }
        }
        boolean existPkDefaultEgg_ = false;
        for (String n : _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(n);
            if (pk_.getGenderRep() != GenderRepartition.NO_GENDER) {
                continue;
            }
            if (StringList.contains(pk_.getEggGroups(), _d.getDefaultEggGroup())) {
                existPkDefaultEgg_ = true;
                break;
            }
        }
        if (!existPkDefaultEgg_) {
            error = true;
        }
        StringList legPk_ = new StringList();
        for (String n : _d.getPokedex().getKeys()) {
            if (!StringList.contains(_d.getLegPks(),n)) {
                continue;
            }
            legPk_.add(n);
        }
        StringList wildPk_ = new StringList();
        for (Coords c : accessibility.getKeys()) {
            Place pl_ = places.getVal(c.getNumberPlace());
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
        wildPk_.removeDuplicates();
        if (!wildPk_.containsAllObj(legPk_)) {
            error = true;
        }
        int maxWidth_ = 0;
        int maxHeight_ = 0;
        CustList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m : list_) {
            if (m.getXcoords() < 0) {
                error = true;
            }
            if (m.getYcoords() < 0) {
                error = true;
            }
            maxWidth_ = Math.max(maxWidth_,m.getXcoords());
            maxHeight_ = Math.max(maxHeight_,m.getYcoords());
        }
        Shorts placesMiniMap_ = new Shorts();
        for (MiniMapCoords m : list_) {
            TileMiniMap tile_ = miniMap.getVal(m);
            if (Numbers.eq(tile_.getPlace(), CustList.INDEX_NOT_FOUND_ELT)) {
                continue;
            }
            placesMiniMap_.add(tile_.getPlace());
        }
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
        for (MiniMapCoords m : list_) {
            TileMiniMap tile_ = miniMap.getVal(m);
            int[][] image_ = _d.getMiniMap(tile_.getFile());
            if (Numbers.eq(tile_.getPlace(), CustList.INDEX_NOT_FOUND_ELT)) {
                imagesOutside_.add(image_);
                continue;
            }
            Place pl_ = places.getVal(tile_.getPlace());
            if (pl_ instanceof City) {
                imagesCities_.add(image_);
                continue;
            }
            if (pl_ instanceof Road) {
                imagesRoads_.add(image_);
                continue;
            }
            if (pl_ instanceof Cave) {
                imagesCaves_.add(image_);
                continue;
            }
            if (pl_ instanceof League) {
                imagesLeagues_.add(image_);
            }
        }
        CustList<CustList<int[][]>> images_ = new CustList<CustList<int[][]>>();
        images_.add(imagesOutside_);
        images_.add(imagesCities_);
        images_.add(imagesRoads_);
        images_.add(imagesCaves_);
        images_.add(imagesLeagues_);
        images_.add(imageUnlockedCity_);
        int size_ = images_.size();
        for (int i = CustList.FIRST_INDEX; i < size_; i++) {
            for (int j = CustList.FIRST_INDEX; j < size_; j++) {
                if (i == j) {
                    continue;
                }
                for (int[][] k : images_.get(i)) {
                    int height_ = k.length;
                    if (height_ == 0) {
                        continue;
                    }
                    int width_ = k[0].length;
                    for (int[][] l : images_.get(j)) {
                        if (height_ != l.length) {
                            continue;
                        }
                        if (width_ != l[0].length) {
                            continue;
                        }
                        boolean eq_ = true;
                        for (int m = 0; m < height_; m++) {
                            for (int n = 0; n < width_; n++) {
                                if (l[m][n] != k[m][n]) {
                                    eq_ = false;
                                    break;
                                }
                            }
                            if (!eq_) {
                                break;
                            }
                        }
                        if (eq_) {
                            error = true;
                        }
                    }
                }
            }
        }
        if (!Numbers.equalsSetShorts(placesMiniMap_, places.getKeys())) {
            error = true;
        }
        if (list_.size() != (maxWidth_ + 1) * (maxHeight_ + 1)) {
            error = true;
        }
        if (_d.getMiniMap(getUnlockedCity()).length == 0) {
            error = true;
        }
        boolean firstCities_ = false;
        for (Coords c : cities) {
            if (accessibility.contains(c)) {
                if (accessibility.getVal(c).isEmpty()) {
                    firstCities_ = true;
                    break;
                }
            } else {
                error = true;
            }
        }
        if (!firstCities_) {
            error = true;
        }
        for (Coords c : beatGymLeader) {
            if (c.isInside()) {
                Coords coordsExt_ = new Coords();
                coordsExt_.setNumberPlace(c.getNumberPlace());
                coordsExt_.setLevel(new LevelPoint());
                Point exitBuilding_ = new Point(c.getInsideBuilding());
                exitBuilding_.moveTo(Direction.DOWN);
                coordsExt_.getLevel().setPoint(exitBuilding_);
                boolean existAccess_ = existAccess(coordsExt_);
                if (!existAccess_) {
                    error = true;
                }
                continue;
            }
            Place plVal_ = places.getVal(c.getNumberPlace());
            if (plVal_ instanceof League) {
                League league_ = (League) plVal_;
                Coords accessLeague_ = league_.getAccessCoords();
                boolean existAccess_ = existAccess(accessLeague_);
                if (!existAccess_) {
                    error = true;
                }
                continue;
            }
            boolean existAccess_ = existAccess(c);
            if (!existAccess_) {
                error = true;
            }
        }
        for (NbFightCoords c : beatTrainer) {
            Coords fightAccess_ = c.getCoords();
            boolean existAccess_ = existAccess(fightAccess_);
            if (!existAccess_) {
                error = true;
            }
        }
    }

    void feedDirectCatch(StringMap<EnumList<Gender>> _directCatchPk, EqList<WildPk> _wildPokemon) {
        for (WildPk p : _wildPokemon) {
            if (!_directCatchPk.contains(p.getName())) {
                _directCatchPk.put(p.getName(),
                        new EnumList<Gender>(p.getGender()));
            } else {
                _directCatchPk.getVal(p.getName()).add(
                        p.getGender());
                _directCatchPk.getVal(p.getName())
                        .removeDuplicates();
            }
        }
    }

    boolean existAccess(Coords _coords) {
        boolean existAccess_ = false;
        for (Direction d : Direction.values()) {
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
        Place plBegin_ = places.getVal(_c.getNumberPlace());
        boolean correctCoords_;
        if (_c.isInside()) {
            Point bIncome_ = _c.getInsideBuilding();
            Level lev_ = ((City) plBegin_).getBuildings().getVal(bIncome_).getLevel();
            correctCoords_ = checkLevel(lev_,_c);
        } else {
            Level curLevel_ = plBegin_.getLevelsList().get(_c.getLevel().getLevelIndex());
            correctCoords_ = checkLevel(curLevel_,_c);
        }
        return correctCoords_;
    }

    public boolean existLevel(Coords _c) {
        Place plBegin_ = places.getVal(_c.getNumberPlace());
        if (plBegin_ == null) {
            return false;
        }
        boolean correctCoords_;
        if (_c.isInside()) {
            if (plBegin_ instanceof City) {
                Point bIncome_ = _c.getInsideBuilding();
                if (!((City)plBegin_).getBuildings().contains(bIncome_)) {
                    correctCoords_ = false;
                } else {
                    correctCoords_ = true;
                }
            } else {
                correctCoords_ = false;
            }
        } else {
            if (!plBegin_.getLevelsList().isValidIndex(_c.getLevel().getLevelIndex())) {
                correctCoords_ = false;
            } else {
                correctCoords_ = true;
            }
        }
        return correctCoords_;
    }
    private static boolean checkLevel(Level _l, Coords _c) {
        boolean correctCoords_ = true;
        if (!_l.getEnvBlockByPoint(_c.getLevel().getPoint()).isValid()) {
            correctCoords_ = false;
        }
        return correctCoords_;
    }
    public AreaApparition getAreaByCoords(Coords _coords) {
        if (!_coords.isValid()) {
            return new AreaApparition();
        }
        Place pl_ = places.getVal(_coords.getNumberPlace());
        Level l_ = pl_.getLevelByCoords(_coords);
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
        beatTrainer = new EqList<NbFightCoords>();
        beatGymLeader = new EqList<Coords>();
        beatGymTrainer = new ShortMap< EqList<Point>>();
        hostPokemons = new EqList<Coords>();
        takenPokemon = new EqList<Coords>();
        takenObjects = new EqList<Coords>();
        int nbPlaces_ = places.size();
        for (short s = CustList.FIRST_INDEX; s < nbPlaces_; s++) {
            Place place_ = places.getVal(s);
            if (place_ instanceof City) {
                for (EntryCust<Point, Building> b : ((City) place_)
                        .getBuildings().entryList()) {
                    if (b.getValue() instanceof Gym) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.affectInside(b.getKey());
                        c_.getLevel().setLevelIndex((byte) 0);
                        c_.getLevel().setPoint(
                                ((Gym) b.getValue()).getIndoor()
                                        .getGymLeaderCoords());
                        beatGymLeader.add(c_);
                        beatGymTrainer
                                .put(s, new EqList<Point>(((Gym) b.getValue())
                                        .getIndoor().getGymTrainers().getKeys()));
                        break;
                    }
                }
                for (EntryCust<Point, Building> b : ((City) place_)
                        .getBuildings().entryList()) {
                    if (b.getValue() instanceof PokemonCenter) {
                        Coords coordsCity_ = new Coords();
                        coordsCity_.setNumberPlace(s);
                        coordsCity_.setLevel(new LevelPoint());
                        coordsCity_.getLevel().setLevelIndex((byte) 0);
                        coordsCity_.outside();
                        coordsCity_.getLevel().setPoint(new Point(b.getKey()));
                        coordsCity_.getLevel().getPoint()
                                .moveTo(Direction.DOWN);
                        cities.add(coordsCity_);
                        for (EntryCust<Point, Person> g : ((PokemonCenter) b
                                .getValue()).getIndoor().getGerants()
                                .entryList()) {
                            if (!(g.getValue() instanceof GerantPokemon)) {
                                continue;
                            }
                            if (((GerantPokemon) g.getValue()).getGerance() == GeranceType.HOST) {
                                Coords c_ = new Coords();
                                c_.setNumberPlace(s);
                                c_.setLevel(new LevelPoint());
                                c_.affectInside(b.getKey());
                                c_.getLevel().setLevelIndex((byte) 0);
                                c_.getLevel().setPoint(g.getKey());
                                hostPokemons.add(c_);
                                break;
                            }
                        }
                    }
                }
            }
            if (place_ instanceof Campaign) {
                for (byte k : place_.getLevelsMap().getKeys()) {
                    LevelWithWildPokemon levelCave_ = (LevelWithWildPokemon) place_
                            .getLevelsMap().getVal(k);
                    for (Point p : levelCave_.getLegendaryPks().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenPokemon.add(c_);
                    }
                    for (Point p : levelCave_.getCharacters().getKeys()) {
                        if (!(levelCave_.getCharacters().getVal(p) instanceof DealerItem)) {
                            continue;
                        }
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p : levelCave_.getItems().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p : levelCave_.getTm().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p : levelCave_.getHm().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p : levelCave_.getDualFights().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        beatGymLeader.add(c_);
                    }
                }
            }
            if (place_ instanceof League) {
                Coords c_ = new Coords();
                c_.setNumberPlace(s);
                c_.setLevel(new LevelPoint());
                c_.getLevel().setLevelIndex((byte) 0);
                c_.getLevel().setPoint(((League) place_).getBegin());
                beatGymLeader.add(c_);
            }
            if (place_ instanceof Campaign) {
                ByteMap< Level> levels_ = place_
                        .getLevelsMap();
                for (EntryCust<Byte, Level> e : levels_.entryList()) {
                    LevelWithWildPokemon wild_ = (LevelWithWildPokemon) e
                            .getValue();
                    for (EntryCust<Point, CharacterInRoadCave> c : wild_
                            .getCharacters().entryList()) {
                        if (!(c.getValue() instanceof TrainerMultiFights)) {
                            continue;
                        }
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(e.getKey());
                        c_.getLevel().setPoint(c.getKey());
                        TrainerMultiFights tr_ = (TrainerMultiFights) c
                                .getValue();
                        int nb_ = tr_.getTeamsRewards().size();
                        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                            beatTrainer.add(new NbFightCoords(c_, i));
                        }
                    }
                }
            }
        }
    }

    public void initializeWildPokemon() {
        for (Place p : places.values()) {
            if (p instanceof Campaign) {
                ((Campaign) p).initializeWildPokemon();
            }
        }
    }

    public TreeMap<MiniMapCoords, int[][]> getImages(DataBase _data) {
        TreeMap<MiniMapCoords, int[][]> map_ = new TreeMap<MiniMapCoords, int[][]>(
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
                if (Numbers.eq(c.getNumberPlace(), place_)) {
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
            if (!Numbers.eq(place_, CustList.INDEX_NOT_FOUND_ELT)) {
                return places.getVal(place_).getName();
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
        EqList<Coords> leaders_ = new EqList<Coords>();
        ObjectMap<Coords, Condition> newElts_ = new ObjectMap<Coords, Condition>();
        newElts_.put(new Coords(begin), new Condition());
        ObjectMap<Coords, Condition> coordsCond_ = new ObjectMap<Coords, Condition>(
                newElts_);
        ObjectMap<Coords, Condition> coordsCondBis_ = new ObjectMap<Coords, Condition>(
                newElts_);
        ObjectMap<Coords, Condition> allTiles_ = new ObjectMap<Coords, Condition>(
                newElts_);
        leagues = new EqList<Coords>();
        while (true) {
            ObjectMap<Coords, Condition> neigh_ = possibleNeighbours(allTiles_,
                    coordsCond_);
            CustList<Coords> diff_ = neigh_.getKeys();
            if (diff_.isEmpty()) {
                break;
            }
            for (EntryCust<Coords, Condition> e : neigh_.entryList()) {
                Coords c_ = e.getKey();
                if (allTiles_.contains(c_)) {
                    continue;
                }
                Condition cond_ = e.getValue();
                if (!accessCondition.contains(c_)) {
                    continue;
                }
                coordsCondBis_.put(c_, cond_);
            }
            EqList<Coords> inext_ = new EqList<Coords>();
            EqList<Coords> ext_ = new EqList<Coords>();
            for (EntryCust<Coords, Condition> e : neigh_.entryList()) {
                Coords c_ = e.getKey();
                if (accessCondition.contains(c_)
                        && !coordsCond_.contains(c_)) {
                    inext_.add(c_);
                    continue;
                }
                ext_.add(c_);
                allTiles_.put(c_, e.getValue());
            }
            if (!validConditions(inext_, neigh_)) {
                error = true;
                return;
            }
            diff_ = ext_;

            ObjectMap<Coords, Coords> newLeaders_ = leaders(diff_);
            CustList<Coords> accessibleLeaders_ = newLeaders_.getKeys();
            for (Coords c : coordsCondBis_.getKeys()) {
                if (allTiles_.contains(c)) {
                    continue;
                }
                for (Coords a : newLeaders_.getKeys()) {
                    if (!accessCondition.getVal(c).containsObj(a)) {
                        continue;
                    }
                    Coords c_ = newLeaders_.getVal(a);
                    coordsCondBis_.getVal(c).addAllElts(allTiles_.getVal(c_));

                }
                coordsCondBis_.getVal(c).removeDuplicates();
            }
            for (Coords c : accessibleLeaders_) {
                Place pl_ = places.getVal(c.getNumberPlace());
                if (!(pl_ instanceof League)) {
                    continue;
                }
                leagues.add(c);
            }
            leaders_.addAllElts(accessibleLeaders_);
            coordsCond_.clear();
            Condition initCond_ = new Condition();
            for (EntryCust<Coords, Condition> e : coordsCondBis_.entryList()) {
                Coords c_ = e.getKey();
                if (!accessCondition.contains(c_)) {
                    continue;
                }
                if (allTiles_.contains(c_)) {
                    continue;
                }
                Condition cond_ = e.getValue();
                if (initCond_.isEmpty() && !cond_.exists(leaders_)) {
                    initCond_ = cond_;
                } else {
                    if (!Coords.equalsSet(cond_, initCond_)) {
                        continue;
                    }
                }
                coordsCond_.put(c_, cond_);
            }
        }
        leagues.removeDuplicates();
        accessibility = allTiles_;

        EqList<Coords> accessLeagues_ = new EqList<Coords>();
        for (short p : places.getKeys()) {
            Place pl_ = places.getVal(p);
            if (pl_ instanceof League) {
                League l_ = (League) pl_;
                accessLeagues_.add(l_.getAccessCoords());
            }
        }
        for (Coords c : accessCondition.getKeys()) {
            if (!accessibility.contains(c)) {
                continue;
            }
            if (accessLeagues_.containsObj(c)) {
                continue;
            }
            ObjectMap<Coords, Condition> conditions_ = getNext(c,
                    accessibility.getVal(c));
            boolean contained_ = false;
            EqList<Coords> elts_ = accessCondition.getVal(c);
            for (Coords n : conditions_.getKeys()) {
                if (accessCondition.contains(n)
                        && !accessLeagues_.containsObj(n)) {
                    EqList<Coords> condLoc_ = accessCondition.getVal(n);
                    if (Coords.equalsSet(condLoc_, elts_)) {
                        continue;
                    }
                }
                Condition condition_ = accessibility.getVal(n);
                for (Coords a : condition_) {
                    if (elts_.containsObj(a)) {
                        contained_ = true;
                    }
                }
            }
            if (!contained_) {
                error = true;
                return;
            }
        }

    }

    public ObjectMap<Coords, Condition> possibleNeighbours(
            ObjectMap<Coords, Condition> _visitedGl,
            ObjectMap<Coords, Condition> _previousVisited) {
        ObjectMap<Coords, Condition> visitedTiles_ = new ObjectMap<Coords, Condition>(
                _previousVisited);
        CustList<Coords> currentTiles_ = _previousVisited.getKeys();
        EqList<Coords> newPlaces_ = new EqList<Coords>();
        while (true) {
            for (Coords i : currentTiles_) {
                if (accessCondition.contains(i)) {
                    if (!_previousVisited.contains(i)) {
                        continue;
                    }
                }
                ObjectMap<Coords, Condition> neighbours_ = getNext(i,
                        visitedTiles_.getVal(i));
                for (EntryCust<Coords, Condition> e : neighbours_.entryList()) {
                    Coords n_ = e.getKey();
                    if (visitedTiles_.contains(n_)) {
                        continue;
                    }
                    if (_visitedGl.contains(n_)) {
                        continue;
                    }
                    visitedTiles_.put(n_, e.getValue());
                    newPlaces_.add(n_);
                }
            }
            if (newPlaces_.isEmpty()) {
                break;
            }
            currentTiles_ = new EqList<Coords>(newPlaces_);
            newPlaces_ = new EqList<Coords>();
        }
        return visitedTiles_;
    }

    public ObjectMap<Coords, Coords> leaders(Listable<Coords> _accessibleCoords) {
        ObjectMap<Coords, Coords> list_ = new ObjectMap<Coords, Coords>();
        for (Coords c : _accessibleCoords) {
            Place place_ = getPlaces().getVal(c.getNumberPlace());
            if (place_ instanceof League) {
                League league_ = (League) place_;
                byte ind_ = c.getLevel().getLevelIndex();
                if (Numbers.eq(ind_ + 1, league_.getRooms().size())) {
                    Coords coords_ = new Coords(c);

                    coords_.getLevel().setLevelIndex((byte) 0);
                    coords_.getLevel().setPoint(new Point(league_.getBegin()));
                    list_.put(coords_, league_.getAccessCoords());
                }
                continue;
            }
            Level l_ = getPlaces().getVal(c.getNumberPlace()).getLevelByCoords(
                    c);
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon) l_).getDualFights().contains(
                        c.getLevel().getPoint())) {
                    list_.put(c, c);
                }
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

    public ObjectMap<Coords, Condition> getNext(Coords _id, Condition _condition) {
        ObjectMap<Coords, Condition> return_ = new ObjectMap<Coords, Condition>();
        Place place_ = places.getVal(_id.getNumberPlace());
        Point pt_ = _id.getLevel().getPoint();
        for (EntryCust<Short, Place> e : places.entryList()) {
            Place pl_ = e.getValue();
            if (!(pl_ instanceof League)) {
                continue;
            }
            if (Coords.eq(((League) pl_).getAccessCoords(), _id)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(e.getKey());
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) 0);
                coords_.getLevel().setPoint(
                        new Point(((League) pl_).getBegin()));
                Condition condition_ = initCondition(coords_, _condition);
                return_.put(coords_, condition_);
                break;
            }
        }
        if (place_ instanceof InitializedPlace) {
            InitializedPlace pl_ = (InitializedPlace) place_;
            if (pl_.getLinksWithCaves().contains(pt_)) {
                Link link_ = pl_.getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_, _condition);
                return_.put(coords_, cond_);
            }
            ObjectMap<PlaceInterConnect, Coords> links_ = pl_
                    .getPointsWithCitiesAndOtherRoads();
            if (!_id.isInside()) {
                Level level_ = place_.getLevelByCoords(_id);
                for (Direction d : Direction.values()) {
                    Point ptNext_ = new Point(pt_);
                    ptNext_.moveTo(d);
                    if (!level_.isEmpty(ptNext_)) {
                        continue;
                    }
                    Block block_ = level_.getBlockByPoint(ptNext_);
                    if (!block_.isValid()) {
                        if (links_.contains(new PlaceInterConnect(pt_, d))) {
                            Coords coords_ = links_
                                    .getVal(new PlaceInterConnect(pt_, d));
                            InitializedPlace plNext_ = (InitializedPlace) places
                                    .getVal(coords_.getNumberPlace());
                            Level levelNext_ = plNext_.getLevel();
                            Point newPoint_ = coords_.getLevel().getPoint();
                            if (levelNext_.getEnvBlockByPoint(newPoint_)
                                    .isValid()) {
                                Condition cond_ = initCondition(coords_,
                                        _condition);
                                return_.put(coords_, cond_);
                            }
                        }
                        continue;
                    }
                    if (block_.getType() == EnvironmentType.NOTHING) {
                        if (pl_ instanceof City) {
                            if (((City) pl_).getBuildings().contains(ptNext_)) {
                                Building building_ = ((City) pl_)
                                        .getBuildings().getVal(ptNext_);
                                Coords coords_ = new Coords(_id);
                                coords_.affectInside(ptNext_);
                                coords_.getLevel().setPoint(
                                        building_.getExitCity());
                                Condition cond_ = initCondition(coords_,
                                        _condition);
                                return_.put(coords_, cond_);
                                continue;
                            }
                        }
                        if (pl_.getLinksWithCaves().contains(ptNext_)) {
                            Coords coords_ = new Coords(_id);
                            coords_.getLevel().setPoint(ptNext_);
                            Condition cond_ = initCondition(coords_, _condition);
                            return_.put(coords_, cond_);
                        }
                        continue;
                    }
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setPoint(ptNext_);
                    Condition cond_ = initCondition(coords_, _condition);
                    return_.put(coords_, cond_);
                }
            }
        } else if (place_ instanceof Cave) {
            Cave cave_ = (Cave) place_;
            if (cave_.getLinksWithOtherPlaces().contains(_id.getLevel())) {
                Link link_ = cave_.getLinksWithOtherPlaces().getVal(
                        _id.getLevel());
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_, _condition);
                return_.put(coords_, cond_);
            }
            LevelCave level_ = (LevelCave) cave_.getLevelsMap().getVal(
                    _id.getLevel().getLevelIndex());
            if (level_.getLinksOtherLevels()
                    .contains(_id.getLevel().getPoint())) {
                Link link_ = level_.getLinksOtherLevels().getVal(
                        _id.getLevel().getPoint());
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_, _condition);
                return_.put(coords_, cond_);
            }
            for (Direction d : Direction.values()) {
                Point ptNext_ = new Point(pt_);
                ptNext_.moveTo(d);
                if (!level_.isEmpty(ptNext_)) {
                    continue;
                }
                Block block_ = level_.getBlockByPoint(ptNext_);
                if (block_.isValid()) {
                    if (block_.getType() == EnvironmentType.NOTHING) {
                        Coords coords_ = new Coords(_id);
                        coords_.getLevel().setPoint(ptNext_);
                        if (cave_.getLinksWithOtherPlaces().contains(
                                coords_.getLevel())) {
                            Condition cond_ = initCondition(coords_, _condition);
                            return_.put(coords_, cond_);
                        }
                        continue;
                    }
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setPoint(ptNext_);
                    Condition cond_ = initCondition(coords_, _condition);
                    return_.put(coords_, cond_);
                }
            }
        } else {

            League league_ = (League) place_;
            byte levelIndex_ = _id.getLevel().getLevelIndex();
            LevelLeague level_ = league_.getRooms().get(levelIndex_);
            if (Point.eq(level_.getAccessPoint(), _id.getLevel().getPoint())) {
                levelIndex_++;
                if (league_.getRooms().isValidIndex(levelIndex_)) {
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setLevelIndex(levelIndex_);
                    coords_.getLevel().setPoint(level_.getNextLevelTarget());
                    Condition cond_ = initCondition(coords_, _condition);
                    return_.put(coords_, cond_);
                }
            }
            for (Direction d : Direction.values()) {
                Point ptNext_ = new Point(pt_);
                ptNext_.moveTo(d);
                if (!level_.isEmpty(ptNext_)) {
                    continue;
                }
                if (level_.getEnvBlockByPoint(ptNext_).isValid()) {
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setPoint(ptNext_);
                    Condition cond_ = initCondition(coords_, _condition);
                    return_.put(coords_, cond_);
                }
            }
        }
        return return_;
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

    boolean validConditions(EqList<Coords> _accessCoords,
            ObjectMap<Coords, Condition> _condition) {
        ObjectMap<Coords, EqList<Coords>> groups_ = new ObjectMap<Coords, EqList<Coords>>();
        Condition defaultCondition_ = new Condition();
        for (Coords c : _accessCoords) {
            boolean continue_ = false;
            for (EqList<Coords> l : groups_.values()) {
                if (l.containsObj(c)) {
                    continue_ = true;
                    break;
                }
            }
            if (continue_) {
                continue;
            }
            EqList<Coords> eq_ = new EqList<Coords>();
            eq_.add(c);
            EqList<Coords> currentElts_ = new EqList<Coords>(eq_);
            EqList<Coords> newElts_ = new EqList<Coords>();
            while (true) {
                for (Coords c2_ : currentElts_) {
                    ObjectMap<Coords, Condition> next_ = getNext(c2_,
                            defaultCondition_);
                    for (Coords n : next_.getKeys()) {
                        if (!_accessCoords.containsObj(n)) {
                            continue;
                        }
                        if (eq_.containsObj(n)) {
                            continue;
                        }
                        newElts_.add(n);
                        eq_.add(n);
                    }
                }
                if (newElts_.isEmpty()) {
                    break;
                }
                currentElts_ = new EqList<Coords>(newElts_);
                newElts_ = new EqList<Coords>();
            }
            groups_.put(c, eq_);
        }
        for (EntryCust<Coords, EqList<Coords>> e : groups_.entryList()) {
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

    public String getTrainerName(Coords _coords) {
        Place pl_ = places.getVal(_coords.getNumberPlace());
        Level level_ = pl_.getLevelByCoords(_coords);
        if (level_ instanceof LevelIndoorGym) {
            LevelIndoorGym g_ = (LevelIndoorGym) level_;
            return g_.getGymLeader().getName();
        }
        if (level_ instanceof LevelLeague) {
            League league_ = (League) pl_;
            LevelLeague l_ = league_.getRooms().last();
            return l_.getTrainer().getName();
        }
        if (level_ instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
            if (w_.getDualFights().contains(_coords.getLevel().getPoint())) {
                return StringList.join(w_.getDualFights().getVal(_coords.getLevel().getPoint())
                        .getNames(), SPACE);
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public boolean validSavedLink() {
        int nbPlaces_ = places.size();
        for (short i = CustList.FIRST_INDEX; i < nbPlaces_; i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            Level level_ = pl_.getLevel();
            Limits limits_ = level_.limits();
            Point leftTopPoint_ = limits_.getTopLeft();
            Point rightBottomPoint_ = limits_.getBottomRight();
            for (PlaceInterConnect k : pl_.getSavedlinks().getKeys()) {
                if (k.getDir() == Direction.UP) {
                    if (!Numbers.eq(leftTopPoint_.gety(), k.getSource().gety())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.DOWN) {
                    if (!Numbers.eq(rightBottomPoint_.gety(), k.getSource()
                            .gety())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.LEFT) {
                    if (!Numbers.eq(leftTopPoint_.getx(), k.getSource().getx())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.RIGHT) {
                    if (!Numbers.eq(rightBottomPoint_.getx(), k.getSource()
                            .getx())) {
                        return false;
                    }
                }
                Coords coords_ = pl_.getSavedlinks().getVal(k);
                InitializedPlace other_ = (InitializedPlace) places
                        .getVal(coords_.getNumberPlace());
                PlaceInterConnect key_ = new PlaceInterConnect(coords_
                        .getLevel().getPoint(), k.getDir().getOpposite());
                if (!other_.getSavedlinks().contains(key_)) {
                    return false;
                }
                Coords otherCoords_ = other_.getSavedlinks().getVal(key_);
                if (!Numbers.eq(otherCoords_.getNumberPlace(), i)) {
                    return false;
                }
                if (!Point
                        .eq(k.getSource(), otherCoords_.getLevel().getPoint())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void initializeLinks() {
        ShortMap< EqList<PlaceInterConnect>> visited_ = new ShortMap< EqList<PlaceInterConnect>>();
        int nbPlaces_ = places.size();
        for (short i = CustList.FIRST_INDEX; i < nbPlaces_; i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            pl_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect, Coords>());
        }
        for (short i = CustList.FIRST_INDEX; i < nbPlaces_; i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            for (PlaceInterConnect k : pl_.getSavedlinks().getKeys()) {
                if (visited_.contains(i) && visited_.getVal(i).containsObj(k)) {
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

    public static void merge(ShortMap<EqList<PlaceInterConnect>> _visited, PlaceInterConnect _k, Point _pt, short _i) {
        if (!_visited.contains(_i)) {
            EqList<PlaceInterConnect> v_ = new EqList<PlaceInterConnect>();
            v_.add(new PlaceInterConnect(_pt, _k.getDir().getOpposite()));
            _visited.put(_i, v_);
        } else {
            EqList<PlaceInterConnect> v_ = _visited.getVal(_i);
            v_.add(new PlaceInterConnect(_pt, _k.getDir().getOpposite()));
        }
    }

    public void join(short _pl1, short _pl2, Point _p1, Point _p2,
            Direction _dir1) {
        Place place1_ = places.getVal(_pl1);
        Place place2_ = places.getVal(_pl2);
        Level l1_ = ((InitializedPlace) place1_).getLevel();
        Level l2_ = ((InitializedPlace) place2_).getLevel();
        EqList<PlaceInterConnect> keys1_ = new EqList<PlaceInterConnect>();
        EqList<PlaceInterConnect> keys2_ = new EqList<PlaceInterConnect>();
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
            for (short i = CustList.FIRST_INDEX; i < length_; i++) {
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
            for (short i = CustList.FIRST_INDEX; i < length_; i++) {
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
        ObjectMap<PlaceInterConnect, Coords> join1_ = new ObjectMap<PlaceInterConnect, Coords>();
        ObjectMap<PlaceInterConnect, Coords> join2_ = new ObjectMap<PlaceInterConnect, Coords>();
        for (short i = CustList.FIRST_INDEX; i < length_; i++) {
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
        Place place_ = places.getVal(_coords.getNumberPlace());
        if (!place_.isEmptyForAdding(_coords)) {
            return false;
        }
        Point pt_ = _coords.getLevel().getPoint();
        if (_coords.isInside()) {
            Building building_ = ((City) place_).getBuildings().getVal(
                    _coords.getInsideBuilding());
            if (Point.eq(pt_, building_.getExitCity())) {
                return false;
            }
        }
        if (place_ instanceof League) {
            LevelPoint lPoint_ = _coords.getLevel();
            LevelLeague levelLeague_ = (LevelLeague) place_
                    .getLevelsMap().getVal(lPoint_.getLevelIndex());
            if (lPoint_.getLevelIndex() == CustList.FIRST_INDEX) {
                if (Point.eq(((League) place_).getBegin(), pt_)) {
                    return false;
                }
            } else {
                LevelLeague levelLeagueBack_ = (LevelLeague) place_
                        .getLevelsMap().getVal(
                                (byte) (lPoint_.getLevelIndex() - 1));
                if (Point.eq(levelLeagueBack_.getNextLevelTarget(), pt_)) {
                    return false;
                }
            }
            if (Point.eq(levelLeague_.getAccessPoint(), pt_)) {
                return false;
            }
        }
        if (place_ instanceof City) {
            if (((City) place_).getBuildings().contains(pt_)) {
                return false;
            }
            if (((City) place_).getLinksWithCaves().contains(pt_)) {
                return false;
            }
        }
        if (place_ instanceof Road) {
            if (((Road) place_).getLinksWithCaves().contains(pt_)) {
                return false;
            }
        }
        LevelPoint lPoint_ = _coords.getLevel();
        if (place_ instanceof Cave) {
            LevelCave levelCave_ = (LevelCave) place_.getLevelsMap()
                    .getVal(lPoint_.getLevelIndex());
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                return false;
            }
            if (((Cave) place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                return false;
            }
            return true;
        }
        for (Place p : places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords coords_ = ((League) p).getAccessCoords();
            if (Coords.eq(coords_, _coords)) {
                return false;
            }
        }
        return true;
    }

    public void addLeague(String _fileName, Coords _accessCoords) {
        League league_ = new League();
        league_.setFileName(_fileName);
        league_.setAccessCoords(_accessCoords);
        league_.setRooms(new CustList<LevelLeague>());
        league_.setBegin(new Point());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new ObjectMap<Point, Block>());
        level_.setAccessPoint(new Point());
        level_.setNextLevelTarget(new Point());
        level_.setTrainerCoords(new Point());
        TrainerLeague trainer_ = new TrainerLeague();
        trainer_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        trainer_.setImageMiniFileName(DataBase.EMPTY_STRING);
        trainer_.setMultiplicityFight((byte) 1);
        trainer_.setTeam(new CustList<PkTrainer>());
        level_.setTrainer(trainer_);
        league_.getRooms().add(level_);
        addPlace(league_);
    }

    public void addLevelLeague(short _league) {
        League league_ = (League) places.getVal(_league);
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new ObjectMap<Point, Block>());
        level_.setAccessPoint(new Point());
        level_.setNextLevelTarget(new Point());
        level_.setTrainerCoords(new Point());
        TrainerLeague trainer_ = new TrainerLeague();
        trainer_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        trainer_.setImageMiniFileName(DataBase.EMPTY_STRING);
        trainer_.setMultiplicityFight((byte) 1);
        trainer_.setTeam(new CustList<PkTrainer>());
        level_.setTrainer(trainer_);
        league_.getRooms().add(level_);
    }

    public void addCity(String _cityName) {
        City city_ = new City();
        city_.setName(_cityName);
        city_.setBuildings(new ObjectMap<Point, Building>());
        city_.setLinksWithCaves(new ObjectMap<Point, Link>());
        city_.setSavedlinks(new ObjectMap<PlaceInterConnect, Coords>());
        LevelOutdoor level_ = new LevelOutdoor();
        level_.setBlocks(new ObjectMap<Point, Block>());
        city_.setLevel(level_);
        addPlace(city_);
    }

    public void addRoad() {
        Road road_ = new Road();
        road_.setLinksWithCaves(new ObjectMap<Point, Link>());
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect, Coords>());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new ObjectMap<Point, Block>());
        level_.setCharacters(new ObjectMap<Point, CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point, DualFight>());
        level_.setHm(new ObjectMap<Point, Short>());
        level_.setTm(new ObjectMap<Point, Short>());
        level_.setItems(new ObjectMap<Point, String>());
        level_.setLegendaryPks(new ObjectMap<Point, WildPk>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        road_.setLevel(level_);
        addPlace(road_);
    }

    short indexOfAddedPlace() {
        Shorts keys_ = new Shorts(places.getKeys());
        if (keys_.isEmpty()) {
            return CustList.FIRST_INDEX;
        }
        long max_ = keys_.getMaximum((short) -1);
        for (short s = CustList.FIRST_INDEX; s < max_; s++) {
            if (keys_.containsObj(s)) {
                continue;
            }
            return s;
        }
        return (short) (max_ + 1);
    }

    public void addPlace(Place _place) {
        short index_ = indexOfAddedPlace();
        places.put(index_, _place);
    }

    public void moveCamera(Direction _direction) {
        if (_direction == Direction.DOWN) {
            for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
                int maxHeight_ = screenHeight - 1;
                for (int j = CustList.FIRST_INDEX; j < maxHeight_; j++) {
                    tiles.put(new ScreenCoords(i, j),
                            tiles.getVal(new ScreenCoords(i, j + 1)));
                }

                ScreenCoords key_ = new ScreenCoords(i, screenHeight - 1);
                Coords coords_ = tiles.getVal(key_);
                if (coords_.isValid()) {
                    tiles.put(key_, closestTile(coords_, _direction));
                } else {
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.UP) {
            for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
                int maxHeight_ = screenHeight - 1;
                for (int j = maxHeight_; j > CustList.FIRST_INDEX; j--) {
                    tiles.put(new ScreenCoords(i, j),
                            tiles.getVal(new ScreenCoords(i, j - 1)));
                }

                ScreenCoords key_ = new ScreenCoords(i, 0);
                Coords coords_ = tiles.getVal(key_);
                if (coords_.isValid()) {
                    tiles.put(key_, closestTile(coords_, _direction));
                } else {
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.RIGHT) {
            for (int j = CustList.FIRST_INDEX; j < screenHeight; j++) {
                int maxWidth_ = screenWidth - 1;
                for (int i = CustList.FIRST_INDEX; i < maxWidth_; i++) {
                    tiles.put(new ScreenCoords(i, j),
                            tiles.getVal(new ScreenCoords(i + 1, j)));
                }

                ScreenCoords key_ = new ScreenCoords(screenWidth - 1, j);
                Coords coords_ = tiles.getVal(key_);
                if (coords_.isValid()) {
                    tiles.put(key_, closestTile(coords_, _direction));
                } else {
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.LEFT) {
            for (int j = CustList.FIRST_INDEX; j < screenHeight; j++) {
                int maxWidth_ = screenWidth - 1;
                for (int i = maxWidth_; i > CustList.FIRST_INDEX; i--) {
                    tiles.put(new ScreenCoords(i, j),
                            tiles.getVal(new ScreenCoords(i - 1, j)));
                }

                ScreenCoords key_ = new ScreenCoords(0, j);
                Coords coords_ = tiles.getVal(key_);
                if (coords_.isValid()) {
                    tiles.put(key_, closestTile(coords_, _direction));
                } else {
                    tiles.put(key_, coords_);
                }
            }
        }
    }

    public void calculateIntersectWithScreen(Coords _coords) {
        tiles = intersectWithScreen(_coords);
    }

    ObjectMap<ScreenCoords, Coords> intersectWithScreen(Coords _coords) {
        ObjectMap<ScreenCoords, Coords> liste_ = new ObjectMap<ScreenCoords, Coords>();

        for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
            for (int j = CustList.FIRST_INDEX; j < screenHeight; j++) {
                liste_.put(new ScreenCoords(i, j), new Coords());
            }
        }
        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros), _coords);
        ObjectMap<ScreenCoords, Coords> oldList_ = new ObjectMap<ScreenCoords, Coords>(liste_);
        EqList<ScreenCoords> currentElements_ = new EqList<ScreenCoords>();
        currentElements_.add(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros));
        EqList<ScreenCoords> newElements_ = new EqList<ScreenCoords>();
        while (true) {
            for (ScreenCoords p : currentElements_) {
                Coords coords_ = liste_.getVal(p);
                if (!coords_.isValid()) {
                    continue;
                }
                for (Direction d : Direction.values()) {
                    ScreenCoords cle_ = new ScreenCoords(d.getx()
                            + p.getXcoords(), d.gety() + p.getYcoords());
                    if (!oldList_.contains(cle_)) {
                        continue;
                    }
                    Coords nextCoords_ = liste_.getVal(cle_);
                    if (nextCoords_.isValid()) {
                        continue;
                    }
                    nextCoords_ = closestTile(coords_, d);
                    liste_.put(cle_, nextCoords_);
                    newElements_.add(cle_);
                }
            }
            if (newElements_.isEmpty()) {
                break;
            }
            currentElements_ = new EqList<ScreenCoords>(newElements_);
            newElements_ = new EqList<ScreenCoords>();
        }
        return liste_;
    }

    public void calculateIntersectWithScreenDirection(Coords _coords) {
        ObjectMap<ScreenCoords, Coords> liste_ = new ObjectMap<ScreenCoords, Coords>();

        for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
            for (int j = CustList.FIRST_INDEX; j < screenHeight; j++) {
                liste_.put(new ScreenCoords(i, j), new Coords());
            }
        }
        for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
            liste_.put(new ScreenCoords(i, -1), new Coords());
        }
        for (int i = CustList.FIRST_INDEX; i < screenWidth; i++) {
            liste_.put(new ScreenCoords(i, screenHeight), new Coords());
        }
        for (int i = CustList.FIRST_INDEX; i < screenHeight; i++) {
            liste_.put(new ScreenCoords(screenWidth, i), new Coords());
        }
        for (int i = CustList.FIRST_INDEX; i < screenHeight; i++) {
            liste_.put(new ScreenCoords(-1, i), new Coords());
        }
        liste_.put(new ScreenCoords(screenWidth, -1), new Coords());
        liste_.put(new ScreenCoords(-1, -1), new Coords());
        liste_.put(new ScreenCoords(-1, screenHeight), new Coords());
        liste_.put(new ScreenCoords(screenWidth, screenHeight), new Coords());

        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros), _coords);
        ObjectMap<ScreenCoords, Coords> oldList_ = new ObjectMap<ScreenCoords, Coords>(liste_);
        EqList<ScreenCoords> currentElements_ = new EqList<ScreenCoords>();
        currentElements_.add(new ScreenCoords(spaceBetweenLeftAndHeros,
                spaceBetweenTopAndHeros));
        EqList<ScreenCoords> newElements_ = new EqList<ScreenCoords>();
        while (true) {
            for (ScreenCoords p : currentElements_) {
                Coords coords_ = liste_.getVal(p);
                if (!coords_.isValid()) {
                    continue;
                }
                for (Direction d : Direction.values()) {
                    ScreenCoords cle_ = new ScreenCoords(d.getx()
                            + p.getXcoords(), d.gety() + p.getYcoords());
                    if (!oldList_.contains(cle_)) {
                        continue;
                    }
                    Coords nextCoords_ = liste_.getVal(cle_);
                    if (nextCoords_.isValid()) {
                        continue;
                    }
                    nextCoords_ = closestTile(coords_, d);
                    liste_.put(cle_, nextCoords_);
                    newElements_.add(cle_);
                }
            }
            if (newElements_.isEmpty()) {
                break;
            }
            currentElements_ = new EqList<ScreenCoords>(newElements_);
            newElements_ = new EqList<ScreenCoords>();
        }
        tiles = liste_;
    }

    public void calculateBackgroundImagesFromTiles(DataBase _data, int _dx,
            int _dy) {
        for (ScreenCoords k : tiles.getKeys()) {
            Coords coords_ = tiles.getVal(k);
            if (!coords_.isValid()) {
                continue;
            }
            Place place_ = places.getVal(coords_.getNumberPlace());
            Level level_ = place_.getLevelByCoords(coords_);
            Point pt_ = coords_.getLevel().getPoint();
            Block bl_ = level_.getBlockByPoint(pt_);
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
        Place currentPlace_ = places.getVal(_currentCoords.getNumberPlace());
        Level currentLevel_ = currentPlace_.getLevelByCoords(_currentCoords);
        Coords closestCoords_ = new Coords(_currentCoords);
        Point closestPoint_ = closestCoords_.getLevel().getPoint();
        closestPoint_.moveTo(_direction);
        if (!currentLevel_.getBlockByPoint(closestPoint_).isValid()) {
            if (currentPlace_ instanceof InitializedPlace) {
                if (!_currentCoords.isInside()) {
                    ObjectMap<PlaceInterConnect, Coords> rc_ = ((InitializedPlace) currentPlace_)
                            .getPointsWithCitiesAndOtherRoads();
                    PlaceInterConnect key_ = new PlaceInterConnect(
                            _currentCoords.getLevel().getPoint(), _direction);
                    if (rc_.contains(key_)) {
                        return new Coords(rc_.getVal(key_));
                    }
                }
            }
            return new Coords();
        }
        return closestCoords_;
    }

    public Block currentBlock(Coords _currentCoords) {
        Place currentPlace_ = places.getVal(_currentCoords.getNumberPlace());
        Level currentLevel_ = currentPlace_.getLevelByCoords(_currentCoords);
        Coords closestCoords_ = new Coords(_currentCoords);
        Point closestPoint_ = closestCoords_.getLevel().getPoint();
        return currentLevel_.getBlockByPoint(closestPoint_);
    }

    public Tree getTree() {
        return tree;
    }

    public ObjectMap<Coords, Condition> getAccessibility() {
        return accessibility;
    }

    public EqList<Coords> getCities() {
        return cities;
    }

    public EqList<Coords> getLeagues() {
        return leagues;
    }

    public EqList<NbFightCoords> getBeatTrainer() {
        return beatTrainer;
    }

    public EqList<Coords> getBeatGymLeader() {
        return beatGymLeader;
    }

    public ShortMap< EqList<Point>> getBeatGymTrainer() {
        return beatGymTrainer;
    }

    public EqList<Coords> getHostPokemons() {
        return hostPokemons;
    }

    public EqList<Coords> getTakenPokemon() {
        return takenPokemon;
    }

    public EqList<Coords> getTakenObjects() {
        return takenObjects;
    }

    public ObjectMap<PlaceLevel, Ints> getWildPokemonBeforeFirstLeague() {
        return wildPokemonBeforeFirstLeague;
    }

    public ObjectMap<ScreenCoords, Coords> getTiles() {
        return tiles;
    }

    public ObjectMap<ScreenCoords, int[][]> getBackgroundImages() {
        return backgroundImages;
    }

    public ObjectMap<ScreenCoords, CustList<int[][]>> getForegroundImages() {
        return foregroundImages;
    }

    public ShortMap< Place> getPlaces() {
        return places;
    }

    public void setPlaces(ShortMap< Place> _places) {
        places = _places;
    }

    public ObjectMap<Coords, EqList<Coords>> getAccessCondition() {
        return accessCondition;
    }

    public void setAccessCondition(
            ObjectMap<Coords, EqList<Coords>> _accessCondition) {
        accessCondition = _accessCondition;
    }

    public ObjectMap<MiniMapCoords, TileMiniMap> getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(ObjectMap<MiniMapCoords, TileMiniMap> _miniMap) {
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

    public void setError(boolean _error) {
        error = _error;
    }

}
