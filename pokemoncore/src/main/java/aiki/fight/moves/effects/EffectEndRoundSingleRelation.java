package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.EntryCust;
import code.util.NumberMap;


public final class EffectEndRoundSingleRelation extends EffectEndRound {

    private NumberMap<Long, Rate> rateDamageFunctionOfNbRounds;
    private MonteCarloNumber lawForEnablingEffect;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!lawForEnablingEffect.checkEvents()) {
            _data.setError(true);
            return;

        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);
            return;

        }
        if (lawForEnablingEffect.events().isEmpty()) {
            _data.setError(true);
            return;

        }
        Rate min_ = lawForEnablingEffect.minimum();
        if (!min_.isZeroOrGt()) {
            _data.setError(true);
            return;

        }
        if (min_.isZero()) {
            _data.setError(true);
            return;

        }
        for (Rate e : lawForEnablingEffect.events()) {
            if (!e.isInteger()) {
                _data.setError(true);
                return;

            }
        }
        for (EntryCust<Long, Rate> e : rateDamageFunctionOfNbRounds.entryList()) {
            e.getKey().longValue();
            if (!e.getValue().isZeroOrGt()) {
                _data.setError(true);
                return;

            }
        }
        if (!rateDamageFunctionOfNbRounds.isEmpty()) {
            return;
        }
        _data.setError(true);
        return;

    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_UNIQUE;
    }

    public NumberMap<Long, Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }

    public void setRateDamageFunctionOfNbRounds(
            NumberMap<Long, Rate> _rateDamageFunctionOfNbRounds) {
        rateDamageFunctionOfNbRounds = _rateDamageFunctionOfNbRounds;
    }

    public MonteCarloNumber getLawForEnablingEffect() {
        return lawForEnablingEffect;
    }

    public void setLawForEnablingEffect(MonteCarloNumber _lawForEnablingEffect) {
        lawForEnablingEffect = _lawForEnablingEffect;
    }
}
