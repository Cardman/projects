package aiki.beans.moves.effects;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectMultSufferedMovePowerBean extends EffectBean {
    private TreeMap<String, Rate> multMovePowerFctType;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectMultSufferedMovePower effect_ = (EffectMultSufferedMovePower) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<String, Rate> multMovePowerFctType_;
        multMovePowerFctType_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String type_: effect_.getMultMovePowerFctType().getKeys()) {
            multMovePowerFctType_.put(type_, effect_.getMultMovePowerFctType().getVal(type_));
        }
        multMovePowerFctType = multMovePowerFctType_;
    }
    public String getTrType(int _index) {
        String type_ = multMovePowerFctType.getKey(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    public TreeMap<String,Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }
}