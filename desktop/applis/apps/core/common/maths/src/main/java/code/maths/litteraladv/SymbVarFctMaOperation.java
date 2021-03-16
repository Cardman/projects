package code.maths.litteraladv;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.Delaunay;
import code.maths.geo.Polygon;
import code.maths.geo.RatePoint;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.Polynom;
import code.maths.matrix.RateImage;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbVarFctMaOperation extends MethodMaOperation  {
    private String oper = "";
    private final MaParameters mapping;
    protected SymbVarFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, MaParameters _mapping) {
        super(_index, _indexChild, _m, _op);
        mapping = _mapping;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq("&&", oper)) {
            procRep(_error);
        }
        if (StringUtil.quickEq("?", oper)) {
            procAlea(_error);
        }
        if (StringUtil.quickEq("<>-|", oper)) {
            procStat(_error);
        }
        if (StringUtil.quickEq(";", oper)) {
            procPolynom(_error);
        }
        if (StringUtil.quickEq("|||", oper)) {
            procPolygon(_error);
        }
        if (StringUtil.quickEq("%%", oper)) {
            procDelaunay(_error,false);
        }
        if (StringUtil.quickEq("%%%", oper)) {
            procDelaunay(_error,true);
        }
    }

    private void procDelaunay(MaError _error, boolean _mid) {
        CustList<MaRatePointStruct> points_ = tryGetAllAsPt(this);
        if (points_ != null) {
            CustList<RatePoint> pts_ = new CustList<RatePoint>();
            for (MaRatePointStruct p: points_) {
                pts_.add(p.getPoint());
            }
            Delaunay del_ = new Delaunay();
            del_.compute(pts_,_mid);
            setStruct(new MaDelaunayStruct(del_,_mid));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procRep(MaError _error) {
        if (getChildren().size() >= 1) {
            CustList<MaRateStruct> rates_ = tryGetRates(this);
            if (areAllIntegers(rates_)) {
                LgInt nbOne_= rates_.first().getRate().intPart();
                CustList<LgInt> ints_ = new CustList<LgInt>();
                int nb_ = rates_.size();
                for (int i = 1; i < nb_; i++) {
                    ints_.add(rates_.get(i).getRate().intPart());
                }
                setStruct(new MaRepartitionStruct(LgInt.seqAmong(ints_,nbOne_)));
                return;
            }
        }
        _error.setOffset(getIndexExp());
    }

    private void procAlea(MaError _error) {
        CustList<MaStruct> all_ = tryGetAll(this);
        if (all_.size() == 2 && all_.first() instanceof MaMonteCarloNumberStruct && all_.last() instanceof MaRateStruct) {
            MaMonteCarloNumberStruct v_ = (MaMonteCarloNumberStruct) all_.first();
            MonteCarloNumber law_ = v_.getLaw();
            MaRateStruct m_ = (MaRateStruct) all_.last();
            Rate max_ = m_.getRate();
            if (law_.nbEvents() > 0 && max_.isInteger() && max_.isZeroOrGt()) {
                setStruct(new MaRateStruct(law_.editNumber(max_.intPart(), mapping.getGenerator())));
                return;
            }
        }
        if (getChildren().size() == 1 && all_.first() instanceof MaMonteCarloNumberStruct) {
            MaMonteCarloNumberStruct v_ = (MaMonteCarloNumberStruct) all_.first();
            MonteCarloNumber law_ = v_.getLaw();
            if (law_.nbEvents() > 0) {
                setStruct(new MaRateStruct(law_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator())));
                return;
            }
        }
        defAlea(_error);
    }

    private void defAlea(MaError _error) {
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
        if (allEvts_&&law_.nbEvents() > 0) {
            setStruct(new MaRateStruct(law_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator())));
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
        if (allEvts_&&lawSimple_.nbEvents() > 0) {
            setStruct(new MaRateStruct(lawSimple_.editNumber(LgInt.getMaxLongPlusOne(), mapping.getGenerator())));
            return;
        }
        _error.setOffset(getIndexExp());
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
        _error.setOffset(getIndexExp());
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
        _error.setOffset(getIndexExp());
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
        _error.setOffset(getIndexExp());
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        oper = vs_.getValue(1);
        vs_.remove(1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }

    String getOper() {
        return oper;
    }
}
