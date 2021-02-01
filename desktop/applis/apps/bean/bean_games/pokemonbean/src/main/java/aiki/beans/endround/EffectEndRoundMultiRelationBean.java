package aiki.beans.endround;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import code.maths.Rate;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectEndRoundMultiRelationBean extends EffectEndRoundBean {
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
    public String getTrDamageStatus(int _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(damageByStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexOne,int _indexTwo) {
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect(_indexOne);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        TreeMap<String,Rate> multDamageStatus_;
        multDamageStatus_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedStatus_));
        for (String s: effect_.getDamageByStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getDamageByStatus().getVal(s));
        }
        getForms().put(CST_STATUS, multDamageStatus_.getKey(_indexTwo));
        return CST_STATUS;
    }

    public TreeMap<String,Rate> getDamageByStatus() {
        return damageByStatus;
    }
}