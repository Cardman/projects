package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;

    void setPart(Struct _part) {
        part = _part;
    }

    String export() {
        if (part instanceof BooleanStruct) {
            return ((BooleanStruct)part).exportValue().getInstance();
        }
        if (part instanceof CharSequenceStruct) {
            return ((CharSequenceStruct)part).exportValue().getInstance();
        }
        if (part instanceof NumberStruct) {
            return ((NumberStruct)part).exportValue().getInstance();
        }
        if (part instanceof ClassMetaInfo) {
            return ((ClassMetaInfo)part).exportValue().getInstance();
        }
        return LgNames.getNameOfEnum(part);
    }
}
