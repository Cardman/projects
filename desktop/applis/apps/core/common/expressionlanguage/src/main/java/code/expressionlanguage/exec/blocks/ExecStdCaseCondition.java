package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;

public final class ExecStdCaseCondition extends ExecAbstractCaseCondition {

    private Argument arg;

    public ExecStdCaseCondition(int _valueOffset, Argument _arg, int _offsetTrim) {
        super(_valueOffset, _offsetTrim);
        arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }
}
