package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;

public final class MaPolMemberStruct implements MaStruct {
    private final Rate rate;
    private final LgInt power;

    public MaPolMemberStruct(Rate _rate, LgInt _power) {
        this.rate = _rate;
        this.power = _power;
    }

    public Rate getRate() {
        return rate;
    }

    public LgInt getPower() {
        return power;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaPolMemberStruct)) {
            return false;
        }
        return eq(rate,power,((MaPolMemberStruct)_other).rate,((MaPolMemberStruct)_other).power);
    }

    static boolean eq(Rate _rateThis, LgInt _powerThis,
                      Rate _rateOther, LgInt _powerOther) {
        return _rateThis.eq(_rateOther) && _powerThis.eq(_powerOther);
    }
    @Override
    public String displayRsult() {
        return exportStr(rate,power);
    }

    static String exportStr(Rate _rateThis, LgInt _powerThis) {
        return _rateThis.toNumberString() + "<>" + _powerThis.toNumberString();
    }
}
