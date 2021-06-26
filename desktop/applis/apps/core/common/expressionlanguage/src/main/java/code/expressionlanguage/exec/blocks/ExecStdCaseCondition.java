package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;

public final class ExecStdCaseCondition extends ExecAbstractCaseCondition {

    private final Argument arg;

    public ExecStdCaseCondition(int _valueOffset, Argument _arg) {
        super(_valueOffset);
        arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }
}
