package code.expressionlanguage.exec;

import code.expressionlanguage.exec.variables.ViewPage;
import code.util.CustList;

public final class StackCallReturnValue {
    private final StackCall stack;
    private final ArgumentWrapper retValue;
    private final CustList<ViewPage> variables;
    private final boolean returning;

    public StackCallReturnValue(StackCall _s, ArgumentWrapper _r, CustList<ViewPage> _v, boolean _e) {
        this.stack = _s;
        this.retValue = _r;
        this.variables = _v;
        this.returning = _e;
    }

    public StackCall getStack() {
        return stack;
    }

    public ArgumentWrapper getRetValue() {
        return retValue;
    }

    public CustList<ViewPage> getVariables() {
        return variables;
    }

    public boolean isReturning() {
        return returning;
    }
}
