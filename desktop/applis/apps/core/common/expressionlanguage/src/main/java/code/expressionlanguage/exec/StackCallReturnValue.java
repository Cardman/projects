package code.expressionlanguage.exec;

import code.expressionlanguage.exec.variables.ViewPage;
import code.util.CustList;

public final class StackCallReturnValue {
    private final StackCall stack;
    private final CustList<ViewPage> variables;

    public StackCallReturnValue(StackCall _s, CustList<ViewPage> _v) {
        this.stack = _s;
        this.variables = _v;
    }

    public StackCall getStack() {
        return stack;
    }

    public CustList<ViewPage> getVariables() {
        return variables;
    }

}
