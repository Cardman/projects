package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NumericMaOperation extends MethodMaOperation {
    public NumericMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        String oper_ = StrTypes.value(getOps(),0).trim();
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            processRates(_error, oper_, (MaRateStruct) first_, (MaRateStruct) second_);
            return;
        }
        if (first_ instanceof MaComplexStruct && second_ instanceof MaComplexStruct) {
            processComplexes(_error, oper_, (MaComplexStruct) first_, (MaComplexStruct) second_);
            return;
        }
        MaFractPolStruct wrOne_ = MaFractPolStruct.wrapOrNull(first_);
        MaFractPolStruct wrTwo_ = MaFractPolStruct.wrapOrNull(second_);
        if (wrOne_ != null && wrTwo_ != null) {
            processFracts(_error, oper_, wrOne_,wrTwo_);
            return;
        }
        if (first_ instanceof MaMatrixStruct && second_ instanceof MaMatrixStruct) {
            processMatrixs(_error, oper_,(MaMatrixStruct)first_,(MaMatrixStruct)second_);
            return;
        }
        _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
    }

    private void processRates(MaError _error, String _oper, MaRateStruct _first, MaRateStruct _second) {
        if (StringUtil.quickEq(_oper, "+")) {
            setStruct(new MaRateStruct(Rate.plus(_first.getRate(), _second.getRate())));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            setStruct(new MaRateStruct(Rate.minus(_first.getRate(), _second.getRate())));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            setStruct(new MaRateStruct(Rate.multiply(_first.getRate(), _second.getRate())));
            return;
        }
        if (_second.getRate().isZero()) {
            _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
            return;
        }
        setStruct(new MaRateStruct(Rate.divide(_first.getRate(), _second.getRate())));
    }

    private void processComplexes(MaError _error, String _oper, MaComplexStruct _first, MaComplexStruct _second) {
        if (StringUtil.quickEq(_oper, "+")) {
            setStruct(new MaComplexStruct(_first.getComplex().add(_second.getComplex())));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            setStruct(new MaComplexStruct(_first.getComplex().minus(_second.getComplex())));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            setStruct(new MaComplexStruct(_first.getComplex().multiply(_second.getComplex())));
            return;
        }
        if (_second.getComplex().isZero()) {
            _error.setOffset(getIndexExp() + getOps().firstKey());
            return;
        }
        setStruct(new MaComplexStruct(_first.getComplex().divide(_second.getComplex())));
    }
    private void processFracts(MaError _error, String _oper, MaFractPolStruct _first, MaFractPolStruct _second) {
        if (StringUtil.quickEq(_oper, "+")) {
            setStruct(new MaFractPolStruct(FractPol.plus(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            setStruct(new MaFractPolStruct(FractPol.minus(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            setStruct(new MaFractPolStruct(FractPol.multiply(_first.getFractPol(),_second.getFractPol())));
            return;
        }
        if (_second.getFractPol().isZero()) {
            _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
            return;
        }
        setStruct(new MaFractPolStruct(FractPol.divide(_first.getFractPol(),_second.getFractPol())));
    }

    private void processMatrixs(MaError _error, String _oper, MaMatrixStruct _first, MaMatrixStruct _second) {
        Matrix f_ = _first.getMatrix();
        Matrix s_ = _second.getMatrix();
        if (StringUtil.quickEq(_oper, "+")) {
            if (diffSizes(f_, s_)) {
                _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
                return;
            }
            setStruct(new MaMatrixStruct(f_.addMatrix(s_)));
            return;
        }
        if (StringUtil.quickEq(_oper, "-")) {
            if (diffSizes(f_, s_)) {
                _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
                return;
            }
            setStruct(new MaMatrixStruct(f_.minusMatrix(s_)));
            return;
        }
        if (StringUtil.quickEq(_oper, "*")) {
            if (f_.nbCols() != s_.nbLines()) {
                _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
                return;
            }
            setStruct(new MaMatrixStruct(f_.multMatrix(s_)));
            return;
        }
        if (f_.nbCols() != s_.nbCols()) {
            _error.setOffset(getIndexExp() + StrTypes.offset(getOps(),0));
            return;
        }
        setStruct(new MaMatrixStruct(f_.multMatrix(s_.inv())));
    }

    private static boolean diffSizes(Matrix _f, Matrix _s) {
        return _f.nbLines() != _s.nbLines() || _f.nbCols() != _s.nbCols();
    }

}
