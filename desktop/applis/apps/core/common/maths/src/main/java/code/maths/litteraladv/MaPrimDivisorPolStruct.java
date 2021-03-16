package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.matrix.RootPol;
import code.util.CustList;

public final class MaPrimDivisorPolStruct extends MaListNbStruct {
    private final RootPol primDivisor;

    public MaPrimDivisorPolStruct(RootPol _primDivisor) {
        this.primDivisor = _primDivisor;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return new CustList<Rate>(primDivisor.getValue(),new Rate(primDivisor.getDegree()));
    }

    public RootPol getPrimDivisor() {
        return primDivisor;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqPrimDiv(this, _other);
    }

    @Override
    public String displayRsult() {
        return "["+primDivisor.getValue().toNumberString()+" "+primDivisor.getDegree()+"]";
    }

    static boolean eqPrimDiv(MaPrimDivisorPolStruct _this, MaStruct _other) {
        if (!(_other instanceof MaPrimDivisorPolStruct)) {
            return false;
        }
        RootPol oth_ = ((MaPrimDivisorPolStruct) _other).primDivisor;
        return eqPrimDiv(_this.primDivisor, oth_);
    }

    static boolean eqPrimDiv(RootPol _this, RootPol _oth) {
        if (!_this.getValue().eq(_oth.getValue())) {
            return false;
        }
        return _this.getDegree() == _oth.getDegree();
    }
}
