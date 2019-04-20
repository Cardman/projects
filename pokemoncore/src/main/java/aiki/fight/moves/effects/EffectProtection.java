package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;


public final class EffectProtection extends Effect {

    private boolean protSingle;
    private Rate protSingleAgainstKo;
    private boolean protTeamAgainstMultTargets;
    private boolean protTeamAgainstPrio;
    private boolean protTeamAgainstStatusMoves;
    private boolean protTeamAgainstDamageMoves;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        if (!protSingleAgainstKo.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (!protSingle && !protTeamAgainstMultTargets && !protTeamAgainstPrio) {
            if (!protTeamAgainstStatusMoves && !protTeamAgainstDamageMoves) {
                if (protSingleAgainstKo.isZero()) {
                    _data.setError(true);

                }
            }
        }
    }

    public boolean getProtSingle() {
        return protSingle;
    }

    public void setProtSingle(boolean _protSingle) {
        protSingle = _protSingle;
    }

    public Rate getProtSingleAgainstKo() {
        return protSingleAgainstKo;
    }

    public void setProtSingleAgainstKo(Rate _protSingleAgainstKo) {
        protSingleAgainstKo = _protSingleAgainstKo;
    }

    public boolean getProtTeamAgainstMultTargets() {
        return protTeamAgainstMultTargets;
    }

    public void setProtTeamAgainstMultTargets(
            boolean _protTeamAgainstMultTargets) {
        protTeamAgainstMultTargets = _protTeamAgainstMultTargets;
    }

    public boolean getProtTeamAgainstPrio() {
        return protTeamAgainstPrio;
    }

    public void setProtTeamAgainstPrio(boolean _protTeamAgainstPrio) {
        protTeamAgainstPrio = _protTeamAgainstPrio;
    }

    public boolean isProtTeamAgainstStatusMoves() {
        return protTeamAgainstStatusMoves;
    }

    public void setProtTeamAgainstStatusMoves(
            boolean _protTeamAgainstStatusMoves) {
        protTeamAgainstStatusMoves = _protTeamAgainstStatusMoves;
    }

    public boolean isProtTeamAgainstDamageMoves() {
        return protTeamAgainstDamageMoves;
    }

    public void setProtTeamAgainstDamageMoves(
            boolean _protTeamAgainstDamageMoves) {
        protTeamAgainstDamageMoves = _protTeamAgainstDamageMoves;
    }
}
