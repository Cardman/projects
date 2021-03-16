package code.maths.litteraladv;

import code.maths.PrimDivisor;
import code.maths.Rate;
import code.util.CustList;

public final class MaPrimDivisorNbStruct extends MaListNbStruct {
    private final PrimDivisor primDivisor;

    public MaPrimDivisorNbStruct(PrimDivisor _primDivisor) {
        this.primDivisor = _primDivisor;
    }

    public PrimDivisor getPrimDivisor() {
        return primDivisor;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return new CustList<Rate>(new Rate(primDivisor.getExponent()),new Rate(primDivisor.getPrime()));
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqPrimDiv(this, _other);
    }

    @Override
    public String displayRsult() {
        return "["+primDivisor.getPrime().toNumberString()+" "+primDivisor.getExponent().toNumberString()+"]";
    }

    static boolean eqPrimDiv(MaPrimDivisorNbStruct _this, MaStruct _other) {
        if (!(_other instanceof MaPrimDivisorNbStruct)) {
            return false;
        }
        PrimDivisor oth_ = ((MaPrimDivisorNbStruct) _other).primDivisor;
        return eqPrimDiv(_this.primDivisor, oth_);
    }

    static boolean eqPrimDiv(PrimDivisor _this, PrimDivisor _oth) {
        if (!_this.getPrime().eq(_oth.getPrime())) {
            return false;
        }
        return _this.getExponent().eq(_oth.getExponent());
    }
}
