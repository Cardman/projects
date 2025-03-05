package aiki.beans;

import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.game.DifficultyBeanForm;
import aiki.beans.pokemon.PokedexBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.CriteriaForSearching;
import aiki.facade.FacadeGame;
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
import code.scripts.pages.aiki.MessagesDataItems;
import code.scripts.pages.aiki.MessagesDataPokemonPokedex;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class WithFilterBean extends CommonBean implements BeanRenderWithAppName {
    private IntBeanChgString typedAbility = new BeanChgString();
    private IntBeanChgString typedName = new BeanChgString();
    private IntBeanChgString typedPrice = new BeanChgString();

    private IntBeanChgString typedClass = new BeanChgString();
    private IntBeanChgString typedType = new BeanChgString();
    private IntBeanChgString typedStatus = new BeanChgString();
    private IntBeanChgString typedCategory = new BeanChgString();
    private IntBeanChgBool wholeWord = new BeanChgBool();
    private IntBeanChgString hasEvo = new BeanChgString();
    private IntBeanChgString isEvo = new BeanChgString();
    private IntBeanChgString isLeg = new BeanChgString();
    private IntBeanChgString learnt = new BeanChgString();
    private IntBeanChgString typedMinNbPossEvos = new BeanChgString();
    private IntBeanChgString typedMaxNbPossEvos = new BeanChgString();
    private IntBeanChgString minPower = new BeanChgString();
    private IntBeanChgString maxPower = new BeanChgString();
    private IntBeanChgString minAccuracy = new BeanChgString();
    private IntBeanChgString maxAccuracy = new BeanChgString();
    private DictionaryComparator<String,String> booleans;
    private AbsMap<TranslatedKey,AbilityData> sortedAbilities = DictionaryComparatorUtil.buildAbilitiesData();
    private final CustList<PokemonLine> pokedex = new CustList<PokemonLine>();
    private final CustList<ItemLine> items = new CustList<ItemLine>();
    private final CustList<TranslatedKey> itemsTr = new CustList<TranslatedKey>();
    protected WithFilterBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
        hasEvo.setupValue(SelectedBoolean.YES_AND_NO.getBoolName());
        isEvo.setupValue(SelectedBoolean.YES_AND_NO.getBoolName());
        isLeg.setupValue(SelectedBoolean.YES_AND_NO.getBoolName());
        learnt.setupValue(SelectedBoolean.YES_AND_NO.getBoolName());
    }
    protected void initFormIt() {
        initLine();
        formatMessage(MessagesPkBean.ITEMS, MessagesDataItems.M_P_29_CONTENT_NAME);
        setTypedName(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedName().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_PRICE_DOT);
        setTypedPrice(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedPrice().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.ITEMS,MessagesDataItems.M_P_29_CONTENT);
        setTypedClass(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedClass().tryRet()));
        feedParents();
    }

    public static AbsMap<TranslatedKey,Item> sortedItems(FacadeGame _data, String _typedPrice, IntBeanChgString _typedName, String _typedClass) {
        DictionaryComparator<TranslatedKey, Item> sortedItems_ = DictionaryComparatorUtil.buildItemsData();
        StringMap<String> translationsItems_;
        translationsItems_ = _data.getTranslatedItems();
        StringMap<String> translationsClasses_;
        translationsClasses_ = _data.getTranslatedClassesDescriptions();
        if (_typedPrice.isEmpty()) {
            for (EntryCust<String, Item> i: _data.getData().getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName.tryRet()) && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(buildIt(_data, i.getKey()),i.getValue());
                }
            }
        } else {
            long int_ = NumberUtil.parseLongZero(_typedPrice);
            for (EntryCust<String, Item> i: _data.getData().getItems().entryList()) {
                String display_ = translationsItems_.getVal(i.getKey());
                //                String class_ = translationsClasses_.getVal(i_.getClass().getName());
                if (StringUtil.match(display_, _typedName.tryRet()) && i.getValue().getPrice() == int_ && StringUtil.match(translationsClasses_.getVal(i.getValue().getItemType()), _typedClass)) {
                    sortedItems_.put(buildIt(_data, i.getKey()),i.getValue());
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

    protected void initFormPk() {
        initLine();
        formatMessage(MessagesPkBean.POKEDEX, MessagesDataPokemonPokedex.M_P_82_CONTENT_NAME);
        setTypedName(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedName().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_CONTENT_TYPE);
        setTypedType(DifficultyBeanForm.txt(getBuilder().getGenInput(),this,getTypedType().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_CONTENT_TYPE_WHOLE);
        setWholeWord(DifficultyBeanForm.check(getBuilder().getGenInput(), this,getWholeWord().isSelected()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_NB_EVOS);
        setTypedMinNbPossEvos(DifficultyBeanForm.txt(getBuilder().getGenInput(), this,getTypedMinNbPossEvos().tryRet()));
        setTypedMaxNbPossEvos(DifficultyBeanForm.txt(getBuilder().getGenInput(), this,getTypedMaxNbPossEvos().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_HAS_EVO);
        setHasEvo(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getHasEvo().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_IS_EVO);
        setIsEvo(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getIsEvo().tryRet()));
        feedParents();
        initLine();
        formatMessage(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_LEG);
        setIsLeg(DifficultyBeanForm.select(getBuilder().getGenInput(), this,getBooleans(),getIsLeg().tryRet()));
        feedParents();
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
//        escapeInputs();
    }
//    protected void escapeInputs() {
//        getTypedName().setupValue(escapedStringQuote(getTypedName().tryRet()));
//        getTypedType().setupValue(escapedStringQuote(getTypedType().tryRet()));
//        getTypedMinNbPossEvos().setupValue(escapedStringQuote(getTypedMinNbPossEvos().tryRet()));
//        getTypedMaxNbPossEvos().setupValue(escapedStringQuote(getTypedMaxNbPossEvos().tryRet()));
//        setTypedAbility(escapedStringQuote(getTypedAbility()));
//        setTypedPrice(escapedStringQuote(getTypedPrice()));
//        setTypedClass(escapedStringQuote(getTypedClass()));
//        minPower = escapedStringQuote(minPower);
//        maxPower = escapedStringQuote(maxPower);
//        minAccuracy = escapedStringQuote(minAccuracy);
//        maxAccuracy = escapedStringQuote(maxAccuracy);
//        setTypedStatus(escapedStringQuote(getTypedStatus()));
//    }

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
            if (!StringUtil.match(displayName_, getTypedName().tryRet())) {
                continue;
            }
            PokemonData pkData_ = k.getValue();
            if (atLeastMatchType(translationsTypes_,pkData_.getTypes()) && (getTypedMinNbPossEvos().tryRet().isEmpty() || pkData_.getDirectEvolutions().size() >= NumberUtil.parseLongZero(getTypedMinNbPossEvos().tryRet())) && (getTypedMaxNbPossEvos().tryRet().isEmpty() || pkData_.getDirectEvolutions().size() <= NumberUtil.parseLongZero(getTypedMaxNbPossEvos().tryRet())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getHasEvo().tryRet()), !pkData_.getEvolutions().isEmpty()) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsEvo().tryRet()), !StringUtil.quickEq(k.getKey(), pkData_.getBaseEvo())) && CriteriaForSearching.match(PokemonStandards.getBoolByName(getIsLeg().tryRet()), pkData_.getGenderRep() == GenderRepartition.LEGENDARY)) {
                pokedex_.put(buildPk(getFacade(),k.getKey()),k.getValue());
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
        getForms().safeMoves(CST_LEARNT_MOVES);
        AbsMap<TranslatedKey, MoveData> learntMoves_ = getForms().getValMoveData(CST_LEARNT_MOVES);
        CustList<String> list_ = keys(learntMoves_.getKeys());
        for (EntryCust<String, MoveData> k: _m.entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, getTypedName().tryRet())) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (CriteriaForSearching.match(PokemonStandards.getBoolByName(getLearnt().tryRet()), StringUtil.contains(list_, k.getKey()))&&atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory().tryRet(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory().tryRet(), getDataBase().getCategory(moveData_))) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.put(buildMv(getFacade(),k.getKey()),k.getValue());
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

    public static StringList values(CustList<TranslatedKey> _keys) {
        StringList o_ = new StringList();
        for (TranslatedKey e:_keys) {
            o_.add(e.getTranslation());
        }
        return o_;
    }
    private boolean excludeByAccuracy(MoveData _move) {
        if (Rate.isValid(getMinAccuracy().tryRet())) {
            String accuraryStr_ = _move.getAccuracy();
            if (!Rate.isValid(accuraryStr_) || !Rate.greaterEq(new Rate(accuraryStr_), new Rate(getMinAccuracy().tryRet()))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxAccuracy().tryRet())) {
            String accuraryStr_ = _move.getAccuracy();
            return Rate.isValid(accuraryStr_) && !Rate.lowerEq(new Rate(accuraryStr_), new Rate(getMaxAccuracy().tryRet()));
        }
        return false;
    }
    private boolean excludeByPower(MoveData _move) {
        if (Rate.isValid(getMinPower().tryRet())) {
            if (!(_move instanceof DamagingMoveData)) {
                return true;
            }
            Rate power_ = new Rate(getMinPower().tryRet());
            String p_ = power(_move);
            if (!power_.isZeroOrLt() && (!Rate.isValid(p_) || !Rate.greaterEq(new Rate(p_), power_))) {
                return true;
            }
        }
        if (Rate.isValid(getMaxPower().tryRet()) && _move instanceof DamagingMoveData) {
            Rate power_ = new Rate(getMaxPower().tryRet());
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
            if (StringUtil.match(ab_, getTypedAbility().tryRet())) {
                sortedAbilities_.put(buildAb(getFacade(),i.getKey()),i.getValue());
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
            item_.setName(i.getKey());
            item_.setDisplayName(i.getKey().getTranslation());
            item_.setPrice(i_.getPrice());
            item_.setDescriptionClass(class_);
            getItems().add(item_);
            getItemsTr().add(i.getKey());
        }
//        escapeInputs();
    }

    protected AbsMap<TranslatedKey,Item> sortedItems() {
        return sortedItems(getFacade(),getTypedPrice().tryRet(),getTypedName(),getTypedClass().tryRet());
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
    public void setTypedAbility(IntBeanChgString _typedAbility) {
        typedAbility = _typedAbility;
    }

    public IntBeanChgString getTypedAbility() {
        return typedAbility;
    }

    public void setTypedName(IntBeanChgString _typedName) {
        typedName = _typedName;
    }

    public IntBeanChgString getTypedName() {
        return typedName;
    }

    public void setTypedPrice(IntBeanChgString _typedPrice) {
        typedPrice = _typedPrice;
    }

    public IntBeanChgString getTypedPrice() {
        return typedPrice;
    }

    public void setTypedClass(IntBeanChgString _typedClass) {
        typedClass = _typedClass;
    }

    public IntBeanChgString getTypedClass() {
        return typedClass;
    }

    public void setTypedType(IntBeanChgString _typedType) {
        typedType = _typedType;
    }

    public IntBeanChgString getTypedType() {
        return typedType;
    }

    public void setTypedStatus(IntBeanChgString _typedStatus) {
        typedStatus = _typedStatus;
    }

    public IntBeanChgString getTypedStatus() {
        return typedStatus;
    }

    public void setWholeWord(IntBeanChgBool _wholeWord) {
        wholeWord = _wholeWord;
    }

    public IntBeanChgBool getWholeWord() {
        return wholeWord;
    }

    public void setTypedMinNbPossEvos(IntBeanChgString _typedMinNbPossEvos) {
        typedMinNbPossEvos = _typedMinNbPossEvos;
    }

    public IntBeanChgString getTypedMinNbPossEvos() {
        return typedMinNbPossEvos;
    }

    public void setTypedMaxNbPossEvos(IntBeanChgString _typedMaxNbPossEvos) {
        typedMaxNbPossEvos = _typedMaxNbPossEvos;
    }

    public IntBeanChgString getTypedMaxNbPossEvos() {
        return typedMaxNbPossEvos;
    }

    public DictionaryComparator<String,String> getBooleans() {
        return booleans;
    }

    public void setBooleans(DictionaryComparator<String, String> _b) {
        this.booleans = _b;
    }

    public IntBeanChgString getHasEvo() {
        return hasEvo;
    }

    public void setHasEvo(IntBeanChgString _hasEvo) {
        hasEvo = _hasEvo;
    }

    public IntBeanChgString getLearnt() {
        return learnt;
    }

    public void setLearnt(IntBeanChgString _l) {
        this.learnt = _l;
    }

    public IntBeanChgString getIsEvo() {
        return isEvo;
    }

    public void setIsEvo(IntBeanChgString _isEvo) {
        isEvo = _isEvo;
    }

    public IntBeanChgString getIsLeg() {
        return isLeg;
    }

    public void setIsLeg(IntBeanChgString _isLeg) {
        isLeg = _isLeg;
    }

    public void setMinAccuracy(IntBeanChgString _minAccuracy) {
        minAccuracy = _minAccuracy;
    }

    public IntBeanChgString getMinAccuracy() {
        return minAccuracy;
    }

    public void setMaxAccuracy(IntBeanChgString _maxAccuracy) {
        maxAccuracy = _maxAccuracy;
    }

    public IntBeanChgString getMaxAccuracy() {
        return maxAccuracy;
    }

    public void setMinPower(IntBeanChgString _minPower) {
        minPower = _minPower;
    }

    public IntBeanChgString getMinPower() {
        return minPower;
    }

    public void setMaxPower(IntBeanChgString _maxPower) {
        maxPower = _maxPower;
    }

    public IntBeanChgString getMaxPower() {
        return maxPower;
    }


    public CustList<TranslatedKey> sortedAbilitiesGet() {
        return sortedAbilities.getKeys();
    }

    public void setSortedAbilities(AbsMap<TranslatedKey,AbilityData> _ab) {
        this.sortedAbilities = _ab;
    }

    public IntBeanChgString getTypedCategory() {
        return typedCategory;
    }

    public void setTypedCategory(IntBeanChgString _c) {
        this.typedCategory = _c;
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
