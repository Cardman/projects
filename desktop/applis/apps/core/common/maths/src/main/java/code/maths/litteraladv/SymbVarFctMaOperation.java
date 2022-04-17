package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Delaunay;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.matrix.Polynom;
import code.maths.matrix.RateImage;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbVarFctMaOperation extends MethodMaOperation  {
    private static final String REP = MaOperationsSequence.REP;
    private static final String RAND = MaOperationsSequence.RAND;
    private static final String STAT = MaOperationsSequence.STAT;
    private static final String EQ_VAR = MaOperationsSequence.EQ_VAR;
    private static final String FIRST_DELAUNAY = MaOperationsSequence.FIRST_DELAUNAY;
    private static final String SECOND_DELAUNAY = MaOperationsSequence.SECOND_DELAUNAY;
    private final String oper;
    private final int operOff;
    private final MaParameters mapping;
    public SymbVarFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping, int _off, String _ope) {
        super(_index, _indexChild, _m, _op);
        mapping = _mapping;
        oper = _ope;
        operOff = _off;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(REP, oper)) {
            procRep(_error);
        }
        if (StringUtil.quickEq(RAND, oper)) {
            procAlea(_error);
        }
        if (StringUtil.quickEq(STAT, oper)) {
            procStat(_error);
        }
        if (StringUtil.quickEq(POLYNOM_SYMB, oper)) {
            procPolynom(_error);
        }
        if (StringUtil.quickEq(POLYGON_SYMBOL, oper)) {
            procPolygon(_error);
        }
        if (StringUtil.quickEq(FIRST_DELAUNAY, oper)) {
            procDelaunay(_error,false);
        }
        if (StringUtil.quickEq(SECOND_DELAUNAY, oper)) {
            procDelaunay(_error,true);
        }
        if (StringUtil.quickEq(EQ_VAR, oper)) {
            procEq(_error);
        }
    }

    private void procEq(MaError _error) {
        CustList<MaStruct> all_ = tryGetAll(this);
        int len_ = all_.size();
        if (len_ < 2) {
            _error.setOffset(getIndexExp()+operOff);
            return;
        }
        MaStruct left_ = all_.first();
        for (int i = 1; i < len_; i++) {
            MaStruct right_ = all_.get(i);
            if (!MaNumParsers.eqMath(left_, right_)) {
                setStruct(MaBoolStruct.of(false));
                return;
            }
            left_ = right_;
        }
        setStruct(MaBoolStruct.of(true));
    }
    private void procDelaunay(MaError _error, boolean _mid) {
        int len_ = getChildren().size();
        CustList<MaRatePointStruct> rates_ = new CustList<MaRatePointStruct>();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            if (!(str_ instanceof MaRatePointStruct)) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            rates_.add((MaRatePointStruct) str_);
        }
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        for (MaRatePointStruct p: rates_) {
            pts_.add(p.getPoint());
        }
        Delaunay del_ = new Delaunay();
        del_.compute(pts_,_mid);
        setStruct(new MaDelaunayStruct(del_,_mid));
    }

    private void procRep(MaError _error) {
        CustList<MaRateStruct> rates_ = new CustList<MaRateStruct>();
        int len_ = getChildren().size();
        for (int i = 0; i < len_; i++) {
            MaStruct str_ = MaNumParsers.tryGet(this, i);
            MaRateStruct intVal_ = asInt(str_);
            if (intVal_ == null) {
                _error.setOffset(getIndexExp()+operOff);
                return;
            }
            rates_.add(intVal_);
        }
        if (rates_.size() >= 1) {
            LgInt nbOne_= rates_.first().getRate().intPart();
            CustList<LgInt> ints_ = new CustList<LgInt>();
            int nb_ = rates_.size();
            for (int i = 1; i < nb_; i++) {
                ints_.add(rates_.get(i).getRate().intPart());
            }
            setStruct(new MaRepartitionStruct(LgInt.seqAmong(ints_,nbOne_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procAlea(MaError _error) {
        CustList<MaStruct> all_ = tryGetAll(this);
        if (all_.size() == 2 && all_.first() instanceof MaMonteCarloNumberStruct && all_.last() instanceof MaRateStruct) {
            MaMonteCarloNumberStruct v_ = (MaMonteCarloNumberStruct) all_.first();
            MonteCarloNumber law_ = v_.getLaw();
            MaRateStruct m_ = (MaRateStruct) all_.last();
            Rate max_ = m_.getRate();
            if (law_.nbEvents() > 0 && max_.isInteger() && max_.isZeroOrGt()) {
                setStruct(new MaRateStruct(law_.editNumber(max_.intPart(), mapping.getGenerator(), mapping.getCust())));
                return;
            }
        }
        if (getChildren().size() == 1 && all_.first() instanceof MaMonteCarloNumberStruct) {
            MaMonteCarloNumberStruct v_ = (MaMonteCarloNumberStruct) all_.first();
            MonteCarloNumber law_ = v_.getLaw();
            if (law_.nbEvents() > 0) {
                setStruct(new MaRateStruct(law_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator(), mapping.getCust())));
                return;
            }
        }
        defAlea(_error);
    }

    private void defAlea(MaError _error) {
        int len_ = getChildren().size();
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaEventFreqStruct)) {
                law_.getEvents().clear();
                break;
            }
            EventFreq<Rate> pair_ = ((MaEventFreqStruct) value_).getPair();
            law_.addQuickEvent(pair_.getEvent(),pair_.getFreq());
        }
        if (law_.nbEvents() > 0) {
            setStruct(new MaRateStruct(law_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator(), mapping.getCust())));
            return;
        }
        MonteCarloNumber lawSimple_ = new MonteCarloNumber();
        boolean allEvts_ = true;
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRateStruct)) {
                allEvts_ = false;
                break;
            }
            lawSimple_.addQuickEvent(((MaRateStruct)value_).getRate(),LgInt.one());
        }
        if (allEvts_&&lawSimple_.nbEvents() > 0) {
            setStruct(new MaRateStruct(lawSimple_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator(), mapping.getCust())));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procStat(MaError _error) {
        CustList<MaStruct> all_ = tryGetAll(this);
        if (all_.size() == 1 && all_.first() instanceof MaMonteCarloNumberStruct) {
            MaMonteCarloNumberStruct v_ = (MaMonteCarloNumberStruct) all_.first();
            MonteCarloNumber law_ = v_.getLaw();
            CustList<Rate> stats_ = new CustList<Rate>(new CollCapacity(4));
            stats_.add(law_.minimum());
            stats_.add(law_.maximum());
            stats_.add(law_.getAvg());
            stats_.add(law_.getVar());
            setStruct(new MaRateListStruct(stats_));
            return;
        }
        defStat(_error);
    }

    private void defStat(MaError _error) {
        int len_ = getChildren().size();
        MonteCarloNumber law_ = new MonteCarloNumber();
        boolean allEvts_ = true;
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaEventFreqStruct)) {
                allEvts_ = false;
                break;
            }
            EventFreq<Rate> pair_ = ((MaEventFreqStruct) value_).getPair();
            law_.addQuickEvent(pair_.getEvent(),pair_.getFreq());
        }
        if (allEvts_) {
            CustList<Rate> stats_ = new CustList<Rate>(new CollCapacity(4));
            stats_.add(law_.minimum());
            stats_.add(law_.maximum());
            stats_.add(law_.getAvg());
            stats_.add(law_.getVar());
            setStruct(new MaRateListStruct(stats_));
            return;
        }
        MonteCarloNumber lawSimple_ = new MonteCarloNumber();
        allEvts_ = true;
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRateStruct)) {
                allEvts_ = false;
                break;
            }
            lawSimple_.addQuickEvent(((MaRateStruct)value_).getRate(),LgInt.one());
        }
        if (allEvts_) {
            CustList<Rate> stats_ = new CustList<Rate>(new CollCapacity(4));
            stats_.add(lawSimple_.minimum());
            stats_.add(lawSimple_.maximum());
            stats_.add(lawSimple_.getAvg());
            stats_.add(lawSimple_.getVar());
            setStruct(new MaRateListStruct(stats_));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPolynom(MaError _error) {
        int len_ = getChildren().size();
        boolean allRates_ = true;
        CustList<Rate> rates_ = new CustList<Rate>();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRateStruct)) {
                allRates_ = false;
                break;
            }
            rates_.add(((MaRateStruct)value_).getRate());
        }
        if (allRates_) {
            setStruct(new MaPolynomStruct(new Polynom(rates_)));
            return;
        }
        Polynom generated_ = Polynom.zero();
        allRates_ = true;
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaPolMemberStruct)) {
                allRates_ = false;
                break;
            }
            Rate rate_ = ((MaPolMemberStruct) value_).getRate();
            LgInt power_ = ((MaPolMemberStruct) value_).getPower();
            generated_.addPol(Polynom.one().prodMonom(rate_,power_.ll()));
        }
        if (allRates_) {
            setStruct(new MaPolynomStruct(generated_));
            return;
        }
        allRates_ = true;
        CustList<RateImage> rateImages_ = new CustList<RateImage>();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRateImageStruct)) {
                allRates_ = false;
                break;
            }
            RateImage raIm_ = ((MaRateImageStruct) value_).getRateImage();
            rateImages_.add(raIm_);
        }
        if (allRates_) {
            setStruct(new MaPolynomStruct(Polynom.interpolation(rateImages_)));
            return;
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    private void procPolygon(MaError _error) {
        int len_ = getChildren().size();
        boolean allRates_ = true;
        CustList<RatePoint> rates_ = new CustList<RatePoint>();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRatePointStruct)) {
                allRates_ = false;
                break;
            }
            rates_.add(((MaRatePointStruct)value_).getPoint());
        }
        if (rates_.size() > 2 && allRates_) {
            setStruct(new MaPolygonStruct(new Polygon(rates_)));
            return;
        }
        if (rates_.size() == 2 && allRates_) {
            CustLine line_ = new CustLine(rates_.first(), rates_.last());
            if (line_.isDefined()) {
                setStruct(new MaCustLineStruct(line_));
                return;
            }
        }
        _error.setOffset(getIndexExp()+operOff);
    }

    String getOper() {
        return oper;
    }
}
