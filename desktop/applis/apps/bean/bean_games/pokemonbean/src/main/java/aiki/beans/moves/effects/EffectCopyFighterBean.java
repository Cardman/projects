package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectCopyFighter;

public class EffectCopyFighterBean extends EffectBean {
    private long ppForMoves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyFighter effect_ = (EffectCopyFighter) getEffect();
        ppForMoves = effect_.getPpForMoves();
    }

    public long getPpForMoves() {
        return ppForMoves;
    }
}