package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import code.maths.Rate;
import code.util.StringMap;

public class EffectMultSufferedMovePowerBean extends EffectBean {
    private DictionaryComparator<String, Rate> multMovePowerFctType;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectMultSufferedMovePower effect_ = (EffectMultSufferedMovePower) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<String, Rate> multMovePowerFctType_;
        multMovePowerFctType_ = DictionaryComparatorUtil.buildTypesRate(data_,getLanguage());
        for (String type_: effect_.getMultMovePowerFctType().getKeys()) {
            multMovePowerFctType_.put(type_, effect_.getMultMovePowerFctType().getVal(type_));
        }
        multMovePowerFctType = multMovePowerFctType_;
    }
    public String getTrType(int _index) {
        String type_ = multMovePowerFctType.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    public DictionaryComparator<String,Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }
}