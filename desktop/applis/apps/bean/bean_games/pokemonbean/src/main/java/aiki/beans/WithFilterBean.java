package aiki.beans;

import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.pokemon.PokedexBean;
import aiki.beans.simulation.SelectItemBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.items.Item;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.*;
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
    private String typedMinNbPossEvos = DataBase.EMPTY_STRING;
    private String typedMaxNbPossEvos = DataBase.EMPTY_STRING;
    private String minPower = DataBase.EMPTY_STRING;
    private String maxPower = DataBase.EMPTY_STRING;
    private String minAccuracy = DataBase.EMPTY_STRING;
    private String maxAccuracy = DataBase.EMPTY_STRING;
    private DictionaryComparator<String,String> booleans;
    private StringList sortedAbilities = new StringList();
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private final CustList<ItemLine> items = new CustList<ItemLine>();

    protected void bools() {
        DataBase data_ = getDataBase();
        AbsMap<SelectedBoolean,String> translatedBooleans_;
        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
        setBooleans(DictionaryComparatorUtil.buildBoolStr(data_, getLanguage()));
        for (SelectedBoolean s: translatedBooleans_.getKeys()) {
            getBooleans().put(s.getBoolName(), translatedBooleans_.getVal(s));
        }
    }

    protected void setupPokedex(StringList _pokedex) {
        DataBase data_ = getDataBase();
        getPokedex().clear();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (String k: _pokedex) {
            PokemonData pkData_ = data_.getPokedex().getVal(k);
            PokemonLine line_ = new PokemonLine();
            line_.setName(k);
            line_.setDisplayName(translationsPk_.getVal(k));
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

    protected String search(String _pk) {
        return search(CST_POKEMON_SET, _pk);
    }

    protected String search(String _k, String _pk) {
        StringList pokedex_ = pokedex();
        getForms().put(_k, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_pk,pokedex_.first());
            return CST_POKEMON;
        }
        return CST_POKEMON_SET;
    }

    protected StringList pokedex() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList pokedex_ = new StringList();
        for (EntryCust<String, PokemonData> k: data_.getPokedex().entryList()) {
            String displayName_ = translationsPk_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_,pkData_.getTypes()) && (getTypedMinNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() >= NumberUtil.parseLongZero(getTypedMinNbPossEvos())) && (getTypedMaxNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() <= NumberUtil.parseLongZero(getTypedMaxNbPossEvos())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getHasEvo()), pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsEvo()), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsLeg()), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.add(k.getKey());
            }
        }
        pokedex_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        return pokedex_;
    }
    protected StringList movesAmong(StringMap<MoveData> _m) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        for (EntryCust<String, MoveData> k: _m.entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), moveData_.getCategory())) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.add(k.getKey());
            }
        }
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(getMinPower());
            if (!power_.isZeroOrLt() && (!Rate.isValid(eff_.getPower()) || !Rate.greaterEq(new Rate(eff_.getPower()), power_))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxPower()) && _move instanceof DamagingMoveData) {
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(getMaxPower());
            return Rate.isValid(eff_.getPower()) && !Rate.lowerEq(new Rate(eff_.getPower()), power_);
        }
        return false;
    }

    protected String searchAbility(String _k) {
        StringList sortedAbilities_ = sortedAbilities();
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_k, sortedAbilities_.first());
            return CST_ABILITY;
        }
        getForms().put(CST_ABILITIES_SET, sortedAbilities_);
        return CST_ABILITIES;
    }

    protected StringList sortedAbilities() {
        StringList sortedAbilities_;
        sortedAbilities_ = new StringList();
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (String i: data_.getAbilities().getKeys()) {
            String ab_ = translationsAbilities_.getVal(i);
            if (StringUtil.match(ab_, getTypedAbility())) {
                sortedAbilities_.add(i);
            }
        }
        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return sortedAbilities_;
    }

    protected boolean atLeastMatchType(StringMap<String> _translationsTypes, StringList _types) {
        return PokedexBean.atLeastMatchType(_translationsTypes,getWholeWord(),getTypedType(),_types);
    }

    protected void itemInit(StringList _sortedItems) {
        DataBase data_ = getDataBase();
        getItems().clear();
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        for (String i: _sortedItems) {
            Item i_ = data_.getItem(i);
//            String class_ = translationsClasses_.getVal(i_.getClass().getName());
            String class_ = translationsClasses_.getVal(i_.getItemType());
//            class_ = XmlParser.transformSpecialChars(class_);
            ItemLine item_ = new ItemLine();
            item_.setName(i);
            item_.setDisplayName(translationsItems_.getVal(i));
            item_.setPrice(i_.getPrice());
            item_.setDescriptionClass(class_);
            getItems().add(item_);
        }
        escapeInputs();
    }

    protected StringList sortedItems(DataBase _data) {
        return SelectItemBean.sortedItems(_data,getTypedPrice(),getTypedName(),getTypedClass(),getLanguage());
    }
    public String getTrSortedAbility(int _index) {
        String ability_ = sortedAbilities.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translationsAbilities_.getVal(ability_);
    }

    public String getMiniImagePk(int _number) {
        String name_ = getPokedex().get(_number).getName();
        DataBase data_ = getDataBase();
//        return ConverterBufferedImage.toBaseSixtyFour(data_.getMiniPk().getVal(name_));
        return BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(name_));
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

    public StringList getSortedAbilities() {
        return sortedAbilities;
    }

    public void setSortedAbilities(StringList _ab) {
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
