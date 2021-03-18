package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class UnaryMaOperation extends MethodMaOperation {
    public UnaryMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct value_ = MaNumParsers.tryGet(this, 0);
        MaFractPolStruct val_ = MaFractPolStruct.wrapOrNull(value_);
        if (val_ != null && StringUtil.quickEq(getOperats().getOpers().firstValue().trim(), UNARY_MINUS)) {
            setStruct(new MaFractPolStruct(val_.getFractPol().opposNb()));
            return;
        }
        if (value_ instanceof MaMatrixStruct) {
            if (StringUtil.quickEq(getOperats().getOpers().firstValue().trim(), UNARY_MINUS)) {
                setStruct(new MaMatrixStruct(((MaMatrixStruct)value_).getMatrix().minusMatrix()));
                return;
            }
            setStruct(new MaMatrixStruct(((MaMatrixStruct)value_).getMatrix().transposeRef()));
            return;
        }
        if (!(value_ instanceof MaRateStruct)) {
            if (value_ instanceof MaComplexStruct) {
                Complex o_ = ((MaComplexStruct)value_).getComplex();
                if (StringUtil.quickEq(getOperats().getOpers().firstValue().trim(), UNARY_MINUS)) {
                    setStruct(new MaComplexStruct(o_.opposite()));
                } else {
                    setStruct(new MaComplexStruct(o_));
                }
                return;
            }
            _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getOpers(),0));
            return;
        }
        Rate o_ = ((MaRateStruct)value_).getRate();
        if (StringUtil.quickEq(getOperats().getOpers().firstValue().trim(), UNARY_MINUS)) {
            setStruct(new MaRateStruct(o_.opposNb()));
        } else {
            setStruct(new MaRateStruct(o_));
        }
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
