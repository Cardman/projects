package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Edge;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
import code.maths.matrix.Polynom;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbBinFctMaOperation extends AbsSymbFixMaOperation {

    public SymbBinFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, int _off, String _ope) {
        super(_index, _indexChild, _m, _op, _off, _ope);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(getOper(), QUOT)) {
            procQuot(_error);
        }
        if (StringUtil.quickEq(getOper(), MOD)) {
            procMod(_error);
        }
        if (StringUtil.quickEq(getOper(), POW)) {
            procPower(_error);
        }
        if (StringUtil.quickEq(getOper(), PARMI)) {
            procParmi(_error);
        }
        if (StringUtil.quickEq(getOper(), BEZOUT)) {
            procBezout(_error);
        }
        if (StringUtil.quickEq(getOper(), POINT)) {
            procPoint(_error);
        }
        if (StringUtil.quickEq(getOper(), EDGE)) {
            procEdge(_error);
        }
        if (StringUtil.quickEq(getOper(), FIRST_INTER)) {
            procEdgesNotContains(_error);
        }
        if (StringUtil.quickEq(getOper(), SECOND_INTER)) {
            intersectNotContainsBound(_error);
        }
    }

    private void procQuot(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstInt_ = asInt(firstVal_);
        MaRateStruct secondInt_ = asInt(secondVal_);
        if (firstInt_ != null && secondInt_ != null) {
            LgInt quot_= firstInt_.getRate().intPart();
            LgInt div_= secondInt_.getRate().intPart();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp()+getOperOff());
            } else {
                setStruct(new MaRateStruct(new Rate(LgInt.divide(quot_,div_))));
            }
            return;
        }
        MaFractPolStruct firstPol_ = asPol(firstVal_);
        MaFractPolStruct secondPol_ = asPol(secondVal_);
        if (firstPol_ != null && secondPol_ != null) {
            Polynom quot_= firstPol_.getFractPol().intPart();
            Polynom div_= secondPol_.getFractPol().intPart();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp()+getOperOff());
            } else {
                setStruct(new MaPolynomStruct(quot_.dividePolynom(div_)));
            }
            return;
        }
        MaCustLineStruct lineFirst_ = asLine(firstVal_);
        MaCustLineStruct lineSecond_ = asLine(secondVal_);
        if (lineFirst_ != null && lineSecond_ != null) {
            CustLine first_ = lineFirst_.getLine();
            CustLine second_ = lineSecond_.getLine();
            if (!first_.getMatrix(second_).det().isZero()) {
                setStruct(new MaRatePointStruct(first_.intersect(second_)));
                return;
            }
            _error.setOffset(getIndexExp()+getOperOff());
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procMod(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstInt_ = asInt(firstVal_);
        MaRateStruct secondInt_ = asInt(secondVal_);
        if (firstInt_ != null && secondInt_ != null) {
            procModInt(_error, firstInt_, secondInt_);
            return;
        }
        MaRateStruct firstRate_ = asRate(firstVal_);
        MaRateStruct secondRate_ = asRate(secondVal_);
        if (firstRate_ != null && secondRate_ != null) {
            procModRate(_error, firstRate_, secondRate_);
            return;
        }
        MaFractPolStruct firstPol_ = asPol(firstVal_);
        MaFractPolStruct secondPol_ = asPol(secondVal_);
        if (firstPol_ != null && secondPol_ != null) {
            procModPol(_error, firstPol_, secondPol_);
            return;
        }
        MaFractPolStruct firstFract_ = asFract(firstVal_);
        MaFractPolStruct secondFract_ = asFract(secondVal_);
        if (firstFract_ != null && secondFract_ != null) {
            procModFract(_error, firstFract_, secondFract_);
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procModInt(MaError _error, MaRateStruct _first, MaRateStruct _second) {
        LgInt quot_= _first.getRate().intPart();
        LgInt div_= _second.getRate().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaRateStruct(new Rate(LgInt.remain(quot_,div_))));
        }
    }

    private void procModRate(MaError _error, MaRateStruct _first, MaRateStruct _second) {
        Rate quot_= _first.getRate();
        Rate div_= _second.getRate();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaRateStruct(Rate.minus(quot_,Rate.multiply(new Rate(Rate.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procModPol(MaError _error, MaFractPolStruct _first, MaFractPolStruct _second) {
        Polynom quot_= _first.getFractPol().intPart();
        Polynom div_= _second.getFractPol().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaPolynomStruct(quot_.remainPolynom(div_)));
        }
    }

    private void procModFract(MaError _error, MaFractPolStruct _first, MaFractPolStruct _second) {
        FractPol quot_= _first.getFractPol();
        FractPol div_= _second.getFractPol();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaFractPolStruct(FractPol.minus(quot_,FractPol.multiply(new FractPol(FractPol.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procPower(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(val_);
        MaRateStruct secondRate_ = asRate(power_);
        if (firstRate_ != null && secondRate_ != null) {
            procPowerRate(_error, firstRate_, secondRate_);
            return;
        }
        MaRateStruct pwInt_ = asInt(power_);
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(val_);
        if (fract_ != null && pwInt_ != null) {
            procPowerFract(_error, fract_, pwInt_);
            return;
        }
        if (val_ instanceof MaMatrixStruct && pwInt_ != null) {
            procPowerMatrix(_error, (MaMatrixStruct) val_, pwInt_);
            return;
        }
        procGeo(_error, val_, power_);
    }

    private void procGeo(MaError _error, MaStruct _first, MaStruct _second) {
        MaCustLineStruct lineFirst_ = asLine(_first);
        MaCustLineStruct lineSecond_ = asLine(_second);
        if (lineFirst_ != null && lineSecond_ != null) {
            CustLine first_ = lineFirst_.getLine();
            CustLine second_ = lineSecond_.getLine();
            setStruct(MaBoolStruct.of(!first_.getMatrix(second_).det().isZero()));
            return;
        }
        MaEdgeStruct edgeFirst_ = asEdge(_first);
        MaEdgeStruct edgeSecond_ = asEdge(_second);
        if (edgeFirst_ != null && edgeSecond_ != null) {
            Edge first_ = edgeFirst_.getEdge();
            Edge second_ = edgeSecond_.getEdge();
            setStruct(MaBoolStruct.of(first_.intersect(second_)));
            return;
        }
        procContainsPt(_error, _first, _second);
    }

    private void procContainsPt(MaError _error, MaStruct _val, MaStruct _power) {
        if (_val instanceof MaEdgeStruct && _power instanceof MaRatePointStruct) {
            Edge edge_ = ((MaEdgeStruct) _val).getEdge();
            RatePoint point_ = ((MaRatePointStruct) _power).getPoint();
            setStruct(MaBoolStruct.of(edge_.containsPoint(point_)));
            return;
        }
        if (_val instanceof MaCustLineStruct && _power instanceof MaRatePointStruct) {
            CustLine line_ = ((MaCustLineStruct) _val).getLine();
            RatePoint point_ = ((MaRatePointStruct) _power).getPoint();
            setStruct(MaBoolStruct.of(line_.containsPoint(point_)));
            return;
        }
        if (_val instanceof MaPolygonStruct && _power instanceof MaRatePointStruct) {
            Polygon line_ = ((MaPolygonStruct) _val).getPolygon();
            RatePoint point_ = ((MaRatePointStruct) _power).getPoint();
            setStruct(MaBoolStruct.of(line_.containsInsideConvexHull(point_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procPowerMatrix(MaError _error, MaMatrixStruct _val, MaRateStruct _second) {
        Matrix matrix_ = _val.getMatrix();
        Rate exposant_= _second.getRate();
        if (matrix_.isSquare()) {
            if (!exposant_.isZeroOrGt()) {
                setStruct(new MaMatrixStruct(matrix_.inv().power(exposant_.absNb().intPart())));
            } else {
                setStruct(new MaMatrixStruct(matrix_.power(exposant_.intPart())));
            }
        } else if (Rate.eq(Rate.one(),exposant_.absNb())){
            if (!exposant_.isZeroOrGt()) {
                setStruct(new MaMatrixStruct(matrix_.inv()));
            } else {
                setStruct(_val);
            }
        } else {
            _error.setOffset(getIndexExp()+getOperOff());
        }
    }

    private void procPowerFract(MaError _error, MaFractPolStruct _fract, MaRateStruct _second) {
        FractPol base_= _fract.getFractPol();
        Rate exposant_= _second.getRate();
        if (base_.isZero() && !exposant_.isZeroOrGt()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaFractPolStruct(base_.powNb(exposant_.intPart())));
        }
    }

    private void procPowerRate(MaError _error, MaRateStruct _first, MaRateStruct _second) {
        Rate base_= _first.getRate();
        Rate exposant_= _second.getRate();
        procPowerRate(_error, base_, exposant_);
    }

    private void procPowerRate(MaError _error, Rate _base, Rate _exposant) {
        if (_base.isZero() && !_exposant.isZeroOrGt()) {
            _error.setOffset(getIndexExp()+getOperOff());
        } else {
            setStruct(new MaRateStruct(Rate.powNb(_base, _exposant)));
        }
    }

    private void procBezout(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstInt_ = asInt(firstVal_);
        MaRateStruct secondInt_ = asInt(secondVal_);
        if (firstInt_ != null && secondInt_ != null) {
            LgInt quot_= firstInt_.getRate().intPart();
            LgInt div_= secondInt_.getRate().intPart();
            setStruct(new MaBezoutNbStruct(LgInt.identiteBezoutPgcdPpcm(quot_,div_)));
            return;
        }
        MaFractPolStruct firstPol_ = asPol(firstVal_);
        MaFractPolStruct secondPol_ = asPol(secondVal_);
        if (firstPol_ != null && secondPol_ != null) {
            Polynom quot_= firstPol_.getFractPol().intPart();
            Polynom div_= secondPol_.getFractPol().intPart();
            setStruct(new MaBezoutPolStruct(Polynom.idBezoutPgcdPpcm(quot_,div_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procParmi(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstInt_ = asInt(firstVal_);
        MaRateStruct secondInt_ = asInt(secondVal_);
        if (firstInt_ != null && secondInt_ != null) {
            Rate nbOne_= firstInt_.getRate();
            Rate nbTwo_= secondInt_.getRate();
            if (nbOne_.isZeroOrGt()&&nbTwo_.isZeroOrGt()) {
                setStruct(new MaRateStruct(new Rate(LgInt.among(nbOne_.intPart(),nbTwo_.intPart()))));
                return;
            }
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procPoint(MaError _error) {
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(first_);
        MaRateStruct secondRate_ = asRate(second_);
        if (firstRate_ != null && secondRate_ != null) {
            Rate x_ = firstRate_.getRate();
            Rate y_ = secondRate_.getRate();
            setStruct(new MaRatePointStruct(new RatePoint(x_,y_)));
            return;
        }
        if (first_ instanceof MaRatePointStruct && second_ instanceof MaRatePointStruct) {
            RatePoint x_ = ((MaRatePointStruct) first_).getPoint();
            RatePoint y_ = ((MaRatePointStruct) second_).getPoint();
            setStruct(new MaRateStruct(x_.sqDist(y_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procEdge(MaError _error) {
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        if (first_ instanceof MaRatePointStruct && second_ instanceof MaRatePointStruct) {
            RatePoint x_ = ((MaRatePointStruct) first_).getPoint();
            RatePoint y_ = ((MaRatePointStruct) second_).getPoint();
            setStruct(new MaEdgeStruct(new Edge(x_,y_)));
            return;
        }
        if (first_ instanceof MaRateStruct && second_ instanceof MaRateStruct) {
            Rate r_ = ((MaRateStruct)first_).getRate();
            Rate i_ = ((MaRateStruct)second_).getRate();
            setStruct(new MaComplexStruct(new Complex(r_,i_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procEdgesNotContains(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        if (val_ instanceof MaComplexStruct && power_ instanceof MaRateStruct) {
            procComplexPower((MaComplexStruct)val_,(MaRateStruct)power_,_error);
            return;
        }
        MaEdgeStruct edgeFirst_ = asEdge(val_);
        MaEdgeStruct edgeSecond_ = asEdge(power_);
        if (edgeFirst_ != null && edgeSecond_ != null) {
            Edge first_ = edgeFirst_.getEdge();
            Edge second_ = edgeSecond_.getEdge();
            setStruct(MaBoolStruct.of(first_.intersectNotContains(second_)));
            return;
        }
        if (val_ instanceof MaPolygonStruct && power_ instanceof MaRatePointStruct) {
            Polygon line_ = ((MaPolygonStruct)val_).getPolygon();
            RatePoint point_ = ((MaRatePointStruct)power_).getPoint();
            setStruct(MaBoolStruct.of(line_.containsInside(point_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

    private void procComplexPower(MaComplexStruct _base, MaRateStruct _exp,MaError _error) {
        Complex val_ = _base.getComplex();
        Rate expo_ = _exp.getRate();
        if (val_.getImag().isZero()) {
            procPowerRate(_error,val_.getReal(), expo_);
            return;
        }
        if (!expo_.isInteger()) {
            _error.setOffset(getIndexExp()+getOperOff());
            return;
        }
        LgInt expInt_ = expo_.intPart();
        if (expInt_.isZero()) {
            setStruct(new MaComplexStruct(new Complex(Rate.one())));
            return;
        }
        if (expInt_.isZeroOrLt()) {
            setStruct(new MaComplexStruct(val_.inv().power(expInt_.absNb())));
            return;
        }
        setStruct(new MaComplexStruct(val_.power(expInt_)));
    }
    private void intersectNotContainsBound(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        MaEdgeStruct edgeFirst_ = asEdge(val_);
        MaEdgeStruct edgeSecond_ = asEdge(power_);
        if (edgeFirst_ != null && edgeSecond_ != null) {
            Edge first_ = edgeFirst_.getEdge();
            Edge second_ = edgeSecond_.getEdge();
            setStruct(MaBoolStruct.of(first_.intersectNotContainsBound(second_)));
            return;
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }
}
