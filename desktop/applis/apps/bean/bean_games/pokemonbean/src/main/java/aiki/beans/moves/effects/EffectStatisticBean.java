package aiki.beans.moves.effects;

import aiki.beans.EffectStatisticCommon;
import aiki.fight.moves.effects.EffectStatistic;
import code.scripts.pages.aiki.MessagesDataEffstatis;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectStatisticBean extends EffectBean {
    private final EffectStatisticCommon effectStatisticCommon = new EffectStatisticCommon();

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectStatistic effect_ = (EffectStatistic) getEffect();
        effectStatisticCommon.init(getFacade(),getLanguage(),effect_, true);
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_EFFECT);
    }

    @Override
    public void buildSubEff() {
        effStatis(getEffectStatisticCommon());
    }

    public EffectStatisticCommon getEffectStatisticCommon() {
        return effectStatisticCommon;
    }

}