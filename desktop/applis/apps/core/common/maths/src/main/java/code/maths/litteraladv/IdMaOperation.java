package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;

public class IdMaOperation extends MethodMaOperation {
    public IdMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        CustList<MaOperationNode> chidren_ = getChildren();
        if (chidren_.size() != 1) {
            _error.setOffset(getIndexExp());
            return;
        }
        setStruct(chidren_.first().getStruct());
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
