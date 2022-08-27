package aiki.beans.pokemon;

import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorPlaceIndex;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.comparators.ComparatorMiniMapCoords;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.*;
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
import aiki.map.util.MiniMapCoordsTile;
import code.images.BaseSixtyFourUtil;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

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
    private CustList<LevelMove> levMoves;
    private ShortTreeMap< String> technicalMoves;
    private ShortTreeMap< String> hiddenMoves;
    private StringList moveTutors;
    private LgInt hatchingSteps;
    private StringList eggGroupsPk;
    private NatStringTreeMap<String> mapVars;
    private CustList<PlaceIndex> places;
    private TreeMap<MiniMapCoords, int[][]> images;

    private TreeMap<MiniMapCoords, String> namesPlaces;

    private Shorts placesAppears;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        places = new CustList<PlaceIndex>();
        short i_ = 0;
        for (Place p: data_.getMap().getPlaces()) {
            PlaceIndex pl_ = new PlaceIndex();
            pl_.setIndex(i_);
            pl_.setPlace(p);
            places.add(pl_);
            i_++;
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
        placesAppears = new Shorts();
        for (MiniMapCoords m: images.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        name = getForms().getValStr(CST_PK);
        int nbPlaces_ = places.size();
        for (short i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            if (isAppearingPlace(i)) {
                placesAppears.add(i);
            }
        }
        backImage = BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkBack().getVal(name));
        //ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkBack().getVal(name));
        frontImage = BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name));
        //ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name));
        PokemonData pk_ = data_.getPokemon(name);
        displayName = translationsPokemon_.getVal(name);
        weight = pk_.getWeight();
        height = pk_.getHeight();
        possibleGenders = new StringList();
        AbsMap<Gender,String> translationsGenders_;
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
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(data_.getExpGrowth(pk_.getExpEvo()),getLanguage());
        mapVars = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVars.put(k, mapVars_.getVal(k));
        }
        expRate = pk_.getExpRate();
        AbsMap<Statistic,String> translationsStatistics_;
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
        levMoves = new CustList<LevelMove>();
        for (LevelMove l: pk_.getLevMoves()) {
            LevelMove l_ = new LevelMove();
            l_.setMove(translationsMoves_.getVal(l.getMove()));
            l_.setLevel(l.getLevel());
            levMoves.add(l_);
        }
        technicalMoves = new ShortTreeMap< String>();
        for (Short s: pk_.getTechnicalMoves()) {
            technicalMoves.put(s, translationsMoves_.getVal(data_.getTm().getVal(s)));
        }
        hiddenMoves = new ShortTreeMap< String>();
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
                if (StringUtil.contains(pkData_.getEggGroups(), e)) {
                    eggGroupsPk.add(p);
                }
            }
        }
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getEggGroups(), data_.getDefaultEggGroup())) {
                eggGroupsPk.add(p);
            }
        }
        //eggGroups.sort();
        //eggGroups.removeDuplicates();
        eggGroupsPk.sortElts(new ComparatorTrStrings(translationsPokemon_));
        eggGroupsPk.removeDuplicates();
        hatchingSteps = pk_.getHatchingSteps();
    }
    public String getMiniMapImage(int _index) {
        int[][] image_ = images.getValue(_index);
        MiniMapCoords key_ = images.getKey(_index);
        DataBase data_ = getDataBase();
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
            int[][] miniImg_ = data_.getMiniPk().getVal(name);
            image_ = ConverterBufferedImage.stackImages(image_, miniImg_);
        }
        return BaseSixtyFourUtil.getStringByImage(image_);
    }
    public String getPlaceName(int _index) {
        return namesPlaces.getValue(_index);
    }
    public int getMapWidth() {
        int w_ = 0;
        while (images.getKey(w_).getYcoords() != IndexConstants.SECOND_INDEX) {
            w_++;
        }
        return w_;
    }
    public boolean isFirstRow(int _index) {
        if (_index == 0) {
            return false;
        }
        MiniMapCoords key_ = images.getKey(_index);
        return key_.getXcoords() == IndexConstants.FIRST_INDEX;
    }
    public String roundWeight() {
        return weight.evaluate(2);
    }
    public String roundHeight() {
        return height.evaluate(2);
    }
    public String clickPokedex() {
        if (!getForms().contains(CST_POKEMON_SET)) {
            getForms().put(CST_POKEMON_SET, new StringList());
        }
        return CST_POKEMON_SET;
    }
    public String getPage(int _index) {
        DataBase data_ = getDataBase();
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

    private String getEvo(int _index) {
        return evolutions.get(_index);
    }
    public String getTrAbility(int _index) {
        DataBase data_ = getDataBase();
        return data_.translateAbility(abilities.get(_index));
    }
    public String clickAbility(int _index) {
        getForms().put(CST_ABILITY,abilities.get(_index));
        return CST_ABILITY;
    }
    public String clickBase() {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        getForms().put(CST_PK,pk_.getBaseEvo());
        return CST_POKEMON;
    }
    public short getBase(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Statistic stat_ = statisticsEnum.get(_index);
        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
        return statEv_.getBase();
    }
    public short getEv(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Statistic stat_ = statisticsEnum.get(_index);
        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
        return statEv_.getEv();
    }
    public String clickMove(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        String move_ = pk_.getLevMoves().get(_index).getMove();
        getForms().put(CST_MOVE,move_);
        return CST_MOVE;
    }
    public String clickTechnicalMove(int _index) {
        DataBase data_ = getDataBase();
        String move_ = data_.getTm().getVal(technicalMoves.getKey(_index));
        getForms().put(CST_MOVE,move_);
        return CST_MOVE;
    }
    public String clickHiddenMove(int _index) {
        DataBase data_ = getDataBase();
        String move_ = data_.getHm().getVal(hiddenMoves.getKey(_index));
        getForms().put(CST_MOVE,move_);
        return CST_MOVE;
    }
    public String getMoveTutor(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(moveTutors.get(_index));
    }
    public String clickMoveTutors(int _index) {
        String move_ = moveTutors.get(_index);
        getForms().put(CST_MOVE,move_);
        return CST_MOVE;
    }
    public String getEggPk(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(eggGroupsPk.get(_index));
    }
    public String clickEggPk(int _index) {
        String pk_ = eggGroupsPk.get(_index);
        getForms().put(CST_PK,pk_);
        return CST_POKEMON;
    }
    public boolean isAppearingAnyWhere() {
        int nbPlaces_ = places.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            int nbLayers_ = layers(i).size();
            for (int j = IndexConstants.FIRST_INDEX; j < nbLayers_; j++) {
                if (isAppearing(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isMultiLayer(int _index) {
        return layers(_index).size() > IndexConstants.SECOND_INDEX;
    }
    public CustList<Level> layers(int _index) {
        Place pl_ = places.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    public boolean isAppearingPlace(int _index) {
        int nbLayers_ = layers(_index).size();
        for (int j = IndexConstants.FIRST_INDEX; j < nbLayers_; j++) {
            if (isAppearing(_index,j)) {
                return true;
            }
        }
        return false;
    }
    public boolean isAppearing(int _indexOne, int _indexTwo) {
        Place pl_ = places.get(_indexOne).getPlace();
        Level level_ = pl_.getLevelsMap().getVal((byte) _indexTwo);
        if (!(level_ instanceof LevelWithWildPokemon)) {
            return false;
        }
        LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
        for (AreaApparition a: w_.getWildPokemonAreas()) {
            for (Pokemon p: a.getWildPokemon()) {
                if (StringUtil.quickEq(p.getName(), name)) {
                    return true;
                }
            }
            for (Pokemon p: a.getWildPokemonFishing()) {
                if (StringUtil.quickEq(p.getName(), name)) {
                    return true;
                }
            }
        }
        for (Pokemon p: w_.getLegendaryPks().values()) {
            if (StringUtil.quickEq(p.getName(), name)) {
                return true;
            }
        }
        return false;
    }
    public String clickLevel(int _indexOne, int _indexTwo) {
        getForms().removeKey(CST_INSIDE);
        getForms().put(CST_LEVEL_MAP_INDEX, _indexTwo);
        getForms().put(CST_PLACE_MAP_INDEX, _indexOne);
        getForms().put(CST_PROPONE_LINK, false);
        getForms().put(CST_PROPONE_TILE, false);
        getForms().put(CST_SEE_AREA, false);
        for (Direction d: Direction.values()) {
            getForms().put(StringUtil.concat(CST_PROPONE_LINK_VAR,d.name()), false);
        }
        return CST_LEVEL;
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

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public long getExpRate() {
        return expRate;
    }

    public StringList getStatistics() {
        return statistics;
    }

    public CustList<LevelMove> getLevMoves() {
        return levMoves;
    }

    public ShortTreeMap<String> getTechnicalMoves() {
        return technicalMoves;
    }

    public ShortTreeMap<String> getHiddenMoves() {
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
        DataBase data_ = getDataBase();
        TreeMap<MiniMapCoords, String> map_ = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        for (MiniMapCoordsTile m_: data_.getMap().getMiniMap().entryList()) {
            int[][] image_ = data_.getMiniMap(m_.getTileMap().getFile());
            map_.put(m_.getMiniMapCoords(), BaseSixtyFourUtil.getStringByImage(image_));
        }
        return map_;
    }
}