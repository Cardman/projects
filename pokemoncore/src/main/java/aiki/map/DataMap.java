package aiki.map;
import aiki.DataBase;
import aiki.comparators.ComparatorMiniMapCoords;
import aiki.comparators.ComparatorScreenCoords;
import aiki.exceptions.BlockNotFoundException;
import aiki.exceptions.DataException;
import aiki.exceptions.NoWildPokemonException;
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
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
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
import code.datacheck.CheckedData;
import code.images.Image;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.ReversibleMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.annot.RwXml;
import code.util.ints.Listable;

@RwXml
public class DataMap {

    private static final String SPACE = " ";

    private NumberMap<Short,Place> places;

    private ObjectMap<Coords,EqList<Coords>> accessCondition;

    private ObjectMap<MiniMapCoords,TileMiniMap> miniMap;

    @CheckedData
    private String unlockedCity;

    @CheckedData
    private Coords begin;

    @CheckedData
    private WildPk firstPokemon;

    //private Map<EnvironmentType,String> spritesGirlBoy;

    @CheckedData
    private int screenWidth = 9;

    @CheckedData
    private int screenHeight = 9;

    @CheckedData
    private int spaceBetweenLeftAndHeros = 4;

    @CheckedData
    private int spaceBetweenTopAndHeros = 4;

    @CheckedData
    private int sideLength = 32;

    private transient Tree tree = new Tree();

    private transient ObjectMap<Coords,Condition> accessibility = new ObjectMap<Coords, Condition>();

    private transient EqList<Coords> cities = new EqList<Coords>();

    private transient EqList<Coords> leagues = new EqList<Coords>();

    private transient EqList<NbFightCoords> beatTrainer = new EqList<NbFightCoords>();

    private transient EqList<Coords> beatGymLeader = new EqList<Coords>();

    private transient NumberMap<Short,EqList<Point>> beatGymTrainer = new NumberMap<Short, EqList<Point>>();

    private transient EqList<Coords> hostPokemons = new EqList<Coords>();

    private transient EqList<Coords> takenPokemon = new EqList<Coords>();

    private transient EqList<Coords> takenObjects = new EqList<Coords>();

    private transient ObjectMap<PlaceLevel,Numbers<Integer>> wildPokemonBeforeFirstLeague = new ObjectMap<PlaceLevel, Numbers<Integer>>();

    private transient ObjectMap<ScreenCoords,Coords> tiles = new ObjectMap<ScreenCoords, Coords>();

    private transient ObjectMap<ScreenCoords,String> backgroundImages = new ObjectMap<ScreenCoords, String>();

    private transient ObjectMap<ScreenCoords,StringList> foregroundImages = new ObjectMap<ScreenCoords, StringList>();

    public void validate(DataBase _d) {
        if (screenWidth < 0 || screenHeight < 0) {
            throw new DataException();
        }
        if (spaceBetweenLeftAndHeros <= 0 || spaceBetweenTopAndHeros <= 0) {
            throw new DataException();
        }
        if (screenWidth <= spaceBetweenLeftAndHeros + 1 || screenHeight <= spaceBetweenTopAndHeros + 1) {
            throw new DataException();
        }
        if (sideLength <= 0) {
            throw new DataException();
        }
        initInteractiveElements();
        firstPokemon.validate(_d, true);
//        if (!firstPokemon.isValid(_d)) {
//            throw new DataException();
//        }
        tree = new Tree();
        tree.initialize(this);
        Place plBegin_ = places.getVal(begin.getNumberPlace());
        Level lBegin_ = plBegin_.getLevelByCoords(begin);
        if (!lBegin_.isEmptyForAdding(begin.getLevel().getPoint())) {
            throw new DataException();
        }
        boolean createdLeague_ = false;
        wildPokemonBeforeFirstLeague = new ObjectMap<PlaceLevel, Numbers<Integer>>();
        int nbPlaces_ = places.size();
        for (short p=CustList.FIRST_INDEX;p<nbPlaces_;p++) {
            places.getVal(p).validate(_d, tree.getPlace(p));
            if (places.getVal(p) instanceof InitializedPlace) {
                if (!((InitializedPlace)places.getVal(p)).validLinks(tree)) {
                    throw new DataException();
                }
            }
            if (places.getVal(p) instanceof League) {
                createdLeague_ = true;
                if (!((League)places.getVal(p)).validLinks(tree)) {
                    throw new DataException();
                }
            }
            if (places.getVal(p) instanceof Cave) {
                if (!((Cave)places.getVal(p)).validLinks(p,tree)) {
                    throw new DataException();
                }
            }
        }
        for (short p=CustList.FIRST_INDEX;p<nbPlaces_;p++) {
            if (places.getVal(p) instanceof InitializedPlace) {
                InitializedPlace place_ = (InitializedPlace) places.getVal(p);
                ObjectMap<Point,Link> links_;
                links_ = place_.getLinksWithCaves();
                for (Point pt_ : links_.getKeys()) {
//                    Coords id_ = new Coords();
//                    id_.setNumberPlace(p);
//                    id_.setLevel(new LevelPoint());
//                    id_.getLevel().setLevelIndex((byte) 0);
//                    id_.getLevel().setPoint(new Point(pt_));
//                    Coords from_ = new Coords(id_);
//                    Direction dir_ = links_.getVal(pt_).getDir();
//                    if (dir_ != null) {
//                        id_.getLevel().getPoint().moveTo(dir_);
//                        if (!isEmptyForAdding(id_)) {
//                            throw new DataException();
//                        }
//                    }
//                    if (!tree.isValid(id_, true)) {
//                        throw new DataException();
//                    }
//                    Coords link_ = links_.getVal(pt_).getCoords();
                    Coords link_ = closestTile(links_.getVal(pt_));
                    if (!tree.isValid(links_.getVal(pt_).getCoords(), true)) {
                        throw new DataException();
                    }
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof Cave)) {
                        throw new DataException();
                    }
                    Cave cave_ = (Cave) t_;
                    LevelPoint lPoint_ = link_.getLevel();
                    if (!cave_.getLinksWithOtherPlaces().contains(lPoint_)) {
                        throw new DataException();
                    }
//                    Coords other_ = cave_.getLinksWithOtherPlaces().getVal(lPoint_).getCoords();
                    Coords other_ = closestTile(cave_.getLinksWithOtherPlaces().getVal(lPoint_));
                    if (!Numbers.eq(other_.getNumberPlace(), p)) {
                        throw new DataException();
                    }
                    if (!Numbers.eq(other_.getLevel().getLevelIndex(), 0)) {
                        throw new DataException();
                    }
                    if (!Point.eq(other_.getLevel().getPoint(), pt_)) {
                        throw new DataException();
                    }
//                    if (!Point.eq(other_.getLevel().getPoint(), id_.getLevel().getPoint())) {
//                        throw new DataException();
//                    }
                }
                continue;
            }
            if (places.getVal(p) instanceof Cave) {
                Cave place_ = (Cave) places.getVal(p);
                ObjectMap<LevelPoint,Link> links_;
                links_ = place_.getLinksWithOtherPlaces();
                for (LevelPoint l : links_.getKeys()) {
//                    Coords id_ = new Coords();
//                    id_.setNumberPlace(p);
//                    id_.setLevel(new LevelPoint());
//                    id_.getLevel().setLevelIndex(l.getLevelIndex());
//                    id_.getLevel().setPoint(new Point(l.getPoint()));
//                    Coords from_ = new Coords(id_);
//                    Direction dir_ = links_.getVal(l).getDir();
//                    if (dir_ != null) {
//                        id_.getLevel().getPoint().moveTo(dir_);
//                        if (!isEmptyForAdding(id_)) {
//                            throw new DataException();
//                        }
//                    }
//                    if (!tree.isValid(id_, true)) {
//                        throw new DataException();
//                    }
//                    Coords link_ = links_.getVal(l).getCoords();
                    Coords link_ = closestTile(links_.getVal(l));
                    if (!tree.isValid(links_.getVal(l).getCoords(), true)) {
                        throw new DataException();
                    }
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof InitializedPlace)) {
                        throw new DataException();
                    }
                    InitializedPlace cave_ = (InitializedPlace) t_;
                    Point point_ = link_.getLevel().getPoint();
                    if (!cave_.getLinksWithCaves().contains(point_)) {
                        throw new DataException();
                    }
//                    Coords other_ = cave_.getLinksWithCaves().getVal(point_).getCoords();
                    Coords other_ = closestTile(cave_.getLinksWithCaves().getVal(point_));
                    if (!Numbers.eq(other_.getNumberPlace(), p)) {
                        throw new DataException();
                    }
                    if (!Numbers.eq(other_.getLevel().getLevelIndex(), l.getLevelIndex())) {
                        throw new DataException();
                    }
                    if (!Point.eq(other_.getLevel().getPoint(), l.getPoint())) {
                        throw new DataException();
                    }
//                    if (!Point.eq(other_.getLevel().getPoint(), id_.getLevel().getPoint())) {
//                        throw new DataException();
//                    }
                }
            }
        }
        if (!createdLeague_) {
            throw new DataException();
        }
        for (Coords c: accessCondition.getKeys()) {
            EqList<Coords> invalidCoords_ = new EqList<Coords>();
            EqList<Coords> addedCoords_ = new EqList<Coords>();
            for (Coords c2_: accessCondition.getVal(c)) {
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
        for (Coords c: accessCondition.getKeys()) {
            accessCondition.getVal(c).removeDuplicates();
        }
        for (Coords c: accessCondition.getKeys()) {
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
        for (Place p:places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            League league_ = (League) p;
            if (!accessCondition.contains(league_.getAccessCoords())) {
                throw new DataException();
            }
        }
        for (Coords c: accessCondition.getKeys()) {
            for (Coords c2_: accessCondition.getVal(c)) {
                if (!tree.isValid(c2_, true)) {
                    throw new DataException();
                }
            }
        }
        initializeAccessibility();
        League firstLeague_ = (League) places.getVal(leagues.first().getNumberPlace());
        Condition coords_ = accessibility.getVal(firstLeague_.getAccessCoords());
        StringList evoObjects_ = new StringList();
        StringList movesTmHm_ = new StringList();
        boolean moveTutor_ = false;
        boolean ball_ = false;
        for (Coords c: accessibility.getKeys()) {
            if (!coords_.containsAllObj(accessibility.getVal(c))) {
                continue;
            }
            Place place_ = places.getVal(c.getNumberPlace());
            Level l_ = place_.getLevelByCoords(c);
            if (l_ instanceof LevelIndoorPokemonCenter) {
                for (Person p: ((LevelIndoorPokemonCenter)l_).getGerants().values()) {
                    if (p instanceof Seller) {
                        if (((Seller)p).getSell() == SellType.ITEM) {
                            evoObjects_.addAllElts(((Seller)p).getItems());
                            if (((Seller)p).getItems().containsObj(_d.getDefaultBall())) {
                                if (accessibility.getVal(c).isEmpty()) {
                                    ball_ = true;
                                }
                            }
                        }
                        for (short s: ((Seller)p).getTm()) {
                            movesTmHm_.add(_d.getTm().getVal(s));
                        }
                        if (((Seller)p).getSell() == SellType.MOVE) {
//                            moveTutor_ = true;
                            if (accessibility.getVal(c).isEmpty()) {
                                moveTutor_ = true;
                            }
                        }
                    }
                }
            }
            PlaceArea pl_ = tree.getPlace(c.getNumberPlace());
            LevelArea level_ = pl_.getLevel(c.getLevel().getLevelIndex());
            PlaceLevel keyPlaceLevel_ = new PlaceLevel(c.getNumberPlace(), c.getLevel().getLevelIndex());
            int index_ = level_.getIndex(c.getLevel().getPoint());
            if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
                continue;
            }
            if (wildPokemonBeforeFirstLeague.contains(keyPlaceLevel_)) {
                wildPokemonBeforeFirstLeague.getVal(keyPlaceLevel_).add(index_);
                wildPokemonBeforeFirstLeague.getVal(keyPlaceLevel_).removeDuplicates();
            } else {
                wildPokemonBeforeFirstLeague.put(keyPlaceLevel_, new Numbers<Integer>(index_));
            }
        }
        if (!ball_) {
            throw new DataException();
        }
        if (!moveTutor_) {
            throw new DataException();
        }
        //Here, a pokemon center exists and is accessible from the beginning
        //The player can buy balls without beating an important trainer
        evoObjects_.removeDuplicates();
        for (String o: _d.getItems().getKeys()) {
            Item o_ = _d.getItems().getVal(o);
            if (o_ instanceof EvolvingStone) {
                if (!evoObjects_.containsObj(o)) {
                    throw new DataException();
                }
            }
            if (o_ instanceof EvolvingItem) {
                if (!evoObjects_.containsObj(o)) {
                    throw new DataException();
                }
            }
        }
        for (String p: _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(p);
            StringList moves_ = new StringList();
            for (Evolution e: pk_.getEvolutions().values()) {
                if (!(e instanceof EvolutionMove)) {
                    continue;
                }
                moves_.add(((EvolutionMove)e).getMove());
            }
            if (moves_.isEmpty()) {
                continue;
            }
            moves_.removeDuplicates();
            for (String m: moves_) {
//                if (!_d.getTm().getKeys(m).isEmpty())
                if (!_d.getTmByMove(m).isEmpty()) {
                    if (!movesTmHm_.containsObj(m)) {
                        throw new DataException();
                    }
                }
            }
        }
        StringList availableTypesTm_ = new StringList();
        for (String m: movesTmHm_) {
            availableTypesTm_.addAllElts(_d.getMove(m).getTypes());
        }
        availableTypesTm_.removeDuplicates();
        for (String p: _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(p);
            StringList types_ = new StringList();
            for (Evolution e: pk_.getEvolutions().values()) {
                if (!(e instanceof EvolutionMoveType)) {
                    continue;
                }
                types_.add(((EvolutionMoveType)e).getType());
            }
            if (types_.isEmpty()) {
                continue;
            }
            types_.removeDuplicates();
            StringList typesRetr_ = new StringList();
            for (String m: pk_.getMoveTutors()) {
                typesRetr_.addAllElts(_d.getMove(m).getTypes());
            }
            for (LevelMove l: pk_.getLevMoves()) {
                typesRetr_.addAllElts(_d.getMove(l.getMove()).getTypes());
            }
            if (typesRetr_.containsAllObj(types_)) {
                continue;
            }
            if (!availableTypesTm_.containsAllObj(types_)) {
                throw new DataException();
            }
        }
        StringMap<EnumList<Gender>> directCatchPk_ = new StringMap<EnumList<Gender>>();
        for (short p_: places.getKeys()) {
            Place pl_ = places.getVal(p_);
            for (byte l: pl_.getLevels().getKeys()) {
                Level level_ = pl_.getLevels().getVal(l);
                if (!(level_ instanceof LevelWithWildPokemon)) {
                    continue;
                }
                LevelWithWildPokemon levelWild_ = (LevelWithWildPokemon) level_;
                PlaceLevel keyPlaceLevel_ = new PlaceLevel(p_, l);
                if (!wildPokemonBeforeFirstLeague.contains(keyPlaceLevel_)) {
                    continue;
                }
                Numbers<Integer> levelPokemon_;
                levelPokemon_ = wildPokemonBeforeFirstLeague.getVal(keyPlaceLevel_);
                for (int index_: levelPokemon_) {
                    if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                    for (WildPk p: levelWild_.getWildPokemonAreas().get(index_).getWildPokemon()) {
                        if (!directCatchPk_.contains(p.getName())) {
                            directCatchPk_.put(p.getName(), new EnumList<Gender>(p.getGender()));
                        } else {
                            directCatchPk_.getVal(p.getName()).add(p.getGender());
                            directCatchPk_.getVal(p.getName()).removeDuplicates();
                        }
                    }
                    for (WildPk p: levelWild_.getWildPokemonAreas().get(index_).getWildPokemonFishing()) {
                        if (!directCatchPk_.contains(p.getName())) {
                            directCatchPk_.put(p.getName(), new EnumList<Gender>(p.getGender()));
                        } else {
                            directCatchPk_.getVal(p.getName()).add(p.getGender());
                            directCatchPk_.getVal(p.getName()).removeDuplicates();
                        }
                    }
                }
            }
        }
//        for (CustList<Pair<String,Gender>> levelPokemon_: wildPokemonBeforeFirstLeague.values()) {
//            for (Pair<String,Gender> pokemon_: levelPokemon_) {
//                listBack_.add(pokemon_.getFirst());
//                if (directCatchPk_.contains(pokemon_.getFirst())) {
//                    EnumList<Gender> list_ = directCatchPk_.getVal(pokemon_.getFirst());
//                    if (!list_.contains(pokemon_.getSecond())) {
//                        list_.add(pokemon_.getSecond());
//                    }
//                } else {
//                    directCatchPk_.put(pokemon_.getFirst(), new EnumList<>(pokemon_.getSecond()));
//                }
//            }
//        }
        StringList baseEvos_ = new StringList();
        for (String n: _d.getPokedex().getKeys()) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (fPk_.getGenderRep() == GenderRepartition.LEGENDARY) {
                continue;
            }
            baseEvos_.add(fPk_.getBaseEvo());
        }
        baseEvos_.removeDuplicates();
        if (!directCatchPk_.containsAllAsKeys(baseEvos_)) {
            throw new DataException();
        }
        for (String n:baseEvos_) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (!directCatchPk_.getVal(n).containsAllObj(fPk_.getGenderRep().getPossibleGenders())) {
                throw new DataException();
            }
        }
        boolean existPkDefaultEgg_ = false;
        for (String n: _d.getPokedex().getKeys()) {
            PokemonData pk_ = _d.getPokemon(n);
            if (pk_.getGenderRep() != GenderRepartition.NO_GENDER) {
                continue;
            }
            if (pk_.getEggGroups().containsObj(_d.getDefaultEggGroup())) {
                existPkDefaultEgg_ = true;
                break;
            }
        }
        if (!existPkDefaultEgg_) {
            throw new DataException();
        }
        StringList legPk_ = new StringList();
        for (String n: _d.getPokedex().getKeys()) {
            PokemonData fPk_ = _d.getPokemon(n);
            if (fPk_.getGenderRep() != GenderRepartition.LEGENDARY) {
                continue;
            }
            legPk_.add(n);
        }
        StringList wildPk_ = new StringList();
        for (Coords c: accessibility.getKeys()) {
            Place pl_ = places.getVal(c.getNumberPlace());
            if (!(pl_ instanceof Campaign)) {
                continue;
            }
            NumberMap<Byte,? extends Level> levels_;
            levels_ = pl_.getLevels();
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) levels_.getVal(c.getLevel().getLevelIndex());
            try {
                AreaApparition area_ = level_.getAreaByPoint(c.getLevel().getPoint());
                for (WildPk pk_: area_.getWildPokemon()) {
                    wildPk_.add(pk_.getName());
                }
                for (WildPk pk_: area_.getWildPokemonFishing()) {
                    wildPk_.add(pk_.getName());
                }
//            } catch (Exception e_) {
            } catch (NoWildPokemonException _0) {
            }
            if (level_.containsPokemon(c.getLevel().getPoint())) {
                WildPk pk_ = level_.getPokemon(c.getLevel().getPoint());
                wildPk_.add(pk_.getName());
            }
        }
        wildPk_.removeDuplicates();
        if (!wildPk_.containsAllObj(legPk_)) {
            throw new DataException();
        }
        int maxWidth_ = 0;
        int maxHeight_ = 0;
        EqList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m: list_) {
            if (m.getXcoords() < 0) {
                throw new DataException();
            }
            if (m.getYcoords() < 0) {
                throw new DataException();
            }
            if (maxWidth_ < m.getXcoords()) {
                maxWidth_ = m.getXcoords();
            }
            if (maxHeight_ < m.getYcoords()) {
                maxHeight_ = m.getYcoords();
            }
        }
        Numbers<Short> placesMiniMap_ = new Numbers<Short>();
        for (MiniMapCoords m: list_) {
            TileMiniMap tile_ = miniMap.getVal(m);
            if (Numbers.eq(tile_.getPlace(), CustList.INDEX_NOT_FOUND_ELT)) {
                continue;
            }
            placesMiniMap_.add(tile_.getPlace());
        }
        StringList imagesCities_;
        StringList imagesRoads_;
        StringList imagesCaves_;
        StringList imagesLeagues_;
        StringList imagesOutside_;
        StringList imageUnlockedCity_ = new StringList(_d.getMiniMap(unlockedCity));
        imagesCities_ = new StringList();
        imagesRoads_ = new StringList();
        imagesCaves_ = new StringList();
        imagesLeagues_ = new StringList();
        imagesOutside_ = new StringList();
        for (MiniMapCoords m: list_) {
            TileMiniMap tile_ = miniMap.getVal(m);
            String image_ = _d.getMiniMap(tile_.getFile());
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
                continue;
            }
        }
        EqList<StringList> images_ = new EqList<StringList>();
        images_.add(imagesOutside_);
        images_.add(imagesCities_);
        images_.add(imagesRoads_);
        images_.add(imagesCaves_);
        images_.add(imagesLeagues_);
        images_.add(imageUnlockedCity_);
        if (!StringList.disjoint(images_)) {
            throw new DataException();
        }
        if (!Numbers.equalsSetShorts(placesMiniMap_, places.getKeys())) {
            throw new DataException();
        }
        if (list_.size() != (maxWidth_ + 1) * (maxHeight_ + 1)) {
            throw new DataException();
        }
        if (_d.getMiniMap(getUnlockedCity()).isEmpty()) {
            throw new DataException();
        }
//        if (!_d.getMiniMap().contains(getUnlockedCity())) {
//            throw new Exception();
//        }
        for (Coords c: cities) {
            if (!accessibility.contains(c)) {
                throw new DataException();
            }
        }
        boolean firstCities_ = false;
        for (Coords c: cities) {
            if (accessibility.contains(c)) {
                if (accessibility.getVal(c).isEmpty()) {
                    firstCities_ = true;
                    break;
                }
            }
        }
        if (!firstCities_) {
            throw new DataException();
        }
        for (Coords c: beatGymLeader) {
            if (c.isInside()) {
                //City city_ = (City) places.getVal(c.getNumberPlace());
                //Gym gym_ = (Gym) city_.getBuildings().getVal(c.getInsideBuilding());
                Coords coordsExt_ = new Coords();
                coordsExt_.setNumberPlace(c.getNumberPlace());
                coordsExt_.setLevel(new LevelPoint());
                Point exitBuilding_ = new Point(c.getInsideBuilding());
                exitBuilding_.moveTo(Direction.DOWN);
                coordsExt_.getLevel().setPoint(exitBuilding_);
                boolean existAccess_ = false;
                for (Direction d: Direction.values()) {
                    if (accessibility.contains(closestTile(coordsExt_, d))) {
                        existAccess_ = true;
                        break;
                    }
                }
                if (!existAccess_) {
                    throw new DataException();
                }
                continue;
            }
            Place plVal_ = places.getVal(c.getNumberPlace());
            if (plVal_ instanceof League) {
                League league_ = (League) plVal_;
                boolean existAccess_ = false;
                for (Direction d: Direction.values()) {
                    if (accessibility.contains(closestTile(league_.getAccessCoords(), d))) {
                        existAccess_ = true;
                        break;
                    }
                }
                if (!existAccess_) {
                    throw new DataException();
                }
                continue;
            }
            boolean existAccess_ = false;
            for (Direction d: Direction.values()) {
                if (accessibility.contains(closestTile(c, d))) {
                    existAccess_ = true;
                    break;
                }
            }
            if (!existAccess_) {
                throw new DataException();
            }
        }
        for (NbFightCoords c: beatTrainer) {
            boolean existAccess_ = false;
            for (Direction d: Direction.values()) {
                if (accessibility.contains(closestTile(c.getCoords(), d))) {
                    existAccess_ = true;
                    break;
                }
            }
            if (!existAccess_) {
                throw new DataException();
            }
        }
    }

    public void initializeTree() {
        tree = new Tree();
        tree.initialize(this);
    }

    public void validateForEditing(DataBase _d) {
        if (sideLength <= 0) {
            throw new DataException();
        }
        beatGymLeader = new EqList<Coords>();
        beatGymTrainer = new NumberMap<Short, EqList<Point>>();
        hostPokemons = new EqList<Coords>();
        takenPokemon = new EqList<Coords>();
        takenObjects = new EqList<Coords>();
        int nbPlaces_ = places.size();
        for (short s = CustList.FIRST_INDEX; s < nbPlaces_; s++) {
            Place place_ = places.getVal(s);
            if (place_ instanceof City) {
                for (EntryCust<Point, Building> b: ((City)place_).getBuildings().entryList()) {
                    if (b.getValue() instanceof Gym) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.affectInside(b.getKey());
                        c_.getLevel().setLevelIndex((byte) 0);
                        c_.getLevel().setPoint(((Gym)b.getValue()).getLevel().getGymLeaderCoords());
                        beatGymLeader.add(c_);
                        //TODO remove after
                        beatGymTrainer.put(s,new EqList<Point>(((Gym)b.getValue()).getLevel().getGymTrainers().getKeys()));
                        break;
                    }
                }
                for (EntryCust<Point, Building> b: ((City)place_).getBuildings().entryList()) {
                    if (b.getValue() instanceof PokemonCenter) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.affectInside(b.getKey());
                        c_.getLevel().setLevelIndex((byte) 0);
                        Coords coordsCity_ = new Coords(c_);
                        coordsCity_.outside();
                        coordsCity_.getLevel().setPoint(new Point(b.getKey()));
                        coordsCity_.getLevel().getPoint().moveTo(Direction.DOWN);
                        cities.add(coordsCity_);
                        boolean add_ = false;
                        for (EntryCust<Point, Person> g: ((PokemonCenter)b.getValue()).getLevel().getGerants().entryList()) {
                            if (!(g.getValue() instanceof GerantPokemon)) {
                                continue;
                            }
                            if(((GerantPokemon) g.getValue()).getGerance() == GeranceType.HOST) {
                                c_.getLevel().setPoint(g.getKey());
                                add_ = true;
                                break;
                            }
                        }
                        if (add_) {
                            hostPokemons.add(c_);
                        }
                    }
                }
            }
            if (place_ instanceof Road) {
                for (Point p: ((Road)place_).getLevel().getLegendaryPks().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenPokemon.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getItems().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getTm().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getHm().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getDualFights().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    beatGymLeader.add(c_);
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
        }
        for (short p=CustList.FIRST_INDEX;p<nbPlaces_;p++) {
            places.getVal(p).validateForEditing(_d);
        }
        for (short p=CustList.FIRST_INDEX;p<nbPlaces_;p++) {
            if (places.getVal(p) instanceof InitializedPlace) {
                InitializedPlace place_ = (InitializedPlace) places.getVal(p);
                ObjectMap<Point,Link> links_;
                links_ = place_.getLinksWithCaves();
                for (Point pt_ : links_.getKeys()) {
                    Coords link_ = links_.getVal(pt_).getCoords();
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof Cave)) {
                        throw new DataException();
                    }
                    Cave cave_ = (Cave) t_;
                    LevelPoint lPoint_ = link_.getLevel();
                    if (!cave_.getLinksWithOtherPlaces().contains(lPoint_)) {
                        throw new DataException();
                    }
                    Coords other_ = cave_.getLinksWithOtherPlaces().getVal(lPoint_).getCoords();
                    if (!Numbers.eq(other_.getNumberPlace(), p)) {
                        throw new DataException();
                    }
                    if (!Numbers.eq(other_.getLevel().getLevelIndex(), 0)) {
                        throw new DataException();
                    }
                    if (!Point.eq(other_.getLevel().getPoint(), pt_)) {
                        throw new DataException();
                    }
                }
                continue;
            }
            if (places.getVal(p) instanceof Cave) {
                Cave place_ = (Cave) places.getVal(p);
                ObjectMap<LevelPoint,Link> links_;
                links_ = place_.getLinksWithOtherPlaces();
                for (LevelPoint l : links_.getKeys()) {
                    Coords link_ = links_.getVal(l).getCoords();
                    Place t_ = places.getVal(link_.getNumberPlace());
                    if (!(t_ instanceof InitializedPlace)) {
                        throw new DataException();
                    }
                    InitializedPlace cave_ = (InitializedPlace) t_;
                    Point point_ = link_.getLevel().getPoint();
                    if (!cave_.getLinksWithCaves().contains(point_)) {
                        throw new DataException();
                    }
                    Coords other_ = cave_.getLinksWithCaves().getVal(point_).getCoords();
                    if (!Numbers.eq(other_.getNumberPlace(), p)) {
                        throw new DataException();
                    }
                    if (!Numbers.eq(other_.getLevel().getLevelIndex(), l.getLevelIndex())) {
                        throw new DataException();
                    }
                    if (!Point.eq(other_.getLevel().getPoint(), l.getPoint())) {
                        throw new DataException();
                    }
                }
            }
        }
    }

    public void initInteractiveElements() {
        beatTrainer = new EqList<NbFightCoords>();
        beatGymLeader = new EqList<Coords>();
        beatGymTrainer = new NumberMap<Short, EqList<Point>>();
        hostPokemons = new EqList<Coords>();
        takenPokemon = new EqList<Coords>();
        takenObjects = new EqList<Coords>();
        int nbPlaces_ = places.size();
        for (short s = CustList.FIRST_INDEX; s < nbPlaces_; s++) {
            Place place_ = places.getVal(s);
            if (place_ instanceof City) {
                for (EntryCust<Point, Building> b: ((City)place_).getBuildings().entryList()) {
                    if (b.getValue() instanceof Gym) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.affectInside(b.getKey());
                        c_.getLevel().setLevelIndex((byte) 0);
                        c_.getLevel().setPoint(((Gym)b.getValue()).getLevel().getGymLeaderCoords());
                        beatGymLeader.add(c_);
                      //TODO remove after
                        beatGymTrainer.put(s,new EqList<Point>(((Gym)b.getValue()).getLevel().getGymTrainers().getKeys()));
                        break;
                    }
                }
                for (EntryCust<Point, Building> b: ((City)place_).getBuildings().entryList()) {
                    if (b.getValue() instanceof PokemonCenter) {
                        Coords coordsCity_ = new Coords();
                        coordsCity_.setNumberPlace(s);
                        coordsCity_.setLevel(new LevelPoint());
                        coordsCity_.getLevel().setLevelIndex((byte) 0);
                        coordsCity_.outside();
                        coordsCity_.getLevel().setPoint(new Point(b.getKey()));
                        coordsCity_.getLevel().getPoint().moveTo(Direction.DOWN);
                        cities.add(coordsCity_);
                        for (EntryCust<Point, Person> g: ((PokemonCenter)b.getValue()).getLevel().getGerants().entryList()) {
                            if (!(g.getValue() instanceof GerantPokemon)) {
                                continue;
                            }
                            if(((GerantPokemon) g.getValue()).getGerance() == GeranceType.HOST) {
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
            if (place_ instanceof Road) {
                for (Point p: ((Road)place_).getLevel().getLegendaryPks().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenPokemon.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getCharacters().getKeys()) {
                    if (!(((Road)place_).getLevel().getCharacters().getVal(p) instanceof DealerItem)) {
                        continue;
                    }
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getItems().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getTm().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getHm().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    takenObjects.add(c_);
                }
                for (Point p: ((Road)place_).getLevel().getDualFights().getKeys()) {
                    Coords c_ = new Coords();
                    c_.setNumberPlace(s);
                    c_.setLevel(new LevelPoint());
                    c_.getLevel().setLevelIndex((byte) 0);
                    c_.getLevel().setPoint(p);
                    beatGymLeader.add(c_);
                }
            }
            if (place_ instanceof Cave) {
                for (byte k: ((Cave)place_).getLevels().getKeys()) {
                    LevelCave levelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(k);
                    for (Point p: levelCave_.getLegendaryPks().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenPokemon.add(c_);
                    }
                    for (Point p: levelCave_.getCharacters().getKeys()) {
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
                    for (Point p: levelCave_.getItems().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p: levelCave_.getTm().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p: levelCave_.getHm().getKeys()) {
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(k);
                        c_.getLevel().setPoint(p);
                        takenObjects.add(c_);
                    }
                    for (Point p: levelCave_.getDualFights().getKeys()) {
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
                NumberMap<Byte,Level> levels_ = ((Campaign)place_).getLevels();
                for (EntryCust<Byte,Level> e: levels_.entryList()) {
                    LevelWithWildPokemon wild_ = (LevelWithWildPokemon)e.getValue();
                    for (EntryCust<Point,CharacterInRoadCave> c: wild_.getCharacters().entryList()) {
                        if (!(c.getValue() instanceof TrainerMultiFights)) {
                            continue;
                        }
                        Coords c_ = new Coords();
                        c_.setNumberPlace(s);
                        c_.setLevel(new LevelPoint());
                        c_.getLevel().setLevelIndex(e.getKey());
                        c_.getLevel().setPoint(c.getKey());
                        TrainerMultiFights tr_ = (TrainerMultiFights) c.getValue();
                        int nb_ = tr_.getTeamsRewards().size();
                        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                            beatTrainer.add(new NbFightCoords(c_,i));
                        }
                    }
                }
            }
        }
    }
    public void initializeWildPokemon() {
        for (Place p: places.values()) {
            if (p instanceof Campaign) {
                ((Campaign)p).initializeWildPokemon();
            }
        }
    }

    public TreeMap<MiniMapCoords, String> getImages(DataBase _data) {
        TreeMap<MiniMapCoords, String> map_ = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        for (MiniMapCoords m_: miniMap.getKeys()) {
            String image_ = _data.getMiniMap(miniMap.getVal(m_).getFile());
//            map_.put(new MiniMapCoords(m_.getXcoords(), m_.getYcoords()), image_);
            map_.put(m_, image_);
        }
        return map_;
    }

    public Coords getCity(MiniMapCoords _m) {
        if (miniMap.contains(_m)) {
            short place_ = miniMap.getVal(_m).getPlace();
            for (Coords c: cities) {
                if (Numbers.eq(c.getNumberPlace(), place_)) {
                    return c;
                }
            }
        }
        return new Coords();
    }

    public String getName(int _x, int _y) {
        MiniMapCoords m_ = new MiniMapCoords((byte)_x,(byte) _y);
        if (miniMap.contains(m_)) {
            short place_ = miniMap.getVal(m_).getPlace();
            if (!Numbers.eq(place_, CustList.INDEX_NOT_FOUND_ELT)) {
                return places.getVal(place_).getName();
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public String getImage(DataBase _data, int _x, int _y) {
        MiniMapCoords m_ = new MiniMapCoords((byte)_x,(byte) _y);
        if (miniMap.contains(m_)) {
            String place_ = miniMap.getVal(m_).getFile();
            return _data.getMiniMap(place_);
        }
        return DataBase.EMPTY_STRING;
    }

    public int getMapWidth() {
        int maxWidth_ = 0;
        EqList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m: list_) {
            if (maxWidth_ < m.getXcoords()) {
                maxWidth_ = m.getXcoords();
            }
        }
        return maxWidth_ + 1;
    }

    public int getMapHeight() {
        int maxHeight_ = 0;
        EqList<MiniMapCoords> list_ = miniMap.getKeys();
        for (MiniMapCoords m: list_) {
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
        ObjectMap<Coords,Condition> newElts_ = new ObjectMap<Coords,Condition>();
        newElts_.put(new Coords(begin), new Condition());
        ObjectMap<Coords,Condition> coordsCond_ = new ObjectMap<Coords, Condition>(newElts_);
        ObjectMap<Coords,Condition> coordsCondBis_ = new ObjectMap<Coords, Condition>(newElts_);
        ObjectMap<Coords,Condition> allTiles_ = new ObjectMap<Coords, Condition>(newElts_);
        leagues = new EqList<Coords>();
        while(true) {
            ObjectMap<Coords,Condition> neigh_ = possibleNeighbours(allTiles_,coordsCond_);
            EqList<Coords> diff_ = neigh_.getKeys();
            if (diff_.isEmpty()) {
                break;
            }
            EqList<Coords> coordsCondBack_ = coordsCond_.getKeys();
            for (EntryCust<Coords,Condition> e:neigh_.entryList()) {
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
            for (EntryCust<Coords,Condition> e:neigh_.entryList()) {
                Coords c_ = e.getKey();
                if (accessCondition.contains(c_) && !coordsCondBack_.containsObj(c_)) {
                    inext_.add(c_);
                    continue;
                }
                allTiles_.put(c_, e.getValue());
            }
            if (!validConditions(inext_,neigh_)) {
                throw new DataException();
            }
            diff_.removeAllElements(inext_);
            //CustList<Coords> accessibleLeaders_ = accessibleLeaders(diff_);
            ObjectMap<Coords,Coords> newLeaders_ = leaders(diff_);
            EqList<Coords> accessibleLeaders_ = newLeaders_.getKeys();
            for (Coords c: coordsCondBis_.getKeys()) {
                if (allTiles_.contains(c)) {
                    continue;
                }
                /*for (Coords l: newLeaders_) {
                    Place place_ = getPlaces().getVal(l.getNumberPlace());
                    if (place_ instanceof League) {
                        League league_ = (League)place_;
                        byte ind_ = l.getLevel().getLevelIndex();
                        if (Numbers.eq(ind_+1,league_.getRooms().size())) {
//                            Coords coords_ = new Coords(c);
////                            coords_.getLevel().setPoint(league_.getRooms().get(ind_).getTrainerCoords());
//                            coords_.getLevel().setLevelIndex((byte) 0);
//                            coords_.getLevel().setPoint(new Point(league_.getBegin()));
//                            list_.add(coords_);
                        }
                        continue;
                    }
                    Level l_ = getPlaces().getVal(l.getNumberPlace()).getLevelByCoords(l);
                    if (l_ instanceof LevelWithWildPokemon) {
                        if (((LevelWithWildPokemon)l_).getDualFights().contains(l.getLevel().getPoint())) {
//                            list_.add(c);
                        }
                    }
                    if (l_ instanceof LevelIndoorGym) {
                        City city_ = (City) getPlaces().getVal(l.getNumberPlace());
                        Coords coords_ = new Coords(l);
                        coords_.getLevel().setPoint(city_.getBuildings().getVal(l.getInsideBuilding()).getExitCity());
                        coordsCondBis_.getVal(c).addAll(allTiles_.getVal(coords_));
//                        list_.add(coords_);
                    }
                }*/
                for (Coords a: newLeaders_.getKeys()) {
                    if (!accessCondition.getVal(c).containsObj(a)) {
                        continue;
                    }
                    Coords c_ = newLeaders_.getVal(a);
                    coordsCondBis_.getVal(c).addAllElts(allTiles_.getVal(c_));
//                    Place place_ = getPlaces().getVal(a.getNumberPlace());
//                    if (place_ instanceof League) {
//                        League league_ = (League)place_;
//                        Coords coords_ = new Coords(c);
//                        coords_.getLevel().setLevelIndex((byte) 0);
//                        coords_.getLevel().setPoint(new Point(league_.getBegin()));
//                        if (allTiles_.contains(coords_)) {
//                            coordsCondBis_.getVal(c).addAll(allTiles_.getVal(coords_));
//                        }
//                        //if (allTiles_.contains(league_.getAccessCoords())) {
//                            coordsCondBis_.getVal(c).addAll(allTiles_.getVal(league_.getAccessCoords()));
//                        //}
//                        continue;
//                    }
//                    Level l_ = getPlaces().getVal(a.getNumberPlace()).getLevelByCoords(a);
//                    if (l_ instanceof LevelWithWildPokemon) {
//                        if (((LevelWithWildPokemon)l_).getDualFights().contains(a.getLevel().getPoint())) {
//                            //if (allTiles_.contains(a)) {
//                                coordsCondBis_.getVal(c).addAll(allTiles_.getVal(a));
//                            //}
//                        }
//                    }
//                    if (l_ instanceof LevelIndoorGym) {
//                        City city_ = (City) getPlaces().getVal(a.getNumberPlace());
//                        Coords coords_ = new Coords(a);
//                        coords_.getLevel().setPoint(city_.getBuildings().getVal(a.getInsideBuilding()).getExitCity());
//                        //if (allTiles_.contains(coords_)) {
//                            coordsCondBis_.getVal(c).addAll(allTiles_.getVal(coords_));
//                        //}
//                    }
                }
                /*for (Coords l: newLeaders_) {
                    if (accessCondition.getVal(c).containsObj(l)) {
                        coordsCondBis_.getVal(c).add(l);
                    } else {
                    }
                }*/
                //coordsCondBis_.getVal(c).addAll(newLeaders_);//INIT
//                for (Coords l: accessibleLeaders_) {
//                    if (allTiles_.contains(l)) {
//                        coordsCondBis_.getVal(c).addAll(allTiles_.getVal(l));
//                    }
//                }
                //coordsCondBis_.getVal(c).addAll(newLeaders_);
                coordsCondBis_.getVal(c).removeDuplicates();
            }
            for (Coords c:accessibleLeaders_) {
                Place pl_ = places.getVal(c.getNumberPlace());
                if (!(pl_ instanceof League)) {
                    continue;
                }
                leagues.add(c);
            }
            leaders_.addAllElts(accessibleLeaders_);
            coordsCond_.clear();
            Condition initCond_ = new Condition();
            for (EntryCust<Coords,Condition> e:coordsCondBis_.entryList()) {
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
        //Check if there is no choice that makes harder to build a solution
        EqList<Coords> accessLeagues_ = new EqList<Coords>();
        for (short p: places.getKeys()) {
            Place pl_ = places.getVal(p);
            if (pl_ instanceof League) {
                League l_ = (League) pl_;
                accessLeagues_.add(l_.getAccessCoords());
            }
        }
        for (Coords c: accessCondition.getKeys()) {
            if (!accessibility.contains(c)) {
                continue;
            }
            if (accessLeagues_.containsObj(c)) {
                continue;
            }
            ObjectMap<Coords,Condition> conditions_ = getNext(c, accessibility.getVal(c));
            boolean contained_ = false;
            EqList<Coords> elts_ = accessCondition.getVal(c);
            for (Coords n: conditions_.getKeys()) {
                if (accessCondition.contains(n) && !accessLeagues_.containsObj(n)) {
                    EqList<Coords> condLoc_ = accessCondition.getVal(n);
                    if (Coords.equalsSet(condLoc_, elts_)) {
                        continue;
                    }
                }
                Condition condition_ = accessibility.getVal(n);
                for (Coords a: condition_) {
                    if (elts_.containsObj(a)) {
                        contained_ = true;
                    }
                }
            }
            if (!contained_) {
                throw new DataException();
            }
        }
//        for (Coords c: accessCondition.getKeys()) {
//            CustList<Coords> coords_ = new CustList<>();
//            if (accessibility.contains(c)) {
//                Condition c_ = new Condition();
//                c_.addAll(accessibility.getVal(c));
//                for (Condition n: getNext(c, c_).values()) {
//                    for (Coords t: n) {
//                        coords_.add(t);
//                    }
//                }
//                coords_.removeDuplicates();
//                accessCondition.getVal(c).clear();
//                accessCondition.getVal(c).addAll(coords_);
//            }
//        }
//        for (Coords c: accessCondition.getKeys()) {
//            CustList<Coords> coords_ = new CustList<>();
//            if (accessibility.contains(c)) {
//                for (Condition n: getNext(c, accessibility.getVal(c)).values()) {
//                    coords_.addAll(n);
//                }
//                coords_.removeDuplicates();
//                accessCondition.getVal(c).clear();
//                accessCondition.getVal(c).addAll(coords_);
//            }
//        }
//        for (Coords c: accessCondition.getKeys()) {
//        }
    }
    public ObjectMap<Coords,Condition> possibleNeighbours(ObjectMap<Coords,Condition> _visitedGl,
            ObjectMap<Coords,Condition> _previousVisited) {
        ObjectMap<Coords,Condition> visitedTiles_ = new ObjectMap<Coords, Condition>(_previousVisited);
        EqList<Coords> roots_ = _previousVisited.getKeys();
        EqList<Coords> currentTiles_ = roots_;
        EqList<Coords> newPlaces_ = new EqList<Coords>();
        while(true) {
            for (Coords i: currentTiles_) {
                if (accessCondition.contains(i)) {
                    if (!roots_.containsObj(i)) {
                        continue;
                    }
                }
                ObjectMap<Coords,Condition> neighbours_ = getNext(i,visitedTiles_.getVal(i));
                for (EntryCust<Coords,Condition> e: neighbours_.entryList()) {
                    Coords n_ = e.getKey();
                    if (visitedTiles_.contains(n_)) {
                        continue;
                    }
                    if (_visitedGl.contains(n_)) {
                        continue;
                    }
                    visitedTiles_.put(n_,e.getValue());
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
    public ObjectMap<Coords,Coords> leaders(Listable<Coords> _accessibleCoords) {
        ObjectMap<Coords,Coords> list_ = new ObjectMap<Coords,Coords>();
        for (Coords c: _accessibleCoords) {
            Place place_ = getPlaces().getVal(c.getNumberPlace());
            if (place_ instanceof League) {
                League league_ = (League)place_;
                byte ind_ = c.getLevel().getLevelIndex();
                if (Numbers.eq(ind_+1,league_.getRooms().size())) {
                    Coords coords_ = new Coords(c);
//                    coords_.getLevel().setPoint(league_.getRooms().get(ind_).getTrainerCoords());
                    coords_.getLevel().setLevelIndex((byte) 0);
                    coords_.getLevel().setPoint(new Point(league_.getBegin()));
                    list_.put(coords_, league_.getAccessCoords());
                }
                continue;
            }
            Level l_ = getPlaces().getVal(c.getNumberPlace()).getLevelByCoords(c);
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon)l_).getDualFights().contains(c.getLevel().getPoint())) {
                    list_.put(c, c);
                }
            }
            if (l_ instanceof LevelIndoorGym) {
                Coords coords_ = new Coords(c);
                coords_.getLevel().setPoint(((LevelIndoorGym)l_).getGymLeaderCoords());
                City city_ = (City) place_;
                Coords coordsValue_ = new Coords(c);
                coordsValue_.getLevel().setPoint(city_.getBuildings().getVal(c.getInsideBuilding()).getExitCity());
                list_.put(coords_, coordsValue_);
            }
        }
        return list_;
    }

    //BEGIN
    public EqList<Coords> accessibleLeaders(EqList<Coords> _accessibleCoords) {
        EqList<Coords> list_ = new EqList<Coords>();
        for (Coords c: _accessibleCoords) {
            Place place_ = getPlaces().getVal(c.getNumberPlace());
            if (place_ instanceof League) {
                League league_ = (League)place_;
                byte ind_ = c.getLevel().getLevelIndex();
                if (Numbers.eq(ind_+1,league_.getRooms().size())) {
                    list_.add(c);
                }
                continue;
            }
            Level l_ = getPlaces().getVal(c.getNumberPlace()).getLevelByCoords(c);
            if (l_ instanceof LevelWithWildPokemon) {
                if (((LevelWithWildPokemon)l_).getDualFights().contains(c.getLevel().getPoint())) {
                    list_.add(c);
                }
            }
            if (l_ instanceof LevelIndoorGym) {
                list_.add(c);
            }
        }
        list_.removeDuplicates();
        return list_;
    }
    //END
    public ObjectMap<Coords,Condition> getNext(Coords _id,Condition _condition) {
        ObjectMap<Coords,Condition> return_ = new ObjectMap<Coords,Condition>();
        Condition gymCond_ = _condition;
        Place place_ = places.getVal(_id.getNumberPlace());
        Point pt_ = _id.getLevel().getPoint();
        for (EntryCust<Short,Place> e: places.entryList()) {
            Place pl_ = e.getValue();
            if (!(pl_ instanceof League)) {
                continue;
            }
            if (Coords.eq(((League)pl_).getAccessCoords(),_id)) {
                Coords coords_ = new Coords();
                coords_.setNumberPlace(e.getKey());
                coords_.setLevel(new LevelPoint());
                coords_.getLevel().setLevelIndex((byte) 0);
                coords_.getLevel().setPoint(new Point(((League)pl_).getBegin()));
                Condition condition_ = initCondition(coords_,gymCond_);
                return_.put(coords_,condition_);
                break;
            }
        }
        if (place_ instanceof InitializedPlace) {
            InitializedPlace pl_ = (InitializedPlace) place_;
            if (pl_.getLinksWithCaves().contains(pt_)) {
                Link link_ = pl_.getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_,gymCond_);
                return_.put(coords_,cond_);
            }
            Level level_ = place_.getLevelByCoords(_id);
            ObjectMap<PlaceInterConnect,Coords> links_ = pl_.getPointsWithCitiesAndOtherRoads();
            if (!_id.isInside()) {
                for (Direction d: Direction.values()) {
                    Point ptNext_ = new Point(pt_);
                    ptNext_.moveTo(d);
                    if (!level_.isEmpty(ptNext_)) {
                        continue;
                    }
                    try {
                        Block block_ = level_.getBlockByPoint(ptNext_);
                        if (block_.getType() == EnvironmentType.NOTHING) {
                            if (pl_ instanceof City) {
                                if(((City)pl_).getBuildings().contains(ptNext_)) {
                                    Building building_ = ((City)pl_).getBuildings().getVal(ptNext_);
//                                    if (building_ instanceof Gym) {
//                                        Coords coords_ = new Coords(_id);
//                                        coords_.affectInside(ptNext_);
//                                        coords_.getLevel().setPoint(building_.getExitCity());
//                                        Condition cond_ = initCondition(coords_,gymCond_);
//                                        return_.put(coords_,cond_);
//                                    }
                                    Coords coords_ = new Coords(_id);
                                    coords_.affectInside(ptNext_);
                                    coords_.getLevel().setPoint(building_.getExitCity());
                                    Condition cond_ = initCondition(coords_,gymCond_);
                                    return_.put(coords_,cond_);
                                    continue;
                                }
                            }
                            if (pl_.getLinksWithCaves().contains(ptNext_)) {
                                Coords coords_ = new Coords(_id);
                                coords_.getLevel().setPoint(ptNext_);
                                Condition cond_ = initCondition(coords_,gymCond_);
                                return_.put(coords_,cond_);
                            }
                            continue;
                        }
                        Coords coords_ = new Coords(_id);
                        coords_.getLevel().setPoint(ptNext_);
                        Condition cond_ = initCondition(coords_,gymCond_);
                        return_.put(coords_,cond_);
                    } catch (BlockNotFoundException _0) {
                        if (links_.contains(new PlaceInterConnect(pt_,d))) {
                            Coords coords_ = links_.getVal(new PlaceInterConnect(pt_,d));
                            try {
                                InitializedPlace plNext_ = (InitializedPlace) places.getVal(coords_.getNumberPlace());
                                Level levelNext_ = plNext_.getLevel();
                                Point newPoint_ = coords_.getLevel().getPoint();
                                levelNext_.getEnvBlockByPoint(newPoint_);
//                            } catch (Exception e2) {
                            } catch (BlockNotFoundException _1) {
                                continue;
                            }
                            Condition cond_ = initCondition(coords_,gymCond_);
                            return_.put(coords_,cond_);
                            continue;
                        }
                    }
                }
            }
        } else if (place_ instanceof Cave) {
            Cave cave_ = (Cave) place_;
            if (cave_.getLinksWithOtherPlaces().contains(_id.getLevel())) {
                Link link_ = cave_.getLinksWithOtherPlaces().getVal(_id.getLevel());
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_,gymCond_);
                return_.put(coords_,cond_);
            }
            LevelCave level_ = (LevelCave)cave_.getLevels().getVal(_id.getLevel().getLevelIndex());
            if (level_.getLinksOtherLevels().contains(_id.getLevel().getPoint())) {
                Link link_ = level_.getLinksOtherLevels().getVal(_id.getLevel().getPoint());
                Coords coords_ = link_.getCoords();
                Condition cond_ = initCondition(coords_,gymCond_);
                return_.put(coords_,cond_);
            }
            for (Direction d: Direction.values()) {
                Point ptNext_ = new Point(pt_);
                ptNext_.moveTo(d);
                if (!level_.isEmpty(ptNext_)) {
                    continue;
                }
                try {
                    Block block_ = level_.getBlockByPoint(ptNext_);
                    if (block_.getType() == EnvironmentType.NOTHING) {
                        Coords coords_ = new Coords(_id);
                        coords_.getLevel().setPoint(ptNext_);
                        if (cave_.getLinksWithOtherPlaces().contains(coords_.getLevel())) {
                            Condition cond_ = initCondition(coords_,gymCond_);
                            return_.put(coords_,cond_);
                        }
                        continue;
                    }
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setPoint(ptNext_);
                    Condition cond_ = initCondition(coords_,gymCond_);
                    return_.put(coords_,cond_);
                } catch (BlockNotFoundException _0) {
                }
            }
        } else {
            //place_ instanceof League
            League league_ = (League) place_;
            byte levelIndex_ = _id.getLevel().getLevelIndex();
            LevelLeague level_ = league_.getRooms().get(levelIndex_);
            if (Point.eq(level_.getAccessPoint(),_id.getLevel().getPoint())) {
                levelIndex_++;
                if (league_.getRooms().isValidIndex(levelIndex_)) {
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setLevelIndex(levelIndex_);
                    coords_.getLevel().setPoint(level_.getNextLevelTarget());
                    Condition cond_ = initCondition(coords_,gymCond_);
                    return_.put(coords_,cond_);
                }
            }
            for (Direction d: Direction.values()) {
                Point ptNext_ = new Point(pt_);
                ptNext_.moveTo(d);
                if (!level_.isEmpty(ptNext_)) {
                    continue;
                }
                try {
                    level_.getEnvBlockByPoint(ptNext_);
                    Coords coords_ = new Coords(_id);
                    coords_.getLevel().setPoint(ptNext_);
                    Condition cond_ = initCondition(coords_,gymCond_);
                    return_.put(coords_,cond_);
                } catch (BlockNotFoundException _0) {
                }
            }
        }
        return return_;
    }

    Condition initCondition(Coords _coords,Condition _gymCondition) {
        if (!accessCondition.contains(_coords)) {
            return _gymCondition;
        }
        Condition condition_ = new Condition();
        condition_.addAllElts(_gymCondition);
        condition_.addAllElts(accessCondition.getVal(_coords));
        return condition_;
    }

    boolean validConditions(EqList<Coords> _accessCoords,ObjectMap<Coords,Condition> _condition) {
        ObjectMap<Coords,EqList<Coords>> groups_ = new ObjectMap<Coords,EqList<Coords>>();
        Condition defaultCondition_ = new Condition();
        for (Coords c: _accessCoords) {
            boolean continue_ = false;
            for (EqList<Coords> l: groups_.values()) {
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
                for (Coords c2_: currentElts_) {
                    ObjectMap<Coords,Condition> next_ = getNext(c2_,defaultCondition_);
                    for (Coords n: next_.getKeys()) {
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
            groups_.put(c,eq_);
        }
        for (EntryCust<Coords,EqList<Coords>> e: groups_.entryList()) {
            Condition cond_ = _condition.getVal(e.getKey());
            for (Coords c2_: e.getValue()) {
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
                return w_.getDualFights().getVal(_coords.getLevel().getPoint()).getNames().join(SPACE);
            }
        }
        return DataBase.EMPTY_STRING;
    }

    public void insertLinePlace(Coords _coords, short _nb) {
        Place p_ = places.getVal(_coords.getNumberPlace());
        //p_.insertLine(_nb, _coords);
        if (p_ instanceof Cave) {
            EqList<Coords> impactedPlaces_ = new EqList<Coords>();
            ObjectMap<LevelPoint,Link> links_ = ((Cave)p_).getLinksWithOtherPlaces();
            for (LevelPoint lp_: links_.getKeys()) {
                Link c_ = links_.getVal(lp_);
                if (impactedPlaces_.containsObj(c_.getCoords())) {
                    continue;
                }
                impactedPlaces_.add(c_.getCoords());
            }
            for (Coords c: impactedPlaces_) {
                Place place_ = places.getVal(c.getNumberPlace());
                if (!(place_ instanceof InitializedPlace)) {
                    continue;
                }
                ObjectMap<Point,Link> linksLoc_ = ((InitializedPlace)place_).getLinksWithCaves();
                for (Point p: linksLoc_.getKeys()) {
                    Link l_ = linksLoc_.getVal(p);
                    Coords c_ = l_.getCoords();
                    if (!Numbers.eq(c_.getNumberPlace(),_coords.getNumberPlace())) {
                        continue;
                    }
                    if (!Numbers.eq(c_.getLevel().getLevelIndex(),_coords.getLevel().getLevelIndex())) {
                        continue;
                    }
                    Point pLoc_ = c_.getLevel().getPoint();
                    if (pLoc_.gety() < _coords.getLevel().getPoint().gety()) {
                        continue;
                    }
                    pLoc_.sety((short) (pLoc_.gety()+_nb));
                }
            }
        } else if (p_ instanceof InitializedPlace) {
            EqList<Coords> impactedPlaces_ = new EqList<Coords>();
            ObjectMap<Point,Link> links_ = ((InitializedPlace)p_).getLinksWithCaves();
            for (Point lp_: links_.getKeys()) {
                Link c_ = links_.getVal(lp_);
                if (impactedPlaces_.containsObj(c_.getCoords())) {
                    continue;
                }
                impactedPlaces_.add(c_.getCoords());
            }
            for (Coords c: impactedPlaces_) {
                Place place_ = places.getVal(c.getNumberPlace());
                if (!(place_ instanceof Cave)) {
                    continue;
                }
                ObjectMap<LevelPoint,Link> linksLoc_ = ((Cave)place_).getLinksWithOtherPlaces();
                for (LevelPoint p: linksLoc_.getKeys()) {
                    Link c_ = linksLoc_.getVal(p);
                    Coords coords_ = c_.getCoords();
                    if (!Numbers.eq(coords_.getNumberPlace(),_coords.getNumberPlace())) {
                        continue;
                    }
                    if (!Numbers.eq(coords_.getLevel().getLevelIndex(),_coords.getLevel().getLevelIndex())) {
                        continue;
                    }
                    Point pLoc_ = coords_.getLevel().getPoint();
                    if (pLoc_.gety() < _coords.getLevel().getPoint().gety()) {
                        continue;
                    }
                    pLoc_.sety((short) (pLoc_.gety()+_nb));
                }
            }
        }
    }

    public void insertColumnPlace(Coords _coords, short _nb) {
        Place p_ = places.getVal(_coords.getNumberPlace());
        //p_.insertColumn(_nb, _coords);
        if (p_ instanceof Cave) {
            EqList<Coords> impactedPlaces_ = new EqList<Coords>();
            ObjectMap<LevelPoint,Link> links_ = ((Cave)p_).getLinksWithOtherPlaces();
            for (LevelPoint lp_: links_.getKeys()) {
                Link c_ = links_.getVal(lp_);
                if (impactedPlaces_.containsObj(c_.getCoords())) {
                    continue;
                }
                impactedPlaces_.add(c_.getCoords());
            }
            for (Coords c: impactedPlaces_) {
                Place place_ = places.getVal(c.getNumberPlace());
                if (!(place_ instanceof InitializedPlace)) {
                    continue;
                }
                ObjectMap<Point,Link> linksLoc_ = ((InitializedPlace)place_).getLinksWithCaves();
                for (Point p: linksLoc_.getKeys()) {
                    Link l_ = linksLoc_.getVal(p);
                    Coords c_ = l_.getCoords();
                    if (!Numbers.eq(c_.getNumberPlace(),_coords.getNumberPlace())) {
                        continue;
                    }
                    if (!Numbers.eq(c_.getLevel().getLevelIndex(),_coords.getLevel().getLevelIndex())) {
                        continue;
                    }
                    Point pLoc_ = c_.getLevel().getPoint();
                    if (pLoc_.getx() < _coords.getLevel().getPoint().getx()) {
                        continue;
                    }
                    pLoc_.setx((short) (pLoc_.getx()+_nb));
                }
            }
        } else if (p_ instanceof InitializedPlace) {
            EqList<Coords> impactedPlaces_ = new EqList<Coords>();
            ObjectMap<Point,Link> links_ = ((InitializedPlace)p_).getLinksWithCaves();
            for (Point lp_: links_.getKeys()) {
                Link c_ = links_.getVal(lp_);
                if (impactedPlaces_.containsObj(c_.getCoords())) {
                    continue;
                }
                impactedPlaces_.add(c_.getCoords());
            }
            for (Coords c: impactedPlaces_) {
                Place place_ = places.getVal(c.getNumberPlace());
                if (!(place_ instanceof Cave)) {
                    continue;
                }
                ObjectMap<LevelPoint,Link> linksLoc_ = ((Cave)place_).getLinksWithOtherPlaces();
                for (LevelPoint p: linksLoc_.getKeys()) {
                    Link c_ = linksLoc_.getVal(p);
                    Coords coords_ = c_.getCoords();
                    if (!Numbers.eq(coords_.getNumberPlace(),_coords.getNumberPlace())) {
                        continue;
                    }
                    if (!Numbers.eq(coords_.getLevel().getLevelIndex(),_coords.getLevel().getLevelIndex())) {
                        continue;
                    }
                    Point pLoc_ = coords_.getLevel().getPoint();
                    if (pLoc_.getx() < _coords.getLevel().getPoint().getx()) {
                        continue;
                    }
                    pLoc_.setx((short) (pLoc_.getx()+_nb));
                }
            }
        }
    }

    public boolean validSavedLink() {
        int nbPlaces_ = places.size();
        for (short i=CustList.FIRST_INDEX;i<nbPlaces_;i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            Level level_ = pl_.getLevel();
            Limits limits_ = level_.limits();
            Point leftTopPoint_ = limits_.getTopLeft();
            Point rightBottomPoint_ = limits_.getBottomRight();
            for (PlaceInterConnect k: pl_.getSavedlinks().getKeys()) {
                if (k.getDir() == Direction.UP) {
                    if (!Numbers.eq(leftTopPoint_.gety(),k.getSource().gety())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.DOWN) {
                    if (!Numbers.eq(rightBottomPoint_.gety(),k.getSource().gety())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.LEFT) {
                    if (!Numbers.eq(leftTopPoint_.getx(),k.getSource().getx())) {
                        return false;
                    }
                }
                if (k.getDir() == Direction.RIGHT) {
                    if (!Numbers.eq(rightBottomPoint_.getx(),k.getSource().getx())) {
                        return false;
                    }
                }
                Coords coords_ = pl_.getSavedlinks().getVal(k);
                InitializedPlace other_ = (InitializedPlace) places.getVal(coords_.getNumberPlace());
                PlaceInterConnect key_ = new PlaceInterConnect(coords_.getLevel().getPoint(),k.getDir().getOpposite());
                if (!other_.getSavedlinks().contains(key_)) {
                    return false;
                }
                Coords otherCoords_ = other_.getSavedlinks().getVal(key_);
                if (!Numbers.eq(otherCoords_.getNumberPlace(),i)) {
                    return false;
                }
                if (!Point.eq(k.getSource(), otherCoords_.getLevel().getPoint())) {
                    return false;
                }
            }
        }
        return true;
    }
    public void initializeLinks() {
        NumberMap<Short,EqList<PlaceInterConnect>> visited_ = new NumberMap<Short,EqList<PlaceInterConnect>>();
        int nbPlaces_ = places.size();
        for (short i=CustList.FIRST_INDEX;i<nbPlaces_;i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            pl_.setPointsWithCitiesAndOtherRoads(new ObjectMap<PlaceInterConnect,Coords>());
        }
        for (short i=CustList.FIRST_INDEX;i<nbPlaces_;i++) {
            Place place_ = places.getVal(i);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            InitializedPlace pl_ = (InitializedPlace) place_;
            for (PlaceInterConnect k: pl_.getSavedlinks().getKeys()) {
                if (visited_.contains(i) && visited_.getVal(i).containsObj(k)) {
                    continue;
                }
                Coords coords_ = pl_.getSavedlinks().getVal(k);
                Point pt_ = coords_.getLevel().getPoint();
                short i_ = coords_.getNumberPlace();
                if (!visited_.contains(i_)) {
                    EqList<PlaceInterConnect> v_ = new EqList<PlaceInterConnect>();
                    v_.add(new PlaceInterConnect(pt_,k.getDir().getOpposite()));
                    visited_.put(i_, v_);
                } else {
                    EqList<PlaceInterConnect> v_ = visited_.getVal(i_);
                    v_.add(new PlaceInterConnect(pt_,k.getDir().getOpposite()));
                }
                join(i, coords_.getNumberPlace(), k.getSource(), pt_, k.getDir());
            }
        }
    }
    public void join(short _pl1, short _pl2, Point _p1, Point _p2, Direction _dir1) {
        Place place1_ = places.getVal(_pl1);
        Place place2_ = places.getVal(_pl2);
        Level l1_ = ((InitializedPlace)place1_).getLevel();
        Level l2_ = ((InitializedPlace)place2_).getLevel();
        EqList<PlaceInterConnect> keys1_ = new EqList<PlaceInterConnect>();
        EqList<PlaceInterConnect> keys2_ = new EqList<PlaceInterConnect>();
        Limits limits1_=l1_.limits();
        Limits limits2_=l2_.limits();
        Point leftTopPointOne_=limits1_.getTopLeft();
        Point rightBottomPointOne_=limits1_.getBottomRight();
        Point leftTopPointTwo_=limits2_.getTopLeft();
        Point rightBottomPointTwo_=limits2_.getBottomRight();
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
            int u_ = Math.min(p1_.getx()-leftTopPointOne_.getx(),p2_.getx()-leftTopPointTwo_.getx());
            int xtop1_ = p1_.getx() - u_;
            int xtop2_ = p2_.getx() - u_;
            int d_ = Math.min(rightBottomPointOne_.getx()-p1_.getx(),rightBottomPointTwo_.getx()-p2_.getx());
            length_ = u_+d_+1;
            for (short i = CustList.FIRST_INDEX;i<length_;i++) {
                keys1_.add(new PlaceInterConnect(new Point((short) (xtop1_+i),p1_.gety()),_dir1));
                keys2_.add(new PlaceInterConnect(new Point((short) (xtop2_+i),p2_.gety()),_dir1.getOpposite()));
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
            int u_ = Math.min(p1_.gety()-leftTopPointOne_.gety(),p2_.gety()-leftTopPointTwo_.gety());
            int ytop1_ = p1_.gety() - u_;
            int ytop2_ = p2_.gety() - u_;
            int d_ = Math.min(rightBottomPointOne_.gety()-p1_.gety(),rightBottomPointTwo_.gety()-p2_.gety());
            length_ = u_+d_+1;
            for (short i = CustList.FIRST_INDEX;i<length_;i++) {
                keys1_.add(new PlaceInterConnect(new Point(p1_.getx(),(short) (ytop1_+i)),_dir1));
                keys2_.add(new PlaceInterConnect(new Point(p2_.getx(),(short) (ytop2_+i)),_dir1.getOpposite()));
            }
        }
        Coords coords1_ = new Coords();
        coords1_.setNumberPlace(_pl2);
        coords1_.setLevel(new LevelPoint());
        coords1_.getLevel().setLevelIndex((byte) 0);
        coords1_.getLevel().setPoint(new Point(p2_));
        ((InitializedPlace)place1_).addSavedLink(new PlaceInterConnect(p1_, _dir1), coords1_);
        Coords coords2_ = new Coords();
        coords2_.setNumberPlace(_pl1);
        coords2_.setLevel(new LevelPoint());
        coords2_.getLevel().setLevelIndex((byte) 0);
        coords2_.getLevel().setPoint(new Point(p1_));
        ((InitializedPlace)place2_).addSavedLink(new PlaceInterConnect(p2_, _dir1.getOpposite()), coords2_);
        ObjectMap<PlaceInterConnect,Coords> join1_ = new ObjectMap<PlaceInterConnect,Coords>();
        ObjectMap<PlaceInterConnect,Coords> join2_ = new ObjectMap<PlaceInterConnect,Coords>();
        for (short i = CustList.FIRST_INDEX;i<length_;i++) {
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
        ((InitializedPlace)place1_).getPointsWithCitiesAndOtherRoads().putAllMap(join1_);
        ((InitializedPlace)place2_).getPointsWithCitiesAndOtherRoads().putAllMap(join2_);
    }
    public void unjoin(short _pl1, short _pl2) {
        Place p1_ = places.getVal(_pl1);
        Place p2_ = places.getVal(_pl2);
        ObjectMap<PlaceInterConnect,Coords> join1_ = ((InitializedPlace)p1_).getPointsWithCitiesAndOtherRoads();
        ObjectMap<PlaceInterConnect,Coords> join2_ = ((InitializedPlace)p2_).getPointsWithCitiesAndOtherRoads();
        EqList<PlaceInterConnect> keys1_ = join1_.getKeys();
        EqList<PlaceInterConnect> keys2_ = join2_.getKeys();
        for (PlaceInterConnect p: keys1_) {
            if (Numbers.eq(join1_.getVal(p).getNumberPlace(),_pl2)) {
                ((InitializedPlace)p1_).deleteSavedLink(p);
                join1_.removeKey(p);
            }
        }
        for (PlaceInterConnect p: keys2_) {
            if (Numbers.eq(join2_.getVal(p).getNumberPlace(),_pl1)) {
                ((InitializedPlace)p2_).deleteSavedLink(p);
                join2_.removeKey(p);
            }
        }
    }
    public void joinLevelCave(short _place,LevelPoint _l1,LevelPoint _l2, String _imgName1, String _imgName2) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_place);
        coords_.setLevel(_l1);
        if (!isEmptyForAdding(coords_)) {
            return;
        }
        coords_ = new Coords();
        coords_.setNumberPlace(_place);
        coords_.setLevel(_l2);
        if (!isEmptyForAdding(coords_)) {
            return;
        }
        joinLevelCave(_place, _l1, _l2, _imgName1);
        joinLevelCave(_place, _l2, _l1, _imgName2);
    }
    public void joinLevelCave(short _place,LevelPoint _l1,LevelPoint _l2, String _imgName) {
        Cave cave_ = (Cave)places.getVal(_place);
        LevelCave l1_ = (LevelCave)cave_.getLevels().getVal(_l1.getLevelIndex());
        ObjectMap<Point,Link> links_ = l1_.getLinksOtherLevels();
        if (links_.contains(_l1.getPoint())) {
            Link link_ = links_.getVal(_l1.getPoint());
            link_.getCoords().setNumberPlace(_place);
            link_.getCoords().setLevel(_l2);
            link_.setFileName(_imgName);
        } else {
            Link link_ = new Link();
            link_.setCoords(new Coords());
            link_.getCoords().setNumberPlace(_place);
            link_.getCoords().setLevel(_l2);
            link_.setFileName(_imgName);
            links_.put(_l1.getPoint(),link_);
        }
    }
    public void unjoinLevelCave(short _place,LevelPoint _l1) {
        Cave cave_ = (Cave)places.getVal(_place);
        LevelCave l1_ = (LevelCave)cave_.getLevels().getVal(_l1.getLevelIndex());
        ObjectMap<Point,Link> links_ = l1_.getLinksOtherLevels();
        Link link_ = links_.getVal(_l1.getPoint());
        links_.removeKey(_l1.getPoint());
        l1_ = (LevelCave)cave_.getLevels().getVal(link_.getCoords().getLevel().getLevelIndex());
        links_ = l1_.getLinksOtherLevels();
        links_.removeKey(link_.getCoords().getLevel().getPoint());
    }
    void unjoinLevelCave(short _place,byte _level) {
        Cave cave_ = (Cave)places.getVal(_place);
        LevelCave l1_ =(LevelCave)cave_.getLevels().getVal(_level);
        ObjectMap<Point,Link> links_ = l1_.getLinksOtherLevels();
        EqList<Point> keys_ = links_.getKeys();
        for (Point p: keys_) {
            Link l_ = links_.getVal(p);
            if (Numbers.eq(l_.getCoords().getLevel().getLevelIndex(), _level)) {
                links_.removeKey(p);
            }
        }
        links_.clear();
    }
    public void joinCavePlace(Coords _coordsCave,Coords _coordsPlace, String _imgName1, String _imgName2) {
        if (!isEmptyForAdding(_coordsCave)) {
            return;
        }
        if (!isEmptyForAdding(_coordsPlace)) {
            return;
        }
        Cave cave_ = (Cave)places.getVal(_coordsCave.getNumberPlace());
        InitializedPlace place_ = (InitializedPlace)places.getVal(_coordsPlace.getNumberPlace());
        ObjectMap<LevelPoint,Link> links1_ = cave_.getLinksWithOtherPlaces();
        Link link1_ = new Link();
        link1_.setCoords(_coordsPlace);
        link1_.setFileName(_imgName1);
        links1_.put(_coordsCave.getLevel(), link1_);
        ObjectMap<Point,Link> links2_ = place_.getLinksWithCaves();
        Link link2_ = new Link();
        link2_.setCoords(_coordsCave);
        link2_.setFileName(_imgName2);
        links2_.put(_coordsPlace.getLevel().getPoint(), link2_);
    }
    public void joinCavePlace(Coords _coordsCave,Coords _coordsPlace, String _imgName1, String _imgName2, Direction... _dirs) {
        if (!isEmptyForAdding(_coordsCave)) {
            return;
        }
        if (!isEmptyForAdding(_coordsPlace)) {
            return;
        }
        if (_dirs.length == CustList.SIZE_EMPTY) {
            Cave cave_ = (Cave)places.getVal(_coordsCave.getNumberPlace());
            InitializedPlace place_ = (InitializedPlace)places.getVal(_coordsPlace.getNumberPlace());
            ObjectMap<LevelPoint,Link> links1_ = cave_.getLinksWithOtherPlaces();
            Link link1_ = new Link();
            link1_.setCoords(_coordsPlace);
            link1_.setFileName(_imgName1);
            links1_.put(_coordsCave.getLevel(), link1_);
            ObjectMap<Point,Link> links2_ = place_.getLinksWithCaves();
            Link link2_ = new Link();
            link2_.setCoords(_coordsCave);
            link2_.setFileName(_imgName2);
            links2_.put(_coordsPlace.getLevel().getPoint(), link2_);
        } else if (_dirs.length == DataBase.ONE_POSSIBLE_CHOICE) {
            if (!isEmptyForAdding(_coordsCave)) {
                return;
            }
            if (!isEmptyForAdding(_coordsPlace)) {
                return;
            }
            if (!isEmptyForAdding(closestTile(_coordsPlace, _dirs[CustList.FIRST_INDEX]))) {
                return;
            }
            Cave cave_ = (Cave)places.getVal(_coordsCave.getNumberPlace());
            InitializedPlace place_ = (InitializedPlace)places.getVal(_coordsPlace.getNumberPlace());
            ObjectMap<LevelPoint,Link> links1_ = cave_.getLinksWithOtherPlaces();
            Link link1_ = new Link();
            link1_.setCoords(_coordsPlace);
            link1_.setFileName(_imgName1);
            links1_.put(_coordsCave.getLevel(), link1_);
            ObjectMap<Point,Link> links2_ = place_.getLinksWithCaves();
            Link link2_ = new Link();
            link2_.setDir(_dirs[CustList.FIRST_INDEX]);
            link2_.setCoords(_coordsCave);
            link2_.setFileName(_imgName2);
            links2_.put(_coordsPlace.getLevel().getPoint(), link2_);
        } else {
            if (!isEmptyForAdding(_coordsCave)) {
                return;
            }
            if (!isEmptyForAdding(_coordsPlace)) {
                return;
            }
            if (!isEmptyForAdding(closestTile(_coordsCave, _dirs[CustList.FIRST_INDEX]))) {
                return;
            }
            if (!isEmptyForAdding(closestTile(_coordsPlace, _dirs[CustList.SECOND_INDEX]))) {
                return;
            }
            Cave cave_ = (Cave)places.getVal(_coordsCave.getNumberPlace());
            InitializedPlace place_ = (InitializedPlace)places.getVal(_coordsPlace.getNumberPlace());
            ObjectMap<LevelPoint,Link> links1_ = cave_.getLinksWithOtherPlaces();
            Link link1_ = new Link();
            link1_.setDir(_dirs[CustList.FIRST_INDEX]);
            link1_.setCoords(_coordsPlace);
            link1_.setFileName(_imgName1);
            links1_.put(_coordsCave.getLevel(), link1_);
            ObjectMap<Point,Link> links2_ = place_.getLinksWithCaves();
            Link link2_ = new Link();
            link2_.setDir(_dirs[CustList.SECOND_INDEX]);
            link2_.setCoords(_coordsCave);
            link2_.setFileName(_imgName2);
            links2_.put(_coordsPlace.getLevel().getPoint(), link2_);
        }
    }
    public void unjoinCavePlace(short _cave,LevelPoint _l1,short _place,LevelPoint _l2) {
        Cave cave_ = (Cave)places.getVal(_cave);
        InitializedPlace place_ = (InitializedPlace)places.getVal(_place);
        ObjectMap<LevelPoint,Link> links1_ = cave_.getLinksWithOtherPlaces();
        links1_.removeKey(_l1);
        ObjectMap<Point,Link> links2_ = place_.getLinksWithCaves();
        links2_.removeKey(_l2.getPoint());
    }

    void unjoinCavePlace(short _cave, short _place) {
        Cave cave_ = (Cave)places.getVal(_cave);
        InitializedPlace place_ = (InitializedPlace)places.getVal(_place);
        ObjectMap<Point,Link> join1_ = place_.getLinksWithCaves();
        ObjectMap<LevelPoint,Link> join2_ = cave_.getLinksWithOtherPlaces();
        EqList<Point> keys1_ = join1_.getKeys();
        EqList<LevelPoint> keys2_ = join2_.getKeys();
        for (Point p: keys1_) {
            if (Numbers.eq(join1_.getVal(p).getCoords().getNumberPlace(),_cave)) {
                join1_.removeKey(p);
            }
        }
        for (LevelPoint p: keys2_) {
            if (Numbers.eq(join2_.getVal(p).getCoords().getNumberPlace(),_place)) {
                join2_.removeKey(p);
            }
        }
    }

    public boolean isEmptyForAdding(Coords _coords) {
        Place place_ = places.getVal(_coords.getNumberPlace());
        if (!place_.isEmptyForAdding(_coords)) {
            return false;
        }
        Point pt_ = _coords.getLevel().getPoint();
        if (_coords.isInside()) {
            Building building_ = ((City)place_).getBuildings().getVal(_coords.getInsideBuilding());
            if (Point.eq(pt_, building_.getExitCity())) {
                return false;
            }
        }
        if (place_ instanceof League) {
            LevelPoint lPoint_ = _coords.getLevel();
            LevelLeague levelLeague_ =  (LevelLeague)((League)place_).getLevels().getVal(lPoint_.getLevelIndex());
            if (lPoint_.getLevelIndex() == CustList.FIRST_INDEX) {
                if (Point.eq(((League)place_).getBegin(), pt_)) {
                    return false;
                }
            } else {
                LevelLeague levelLeagueBack_ =  (LevelLeague)((League)place_).getLevels().getVal((byte) (lPoint_.getLevelIndex()-1));
                if (Point.eq(levelLeagueBack_.getNextLevelTarget(), pt_)) {
                    return false;
                }
            }
            if (Point.eq(levelLeague_.getAccessPoint(), pt_)) {
                return false;
            }
        }
        if (place_ instanceof City) {
            if (((City)place_).getBuildings().contains(pt_)) {
                return false;
            }
            if (((City)place_).getLinksWithCaves().contains(pt_)) {
                return false;
            }
        }
        if (place_ instanceof Road) {
            if (((Road)place_).getLinksWithCaves().contains(pt_)) {
                return false;
            }
        }
        LevelPoint lPoint_ = _coords.getLevel();
        if (place_ instanceof Cave) {
            LevelCave levelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(lPoint_.getLevelIndex());
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                return false;
            }
            if (((Cave)place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                return false;
            }
            return true;
        }
        for (Place p: places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords coords_ = ((League)p).getAccessCoords();
            if (Coords.eq(coords_, _coords)) {
                return false;
            }
        }
        return true;
    }

    public void moveElement(Coords _element, Point _target) {
        Place place_ = places.getVal(_element.getNumberPlace());
        Level level_ = place_.getLevelByCoords(_element);
        Point id_ = _element.getLevel().getPoint();
        if (!level_.isEmptyForAdding(_target)) {
            return;
        }
        level_.translateElement(id_, _target);
        if (beatGymLeader.containsObj(_element)) {
            Coords coords_ = new Coords(_element);
            coords_.getLevel().getPoint().affect(_target);
            beatGymLeader.removeObj(_element);
            beatGymLeader.add(coords_);
        }
    }
    public void moveLink(Coords _link, Point _target) {
        Place place_ = places.getVal(_link.getNumberPlace());
        Level level_ = place_.getLevelByCoords(_link);
        if (!level_.isEmptyForAdding(_target)) {
            return;
        }
        Point pt_ = _link.getLevel().getPoint();
        if (_link.isInside()) {
            Building building_ = ((City)place_).getBuildings().getVal(_link.getInsideBuilding());
            if (!Point.eq(pt_, building_.getExitCity())) {
                return;
            }
            building_.getExitCity().affect(_target);
            return;
        }
        if (place_ instanceof League) {
            LevelPoint lPoint_ = _link.getLevel();
            LevelLeague levelLeague_ = (LevelLeague)((League)place_).getLevels().getVal(lPoint_.getLevelIndex());
            if (lPoint_.getLevelIndex() == CustList.FIRST_INDEX) {
                if (Point.eq(((League)place_).getBegin(), pt_)) {
                    if (Point.eq(levelLeague_.getAccessPoint(), _target)) {
                        return;
                    }
                    ((League)place_).getBegin().affect(_target);
                }
            } else {
                LevelLeague levelLeagueBack_ = (LevelLeague)((League)place_).getLevels().getVal((byte) (lPoint_.getLevelIndex()-1));
                if (Point.eq(levelLeagueBack_.getNextLevelTarget(), pt_)) {
                    if (Point.eq(levelLeague_.getAccessPoint(), _target)) {
                        return;
                    }
                    levelLeagueBack_.getNextLevelTarget().affect(_target);
                }
            }
            if (Point.eq(levelLeague_.getAccessPoint(), pt_)) {
                if (lPoint_.getLevelIndex() > 0) {
                    LevelLeague levelLeagueBack_ = (LevelLeague)((League)place_).getLevels().getVal((byte) (lPoint_.getLevelIndex()-1));
                    if (Point.eq(levelLeagueBack_.getNextLevelTarget(), _target)) {
                        return;
                    }
                } else {
                    if (Point.eq(((League)place_).getBegin(), _target)) {
                        return;
                    }
                }
                levelLeague_.getAccessPoint().affect(_target);
            }
        }
        if (place_ instanceof City) {
            if (((City)place_).getBuildings().contains(_target)) {
                return;
            }
            if (((City)place_).getLinksWithCaves().contains(_target)) {
                return;
            }
            if (((City)place_).getBuildings().contains(pt_)) {
                ((City)place_).getBuildings().move(pt_, _target);
            }
            if (((City)place_).getLinksWithCaves().contains(pt_)) {
                Link link_ = ((City)place_).getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Cave placeTarget_ = (Cave) places.getVal(coords_.getNumberPlace());
                LevelPoint lPoint_ = coords_.getLevel();
                Link otherLink_;
                if (placeTarget_.getLinksWithOtherPlaces().contains(lPoint_)) {
                    otherLink_ = placeTarget_.getLinksWithOtherPlaces().getVal(lPoint_);
                } else {
                    LevelCave levelCave_ = (LevelCave)placeTarget_.getLevels().getVal(lPoint_.getLevelIndex());
                    if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                        otherLink_ = levelCave_.getLinksOtherLevels().getVal(pt_);
                    } else {
                        return;
                    }
                }
                otherLink_.getCoords().getLevel().getPoint().affect(_target);
                ((City)place_).getLinksWithCaves().removeKey(pt_);
                ((City)place_).getLinksWithCaves().put(_target, link_);
            }
        }
        if (place_ instanceof Road) {
            if (((Road)place_).getLinksWithCaves().contains(_target)) {
                return;
            }
            if (((Road)place_).getLinksWithCaves().contains(pt_)) {
                Link link_ = ((Road)place_).getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Cave placeTarget_ = (Cave) places.getVal(coords_.getNumberPlace());
                LevelPoint lPoint_ = coords_.getLevel();
                Link otherLink_;
                if (placeTarget_.getLinksWithOtherPlaces().contains(lPoint_)) {
                    otherLink_ = placeTarget_.getLinksWithOtherPlaces().getVal(lPoint_);
                } else {
                    LevelCave levelCave_ = (LevelCave)placeTarget_.getLevels().getVal(lPoint_.getLevelIndex());
                    if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                        otherLink_ = levelCave_.getLinksOtherLevels().getVal(pt_);
                    } else {
                        return;
                    }
                }
                otherLink_.getCoords().getLevel().getPoint().affect(_target);
                ((Road)place_).getLinksWithCaves().removeKey(pt_);
                ((Road)place_).getLinksWithCaves().put(_target, link_);
            }
        }
        LevelPoint lPoint_ = _link.getLevel();
        if (place_ instanceof Cave) {
            LevelCave levelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(lPoint_.getLevelIndex());
            if (levelCave_.getLinksOtherLevels().contains(_target)) {
                return;
            }
            LevelPoint other_ = new LevelPoint();
            other_.setLevelIndex(lPoint_.getLevelIndex());
            other_.setPoint(new Point(_target));
            if (((Cave)place_).getLinksWithOtherPlaces().contains(other_)) {
                return;
            }
            Link link_;
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                link_ = levelCave_.getLinksOtherLevels().getVal(pt_);
                other_ = link_.getCoords().getLevel();
                LevelCave otherLevelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(other_.getLevelIndex());
                Link otherLink_ = otherLevelCave_.getLinksOtherLevels().getVal(other_.getPoint());
                otherLink_.getCoords().getLevel().getPoint().affect(_target);
            } else {
                if (((Cave)place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                    link_ = ((Cave)place_).getLinksWithOtherPlaces().getVal(lPoint_);
                    InitializedPlace init_ = (InitializedPlace)places.getVal(link_.getCoords().getNumberPlace());
                    Link otherLink_ = init_.getLinksWithCaves().getVal(link_.getCoords().getLevel().getPoint());
                    otherLink_.getCoords().getLevel().getPoint().affect(_target);
                } else {
                    return;
                }
            }
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                levelCave_.getLinksOtherLevels().removeKey(pt_);
                levelCave_.getLinksOtherLevels().put(_target, link_);
            }
            if (((Cave)place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                ((Cave)place_).getLinksWithOtherPlaces().removeKey(lPoint_);
                ((Cave)place_).getLinksWithOtherPlaces().put(other_, link_);
            }
        }
        for (Place p: places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords coords_ = ((League)p).getAccessCoords();
            if (Coords.eq(coords_, _link)) {
                coords_.getLevel().getPoint().affect(_target);
                break;
            }
        }
    }

    public void deleteRoomForLeague(short _league, int _index) {
        League league_ = (League) places.getVal(_league);
        league_.deleteRoom(_index);
        if (league_.getRooms().isEmpty()) {
            deleteLeague(_league);
        }
    }

    public void addLeague(String _fileName, Coords _accessCoords) {
        League league_ = new League();
        league_.setFileName(_fileName);
        league_.setAccessCoords(_accessCoords);
        league_.setRooms(new CustList<LevelLeague>());
        league_.setBegin(new Point());
        LevelLeague level_ = new LevelLeague();
        level_.setBlocks(new ObjectMap<Point,Block>());
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
        level_.setBlocks(new ObjectMap<Point,Block>());
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

    public void removeBeginForLeague(short _league) {
        setBeginForLeague(_league, new Point());
    }

    public void removeAccessLeague(short _league) {
        League league_ = (League) places.getVal(_league);
        league_.getAccessCoords().affect(new Coords());
    }

    public void setAccessLeague(Coords _coords,short _league) {
        if (!isEmptyForAdding(_coords)) {
            return;
        }
        League league_ = (League) places.getVal(_league);
        league_.getAccessCoords().affect(_coords);
    }

    public void setBeginForLeague(short _league, Point _pt) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_league);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) 0);
        c_.getLevel().setPoint(_pt);
        if (!isEmptyForAdding(c_)) {
            return;
        }
        League league_ = (League) places.getVal(_league);
        league_.getBegin().affect(_pt);
        for (Coords c: beatGymLeader) {
            if (Numbers.eq(c.getNumberPlace(), _league)) {
                beatGymLeader.removeObj(c);
                break;
            }
        }
        beatGymLeader.add(c_);
    }

    public void deleteLeague(short _league) {
        League league_ = (League) places.getVal(_league);
        places.removeKey(_league);
        Coords c_ = new Coords();
        c_.setNumberPlace(_league);
        c_.setLevel(new LevelPoint());
        c_.getLevel().setLevelIndex((byte) 0);
        c_.getLevel().setPoint(league_.getBegin());
        accessCondition.removeKey(league_.getAccessCoords());
        for (EqList<Coords> l: accessCondition.values()) {
            l.removeObj(c_);
        }
        beatGymLeader.removeObj(c_);
    }

    public void addCity(String _cityName) {
        City city_ = new City();
        city_.setName(_cityName);
        city_.setBuildings(new ObjectMap<Point,Building>());
        city_.setLinksWithCaves(new ObjectMap<Point,Link>());
        city_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        LevelOutdoor level_ = new LevelOutdoor();
        level_.setBlocks(new ObjectMap<Point,Block>());
        city_.setLevel(level_);
        addPlace(city_);
    }

    public void addPokemonCenter(short _city, Point _pt) {
        City city_ = (City) places.getVal(_city);
        if (!city_.getLevel().isEmptyForAdding(_pt)) {
            return;
        }
        PokemonCenter pokemonCenter_ = new PokemonCenter();
        pokemonCenter_.setExitCity(new Point());
        pokemonCenter_.setImageFileName(DataBase.EMPTY_STRING);
        LevelIndoorPokemonCenter level_ = new LevelIndoorPokemonCenter();
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setGerants(new ObjectMap<Point,Person>());
        level_.setStorageCoords(new Point());
        pokemonCenter_.setLevel(level_);
        city_.getBuildings().put(_pt, pokemonCenter_);
    }

    public void addGym(short _city, Point _pt, String _microImage) {
        City city_ = (City) places.getVal(_city);
        if (!city_.getLevel().isEmptyForAdding(_pt)) {
            return;
        }
        Gym gym_ = new Gym();
        gym_.setExitCity(new Point());
        gym_.setImageFileName(DataBase.EMPTY_STRING);
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setGymTrainers(new ObjectMap<Point,GymTrainer>());
        level_.setGymLeaderCoords(new Point());
        GymLeader gymLeader_ = new GymLeader();
        gymLeader_.setTeam(new CustList<PkTrainer>());
        gymLeader_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        gymLeader_.setImageMiniFileName(_microImage);
        level_.setGymLeader(gymLeader_);
        gym_.setLevel(level_);
        city_.getBuildings().put(_pt, gym_);
    }

    public void setGymLeaderCoords(short _city, Point _pt, Point _newCoords) {
        City city_ = (City) places.getVal(_city);
        Gym gym_ = (Gym) city_.getBuildings().getVal(_pt);
        if (!gym_.getLevel().isEmptyForAdding(_newCoords)) {
            Coords c_ = new Coords();
            c_.setNumberPlace(_city);
            c_.setLevel(new LevelPoint());
            c_.affectInside(_pt);
            c_.getLevel().setLevelIndex((byte) 0);
            c_.getLevel().setPoint(gym_.getLevel().getGymLeaderCoords());
            beatGymLeader.removeObj(c_);
        }
    }

    public void addRoad() {
        Road road_ = new Road();
        road_.setLinksWithCaves(new ObjectMap<Point,Link>());
        road_.setSavedlinks(new ObjectMap<PlaceInterConnect,Coords>());
        LevelRoad level_ = new LevelRoad();
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.setCharacters(new ObjectMap<Point,CharacterInRoadCave>());
        level_.setDualFights(new ObjectMap<Point,DualFight>());
        level_.setHm(new ObjectMap<Point,Short>());
        level_.setTm(new ObjectMap<Point,Short>());
        level_.setItems(new ObjectMap<Point,String>());
        level_.setLegendaryPks(new ObjectMap<Point,WildPk>());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        road_.setLevel(level_);
        addPlace(road_);
    }

    public void addCave() {
        Cave cave_ = new Cave();
        cave_.setLevels(new NumberMap<Byte,LevelCave>());
        cave_.setLinksWithOtherPlaces(new ObjectMap<LevelPoint,Link>());
        cave_.addNewLevel();
        addPlace(cave_);
    }

    public void addLevelCave(short _cave) {
        Cave cave_ = (Cave) places.getVal(_cave);
        cave_.addNewLevel();
    }

    public void addPerson(Coords _coords, Person _person) {
        Place place_ = places.getVal(_coords.getNumberPlace());
        if (place_ instanceof InitializedPlace) {
            InitializedPlace initPlace_ = (InitializedPlace) place_;
            initPlace_.addPerson(_coords, _person);
        }
        if (place_ instanceof Campaign) {
            Campaign initPlace_ = (Campaign) place_;
            initPlace_.addPerson(_coords, _person);
        }
    }

    public void addPokemon(Coords _coords,WildPk _pk){
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.addPokemon(_coords, _pk);
    }

    public void addObject(Coords _coords,String _object){
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.addObject(_coords, _object);
    }

    public void addDualFight(Coords _coords,DualFight _dualFight){
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.addDualFight(_coords, _dualFight);
    }

    public void addHm(Coords _coords,short _hm){
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.addHm(_coords, _hm);
    }

    public void addTm(Coords _coords,short _tm){
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.addTm(_coords, _tm);
    }

    public void setItem(Coords _coords, String _object) {
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.setItem(_coords, _object);
    }

    public void setTm(Coords _coords, short _tm) {
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.setTm(_coords, _tm);
    }

    public void setHm(Coords _coords, short _object) {
        Campaign initPlace_ = (Campaign) places.getVal(_coords.getNumberPlace());
        initPlace_.setHm(_coords, _object);
    }

    public void removeLink(Coords _link) {
        Place place_ = places.getVal(_link.getNumberPlace());
        Point pt_ = _link.getLevel().getPoint();
        if (_link.isInside()) {
            Building building_ = ((City)place_).getBuildings().getVal(_link.getInsideBuilding());
            if (!Point.eq(pt_, building_.getExitCity())) {
                return;
            }
            building_.getExitCity().affect(new Point());
            return;
        }
        if (place_ instanceof League) {
            LevelPoint lPoint_ = _link.getLevel();
            LevelLeague levelLeague_ =  (LevelLeague)((League)place_).getLevels().getVal(lPoint_.getLevelIndex());
            if (lPoint_.getLevelIndex() == CustList.FIRST_INDEX) {
                if (Point.eq(((League)place_).getBegin(), pt_)) {
                    ((League)place_).getBegin().affect(new Point());
                }
            } else {
                LevelLeague levelLeagueBack_ = (LevelLeague)((League)place_).getLevels().getVal((byte) (lPoint_.getLevelIndex()-1));
                if (Point.eq(levelLeagueBack_.getNextLevelTarget(), pt_)) {
                    levelLeagueBack_.getNextLevelTarget().affect(new Point());
                }
            }
            if (Point.eq(levelLeague_.getAccessPoint(), pt_)) {
                levelLeague_.getAccessPoint().affect(new Point());
            }
        }
        if (place_ instanceof City) {
            if (((City)place_).getLinksWithCaves().contains(pt_)) {
                Link link_ = ((City)place_).getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Cave placeTarget_ = (Cave) places.getVal(coords_.getNumberPlace());
                LevelPoint lPoint_ = coords_.getLevel();
                if (placeTarget_.getLinksWithOtherPlaces().contains(lPoint_)) {
                    placeTarget_.getLinksWithOtherPlaces().removeKey(lPoint_);
                } else {
                    LevelCave levelCave_ = (LevelCave)placeTarget_.getLevels().getVal(lPoint_.getLevelIndex());
                    if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                        levelCave_.getLinksOtherLevels().removeKey(pt_);
                    } else {
                        return;
                    }
                }
                ((City)place_).getLinksWithCaves().removeKey(pt_);
            }
        }
        if (place_ instanceof Road) {
            if (((Road)place_).getLinksWithCaves().contains(pt_)) {
                Link link_ = ((Road)place_).getLinksWithCaves().getVal(pt_);
                Coords coords_ = link_.getCoords();
                Cave placeTarget_ = (Cave) places.getVal(coords_.getNumberPlace());
                LevelPoint lPoint_ = coords_.getLevel();
                if (placeTarget_.getLinksWithOtherPlaces().contains(lPoint_)) {
                    placeTarget_.getLinksWithOtherPlaces().removeKey(lPoint_);
                } else {
                    LevelCave levelCave_ = (LevelCave)placeTarget_.getLevels().getVal(lPoint_.getLevelIndex());
                    if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                        levelCave_.getLinksOtherLevels().removeKey(pt_);
                    } else {
                        return;
                    }
                }
                ((Road)place_).getLinksWithCaves().removeKey(pt_);
            }
        }
        LevelPoint lPoint_ = _link.getLevel();
        if (place_ instanceof Cave) {
            LevelCave levelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(lPoint_.getLevelIndex());
            LevelPoint other_;
            Link link_;
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                link_ = levelCave_.getLinksOtherLevels().getVal(pt_);
                other_ = link_.getCoords().getLevel();
                LevelCave otherLevelCave_ = (LevelCave)((Cave)place_).getLevels().getVal(other_.getLevelIndex());
                otherLevelCave_.getLinksOtherLevels().removeKey(other_.getPoint());
            } else {
                if (((Cave)place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                    link_ = ((Cave)place_).getLinksWithOtherPlaces().getVal(lPoint_);
                    InitializedPlace init_ = (InitializedPlace)places.getVal(link_.getCoords().getNumberPlace());
                    init_.getLinksWithCaves().removeKey(link_.getCoords().getLevel().getPoint());
                } else {
                    return;
                }
            }
            if (levelCave_.getLinksOtherLevels().contains(pt_)) {
                levelCave_.getLinksOtherLevels().removeKey(pt_);
            }
            if (((Cave)place_).getLinksWithOtherPlaces().contains(lPoint_)) {
                ((Cave)place_).getLinksWithOtherPlaces().removeKey(lPoint_);
            }
        }
        for (Place p: places.values()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords coords_ = ((League)p).getAccessCoords();
            if (Coords.eq(coords_, _link)) {
                ((League)p).getAccessCoords().affect(new Coords());
                break;
            }
        }
    }

    public void clearElements(Coords _coords) {
        Place pl_ = places.getVal(_coords.getNumberPlace());
        Level level_ = pl_.getLevelByCoords(_coords);
        level_.clearElements(_coords.getLevel().getPoint());
        beatGymLeader.removeObj(_coords);
    }

    public void deleteLevelCave(short _cave, byte _level) {
        Cave cave_ = (Cave) places.getVal(_cave);
        unjoinLevelCave(_cave, _level);
        cave_.getLevels().removeKey(_level);
        if (cave_.getLevels().isEmpty()) {
            deleteCave(_cave);
        }
    }

    public void deleteCave(short _cave) {
        int nbPlaces_ = places.size();
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            Place place_ = places.getVal(p);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            unjoinCavePlace(_cave, p);
        }
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            Place place_ = places.getVal(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            if (Numbers.eq(((League)place_).getAccessCoords().getNumberPlace(), _cave)) {
                removeAccessLeague(p);
            }
        }
        places.removeKey(_cave);
    }

    public void deletePlace(short _place) {
        int nbPlaces_ = places.size();
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            Place place_ = places.getVal(p);
            if (!(place_ instanceof InitializedPlace)) {
                continue;
            }
            unjoin(_place, p);
        }
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            Place place_ = places.getVal(p);
            if (!(place_ instanceof Cave)) {
                continue;
            }
            unjoinCavePlace(p, _place);
        }
        for (short p = CustList.FIRST_INDEX; p < nbPlaces_; p++) {
            Place place_ = places.getVal(p);
            if (!(place_ instanceof League)) {
                continue;
            }
            if (Numbers.eq(((League)place_).getAccessCoords().getNumberPlace(), _place)) {
                removeAccessLeague(p);
            }
        }
        places.removeKey(_place);
    }

    short indexOfAddedPlace() {
        Numbers<Short> keys_ = new Numbers<Short>(places.getKeys());
        if (keys_.isEmpty()) {
            return (short) CustList.FIRST_INDEX;
        }
        short max_ = keys_.getMaximum();
        for (short s = CustList.FIRST_INDEX; s < max_; s++) {
            if (keys_.containsObj(s)) {
                continue;
            }
            return s;
        }
        return (short) (max_ + 1);
    }

    void addPlace(Place _place) {
        short index_ = indexOfAddedPlace();
        places.put(index_, _place);
    }

    public void moveCamera(Direction _direction){
        if (_direction == Direction.DOWN) {
            for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
                int maxHeight_ = screenHeight-1;
                for (int j = CustList.FIRST_INDEX;j<maxHeight_;j++){
                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i,j+1)));
                }
//                for (int j = CustList.FIRST_INDEX - 1;j<maxHeight_;j++){
//                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i,j+1)));
//                }
                ScreenCoords key_ = new ScreenCoords(i,screenHeight-1);
                Coords coords_=tiles.getVal(key_);
                if(coords_.isValid()){
                    tiles.put(key_, closestTile(coords_, _direction));
                }else{
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.UP) {
            for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
                int maxHeight_ = screenHeight-1;
                for(int j=maxHeight_;j>CustList.FIRST_INDEX;j--){
                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i,j-1)));
                }
//                for(int j=maxHeight_+1;j>CustList.FIRST_INDEX;j--){
//                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i,j-1)));
//                }
                ScreenCoords key_ = new ScreenCoords(i,0);
                Coords coords_=tiles.getVal(key_);
                if(coords_.isValid()){
                    tiles.put(key_, closestTile(coords_, _direction));
                }else{
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.RIGHT) {
            for (int j = CustList.FIRST_INDEX;j<screenHeight;j++){
                int maxWidth_ = screenWidth-1;
                for (int i = CustList.FIRST_INDEX;i<maxWidth_;i++){
                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i+1,j)));
                }
//                for (int i = CustList.FIRST_INDEX - 1;i<maxWidth_;i++){
//                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i+1,j)));
//                }
                ScreenCoords key_ = new ScreenCoords(screenWidth-1,j);
                Coords coords_=tiles.getVal(key_);
                if(coords_.isValid()){
                    tiles.put(key_, closestTile(coords_, _direction));
                }else{
                    tiles.put(key_, coords_);
                }
            }
        } else if (_direction == Direction.LEFT) {
            for (int j = CustList.FIRST_INDEX;j<screenHeight;j++){
                int maxWidth_ = screenWidth-1;
                for(int i=maxWidth_;i>CustList.FIRST_INDEX;i--){
                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i-1,j)));
                }
//                for(int i=maxWidth_+1;i>CustList.FIRST_INDEX;i--){
//                    tiles.put(new ScreenCoords(i,j), tiles.getVal(new ScreenCoords(i-1,j)));
//                }
                ScreenCoords key_ = new ScreenCoords(0,j);
                Coords coords_=tiles.getVal(key_);
                if(coords_.isValid()){
                    tiles.put(key_, closestTile(coords_, _direction));
                }else{
                    tiles.put(key_, coords_);
                }
            }
        }
    }

    public void calculateIntersectWithScreen(Coords _coords) {
        tiles = intersectWithScreen(_coords);
    }

    ObjectMap<ScreenCoords,Coords> intersectWithScreen(Coords _coords){
        ObjectMap<ScreenCoords,Coords> liste_ = new ObjectMap<ScreenCoords, Coords>();
        //CustList<Point> points_ = Level.
        for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
            for (int j = CustList.FIRST_INDEX;j<screenHeight;j++){
                liste_.put(new ScreenCoords(i,j),new Coords());
            }
        }
        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,spaceBetweenTopAndHeros),_coords);
        EqList<ScreenCoords> cles_=liste_.getKeys();
        EqList<ScreenCoords> currentElements_ = new EqList<ScreenCoords>();
        currentElements_.add(new ScreenCoords(spaceBetweenLeftAndHeros,spaceBetweenTopAndHeros));
        EqList<ScreenCoords> newElements_ = new EqList<ScreenCoords>();
        while (true) {
            for(ScreenCoords p:currentElements_){
                Coords coords_=liste_.getVal(p);
                if(!coords_.isValid()){
                    continue;
                }
                for(Direction d:Direction.values()){
                    ScreenCoords cle_=new ScreenCoords(d.getx()+p.getXcoords(),d.gety()+p.getYcoords());
                    if(!cles_.containsObj(cle_)){
                        continue;
                    }
                    Coords nextCoords_=liste_.getVal(cle_);
                    if(nextCoords_.isValid()){
                        continue;
                    }
                    nextCoords_=closestTile(coords_,d);
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
        ObjectMap<ScreenCoords,Coords> liste_ = new ObjectMap<ScreenCoords, Coords>();
        //CustList<Point> points_ = Level.
        for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
            for (int j = CustList.FIRST_INDEX;j<screenHeight;j++){
                liste_.put(new ScreenCoords(i,j),new Coords());
            }
        }
        for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
            liste_.put(new ScreenCoords(i,-1),new Coords());
        }
        for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
            liste_.put(new ScreenCoords(i,screenHeight),new Coords());
        }
        for (int i = CustList.FIRST_INDEX;i<screenHeight;i++){
            liste_.put(new ScreenCoords(screenWidth, i),new Coords());
        }
        for (int i = CustList.FIRST_INDEX;i<screenHeight;i++){
            liste_.put(new ScreenCoords(-1, i),new Coords());
        }
        liste_.put(new ScreenCoords(screenWidth,-1),new Coords());
        liste_.put(new ScreenCoords(-1,-1),new Coords());
        liste_.put(new ScreenCoords(-1,screenHeight),new Coords());
        liste_.put(new ScreenCoords(screenWidth,screenHeight),new Coords());
//        if (_dir == Direction.DOWN) {
//            for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
//                liste_.put(new ScreenCoords(i,-1),new Coords());
//            }
//        } else if (_dir == Direction.UP) {
//            for (int i = CustList.FIRST_INDEX;i<screenWidth;i++){
//                liste_.put(new ScreenCoords(i,screenHeight),new Coords());
//            }
//        } else if (_dir == Direction.LEFT) {
//            for (int i = CustList.FIRST_INDEX;i<screenHeight;i++){
//                liste_.put(new ScreenCoords(screenWidth, i),new Coords());
//            }
//        } else if (_dir == Direction.RIGHT) {
//            for (int i = CustList.FIRST_INDEX;i<screenHeight;i++){
//                liste_.put(new ScreenCoords(-1, i),new Coords());
//            }
//        }
        liste_.put(new ScreenCoords(spaceBetweenLeftAndHeros,spaceBetweenTopAndHeros),_coords);
        EqList<ScreenCoords> cles_=liste_.getKeys();
        EqList<ScreenCoords> currentElements_ = new EqList<ScreenCoords>();
        currentElements_.add(new ScreenCoords(spaceBetweenLeftAndHeros,spaceBetweenTopAndHeros));
        EqList<ScreenCoords> newElements_ = new EqList<ScreenCoords>();
        while (true) {
            for(ScreenCoords p:currentElements_){
                Coords coords_=liste_.getVal(p);
                if(!coords_.isValid()){
                    continue;
                }
                for(Direction d:Direction.values()){
                    ScreenCoords cle_=new ScreenCoords(d.getx()+p.getXcoords(),d.gety()+p.getYcoords());
                    if(!cles_.containsObj(cle_)){
                        continue;
                    }
                    Coords nextCoords_=liste_.getVal(cle_);
                    if(nextCoords_.isValid()){
                        continue;
                    }
                    nextCoords_=closestTile(coords_,d);
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

    public void calculateBackgroundImagesFromTiles(StringMap<String> _images, int _dx, int _dy) {
        ReversibleMap<ScreenCoords, Coords> ids_;
        ids_ = new ReversibleMap<ScreenCoords, Coords>();
        for (ScreenCoords k: tiles.getKeys()) {
            Coords coords_ = tiles.getVal(k);
            try {
                Place place_ = places.getVal(coords_.getNumberPlace());
                Level level_ = place_.getLevelByCoords(coords_);
                Point pt_ = coords_.getLevel().getPoint();
                Point idBlock_ = level_.getBlockIdByPoint(pt_);
                Coords coordsId_ = new Coords(coords_);
                coordsId_.getLevel().getPoint().affect(idBlock_);
                ids_.put(k, coordsId_);
            } catch (RuntimeException _0) {
            }
        }
        EqList<ScreenCoords> topsLeft_;
        topsLeft_ = new EqList<ScreenCoords>();
        ObjectMap<Coords, EqList<ScreenCoords>> reverse_;
        reverse_ = ids_.reverseMap();
//        reverse_ = new Map<Coords, CustList<ScreenCoords>>();
//        for (EntryCust<ScreenCoords, Coords> e: ids_.entryList()) {
//            Coords c_ = e.getValue();
//            ScreenCoords s_ = e.getKey();
//            if (reverse_.contains(c_)) {
//                reverse_.getVal(c_).add(s_);
//            } else {
//                CustList<ScreenCoords> l_;
//                l_ = new CustList<ScreenCoords>(s_);
//                reverse_.put(c_, l_);
//            }
//        }
        for (Coords c: reverse_.getKeys()) {
            EqList<ScreenCoords> l_;
            l_ = reverse_.getVal(c);
            l_.sortElts(new ComparatorScreenCoords());
            topsLeft_.add(l_.first());
        }
        for (ScreenCoords k: topsLeft_) {
            Coords coords_ = tiles.getVal(k);
            Coords coordsId_ = ids_.getVal(k);
            Place place_ = places.getVal(coords_.getNumberPlace());
            Level level_ = place_.getLevelByCoords(coords_);
            Point pt_ = coords_.getLevel().getPoint();
            Point idBlock_ = coordsId_.getLevel().getPoint();
            Block block_ = level_.getBlocks().getVal(idBlock_);
//            String img_ = _images.getVal(block_.getTileFileName());
            String img_ = DataBase.getValueCaseInsensitive(_images, block_.getTileFileName());
            int x_ = (pt_.getx() - idBlock_.getx()) * sideLength;
            int y_ = (pt_.gety() - idBlock_.gety()) * sideLength;
            int w_;
            w_ = Math.min(block_.getWidth(), screenWidth - k.getXcoords() + 2 * _dx);
            int h_;
            h_ = Math.min(block_.getHeight(), screenHeight - k.getYcoords() + 2 * _dy);
            try {
                backgroundImages.put(k,Image.clipSixtyFour(img_, x_, y_, w_ * sideLength, h_ * sideLength));
            } catch (RuntimeException _0) {
            }
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

    public Coords checkTile(Coords _currentCoords,Direction _direction){
        Place currentPlace_=places.getVal(_currentCoords.getNumberPlace());
        Level currentLevel_=currentPlace_.getLevelByCoords(_currentCoords);
        Point closestPoint_ = _currentCoords.getLevel().getPoint();
        try {
            currentLevel_.getBlockByPoint(closestPoint_);
            return new Coords(_currentCoords);
        } catch (BlockNotFoundException _0) {
            if (currentPlace_ instanceof InitializedPlace) {
                if (!_currentCoords.isInside()) {
                    ObjectMap<PlaceInterConnect,Coords> rc_ = ((InitializedPlace)currentPlace_).getPointsWithCitiesAndOtherRoads();
                    PlaceInterConnect key_ = new PlaceInterConnect(_currentCoords.getLevel().getPoint(),_direction);
                    if (rc_.contains(key_)) {
                        return new Coords(rc_.getVal(key_));
                    }
                }
            }
            return new Coords();
        }
    }

    public Coords closestTile(Coords _currentCoords,Direction _direction){
        Place currentPlace_=places.getVal(_currentCoords.getNumberPlace());
        Level currentLevel_=currentPlace_.getLevelByCoords(_currentCoords);
        try {
            Coords closestCoords_ = new Coords(_currentCoords);
            Point closestPoint_ = closestCoords_.getLevel().getPoint();
            closestPoint_.moveTo(_direction);
            currentLevel_.getBlockByPoint(closestPoint_);
            return closestCoords_;
        } catch (BlockNotFoundException _0) {
            if (currentPlace_ instanceof InitializedPlace) {
                if (!_currentCoords.isInside()) {
                    ObjectMap<PlaceInterConnect,Coords> rc_ = ((InitializedPlace)currentPlace_).getPointsWithCitiesAndOtherRoads();
                    PlaceInterConnect key_ = new PlaceInterConnect(_currentCoords.getLevel().getPoint(),_direction);
                    if (rc_.contains(key_)) {
                        return new Coords(rc_.getVal(key_));
                    }
                }
            }
            return new Coords();
        }
    }

    public Block currentBlock(Coords _currentCoords){
        Place currentPlace_=places.getVal(_currentCoords.getNumberPlace());
        Level currentLevel_=currentPlace_.getLevelByCoords(_currentCoords);
        try {
            Coords closestCoords_ = new Coords(_currentCoords);
            Point closestPoint_ = closestCoords_.getLevel().getPoint();
            return currentLevel_.getBlockByPoint(closestPoint_);
        } catch (BlockNotFoundException _0) {
            return new Block();
        }
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

    public NumberMap<Short,EqList<Point>> getBeatGymTrainer() {
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

    public ObjectMap<PlaceLevel, Numbers<Integer>> getWildPokemonBeforeFirstLeague() {
        return wildPokemonBeforeFirstLeague;
    }

    public ObjectMap<ScreenCoords, Coords> getTiles() {
        return tiles;
    }

    public ObjectMap<ScreenCoords, String> getBackgroundImages() {
        return backgroundImages;
    }

    public ObjectMap<ScreenCoords, StringList> getForegroundImages() {
        return foregroundImages;
    }

    public NumberMap<Short,Place> getPlaces() {
        return places;
    }

    public void setPlaces(NumberMap<Short,Place> _places) {
        places = _places;
    }

    public ObjectMap<Coords,EqList<Coords>> getAccessCondition() {
        return accessCondition;
    }

    public void setAccessCondition(ObjectMap<Coords,EqList<Coords>> _accessCondition) {
        accessCondition = _accessCondition;
    }

    public ObjectMap<MiniMapCoords,TileMiniMap> getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(ObjectMap<MiniMapCoords,TileMiniMap> _miniMap) {
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

//    public Map<EnvironmentType,String> getSpritesGirlBoy() {
//        return spritesGirlBoy;
//    }
//
//    public void setSpritesGirlBoy(Map<EnvironmentType,String> _spritesGirlBoy) {
//        spritesGirlBoy = _spritesGirlBoy;
//    }

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

//    @Override
//    public void beforeSave() {
//        firstPokemon = new WildPk(firstPokemon);
//    }
//
//    @Override
//    public void afterLoad() {
//    }
}
