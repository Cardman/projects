package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;


public final class EffectCombo {

    private Rate multEvtRateSecEff;
    private MonteCarloNumber repeatedRoundsLaw;
    private short rankIncrementNbRound;
    private CustList<EffectEndRoundFoe> effectEndRound;
    private CustList<EffectTeam> teamMove;

    public void validate(DataBase _data) {
        DataInfoChecker.checkEvents(repeatedRoundsLaw,_data);
        DataInfoChecker.checkPositiveOrZero(multEvtRateSecEff,_data);
        if (repeatedRoundsLaw.events().isEmpty()) {
            _data.setError(true);

        } else {
            Rate min_ = repeatedRoundsLaw.minimum();
            DataInfoChecker.checkPositive(min_,_data);
        }

        DataInfoChecker.checkIntegers(repeatedRoundsLaw.events(),_data);
        if (effectEndRound.size() > 1) {
            _data.setError(true);
        }
        if (teamMove.size() > 1) {
            _data.setError(true);
        }
        if (!effectEndRound.isEmpty()) {
            effectEndRound.first().validate(_data);
        }
        if (!teamMove.isEmpty()) {
            teamMove.first().validate(_data);
        }
    }

    public boolean estActifEquipe() {
        return !teamMove.isEmpty();
    }

    public Rate getMultEvtRateSecEff() {
        return multEvtRateSecEff;
    }

    public void setMultEvtRateSecEff(Rate _multEvtRateSecEff) {
        multEvtRateSecEff = _multEvtRateSecEff;
    }

    public MonteCarloNumber getRepeatedRoundsLaw() {
        return repeatedRoundsLaw;
    }

    public void setRepeatedRoundsLaw(MonteCarloNumber _repeatedRoundsLaw) {
        repeatedRoundsLaw = _repeatedRoundsLaw;
    }

    public short getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public void setRankIncrementNbRound(short _rankIncrementNbRound) {
        rankIncrementNbRound = _rankIncrementNbRound;
    }

    public CustList<EffectEndRoundFoe> getEffectEndRound() {
        return effectEndRound;
    }

    public void setEffectEndRound(CustList<EffectEndRoundFoe> _effectEndRound) {
        effectEndRound = _effectEndRound;
    }

    public CustList<EffectTeam> getTeamMove() {
        return teamMove;
    }

    public void setTeamMove(CustList<EffectTeam> _teamMove) {
        teamMove = _teamMove;
    }
}
