package code.maths.litteraladv;

import code.maths.litteralcom.IndexStrPart;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class EqMaOperation extends MethodMaOperation {
    public EqMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        int i_ = IndexConstants.SECOND_INDEX;
        boolean result_ = true;
        for (IndexStrPart e: getOps().getValues()) {
            MaStruct first_ = MaNumParsers.tryGet(this, i_-1);
            MaStruct second_ = MaNumParsers.tryGet(this, i_);
            if (!MaNumParsers.eqNb(first_,second_,e.getPart())) {
                result_ = false;
            }
            i_++;
        }
        setStruct(MaBoolStruct.of(result_));
    }

}
