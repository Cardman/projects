package code.expressionlanguage.fwd.opers;

public final class ExecVariableContent {

    private final boolean variable;

    private final String variableName;

    private final int off;

    private final int deep;
    private final boolean ref;

    public ExecVariableContent(AnaVariableContent _cont) {
        variable = _cont.isVariable();
        variableName = _cont.getVariableName();
        off = _cont.getOff();
        deep = _cont.getDeep();
        ref = _cont.isRef();
    }

    public boolean isVariable() {
        return variable;
    }

    public int getOff() {
        return off;
    }

    public int getDeep() {
        return deep;
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isRef() {
        return ref;
    }
}
