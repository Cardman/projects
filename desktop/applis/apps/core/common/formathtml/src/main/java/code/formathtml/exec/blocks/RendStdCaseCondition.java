package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;

public final class RendStdCaseCondition extends RendAbstractCaseCondition {

    private final Argument arg;

    public RendStdCaseCondition(Argument _arg) {
        arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }
}
