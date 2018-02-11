package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectProtection;

public class EffectProtectionBean extends EffectBean {

    @Accessible
    private boolean protSingle;

    @Accessible
    private Rate protSingleAgainstKo;

    @Accessible
    private boolean protTeamAgainstMultTargets;

    @Accessible
    private boolean protTeamAgainstPrio;

    @Accessible
    private boolean protTeamAgainstStatusMoves;

    @Accessible
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
}
