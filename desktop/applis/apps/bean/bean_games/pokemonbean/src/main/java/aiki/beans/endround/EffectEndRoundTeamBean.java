package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import code.maths.Rate;
import code.scripts.pages.aiki.*;

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
    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_TEAM,MessagesDataEndroundTeam.M_P_13_EFFECT);
        displayIntDef(deleteAllStatus,MessagesPkBean.ENDROUND_TEAM,MessagesDataEndroundTeam.M_P_13_OWNER);
        formatMessage(MessagesPkBean.ENDROUND_TEAM,MessagesDataEndroundTeam.M_P_13_TEAM,deleteAllStatusAlly.toNumberString());
    }
    public Rate getDeleteAllStatus() {
        return deleteAllStatus;
    }

    public Rate getDeleteAllStatusAlly() {
        return deleteAllStatusAlly;
    }
}