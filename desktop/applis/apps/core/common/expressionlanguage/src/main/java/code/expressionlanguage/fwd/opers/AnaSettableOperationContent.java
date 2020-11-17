package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.common.ClassField;

public final class AnaSettableOperationContent {
    private boolean variable;
    private String realType;
    private boolean staticField;
    private boolean finalField;
    private ClassField classField;

    private boolean catString;

    private int anc;

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean _variable) {
        this.variable = _variable;
    }

    public ClassField getClassField() {
        return classField;
    }

    public void setClassField(ClassField _classField) {
        this.classField = _classField;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean _staticField) {
        this.staticField = _staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public void setFinalField(boolean _finalField) {
        this.finalField = _finalField;
    }

    public String getRealType() {
        return realType;
    }

    public void setRealType(String _realType) {
        this.realType = _realType;
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
