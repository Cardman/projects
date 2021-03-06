package code.maths.litteraladv;

import code.util.StringMap;
import code.util.core.IndexConstants;

public final class VariableMaOperation extends LeafMaOperation {
    private final String varName;
    public VariableMaOperation(int _indexInEl, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        varName = getOperats().getParts().getValue(IndexConstants.FIRST_INDEX).trim();
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct val_ = _conf.getVal(varName);
        if (val_ != null) {
            setStruct(val_);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    String getVarName() {
        return varName;
    }
}
