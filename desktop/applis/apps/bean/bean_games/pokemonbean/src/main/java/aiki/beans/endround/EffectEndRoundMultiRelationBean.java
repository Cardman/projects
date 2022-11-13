package aiki.beans.endround;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import code.maths.Rate;
import code.util.StringMap;

public class EffectEndRoundMultiRelationBean extends EffectEndRoundBean {
    private DictionaryComparator<String,Rate> damageByStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect();
        DictionaryComparator<String,Rate> damageByStatus_;
        damageByStatus_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String s: effect_.getDamageByStatus().getKeys()) {
            damageByStatus_.put(s, effect_.getDamageByStatus().getVal(s));
        }
        damageByStatus = damageByStatus_;
    }
    public String getTrDamageStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_;
        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(damageByStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexOne,int _indexTwo) {
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect(_indexOne);
        DataBase data_ = getDataBase();
        DictionaryComparator<String,Rate> multDamageStatus_;
        multDamageStatus_ = DictionaryComparatorUtil.buildStatusRate(data_,getLanguage());
        for (String s: effect_.getDamageByStatus().getKeys()) {
            multDamageStatus_.put(s, effect_.getDamageByStatus().getVal(s));
        }
        return tryRedirectSt(multDamageStatus_.getKey(_indexTwo));
    }

    public DictionaryComparator<String,Rate> getDamageByStatus() {
        return damageByStatus;
    }
}