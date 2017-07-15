package aiki.beans.moves.effects;
import code.bean.Accessible;
import aiki.fight.moves.effects.EffectCopyFighter;

public class EffectCopyFighterBean extends EffectBean {

    @Accessible
    private short ppForMoves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyFighter effect_ = (EffectCopyFighter) getEffect();
        ppForMoves = effect_.getPpForMoves();
    }
}
