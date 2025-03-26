package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.pokemon.evolutions.*;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.LevelMove;
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
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class PokemonBean extends CommonBean implements BeanRenderWithAppName  {

//    private static final String PAGE_LEVELGENDER = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVELGENDER_HTML;
//    private static final String PAGE_LEVEL = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVEL_HTML;
//    private static final String PAGE_HAPPY = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOHAPPY_HTML;
//    private static final String PAGE_MOVE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOMOVE_HTML;
//    private static final String PAGE_ITEM = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOITEM_HTML;
//    private static final String PAGE_STONEGENDER = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONEGENDER_HTML;
//    private static final String PAGE_STONE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONE_HTML;
//    private static final String PAGE_TYPE = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTYPE_HTML;
//    private static final String PAGE_TEAM = PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTEAM_HTML;
    private String name;
    private int[][] backImage;
    private int[][] frontImage;
    private String displayName;
    private Rate weight;
    private Rate height;
    private CustList<TranslatedKey> possibleGenders;
    private CustList<TranslatedKey> types;
    private CustList<TranslatedKey> abilities;
    private long catchingRate;
    private CustList<TranslatedKey> evolutions;
//    private IdList<Statistic> statisticsEnum;
    private CustList<StringStatBaseEv> statistics;
    private TranslatedKey evoBase;
    private String expEvo;
    private long expRate;
    private CustList<LevelMoveTranslatedKey> levMoves;
    private IntTreeMap< TranslatedKey> technicalMoves;
    private IntTreeMap< TranslatedKey> hiddenMoves;
    private CustList<TranslatedKey> moveTutors;
    private LgInt hatchingSteps;
    private CustList<TranslatedKey> eggGroupsPk;
    private NatStringTreeMap<String> mapVars;
    private CustList<PlaceIndex> places;
    private MiniMapCoordsTileInts images;

    private DictionaryComparator<MiniMapCoords, String> namesPlaces;

    private Ints placesAppears;
    private CustList<EvolutionBean> beans;
    private long happiness;
    private long happinessHatch;
    public PokemonBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesDataPokemonData.M_P_72_TITLE),displayName));
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_GENERAL);
        formatMessageAnc(new PokemonBeanClickPokedex(this),MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_POKEDEX);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_NAME,displayName);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_BACK);
        addImg(getBackImage());
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_FRONT);
        addImg(getFrontImage());
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_WEIGHT,displayName,weight.toNumberString(),roundWeight());
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HEIGHT,displayName,height.toNumberString(),roundHeight());
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,possibleGenders,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_GENDERS,displayName);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,types,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_TYPES,displayName);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,abilities,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_ABILITIES,displayName);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_CATCHINGRATE,displayName,Long.toString(catchingRate));
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_TREE);
        new BeanDisplayListGrid<EvolutionBean>(new BeanDisplayEvolution()).displayGridParam(this,beans,new String[]{displayName},MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_EVOLUTIONS_TITLE,MessagesDataPokemonData.M_P_72_EVOLUTIONS_KEY,MessagesDataPokemonData.M_P_72_GET_EVO);
//        int len_ = beans.size();
//        for (int i = 0; i < len_; i++) {
//            evo(beans.get(i));
//        }
//        feedParents();
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_BASE,displayName);
        formatMessageDir(evoBase);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_EXP);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_EXP_GROWTH,displayName,expEvo);
        mapVarsInit(mapVars);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_PTS_EXP,displayName,Long.toString(expRate));
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_STATISTICS_TITLE);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_STATISTICS,displayName);
        new BeanDisplayListGrid<StringStatBaseEv>(new BeanDisplayStatBaseEv()).displayGrid(this,statistics,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_STATISTICS_TITLE,MessagesDataPokemonData.M_P_72_STATISTICS_KEY,MessagesDataPokemonData.M_P_72_STATISTICS_VALUE,MessagesDataPokemonData.M_P_72_STATISTICS_EV);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_MOVES,displayName);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_LEVEL_MOVES,displayName);
        new BeanDisplayListGrid<LevelMoveTranslatedKey>(new BeanDisplayLevelMove()).displayGrid(this,levMoves,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_MOVES_LEVELS,MessagesDataPokemonData.M_P_72_LEVEL,MessagesDataPokemonData.M_P_72_MOVE);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_TECHNICAL_MOVES,displayName);
        new BeanDisplayMap<Integer,TranslatedKey>(new BeanDisplayInt(),new BeanDisplayTranslatedKey()).displayGrid(this,technicalMoves,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_TM_TITLE,MessagesDataPokemonData.M_P_72_TM_NUMBER,MessagesDataPokemonData.M_P_72_TM_MOVE);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HIDDEN_MOVES,displayName);
        new BeanDisplayMap<Integer,TranslatedKey>(new BeanDisplayInt(),new BeanDisplayTranslatedKey()).displayGrid(this,hiddenMoves,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HM_TITLE,MessagesDataPokemonData.M_P_72_HM_NUMBER,MessagesDataPokemonData.M_P_72_HM_MOVE);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_MOVE_TUTORS,displayName);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,moveTutors);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_EGG_GROUPS);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,eggGroupsPk,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_EGG_GROUPS_PK,displayName);
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HATCHING,displayName,hatchingSteps.toNumberString());
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HAPPINESS);
        formatMessageDir(Long.toString(happiness));
        formatMessage(MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_HAPPINESS_HATCH);
        formatMessageDir(Long.toString(happinessHatch));
        if (!isAppearingAnyWhere()) {
            formatMessageAnc(new PokemonBeanClickPokedex(this),MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_POKEDEX);
            return;
        }
        initPlacesLevelList();
        initGrid();
        getBuilder().colCount(getMapWidth());
        DictionaryComparator<MiniMapCoords, int[][]> dic_ = getImages();
        int dLen_ = dic_.size();
        for (int i = 0; i < dLen_; i++) {
            getBuilder().addImgCts(getMiniMapImage(i),namesPlaces.getValue(i));
        }
        feedParents();
        formatMessageAnc(new PokemonBeanClickPokedex(this),MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_POKEDEX);
    }

    public CustList<EvolutionBean> getBeans() {
        return beans;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.PK_DATA).getMapping();
    }
    public void initPlacesLevelList() {
//        initPage();
        int len_ = places.size();
        for (int i = 0; i < len_; i++) {
            if (!isAppearingPlace(i)) {
                continue;
            }
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(places.get(i).getPlace().getName());
            if (isMultiLayer(places,i)) {
                initPage();
                CustList<Level> layers_ = layers(places, i);
                int lCount_ = layers_.size();
                for (int j = 0; j < lCount_; j++) {
                    if (isAppearing(i,j)) {
                        initLine();
                        paintMetaLabelDisk();
                        getBuilder().formatMessageDir(Long.toString(j),new MapBeanClickLevelBeanAction(this,i,j));
                        feedParents();
                    }
                }
                feedParents();
            } else {
                initLine();
                paintMetaLabelDisk();
                formatMessageAnc(new MapBeanClickLevelBeanAction(this,i,0),MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_GOLEVEL);
                feedParents();
            }
            feedParents();
        }
//        feedParents();
    }
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
        possibleGenders = new CustList<TranslatedKey>();
        for (Gender g: pk_.getGenderRep().getPossibleGenders()) {
            possibleGenders.add(buildGender(getFacade(),g));
        }
        types = new CustList<TranslatedKey>();
        for (String t: pk_.getTypes()) {
            types.add(buildTy(getFacade(),t));
        }
        types.sortElts(new ComparingTranslatedKey());
        abilities = listTrStringsAb(pk_.getAbilities(),getFacade());
        catchingRate = pk_.getCatchingRate();
        evolutions = listTrStringsPk(pk_.getEvolutions().getKeys(),getFacade());
        CustList<EvolutionBean> evoBean_ = new CustList<EvolutionBean>();
        beans = evoBean_;
        int len_ = evolutions.size();
        for (int i = 0; i < len_; i++) {
            TranslatedKey tk_ = evolutions.get(i);
            build(evoBean_,pk_.getEvolutions(), tk_.getKey());
        }
        evoBase = buildPk(getFacade(),pk_.getBaseEvo());
        expEvo = data_.getFormula(data_.getExpGrowth(pk_.getExpEvo()),getLanguage());
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(data_.getExpGrowth(pk_.getExpEvo()),getLanguage());
        mapVars = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVars.put(k, mapVars_.getVal(k));
        }
        expRate = pk_.getExpRate();
//        statisticsEnum = new IdList<Statistic>();
        statistics = new CustList<StringStatBaseEv>();
        for (Statistic s: Statistic.getStatisticsWithBase()) {
//            statisticsEnum.add(s);
            statistics.add(new StringStatBaseEv(buildSi(getFacade(),s),pk_.getStatistics().getVal(s)));
        }
        levMoves = new CustList<LevelMoveTranslatedKey>();
        for (LevelMove l: pk_.getLevMoves()) {
            levMoves.add(new LevelMoveTranslatedKey(buildMv(getFacade(),l.getMove()),l.getLevel()));
        }
        technicalMoves = new IntTreeMap< TranslatedKey>();
        for (Integer s: pk_.getTechnicalMoves()) {
            technicalMoves.put(s, buildMv(getFacade(),data_.getTm().getVal(s)));
        }
        hiddenMoves = new IntTreeMap< TranslatedKey>();
        for (Integer s: pk_.getHiddenMoves()) {
            hiddenMoves.put(s, buildMv(getFacade(),data_.getHm().getVal(s)));
        }
        moveTutors = listTrStringsMv(pk_.getMoveTutors(),getFacade());
        //eggGroups = new StringList();
        initEggGroup(pk_);
        hatchingSteps = pk_.getHatchingSteps();
        happiness = pk_.getHappiness();
        happinessHatch = pk_.getHappinessHatch();
    }

    private void build(CustList<EvolutionBean> _curr, StringMap<Evolution> _effs, String _n) {
        Evolution evo_ = _effs.getVal(_n);
        if (evo_ instanceof EvolutionLevelGender) {
            build(_curr, new EvolutionLevelGenderBean(),_n);
        } else if (evo_ instanceof EvolutionLevel) {
            build(_curr, new EvolutionLevelBean(),_n);
        } else if (evo_ instanceof EvolutionHappiness) {
            build(_curr, new EvolutionHappinessBean(),_n);
        } else if (evo_ instanceof EvolutionMove) {
            build(_curr, new EvolutionMoveBean(),_n);
        } else if (evo_ instanceof EvolutionItem) {
            build(_curr, new EvolutionItemBean(),_n);
        } else if (evo_ instanceof EvolutionStoneGender) {
            build(_curr, new EvolutionStoneGenderBean(),_n);
        } else  if (evo_ instanceof EvolutionStone) {
            build(_curr, new EvolutionStoneBean(),_n);
        } else  if (evo_ instanceof EvolutionMoveType) {
            build(_curr, new EvolutionMoveTypeBean(),_n);
        } else {
            build(_curr, new EvolutionTeamBean(),_n);
        }
    }
    private void build(CustList<EvolutionBean> _feed, EvolutionBean _b, String _n) {
        fwd(_b);
//        _b.setIndex(_i);
        _b.setBase(getName());
        _b.setName(_n);
        _b.beforeDisplaying();
        _feed.add(_b);
    }
    private void initEggGroup(PokemonData _pk) {
        DataBase data_ = getDataBase();
        //CustList<TranslatedKey>
        StringList eggGroups_ = new StringList();
        //Map<String,String> translationsEggs_;
        //translationsEggs_ = data_.getTranslatedEggs().getVal(getLanguage());
        for (String e: _pk.getEggGroups()) {
            //eggGroups.add(translationsEggs_.getVal(e));
            for (String p: data_.getPokedex().getKeys()) {
                PokemonData pkData_ = data_.getPokemon(p);
                if (StringUtil.contains(pkData_.getEggGroups(), e)) {
                    eggGroups_.add(p);
                }
            }
        }
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getEggGroups(), data_.getDefaultEggGroup())) {
                eggGroups_.add(p);
            }
        }
        //eggGroups.sort();
        //eggGroups.removeDuplicates();
        eggGroups_.removeDuplicates();
        eggGroupsPk = listTrStringsPk(eggGroups_,getFacade());
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
        return width(images);
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
        return CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML;
    }
//    public String getPage(int _index) {
//        DataBase data_ = getDataBase();
//        PokemonData pk_ = data_.getPokemon(name);
//        Evolution evo_ = pk_.getEvolutions().getVal(evolutions.get(_index).getKey());
//        if (evo_ instanceof EvolutionLevelGender) {
//            return PAGE_LEVELGENDER;
//        }
//        if (evo_ instanceof EvolutionLevel) {
//            return PAGE_LEVEL;
//        }
//        if (evo_ instanceof EvolutionHappiness) {
//            return PAGE_HAPPY;
//        }
//        if (evo_ instanceof EvolutionMove) {
//            return PAGE_MOVE;
//        }
//        if (evo_ instanceof EvolutionItem) {
//            return PAGE_ITEM;
//        }
//        if (evo_ instanceof EvolutionStoneGender) {
//            return PAGE_STONEGENDER;
//        }
//        if (evo_ instanceof EvolutionStone) {
//            return PAGE_STONE;
//        }
//        if (evo_ instanceof EvolutionMoveType) {
//            return PAGE_TYPE;
//        }
//        return PAGE_TEAM;
//    }

    public String getTrAbility(int _index) {
        return abilities.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        return data_.translateAbility(abilities.get(_index));
    }
    public String clickAbility(int _index) {
        return tryRedirect(abilities.get(_index));
    }
    public String clickBase() {
        return tryRedirect(evoBase);
//        DataBase data_ = getDataBase();
//        PokemonData pk_ = data_.getPokemon(name);
//        return tryRedirectPk(pk_.getBaseEvo());
    }
    public long getBase(int _index) {
        return statistics.get(_index).getStat().getBase();
//        DataBase data_ = getDataBase();
//        PokemonData pk_ = data_.getPokemon(name);
//        Statistic stat_ = statisticsEnum.get(_index);
//        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
//        return statEv_.getBase();
    }
    public long getEv(int _index) {
        return statistics.get(_index).getStat().getEv();
//        DataBase data_ = getDataBase();
//        PokemonData pk_ = data_.getPokemon(name);
//        Statistic stat_ = statisticsEnum.get(_index);
//        StatBaseEv statEv_ = pk_.getStatistics().getVal(stat_);
//        return statEv_.getEv();
    }
    public String clickMove(int _index) {
        return tryRedirect(levMoves.get(_index).getMove());
    }
    public String clickTechnicalMove(int _index) {
        return tryRedirect(technicalMoves.getValue(_index));
    }
    public String clickHiddenMove(int _index) {
        return tryRedirect(hiddenMoves.getValue(_index));
    }
    public String getMoveTutor(int _index) {
        return moveTutors.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translationsMoves_.getVal(moveTutors.get(_index));
    }
    public String clickMoveTutors(int _index) {
        return tryRedirect(moveTutors.get(_index));
    }
    public String getEggPk(int _index) {
        return eggGroupsPk.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translationsPokemon_.getVal(eggGroupsPk.get(_index));
    }
    public String clickEggPk(int _index) {
        return tryRedirect(eggGroupsPk.get(_index));
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
        return CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
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

    public CustList<TranslatedKey> getPossibleGenders() {
        return possibleGenders;
    }

    public CustList<TranslatedKey> getTypes() {
        return types;
    }

    public CustList<TranslatedKey> getAbilities() {
        return abilities;
    }

    public long getCatchingRate() {
        return catchingRate;
    }

    public CustList<TranslatedKey> getEvolutions() {
        return evolutions;
    }

    public String getName() {
        return name;
    }

    public String getEvoBase() {
        return evoBase.getTranslation();
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

    public CustList<StringStatBaseEv> getStatistics() {
        return statistics;
    }

    public CustList<LevelMoveTranslatedKey> getLevMoves() {
        return levMoves;
    }

    public IntTreeMap<TranslatedKey> getTechnicalMoves() {
        return technicalMoves;
    }

    public IntTreeMap<TranslatedKey> getHiddenMoves() {
        return hiddenMoves;
    }

    public CustList<TranslatedKey> getMoveTutors() {
        return moveTutors;
    }

    public CustList<TranslatedKey> getEggGroupsPk() {
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