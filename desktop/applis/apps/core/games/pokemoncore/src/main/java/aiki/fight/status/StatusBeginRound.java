package aiki.fight.status;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
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
        validateStatus(_data);
    }
    protected final void validateStatusBeginRound(DataBase _data) {
        DataInfoChecker.checkEvents(lawForUsingAMove,_data);
        DataInfoChecker.checkEvents(lawForUsingAMoveNbRound,_data);
        DataInfoChecker.checkEvents(lawForUsingAMoveIfFoe,_data);
        DataInfoChecker.checkEvents(lawForFullHealIfMove,_data);
        if (!lawForUsingAMoveNbRound.events().isEmpty()) {
            Rate min_ = lawForUsingAMoveNbRound.minimum();
            DataInfoChecker.checkPositiveOrZero(min_,_data);
            DataInfoChecker.checkIntegers(lawForUsingAMoveNbRound.events(),_data);
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
