package code.expressionlanguage.fwd.opers;

public final class ExecVariableContent {

    private final boolean variable;

    private final boolean catString;

    private final String variableName;

    private final int off;

    private final int deep;

    public ExecVariableContent(AnaVariableContent _cont) {
        variable = _cont.isVariable();
        catString = _cont.isCatString();
        variableName = _cont.getVariableName();
        off = _cont.getOff();
        deep = _cont.getDeep();
    }

    public boolean isVariable() {
        return variable;
    }

    public boolean isCatString() {
        return catString;
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
}
