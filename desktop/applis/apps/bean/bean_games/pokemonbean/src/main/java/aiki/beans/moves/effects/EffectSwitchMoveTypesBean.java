package aiki.beans.moves.effects;

import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.util.StringList;
import code.util.StringMap;

public class EffectSwitchMoveTypesBean extends EffectBean {
    private StringList replacingTypes;
    private DictionaryComparator<TranslatedKey,TranslatedKey> changeTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) getEffect();
        DataBase data_ = getDataBase();
        StringList replacingTypes_;
        replacingTypes_ = new StringList();
        for (String t: effect_.getReplacingTypes()) {
            replacingTypes_.add(t);
        }
        replacingTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        replacingTypes = replacingTypes_;
        DictionaryComparator<TranslatedKey,TranslatedKey> changeTypes_;
        changeTypes_ = DictionaryComparatorUtil.buildTypesStr();
        for (String t: effect_.getChangeTypes().getKeys()) {
            changeTypes_.put(buildTy(getFacade(),t), buildTy(getFacade(),effect_.getChangeTypes().getVal(t)));
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
        return changeTypes.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String st_ = changeTypes.getKey(_index);
//        return translatedTypes_.getVal(st_);
    }

    public StringList getReplacingTypes() {
        return replacingTypes;
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getChangeTypes() {
        return changeTypes;
    }
}