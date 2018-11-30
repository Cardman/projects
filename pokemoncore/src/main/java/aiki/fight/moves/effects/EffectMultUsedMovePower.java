package aiki.fight.moves.effects;

import aiki.DataBase;
import code.maths.Rate;
import code.util.StringMap;


public final class EffectMultUsedMovePower extends Effect {

    private StringMap<Rate> multMovePowerFctType;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String s : multMovePowerFctType.getKeys()) {
            if (!_data.getTypes().containsObj(s)) {
                _data.setError(true);
                return;

            }
            if (!multMovePowerFctType.getVal(s).isZeroOrGt()) {
                _data.setError(true);
                return;

            }
        }
    }

    public StringMap<Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }

    public void setMultMovePowerFctType(StringMap<Rate> _multMovePowerFctType) {
        multMovePowerFctType = _multMovePowerFctType;
    }

}
