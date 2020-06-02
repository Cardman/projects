package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectProtection;
import code.maths.Rate;

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