package code.maths.litteraladv;

import code.maths.Rate;

public final class MaRateStruct implements MaStruct {
    private final Rate rate;

    public MaRateStruct(Rate _rate) {
        rate = _rate;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqRate(this, _other);
    }

    @Override
    public String displayRsult() {
        return rate.toNumberString();
    }

    public static boolean eqRate(MaRateStruct _this, MaStruct _other) {
        if (!(_other instanceof MaRateStruct)) {
            return false;
        }
        return Rate.eq(_this.rate, ((MaRateStruct) _other).rate);
    }

    public Rate getRate() {
        return rate;
    }

}
