package aiki.beans.pokemon;

import aiki.beans.CommonBean;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.levels.Level;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Place;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTile;
import aiki.map.util.MiniMapCoordsTileInts;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class PokemonBean extends CommonBean {

    private static final String PAGE_LEVELGENDER = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVELGENDER_HTML;
    private static final String PAGE_LEVEL = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVEL_HTML;
    private static final String PAGE_HAPPY = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOHAPPY_HTML;
    private static final String PAGE_MOVE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOMOVE_HTML;
    private static final String PAGE_ITEM = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOITEM_HTML;
    private static final String PAGE_STONEGENDER = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONEGENDER_HTML;
    private static final String PAGE_STONE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONE_HTML;
    private static final String PAGE_TYPE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTYPE_HTML;
    private static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTEAM_HTML;
    private String name;
    private int[][] backImage;
    private int[][] frontImage;
    private String displayName;
    private Rate weight;
    private Rate height;
    private StringList possibleGenders;
    private StringList types;
    private StringList abilities;
    private long catchingRate;
    private StringList evolutions;
    private IdList<Statistic> statisticsEnum;
    private StringList statistics;
    private String evoBase;
    private String expEvo;
    private long expRate;
    private CustList<LevelMove> levMoves;
    private IntTreeMap< String> technicalMoves;
    private IntTreeMap< String> hiddenMoves;
    private StringList moveTutors;
    private LgInt hatchingSteps;
    private StringList eggGroupsPk;
    private NatStringTreeMap<String> mapVars;
    private CustList<PlaceIndex> places;
    private MiniMapCoordsTileInts images;

    private DictionaryComparator<MiniMapCoords, String> namesPlaces;

    private Ints placesAppears;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        places = PlaceIndex.places(data_);
//        places.sort(new NaturalComparator<PlaceIndex>() {
//            @Override
//            public int compare(PlaceIndex _o1, PlaceIndex _o2) {
//                return _o1.getPlace().getName().compareTo(_o2.getPlace().getName());
//            }
//        });
        images = data_.getMap().getImages(data_);
        namesPlaces = DictionaryComparatorUtil.buildMiniMapCoords();
        placesAppears = new Ints();
        for (MiniMapCoords m: images.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        name = getForms().getValStr(CST_PK);
        int nbPlaces_ = places.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPlaces_; i++) {
            if (isAppearingPlace(i)) {
                placesAppears.add(i);
            }
        }
        backImage = data_.getMaxiPkBack().getVal(name).getImage();
        //ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkBack().getVal(name));
        frontImage = data_.getMaxiPkFront().getVal(name).getImage();
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
        abilities = new StringList(pk_.getAbilities());
        abilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        catchingRate = pk_.getCatchingRate();
        evolutions = new StringList(pk_.getEvolutions().getKeys());
        evolutions.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
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
        statisticsEnum = new IdList<Statistic>();
        statistics = new StringList();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
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
        technicalMoves = new IntTreeMap< String>();
        for (Integer s: pk_.getTechnicalMoves()) {
            technicalMoves.put(s, translationsMoves_.getVal(data_.getTm().getVal(s)));
        }
        hiddenMoves = new IntTreeMap< String>();
        for (Integer s: pk_.getHiddenMoves()) {
            hiddenMoves.put(s, translationsMoves_.getVal(data_.getHm().getVal(s)));
        }
        moveTutors = new StringList(pk_.getMoveTutors());
        moveTutors.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        //eggGroups = new StringList();
        initEggGroup(pk_);
        hatchingSteps = pk_.getHatchingSteps();
    }

    private void initEggGroup(PokemonData _pk) {
        DataBase data_ = getDataBase();
        eggGroupsPk = new StringList();
        //Map<String,String> translationsEggs_;
        //translationsEggs_ = data_.getTranslatedEggs().getVal(getLanguage());
        for (String e: _pk.getEggGroups()) {
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
        eggGroupsPk.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        eggGroupsPk.removeDuplicates();
    }

    public int[][] getMiniMapImage(int _index) {
        int[][] image_ = images.getValue(_index);
        MiniMapCoords key_ = images.getKey(_index);
        DataBase data_ = getDataBase();
        int pl_ = data_.getMap().getMiniMap().getVal(key_).getPlace();
        boolean appear_ = false;
        for (int p: placesAppears) {
            PlaceIndex plInd_ = places.get(p);
            if (plInd_.getIndex() == pl_) {
                appear_ = true;
                break;
            }
        }
        if (appear_) {
            int[][] miniImg_ = data_.getMiniPk(name);
            image_ = ConverterBufferedImage.stackImages(image_, miniImg_);
        }
        return image_;
    }
    public String getPlaceName(int _index) {
        return namesPlaces.getValue(_index);
    }
    public int getMapWidth() {
        int w_ = 0;
        int y_ = images.getKey(w_).getYcoords();
        while (images.getKey(w_).getYcoords() != y_+1) {
            w_++;
        }
        return w_;
    }
//    public boolean isFirstRow(int _index) {
//        if (_index == 0) {
//            return false;
//        }
//        MiniMapCoords key_ = images.getKey(_index);
//        return key_.getXcoords() == IndexConstants.FIRST_INDEX;
//    }
    public String roundWeight() {
        return weight.evaluate(2);
    }
    public String roundHeight() {
        return height.evaluate(2);
    }
    public String clickPokedex() {
        getForms().safePokedex(CST_POKEMON_SET);
        return PkScriptPages.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML;
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
        return tryRedirectAb(abilities.get(_index));
    }
    public String clickBase() {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        return tryRedirectPk(pk_.getBaseEvo());
    }
    public long getBase(int _index) {
        DataBase data_ = getDataBase();
        PokemonData pk_ = data_.getPokemon(name);
        Statistic stat_ = statisticsEnum.get(_index);
        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
        return statEv_.getBase();
    }
    public long getEv(int _index) {
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
        return tryRedirectMv(move_);
    }
    public String clickTechnicalMove(int _index) {
        DataBase data_ = getDataBase();
        String move_ = data_.getTm().getVal(technicalMoves.getKey(_index));
        return tryRedirectMv(move_);
    }
    public String clickHiddenMove(int _index) {
        DataBase data_ = getDataBase();
        String move_ = data_.getHm().getVal(hiddenMoves.getKey(_index));
        return tryRedirectMv(move_);
    }
    public String getMoveTutor(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translationsMoves_.getVal(moveTutors.get(_index));
    }
    public String clickMoveTutors(int _index) {
        String move_ = moveTutors.get(_index);
        return tryRedirectMv(move_);
    }
    public String getEggPk(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translationsPokemon_.getVal(eggGroupsPk.get(_index));
    }
    public String clickEggPk(int _index) {
        String pk_ = eggGroupsPk.get(_index);
        return tryRedirectPk(pk_);
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
        Level level_ = pl_.getLevelsMap().getVal(_indexTwo);
        if (!(level_ instanceof LevelWithWildPokemon)) {
            return false;
        }
        LevelWithWildPokemon w_ = (LevelWithWildPokemon) level_;
        for (AbsAreaApparition a: w_.getWildPokemonAreas()) {
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
        CommonBean.feedForms(_indexOne, _indexTwo, getForms());
        return PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int[][] getBackImage() {
        return backImage;
    }

    public int[][] getFrontImage() {
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

    public long getCatchingRate() {
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

    public IntTreeMap<String> getTechnicalMoves() {
        return technicalMoves;
    }

    public IntTreeMap<String> getHiddenMoves() {
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

    public DictionaryComparator<MiniMapCoords,int[][]> getImages() {
        DataBase data_ = getDataBase();
        DictionaryComparator<MiniMapCoords, int[][]> map_ = DictionaryComparatorUtil.buildMiniMapImgs();
        for (MiniMapCoordsTile m_: data_.getMap().getMiniMap().entryList()) {
            int[][] image_ = data_.getMiniMap(m_.getTileMap().getFile());
            map_.put(m_.getMiniMapCoords(), image_);
        }
        return map_;
    }
}