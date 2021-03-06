package code.maths.litteraladv;

import code.maths.LgInt;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaDividersNbStruct implements MaStruct {
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

    @Override
    public String displayRsult() {
        StringList list_ = new StringList(new CollCapacity(dividers.size()));
        for (LgInt d: dividers) {
            list_.add(d.toNumberString());
        }
        return "("+ StringUtil.join(list_,",")+")";
    }

    static boolean eqDivs(MaDividersNbStruct _this, MaStruct _other) {
        if (!(_other instanceof MaDividersNbStruct)) {
            return false;
        }
        CustList<LgInt> oth_ = ((MaDividersNbStruct) _other).dividers;
        return LgInt.eq(_this.dividers,oth_);
    }
}
