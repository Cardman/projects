package code.maths.litteraladv;

import code.maths.Decomposition;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbUnFctMaOperation extends MethodMaOperation {
    private String oper = "";
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
    }

    private void procSgn(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
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
        _error.setOffset(getIndexExp());
    }

    private void procAbs(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
            setStruct(new MaRateStruct(nb_.absNb()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procNum(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
            setStruct(new MaRateStruct(new Rate(nb_.getNumerator())));
            return;
        }
        CustList<MaFractPolStruct> valFracts_ = tryGetAllAsFractPol(this);
        if (valFracts_ != null) {
            FractPol nb_= valFracts_.first().getFractPol();
            setStruct(new MaPolynomStruct(nb_.getNumerator()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procDen(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
            setStruct(new MaRateStruct(new Rate(nb_.getDenominator())));
            return;
        }
        CustList<MaFractPolStruct> valFracts_ = tryGetAllAsFractPol(this);
        if (valFracts_ != null) {
            FractPol nb_= valFracts_.first().getFractPol();
            setStruct(new MaPolynomStruct(nb_.getDenominator()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procEnt(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
            setStruct(new MaRateStruct(new Rate(nb_.intPart())));
            return;
        }
        CustList<MaFractPolStruct> valFracts_ = tryGetAllAsFractPol(this);
        if (valFracts_ != null) {
            FractPol nb_= valFracts_.first().getFractPol();
            setStruct(new MaPolynomStruct(nb_.intPart()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procTroncature(MaError _error) {
        CustList<MaRateStruct> valRates_ = tryGetAllAsRate(this);
        if (valRates_ != null) {
            Rate nb_= valRates_.first().getRate();
            setStruct(new MaRateStruct(new Rate(nb_.toLgInt())));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procPrem(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates(this);
        if (areAllIntegersNb(rates_,1)) {
            Rate nb_= rates_.first().getRate();
            setStruct(MaBoolStruct.of(nb_.intPart().isPrime()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procDivs(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates(this);
        if (areAllIntegersNb(rates_,1)) {
            Rate nb_= rates_.first().getRate();
            setStruct(new MaDividersNbStruct(nb_.intPart().getDividers()));
            return;
        }
        _error.setOffset(getIndexExp());
    }

    private void procDecomp(MaError _error) {
        CustList<MaRateStruct> rates_ = tryGetRates(this);
        if (areAllIntegersNb(rates_,1)) {
            Rate nb_= rates_.first().getRate();
            setStruct(new MaDecompositionNbStruct(nb_.intPart().decompoPrim()));
            return;
        }
        _error.setOffset(getIndexExp());
    }
    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        oper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
