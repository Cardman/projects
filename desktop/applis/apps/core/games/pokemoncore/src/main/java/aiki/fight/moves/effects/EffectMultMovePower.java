package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;
import code.maths.Rate;
import code.util.StringMap;

public class EffectMultMovePower extends Effect {

    private StringMap<Rate> multMovePowerFctType;
    private final boolean used;

    public EffectMultMovePower(boolean _u) {
        this.used = _u;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getTypes(),multMovePowerFctType.getKeys(),_data);
        DataInfoChecker.checkPositiveOrZeroRates(multMovePowerFctType.values(),_data);
    }

    public boolean isUsed() {
        return used;
    }

    public StringMap<Rate> getMultMovePowerFctType() {
        return multMovePowerFctType;
    }

    public void setMultMovePowerFctType(StringMap<Rate> _multMovePowerFctType) {
        multMovePowerFctType = _multMovePowerFctType;
    }
}