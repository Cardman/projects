package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import code.maths.Rate;

public class EffectEndRoundTeamBean extends EffectEndRoundBean {
    private Rate deleteAllStatus;
    private Rate deleteAllStatusAlly;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundTeam effect_ = (EffectEndRoundTeam) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        deleteAllStatusAlly = effect_.getDeleteAllStatusAlly();
    }

    public Rate getDeleteAllStatus() {
        return deleteAllStatus;
    }

    public Rate getDeleteAllStatusAlly() {
        return deleteAllStatusAlly;
    }
}