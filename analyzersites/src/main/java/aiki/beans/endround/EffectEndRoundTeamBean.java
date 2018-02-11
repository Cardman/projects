package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectEndRoundTeam;

public class EffectEndRoundTeamBean extends EffectEndRoundBean {

    @Accessible
    private Rate deleteAllStatus;

    @Accessible
    private Rate deleteAllStatusAlly;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundTeam effect_ = (EffectEndRoundTeam) getEffect();
        deleteAllStatus = effect_.getDeleteAllStatus();
        deleteAllStatusAlly = effect_.getDeleteAllStatusAlly();
    }
}
