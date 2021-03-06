package code.maths.litteraladv;

import code.maths.Rate;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ConstantMaOperation extends LeafMaOperation {
    public ConstantMaOperation(int _indexInEl, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        int begin_ = getOperats().getCst();
        if (!_del.getNbParts().isValidIndex(begin_)) {
            _error.setOffset(StringUtil.getFirstPrintableCharIndex(getOperats().getParts().firstValue()) + getIndexExp());
            return;
        }
        String nb_ = _del.getNbParts().get(begin_).toString().trim();
        if (Rate.isValid(nb_)) {
            setStruct(new MaRateStruct(Rate.newRate(nb_)));
            return;
        }
        _error.setOffset(StringUtil.getFirstPrintableCharIndex(getOperats().getParts().firstValue()) + getIndexExp());
    }
}
