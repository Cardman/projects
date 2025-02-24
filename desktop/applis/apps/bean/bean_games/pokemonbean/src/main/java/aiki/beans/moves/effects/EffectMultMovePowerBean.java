package aiki.beans.moves.effects;

import aiki.beans.BeanDisplayMap;
import aiki.beans.BeanDisplayRate;
import aiki.beans.BeanDisplayTranslatedKey;
import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectMultMovePower;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffmultsufferedmovepower;
import code.scripts.pages.aiki.MessagesDataEffmultusedmovepower;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectMultMovePowerBean extends EffectBean {
    private DictionaryComparator<TranslatedKey, Rate> multMovePowerFctType;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectMultMovePower effect_ = (EffectMultMovePower) getEffect();
        DictionaryComparator<TranslatedKey, Rate> multMovePowerFctType_;
        multMovePowerFctType_ = DictionaryComparatorUtil.buildTypesRate();
        for (String type_: effect_.getMultMovePowerFctType().getKeys()) {
            multMovePowerFctType_.put(buildTy(getFacade(),type_), effect_.getMultMovePowerFctType().getVal(type_));
        }
        multMovePowerFctType = multMovePowerFctType_;
    }

    @Override
    public void buildSubEffPre() {
        if (getEffect() instanceof EffectMultSufferedMovePower) {
            formatMessage(MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER,MessagesDataEffmultsufferedmovepower.M_P_51_EFFECT);
        } else  {
            formatMessage(MessagesPkBean.EFF_MULTUSEDMOVEPOWER,MessagesDataEffmultusedmovepower.M_P_52_EFFECT);
        }
    }

    @Override
    public void buildSubEff() {
        if (getEffect() instanceof EffectMultSufferedMovePower) {
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this, getMultMovePowerFctType(), MessagesPkBean.EFF_MULTSUFFEREDMOVEPOWER, MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER,MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER_TYPE,MessagesDataEffmultsufferedmovepower.M_P_51_MULT_POWER_RATE);
        } else {
            new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this, getMultMovePowerFctType(),MessagesPkBean.EFF_MULTUSEDMOVEPOWER, MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER,MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER_TYPE,MessagesDataEffmultusedmovepower.M_P_52_MULT_POWER_RATE);
        }
    }

    public String getTrType(int _index) {
        return multMovePowerFctType.getKey(_index).getTranslation();
//        String type_ = multMovePowerFctType.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
    }

    public DictionaryComparator<TranslatedKey,Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }
}