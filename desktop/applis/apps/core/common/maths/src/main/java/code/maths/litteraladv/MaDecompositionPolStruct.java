package code.maths.litteraladv;

import code.maths.matrix.RootPol;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaDecompositionPolStruct implements MaStruct {
    private final CustList<RootPol> decomposition;

    public MaDecompositionPolStruct(CustList<RootPol> _decomposition) {
        this.decomposition = _decomposition;
    }

    public CustList<RootPol> getDecomposition() {
        return decomposition;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqDecomposition(this, _other);
    }

    @Override
    public String displayRsult() {
        String out_ = "";
        StringList list_ = new StringList(new CollCapacity(decomposition.size()));
        for (RootPol d: decomposition) {
            list_.add("["+d.getValue().toNumberString()+" "+d.getDegree()+"]");
        }
        return out_+"["+ StringUtil.join(list_,"")+"]";
    }

    static boolean eqDecomposition(MaDecompositionPolStruct _this, MaStruct _other) {
        if (!(_other instanceof MaDecompositionPolStruct)) {
            return false;
        }
        CustList<RootPol> oth_ = ((MaDecompositionPolStruct) _other).decomposition;
        CustList<RootPol> factorsThis_ = _this.decomposition;
        int len_ = factorsThis_.size();
        if (len_ != oth_.size()) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            RootPol pThis_ = factorsThis_.get(i);
            RootPol pOther_ = oth_.get(i);
            if (!MaPrimDivisorPolStruct.eqPrimDiv(pThis_,pOther_)) {
                return false;
            }
        }
        return true;
    }
}
