package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteralcom.StrTypes;
import code.maths.matrix.FractPol;
import code.maths.matrix.Matrix;
import code.maths.matrix.Polynom;
import code.maths.montecarlo.EventFreq;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.StringMap;

public final class IdMaOperation extends MethodMaOperation {
    public IdMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaOperationNode> chidren_ = getChildren();
        int len_ = chidren_.size();
        MaFractPolStruct fract_ = MaFractPolStruct.wrapOrNull(MaNumParsers.tryGet(this, 0));
        if (fractFct(len_, fract_)) {
            processFract(_error, fract_);
            return;
        }
        MonteCarloNumber assLaw_ = tryBuildByAss();
        if (assLaw_ != null) {
            setStruct(new MaMonteCarloNumberStruct(assLaw_));
            return;
        }
        MonteCarloNumber lawSimple_ = tryBuildUni();
        if (lawSimple_ != null && len_ > 1) {
            setStruct(new MaMonteCarloNumberStruct(lawSimple_));
            return;
        }
        if (len_ != 1) {
            int index_ = Math.min(getOperats().getOpers().size()-1,1);
            _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getOpers(),index_));
            return;
        }
        setStruct(chidren_.first().getStruct());
    }

    private MonteCarloNumber tryBuildByAss() {
        CustList<MaOperationNode> chidren_ = getChildren();
        int len_ = chidren_.size();
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaEventFreqStruct)) {
                return null;
            }
            EventFreq<Rate> pair_ = ((MaEventFreqStruct) value_).getPair();
            law_.addQuickEvent(pair_.getEvent(),pair_.getFreq());
        }
        return law_;
    }

    private MonteCarloNumber tryBuildUni() {
        CustList<MaOperationNode> chidren_ = getChildren();
        int len_ = chidren_.size();
        MonteCarloNumber lawSimple_ = new MonteCarloNumber();
        for (int i = 0; i < len_; i++) {
            MaStruct value_ = MaNumParsers.tryGet(this, i);
            if (!(value_ instanceof MaRateStruct)) {
                return null;
            }
            lawSimple_.addQuickEvent(((MaRateStruct) value_).getRate(), LgInt.one());
        }
        return lawSimple_;
    }
    private static boolean fractFct(int _len, MaFractPolStruct _fract) {
        return _len == 2 && _fract != null;
    }

    private void processFract(MaError _error, MaFractPolStruct _fract) {
        MaStruct value_ = MaNumParsers.tryGet(this, 1);
        if (value_ instanceof MaRateStruct) {
            FractPol pols_ = _fract.getFractPol();
            Polynom den_ = pols_.getDenominator();
            Rate rate_ = ((MaRateStruct) value_).getRate();
            Rate imageDen_ = den_.image(rate_);
            if (imageDen_.isZero()) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),1));
                return;
            }
            Polynom num_ = pols_.getNumerator();
            Rate imageNum_ = num_.image(rate_);
            setStruct(new MaRateStruct(Rate.divide(imageNum_,imageDen_)));
            return;
        }
        if (value_ instanceof MaComplexStruct) {
            FractPol pols_ = _fract.getFractPol();
            Polynom den_ = pols_.getDenominator();
            Complex rate_ = ((MaComplexStruct) value_).getComplex();
            Complex imageDen_ = den_.image(rate_);
            if (imageDen_.isZero()) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),1));
                return;
            }
            Polynom num_ = pols_.getNumerator();
            Complex imageNum_ = num_.image(rate_);
            setStruct(new MaComplexStruct(imageNum_.divide(imageDen_)));
            return;
        }
        if (value_ instanceof MaMatrixStruct) {
            FractPol pols_ = _fract.getFractPol();
            Matrix matrix_ = ((MaMatrixStruct) value_).getMatrix();
            if (!matrix_.isSquare()) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),1));
                return;
            }
            Polynom num_ = pols_.getNumerator();
            Matrix imageNum_ = num_.image(matrix_);
            Polynom den_ = pols_.getDenominator();
            Matrix imageDen_ = den_.image(matrix_);
            setStruct(new MaMatrixStruct(imageNum_.multMatrix(imageDen_.inv())));
            return;
        }
        MaFractPolStruct wr_ = MaFractPolStruct.wrapOrNull(value_);
        if (wr_ != null) {
            FractPol pols_ = _fract.getFractPol();
            Polynom den_ = pols_.getDenominator();
            FractPol rate_ = wr_.getFractPol();
            FractPol imageDen_ = den_.comp(rate_);
            if (imageDen_.isZero()) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),1));
                return;
            }
            Polynom num_ = pols_.getNumerator();
            FractPol imageNum_ = num_.comp(rate_);
            setStruct(new MaFractPolStruct(FractPol.divide(imageNum_,imageDen_)));
            return;
        }
        _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),1));
    }

}
