package code.maths.litteraladv;

import code.maths.Complex;
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
        MaComplexStruct th_ = new MaComplexStruct(new Complex(_this.getRate()));
        return MaComplexStruct.eqRate(th_,_other);
    }

    public Rate getRate() {
        return rate;
    }

}
