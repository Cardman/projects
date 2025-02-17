package aiki.beans.moves.effects;

import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.util.*;

public class EffectSwitchMoveTypesBean extends EffectBean {
    private CustList<TranslatedKey> replacingTypes;
    private DictionaryComparator<TranslatedKey,TranslatedKey> changeTypes;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchMoveTypes effect_ = (EffectSwitchMoveTypes) getEffect();
//        DataBase data_ = getDataBase();
//        StringList replacingTypes_;
//        replacingTypes_ = new StringList();
//        for (String t: effect_.getReplacingTypes()) {
//            replacingTypes_.add(t);
//        }
//        replacingTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        replacingTypes = listTrStringsTy(effect_.getReplacingTypes(),getFacade());
        DictionaryComparator<TranslatedKey,TranslatedKey> changeTypes_;
        changeTypes_ = DictionaryComparatorUtil.buildTypesStr();
        for (String t: effect_.getChangeTypes().getKeys()) {
            changeTypes_.put(buildTy(getFacade(),t), buildTy(getFacade(),effect_.getChangeTypes().getVal(t)));
        }
        changeTypes = changeTypes_;
    }
    public String getTrReplacingTypes(int _index) {
        return replacingTypes.get(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String st_ = replacingTypes.get(_index);
//        return translatedTypes_.getVal(st_);
    }
    public String getTrChangedTypes(int _index) {
        return changeTypes.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String st_ = changeTypes.getKey(_index);
//        return translatedTypes_.getVal(st_);
    }

    public CustList<TranslatedKey> getReplacingTypes() {
        return replacingTypes;
    }

    public DictionaryComparator<TranslatedKey,TranslatedKey> getChangeTypes() {
        return changeTypes;
    }
}