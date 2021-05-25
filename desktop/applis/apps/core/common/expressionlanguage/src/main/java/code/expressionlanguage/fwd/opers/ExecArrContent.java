package code.expressionlanguage.fwd.opers;

public final class ExecArrContent {

    private final boolean variable;

    public ExecArrContent(AnaArrContent _cont) {
        variable = _cont.isVariable();
    }

    public ExecArrContent(boolean _variable) {
        variable = _variable;
    }
    public boolean isVariable() {
        return variable;
    }

}
