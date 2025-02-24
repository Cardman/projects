package aiki.beans.moves.effects;

import aiki.beans.EffectStatisticCommon;
import aiki.fight.moves.effects.EffectStatistic;

public class EffectStatisticBean extends EffectBean {
    private final EffectStatisticCommon effectStatisticCommon = new EffectStatisticCommon();

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatistic effect_ = (EffectStatistic) getEffect();
        effectStatisticCommon.init(getFacade(),getLanguage(),effect_, true);
    }

    @Override
    public void buildSubEff() {
        effStatis(getEffectStatisticCommon());
    }

    public EffectStatisticCommon getEffectStatisticCommon() {
        return effectStatisticCommon;
    }

}