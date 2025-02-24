package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectCopyFighter;
import code.scripts.pages.aiki.MessagesDataEffcopyfighter;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectCopyFighterBean extends EffectBean {
    private long ppForMoves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCopyFighter effect_ = (EffectCopyFighter) getEffect();
        ppForMoves = effect_.getPpForMoves();
    }

    @Override
    public void buildSubEff() {
        formatMessage(MessagesPkBean.EFF_COPYFIGHTER, MessagesDataEffcopyfighter.M_P_42_PP_MOVES,Long.toString(getPpForMoves()));
    }

    public long getPpForMoves() {
        return ppForMoves;
    }
}