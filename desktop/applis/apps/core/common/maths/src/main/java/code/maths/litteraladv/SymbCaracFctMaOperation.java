package code.maths.litteraladv;

import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbCaracFctMaOperation extends MethodMaOperation {
    private String oper="";
    private int operOff;
    protected SymbCaracFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(RIGHT_OPEN,oper)) {
            caracdroiteouvert(_error);
        }
        if (StringUtil.quickEq(RIGHT_CLOSE,oper)) {
            caracdroiteferme(_error);
        }
        if (StringUtil.quickEq(LEFT_OPEN,oper)) {
            caracgaucheouvert(_error);
        }
        if (StringUtil.quickEq(LEFT_CLOSE,oper)) {
            caracgaucheferme(_error);
        }

    }

    private void caracdroiteouvert(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(firstVal_);
        MaRateStruct secondRate_ = asRate(secondVal_);
        if (firstRate_ != null&&secondRate_!=null) {
            setStruct(new MaRateStruct(MathExpUtil.caracdroiteouvert(firstRate_.getRate(),
                    secondRate_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void caracdroiteferme(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(firstVal_);
        MaRateStruct secondRate_ = asRate(secondVal_);
        if (firstRate_ != null&&secondRate_!=null) {
            setStruct(new MaRateStruct(MathExpUtil.caracdroiteferme(firstRate_.getRate(),
                    secondRate_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void caracgaucheouvert(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(firstVal_);
        MaRateStruct secondRate_ = asRate(secondVal_);
        if (firstRate_ != null&&secondRate_!=null) {
            setStruct(new MaRateStruct(MathExpUtil.caracgaucheouvert(firstRate_.getRate(),
                    secondRate_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void caracgaucheferme(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaRateStruct firstRate_ = asRate(firstVal_);
        MaRateStruct secondRate_ = asRate(secondVal_);
        if (firstRate_ != null&&secondRate_!=null) {
            setStruct(new MaRateStruct(MathExpUtil.caracgaucheferme(firstRate_.getRate(),
                    secondRate_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
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
