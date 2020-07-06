package code.expressionlanguage.analyze.variables;

public class AnaLoopVariable {

    private String className;

    private String indexClassName;

    private boolean finalVariable;

    private int ref;

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public void setFinalVariable(boolean _finalVariable) {
        finalVariable = _finalVariable;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public String getIndexClassName() {
        return indexClassName;
    }

    public void setIndexClassName(String _indexClassName) {
        indexClassName = _indexClassName;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int _ref) {
        ref = _ref;
    }
}
