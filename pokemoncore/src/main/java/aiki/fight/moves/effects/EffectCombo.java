package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class EffectCombo {

    @CheckedData
    private Rate multEvtRateSecEff;
    private MonteCarloNumber repeatedRoundsLaw;
    @CheckedData
    private short rankIncrementNbRound;
    private CustList<EffectEndRoundFoe> effectEndRound;
    private CustList<EffectTeam> teamMove;

    public void validate(DataBase _data) {
        repeatedRoundsLaw.checkEvents();
        if (!multEvtRateSecEff.isZeroOrGt()) {
            throw new DataException();
        }
        if (repeatedRoundsLaw.events().isEmpty()) {
            throw new DataException();
        }
        Rate min_ = repeatedRoundsLaw.minimum();
        if (!min_.isZeroOrGt()) {
            throw new DataException();
        }
        if (min_.isZero()) {
            throw new DataException();
        }
        for (Rate e: repeatedRoundsLaw.events()) {
            if (!e.isInteger()) {
                throw new DataException();
            }
        }
        if (effectEndRound.size() > 1) {
            throw new DataException();
        }
        if (teamMove.size() > 1) {
            throw new DataException();
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
