package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ConstantMaOperation extends LeafMaOperation {
    public ConstantMaOperation(int _indexInEl, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    static String nb(CustList<StringBuilder> _nbs, int _begin) {
        if (_nbs.isValidIndex(_begin)) {
            return _nbs.get(_begin).toString().trim();
        }
        return "";
    }
    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        int begin_ = getOperats().getCst();
        String nb_ = nb(_del.getNbParts(),begin_);
        if (Rate.isValid(nb_)) {
            setStruct(new MaRateStruct(Rate.newRate(nb_)));
            return;
        }
        _error.setOffset(StringUtil.getFirstPrintableCharIndex(StrTypes.value(getOperats().getParts(),0)) + getIndexExp());
    }
}
