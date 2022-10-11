package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.util.*;
import code.util.core.StringUtil;

public class LangsBean extends CommonBean {
    private DictionaryComparator<LanguageElementStringKey,String> translatedCategories;
    private DictionaryComparator<LanguageElementStringKey,String> translatedEnvironment;
    private DictionaryComparator<LanguageElementStringKey,String> translatedBooleans;
    private DictionaryComparator<LanguageElementStringKey,String> translatedGenders;
    private DictionaryComparator<LanguageElementStringKey,String> translatedStatistics;
    private DictionaryComparator<LanguageElementStringKey,String> translatedTargets;
    private DictionaryComparator<LanguageElementStringKey,String> translatedTypes;
    private DictionaryComparator<LanguageElementStringKey,String> translatedPokemon;
    private DictionaryComparator<LanguageElementStringKey,String> translatedMoves;
    private DictionaryComparator<LanguageElementStringKey,String> translatedItems;
    private DictionaryComparator<LanguageElementStringKey,String> translatedAbilities;
    private DictionaryComparator<LanguageElementStringKey,String> translatedStatus;
    private DictionaryComparator<LanguageElementStringKey,String> translatedClassesDescriptions;
    private DictionaryComparator<LanguageElementStringKey,String> translatedFctMath;
    private StringList languages;

    @Override
    public void beforeDisplaying() {
        languages = new StringList();
        String curLg_ = getLanguage();
        languages.add(curLg_);
        DataBase data_ = getDataBase();
        StringList lgs_ = data_.getLanguages();
        for (String l: lgs_) {
            if (StringUtil.quickEq(l, curLg_)) {
                continue;
            }
            languages.add(l);
        }
        translatedCategories = DictionaryComparatorUtil.buildTrs(data_.getTranslatedCategories(), curLg_, languages);
        translatedEnvironment = DictionaryComparatorUtil.buildTrs(translatedEnvironment(), curLg_, languages);
        translatedBooleans = DictionaryComparatorUtil.buildTrs(translatedBooleans(), curLg_, languages);
        translatedGenders = DictionaryComparatorUtil.buildTrs(translatedGenders(), curLg_, languages);
        translatedStatistics = DictionaryComparatorUtil.buildTrs(translatedStatistics(), curLg_, languages);
        translatedTargets = DictionaryComparatorUtil.buildTrs(translatedTargets(), curLg_, languages);
        translatedTypes = DictionaryComparatorUtil.buildTrs(data_.getTranslatedTypes(), curLg_, languages);
        translatedPokemon = DictionaryComparatorUtil.buildTrs(data_.getTranslatedPokemon(), curLg_, languages);
        translatedMoves = DictionaryComparatorUtil.buildTrs(data_.getTranslatedMoves(), curLg_, languages);
        translatedItems = DictionaryComparatorUtil.buildTrs(data_.getTranslatedItems(), curLg_, languages);
        translatedAbilities = DictionaryComparatorUtil.buildTrs(data_.getTranslatedAbilities(), curLg_, languages);
        translatedStatus = DictionaryComparatorUtil.buildTrs(data_.getTranslatedStatus(), curLg_, languages);
        translatedClassesDescriptions = DictionaryComparatorUtil.buildTrs(data_.getTranslatedClassesDescriptions(), curLg_, languages);
        translatedFctMath = DictionaryComparatorUtil.buildTrs(data_.getTranslatedFctMath(), curLg_, languages);
        for (String l:lgs_) {
            loopLgs(l);
        }
    }

    private void loopLgs(String _l) {
        DataBase data_ = getDataBase();
        for (String c: data_.getAllCategories()) {
            translatedCategories.put(new LanguageElementStringKey(_l,c), data_.getTranslatedCategories().getVal(_l).getVal(c));
        }
        for (EnvironmentType e: EnvironmentType.all()) {
            translatedEnvironment.put(new LanguageElementStringKey(_l,e.getEnvName()), data_.getTranslatedEnvironment().getVal(_l).getVal(e));
        }
        for (SelectedBoolean e: SelectedBoolean.all()) {
            translatedBooleans.put(new LanguageElementStringKey(_l,e.getBoolName()), data_.getTranslatedBooleans().getVal(_l).getVal(e));
        }
        for (Gender e: Gender.all()) {
            translatedGenders.put(new LanguageElementStringKey(_l,e.getGenderName()), data_.getTranslatedGenders().getVal(_l).getVal(e));
        }
        for (Statistic e: Statistic.all()) {
            translatedStatistics.put(new LanguageElementStringKey(_l,e.getStatName()), data_.getTranslatedStatistics().getVal(_l).getVal(e));
        }
        for (TargetChoice e: TargetChoice.all()) {
            translatedTargets.put(new LanguageElementStringKey(_l,e.getTargetName()), data_.getTranslatedTargets().getVal(_l).getVal(e));
        }
        for (String e: data_.getTypes()) {
            translatedTypes.put(new LanguageElementStringKey(_l,e), data_.getTranslatedTypes().getVal(_l).getVal(e));
        }
        for (String e: data_.getPokedex().getKeys()) {
            translatedPokemon.put(new LanguageElementStringKey(_l,e), data_.getTranslatedPokemon().getVal(_l).getVal(e));
        }
        for (String e: data_.getMoves().getKeys()) {
            translatedMoves.put(new LanguageElementStringKey(_l,e), data_.getTranslatedMoves().getVal(_l).getVal(e));
        }
        for (String e: data_.getItems().getKeys()) {
            translatedItems.put(new LanguageElementStringKey(_l,e), data_.getTranslatedItems().getVal(_l).getVal(e));
        }
        for (String e: data_.getAbilities().getKeys()) {
            translatedAbilities.put(new LanguageElementStringKey(_l,e), data_.getTranslatedAbilities().getVal(_l).getVal(e));
        }
        for (String e: data_.getStatus().getKeys()) {
            translatedStatus.put(new LanguageElementStringKey(_l,e), data_.getTranslatedStatus().getVal(_l).getVal(e));
        }
        for (String e: classesItems()) {
            translatedClassesDescriptions.put(new LanguageElementStringKey(_l,e), data_.getTranslatedClassesDescriptions().getVal(_l).getVal(e));
        }
        StringMap<String> fcts_ = data_.getTranslatedFctMath().getVal(_l);
        for (EntryCust<String, String> e: fcts_.entryList()) {
            translatedFctMath.put(new LanguageElementStringKey(_l,e.getKey()), e.getValue());
        }
    }

    private StringList classesItems() {
        DataBase data_ = getDataBase();
        StringList classesItems_;
        classesItems_ = new StringList();
        for (Item i: data_.getItems().values()) {
//            classesItems_.add(i.getClass().getName());
            classesItems_.add(i.getItemType());
        }
        classesItems_.removeDuplicates();
        return classesItems_;
    }

    private StringMap<StringMap<String>> translatedTargets() {
        DataBase data_ = getDataBase();
        StringMap<StringMap<String>> translatedTargets_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,IdMap<TargetChoice, String>> e: data_.getTranslatedTargets().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<TargetChoice, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().getTargetName(),f.getValue());
            }
            translatedTargets_.addEntry(e.getKey(),tr_);
        }
        return translatedTargets_;
    }

    private StringMap<StringMap<String>> translatedStatistics() {
        DataBase data_ = getDataBase();
        StringMap<StringMap<String>> translatedStatistics_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,IdMap<Statistic, String>> e: data_.getTranslatedStatistics().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<Statistic, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().getStatName(),f.getValue());
            }
            translatedStatistics_.addEntry(e.getKey(),tr_);
        }
        return translatedStatistics_;
    }

    private StringMap<StringMap<String>> translatedGenders() {
        DataBase data_ = getDataBase();
        StringMap<StringMap<String>> translatedGenders_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,IdMap<Gender, String>> e: data_.getTranslatedGenders().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<Gender, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().getGenderName(),f.getValue());
            }
            translatedGenders_.addEntry(e.getKey(),tr_);
        }
        return translatedGenders_;
    }

    private StringMap<StringMap<String>> translatedBooleans() {
        DataBase data_ = getDataBase();
        StringMap<StringMap<String>> translatedBooleans_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,IdMap<SelectedBoolean, String>> e: data_.getTranslatedBooleans().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<SelectedBoolean, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().getBoolName(),f.getValue());
            }
            translatedBooleans_.addEntry(e.getKey(),tr_);
        }
        return translatedBooleans_;
    }

    private StringMap<StringMap<String>> translatedEnvironment() {
        DataBase data_ = getDataBase();
        StringMap<StringMap<String>> translatedEnvironment_ = new StringMap<StringMap<String>>();
        for (EntryCust<String,IdMap<EnvironmentType, String>> e: data_.getTranslatedEnvironment().entryList()) {
            StringMap<String> tr_ = new StringMap<String>();
            for (EntryCust<EnvironmentType, String> f:e.getValue().entryList()) {
                tr_.addEntry(f.getKey().getEnvName(),f.getValue());
            }
            translatedEnvironment_.addEntry(e.getKey(),tr_);
        }
        return translatedEnvironment_;
    }

    public String getTrLang(int _index) {
        String lang_ = languages.get(_index);
        DataBase data_ = getDataBase();
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
    private static StringList getStringKeys(DictionaryComparator<LanguageElementStringKey,String> _treeMap) {
        StringList list_ = new StringList();
        for (LanguageElementStringKey k: _treeMap.getKeys()) {
            list_.add(k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }

    private static StringList getRowByStringKey(StringList _languages,DictionaryComparator<LanguageElementStringKey,String> _treeMap,int _index) {
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