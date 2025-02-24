package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectVarPP;
import code.scripts.pages.aiki.MessagesDataEffvarpp;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectVarPPBean extends EffectBean {
    private long deletePp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectVarPP effect_ = (EffectVarPP) getEffect();
        deletePp = effect_.getDeletePp();
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_VARPP,MessagesDataEffvarpp.M_P_69_EFFECT);
    }

    @Override
    public void buildSubEff() {
        formatMessage(MessagesPkBean.EFF_VARPP, MessagesDataEffvarpp.M_P_69_DELETE_PP,Long.toString(getDeletePp()));
    }

    public long getDeletePp() {
        return deletePp;
    }
}