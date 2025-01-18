package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectCopyFighter;

public class EffectCopyFighterBean extends EffectBean {
    private int ppForMoves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyFighter effect_ = (EffectCopyFighter) getEffect();
        ppForMoves = effect_.getPpForMoves();
    }

    public int getPpForMoves() {
        return ppForMoves;
    }
}