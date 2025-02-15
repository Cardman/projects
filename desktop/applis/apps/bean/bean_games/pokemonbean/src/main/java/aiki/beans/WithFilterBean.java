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
    private IntBeanChgString typedNameForm;
    private String typedPrice = DataBase.EMPTY_STRING;

    private String typedClass = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private IntBeanChgString typedTypeForm;
    private String typedStatus = DataBase.EMPTY_STRING;
    private String typedCategory = DataBase.EMPTY_STRING;
    private IntBeanChgString typedCategoryForm;
    private boolean wholeWord;
    private IntBeanChgBool wholeWordForm;
    private String hasEvo = SelectedBoolean.YES_AND_NO.getBoolName();
    private IntBeanChgString hasEvoForm;
    private String isEvo = SelectedBoolean.YES_AND_NO.getBoolName();
    private IntBeanChgString isEvoForm;
    private String isLeg = SelectedBoolean.YES_AND_NO.getBoolName();
    private IntBeanChgString isLegForm;
    private String learnt = SelectedBoolean.YES_AND_NO.getBoolName();
    private IntBeanChgString learntForm;
    private String typedMinNbPossEvos = DataBase.EMPTY_STRING;
    private IntBeanChgString typedMinNbPossEvosForm;
    private String typedMaxNbPossEvos = DataBase.EMPTY_STRING;
    private IntBeanChgString typedMaxNbPossEvosForm;
    private String minPower = DataBase.EMPTY_STRING;
    private IntBeanChgString minPowerForm;
    private String maxPower = DataBase.EMPTY_STRING;
    private IntBeanChgString maxPowerForm;
    private String minAccuracy = DataBase.EMPTY_STRING;
    private IntBeanChgString minAccuracyForm;
    private String maxAccuracy = DataBase.EMPTY_STRING;
    private IntBeanChgString maxAccuracyForm;
    private DictionaryComparator<String,String> booleans;
    private AbsMap<TranslatedKey,AbilityData> sortedAbilities = DictionaryComparatorUtil.buildAbilitiesData();
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private final CustList<ItemLine> items = new CustList<ItemLine>();
    private final CustList<TranslatedKey> itemsTr = new CustList<TranslatedKey>();

    public static AbsMap<TranslatedKey,Item> sortedItems(DataBase _data, String _typedPrice, String _typedName, String _typedClass, String _language) {
        DictionaryComparator<TranslatedKey, Item> sortedItems_ = DictionaryComparatorUtil.buildItemsData();
        StringMap<String> translationsItems_;
        translationsItems_ = _data.getTranslatedItems().getVal(_language);
        StringMap<String> translationsClasses_;
        translationsClasses_ = _data.getTranslatedClassesDescriptions().getVal(_language);
        if (_typedPrice.isEmpty()) {
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(buildIt(_data,translationsItems_,i.getKey()),i.getValue());
                }
            }
        } else {
            long int_ = NumberUtil.parseLongZero(_typedPrice);
            for (EntryCust<String, Item> i: _data.getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName) && i.getValue().getPrice() == int_ && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(buildIt(_data,translationsItems_,i.getKey()),i.getValue());
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

    protected void setupPokedex(AbsMap<TranslatedKey,PokemonData> _pokedex) {
        DataBase data_ = getDataBase();
        getPokedex().clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        for (EntryCust<TranslatedKey, PokemonData> k: _pokedex.entryList()) {
            PokemonData pkData_ = k.getValue();
            PokemonLine line_ = new PokemonLine();
            line_.setKey(k.getKey());
            line_.setDisplayName(k.getKey().getTranslation());
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
        AbsMap<TranslatedKey,PokemonData> pokedex_ = pokedex();
        getForms().putPokedex(_k, pokedex_);
        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_pk,pokedex_.firstKey().getKey());
            return _mono;
        }
        return _multi;
    }

    protected AbsMap<TranslatedKey,PokemonData> pokedex() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPk_;
        translationsPk_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        DictionaryComparator<TranslatedKey, PokemonData> pokedex_ = DictionaryComparatorUtil.buildPkData();
        for (EntryCust<String, PokemonData> k: data_.getPokedex().entryList()) {
            String displayName_ = translationsPk_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_,pkData_.getTypes()) && (getTypedMinNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() >= NumberUtil.parseLongZero(getTypedMinNbPossEvos())) && (getTypedMaxNbPossEvos().isEmpty() || pkData_.getDirectEvolutions().size() <= NumberUtil.parseLongZero(getTypedMaxNbPossEvos())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getHasEvo()), !pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsEvo()), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsLeg()), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.put(buildPk(translationsPk_,k.getKey()),k.getValue());
            }
        }
//        pokedex_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        return pokedex_;
    }
    protected AbsMap<TranslatedKey,MoveData> movesAmong(StringMap<MoveData> _m) {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        DictionaryComparator<TranslatedKey,MoveData> moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = DictionaryComparatorUtil.buildMovesData();
        AbsMap<TranslatedKey, MoveData> learntMoves_ = getForms().getValMoveData(CST_LEARNT_MOVES);
        CustList<String> list_ = keys(learntMoves_.getKeys());
        for (EntryCust<String, MoveData> k: _m.entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName())) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (CriteriaForSearching.match(PokemonStandards.getBoolByName(getLearnt()), StringUtil.contains(list_, k.getKey()))&&atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), getDataBase().getCategory(moveData_))) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.put(buildMv(translationsMoves_,k.getKey()),k.getValue());
            }
        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        return moves_;
    }

    public static StringList keys(CustList<TranslatedKey> _keys) {
        StringList o_ = new StringList();
        for (TranslatedKey e:_keys) {
            o_.add(e.getKey());
        }
        return o_;
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
        AbsMap<TranslatedKey,AbilityData> sortedAbilities_ = sortedAbilities();
        getForms().putAbilities(CST_ABILITIES_SET, sortedAbilities_);
        if (sortedAbilities_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(_k, sortedAbilities_.firstKey().getKey());
            return _mono;
        }
        return _multi;
    }

    protected AbsMap<TranslatedKey,AbilityData> sortedAbilities() {
        DictionaryComparator<TranslatedKey,AbilityData> sortedAbilities_;
        DataBase data_ = getDataBase();
        sortedAbilities_ = DictionaryComparatorUtil.buildAbilitiesData();
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        for (EntryCust<String, AbilityData> i: data_.getAbilities().entryList()) {
            String ab_ = translationsAbilities_.getVal(i.getKey());
            if (StringUtil.match(ab_, getTypedAbility())) {
                sortedAbilities_.put(buildAb(translationsAbilities_,i.getKey()),i.getValue());
            }
        }
//        sortedAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        return sortedAbilities_;
    }

    protected boolean atLeastMatchType(StringMap<String> _translationsTypes, StringList _types) {
        return PokedexBean.atLeastMatchType(_translationsTypes,getWholeWord(),getTypedType(),_types);
    }

    protected void itemInit(AbsMap<TranslatedKey,Item> _sortedItems) {
        DataBase data_ = getDataBase();
        getItems().clear();
        getItemsTr().clear();
        StringMap<String> translationsClasses_;
        translationsClasses_ = data_.getTranslatedClassesDescriptions().getVal(getLanguage());
        for (EntryCust<TranslatedKey, Item> i: _sortedItems.entryList()) {
            Item i_ = i.getValue();
//            String class_ = translationsClasses_.getVal(i_.getClass().getName());
            String class_ = translationsClasses_.getVal(i_.getItemType());
//            class_ = XmlParser.transformSpecialChars(class_);
            ItemLine item_ = new ItemLine();
            item_.setName(i.getKey().getKey());
            item_.setDisplayName(i.getKey().getTranslation());
            item_.setPrice(i_.getPrice());
            item_.setDescriptionClass(class_);
            getItems().add(item_);
            getItemsTr().add(i.getKey());
        }
        escapeInputs();
    }

    protected AbsMap<TranslatedKey,Item> sortedItems(DataBase _data) {
        return sortedItems(_data,getTypedPrice(),getTypedName(),getTypedClass(),getLanguage());
    }
    public String getTrSortedAbility(int _index) {
        return sortedAbilities.getKey(_index).getTranslation();
//        String ability_ = sortedAbilities.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translationsAbilities_;
//        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translationsAbilities_.getVal(ability_);
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

    public IntBeanChgString getTypedNameForm() {
        return typedNameForm;
    }

    public void setTypedNameForm(IntBeanChgString _t) {
        this.typedNameForm = _t;
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

    public IntBeanChgString getTypedTypeForm() {
        return typedTypeForm;
    }

    public void setTypedTypeForm(IntBeanChgString _t) {
        this.typedTypeForm = _t;
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

    public IntBeanChgBool getWholeWordForm() {
        return wholeWordForm;
    }

    public void setWholeWordForm(IntBeanChgBool _w) {
        this.wholeWordForm = _w;
    }

    public void setTypedMinNbPossEvos(String _typedMinNbPossEvos) {
        typedMinNbPossEvos = _typedMinNbPossEvos;
    }

    public String getTypedMinNbPossEvos() {
        return typedMinNbPossEvos;
    }

    public IntBeanChgString getTypedMinNbPossEvosForm() {
        return typedMinNbPossEvosForm;
    }

    public void setTypedMinNbPossEvosForm(IntBeanChgString _t) {
        this.typedMinNbPossEvosForm = _t;
    }

    public void setTypedMaxNbPossEvos(String _typedMaxNbPossEvos) {
        typedMaxNbPossEvos = _typedMaxNbPossEvos;
    }

    public String getTypedMaxNbPossEvos() {
        return typedMaxNbPossEvos;
    }

    public IntBeanChgString getTypedMaxNbPossEvosForm() {
        return typedMaxNbPossEvosForm;
    }

    public void setTypedMaxNbPossEvosForm(IntBeanChgString _t) {
        this.typedMaxNbPossEvosForm = _t;
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

    public IntBeanChgString getHasEvoForm() {
        return hasEvoForm;
    }

    public void setHasEvoForm(IntBeanChgString _h) {
        this.hasEvoForm = _h;
    }

    public String getLearnt() {
        return learnt;
    }

    public void setLearnt(String _l) {
        this.learnt = _l;
    }

    public IntBeanChgString getLearntForm() {
        return learntForm;
    }

    public void setLearntForm(IntBeanChgString _l) {
        this.learntForm = _l;
    }

    public String getIsEvo() {
        return isEvo;
    }

    public void setIsEvo(String _isEvo) {
        isEvo = _isEvo;
    }

    public IntBeanChgString getIsLegForm() {
        return isLegForm;
    }

    public void setIsLegForm(IntBeanChgString _i) {
        this.isLegForm = _i;
    }

    public String getIsLeg() {
        return isLeg;
    }

    public void setIsLeg(String _isLeg) {
        isLeg = _isLeg;
    }

    public IntBeanChgString getIsEvoForm() {
        return isEvoForm;
    }

    public void setIsEvoForm(IntBeanChgString _i) {
        this.isEvoForm = _i;
    }

    public void setMinAccuracy(String _minAccuracy) {
        minAccuracy = _minAccuracy;
    }

    public String getMinAccuracy() {
        return minAccuracy;
    }

    public IntBeanChgString getMinAccuracyForm() {
        return minAccuracyForm;
    }

    public void setMinAccuracyForm(IntBeanChgString _m) {
        this.minAccuracyForm = _m;
    }

    public void setMaxAccuracy(String _maxAccuracy) {
        maxAccuracy = _maxAccuracy;
    }

    public String getMaxAccuracy() {
        return maxAccuracy;
    }

    public IntBeanChgString getMaxAccuracyForm() {
        return maxAccuracyForm;
    }

    public void setMaxAccuracyForm(IntBeanChgString _m) {
        this.maxAccuracyForm = _m;
    }

    public void setMinPower(String _minPower) {
        minPower = _minPower;
    }

    public String getMinPower() {
        return minPower;
    }

    public IntBeanChgString getMinPowerForm() {
        return minPowerForm;
    }

    public void setMinPowerForm(IntBeanChgString _m) {
        this.minPowerForm = _m;
    }

    public void setMaxPower(String _maxPower) {
        maxPower = _maxPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public IntBeanChgString getMaxPowerForm() {
        return maxPowerForm;
    }

    public void setMaxPowerForm(IntBeanChgString _m) {
        this.maxPowerForm = _m;
    }

    public CustList<TranslatedKey> sortedAbilitiesGet() {
        return sortedAbilities.getKeys();
    }

    public void setSortedAbilities(AbsMap<TranslatedKey,AbilityData> _ab) {
        this.sortedAbilities = _ab;
    }

    public String getTypedCategory() {
        return typedCategory;
    }

    public void setTypedCategory(String _c) {
        this.typedCategory = _c;
    }

    public IntBeanChgString getTypedCategoryForm() {
        return typedCategoryForm;
    }

    public void setTypedCategoryForm(IntBeanChgString _t) {
        this.typedCategoryForm = _t;
    }

    public CustList<PokemonLine> getPokedex() {
        return pokedex;
    }

    public CustList<ItemLine> getItems() {
        return items;
    }

    public CustList<TranslatedKey> getItemsTr() {
        return itemsTr;
    }
}
