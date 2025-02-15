package aiki.beans.endround;

import aiki.beans.*;
import aiki.comparators.*;
import aiki.fight.moves.effects.*;
import code.maths.*;

public class EffectEndRoundMultiRelationBean extends EffectEndRoundBean {
    private DictionaryComparator<TranslatedKey,Rate> damageByStatus;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundMultiRelation effect_ = (EffectEndRoundMultiRelation) getEffect();
        DictionaryComparator<TranslatedKey,Rate> damageByStatus_;
        damageByStatus_ = DictionaryComparatorUtil.buildStatusRate();
        for (String s: effect_.getDamageByStatus().getKeys()) {
            damageByStatus_.put(buildSt(getFacade(),s), effect_.getDamageByStatus().getVal(s));
        }
        damageByStatus = damageByStatus_;
    }
    public String getTrDamageStatus(int _index) {
        return damageByStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_;
//        translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(damageByStatus.getKey(_index));
    }
    public String clickDamageStatus(int _indexOne,int _indexTwo) {
        return tryRedirect(((EffectEndRoundMultiRelationBean)getForms().getCurrentBeanEnd().get(_indexOne)).damageByStatus.getKey(_indexTwo));
    }

    public DictionaryComparator<TranslatedKey,Rate> getDamageByStatus() {
        return damageByStatus;
    }
}