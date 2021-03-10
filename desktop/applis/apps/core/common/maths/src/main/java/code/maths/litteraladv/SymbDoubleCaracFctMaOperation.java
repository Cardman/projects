package code.maths.litteraladv;

import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbDoubleCaracFctMaOperation extends MethodMaOperation {
    private String firstOper ="";
    private String secondOper ="";
    protected SymbDoubleCaracFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq("<=", firstOper)&&StringUtil.quickEq("<=", secondOper)) {
            segment(_error);
        }
        if (StringUtil.quickEq("<", firstOper)&&StringUtil.quickEq("<", secondOper)) {
            ouvert(_error);
        }
        if (StringUtil.quickEq("<", firstOper)&&StringUtil.quickEq("<=", secondOper)) {
            caracsemiouvertg(_error);
        }
        if (StringUtil.quickEq("<=", firstOper)&&StringUtil.quickEq("<", secondOper)) {
            caracsemiouvertd(_error);
        }
    }

    private void segment(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.segment(values_.get(0).getRate(),
                    values_.get(1).getRate(),
                    values_.get(2).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void ouvert(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracouvert(values_.get(0).getRate(),
                    values_.get(1).getRate(),
                    values_.get(2).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void caracsemiouvertg(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracsemiouvertg(values_.get(0).getRate(),
                    values_.get(1).getRate(),
                    values_.get(2).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void caracsemiouvertd(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracsemiouvertd(values_.get(0).getRate(),
                    values_.get(1).getRate(),
                    values_.get(2).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        secondOper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        firstOper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
