package code.maths.litteraladv;

import code.maths.matrix.Polynom;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaDividersPolStruct implements MaStruct {
    private final CustList<Polynom> dividers;

    public MaDividersPolStruct(CustList<Polynom> _idBezout) {
        this.dividers = _idBezout;
    }

    public CustList<Polynom> getDividers() {
        return dividers;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqDivs(this, _other);
    }

    @Override
    public String displayRsult() {
        StringList list_ = new StringList(new CollCapacity(dividers.size()));
        for (Polynom d: dividers) {
            list_.add(MaPolynomStruct.displayRsult(d));
        }
        return "("+ StringUtil.join(list_,",")+")";
    }

    static boolean eqDivs(MaDividersPolStruct _this, MaStruct _other) {
        if (!(_other instanceof MaDividersPolStruct)) {
            return false;
        }
        CustList<Polynom> oth_ = ((MaDividersPolStruct) _other).dividers;
        CustList<Polynom> divThis_ = _this.dividers;
        int nbThis_ = divThis_.size();
        if (nbThis_ != oth_.size()) {
            return false;
        }
        for (int i = 0; i < nbThis_; i++) {
            if (!Polynom.eq(divThis_.get(i),oth_.get(i))) {
                return false;
            }
        }
        return true;
    }
}
