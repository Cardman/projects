package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.structs.*;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;

    void setPart(Struct _part) {
        part = _part;
    }

    String export(String _infinity, String _nan,String _exp) {
        if (part instanceof BooleanStruct) {
            return ((BooleanStruct)part).exportValue().getInstance();
        }
        if (part instanceof CharSequenceStruct) {
            return ((CharSequenceStruct)part).exportValue().getInstance();
        }
        if (part instanceof NumberStruct) {
            return ((NumberStruct)part).exportValue(_infinity,_nan,_exp).getInstance();
        }
        if (part instanceof ClassMetaInfo) {
            return ((ClassMetaInfo)part).exportValue().getInstance();
        }
        return ApplyCoreMethodUtil.getNameOfEnum(part);
    }
}
