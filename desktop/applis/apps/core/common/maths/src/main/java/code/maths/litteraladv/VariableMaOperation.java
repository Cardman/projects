package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class VariableMaOperation extends LeafMaOperation {
    private final String varName;
    private final int varOffset;
    public VariableMaOperation(int _indexInEl, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m);
        varOffset = _op.getOffset();
        varName = StrTypes.value(_op.getParts(),IndexConstants.FIRST_INDEX).trim();
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        MaStruct val_ = MaNullStruct.def(_conf.getVal(varName));
        if (val_ != MaNullStruct.NULL_VALUE) {
            setStruct(val_);
            return;
        }
        _error.setOffset(getIndexExp()+varOffset);
    }

    String getVarName() {
        return varName;
    }
}
