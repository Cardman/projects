package code.maths.litteraladv;

import code.maths.litteralcom.MathExpUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbDoubleCaracFctMaOperation extends MethodMaOperation {
    private final String firstOper;
    private final String secondOper;
    private final int operOff;
    public SymbDoubleCaracFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, String _sec, int _off, String _first) {
        super(_index, _indexChild, _m, _op);
        secondOper = _sec;
        firstOper = _first;
        operOff = _off;
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

}
