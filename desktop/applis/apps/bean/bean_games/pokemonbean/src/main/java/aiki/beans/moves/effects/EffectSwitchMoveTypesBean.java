package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.util.StringList;
import code.util.StringMap;

public class EffectSwitchMoveTypesBean extends EffectBean {
    private StringList replacingTypes;
    private DictionaryComparator<String,String> changeTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) getEffect();
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringList replacingTypes_;
        replacingTypes_ = new StringList();
        for (String t: effect_.getReplacingTypes()) {
            replacingTypes_.add(t);
        }
        replacingTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        replacingTypes = replacingTypes_;
        DictionaryComparator<String,String> changeTypes_;
        changeTypes_ = DictionaryComparatorUtil.buildTypesStr(data_,getLanguage());
        for (String t: effect_.getChangeTypes().getKeys()) {
            changeTypes_.put(t, translatedTypes_.getVal(effect_.getChangeTypes().getVal(t)));
        }
        changeTypes = changeTypes_;
    }
    public String getTrReplacingTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = replacingTypes.get(_index);
        return translatedTypes_.getVal(st_);
    }
    public String getTrChangedTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = changeTypes.getKey(_index);
        return translatedTypes_.getVal(st_);
    }

    public StringList getReplacingTypes() {
        return replacingTypes;
    }

    public DictionaryComparator<String,String> getChangeTypes() {
        return changeTypes;
    }
}