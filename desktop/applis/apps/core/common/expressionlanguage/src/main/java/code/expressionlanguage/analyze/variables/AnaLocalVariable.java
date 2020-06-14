package code.expressionlanguage.analyze.variables;

public class AnaLocalVariable {

    private String className;

    private boolean finalVariable;

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
