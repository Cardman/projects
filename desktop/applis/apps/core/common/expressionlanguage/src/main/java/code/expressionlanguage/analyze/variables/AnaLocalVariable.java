package code.expressionlanguage.analyze.variables;

public class AnaLocalVariable {

    private String className;

    private boolean finalVariable;

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

    public int getRef() {
        return ref;
    }

    public void setRef(int _ref) {
        ref = _ref;
    }
}
