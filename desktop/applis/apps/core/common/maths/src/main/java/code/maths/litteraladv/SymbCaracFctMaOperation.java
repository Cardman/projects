package code.maths.litteraladv;

import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbCaracFctMaOperation extends MethodMaOperation {
    private String oper="";
    protected SymbCaracFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq("==<",oper)) {
            caracdroiteouvert(_error);
        }
        if (StringUtil.quickEq("==>",oper)) {
            caracdroiteferme(_error);
        }
        if (StringUtil.quickEq(">==",oper)) {
            caracgaucheouvert(_error);
        }
        if (StringUtil.quickEq("<==",oper)) {
            caracgaucheferme(_error);
        }

    }

    private void caracdroiteouvert(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracdroiteouvert(values_.get(0).getRate(),
                    values_.get(1).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void caracdroiteferme(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracdroiteferme(values_.get(0).getRate(),
                    values_.get(1).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void caracgaucheouvert(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracgaucheouvert(values_.get(0).getRate(),
                    values_.get(1).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
    }

    private void caracgaucheferme(MaError _error) {
        CustList<MaRateStruct> values_ = tryGetAllAsRate(this);
        if (values_ != null) {
            setStruct(new MaRateStruct(MathExpUtil.caracgaucheferme(values_.get(0).getRate(),
                    values_.get(1).getRate())));
        } else {
            _error.setOffset(getIndexExp());
        }
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
