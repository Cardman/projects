package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class FctMaOperation extends MethodMaOperation {
    private final String methodName;
//    private final MaParameters mapping;
    public FctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = _op.getFct();
//        mapping = _mapping;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del, CustList<String> _rands) {
        if (StringUtil.quickEq("3",methodName.trim())) {
            int index_ = NumberUtil.min(getOps().size()-1,3);
            if (getChildren().size() != 3) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOps(),index_));
                return;
            }
            MaStruct valBool_ = MaNumParsers.tryGet(this, 0);
            if (!(valBool_ instanceof MaBoolStruct)) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getChs(),0));
                return;
            }
            MaBoolStruct v_ = (MaBoolStruct) valBool_;
            if (MaBoolStruct.of(true).sameReference(v_)) {
                setStruct(MaNumParsers.tryGet(this,1));
            } else {
                setStruct(MaNumParsers.tryGet(this,2));
            }
            return;
        }
//        if (StringUtil.contains(MaParser.fcts(),id_)) {
//            procVariables(_error, id_);
//            return;
//        }
        _error.setOffset(getIndexExp()+StringUtil.getFirstPrintableCharIndex(methodName));
    }

}
