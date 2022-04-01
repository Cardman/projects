package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.comparators.ComparatorMiniMapCoords;
import aiki.comparators.ComparatorTrStrings;
import aiki.comparators.TrMovesComparator;
import aiki.db.DataBase;
import aiki.fight.items.*;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsTile;
import code.images.BaseSixtyFourUtil;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class GeneralHelpBean extends CommonBean {
    private TreeMap<MiniMapCoords, int[][]> miniMap;

    private TreeMap<MiniMapCoords, String> namesPlaces;
    private String unlockedCity;
    private String begin;

    private Pokemon firstPokemon;
    private byte nbMaxTeam;
    private int minLevel;
    private int maxLevel;
    private int nbMaxMoves;
    private long maxPp;
    private int maxEv;
    private int maxIv;
    private int happinessMax;
    private int nbNecStepsIncrHappiness;
    private short nbMaxStepsSameEvoBase;
    private short nbMaxSteps;
    private StringList pokemonDefaultEggGroup = new StringList();
    private Rate defaultMoney;
    private StringList tm = new StringList();
    private StringList hm = new StringList();
    private StringList types = new StringList();

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        miniMap = data_.getMap().getImages(data_);
        begin = data_.getMap().getPlace(data_.getMap().getBegin().getNumberPlace()).getName();
        firstPokemon = data_.getMap().getFirstPokemon();
        namesPlaces = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        for (MiniMapCoords m: miniMap.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        unlockedCity = BaseSixtyFourUtil.getStringByImage(data_.getMiniMap(data_.getMap().getUnlockedCity()));
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
        pokemonDefaultEggGroup = new StringList();
        for (String p: data_.getPokedex().getKeys()) {
            PokemonData pkData_ = data_.getPokemon(p);
            if (StringUtil.contains(pkData_.getEggGroups(), data_.getDefaultEggGroup())) {
                pokemonDefaultEggGroup.add(p);
            }
        }
        pokemonDefaultEggGroup.sortElts(new ComparatorTrStrings(data_.getTranslatedPokemon().getVal(getLanguage())));
        defaultMoney = data_.getDefaultMoney();
        tm = new StringList(data_.getTm().values());
        tm.sortElts(new TrMovesComparator(data_));
        hm = new StringList(data_.getHm().values());
        hm.sortElts(new TrMovesComparator(data_));
        types = new StringList(data_.getTypes());
        types.sortElts(new ComparatorTrStrings(data_.getTranslatedTypes().getVal(getLanguage())));
    }
    public String getMiniMapImage(int _index) {
        int[][] image_ = miniMap.getValue(_index);
        return BaseSixtyFourUtil.getStringByImage(image_);
    }
    public int getMapWidth() {
        int w_ = 0;
        while (miniMap.getKey(w_).getYcoords() != IndexConstants.SECOND_INDEX) {
            w_++;
        }
        return w_;
    }
    public boolean isFirstRow(int _index) {
        if (_index == 0) {
            return false;
        }
        MiniMapCoords key_ = miniMap.getKey(_index);
        return key_.getXcoords() == IndexConstants.FIRST_INDEX;
    }
    public String getPlaceName(int _index) {
        return namesPlaces.getValue(_index);
    }
    public String getTrPokemon(int _index) {
        DataBase data_ = getDataBase();
        String pk_ = pokemonDefaultEggGroup.get(_index);
        return data_.translatePokemon(pk_);
    }
    public String clickPokemon(int _index) {
        String pk_ = pokemonDefaultEggGroup.get(_index);
        getForms().put(CST_PK,pk_);
        return CST_POKEMON;
    }
    public String getImage() {
        DataBase data_ = getDataBase();
        String name_ = firstPokemon.getName();
        return BaseSixtyFourUtil.getStringByImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }
    public String getName() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String name_ = firstPokemon.getName();
        return translationsPokemon_.getVal(name_);
    }
    public String clickName() {
        String name_ = firstPokemon.getName();
        getForms().put(CST_PK, name_);
        return CST_POKEMON;
    }
    public short getLevel() {
        return firstPokemon.getLevel();
    }
    public String getGender() {
        DataBase data_ = getDataBase();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        Gender gender_ = firstPokemon.getGender();
        return translationsGenders_.getVal(gender_);
    }
    public String getAbility() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String ability_ = firstPokemon.getAbility();
        return translationsAbilities_.getVal(ability_);
    }
    public String clickAbility() {
        String ability_ = firstPokemon.getAbility();
        getForms().put(CST_ABILITY, ability_);
        return CST_ABILITY;
    }
    public boolean firstPokemonHasItem() {
        return !firstPokemon.getItem().isEmpty();
    }
    public String getItem() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = firstPokemon.getItem();
        return translationsItems_.getVal(item_);
    }
    public String clickItem() {
        DataBase data_ = getDataBase();
        String item_ = firstPokemon.getItem();
        getForms().put(CST_ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return CST_BALL;
        }
        if (it_ instanceof Berry) {
            return CST_BERRY;
        }
        if (it_ instanceof Boost) {
            return CST_BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return CST_EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return CST_EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return CST_FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return CST_HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return CST_HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return CST_HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return CST_HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return CST_HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return CST_ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return CST_REPEL;
        }
        if (it_ instanceof SellingItem) {
            return CST_SELLINGITEM;
        }
        return CST_ITEM;
    }
    public String getMove(int _moveIndex) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getMovesAtLevel().get(_moveIndex);
        return translationsMoves_.getVal(move_);
    }
    public String clickMove(int _moveIndex) {
        String move_ = getMovesAtLevel().get(_moveIndex);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public StringList getMovesAtLevel() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = data_.getPokemon(firstPokemon.getName()).getMovesAtLevel(firstPokemon.getLevel(), data_.getNbMaxMoves());
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        return moves_;
    }
    public String clickTm(int _moveIndex) {
        String move_ = tm.get(_moveIndex);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String clickHm(int _moveIndex) {
        String move_ = hm.get(_moveIndex);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrTm(int _moveIndex) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = tm.get(_moveIndex);
        return translationsMoves_.getVal(move_);
    }
    public String getTmPrice(int _moveIndex) {
        DataBase data_ = getDataBase();
        String move_ = tm.get(_moveIndex);
//        short key_ = data_.getTm().getKeys(move_).first();
        short key_ = data_.getTmByMove(move_).first();
        if (data_.getTmPrice().contains(key_)) {
            return data_.getTmPrice().getVal(key_).toNumberString();
        }
        return DataBase.EMPTY_STRING;
    }
    public String getTrHm(int _moveIndex) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = hm.get(_moveIndex);
        return translationsMoves_.getVal(move_);
    }
    public String getTrType(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index);
        return translationsTypes_.getVal(type_);
    }
    public String getImageType(int _index) {
        DataBase data_ = getDataBase();
        String type_ = types.get(_index);
        return BaseSixtyFourUtil.getStringByImage(data_.getTypesImages().getVal(type_));
    }
    public String getColorType(int _index) {
        DataBase data_ = getDataBase();
        String type_ = types.get(_index);
        String color_ = data_.getTypesColors().getVal(type_);
        String img_ = ConverterBufferedImage.getSquareColorSixtyFour(color_, DataBase.SEPARATOR_RGB, data_.getMap().getSideLength());
        return img_;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMaxEv() {
        return maxEv;
    }

    public int getMaxIv() {
        return maxIv;
    }

    public int getHappinessMax() {
        return happinessMax;
    }

    public String getBegin() {
        return begin;
    }

    public TreeMap<MiniMapCoords,String> getMiniMap() {
        DataBase data_ = getDataBase();
        TreeMap<MiniMapCoords, String> map_ = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        for (MiniMapCoordsTile m_: data_.getMap().getMiniMap().entryList()) {
            int[][] image_ = data_.getMiniMap(m_.getTileMap().getFile());
            map_.put(m_.getMiniMapCoords(), BaseSixtyFourUtil.getStringByImage(image_));
        }
        return map_;
    }

    public String getUnlockedCity() {
        return unlockedCity;
    }

    public byte getNbMaxTeam() {
        return nbMaxTeam;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getNbMaxMoves() {
        return nbMaxMoves;
    }

    public long getMaxPp() {
        return maxPp;
    }

    public int getNbNecStepsIncrHappiness() {
        return nbNecStepsIncrHappiness;
    }

    public short getNbMaxStepsSameEvoBase() {
        return nbMaxStepsSameEvoBase;
    }

    public short getNbMaxSteps() {
        return nbMaxSteps;
    }

    public StringList getPokemonDefaultEggGroup() {
        return pokemonDefaultEggGroup;
    }

    public Rate getDefaultMoney() {
        return defaultMoney;
    }

    public StringList getTm() {
        return tm;
    }

    public StringList getHm() {
        return hm;
    }

    public StringList getTypes() {
        return types;
    }
}