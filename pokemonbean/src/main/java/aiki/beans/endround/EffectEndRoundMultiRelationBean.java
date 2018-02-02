package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;

public class EffectEndRoundMultiRelationBean extends EffectEndRoundBean {

    @Accessible
    private TreeMap<String,Rate> damageByStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect();
        TreeMap<String,Rate> damageByStatus_;
        damageByStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getDamageByStatus().getKeys()) {
            damageByStatus_.put(s, effect_.getDamageByStatus().getVal(s));
        }
        damageByStatus = damageByStatus_;
    }

    @Accessible
    private String getTrDamageStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(damageByStatus.getKey(_index.intValue()));
    }

    @Accessible
    private String clickDamageStatus(Long _indexOne,Long _indexTwo) {
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect(_indexOne);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getDamageByStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getDamageByStatus().getVal(s));
        }
        getForms().put(STATUS, multDamageStatus_.getKey(_indexTwo.intValue()));
        return STATUS;
    }
}
