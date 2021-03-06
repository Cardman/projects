package code.maths.litteraladv;

import code.maths.LgInt;
import code.util.CustList;

public class MaDividersNbStruct implements MaStruct {
    private final CustList<LgInt> dividers;

    public MaDividersNbStruct(CustList<LgInt> _idBezout) {
        this.dividers = _idBezout;
    }

    public CustList<LgInt> getDividers() {
        return dividers;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqDivs(this, _other);
    }

    static boolean eqDivs(MaDividersNbStruct _this, MaStruct _other) {
        if (!(_other instanceof MaDividersNbStruct)) {
            return false;
        }
        CustList<LgInt> oth_ = ((MaDividersNbStruct) _other).dividers;
        return LgInt.eq(_this.dividers,oth_);
    }
}
