package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FctMaOperation extends MethodMaOperation {
    private final String methodName;
//    private final MaParameters mapping;
    public FctMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperats().getFct().trim();
//        mapping = _mapping;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq("3",methodName)) {
            int index_ = Math.min(getOperats().getOpers().size()-1,3);
            if (getChildren().size() != 3) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getOpers(),index_));
                return;
            }
            MaStruct valBool_ = MaNumParsers.tryGet(this, 0);
            if (!(valBool_ instanceof MaBoolStruct)) {
                _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getParts(),0));
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
        _error.setOffset(getIndexExp()+StringUtil.getFirstPrintableCharIndex(getOperats().getFct()));
    }

}
