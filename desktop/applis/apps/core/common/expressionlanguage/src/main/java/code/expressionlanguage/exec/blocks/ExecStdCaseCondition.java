package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;

public final class ExecStdCaseCondition extends ExecAbstractCaseCondition {

    private final Argument arg;

    public ExecStdCaseCondition(Argument _arg) {
        arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }
}
