package aiki.fight.moves.effects;

import aiki.DataBase;
import aiki.fight.moves.enums.TargetChoice;

public final class EffectSwitchPosition extends Effect {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.ALLIE) {
            _data.setError(true);
            return;

        }
    }
}
