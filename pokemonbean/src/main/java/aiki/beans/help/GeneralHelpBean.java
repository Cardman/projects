package aiki.beans.help;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorMiniMapCoords;
import aiki.comparators.ComparatorTrStrings;
import aiki.comparators.TrMovesComparator;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;

public class GeneralHelpBean extends CommonBean {

    @Accessible
    private TreeMap<MiniMapCoords, String> miniMap;

    private TreeMap<MiniMapCoords, String> namesPlaces;

    @Accessible
    private String unlockedCity;

    @Accessible
    private String begin;

    private Pokemon firstPokemon;

    @Accessible
    private byte nbMaxTeam;

    @Accessible
    private int minLevel;

    @Accessible
    private int maxLevel;

    @Accessible
    private int nbMaxMoves;

    @Accessible
    private long maxPp;

    @Accessible
    private int maxEv;

    @Accessible
    private int maxIv;

    @Accessible
    private int happinessMax;

    @Accessible
    private int nbNecStepsIncrHappiness;

    @Accessible
    private short nbMaxStepsSameEvoBase;

    @Accessible
    private short nbMaxSteps;

    @Accessible
    private StringList pokemonDefaultEggGroup = new StringList();

    @Accessible
    private Rate defaultMoney;

    @Accessible
    private StringList tm = new StringList();

    @Accessible
    private StringList hm = new StringList();

    @Accessible
    private StringList types = new StringList();

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        miniMap = data_.getMap().getImages(data_);
        begin = data_.getMap().getPlaces().getVal(data_.getMap().getBegin().getNumberPlace()).getName();
        firstPokemon = data_.getMap().getFirstPokemon();
        namesPlaces = new TreeMap<MiniMapCoords, String>(new ComparatorMiniMapCoords());
        for (MiniMapCoords m: miniMap.getKeys()) {
            namesPlaces.put(m, data_.getMap().getName(m.getXcoords(), m.getYcoords()));
        }
        unlockedCity = ConverterBufferedImage.surroundImage(data_.getMiniMap(data_.getMap().getUnlockedCity()));
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
            if (pkData_.getEggGroups().containsObj(data_.getDefaultEggGroup())) {
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


    @Accessible
    private String getMiniMapImage(Long _index) {
        String image_ = miniMap.getValue(_index.intValue());
        return ConverterBufferedImage.surroundImage(image_);
    }

    @Accessible
    private boolean isFirstRow(Long _index) {
        MiniMapCoords key_ = miniMap.getKey(_index.intValue());
        return key_.getXcoords() == CustList.FIRST_INDEX;
    }

    @Accessible
    private String getPlaceName(Long _index) {
        return namesPlaces.getValue(_index.intValue());
    }

    @Accessible
    private String getTrPokemon(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String pk_ = pokemonDefaultEggGroup.get(_index.intValue());
        return data_.translatePokemon(pk_);
    }

    @Accessible
    private String clickPokemon(Long _index) {
        String pk_ = pokemonDefaultEggGroup.get(_index.intValue());
        getForms().put(PK,pk_);
        return POKEMON;
    }

    @Accessible
    private String getImage() {
        DataBase data_ = (DataBase) getDataBase();
        String name_ = firstPokemon.getName();
        return ConverterBufferedImage.surroundImage(data_.getMaxiPkFront().getVal(name_));
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMaxiPkFront().getVal(name_));
    }

    @Accessible
    private String getName() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        String name_ = firstPokemon.getName();
        return translationsPokemon_.getVal(name_);
    }

    @Accessible
    private String clickName() {
        String name_ = firstPokemon.getName();
        getForms().put(PK, name_);
        return POKEMON;
    }

    @Accessible
    private short getLevel() {
        return firstPokemon.getLevel();
    }

    @Accessible
    private String getGender() {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        Gender gender_ = firstPokemon.getGender();
        return translationsGenders_.getVal(gender_);
    }

    @Accessible
    private String getAbility() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        String ability_ = firstPokemon.getAbility();
        return translationsAbilities_.getVal(ability_);
    }

    @Accessible
    private String clickAbility() {
        String ability_ = firstPokemon.getAbility();
        getForms().put(ABILITY, ability_);
        return ABILITY;
    }

    @Accessible
    private boolean firstPokemonHasItem() {
        return !firstPokemon.getItem().isEmpty();
    }

    @Accessible
    private String getItem() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        String item_ = firstPokemon.getItem();
        return translationsItems_.getVal(item_);
    }

    @Accessible
    private String clickItem() {
        DataBase data_ = (DataBase) getDataBase();
        String item_ = firstPokemon.getItem();
        getForms().put(ITEM, item_);
        Item it_ = data_.getItem(item_);
        if (it_ instanceof Ball) {
            return BALL;
        }
        if (it_ instanceof Berry) {
            return BERRY;
        }
        if (it_ instanceof Boost) {
            return BOOST;
        }
        if (it_ instanceof EvolvingItem) {
            return EVOLVINGITEM;
        }
        if (it_ instanceof EvolvingStone) {
            return EVOLVINGSTONE;
        }
        if (it_ instanceof Fossil) {
            return FOSSIL;
        }
        if (it_ instanceof HealingHpStatus) {
            return HEALINGHPSTATUS;
        }
        if (it_ instanceof HealingStatus) {
            return HEALINGSTATUS;
        }
        if (it_ instanceof HealingHp) {
            return HEALINGHP;
        }
        if (it_ instanceof HealingPp) {
            return HEALINGPP;
        }
        if (it_ instanceof HealingItem) {
            return HEALINGITEM;
        }
        if (it_ instanceof ItemForBattle) {
            return ITEMFORBATTLE;
        }
        if (it_ instanceof Repel) {
            return REPEL;
        }
        if (it_ instanceof SellingItem) {
            return SELLINGITEM;
        }
        return ITEM;
    }

    @Accessible
    private String getMove(Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = getMovesAtLevel().get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }

    @Accessible
    private String clickMove(Long _moveIndex) {
        String move_ = getMovesAtLevel().get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private StringList getMovesAtLevel() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = data_.getPokemon(firstPokemon.getName()).getMovesAtLevel(firstPokemon.getLevel(), data_.getNbMaxMoves());
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        return moves_;
    }

    @Accessible
    private String clickTm(Long _moveIndex) {
        String move_ = tm.get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private String clickHm(Long _moveIndex) {
        String move_ = hm.get(_moveIndex.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private String getTrTm(Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = tm.get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }

    @Accessible
    private String getTmPrice(Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        String move_ = tm.get(_moveIndex.intValue());
//        short key_ = data_.getTm().getKeys(move_).first();
        short key_ = data_.getTmByMove(move_).first();
        if (data_.getTmPrice().contains(key_)) {
            return data_.getTmPrice().getVal(key_).toNumberString();
        }
        return DataBase.EMPTY_STRING;
    }

    @Accessible
    private String getTrHm(Long _moveIndex) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        String move_ = hm.get(_moveIndex.intValue());
        return translationsMoves_.getVal(move_);
    }

    @Accessible
    private String getTrType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String type_ = types.get(_index.intValue());
        return translationsTypes_.getVal(type_);
    }

    @Accessible
    private String getImageType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String type_ = types.get(_index.intValue());
        return ConverterBufferedImage.surroundImage(data_.getTypesImages().getVal(type_));
    }

    @Accessible
    private String getColorType(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String type_ = types.get(_index.intValue());
        String color_ = data_.getTypesColors().getVal(type_);
        String img_ = ConverterBufferedImage.getSquareColorSixtyFour(color_, DataBase.SEPARATOR_RGB, data_.getMap().getSideLength());
        return ConverterBufferedImage.surroundImage(img_);
    }
}
