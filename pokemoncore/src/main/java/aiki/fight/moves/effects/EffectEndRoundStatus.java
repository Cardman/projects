package aiki.fight.moves.effects;

import aiki.DataBase;
import code.maths.Rate;


public abstract class EffectEndRoundStatus extends EffectEndRound {

    private Rate inflictedRateHpTarget;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (inflictedRateHpTarget == null) {
            _data.setError(true);
            return;

        }
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public void setInflictedRateHpTarget(Rate _inflictedRateHpTarget) {
        inflictedRateHpTarget = _inflictedRateHpTarget;
    }
}
