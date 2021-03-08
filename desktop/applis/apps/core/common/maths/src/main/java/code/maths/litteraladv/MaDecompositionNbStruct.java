package code.maths.litteraladv;

import code.maths.Decomposition;
import code.maths.PrimDivisor;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaDecompositionNbStruct implements MaStruct {
    private final Decomposition decomposition;

    public MaDecompositionNbStruct(Decomposition _decomposition) {
        this.decomposition = _decomposition;
    }

    public Decomposition getDecomposition() {
        return decomposition;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqDecomposition(this, _other);
    }

    @Override
    public String displayRsult() {
        String out_ = "";
        if (!decomposition.isPositive()) {
            out_ += "-";
        }
        CustList<PrimDivisor> factors_ = decomposition.getFactors();
        StringList list_ = new StringList(new CollCapacity(factors_.size()));
        for (PrimDivisor d: factors_) {
            list_.add("["+d.getPrime().toNumberString()+" "+d.getExponent().toNumberString()+"]");
        }
        return out_+"["+ StringUtil.join(list_,"")+"]";
    }

    static boolean eqDecomposition(MaDecompositionNbStruct _this, MaStruct _other) {
        if (!(_other instanceof MaDecompositionNbStruct)) {
            return false;
        }
        Decomposition oth_ = ((MaDecompositionNbStruct) _other).decomposition;
        if (_this.decomposition.isPositive() != oth_.isPositive()) {
            return false;
        }
        CustList<PrimDivisor> factorsThis_ = _this.decomposition.getFactors();
        CustList<PrimDivisor> factorsOther_ = oth_.getFactors();
        int len_ = factorsThis_.size();
        if (len_ != factorsOther_.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            PrimDivisor pThis_ = factorsThis_.get(i);
            PrimDivisor pOther_ = factorsOther_.get(i);
            if (!MaPrimDivisorNbStruct.eqPrimDiv(pThis_,pOther_)) {
                return false;
            }
        }
        return true;
    }
}
