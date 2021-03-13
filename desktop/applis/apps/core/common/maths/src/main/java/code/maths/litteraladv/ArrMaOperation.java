package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.PrimDivisor;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Edge;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.Matrix;
import code.maths.matrix.Polynom;
import code.maths.matrix.RootPol;
import code.maths.matrix.Vect;
import code.maths.montecarlo.EventFreq;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;

public final class ArrMaOperation extends MethodMaOperation {
    public ArrMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaStruct> values_ = tryGetAll();
        if (values_.size() == 1) {
            applyLg(values_);
            return;
        }
        if (values_.size() == 2 && areAllIndexes(values_)) {
            procOneIndex(_error, values_);
            return;
        }
        if (values_.size() == 3 && areAllIndexes(values_)) {
            procTwoIndexes(_error, values_);
            return;
        }
        if (values_.size() == 4 && areAllIndexes(values_)) {
            procThreeIndexes(_error, values_);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void applyLg(CustList<MaStruct> _list) {
        if (isFour(_list.first())) {
            setStruct(new MaRateStruct(new Rate(4)));
            return;
        }
        if (_list.first() instanceof MaDividersNbStruct) {
            setStruct(new MaRateStruct(new Rate(((MaDividersNbStruct) _list.first()).getDividers().size())));
            return;
        }
        if (_list.first() instanceof MaDividersPolStruct) {
            setStruct(new MaRateStruct(new Rate(((MaDividersPolStruct) _list.first()).getDividers().size())));
            return;
        }
        if (_list.first() instanceof MaDecompositionNbStruct) {
            setStruct(new MaRateStruct(new Rate(((MaDecompositionNbStruct) _list.first()).getDecomposition().getFactors().size())));
            return;
        }
        if (_list.first() instanceof MaDecompositionPolStruct) {
            setStruct(new MaRateStruct(new Rate(((MaDecompositionPolStruct) _list.first()).getDecomposition().size())));
            return;
        }
        if (_list.first() instanceof MaRepartitionStruct) {
            setStruct(new MaRateStruct(new Rate(((MaRepartitionStruct) _list.first()).getEvents().size())));
            return;
        }
        if (isDual(_list.first())) {
            setStruct(new MaRateStruct(new Rate(2)));
            return;
        }
        if (_list.first() instanceof MaCustLineStruct) {
            setStruct(new MaRateStruct(new Rate(3)));
            return;
        }
        procDefLg(_list);
    }

    private void procDefLg(CustList<MaStruct> _list) {
        if (_list.first() instanceof MaMonteCarloNumberStruct) {
            setStruct(new MaRateStruct(new Rate(((MaMonteCarloNumberStruct) _list.first()).getLaw().nbEvents())));
            return;
        }
        if (_list.first() instanceof MaRateListStruct) {
            setStruct(new MaRateStruct(new Rate(((MaRateListStruct) _list.first()).getRates().size())));
            return;
        }
        if (_list.first() instanceof MaPolynomStruct) {
            setStruct(new MaRateStruct(new Rate(((MaPolynomStruct) _list.first()).getPolynom().size())));
            return;
        }
        if (_list.first() instanceof MaMatrixStruct) {
            setStruct(new MaRateStruct(new Rate(((MaMatrixStruct) _list.first()).getMatrix().nbLines())));
            return;
        }
        if (_list.first() instanceof MaVectStruct) {
            setStruct(new MaRateStruct(new Rate(((MaVectStruct) _list.first()).getVect().size())));
            return;
        }
        if (_list.first() instanceof MaPolygonStruct) {
            setStruct(new MaRateStruct(new Rate(((MaPolygonStruct) _list.first()).getPolygon().size())));
            return;
        }
        if (_list.first() instanceof MaListPolygonStruct) {
            setStruct(new MaRateStruct(new Rate(((MaListPolygonStruct) _list.first()).getPolygons().size())));
            return;
        }
        setStruct(new MaRateStruct(new Rate(-1)));
    }

    private static boolean isFour(MaStruct _first) {
        return _first instanceof MaBezoutNbStruct || _first instanceof MaBezoutPolStruct;
    }

    private static boolean isDual(MaStruct _first) {
        return _first instanceof MaPrimDivisorNbStruct || _first instanceof MaPrimDivisorPolStruct || _first instanceof MaEventFreqStruct || _first instanceof MaRatePointStruct || _first instanceof MaEdgeStruct;
    }

    private void procTwoIndexes(MaError _error, CustList<MaStruct> _values) {
        if (_values.first() instanceof MaDecompositionNbStruct) {
            procDecomp((MaDecompositionNbStruct) _values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaDecompositionPolStruct) {
            procDecomp((MaDecompositionPolStruct) _values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaRepartitionStruct) {
            procRep((MaRepartitionStruct) _values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaMonteCarloNumberStruct) {
            procLaw((MaMonteCarloNumberStruct) _values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaMatrixStruct) {
            procMatrix((MaMatrixStruct)_values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaPolygonStruct) {
            procPolygon((MaPolygonStruct)_values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaListPolygonStruct) {
            procPolygons((MaListPolygonStruct)_values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        if (_values.first() instanceof MaEdgeStruct) {
            procEdge((MaEdgeStruct)_values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2), _error);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procThreeIndexes(MaError _error, CustList<MaStruct> _values) {
        if (_values.first() instanceof MaListPolygonStruct) {
            procPolygons((MaListPolygonStruct)_values.first(),(MaRateStruct) _values.get(1),(MaRateStruct) _values.get(2),(MaRateStruct) _values.get(3), _error);
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procOneIndex(MaError _error, CustList<MaStruct> _values) {
        if (_values.first() instanceof MaBezoutNbStruct) {
            procArr((MaBezoutNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaBezoutPolStruct) {
            procArr((MaBezoutPolStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDividersNbStruct) {
            procDivs((MaDividersNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDecompositionNbStruct) {
            procDecomp((MaDecompositionNbStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDividersPolStruct) {
            procDivs((MaDividersPolStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaDecompositionPolStruct) {
            procDecomp((MaDecompositionPolStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaRepartitionStruct) {
            procRep((MaRepartitionStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaMonteCarloNumberStruct) {
            procLaw((MaMonteCarloNumberStruct) _values.first(),(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_values.first() instanceof MaEventFreqStruct) {
            procEvt((MaEventFreqStruct) _values.first(),(MaRateStruct) _values.get(1),_error);
            return;
        }
        procOneIndexDef(_error, _values, _values.first());
    }

    private void procOneIndexDef(MaError _error, CustList<MaStruct> _values, MaStruct _first) {
        if (_first instanceof MaPrimDivisorNbStruct) {
            procPrimDivisor((MaPrimDivisorNbStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaPrimDivisorPolStruct) {
            procPrimDivisor((MaPrimDivisorPolStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaRatePointStruct) {
            procPrimDivisor((MaRatePointStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaRateListStruct) {
            procRateList((MaRateListStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaPolynomStruct) {
            procPolynom((MaPolynomStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaMatrixStruct) {
            procMatrix((MaMatrixStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaVectStruct) {
            procVect((MaVectStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaPolygonStruct) {
            procPolygon((MaPolygonStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaListPolygonStruct) {
            procPolygons((MaListPolygonStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaDelaunayStruct) {
            procDelaunay((MaDelaunayStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaCustLineStruct) {
            procLine((MaCustLineStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        if (_first instanceof MaEdgeStruct) {
            procEdge((MaEdgeStruct) _first,(MaRateStruct) _values.get(1), _error);
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private static boolean areAllIndexes(CustList<MaStruct> _values) {
        for (MaStruct m: _values.mid(1)) {
            if (!(m instanceof MaRateStruct)) {
                return false;
            }
            if (!((MaRateStruct)m).getRate().isInteger()) {
                return false;
            }
        }
        return true;
    }
    private void procArr(MaBezoutNbStruct _bezout,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(4));
        }
        if (LgInt.zero().eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getFirst())));
            return;
        }
        if (LgInt.one().eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getSecond())));
            return;
        }
        if (new LgInt(2).eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getPgcd())));
            return;
        }
        if (new LgInt(3).eq(lgInt_)) {
            setStruct(new MaRateStruct(new Rate(_bezout.getIdBezout().getPpcm())));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procArr(MaBezoutPolStruct _bezout,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(4));
        }
        if (LgInt.zero().eq(lgInt_)) {
            setStruct(new MaPolynomStruct(_bezout.getIdBezout().getFirst()));
            return;
        }
        if (LgInt.one().eq(lgInt_)) {
            setStruct(new MaPolynomStruct(_bezout.getIdBezout().getSecond()));
            return;
        }
        if (new LgInt(2).eq(lgInt_)) {
            setStruct(new MaPolynomStruct(_bezout.getIdBezout().getPgcd()));
            return;
        }
        if (new LgInt(3).eq(lgInt_)) {
            setStruct(new MaPolynomStruct(_bezout.getIdBezout().getPpcm()));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolygons(MaListPolygonStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo,MaRateStruct _indexThree, MaError _error) {
        CustList<Polygon> polygon_ = _divs.getPolygons();
        Polygon pt_ = procPolygon(polygon_, _index);
        if (pt_ != null) {
            RatePoint val_ = procPolygonPt(pt_, _indexTwo);
            if (val_ != null) {
                MaStruct nb_ = procRatePoint(val_, _indexThree);
                if (nb_ != null) {
                    setStruct(nb_);
                    return;
                }
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procDivs(MaDividersNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<LgInt> dividers_ = _divs.getDividers();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            setStruct(new MaRateStruct(new Rate(dividers_.get((int) lgInt_.ll()))));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDivs(MaDividersPolStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<Polynom> dividers_ = _divs.getDividers();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            setStruct(new MaPolynomStruct(dividers_.get((int) lgInt_.ll())));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private static boolean validIndex(LgInt _lgInt, int _len) {
        return _lgInt.isZeroOrGt() && LgInt.strLower(_lgInt, new LgInt(_len));
    }

    private void procDecomp(MaDecompositionNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<PrimDivisor> dividers_ = _divs.getDecomposition().getFactors();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            setStruct(new MaPrimDivisorNbStruct(dividers_.get((int) lgInt_.ll())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDecomp(MaDecompositionNbStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<PrimDivisor> dividers_ = _divs.getDecomposition().getFactors();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            PrimDivisor primDivisor_ = dividers_.get((int) lgInt_.ll());
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(2));
            }
            if (lgIntSec_.eq(LgInt.zero())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getPrime())));
                return;
            }
            if (lgIntSec_.eq(LgInt.one())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getExponent())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procPrimDivisor(MaPrimDivisorNbStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        PrimDivisor primDivisor_ = _divs.getPrimDivisor();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getPrime())));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getExponent())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDecomp(MaDecompositionPolStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<RootPol> dividers_ = _divs.getDecomposition();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            setStruct(new MaPrimDivisorPolStruct(dividers_.get((int) lgInt_.ll())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDecomp(MaDecompositionPolStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<RootPol> dividers_ = _divs.getDecomposition();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            RootPol primDivisor_ = dividers_.get((int) lgInt_.ll());
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(2));
            }
            if (lgIntSec_.eq(LgInt.zero())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getValue())));
                return;
            }
            if (lgIntSec_.eq(LgInt.one())) {
                setStruct(new MaRateStruct(new Rate(primDivisor_.getDegree())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procPrimDivisor(MaPrimDivisorPolStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        RootPol primDivisor_ = _divs.getPrimDivisor();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getValue())));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRateStruct(new Rate(primDivisor_.getDegree())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procPrimDivisor(MaRatePointStruct _divs,MaRateStruct _index, MaError _error) {
        MaStruct res_ = procRatePoint(_divs.getPoint(), _index);
        if (res_ != null) {
            setStruct(res_);
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private static MaStruct procRatePoint(RatePoint _divs,MaRateStruct _index) {
        LgInt lgInt_ = _index.getRate().intPart();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            return new MaRateStruct(new Rate(_divs.getXcoords()));
        }
        if (lgInt_.eq(LgInt.one())) {
            return new MaRateStruct(new Rate(_divs.getYcoords()));
        }
        return null;
    }
    private void procRep(MaRepartitionStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<EventFreq<CustList<LgInt>>> dividers_ = _divs.getEvents();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            EventFreq<CustList<LgInt>> dual_ = dividers_.get((int) lgInt_.ll());
            CustList<LgInt> copy_ = new CustList<LgInt>(new CollCapacity(dual_.getEvent().size()+1));
            copy_.addAllElts(dual_.getEvent());
            copy_.add(dual_.getFreq());
            setStruct(new MaDividersNbStruct(copy_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procRep(MaRepartitionStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<EventFreq<CustList<LgInt>>> dividers_ = _divs.getEvents();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            EventFreq<CustList<LgInt>> dual_ = dividers_.get((int) lgInt_.ll());
            CustList<LgInt> copy_ = new CustList<LgInt>(new CollCapacity(dual_.getEvent().size()+1));
            copy_.addAllElts(dual_.getEvent());
            copy_.add(dual_.getFreq());
            int eltLen_ = copy_.size();
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(copy_.size()));
            }
            if (validIndex(lgIntSec_, eltLen_)) {
                setStruct(new MaRateStruct(new Rate(copy_.get((int)lgIntSec_.ll()))));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procLaw(MaMonteCarloNumberStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<EventFreq<Rate>> dividers_ = _divs.getLaw().getEvents();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            EventFreq<Rate> dual_ = dividers_.get((int) lgInt_.ll());
            setStruct(new MaEventFreqStruct(dual_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procLaw(MaMonteCarloNumberStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<EventFreq<Rate>> dividers_ = _divs.getLaw().getEvents();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            EventFreq<Rate> dual_ = dividers_.get((int) lgInt_.ll());
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(2));
            }
            if (lgIntSec_.eq(LgInt.zero())) {
                setStruct(new MaRateStruct(dual_.getEvent()));
                return;
            }
            if (lgIntSec_.eq(LgInt.one())) {
                setStruct(new MaRateStruct(new Rate(dual_.getFreq())));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procMatrix(MaMatrixStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        Matrix matrix_ = _divs.getMatrix();
        int len_ = matrix_.nbLines();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            Vect dual_ = matrix_.get((int) lgInt_.ll());
            int eltLen_ = dual_.size();
            LgInt lgIntSec_ = _indexTwo.getRate().intPart();
            if (!lgIntSec_.isZeroOrGt()) {
                lgIntSec_.addNb(new LgInt(dual_.size()));
            }
            if (validIndex(lgIntSec_, eltLen_)) {
                setStruct(new MaRateStruct(new Rate(dual_.get((int)lgIntSec_.ll()))));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolygon(MaPolygonStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        Polygon polygon_ = _divs.getPolygon();
        RatePoint pt_ = procPolygonPt(polygon_, _index);
        if (pt_ != null) {
            MaStruct val_ = procRatePoint(pt_, _indexTwo);
            if (val_ != null) {
                setStruct(val_);
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolygons(MaListPolygonStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        CustList<Polygon> polygon_ = _divs.getPolygons();
        Polygon pt_ = procPolygon(polygon_, _index);
        if (pt_ != null) {
            RatePoint val_ = procPolygonPt(pt_, _indexTwo);
            if (val_ != null) {
                setStruct(new MaRatePointStruct(val_));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }
    private static RatePoint procPolygonPt(Polygon _divs,MaRateStruct _index) {
        LgInt lgInt_ = _index.getRate().intPart();
        int len_ = _divs.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            return _divs.get((int) lgInt_.ll());
        }
        return null;
    }
    private void procEdge(MaEdgeStruct _divs,MaRateStruct _index,MaRateStruct _indexTwo, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        Edge polygon_ = _divs.getEdge();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            RatePoint dual_ = polygon_.getFirst();
            MaStruct val_ = procRatePoint(dual_, _indexTwo);
            if (val_ != null) {
                setStruct(val_);
                return;
            }
            _error.setOffset(getIndexExp());
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            RatePoint dual_ = polygon_.getSecond();
            MaStruct val_ = procRatePoint(dual_, _indexTwo);
            if (val_ != null) {
                setStruct(val_);
                return;
            }
            _error.setOffset(getIndexExp());
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procEvt(MaEventFreqStruct _divs, MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        EventFreq<Rate> dividers_ = _divs.getPair();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRateStruct(dividers_.getEvent()));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRateStruct(new Rate(dividers_.getFreq())));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procRateList(MaRateListStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<Rate> dividers_ = _divs.getRates();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            Rate dual_ = dividers_.get((int) lgInt_.ll());
            setStruct(new MaRateStruct(dual_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolynom(MaPolynomStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustList<Rate> dividers_ = _divs.getPolynom().getNumbers();
        int len_ = dividers_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            Rate dual_ = dividers_.get((int) lgInt_.ll());
            setStruct(new MaRateStruct(dual_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procMatrix(MaMatrixStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        Matrix matrix_ = _divs.getMatrix();
        int len_ = matrix_.nbLines();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            Vect dual_ = matrix_.get((int) lgInt_.ll());
            setStruct(new MaVectStruct(dual_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procVect(MaVectStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        Vect matrix_ = _divs.getVect();
        int len_ = matrix_.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            Rate dual_ = matrix_.get((int) lgInt_.ll());
            setStruct(new MaRateStruct(dual_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolygon(MaPolygonStruct _divs,MaRateStruct _index, MaError _error) {
        Polygon matrix_ = _divs.getPolygon();
        RatePoint pt_ = procPolygonPt(matrix_, _index);
        if (pt_ != null) {
            setStruct(new MaRatePointStruct(pt_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procPolygons(MaListPolygonStruct _divs,MaRateStruct _index, MaError _error) {
        CustList<Polygon> matrix_ = _divs.getPolygons();
        Polygon pol_ = procPolygon(matrix_, _index);
        if (pol_ != null) {
            setStruct(new MaPolygonStruct(pol_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procDelaunay(MaDelaunayStruct _divs,MaRateStruct _index, MaError _error) {
        CustList<Polygon> matrix_ = MaListPolygonStruct.toPolygons(_divs.getDelaunay().getTriangles());
        LgInt lgInt_ = _index.getRate().intPart();
        if (lgInt_.isZero()) {
            setStruct(new MaListPolygonStruct(matrix_));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private static Polygon procPolygon(CustList<Polygon> _divs,MaRateStruct _index) {
        LgInt lgInt_ = _index.getRate().intPart();
        int len_ = _divs.size();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(len_));
        }
        if (validIndex(lgInt_, len_)) {
            return _divs.get((int) lgInt_.ll());
        }
        return null;
    }
    private void procLine(MaCustLineStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        CustLine line_ = _divs.getLine();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(3));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRateStruct(line_.getxRate()));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRateStruct(line_.getyRate()));
            return;
        }
        if (lgInt_.eq(new LgInt(2))) {
            setStruct(new MaRateStruct(line_.getCst()));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    private void procEdge(MaEdgeStruct _divs,MaRateStruct _index, MaError _error) {
        LgInt lgInt_ = _index.getRate().intPart();
        Edge line_ = _divs.getEdge();
        if (!lgInt_.isZeroOrGt()) {
            lgInt_.addNb(new LgInt(2));
        }
        if (lgInt_.eq(LgInt.zero())) {
            setStruct(new MaRatePointStruct(line_.getFirst()));
            return;
        }
        if (lgInt_.eq(LgInt.one())) {
            setStruct(new MaRatePointStruct(line_.getSecond()));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    CustList<MaStruct> tryGetAll() {
        CustList<MaStruct> rates_ = new CustList<MaStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            rates_.add(MaNumParsers.tryGet(this, i));
        }
        return rates_;
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
