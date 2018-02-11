package aiki.beans.pokemon;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.comparators.ComparatorMiniMapCoords;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionLevel;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.pokemon.evolution.EvolutionStoneGender;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Level;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Place;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class PokemonBean extends CommonBean {

    private static final String PAGE_LEVELGENDER ="web/html/pokemon/evolutions/evolevelgender.html";
    private static final String PAGE_LEVEL ="web/html/pokemon/evolutions/evolevel.html";
    private static final String PAGE_HAPPY ="web/html/pokemon/evolutions/evohappy.html";
    private static final String PAGE_MOVE ="web/html/pokemon/evolutions/evomove.html";
    private static final String PAGE_ITEM ="web/html/pokemon/evolutions/evoitem.html";
    private static final String PAGE_STONEGENDER ="web/html/pokemon/evolutions/evostonegender.html";
    private static final String PAGE_STONE ="web/html/pokemon/evolutions/evostone.html";
    private static final String PAGE_TYPE ="web/html/pokemon/evolutions/evotype.html";
    private static final String PAGE_TEAM ="web/html/pokemon/evolutions/evoteam.html";
    private String name;
    private String backImage;
    private String frontImage;
    private String displayName;
    private Rate weight;
    private Rate height;
    private StringList possibleGenders;
    private StringList types;
    private StringList abilities;
    private short catchingRate;
    private StringList evolutions;
    private EnumList<Statistic> statisticsEnum;
    private StringList statistics;
    private String evoBase;
    private String expEvo;
    private long expRate;
    private EqList<LevelMove> levMoves;
    private NatTreeMap<Short, String> technicalMoves;
    private NatTreeMap<Short, String> hiddenMoves;
    private StringList moveTutors;
    private LgInt hatchingSteps;
    private StringList eggGroupsPk;
    private NatTreeMap<String,String> mapVars;
    private CustList<PlaceIndex> places;
    private TreeMap<MiniMapCoords, String> images;

    private TreeMap<MiniMapCoords, String> namesPlaces;

    private Numbers<Short> placesAppears;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        places = new CustList<PlaceIndex>();
        for (Short i: data_.getMap().getPlaces().getKeys()) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i);
            pl_.setPlace(data_.getMap().getPlaces().getVal(i));
            places.add(pl_);
        }
//        places.sort(new NaturalComparator<PlaceIndex>() {
//            @Override
//            public int compare(PlaceIndex _o1, PlaceIndex _o2) {
//                return _o1.getPlace().getName().compareTo(_o2.getPlace().getName());
//            }
//        });
        places.sortElts(new ComparatorPlaceIndex());
        images = data_.getMap().getImages(data_);
        namesPlaces = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        placesAppears = new Numbers<Short>();
        for (MiniMapCoords m: images.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        name = (String) getForms().getVal(PK);
        int nbPlaces_ = places.size();
        for (short i = CustList.FIRST_INDEX; i < nbPlaces_; i++) {
            if (isAppearingPlace((long) i)) {
                placesAppears.add(i);
            }
        }
        backImage = ConverterBufferedImage.surroundImage(data_.getMaxiPkBack().getVal(name));
        //ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkBack().getVal(name));
        frontImage = ConverterBufferedImage.surroundImage(data_.getMaxiPkFront().getVal(name));
        //ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name));
        PokemonData pk_ = data_.getPokemon(name);
        displayName = translationsPokemon_.getVal(name);
        weight = pk_.getWeight();
        height = pk_.getHeight();
        possibleGenders = new StringList();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        for (Gender g: pk_.getGenderRep().getPossibleGenders()) {
            possibleGenders.add(translationsGenders_.getVal(g));
        }
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        types = new StringList();
        for (String t: pk_.getTypes()) {
            types.add(translationsTypes_.getVal(t));
        }
        types.sort();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilities = new StringList(pk_.getAbilities());
        abilities.sortElts(new ComparatorTrStrings(translationsAbilities_));
        catchingRate = pk_.getCatchingRate();
        evolutions = new StringList(pk_.getEvolutions().getKeys());
        evolutions.sortElts(new ComparatorTrStrings(translationsPokemon_));
        evoBase = translationsPokemon_.getVal(pk_.getBaseEvo());
        expEvo = data_.getFormula(data_.getExpGrowth(pk_.getExpEvo()),getLanguage());
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(data_.getExpGrowth(pk_.getExpEvo()),getLanguage());
        mapVars = new NatTreeMap<String,String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVars.put(k, mapVars_.getVal(k));
        }
        expRate = pk_.getExpRate();
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        statisticsEnum = new EnumList<Statistic>();
        statistics = new StringList();
        for (Statistic s: Statistic.values()) {
            if (!s.isWithBaseStatistic()) {
                continue;
            }
            statisticsEnum.add(s);
            statistics.add(translationsStatistics_.getVal(s));
        }
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        levMoves = new EqList<LevelMove>();
        for (LevelMove l: pk_.getLevMoves()) {
            LevelMove l_ = new LevelMove();
            l_.setMove(translationsMoves_.getVal(l.getMove()));
            l_.setLevel(l.getLevel());
            levMoves.add(l_);
        }
        technicalMoves = new NatTreeMap<Short, String>();
        for (Short s: pk_.getTechnicalMoves()) {
            technicalMoves.put(s, translationsMoves_.getVal(data_.getTm().getVal(s)));
        }
        hiddenMoves = new NatTreeMap<Short, String>();
        for (Short s: pk_.getHiddenMoves()) {
            hiddenMoves.put(s, translationsMoves_.getVal(data_.getHm().getVal(s)));
        }
        moveTutors = new StringList(pk_.getMoveTutors());
        moveTutors.sortElts(new ComparatorTrStrings(translationsMoves_));
        //eggGroups = new StringList();
        eggGroupsPk = new StringList();
        //Map<String,String> translationsEggs_;
        //translationsEggs_ = data_.getTranslatedEggs().getVal(getLanguage());
        for (String e: pk_.getEggGroups()) {
            //eggGroups.add(translationsEggs_.getVal(e));
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (pkData_.getEggGroups().containsStr(e)) {
                    eggGroupsPk.add(p);
                }
            }
        }
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (pkData_.getEggGroups().containsStr(data_.getDefaultEggGroup())) {
                eggGroupsPk.add(p);
            }
        }
        //eggGroups.sort();
        //eggGroups.removeDuplicates();
        eggGroupsPk.sortElts(new ComparatorTrStrings(translationsPokemon_));
        eggGroupsPk.removeDuplicates();
        hatchingSteps = pk_.getHatchingSteps();
    }
    public String getMiniMapImage(Long _index) {
        String image_ = images.getValue(_index.intValue());
        MiniMapCoords key_ = images.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        short pl_ = data_.getMap().getMiniMap().getVal(key_).getPlace();
        boolean appear_ = false;
        for (short p: placesAppears) {
            PlaceIndex plInd_ = places.get(p);
            if (plInd_.getIndex() == pl_) {
                appear_ = true;
                break;
            }
        }
        if (appear_) {
            String miniImg_ = data_.getMiniPk().getVal(name);
            image_ = ConverterBufferedImage.stackImages(image_, miniImg_);
        }
        return ConverterBufferedImage.surroundImage(image_);
    }
    public String getPlaceName(Long _index) {
        return namesPlaces.getValue(_index.intValue());
    }
    public boolean isFirstRow(Long _index) {
        MiniMapCoords key_ = images.getKey(_index.intValue());
        return key_.getXcoords() == CustList.FIRST_INDEX;
    }
    public String roundWeight() {
        return weight.evaluate(2);
    }
    public String roundHeight() {
        return height.evaluate(2);
    }
    public String clickPokedex() {
        if (!getForms().contains(POKEMON_SET)) {
            getForms().put(POKEMON_SET, new StringList());
        }
        return POKEMON_SET;
    }
    public String getPage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Evolution evo_ = pk_.getEvolutions().getVal(getEvo(_index));
        if (evo_ instanceof EvolutionLevelGender) {
            return PAGE_LEVELGENDER;
        }
        if (evo_ instanceof EvolutionLevel) {
            return PAGE_LEVEL;
        }
        if (evo_ instanceof EvolutionHappiness) {
            return PAGE_HAPPY;
        }
        if (evo_ instanceof EvolutionMove) {
            return PAGE_MOVE;
        }
        if (evo_ instanceof EvolutionItem) {
            return PAGE_ITEM;
        }
        if (evo_ instanceof EvolutionStoneGender) {
            return PAGE_STONEGENDER;
        }
        if (evo_ instanceof EvolutionStone) {
            return PAGE_STONE;
        }
        if (evo_ instanceof EvolutionMoveType) {
            return PAGE_TYPE;
        }
        return PAGE_TEAM;
    }

    private String getEvo(Long _index) {
        return evolutions.get(_index.intValue());
    }
    public String getTrAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        return data_.translateAbility(abilities.get(_index.intValue()));
    }
    public String clickAbility(Long _index) {
        getForms().put(ABILITY,abilities.get(_index.intValue()));
        return ABILITY;
    }
    public String clickBase() {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        getForms().put(PK,pk_.getBaseEvo());
        return POKEMON;
    }
    public short getBase(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Statistic stat_ = statisticsEnum.get(_index.intValue());
        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
        return statEv_.getBase();
    }
    public short getEv(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Statistic stat_ = statisticsEnum.get(_index.intValue());
        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
        return statEv_.getEv();
    }
    public String clickMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        String move_ = pk_.getLevMoves().get(_index.intValue()).getMove();
        getForms().put(MOVE,move_);
        return MOVE;
    }
    public String clickTechnicalMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String move_ = data_.getTm().getVal(technicalMoves.getKey(_index.intValue()));
        getForms().put(MOVE,move_);
        return MOVE;
    }
    public String clickHiddenMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String move_ = data_.getHm().getVal(hiddenMoves.getKey(_index.intValue()));
        getForms().put(MOVE,move_);
        return MOVE;
    }
    public String getMoveTutor(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(moveTutors.get(_index.intValue()));
    }
    public String clickMoveTutors(Long _index) {
        String move_ = moveTutors.get(_index.intValue());
        getForms().put(MOVE,move_);
        return MOVE;
    }
    public String getEggPk(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(eggGroupsPk.get(_index.intValue()));
    }
    public String clickEggPk(Long _index) {
        String pk_ = eggGroupsPk.get(_index.intValue());
        getForms().put(PK,pk_);
        return POKEMON;
    }
    public boolean isAppearingAnyWhere() {
        int nbPlaces_ = places.size();
        for (int i = CustList.FIRST_INDEX; i < nbPlaces_; i++) {
            int nbLayers_ = layers((long)i).size();
            for (int j = CustList.FIRST_INDEX; j < nbLayers_; j++) {
                if (isAppearing((long)i,(long) j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isMultiLayer(Long _index) {
        return layers(_index).size() > CustList.SECOND_INDEX;
    }
    public CustList<Level> layers(Long _index) {
        Place pl_ = places.get(_index.intValue()).getPlace();
        return pl_.getLevelsList();
    }
    public boolean isAppearingPlace(Long _index) {
        int nbLayers_ = layers(_index).size();
        for (int j = CustList.FIRST_INDEX; j < nbLayers_; j++) {
            if (isAppearing(_index,(long) j)) {
                return true;
            }
        }
        return false;
    }
    public boolean isAppearing(Long _indexOne, Long _indexTwo) {
        Place pl_ = places.get(_indexOne.intValue()).getPlace();
        Level level_ = pl_.getLevels().getVal(_indexTwo.byteValue());
        if (!(level_ instanceof LevelWithWildPokemon)) {
            return false;
        }
        LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
        for (AreaApparition a: w_.getWildPokemonAreas()) {
            for (Pokemon p: a.getWildPokemon()) {
                if (StringList.quickEq(p.getName(), name)) {
                    return true;
                }
            }
            for (Pokemon p: a.getWildPokemonFishing()) {
                if (StringList.quickEq(p.getName(), name)) {
                    return true;
                }
            }
        }
        for (Pokemon p: w_.getLegendaryPks().values()) {
            if (StringList.quickEq(p.getName(), name)) {
                return true;
            }
        }
        return false;
    }
    public String clickLevel(Long _indexOne, Long _indexTwo) {
        getForms().removeKey(INSIDE);
        getForms().put(LEVEL_MAP_INDEX, _indexTwo.intValue());
        getForms().put(PLACE_MAP_INDEX, _indexOne.byteValue());
        getForms().put(PROPONE_LINK, false);
        getForms().put(PROPONE_TILE, false);
        getForms().put(SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringList.concat(PROPONE_LINK_VAR,d.name()), false);
        }
        return LEVEL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBackImage() {
        return backImage;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public Rate getWeight() {
        return weight;
    }

    public Rate getHeight() {
        return height;
    }

    public StringList getPossibleGenders() {
        return possibleGenders;
    }

    public StringList getTypes() {
        return types;
    }

    public StringList getAbilities() {
        return abilities;
    }

    public short getCatchingRate() {
        return catchingRate;
    }

    public StringList getEvolutions() {
        return evolutions;
    }

    public String getName() {
        return name;
    }

    public String getEvoBase() {
        return evoBase;
    }

    public String getExpEvo() {
        return expEvo;
    }

    public NatTreeMap<String,String> getMapVars() {
        return mapVars;
    }

    public long getExpRate() {
        return expRate;
    }

    public StringList getStatistics() {
        return statistics;
    }

    public EqList<LevelMove> getLevMoves() {
        return levMoves;
    }

    public NatTreeMap<Short,String> getTechnicalMoves() {
        return technicalMoves;
    }

    public NatTreeMap<Short,String> getHiddenMoves() {
        return hiddenMoves;
    }

    public StringList getMoveTutors() {
        return moveTutors;
    }

    public StringList getEggGroupsPk() {
        return eggGroupsPk;
    }

    public LgInt getHatchingSteps() {
        return hatchingSteps;
    }

    public CustList<PlaceIndex> getPlaces() {
        return places;
    }

    public TreeMap<MiniMapCoords,String> getImages() {
        return images;
    }
}