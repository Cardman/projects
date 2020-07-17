package code.expressionlanguage.analyze.variables;

import code.expressionlanguage.common.ConstType;

public class AnaLocalVariable {

    private String className;

    private boolean finalVariable;

    private ConstType constType;

    private int ref;

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean _finalVariable) {
        finalVariable = _finalVariable;
    }

    public ConstType getConstType() {
        return constType;
    }

    public void setConstType(ConstType constType) {
        this.constType = constType;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int _ref) {
        ref = _ref;
    }
}
