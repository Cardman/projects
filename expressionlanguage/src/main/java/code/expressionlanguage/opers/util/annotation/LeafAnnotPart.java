package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.ExportableStringStruct;
import code.expressionlanguage.structs.Struct;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;

    void setPart(Struct _part) {
        part = _part;
    }

    String export() {
        return ((ExportableStringStruct)part).exportValue().getInstance();
    }
}
