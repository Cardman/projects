package aiki.fight.moves.effects;

import aiki.db.DataBase;
import code.maths.Rate;
import code.util.StringMap;
import code.util.core.StringUtil;


public final class EffectMultSufferedMovePower extends Effect {

    private StringMap<Rate> multMovePowerFctType;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (String s : multMovePowerFctType.getKeys()) {
            if (!StringUtil.contains(_data.getTypes(), s)) {
                _data.setError(true);
            }
            if (!multMovePowerFctType.getVal(s).isZeroOrGt()) {
                _data.setError(true);
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
