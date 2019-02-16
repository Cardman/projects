package code.expressionlanguage.variables;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class LocalVariable {

    private Struct element = NullStruct.NULL_VALUE;

    private String className;

    private boolean finalVariable;

    public Struct getStruct() {
        return element;
    }

    public void setStruct(Struct _element) {
        element = _element;
    }

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
}
