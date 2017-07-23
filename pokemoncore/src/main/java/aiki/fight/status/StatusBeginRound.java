package aiki.fight.status;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.exceptions.DataException;

@RwXml
public class StatusBeginRound extends Status {

    private MonteCarloBoolean lawForUsingAMove;
    private MonteCarloNumber lawForUsingAMoveNbRound;
    private MonteCarloBoolean lawForUsingAMoveIfFoe;
    private MonteCarloBoolean lawForFullHealIfMove;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        lawForUsingAMove.checkEvents();
        lawForUsingAMoveNbRound.checkEvents();
        lawForUsingAMoveIfFoe.checkEvents();
        lawForFullHealIfMove.checkEvents();
        if (!lawForUsingAMoveNbRound.events().isEmpty()) {
            Rate min_ = lawForUsingAMoveNbRound.minimum();
            if (!min_.isZeroOrGt()) {
                throw new DataException();
            }
            for (Rate e: lawForUsingAMoveNbRound.events()) {
                if (!e.isInteger()) {
                    throw new DataException();
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
        throw new DataException();
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

    public void setLawForUsingAMoveNbRound(MonteCarloNumber _lawForUsingAMoveNbRound) {
        lawForUsingAMoveNbRound = _lawForUsingAMoveNbRound;
    }

    public MonteCarloBoolean getLawForUsingAMoveIfFoe() {
        return lawForUsingAMoveIfFoe;
    }

    public void setLawForUsingAMoveIfFoe(MonteCarloBoolean _lawForUsingAMoveIfFoe) {
        lawForUsingAMoveIfFoe = _lawForUsingAMoveIfFoe;
    }

    public MonteCarloBoolean getLawForFullHealIfMove() {
        return lawForFullHealIfMove;
    }

    public void setLawForFullHealIfMove(MonteCarloBoolean _lawForFullHealIfMove) {
        lawForFullHealIfMove = _lawForFullHealIfMove;
    }
}
