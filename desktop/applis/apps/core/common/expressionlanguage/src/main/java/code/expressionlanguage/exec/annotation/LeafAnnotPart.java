package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;

    void setPart(Struct _part) {
        part = _part;
    }

    String export(String _infinity, String _nan,String _exp, String _unicode) {
        if (part instanceof BooleanStruct) {
            return ((BooleanStruct)part).exportValue().getInstance();
        }
        if (part instanceof CharSequenceStruct) {
            return NumParsers.exportValue((CharSequenceStruct)part,_unicode).getInstance();
        }
        if (part instanceof NumberStruct) {
            return NumParsers.exportValue((NumberStruct)part,_infinity,_nan,_exp).getInstance();
        }
        if (part instanceof ClassMetaInfo) {
            return ((ClassMetaInfo)part).exportValue().getInstance();
        }
        return NumParsers.getNameOfEnum(part);
    }
}
