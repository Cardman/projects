package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Edge;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
import code.maths.matrix.Polynom;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbBinFctMaOperation extends MethodMaOperation {
    private String oper = "";
    private int operOff;

    public SymbBinFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(oper,"/")) {
            procQuot(_error);
        }
        if (StringUtil.quickEq(oper,"%")) {
            procMod(_error);
        }
        if (StringUtil.quickEq(oper,"^")) {
            procPower(_error);
        }
        if (StringUtil.quickEq(oper,"<=")) {
            procParmi(_error);
        }
        if (StringUtil.quickEq(oper,"/%")) {
            procBezout(_error);
        }
        if (StringUtil.quickEq(oper,".")) {
            procPoint(_error);
        }
        if (StringUtil.quickEq(oper,"-")) {
            procEdge(_error);
        }
        if (StringUtil.quickEq(oper,"^^")) {
            procEdgesNotContains(_error);
        }
        if (StringUtil.quickEq(oper,"^^^")) {
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
                _error.setOffset(getIndexExp()+operOff);
            } else {
                setStruct(new MaRateStruct(new Rate(LgInt.divide(quot_,div_))));
            }
            return;
        }
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            Polynom quot_= fractPols_.first().getFractPol().intPart();
            Polynom div_= fractPols_.last().getFractPol().intPart();
            if (div_.isZero()) {
                _error.setOffset(getIndexExp()+operOff);
            } else {
                setStruct(new MaPolynomStruct(quot_.dividePolynom(div_)));
            }
            return;
        }
        CustList<MaCustLineStruct> lines_ = tryGetAllAsLine(this);
        if (lines_.size() == 2) {
            CustLine first_ = lines_.first().getLine();
            CustLine second_ = lines_.last().getLine();
            if (!first_.getMatrix(second_).det().isZero()) {
                setStruct(new MaRatePointStruct(first_.intersect(second_)));
                return;
            }
            _error.setOffset(getIndexExp()+operOff);
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
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
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            procModPol(_error, fractPols_);
            return;
        }
        if (fractPols_.size() == 2) {
            procModFract(_error, fractPols_);
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procModInt(MaError _error, MaRateStruct _first, MaRateStruct _second) {
        LgInt quot_= _first.getRate().intPart();
        LgInt div_= _second.getRate().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+operOff);
        } else {
            setStruct(new MaRateStruct(new Rate(LgInt.remain(quot_,div_))));
        }
    }

    private void procModRate(MaError _error, MaRateStruct _first, MaRateStruct _second) {
        Rate quot_= _first.getRate();
        Rate div_= _second.getRate();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+operOff);
        } else {
            setStruct(new MaRateStruct(Rate.minus(quot_,Rate.multiply(new Rate(Rate.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procModPol(MaError _error, CustList<MaFractPolStruct> _fractPols) {
        Polynom quot_= _fractPols.first().getFractPol().intPart();
        Polynom div_= _fractPols.last().getFractPol().intPart();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+operOff);
        } else {
            setStruct(new MaPolynomStruct(quot_.remainPolynom(div_)));
        }
    }

    private void procModFract(MaError _error, CustList<MaFractPolStruct> _fractPols) {
        FractPol quot_= _fractPols.first().getFractPol();
        FractPol div_= _fractPols.last().getFractPol();
        if (div_.isZero()) {
            _error.setOffset(getIndexExp()+operOff);
        } else {
            setStruct(new MaFractPolStruct(FractPol.minus(quot_,FractPol.multiply(new FractPol(FractPol.divide(quot_,div_).intPart()),div_))));
        }
    }

    private void procPower(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates();
        if (rates_.size() == 2) {
            procPowerRate(_error, rates_);
            return;
        }
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(val_);
        if (fract_ != null && power_ instanceof MaRateStruct && ((MaRateStruct)power_).getRate().isInteger()) {
            procPowerFract(_error, rates_, fract_);
            return;
        }
        if (val_ instanceof MaMatrixStruct && power_ instanceof MaRateStruct && ((MaRateStruct)power_).getRate().isInteger()) {
            procPowerMatrix(_error, rates_, (MaMatrixStruct) val_);
            return;
        }
        CustList<MaCustLineStruct> lines_ = tryGetAllAsLine(this);
        if (lines_.size() == 2) {
            CustLine first_ = lines_.first().getLine();
            CustLine second_ = lines_.last().getLine();
            setStruct(MaBoolStruct.of(!first_.getMatrix(second_).det().isZero()));
            return;
        }
        CustList<MaEdgeStruct> edges_ = tryGetAllAsEdge(this);
        if (edges_.size() == 2) {
            Edge first_ = edges_.first().getEdge();
            Edge second_ = edges_.last().getEdge();
            setStruct(MaBoolStruct.of(first_.intersect(second_)));
            return;
        }
        procContainsPt(_error, val_, power_);
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
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPowerMatrix(MaError _error, CustList<MaRateStruct> _rates, MaMatrixStruct _val) {
        Matrix matrix_ = _val.getMatrix();
        Rate exposant_= _rates.last().getRate();
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
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void procPowerFract(MaError _error, CustList<MaRateStruct> _rates, MaFractPolStruct _fract) {
        FractPol base_= _fract.getFractPol();
        Rate exposant_= _rates.last().getRate();
        if (base_.isZero() && !exposant_.isZeroOrGt()) {
            _error.setOffset(getIndexExp()+operOff);
        } else {
            setStruct(new MaFractPolStruct(base_.powNb(exposant_.intPart())));
        }
    }

    private void procPowerRate(MaError _error, CustList<MaRateStruct> _rates) {
        Rate base_= _rates.first().getRate();
        Rate exposant_= _rates.last().getRate();
        procPowerRate(_error, base_, exposant_);
    }

    private void procPowerRate(MaError _error, Rate _base, Rate _exposant) {
        if (_base.isZero() && !_exposant.isZeroOrGt()) {
            _error.setOffset(getIndexExp()+operOff);
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
        CustList<MaFractPolStruct> fractPols_ = tryGetFracPols();
        if (areTwoPols(fractPols_)) {
            Polynom quot_= fractPols_.first().getFractPol().intPart();
            Polynom div_= fractPols_.last().getFractPol().intPart();
            setStruct(new MaBezoutPolStruct(Polynom.idBezoutPgcdPpcm(quot_,div_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
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
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPoint(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates();
        if (rates_.size() == 2) {
            Rate x_ = rates_.first().getRate();
            Rate y_ = rates_.last().getRate();
            setStruct(new MaRatePointStruct(new RatePoint(x_,y_)));
            return;
        }
        MaStruct first_ = MaNumParsers.tryGet(this, 0);
        MaStruct second_ = MaNumParsers.tryGet(this, 1);
        if (first_ instanceof MaRatePointStruct && second_ instanceof MaRatePointStruct) {
            RatePoint x_ = ((MaRatePointStruct) first_).getPoint();
            RatePoint y_ = ((MaRatePointStruct) second_).getPoint();
            setStruct(new MaRateStruct(x_.sqDist(y_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
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
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procEdgesNotContains(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaStruct power_ = MaNumParsers.tryGet(this, 1);
        if (val_ instanceof MaComplexStruct && power_ instanceof MaRateStruct) {
            procComplexPower((MaComplexStruct)val_,(MaRateStruct)power_,_error);
            return;
        }
        CustList<MaEdgeStruct> edges_ = tryGetAllAsEdge(this);
        if (edges_.size() == 2) {
            Edge first_ = edges_.first().getEdge();
            Edge second_ = edges_.last().getEdge();
            setStruct(MaBoolStruct.of(first_.intersectNotContains(second_)));
            return;
        }
        if (val_ instanceof MaPolygonStruct && power_ instanceof MaRatePointStruct) {
            Polygon line_ = ((MaPolygonStruct)val_).getPolygon();
            RatePoint point_ = ((MaRatePointStruct)power_).getPoint();
            setStruct(MaBoolStruct.of(line_.containsInside(point_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procComplexPower(MaComplexStruct _base, MaRateStruct _exp,MaError _error) {
        Complex val_ = _base.getComplex();
        Rate expo_ = _exp.getRate();
        if (val_.getImag().isZero()) {
            procPowerRate(_error,val_.getReal(), expo_);
            return;
        }
        if (!expo_.isInteger()) {
            _error.setOffset(getIndexExp()+operOff);
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
        CustList<MaEdgeStruct> edges_ = tryGetAllAsEdge(this);
        if (edges_.size() == 2) {
            Edge first_ = edges_.first().getEdge();
            Edge second_ = edges_.last().getEdge();
            setStruct(MaBoolStruct.of(first_.intersectNotContainsBound(second_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }
    CustList<MaRateStruct> tryGetRates() {
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            if (str_ instanceof MaRateStruct) {
                rates_.add((MaRateStruct)str_);
            }
        }
        return rates_;
    }
    CustList<MaFractPolStruct> tryGetFracPols() {
        CustList<MaFractPolStruct> rates_ = new CustList<MaFractPolStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            MaFractPolStruct wr_ = MaFractPolStruct.wrapOrNull(str_);
            if (wr_ != null) {
                rates_.add(wr_);
            }
        }
        return rates_;
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        operOff = vs_.lastKey();
        oper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
