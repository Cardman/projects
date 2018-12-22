package aiki.beans.endround;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectEndRoundSingleStatusBean extends EffectEndRoundStatusBean {
    private TreeMap<String,Rate> multDamageStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundSingleStatus effect_ = (EffectEndRoundSingleStatus) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
        }
        multDamageStatus = multDamageStatus_;
    }
    public String getTrDamageStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(multDamageStatus.getKey(_index.intValue()));
    }
    public String clickDamageStatus(Long _indexOne,Long _indexTwo) {
        EffectEndRoundSingleStatus effect_ = (EffectEndRoundSingleStatus) getEffect(_indexOne);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getMultDamageStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getMultDamageStatus().getVal(s));
        }
        getForms().put(STATUS, multDamageStatus_.getKey(_indexTwo.intValue()));
        return STATUS;
    }
}