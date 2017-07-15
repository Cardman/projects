package aiki.beans.moves.effects;
import code.bean.Accessible;
import aiki.fight.moves.effects.EffectVarPP;

public class EffectVarPPBean extends EffectBean {

    @Accessible
    private short deletePp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectVarPP effect_ = (EffectVarPP) getEffect();
        deletePp = effect_.getDeletePp();
    }
}
