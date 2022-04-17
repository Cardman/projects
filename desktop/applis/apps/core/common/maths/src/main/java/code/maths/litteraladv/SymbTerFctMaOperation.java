package code.maths.litteraladv;

import code.maths.geo.CustLine;
import code.util.StringMap;

public final class SymbTerFctMaOperation extends AbsSymbFixMaOperation {

    public SymbTerFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, int _off, String _ope) {
        super(_index, _indexChild, _m, _op, _off, _ope);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct firstVal_ = MaNumParsers.tryGet(this, 0);
        MaStruct secondVal_ = MaNumParsers.tryGet(this, 1);
        MaStruct thirdVal_ = MaNumParsers.tryGet(this, 2);
        MaRateStruct firstInt_ = asRate(firstVal_);
        MaRateStruct secondInt_ = asRate(secondVal_);
        MaRateStruct thirdInt_ = asRate(thirdVal_);
        if (firstInt_ != null && secondInt_ != null && thirdInt_ != null) {
            CustLine line_ = new CustLine(firstInt_.getRate(), secondInt_.getRate(), thirdInt_.getRate());
            if (line_.isDefined()) {
                setStruct(new MaCustLineStruct(line_));
                return;
            }
        }
        _error.setOffset(getIndexExp()+getOperOff());
    }

}
