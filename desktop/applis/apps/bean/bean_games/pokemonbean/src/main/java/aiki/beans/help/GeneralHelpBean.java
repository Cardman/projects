package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.map.elements.TranslatedPkElements;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.PokemonData;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTile;
import aiki.map.util.MiniMapCoordsTileInts;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.StringUtil;

public final class GeneralHelpBean extends CommonBean implements BeanRenderWithAppName {
    private MiniMapCoordsTileInts miniMap;

    private DictionaryComparator<MiniMapCoords, String> namesPlaces;
    private int[][] unlockedCity;
    private String begin;

    private TranslatedPkElements firstPokemon;
    private long nbMaxTeam;
    private long minLevel;
    private long maxLevel;
    private long nbMaxMoves;
    private long maxPp;
    private long maxEv;
    private long maxIv;
    private long happinessMax;
    private long nbNecStepsIncrHappiness;
    private long nbMaxStepsSameEvoBase;
    private long nbMaxSteps;
    private CustList<TranslatedKey> pokemonDefaultEggGroup = new CustList<TranslatedKey>();
    private Rate defaultMoney;
    private DictionaryComparator<TranslatedKey,String> tm = new DictionaryComparator<TranslatedKey, String>(new ComparingTranslatedKey());
    private CustList<TranslatedKey> hm = new CustList<TranslatedKey>();
    private DictionaryComparator<TranslatedKey,TranslatedKeyImgs> types = new DictionaryComparator<TranslatedKey,TranslatedKeyImgs>(new ComparingTranslatedKey());
    private int mapWidth;
    public GeneralHelpBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataGeneral.M_P_14_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_INDEX);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_0);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_1);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_2);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_3,Long.toString(maxLevel));
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_4);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_5,Long.toString(maxEv));
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_6,Long.toString(happinessMax));
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_0_7);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_1,begin);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_2);
        initGrid();
        getBuilder().colCount(getMapWidth());
        int len_ = miniMap.size();
        for (int i = 0; i < len_; i++) {
            int[][] img_ = miniMap.getValue(i);
            getBuilder().addImgCts(img_,getPlaceName(i));
        }
        feedParents();
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_3);
        addImg(unlockedCity);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_0);
        disTranslatedPkElements(firstPokemon);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_1,Long.toString(nbMaxTeam));
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_2,Long.toString(minLevel),Long.toString(maxLevel));
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_3,Long.toString(nbMaxMoves));
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_4,Long.toString(maxPp));
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5_0,Long.toString(nbNecStepsIncrHappiness));
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5_1);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5_2);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5_3);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_5_4);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_0);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_1);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_2);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_3);
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_4);
        element(1,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_5,Long.toString(nbMaxStepsSameEvoBase));
        element(1,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_6,Long.toString(nbMaxSteps));
        element(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_7);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_8);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,pokemonDefaultEggGroup);
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_6_9,defaultMoney.toNumberString());
        formatMessage(MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_7,Long.toString(maxIv));
        for (EntryCust<TranslatedKey,String> e:tm.entryList()) {
            if (e.getValue().isEmpty()) {
                e.setValue(StringUtil.nullToEmpty(file().getVal(MessagesDataGeneral.M_P_14_HELP_TM_HM_PRICE_NO)));
            }
        }
        new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,tm,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_TM,MessagesDataGeneral.M_P_14_HELP_TM_HM_MOVE,MessagesDataGeneral.M_P_14_HELP_TM_HM_PRICE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,hm,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_HM);
        new BeanDisplayMap<TranslatedKey,TranslatedKeyImgs>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKeyImgs()).displayGrid(this,types,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_TYPES_INTRO,MessagesDataGeneral.M_P_14_TYPES_NAME,MessagesDataGeneral.M_P_14_TYPES_IMAGE,MessagesDataGeneral.M_P_14_TYPES_COULOUR);
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_INDEX);
    }
    public StringMap<String> file() {
        return file(MessagesPkBean.GENERAL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        miniMap = data_.getMap().getImages(data_);
        begin = data_.getMap().getPlace(data_.getMap().getBegin().getNumberPlace()).getName();
        firstPokemon = new TranslatedPkElements(getFacade(),data_.getMap().getFirstPokemon());
        namesPlaces = DictionaryComparatorUtil.buildMiniMapCoords();
        for (MiniMapCoords m: miniMap.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        unlockedCity = data_.getMiniMap(data_.getMap().getUnlockedCity());
        nbMaxTeam = data_.getNbMaxTeam();
        minLevel = data_.getMinLevel();
        maxLevel = data_.getMaxLevel();
        nbMaxMoves = data_.getNbMaxMoves();
        maxPp = data_.getMaxPp();
        maxEv = data_.getMaxEv();
        maxIv = data_.getMaxIv();
        happinessMax = data_.getHappinessMax();
        nbNecStepsIncrHappiness = data_.getNbNecStepsIncrHappiness();
        nbMaxStepsSameEvoBase = data_.getNbMaxStepsSameEvoBase();
        nbMaxSteps = data_.getNbMaxSteps();
        pokemonDefaultEggGroup = new CustList<TranslatedKey>();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getEggGroups(), data_.getDefaultEggGroup())) {
                pokemonDefaultEggGroup.add(buildPk(getFacade(),p));
            }
        }
        pokemonDefaultEggGroup.sortElts(new ComparingTranslatedKey());
        defaultMoney = data_.getDefaultMoney();
        tm = build(data_.getTm());
        hm = keys(data_.getHm());
        types = buildTypes();
        mapWidth = width(miniMap);
    }
    private DictionaryComparator<TranslatedKey,String> build(IntMap< String> _map) {
        DataBase data_ = getDataBase();
        CustList<TranslatedKey> moves_ = keys(_map);
        DictionaryComparator<TranslatedKey,String> res_ = new DictionaryComparator<TranslatedKey, String>(new ComparingTranslatedKey());
        for (TranslatedKey m: moves_) {
            int key_ = data_.getTmByMove(m.getKey()).first();
            if (data_.getTmPrice().contains(key_)) {
                res_.addEntry(m,data_.getTmPrice().getVal(key_).toNumberString());
            } else {
                res_.addEntry(m,DataBase.EMPTY_STRING);
            }
        }
        return res_;
    }
    private DictionaryComparator<TranslatedKey,TranslatedKeyImgs> buildTypes() {
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey,TranslatedKeyImgs> res_ = new DictionaryComparator<TranslatedKey,TranslatedKeyImgs>(new ComparingTranslatedKey());
        for (TranslatedKey m: listTrStringsTy(data_.getTypes(),getFacade())) {
            res_.addEntry(m,new TranslatedKeyImgs(data_.getTypesImages().getVal(m.getKey()).getImage(),ConverterBufferedImage.getSquareColorSixtyFour(data_.getTypesColors().getVal(m.getKey()), DataBase.SEPARATOR_RGB, data_.getMap().getSideLength())));
        }
        return res_;
    }

    private CustList<TranslatedKey> keys(IntMap<String> _map) {
        CustList<TranslatedKey> moves_ = new CustList<TranslatedKey>();
        for (String v: _map.values()) {
            moves_.add(buildMv(getFacade(),v));
        }
        moves_.sortElts(new ComparingTranslatedKey());
        return moves_;
    }

    public int[][] getMiniMapImage(int _index) {
        return miniMap.getValue(_index);
    }
    public int getMapWidth() {
        return mapWidth;
    }
//    public boolean isFirstRow(int _index) {
//        if (_index == 0) {
//            return false;
//        }
//        MiniMapCoords key_ = miniMap.getKey(_index);
//        return key_.getXcoords() == IndexConstants.FIRST_INDEX;
//    }
    public String getPlaceName(int _index) {
        return namesPlaces.getValue(_index);
    }
    public String getTrPokemon(int _index) {
        return pokemonDefaultEggGroup.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        String pk_ = pokemonDefaultEggGroup.get(_index);
//        return data_.translatePokemon(pk_);
    }
    public String clickPokemon(int _index) {
        return tryRedirect(pokemonDefaultEggGroup.get(_index));
    }
    public int[][] getImage() {
        return firstPokemon.getImage();
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName() {
        return firstPokemon.getName().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsPokemon_;
//        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        String name_ = firstPokemon.getName();
//        return translationsPokemon_.getVal(name_);
    }
    public String clickName() {
        return tryRedirect(firstPokemon.getName());
    }
    public long getLevel() {
        return firstPokemon.getLevel();
    }
    public String getGender() {
        return firstPokemon.getGender().getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Gender,String> translationsGenders_;
//        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        Gender gender_ = firstPokemon.getGender();
//        return translationsGenders_.getVal(gender_);
    }
    public String getAbility() {
        return firstPokemon.getAbility().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        String ability_ = firstPokemon.getAbility();
//        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility() {
        return tryRedirect(firstPokemon.getAbility());
    }
    public boolean firstPokemonHasItem() {
        return !firstPokemon.getItem().getKey().isEmpty();
    }
    public String getItem() {
        return firstPokemon.getItem().getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsItems_;
//        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
//        String item_ = firstPokemon.getItem();
//        return translationsItems_.getVal(item_);
    }
    public String clickItem() {
        return tryRedirect(firstPokemon.getItem());
//        if (it_ instanceof Ball) {
//            return CST_BALL;
//        }
//        if (it_ instanceof Berry) {
//            return CST_BERRY;
//        }
//        if (it_ instanceof Boost) {
//            return CST_BOOST;
//        }
//        if (it_ instanceof EvolvingItem) {
//            return CST_EVOLVINGITEM;
//        }
//        if (it_ instanceof EvolvingStone) {
//            return CST_EVOLVINGSTONE;
//        }
//        if (it_ instanceof Fossil) {
//            return CST_FOSSIL;
//        }
//        if (it_ instanceof HealingHpStatus) {
//            return CST_HEALINGHPSTATUS;
//        }
//        if (it_ instanceof HealingStatus) {
//            return CST_HEALINGSTATUS;
//        }
//        if (it_ instanceof HealingHp) {
//            return CST_HEALINGHP;
//        }
//        if (it_ instanceof HealingPp) {
//            return CST_HEALINGPP;
//        }
//        if (it_ instanceof HealingItem) {
//            return CST_HEALINGITEM;
//        }
//        if (it_ instanceof ItemForBattle) {
//            return CST_ITEMFORBATTLE;
//        }
//        if (it_ instanceof Repel) {
//            return CST_REPEL;
//        }
//        if (it_ instanceof SellingItem) {
//            return CST_SELLINGITEM;
//        }
//        return CST_ITEM;
    }
    public String getMoveFirstPk(int _moveIndex) {
        return getMovesAtLevelFirstPk().get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = getMovesAtLevelFirstPk().get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String clickMoveFirstPk(int _moveIndex) {
        return tryRedirect(getMovesAtLevelFirstPk().get(_moveIndex));
    }
    public CustList<TranslatedKey> getMovesAtLevelFirstPk() {
        return firstPokemon.getMoves();
//        DataBase data_ = getDataBase();
//        StringList moves_ = data_.getPokemon(firstPokemon.getName()).getMovesAtLevel(firstPokemon.getLevel(), data_.getNbMaxMoves());
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
//        return moves_;
    }
    public String clickTm(int _moveIndex) {
        return tryRedirect(tm.getKey(_moveIndex));
    }
    public String clickHm(int _moveIndex) {
        return tryRedirect(hm.get(_moveIndex));
    }
    public String getTrTm(int _moveIndex) {
        return tm.getKey(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = tm.get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String getTmPrice(int _moveIndex) {
        return tm.getValue(_moveIndex);
//        DataBase data_ = getDataBase();
//        String move_ = tm.get(_moveIndex).getKey();
////        short key_ = data_.getTm().getKeys(move_).first();
//        int key_ = data_.getTmByMove(move_).first();
//        if (data_.getTmPrice().contains(key_)) {
//            return data_.getTmPrice().getVal(key_).toNumberString();
//        }
//        return DataBase.EMPTY_STRING;
    }
    public String getTrHm(int _moveIndex) {
        return hm.get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = hm.get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String getTrType(int _index) {
        return types.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String type_ = types.get(_index);
//        return translationsTypes_.getVal(type_);
    }
    public int[][] getImageType(int _index) {
        return types.getValue(_index).getImg();
//        DataBase data_ = getDataBase();
////        String type_ = types.get(_index);
////        return data_.getTypesImages().getVal(type_).getImage();
//        return data_.getTypesImages().getVal(types.get(_index).getKey()).getImage();
    }
    public int[][] getColorType(int _index) {
        return types.getValue(_index).getColor();
//        DataBase data_ = getDataBase();
////        String type_ = types.get(_index);
//        String color_ = data_.getTypesColors().getVal(types.get(_index).getKey());
//        return ConverterBufferedImage.getSquareColorSixtyFour(color_, DataBase.SEPARATOR_RGB, data_.getMap().getSideLength());
    }

    public long getMaxLevel() {
        return maxLevel;
    }

    public long getMaxEv() {
        return maxEv;
    }

    public long getMaxIv() {
        return maxIv;
    }

    public long getHappinessMax() {
        return happinessMax;
    }

    public String getBegin() {
        return begin;
    }

    public DictionaryComparator<MiniMapCoords,int[][]> getMiniMap() {
        DataBase data_ = getDataBase();
        DictionaryComparator<MiniMapCoords, int[][]> map_ = DictionaryComparatorUtil.buildMiniMapImgs();
        for (MiniMapCoordsTile m_: data_.getMap().getMiniMap().entryList()) {
            int[][] image_ = data_.getMiniMap(m_.getTileMap().getFile());
            map_.put(m_.getMiniMapCoords(), image_);
        }
        return map_;
    }

    public int[][] getUnlockedCity() {
        return unlockedCity;
    }

    public long getNbMaxTeam() {
        return nbMaxTeam;
    }

    public long getMinLevel() {
        return minLevel;
    }

    public long getNbMaxMoves() {
        return nbMaxMoves;
    }

    public long getMaxPp() {
        return maxPp;
    }

    public long getNbNecStepsIncrHappiness() {
        return nbNecStepsIncrHappiness;
    }

    public long getNbMaxStepsSameEvoBase() {
        return nbMaxStepsSameEvoBase;
    }

    public long getNbMaxSteps() {
        return nbMaxSteps;
    }

    public CustList<TranslatedKey> getPokemonDefaultEggGroup() {
        return pokemonDefaultEggGroup;
    }

    public Rate getDefaultMoney() {
        return defaultMoney;
    }

    public DictionaryComparator<TranslatedKey,String> getTm() {
        return tm;
    }

    public CustList<TranslatedKey> getHm() {
        return hm;
    }

    public CustList<TranslatedKey> getTypes() {
        return types.getKeys();
    }
}