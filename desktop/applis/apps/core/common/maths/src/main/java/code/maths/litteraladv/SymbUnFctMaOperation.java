package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.Decomposition;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.geo.Triangle;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbUnFctMaOperation extends MethodMaOperation {
    private String oper = "";
    private int operOff;
    protected SymbUnFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq("-", oper)) {
            procSgn(_error);
        }
        if (StringUtil.quickEq("|", oper)) {
            procAbs(_error);
        }
        procComplexParts(_error);
        if (StringUtil.quickEq("/0", oper)) {
            procNum(_error);
        }
        if (StringUtil.quickEq("/1", oper)) {
            procDen(_error);
        }
        if (StringUtil.quickEq("0/", oper)) {
            procEnt(_error);
        }
        if (StringUtil.quickEq("1/", oper)) {
            procTroncature(_error);
        }
        if (StringUtil.quickEq("/", oper)) {
            procPrem(_error);
        }
        if (StringUtil.quickEq("||", oper)) {
            procDivs(_error);
        }
        if (StringUtil.quickEq("&", oper)) {
            procDecomp(_error);
        }
        procGravCenter(_error);
        procDerive(_error);
        procIdMat(_error);
    }

    private void procGravCenter(MaError _error) {
        if (StringUtil.quickEq("*", oper)) {
            CustList<MaComplexStruct> compl_ = tryGetAllAsComplex(this);
            if (compl_.size() == 1) {
                setStruct(new MaComplexStruct(compl_.first().getComplex().conjug()));
                return;
            }
            CustList<MaPolygonStruct> pols_ = tryGetAllAsPolygon(this);
            if (pols_.size() != 1) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            CustList<RatePoint> pts_ = pols_.first().getPolygon().getPoints();
            if (pts_.size() != 3) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            Triangle tri_ = new Triangle(pts_.get(0),pts_.get(1),pts_.get(2));
            setStruct(new MaRatePointStruct(tri_.getGravityCenter()));
        }
    }

    private void procIdMat(MaError _error) {
        if (StringUtil.quickEq("#", oper)) {
            MaStruct valop_ = MaNumParsers.tryGet(this, 0);
            MaRateStruct intVal_ = asInt(valop_);
            if (intVal_ != null) {
                LgInt lgInt_ = intVal_.getRate().intPart();
                int val_ = (int) lgInt_.ll();
                if (val_ <= 0) {
                    _error.setOffset(getIndexExp()+operOff);
                } else {
                    setStruct(new MaMatrixStruct(Matrix.buildId(val_)));
                }
                return;
            }
            CustList<MaPolygonStruct> polyg_ = tryGetAllAsPolygon(this);
            if (polyg_.size() == 1){
                CustList<RatePoint> pts_ = polyg_.first().getPolygon().getPoints();
                if (pts_.size() != 3) {
                    _error.setOffset(getIndexExp()+operOff);
                    return;
                }
                Triangle tri_ = new Triangle(pts_.get(0),pts_.get(1),pts_.get(2));
                if (tri_.com().isZero()) {
                    _error.setOffset(getIndexExp()+operOff);
                    return;
                }
                setStruct(new MaRatePointStruct(tri_.getCircumCenter()));
                return;
            }
            _error.setOffset(getIndexExp()+operOff);
        }
    }
    private void procDerive(MaError _error) {
        int count_ = containsOnlySimpleQuotes(oper);
        if (count_ > 0) {
            procDerive(_error,count_);
        }
    }

    private void procSgn(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(nb_.signum()));
            return;
        }
        CustList<MaDecompositionNbStruct> decomp_ = tryGetDecompNb(this);
        if (decomp_.size() == 1) {
            Decomposition decomposition_ = decomp_.first().getDecomposition();
            if (decomposition_.getFactors().isEmpty()) {
                setStruct(new MaRateStruct(Rate.zero()));
                return;
            }
            if (decomposition_.isPositive()) {
                setStruct(new MaRateStruct(Rate.one()));
                return;
            }
            setStruct(new MaRateStruct(Rate.minusOne()));
            return;
        }
        CustList<MaMatrixStruct> all_ = tryGetAllAsMatrix(this);
        if (all_.size() == 1) {
            Matrix matrix_ = all_.first().getMatrix();
            setStruct(new MaMatrixStruct(matrix_.passVects()));
            return;
        }
        CustList<MaDelaunayStruct> dels_ = tryGetAllAsDelaunay(this);
        if (dels_.size() == 1) {
            setStruct(MaBoolStruct.of(dels_.first().isWithMids()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procAbs(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(nb_.absNb()));
            return;
        }
        CustList<MaMatrixStruct> all_ = tryGetAllAsMatrix(this);
        if (all_.size() == 1) {
            Matrix matrix_ = all_.first().getMatrix();
            setStruct(new MaRateStruct(matrix_.det()));
            return;
        }
        CustList<MaPolygonStruct> pols_ = tryGetAllAsPolygon(this);
        if (pols_.size() == 1) {
            CustList<RatePoint> pts_ = pols_.first().getPolygon().getPoints();
            if (pts_.size() != 3) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            Triangle tri_ = new Triangle(pts_.get(0), pts_.get(1), pts_.get(2));
            CustLine euler_ = tri_.euler();
            if (!euler_.isDefined()) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            setStruct(new MaCustLineStruct(euler_));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procComplexParts(MaError _error) {
        if (!StringUtil.quickEq("//", oper)) {
            return;
        }
        CustList<MaComplexStruct> cpl_ = tryGetAllAsComplex(this);
        if (cpl_.size() == 1) {
            Complex complex_ = cpl_.first().getComplex();
            setStruct(new MaRateListStruct(new CustList<Rate>(complex_.getReal(),complex_.getImag())));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }
    private void procNum(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(new Rate(nb_.getNumerator())));
            return;
        }
        MaFractPolStruct fract_ = asFract(val_);
        if (fract_ != null) {
            FractPol nb_= fract_.getFractPol();
            setStruct(new MaPolynomStruct(nb_.getNumerator()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procDen(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(new Rate(nb_.getDenominator())));
            return;
        }
        MaFractPolStruct fract_ = asFract(val_);
        if (fract_ != null) {
            FractPol nb_= fract_.getFractPol();
            setStruct(new MaPolynomStruct(nb_.getDenominator()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procEnt(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(new Rate(nb_.intPart())));
            return;
        }
        MaFractPolStruct fract_ = asFract(val_);
        if (fract_ != null) {
            FractPol nb_= fract_.getFractPol();
            setStruct(new MaPolynomStruct(nb_.intPart()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procTroncature(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct rt_ = asRate(val_);
        if (rt_ != null) {
            Rate nb_= rt_.getRate();
            setStruct(new MaRateStruct(new Rate(nb_.toLgInt())));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPrem(MaError _error) {
        MaStruct valop_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct intVal_ = asInt(valop_);
        if (intVal_ != null) {
            Rate nb_= intVal_.getRate();
            setStruct(MaBoolStruct.of(nb_.intPart().isPrime()));
            return;
        }
        CustList<MaMatrixStruct> all_ = tryGetAllAsMatrix(this);
        if (all_.size() == 1) {
            Matrix matrix_ = all_.first().getMatrix();
            setStruct(new MaRateStruct(matrix_.trace()));
            return;
        }
        CustList<MaPolygonStruct> pols_ = tryGetAllAsPolygon(this);
        if (pols_.size() == 1) {
            procPolygon(pols_);
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPolygon(CustList<MaPolygonStruct> _pols) {
        CustList<RatePoint> pts_ = _pols.first().getPolygon().getPoints();
        if (pts_.size() != 3) {
            setStruct(MaBoolStruct.of(false));
            return;
        }
        Triangle tri_ = new Triangle(pts_.get(0), pts_.get(1), pts_.get(2));
        if (tri_.com().isZero()) {
            setStruct(MaBoolStruct.of(false));
            return;
        }
        setStruct(MaBoolStruct.of(true));
    }

    private void procDivs(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct intVal_ = asInt(val_);
        if (intVal_ != null) {
            Rate nb_= intVal_.getRate();
            setStruct(new MaDividersNbStruct(nb_.intPart().getDividers()));
            return;
        }
        MaFractPolStruct polVal_ = asPol(val_);
        if (polVal_ != null) {
            FractPol nb_= polVal_.getFractPol();
            setStruct(new MaDividersPolStruct(nb_.intPart().factor()));
            return;
        }
        CustList<MaMatrixStruct> all_ = tryGetAllAsMatrix(this);
        if (all_.size() == 1) {
            Matrix matrix_ = all_.first().getMatrix();
            setStruct(new MaRateStruct(new Rate(matrix_.quickRank())));
            return;
        }
        CustList<MaPolygonStruct> polyg_ = tryGetAllAsPolygon(this);
        if (polyg_.size() == 1) {
            Polygon polygon_ = polyg_.first().getPolygon();
            setStruct(new MaPolygonStruct(polygon_.getConvexHull()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procDecomp(MaError _error) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaRateStruct intVal_ = asInt(val_);
        if (intVal_ != null) {
            Rate nb_= intVal_.getRate();
            setStruct(new MaDecompositionNbStruct(nb_.intPart().decompoPrim()));
            return;
        }
        MaFractPolStruct polVal_ = asPol(val_);
        if (polVal_ != null) {
            FractPol nb_= polVal_.getFractPol();
            setStruct(new MaDecompositionPolStruct(nb_.intPart().racines()));
            return;
        }
        CustList<MaMatrixStruct> all_ = tryGetAllAsMatrix(this);
        if (all_.size() == 1) {
            Matrix matrix_ = all_.first().getMatrix();
            setStruct(new MaPolynomStruct(matrix_.polCaract()));
            return;
        }
        CustList<MaPolygonStruct> pols_ = tryGetAllAsPolygon(this);
        if (pols_.size() == 1) {
            CustList<Triangle> tris_ = pols_.first().getPolygon().getTriangles();
            CustList<Polygon> conv_ = Polygon.toPolygons(tris_);
            setStruct(new MaListPolygonStruct(conv_));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procDerive(MaError _error, int _der) {
        MaStruct val_ = MaNumParsers.tryGet(this, 0);
        MaFractPolStruct fract_ = asFract(val_);
        if (fract_ != null) {
            FractPol nb_= fract_.getFractPol();
            for (int i = 0; i < _der; i++) {
                nb_ = nb_.derivee();
            }
            setStruct(new MaFractPolStruct(nb_));
            return;
        }
        CustList<MaPolygonStruct> polyg_ = tryGetAllAsPolygon(this);
        if (polyg_.size() == 1) {
            Polygon polygon_ = polyg_.first().getPolygon();
            setStruct(new MaPolygonStruct(polygon_.getStrictHull()));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
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
