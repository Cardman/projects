package aiki.beans.help;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorLanguageEnvType;
import aiki.beans.facade.comparators.ComparatorLanguageGender;
import aiki.beans.facade.comparators.ComparatorLanguageSelectedBoolean;
import aiki.beans.facade.comparators.ComparatorLanguageStatisic;
import aiki.beans.facade.comparators.ComparatorLanguageString;
import aiki.beans.facade.comparators.ComparatorLanguageTargetChoice;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.moves.enums.TargetChoice;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
import code.bean.Accessible;
import code.util.EnumList;
import code.util.StringList;
import code.util.TreeMap;
import code.util.consts.Constants;
import code.util.ints.Listable;
import code.util.pagination.SelectedBoolean;

public class LangsBean extends CommonBean {

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedCategories;

    @Accessible
    private TreeMap<LanguageElementKey,String> translatedEnvironment;

    @Accessible
    private TreeMap<LanguageElementKey,String> translatedBooleans;

    @Accessible
    private TreeMap<LanguageElementKey,String> translatedGenders;

    @Accessible
    private TreeMap<LanguageElementKey,String> translatedStatistics;

    @Accessible
    private TreeMap<LanguageElementKey,String> translatedTargets;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedTypes;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedPokemon;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedMoves;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedItems;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedAbilities;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedStatus;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedClassesDescriptions;

    @Accessible
    private TreeMap<LanguageElementStringKey,String> translatedFctMath;

    @Accessible
    private StringList languages;

    @Override
    public void beforeDisplaying() {
        languages = new StringList();
        languages.add(getLanguage());
        for (String l: Constants.getAvailableLanguages()) {
            if (StringList.quickEq(l,getLanguage())) {
                continue;
            }
            languages.add(l);
        }
        DataBase data_ = (DataBase) getDataBase();
        translatedCategories = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedCategories(),getLanguage()));
        translatedEnvironment = new TreeMap<LanguageElementKey, String>(new ComparatorLanguageEnvType(data_.getTranslatedEnvironment(),getLanguage()));
        translatedBooleans = new TreeMap<LanguageElementKey, String>(new ComparatorLanguageSelectedBoolean(data_.getTranslatedBooleans(),getLanguage()));
        translatedGenders = new TreeMap<LanguageElementKey, String>(new ComparatorLanguageGender(data_.getTranslatedGenders(),getLanguage()));
        translatedStatistics = new TreeMap<LanguageElementKey, String>(new ComparatorLanguageStatisic(data_.getTranslatedStatistics(),getLanguage()));
        translatedTargets = new TreeMap<LanguageElementKey, String>(new ComparatorLanguageTargetChoice(data_.getTranslatedTargets(),getLanguage()));
        translatedTypes = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedTypes(),getLanguage()));
        translatedPokemon = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedPokemon(),getLanguage()));
        translatedMoves = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedMoves(),getLanguage()));
        translatedItems = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedItems(),getLanguage()));
        translatedAbilities = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedAbilities(),getLanguage()));
        translatedStatus = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedStatus(),getLanguage()));
        translatedClassesDescriptions = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedClassesDescriptions(),getLanguage()));
        translatedFctMath = new TreeMap<LanguageElementStringKey, String>(new ComparatorLanguageString(data_.getTranslatedFctMath(),getLanguage()));
        StringList classesItems_;
        classesItems_ = new StringList();
        for (Item i: data_.getItems().values()) {
//            classesItems_.add(i.getClass().getName());
            classesItems_.add(i.getItemType());
        }
        classesItems_.removeDuplicates();
        for (String l:Constants.getAvailableLanguages()) {
            for (String c: data_.getAllCategories()) {
                translatedCategories.put(new LanguageElementStringKey(l,c), data_.getTranslatedCategories().getVal(l).getVal(c));
            }
            for (EnvironmentType e: EnvironmentType.values()) {
                translatedEnvironment.put(new LanguageElementKey(l,e), data_.getTranslatedEnvironment().getVal(l).getVal(e));
            }
            for (SelectedBoolean e: SelectedBoolean.values()) {
                translatedBooleans.put(new LanguageElementKey(l,e), data_.getTranslatedBooleans().getVal(l).getVal(e));
            }
            for (Gender e: Gender.values()) {
                translatedGenders.put(new LanguageElementKey(l,e), data_.getTranslatedGenders().getVal(l).getVal(e));
            }
            for (Statistic e: Statistic.values()) {
                translatedStatistics.put(new LanguageElementKey(l,e), data_.getTranslatedStatistics().getVal(l).getVal(e));
            }
            for (TargetChoice e: TargetChoice.values()) {
                translatedTargets.put(new LanguageElementKey(l,e), data_.getTranslatedTargets().getVal(l).getVal(e));
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
            for (String e: data_.getFunctions()) {
                translatedFctMath.put(new LanguageElementStringKey(l,e), data_.getTranslatedFctMath().getVal(l).getVal(e));
            }
        }
    }

    @Accessible
    private String getTrLang(Long _index) {
        String lang_ = languages.get(_index.intValue());
        return Constants.getDisplayLanguage(lang_);
    }

    @Accessible
    private StringList getKeysCategories() {
        return getStringKeys(translatedCategories);
    }

    @Accessible
    private StringList getRowCategory(Long _index) {
//        return getRowByKey(translatedCategories, getKeys(translatedCategories).get(_index.intValue()));
        return getRowByStringKey(languages, translatedCategories, _index.intValue());
    }

    @Accessible
    private EnumList<EnvironmentType> getKeysEnvironments() {
        return getEnvKeys(translatedEnvironment);
    }

    @Accessible
    private StringList getRowEnvironment(Long _index) {
//        return getRowByKey(translatedEnvironment, getKeys(translatedEnvironment).get(_index.intValue()));
        return getEnvRowByKey(languages, translatedEnvironment, _index.intValue());
    }

    @Accessible
    private EnumList<SelectedBoolean> getKeysBooleans() {
        return getSelectedBooleanKeys(translatedBooleans);
    }

    @Accessible
    private StringList getRowBoolean(Long _index) {
//        return getRowByKey(translatedBooleans, getKeys(translatedBooleans).get(_index.intValue()));
        return getSelectedBooleanRowByKey(languages, translatedBooleans, _index.intValue());
    }

    @Accessible
    private EnumList<Gender> getKeysGenders() {
        return getGenderKeys(translatedGenders);
    }

    @Accessible
    private StringList getRowGender(Long _index) {
//        return getRowByKey(translatedGenders, getKeys(translatedGenders).get(_index.intValue()));
        return getGenderRowByKey(languages, translatedGenders, _index.intValue());
    }

    @Accessible
    private EnumList<Statistic> getKeysStatistics() {
        return getStatisticKeys(translatedStatistics);
    }

    @Accessible
    private StringList getRowStatistic(Long _index) {
//        return getRowByKey(translatedStatistics, getKeys(translatedStatistics).get(_index.intValue()));
        return getStatisticRowByKey(languages, translatedStatistics, _index.intValue());
    }

    @Accessible
    private EnumList<TargetChoice> getKeysTargets() {
        return getTargetKeys(translatedTargets);
    }

    @Accessible
    private StringList getRowTarget(Long _index) {
//        return getRowByKey(translatedTargets, getKeys(translatedTargets).get(_index.intValue()));
        return getTargetRowByKey(languages, translatedTargets, _index.intValue());
    }

    @Accessible
    private StringList getKeysTypes() {
        return getStringKeys(translatedTypes);
    }

    @Accessible
    private StringList getRowType(Long _index) {
//        return getRowByKey(translatedTypes, getKeys(translatedTypes).get(_index.intValue()));
        return getRowByStringKey(languages, translatedTypes, _index.intValue());
    }

    @Accessible
    private StringList getKeysPokemon() {
        return getStringKeys(translatedPokemon);
    }

    @Accessible
    private StringList getRowPokemon(Long _index) {
//        return getRowByKey(translatedPokemon, getKeys(translatedPokemon).get(_index.intValue()));
        return getRowByStringKey(languages, translatedPokemon, _index.intValue());
    }

    @Accessible
    private StringList getKeysMoves() {
        return getStringKeys(translatedMoves);
    }

    @Accessible
    private StringList getRowMove(Long _index) {
//        return getRowByKey(translatedMoves, getKeys(translatedMoves).get(_index.intValue()));
        return getRowByStringKey(languages, translatedMoves, _index.intValue());
    }

    @Accessible
    private StringList getKeysItems() {
        return getStringKeys(translatedItems);
    }

    @Accessible
    private StringList getRowItem(Long _index) {
//        return getRowByKey(translatedItems, getKeys(translatedItems).get(_index.intValue()));
        return getRowByStringKey(languages, translatedItems, _index.intValue());
    }

    @Accessible
    private StringList getKeysAbilities() {
        return getStringKeys(translatedAbilities);
    }

    @Accessible
    private StringList getRowAbility(Long _index) {
//        return getRowByKey(translatedAbilities, getKeys(translatedAbilities).get(_index.intValue()));
        return getRowByStringKey(languages,translatedAbilities, _index.intValue());
    }

    @Accessible
    private StringList getKeysStatus() {
        return getStringKeys(translatedStatus);
    }

    @Accessible
    private StringList getRowStatus(Long _index) {
//        return getRowByKey(translatedStatus, getKeys(translatedStatus).get(_index.intValue()));
        return getRowByStringKey(languages, translatedStatus, _index.intValue());
    }

    @Accessible
    private StringList getKeysDesc() {
        return getStringKeys(translatedClassesDescriptions);
    }

    @Accessible
    private StringList getRowDesc(Long _index) {
//        return getRowByKey(translatedClassesDescriptions, getKeys(translatedClassesDescriptions).get(_index.intValue()));
        return getRowByStringKey(languages, translatedClassesDescriptions, _index.intValue());
    }

    @Accessible
    private StringList getKeysMath() {
        return getStringKeys(translatedFctMath);
    }

    @Accessible
    private StringList getRowMath(Long _index) {
//        return getRowByKey(translatedFctMath, getKeys(translatedFctMath).get(_index.intValue()));
        return getRowByStringKey(languages, translatedFctMath, _index.intValue());
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
    private static EnumList<Statistic> getStatisticKeys(TreeMap<LanguageElementKey,String> _treeMap) {
        EnumList<Statistic> list_ = new EnumList<Statistic>();
        for (LanguageElementKey k: _treeMap.getKeys()) {
            list_.add((Statistic) k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }
    private static EnumList<SelectedBoolean> getSelectedBooleanKeys(TreeMap<LanguageElementKey,String> _treeMap) {
        EnumList<SelectedBoolean> list_ = new EnumList<SelectedBoolean>();
        for (LanguageElementKey k: _treeMap.getKeys()) {
            list_.add((SelectedBoolean) k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }
    private static EnumList<EnvironmentType> getEnvKeys(TreeMap<LanguageElementKey,String> _treeMap) {
        EnumList<EnvironmentType> list_ = new EnumList<EnvironmentType>();
        for (LanguageElementKey k: _treeMap.getKeys()) {
            list_.add((EnvironmentType) k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }
    private static EnumList<Gender> getGenderKeys(TreeMap<LanguageElementKey,String> _treeMap) {
        EnumList<Gender> list_ = new EnumList<Gender>();
        for (LanguageElementKey k: _treeMap.getKeys()) {
            list_.add((Gender) k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }
    private static EnumList<TargetChoice> getTargetKeys(TreeMap<LanguageElementKey,String> _treeMap) {
        EnumList<TargetChoice> list_ = new EnumList<TargetChoice>();
        for (LanguageElementKey k: _treeMap.getKeys()) {
            list_.add((TargetChoice) k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }

    private static StringList getStringKeys(TreeMap<LanguageElementStringKey,String> _treeMap) {
        StringList list_ = new StringList();
        for (LanguageElementStringKey k: _treeMap.getKeys()) {
            list_.add(k.getKey());
        }
        list_.removeDuplicates();
        return list_;
    }
    private static StringList getTargetRowByKey(
            StringList _languages,
            TreeMap<LanguageElementKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }
    private static StringList getEnvRowByKey(
            StringList _languages,
            TreeMap<LanguageElementKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }
    private static StringList getGenderRowByKey(
            StringList _languages,
            TreeMap<LanguageElementKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }
    private static StringList getSelectedBooleanRowByKey(
            StringList _languages,
            TreeMap<LanguageElementKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }

    private static StringList getStatisticRowByKey(
            StringList _languages,
            TreeMap<LanguageElementKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }

    private static StringList getRowByStringKey(StringList _languages,TreeMap<LanguageElementStringKey,String> _treeMap,int _index) {
        StringList list_ = new StringList();
        int nbLangs_ = _languages.size();
        int begin_ = _index * nbLangs_;
        int end_ = begin_ + nbLangs_;
        Listable<String> entries_;
        entries_ = _treeMap.values();
        for (int i = begin_; i < end_; i++) {
            list_.add(entries_.get(i));
        }
        return list_;
    }
}
