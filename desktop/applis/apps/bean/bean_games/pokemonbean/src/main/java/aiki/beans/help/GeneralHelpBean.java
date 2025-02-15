package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.beans.map.elements.TranslatedPkElements;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTile;
import aiki.map.util.MiniMapCoordsTileInts;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;

public class GeneralHelpBean extends CommonBean {
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
    private CustList<TranslatedKey> tm = new CustList<TranslatedKey>();
    private CustList<TranslatedKey> hm = new CustList<TranslatedKey>();
    private StringList types = new StringList();
    private int mapWidth;

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
        hm = build(data_.getHm());
        types = new StringList(data_.getTypes());
        types.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        mapWidth = width(miniMap);
    }
    private CustList<TranslatedKey> build(IntMap< String> _map) {
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
        return tryRedirect(tm.get(_moveIndex));
    }
    public String clickHm(int _moveIndex) {
        return tryRedirect(hm.get(_moveIndex));
    }
    public String getTrTm(int _moveIndex) {
        return tm.get(_moveIndex).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        String move_ = tm.get(_moveIndex);
//        return translationsMoves_.getVal(move_);
    }
    public String getTmPrice(int _moveIndex) {
        DataBase data_ = getDataBase();
        String move_ = tm.get(_moveIndex).getKey();
//        short key_ = data_.getTm().getKeys(move_).first();
        int key_ = data_.getTmByMove(move_).first();
        if (data_.getTmPrice().contains(key_)) {
            return data_.getTmPrice().getVal(key_).toNumberString();
        }
        return DataBase.EMPTY_STRING;
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
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index);
        return translationsTypes_.getVal(type_);
    }
    public int[][] getImageType(int _index) {
        DataBase data_ = getDataBase();
        String type_ = types.get(_index);
        return data_.getTypesImages().getVal(type_).getImage();
    }
    public int[][] getColorType(int _index) {
        DataBase data_ = getDataBase();
        String type_ = types.get(_index);
        String color_ = data_.getTypesColors().getVal(type_);
        return ConverterBufferedImage.getSquareColorSixtyFour(color_, DataBase.SEPARATOR_RGB, data_.getMap().getSideLength());
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

    public CustList<TranslatedKey> getTm() {
        return tm;
    }

    public CustList<TranslatedKey> getHm() {
        return hm;
    }

    public StringList getTypes() {
        return types;
    }
}