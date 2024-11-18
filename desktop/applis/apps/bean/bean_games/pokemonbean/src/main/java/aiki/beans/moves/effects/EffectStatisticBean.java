package aiki.beans.moves.effects;

import aiki.beans.EffectStatisticCommon;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectStatistic;

public class EffectStatisticBean extends EffectBean {
    private final EffectStatisticCommon effectStatisticCommon = new EffectStatisticCommon();

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatistic effect_ = (EffectStatistic) getEffect();
        DataBase data_ = getDataBase();
        effectStatisticCommon.init(data_,getLanguage(),effect_, true);
    }

    public EffectStatisticCommon getEffectStatisticCommon() {
        return effectStatisticCommon;
    }

}