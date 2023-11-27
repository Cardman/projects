package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class UnaryMaOperation extends MethodMaOperation {

    private static final String UNARY_MINUS = MaOperationsSequence.UNARY_MINUS;

    public UnaryMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        MaStruct value_ = MaNumParsers.tryGet(this, 0);
        MaFractPolStruct val_ = MaFractPolStruct.wrapOrNull(value_);
        if (val_ != null && StringUtil.quickEq(getOps().firstValue().trim(), UNARY_MINUS)) {
            setStruct(new MaFractPolStruct(val_.getFractPol().opposNb()));
            return;
        }
        if (value_ instanceof MaMatrixStruct) {
            if (StringUtil.quickEq(getOps().firstValue().trim(), UNARY_MINUS)) {
                setStruct(new MaMatrixStruct(((MaMatrixStruct)value_).getMatrix().minusMatrix()));
                return;
            }
            setStruct(new MaMatrixStruct(((MaMatrixStruct)value_).getMatrix().transposeRef()));
            return;
        }
        if (!(value_ instanceof MaRateStruct)) {
            if (value_ instanceof MaComplexStruct) {
                Complex o_ = ((MaComplexStruct)value_).getComplex();
                if (StringUtil.quickEq(getOps().firstValue().trim(), UNARY_MINUS)) {
                    setStruct(new MaComplexStruct(o_.opposite()));
                } else {
                    setStruct(new MaComplexStruct(o_));
                }
                return;
            }
            _error.setOffset(getIndexExp()+StrTypes.offset(getOps(),0));
            return;
        }
        Rate o_ = ((MaRateStruct)value_).getRate();
        if (StringUtil.quickEq(getOps().firstValue().trim(), UNARY_MINUS)) {
            setStruct(new MaRateStruct(o_.opposNb()));
        } else {
            setStruct(new MaRateStruct(o_));
        }
    }

}
