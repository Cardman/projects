package aiki.beans.moves.effects;

import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectMultMovePower;
import code.maths.Rate;

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