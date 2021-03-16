package code.maths.litteraladv;

import code.maths.geo.CustLine;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

public final class SymbTerFctMaOperation extends MethodMaOperation {
    protected SymbTerFctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaRateStruct> rates_ = tryGetRates(this);
        if (rates_.size() == 3) {
            CustLine line_ = new CustLine(rates_.get(0).getRate(), rates_.get(1).getRate(), rates_.get(2).getRate());
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
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
