package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.FieldInfo;

public final class AnaSettableOperationContent {
    private boolean variable;
    private FieldInfo fieldMetaInfo;

    private boolean catString;

    private int anc;

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean variable) {
        this.variable = variable;
    }

    public FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    public void setFieldMetaInfo(FieldInfo fieldMetaInfo) {
        this.fieldMetaInfo = fieldMetaInfo;
    }

    public boolean isCatString() {
        return catString;
    }

    public void setCatString(boolean catString) {
        this.catString = catString;
    }

    public int getAnc() {
        return anc;
    }

    public void setAnc(int anc) {
        this.anc = anc;
    }
}
