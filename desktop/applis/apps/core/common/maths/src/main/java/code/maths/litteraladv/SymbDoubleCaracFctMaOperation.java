package code.maths.litteraladv;

import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbDoubleCaracFctMaOperation extends MethodMaOperation {
    private String firstOper ="";
    private String secondOper ="";
    private int operOff;
    protected SymbDoubleCaracFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(WIDE_BOUND, firstOper)&&StringUtil.quickEq(WIDE_BOUND, secondOper)) {
            segment(_error);
        }
        if (StringUtil.quickEq(STRICT_BOUND, firstOper)&&StringUtil.quickEq(STRICT_BOUND, secondOper)) {
            ouvert(_error);
        }
        if (StringUtil.quickEq(STRICT_BOUND, firstOper)&&StringUtil.quickEq(WIDE_BOUND, secondOper)) {
            caracsemiouvertg(_error);
        }
        if (StringUtil.quickEq(WIDE_BOUND, firstOper)&&StringUtil.quickEq(STRICT_BOUND, secondOper)) {
            caracsemiouvertd(_error);
        }
    }

    private void segment(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaStruct thirdVal_ = MaNumParsers.tryGet(this, 2);
        MaRateStruct firstInt_ = asRate(firstVal_);
        MaRateStruct secondInt_ = asRate(secondVal_);
        MaRateStruct thirdInt_ = asRate(thirdVal_);
        if (firstInt_ != null && secondInt_ != null && thirdInt_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.segment(firstInt_.getRate(),
                    secondInt_.getRate(),
                    thirdInt_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void ouvert(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaStruct thirdVal_ = MaNumParsers.tryGet(this, 2);
        MaRateStruct firstInt_ = asRate(firstVal_);
        MaRateStruct secondInt_ = asRate(secondVal_);
        MaRateStruct thirdInt_ = asRate(thirdVal_);
        if (firstInt_ != null && secondInt_ != null && thirdInt_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracouvert(firstInt_.getRate(),
                    secondInt_.getRate(),
                    thirdInt_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void caracsemiouvertg(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaStruct thirdVal_ = MaNumParsers.tryGet(this, 2);
        MaRateStruct firstInt_ = asRate(firstVal_);
        MaRateStruct secondInt_ = asRate(secondVal_);
        MaRateStruct thirdInt_ = asRate(thirdVal_);
        if (firstInt_ != null && secondInt_ != null && thirdInt_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracsemiouvertg(firstInt_.getRate(),
                    secondInt_.getRate(),
                    thirdInt_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    private void caracsemiouvertd(MaError _error) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaStruct thirdVal_ = MaNumParsers.tryGet(this, 2);
        MaRateStruct firstInt_ = asRate(firstVal_);
        MaRateStruct secondInt_ = asRate(secondVal_);
        MaRateStruct thirdInt_ = asRate(thirdVal_);
        if (firstInt_ != null && secondInt_ != null && thirdInt_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracsemiouvertd(firstInt_.getRate(),
                    secondInt_.getRate(),
                    thirdInt_.getRate())));
        } else {
            _error.setOffset(getIndexExp()+operOff);
        }
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        secondOper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        firstOper = vs_.lastValue();
        operOff = vs_.lastKey();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
