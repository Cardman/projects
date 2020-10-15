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

    public void setVariable(boolean _variable) {
        this.variable = _variable;
    }

    public FieldInfo getFieldMetaInfo() {
        return fieldMetaInfo;
    }

    public void setFieldMetaInfo(FieldInfo _fieldMetaInfo) {
        this.fieldMetaInfo = _fieldMetaInfo;
    }

    public boolean isCatString() {
        return catString;
    }

    public void setCatString(boolean _catString) {
        this.catString = _catString;
    }

    public int getAnc() {
        return anc;
    }

    public void setAnc(int _anc) {
        this.anc = _anc;
    }
}
