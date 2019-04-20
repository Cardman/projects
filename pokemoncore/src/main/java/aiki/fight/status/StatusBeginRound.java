package aiki.fight.status;

import aiki.db.DataBase;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;


public abstract class StatusBeginRound extends Status {

    private MonteCarloBoolean lawForUsingAMove;
    private MonteCarloNumber lawForUsingAMoveNbRound;
    private MonteCarloBoolean lawForUsingAMoveIfFoe;
    private MonteCarloBoolean lawForFullHealIfMove;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawForUsingAMove.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (!lawForUsingAMoveNbRound.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (!lawForUsingAMoveIfFoe.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (!lawForFullHealIfMove.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (!lawForUsingAMoveNbRound.events().isEmpty()) {
            Rate min_ = lawForUsingAMoveNbRound.minimum();
            if (!min_.isZeroOrGt()) {
                _data.setError(true);
                return;

            }
            for (Rate e : lawForUsingAMoveNbRound.events()) {
                if (!e.isInteger()) {
                    _data.setError(true);
                    return;

                }
            }
            return;
        }
        if (!lawForUsingAMove.events().isEmpty()) {
            return;
        }
        if (!lawForUsingAMoveIfFoe.events().isEmpty()) {
            return;
        }
        _data.setError(true);

    }

    public MonteCarloBoolean getLawForUsingAMove() {
        return lawForUsingAMove;
    }

    public void setLawForUsingAMove(MonteCarloBoolean _lawForUsingAMove) {
        lawForUsingAMove = _lawForUsingAMove;
    }

    public MonteCarloNumber getLawForUsingAMoveNbRound() {
        return lawForUsingAMoveNbRound;
    }

    public void setLawForUsingAMoveNbRound(
            MonteCarloNumber _lawForUsingAMoveNbRound) {
        lawForUsingAMoveNbRound = _lawForUsingAMoveNbRound;
    }

    public MonteCarloBoolean getLawForUsingAMoveIfFoe() {
        return lawForUsingAMoveIfFoe;
    }

    public void setLawForUsingAMoveIfFoe(
            MonteCarloBoolean _lawForUsingAMoveIfFoe) {
        lawForUsingAMoveIfFoe = _lawForUsingAMoveIfFoe;
    }

    public MonteCarloBoolean getLawForFullHealIfMove() {
        return lawForFullHealIfMove;
    }

    public void setLawForFullHealIfMove(MonteCarloBoolean _lawForFullHealIfMove) {
        lawForFullHealIfMove = _lawForFullHealIfMove;
    }
}
