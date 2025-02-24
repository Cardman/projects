package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectProtection;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffprotection;
import code.scripts.pages.aiki.MessagesPkBean;

public class EffectProtectionBean extends EffectBean {
    private boolean protSingle;
    private Rate protSingleAgainstKo;
    private boolean protTeamAgainstMultTargets;
    private boolean protTeamAgainstPrio;
    private boolean protTeamAgainstStatusMoves;
    private boolean protTeamAgainstDamageMoves;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectProtection effect_ = (EffectProtection) getEffect();
        protSingle = effect_.getProtSingle();
        protSingleAgainstKo = effect_.getProtSingleAgainstKo();
        protTeamAgainstMultTargets = effect_.getProtTeamAgainstMultTargets();
        protTeamAgainstPrio = effect_.getProtTeamAgainstPrio();
        protTeamAgainstStatusMoves = effect_.isProtTeamAgainstStatusMoves();
        protTeamAgainstDamageMoves = effect_.isProtTeamAgainstDamageMoves();
    }

    @Override
    public void buildSubEff() {
        displayBoolTrue(toInt(getProtSingle()), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_SINGLE);
        displayIntDef(getProtSingleAgainstKo(), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_SINGLE_KO);
        displayBoolTrue(toInt(getProtTeamAgainstMultTargets()), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_MULTI_TARGETS);
        displayBoolTrue(toInt(getProtTeamAgainstPrio()), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_PRIO);
        displayBoolTrue(toInt(getProtTeamAgainstStatusMoves()), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_SINGLE_STATUS);
        displayBoolTrue(toInt(getProtTeamAgainstDamageMoves()), MessagesPkBean.EFF_PROTECTION, MessagesDataEffprotection.M_P_55_PROT_SINGLE_DAMAGE);
    }

    public boolean getProtSingle() {
        return protSingle;
    }

    public Rate getProtSingleAgainstKo() {
        return protSingleAgainstKo;
    }

    public boolean getProtTeamAgainstMultTargets() {
        return protTeamAgainstMultTargets;
    }

    public boolean getProtTeamAgainstPrio() {
        return protTeamAgainstPrio;
    }

    public boolean getProtTeamAgainstStatusMoves() {
        return protTeamAgainstStatusMoves;
    }

    public boolean getProtTeamAgainstDamageMoves() {
        return protTeamAgainstDamageMoves;
    }
}