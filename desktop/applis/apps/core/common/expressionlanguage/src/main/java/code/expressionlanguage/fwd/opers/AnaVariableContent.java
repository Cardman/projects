package code.expressionlanguage.fwd.opers;

public final class AnaVariableContent {

    private boolean variable;

    private String variableName = "";

    private final int off;

    private int deep;
    private boolean ref;
    public AnaVariableContent(int _off) {
        off = _off;
    }

    public boolean isVariable() {
        return variable;
    }

    public void setVariable(boolean _variable) {
        this.variable = _variable;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String _variableName) {
        this.variableName = _variableName;
    }

    public int getOff() {
        return off;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int _deep) {
        this.deep = _deep;
    }

    public boolean isRef() {
        return ref;
    }

    public void setRef(boolean _r) {
        this.ref = _r;
    }
}
