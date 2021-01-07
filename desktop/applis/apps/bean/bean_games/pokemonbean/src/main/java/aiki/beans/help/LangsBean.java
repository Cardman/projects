package aiki.beans.help;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorLanguageString;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.util.*;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.StringUtil;

public class LangsBean extends CommonBean {
    private TreeMap<LanguageElementStringKey,String> translatedCategories;
    private TreeMap<LanguageElementStringKey,String> translatedEnvironment;
    private TreeMap<LanguageElementStringKey,String> translatedBooleans;
    private TreeMap<LanguageElementStringKey,String> translatedGenders;
    private TreeMap<LanguageElementStringKey,String> translatedStatistics;
    private TreeMap<LanguageElementStringKey,String> translatedTargets;
    private TreeMap<LanguageElementStringKey,String> translatedTypes;
    private TreeMap<LanguageElementStringKey,String> translatedPokemon;
    private TreeMap<LanguageElementStringKey,String> translatedMoves;
    private TreeMap<LanguageElementStringKey,String> translatedItems;
    private TreeMap<LanguageElementStringKey,String> translatedAbilities;
    private TreeMap<LanguageElementStringKey,String> translatedStatus;
    private TreeMap<LanguageElementStringKey,String> translatedClassesDescriptions;
    private TreeMap<LanguageElementStringKey,String> translatedFctMath;
    private StringList languages;

    @Override
    public void beforeDisplaying() {
        languages = new StringList();
        String curLg_ = getLanguage();
        languages.add(curLg_);
        DataBase data_ = (DataBase) getDataBase();
        StringList lgs_ = data_.getLanguages();
        for (String l: lgs_) {
            if (StringUtil.quickEq(l, curLg_)) {
                continue;
            }
            languages.add(l);
        }
        translatedCategories = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedCategories(), curLg_, languages));
        StringMap<StringMap<String>> translatedEnvironment_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,EnumMap<EnvironmentType, String>> e:data_.getTranslatedEnvironment().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<EnvironmentType, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().name(),f.getValue());
            }
            translatedEnvironment_.addEntry(e.getKey(),tr_);
        }
        translatedEnvironment = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(translatedEnvironment_, curLg_, languages));
        StringMap<StringMap<String>> translatedBooleans_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,EnumMap<SelectedBoolean, String>> e:data_.getTranslatedBooleans().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<SelectedBoolean, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().name(),f.getValue());
            }
            translatedBooleans_.addEntry(e.getKey(),tr_);
        }
        translatedBooleans = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(translatedBooleans_, curLg_, languages));
        StringMap<StringMap<String>> translatedGenders_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,EnumMap<Gender, String>> e:data_.getTranslatedGenders().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<Gender, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().name(),f.getValue());
            }
            translatedGenders_.addEntry(e.getKey(),tr_);
        }
        translatedGenders = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(translatedGenders_, curLg_, languages));
        StringMap<StringMap<String>> translatedStatistics_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,EnumMap<Statistic, String>> e:data_.getTranslatedStatistics().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<Statistic, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().name(),f.getValue());
            }
            translatedStatistics_.addEntry(e.getKey(),tr_);
        }
        translatedStatistics = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(translatedStatistics_, curLg_, languages));
        StringMap<StringMap<String>> translatedTargets_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,EnumMap<TargetChoice, String>> e:data_.getTranslatedTargets().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<TargetChoice, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().name(),f.getValue());
            }
            translatedTargets_.addEntry(e.getKey(),tr_);
        }
        translatedTargets = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(translatedTargets_, curLg_, languages));
        translatedTypes = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedTypes(), curLg_, languages));
        translatedPokemon = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedPokemon(), curLg_, languages));
        translatedMoves = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedMoves(), curLg_, languages));
        translatedItems = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedItems(), curLg_, languages));
        translatedAbilities = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedAbilities(), curLg_, languages));
        translatedStatus = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedStatus(), curLg_, languages));
        translatedClassesDescriptions = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedClassesDescriptions(), curLg_, languages));
        translatedFctMath = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedFctMath(), curLg_, languages));
        StringList classesItems_;
        classesItems_ = new StringList();
        for (Item i: data_.getItems().values()) {
//            classesItems_.add(i.getClass().getName());
            classesItems_.add(i.getItemType());
        }
        classesItems_.removeDuplicates();
        for (String l:lgs_) {
            for (String c: data_.getAllCategories()) {
                translatedCategories.put(new LanguageElementStringKey(l,c), data_.getTranslatedCategories().getVal(l).getVal(c));
            }
            for (EnvironmentType e: EnvironmentType.values()) {
                translatedEnvironment.put(new LanguageElementStringKey(l,e.name()), data_.getTranslatedEnvironment().getVal(l).getVal(e));
            }
            for (SelectedBoolean e: SelectedBoolean.values()) {
                translatedBooleans.put(new LanguageElementStringKey(l,e.name()), data_.getTranslatedBooleans().getVal(l).getVal(e));
            }
            for (Gender e: Gender.values()) {
                translatedGenders.put(new LanguageElementStringKey(l,e.name()), data_.getTranslatedGenders().getVal(l).getVal(e));
            }
            for (Statistic e: Statistic.values()) {
                translatedStatistics.put(new LanguageElementStringKey(l,e.name()), data_.getTranslatedStatistics().getVal(l).getVal(e));
            }
            for (TargetChoice e: TargetChoice.values()) {
                translatedTargets.put(new LanguageElementStringKey(l,e.name()), data_.getTranslatedTargets().getVal(l).getVal(e));
            }
            for (String e: data_.getTypes()) {
                translatedTypes.put(new LanguageElementStringKey(l,e), data_.getTranslatedTypes().getVal(l).getVal(e));
            }
            for (String e: data_.getPokedex().getKeys()) {
                translatedPokemon.put(new LanguageElementStringKey(l,e), data_.getTranslatedPokemon().getVal(l).getVal(e));
            }
            for (String e: data_.getMoves().getKeys()) {
                translatedMoves.put(new LanguageElementStringKey(l,e), data_.getTranslatedMoves().getVal(l).getVal(e));
            }
            for (String e: data_.getItems().getKeys()) {
                translatedItems.put(new LanguageElementStringKey(l,e), data_.getTranslatedItems().getVal(l).getVal(e));
            }
            for (String e: data_.getAbilities().getKeys()) {
                translatedAbilities.put(new LanguageElementStringKey(l,e), data_.getTranslatedAbilities().getVal(l).getVal(e));
            }
            for (String e: data_.getStatus().getKeys()) {
                translatedStatus.put(new LanguageElementStringKey(l,e), data_.getTranslatedStatus().getVal(l).getVal(e));
            }
            for (String e: classesItems_) {
                translatedClassesDescriptions.put(new LanguageElementStringKey(l,e), data_.getTranslatedClassesDescriptions().getVal(l).getVal(e));
            }
            StringMap<String> fcts_ = data_.getTranslatedFctMath().getVal(l);
            for (EntryCust<String, String> e: fcts_.entryList()) {
                translatedFctMath.put(new LanguageElementStringKey(l,e.getKey()), e.getValue());
            }
        }
    }
    public String getTrLang(int _index) {
        String lang_ = languages.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        return data_.getDisplayLanguages().getVal(lang_);
    }
    public StringList getKeysCategories() {
        return getStringKeys(translatedCategories);
    }
    public StringList getRowCategory(int _index) {
//        return getRowByKey(translatedCategories, getKeys(translatedCategories).get(_index));
        return getRowByStringKey(languages, translatedCategories, _index);
    }
    public StringList getKeysEnvironments() {
        return getStringKeys(translatedEnvironment);
    }
    public StringList getRowEnvironment(int _index) {
//        return getRowByKey(translatedEnvironment, getKeys(translatedEnvironment).get(_index));
        return getRowByStringKey(languages, translatedEnvironment, _index);
    }
    public StringList getKeysBooleans() {
        return getStringKeys(translatedBooleans);
    }
    public StringList getRowBoolean(int _index) {
//        return getRowByKey(translatedBooleans, getKeys(translatedBooleans).get(_index));
        return getRowByStringKey(languages, translatedBooleans, _index);
    }
    public StringList getKeysGenders() {
        return getStringKeys(translatedGenders);
    }
    public StringList getRowGender(int _index) {
//        return getRowByKey(translatedGenders, getKeys(translatedGenders).get(_index));
        return getRowByStringKey(languages, translatedGenders, _index);
    }
    public StringList getKeysStatistics() {
        return getStringKeys(translatedStatistics);
    }
    public StringList getRowStatistic(int _index) {
//        return getRowByKey(translatedStatistics, getKeys(translatedStatistics).get(_index));
        return getRowByStringKey(languages, translatedStatistics, _index);
    }
    public StringList getKeysTargets() {
        return getStringKeys(translatedTargets);
    }
    public StringList getRowTarget(int _index) {
//        return getRowByKey(translatedTargets, getKeys(translatedTargets).get(_index));
        return getRowByStringKey(languages, translatedTargets, _index);
    }
    public StringList getKeysTypes() {
        return getStringKeys(translatedTypes);
    }
    public StringList getRowType(int _index) {
//        return getRowByKey(translatedTypes, getKeys(translatedTypes).get(_index));
        return getRowByStringKey(languages, translatedTypes, _index);
    }
    public StringList getKeysPokemon() {
        return getStringKeys(translatedPokemon);
    }
    public StringList getRowPokemon(int _index) {
//        return getRowByKey(translatedPokemon, getKeys(translatedPokemon).get(_index));
        return getRowByStringKey(languages, translatedPokemon, _index);
    }
    public StringList getKeysMoves() {
        return getStringKeys(translatedMoves);
    }
    public StringList getRowMove(int _index) {
//        return getRowByKey(translatedMoves, getKeys(translatedMoves).get(_index));
        return getRowByStringKey(languages, translatedMoves, _index);
    }
    public StringList getKeysItems() {
        return getStringKeys(translatedItems);
    }
    public StringList getRowItem(int _index) {
//        return getRowByKey(translatedItems, getKeys(translatedItems).get(_index));
        return getRowByStringKey(languages, translatedItems, _index);
    }
    public StringList getKeysAbilities() {
        return getStringKeys(translatedAbilities);
    }
    public StringList getRowAbility(int _index) {
//        return getRowByKey(translatedAbilities, getKeys(translatedAbilities).get(_index));
        return getRowByStringKey(languages,translatedAbilities, _index);
    }
    public StringList getKeysStatus() {
        return getStringKeys(translatedStatus);
    }
    public StringList getRowStatus(int _index) {
//        return getRowByKey(translatedStatus, getKeys(translatedStatus).get(_index));
        return getRowByStringKey(languages, translatedStatus, _index);
    }
    public StringList getKeysDesc() {
        return getStringKeys(translatedClassesDescriptions);
    }
    public StringList getRowDesc(int _index) {
//        return getRowByKey(translatedClassesDescriptions, getKeys(translatedClassesDescriptions).get(_index));
        return getRowByStringKey(languages, translatedClassesDescriptions, _index);
    }
    public StringList getKeysMath() {
        return getStringKeys(translatedFctMath);
    }
    public StringList getRowMath(int _index) {
//        return getRowByKey(translatedFctMath, getKeys(translatedFctMath).get(_index));
        return getRowByStringKey(languages, translatedFctMath, _index);
    }

//    private static <E> E getKey(StringList _languages,TreeMap<Pair<String,E>,String> _treeMap, int _index) {
//        int index_ = List.FIRST_INDEX;
//        int i_ = List.FIRST_INDEX;
//        int nbElts_ = _treeMap.size();
//        while (index_ < nbElts_) {
//            if (i_ == _index) {
//                return _treeMap.entryList().get(index_).getKey().getSecond();
//            }
//            index_ += _languages.size();
//            i_ ++;
//        }
//        return null;
//    }
    private static StringList getStringKeys(TreeMap<LanguageElementStringKey,String> _treeMap) {
        StringList list_ = new StringList();
        for (LanguageElementStringKey k: _treeMap.getKeys()) {
            list_.add(k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }

    private static StringList getRowByStringKey(StringList _languages,TreeMap<LanguageElementStringKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        CustList<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }

    public StringList getLanguages() {
        return languages;
    }
}