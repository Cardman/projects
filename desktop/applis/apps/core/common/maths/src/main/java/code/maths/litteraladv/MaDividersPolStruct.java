package code.maths.litteraladv;

import code.maths.matrix.Polynom;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaDividersPolStruct implements MaAddonStruct {
    private final CustList<Polynom> dividers;

    public MaDividersPolStruct(CustList<Polynom> _idBezout) {
        this.dividers = _idBezout;
    }

    public CustList<Polynom> getDividers() {
        return dividers;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaDividersPolStruct)) {
            return false;
        }
        return eqPols(MaComparePolynom.sorted(dividers),MaComparePolynom.sorted(((MaDividersPolStruct)_other).dividers));
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
        return eqPols(_this.dividers, oth_);
    }

    static boolean eqPols(CustList<Polynom> _divThis, CustList<Polynom> _oth) {
        int nbThis_ = _divThis.size();
        if (nbThis_ != _oth.size()) {
            return false;
        }
        for (int i = 0; i < nbThis_; i++) {
            if (!Polynom.eq(_divThis.get(i), _oth.get(i))) {
                return false;
            }
        }
        return true;
    }
}
