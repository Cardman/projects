package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectMultUsedMovePower;

public class EffectMultUsedMovePowerBean extends EffectBean {

    @Accessible
    private TreeMap<String, Rate> multMovePowerFctType;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectMultUsedMovePower effect_ = (EffectMultUsedMovePower) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<String, Rate> multMovePowerFctType_;
        multMovePowerFctType_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String type_: effect_.getMultMovePowerFctType().getKeys()) {
            multMovePowerFctType_.put(type_, effect_.getMultMovePowerFctType().getVal(type_));
        }
        multMovePowerFctType = multMovePowerFctType_;
    }

    @Accessible
    private String getTrType(Long _index) {
        String type_ = multMovePowerFctType.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
}
