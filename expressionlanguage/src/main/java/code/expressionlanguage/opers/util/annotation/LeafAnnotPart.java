package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.ExportableStringStruct;
import code.expressionlanguage.structs.Struct;

final class LeafAnnotPart extends InfoAnnotPart {

    private Struct part;
    
    public Struct getPart() {
        return part;
    }

    public void setPart(Struct _part) {
        part = _part;
    }

    @Override
    Struct getInstance() {
        return part;
    }
    @Override
    InfoAnnotPart getFirst() {
        return null;
    }
    public String export() {
        return ((ExportableStringStruct)part).exportValue().getInstance();
    }
}
