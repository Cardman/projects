package code.expressionlanguage.fwd.opers;

public final class ExecArrContent {

    private final boolean variable;

    private final boolean catString;

    public ExecArrContent(AnaArrContent _cont) {
        variable = _cont.isVariable();
        catString = _cont.isCatString();
    }

    public ExecArrContent(boolean _variable, boolean _catString) {
        variable = _variable;
        catString = _catString;
    }
    public boolean isVariable() {
        return variable;
    }

    public boolean isCatString() {
        return catString;
    }

}
