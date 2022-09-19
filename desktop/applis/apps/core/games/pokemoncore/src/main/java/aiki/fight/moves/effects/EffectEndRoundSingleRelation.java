package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;


public final class EffectEndRoundSingleRelation extends EffectEndRound {

    private LongMap< Rate> rateDamageFunctionOfNbRounds;
    private MonteCarloNumber lawForEnablingEffect;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkEvents(lawForEnablingEffect,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
        if (lawForEnablingEffect.events().isEmpty()) {
            _data.setError(true);
        } else {
            Rate min_ = lawForEnablingEffect.minimum();
            DataInfoChecker.checkPositive(min_,_data);
        }
        DataInfoChecker.checkIntegers(lawForEnablingEffect.events(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(rateDamageFunctionOfNbRounds.values(),_data);
        if (!rateDamageFunctionOfNbRounds.isEmpty()) {
            return;
        }
        _data.setError(true);

    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_UNIQUE;
    }

    public LongMap< Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }

    public void setRateDamageFunctionOfNbRounds(
            LongMap< Rate> _rateDamageFunctionOfNbRounds) {
        rateDamageFunctionOfNbRounds = _rateDamageFunctionOfNbRounds;
    }

    public MonteCarloNumber getLawForEnablingEffect() {
        return lawForEnablingEffect;
    }

    public void setLawForEnablingEffect(MonteCarloNumber _lawForEnablingEffect) {
        lawForEnablingEffect = _lawForEnablingEffect;
    }
}
