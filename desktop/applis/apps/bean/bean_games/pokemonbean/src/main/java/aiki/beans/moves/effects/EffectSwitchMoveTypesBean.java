package aiki.beans.moves.effects;

import aiki.beans.BeanDisplayList;
import aiki.beans.BeanDisplayMap;
import aiki.beans.BeanDisplayTranslatedKey;
import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import code.scripts.pages.aiki.MessagesDataEffswitchmovestypes;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;
import code.util.core.NumberUtil;

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

    @Override
    public void buildSubEff() {
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getReplacingTypes(), MessagesPkBean.EFF_SWITCHMOVESTYPES, MessagesDataEffswitchmovestypes.M_P_62_REPLACING_TYPES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).displayHead(this,getChangeTypes().getKeys(), NumberUtil.signum(getReplacingTypes().size()),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_CHANGING_TYPE_POSSIBLE);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).displayHead(this,getChangeTypes().getKeys(),1-NumberUtil.signum(getReplacingTypes().size()),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_CHANGING_TYPE);
        new BeanDisplayMap<TranslatedKey,TranslatedKey>(new BeanDisplayTranslatedKey(),new BeanDisplayTranslatedKey()).displayGrid(this,getChangeTypes(),MessagesPkBean.EFF_SWITCHMOVESTYPES,"",MessagesDataEffswitchmovestypes.M_P_62_OLD_TYPE,MessagesDataEffswitchmovestypes.M_P_62_NEW_TYPE);
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