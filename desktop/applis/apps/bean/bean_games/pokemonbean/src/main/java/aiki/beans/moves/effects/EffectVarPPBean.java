package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectVarPP;

public class EffectVarPPBean extends EffectBean {
    private long deletePp;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectVarPP effect_ = (EffectVarPP) getEffect();
        deletePp = effect_.getDeletePp();
    }

    public long getDeletePp() {
        return deletePp;
    }
}