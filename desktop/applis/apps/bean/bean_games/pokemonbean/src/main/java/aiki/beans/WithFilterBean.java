package aiki.beans;

import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.pokemon.PokedexBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.maths.Rate;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class WithFilterBean extends CommonBean {
    private String typedAbility = DataBase.EMPTY_STRING;
    private String typedName = DataBase.EMPTY_STRING;
    private String typedPrice = DataBase.EMPTY_STRING;

    private String typedClass = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private String typedStatus = DataBase.EMPTY_STRING;
    private String typedCategory = DataBase.EMPTY_STRING;
    private boolean wholeWord;
    private String hasEvo = SelectedBoolean.YES_AND_NO.getBoolName();
    private String isEvo = SelectedBoolean.YES_AND_NO.getBoolName();
    private String isLeg = SelectedBoolean.YES_AND_NO.getBoolName();
    private String learnt = SelectedBoolean.YES_AND_NO.getBoolName();
    private String typedMinNbPossEvos = DataBase.EMPTY_STRING;
    private String typedMaxNbPossEvos = DataBase.EMPTY_STRING;
    private String minPower = DataBase.EMPTY_STRING;
    private String maxPower = DataBase.EMPTY_STRING;
    private String minAccuracy = DataBase.EMPTY_STRING;
    private String maxAccuracy = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> booleans;
    private AbsMap<String,AbilityData> sortedAbilities = new StringMap<AbilityData>();
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private final CustList<ItemLine> items = new CustList<ItemLine>();

    public static AbsMap<String,Item> sortedItems(DataBase _data, String _typedPrice, String _typedName, String _typedClass, String _language) {
        AbsMap<String,Item> sortedItems_ = DictionaryComparatorUtil.buildItemsData(_data,_language);
        StringMap<String> translationsItems_;
        translationsItems_ = _data.getTranslatedItems().getVal(_language);
        StringMap<String> translationsClasses_;
        translationsClasses_ = _data.getTranslatedClassesDescriptions().getVal(_language);
        if (_typedPrice.isEmpty()) {
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(i.getKey(),i.getValue());
                }
            }
        } else {
            long int_ = NumberUtil.parseLongZero(_typedPrice);
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && i.getValue().getPrice() == int_ && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(i.getKey(),i.getValue());
                }
            }
        }
//        sortedItems_.sortElts(DictionaryComparatorUtil.cmpItems(_data, _language));
        return sortedItems_;
    }

    protected void bools() {
        DataBase data_ = getDataBase();
        AbsMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        setBooleans(DictionaryComparatorUtil.buildBoolStr(data_, getLanguage()));
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            getBooleans().put(s.getBoolName(), translatedBooleans_.getVal(s));
        }
    }

    protected void setupPokedex(AbsMap<String,PokemonData> _pokedex) {
        DataBase data_ = getDataBase();
        getPokedex().clear();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (EntryCust<String, PokemonData> k: _pokedex.entryList()) {
            PokemonData pkData_ = k.getValue();
            PokemonLine line_ = new PokemonLine();
            line_.setName(k.getKey());
            line_.setDisplayName(translationsPk_.getVal(k.getKey()));
            StringList types_ = new StringList();
            for (String t: pkData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setEvolutions(pkData_.getEvolutions().size());
            getPokedex().add(line_);
        }
        escapeInputs();
    }
    protected void escapeInputs() {
        setTypedName(escapedStringQuote(getTypedName()));
        setTypedType(escapedStringQuote(getTypedType()));
        setTypedMinNbPossEvos(escapedStringQuote(getTypedMinNbPossEvos()));
        setTypedMaxNbPossEvos(escapedStringQuote(getTypedMaxNbPossEvos()));
        setTypedAbility(escapedStringQuote(getTypedAbility()));
        setTypedPrice(escapedStringQuote(getTypedPrice()));
        setTypedClass(escapedStringQuote(getTypedClass()));
        minPower = escapedStringQuote(minPower);
        maxPower = escapedStringQuote(maxPower);
        minAccuracy = escapedStringQuote(minAccuracy);
        maxAccuracy = escapedStringQuote(maxAccuracy);
        setTypedStatus(escapedStringQuote(getTypedStatus()));
    }

    protected String search(String _pk, String _mono, String _multi) {
        return search(CST_POKEMON_SET, _pk, _mono, _multi);
    }

    protected String search(String _k, String _pk, String _mono, String _multi) {
        AbsMap<String,PokemonData> pokedex_ = pokedex();
        getForms().putPokedex(_k, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_pk,pokedex_.firstKey());
            return _mono;
        }
        return _multi;
    }

    protected AbsMap<String,PokemonData> pokedex() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        DictionaryComparator<String, PokemonData> pokedex_ = DictionaryComparatorUtil.buildPkData(data_,getLanguage());
        for (EntryCust<String, PokemonData> k: data_.getPokedex().entryList()) {
            String displayName_ = translationsPk_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_,pkData_.getTypes()) && (getTypedMinNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() >= NumberUtil.parseLongZero(getTypedMinNbPossEvos())) && (getTypedMaxNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() <= NumberUtil.parseLongZero(getTypedMaxNbPossEvos())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getHasEvo()), !pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsEvo()), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsLeg()), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.put(k.getKey(),k.getValue());
            }
        }
//        pokedex_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        return pokedex_;
    }
    protected AbsMap<String,MoveData> movesAmong(StringMap<MoveData> _m) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        DictionaryComparator<String,MoveData> moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = DictionaryComparatorUtil.buildMovesData(data_,getLanguage());
        AbsMap<String,MoveData> learntMoves_ = getForms().getValMoveData(CST_LEARNT_MOVES);
        CustList<String> list_ = learntMoves_.getKeys();
        for (EntryCust<String, MoveData> k: _m.entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (CriteriaForSearching.match(PokemonStandards.getBoolByName(getLearnt()), StringUtil.contains(list_, k.getKey()))&&atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), getDataBase().getCategory(moveData_))) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.put(k.getKey(),k.getValue());
            }
        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }

    private boolean excludeByAccuracy(MoveData _move) {
        if (Rate.isValid(getMinAccuracy())) {
            String accuraryStr_ = _move.getAccuracy();
            if (!Rate.isValid(accuraryStr_) || !Rate.greaterEq(new Rate(accuraryStr_), new Rate(getMinAccuracy()))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxAccuracy())) {
            String accuraryStr_ = _move.getAccuracy();
            return Rate.isValid(accuraryStr_) && !Rate.lowerEq(new Rate(accuraryStr_), new Rate(getMaxAccuracy()));
        }
        return false;
    }
    private boolean excludeByPower(MoveData _move) {
        if (Rate.isValid(getMinPower())) {
            if (!(_move instanceof DamagingMoveData)) {
                return true;
            }
            Rate power_ = new Rate(getMinPower());
            String p_ = power(_move);
            if (!power_.isZeroOrLt() && (!Rate.isValid(p_) || !Rate.greaterEq(new Rate(p_), power_))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxPower()) && _move instanceof DamagingMoveData) {
            Rate power_ = new Rate(getMaxPower());
            String p_ = power(_move);
            return Rate.isValid(p_) && !Rate.lowerEq(new Rate(p_), power_);
        }
        return false;
    }
    public static boolean direct(MoveData _move) {
        return _move instanceof DamagingMoveData && ((DamagingMoveData)_move).isDirect();
    }

    protected static String power(MoveData _move) {
        int pr_ = _move.indexOfPrimaryEffect();
        if (pr_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return "";
        }
        Effect eff_ = _move.getEffet(pr_);
        if (!(eff_ instanceof EffectDamage)) {
            return "";
        }
        return ((EffectDamage)eff_).getPower();
    }

    protected String searchAbility(String _k, String _mono, String _multi) {
        AbsMap<String,AbilityData> sortedAbilities_ = sortedAbilities();
        getForms().putAbilities(CST_ABILITIES_SET, sortedAbilities_);
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_k, sortedAbilities_.firstKey());
            return _mono;
        }
        return _multi;
    }

    protected AbsMap<String,AbilityData> sortedAbilities() {
        DictionaryComparator<String,AbilityData> sortedAbilities_;
        DataBase data_ = getDataBase();
        sortedAbilities_ = DictionaryComparatorUtil.buildAbilitiesData(data_,getLanguage());
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (EntryCust<String, AbilityData> i: data_.getAbilities().entryList()) {
            String ab_ = translationsAbilities_.getVal(i.getKey());
            if (StringUtil.match(ab_, getTypedAbility())) {
                sortedAbilities_.put(i.getKey(),i.getValue());
            }
        }
//        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return sortedAbilities_;
    }

    protected boolean atLeastMatchType(StringMap<String> _translationsTypes, StringList _types) {
        return PokedexBean.atLeastMatchType(_translationsTypes,getWholeWord(),getTypedType(),_types);
    }

    protected void itemInit(AbsMap<String,Item> _sortedItems) {
        DataBase data_ = getDataBase();
        getItems().clear();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        for (EntryCust<String, Item> i: _sortedItems.entryList()) {
            Item i_ = i.getValue();
//            String class_ = translationsClasses_.getVal(i_.getClass().getName());
            String class_ = translationsClasses_.getVal(i_.getItemType());
//            class_ = XmlParser.transformSpecialChars(class_);
            ItemLine item_ = new ItemLine();
            item_.setName(i.getKey());
            item_.setDisplayName(translationsItems_.getVal(i.getKey()));
            item_.setPrice(i_.getPrice());
            item_.setDescriptionClass(class_);
            getItems().add(item_);
        }
        escapeInputs();
    }

    protected AbsMap<String,Item> sortedItems(DataBase _data) {
        return sortedItems(_data,getTypedPrice(),getTypedName(),getTypedClass(),getLanguage());
    }
    public String getTrSortedAbility(int _index) {
        String ability_ = sortedAbilities.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(ability_);
    }

    public int[][] getMiniImagePk(int _number) {
        String name_ = getPokedex().get(_number).getName();
        DataBase data_ = getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return data_.getMiniPk(name_);
        //return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
    }
    public void setTypedAbility(String _typedAbility) {
        typedAbility = _typedAbility;
    }

    public String getTypedAbility() {
        return typedAbility;
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public void setTypedPrice(String _typedPrice) {
        typedPrice = _typedPrice;
    }

    public String getTypedPrice() {
        return typedPrice;
    }

    public void setTypedClass(String _typedClass) {
        typedClass = _typedClass;
    }

    public String getTypedClass() {
        return typedClass;
    }

    public void setTypedType(String _typedType) {
        typedType = _typedType;
    }

    public String getTypedType() {
        return typedType;
    }

    public void setTypedStatus(String _typedStatus) {
        typedStatus = _typedStatus;
    }

    public String getTypedStatus() {
        return typedStatus;
    }

    public void setWholeWord(boolean _wholeWord) {
        wholeWord = _wholeWord;
    }

    public boolean getWholeWord() {
        return wholeWord;
    }

    public void setTypedMinNbPossEvos(String _typedMinNbPossEvos) {
        typedMinNbPossEvos = _typedMinNbPossEvos;
    }

    public String getTypedMinNbPossEvos() {
        return typedMinNbPossEvos;
    }

    public void setTypedMaxNbPossEvos(String _typedMaxNbPossEvos) {
        typedMaxNbPossEvos = _typedMaxNbPossEvos;
    }

    public String getTypedMaxNbPossEvos() {
        return typedMaxNbPossEvos;
    }

    public DictionaryComparator<String,String> getBooleans() {
        return booleans;
    }

    public void setBooleans(DictionaryComparator<String, String> _b) {
        this.booleans = _b;
    }

    public String getHasEvo() {
        return hasEvo;
    }

    public void setHasEvo(String _hasEvo) {
        hasEvo = _hasEvo;
    }

    public String getLearnt() {
        return learnt;
    }

    public void setLearnt(String _l) {
        this.learnt = _l;
    }

    public String getIsEvo() {
        return isEvo;
    }

    public void setIsEvo(String _isEvo) {
        isEvo = _isEvo;
    }

    public String getIsLeg() {
        return isLeg;
    }

    public void setIsLeg(String _isLeg) {
        isLeg = _isLeg;
    }

    public void setMinAccuracy(String _minAccuracy) {
        minAccuracy = _minAccuracy;
    }

    public String getMinAccuracy() {
        return minAccuracy;
    }

    public void setMaxAccuracy(String _maxAccuracy) {
        maxAccuracy = _maxAccuracy;
    }

    public String getMaxAccuracy() {
        return maxAccuracy;
    }

    public void setMinPower(String _minPower) {
        minPower = _minPower;
    }

    public String getMinPower() {
        return minPower;
    }

    public void setMaxPower(String _maxPower) {
        maxPower = _maxPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public CustList<String> getSortedAbilities() {
        return sortedAbilities.getKeys();
    }

    public void setSortedAbilities(AbsMap<String,AbilityData> _ab) {
        this.sortedAbilities = _ab;
    }

    public String getTypedCategory() {
        return typedCategory;
    }

    public void setTypedCategory(String _c) {
        this.typedCategory = _c;
    }
    public CustList<PokemonLine> getPokedex() {
        return pokedex;
    }

    public CustList<ItemLine> getItems() {
        return items;
    }

}
