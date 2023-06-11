package code.expressionlanguage.exec;

import code.expressionlanguage.exec.variables.ViewVariable;
import code.util.CustList;

public final class StackCallReturnValue {
    private final StackCall stack;
    private final ArgumentWrapper retValue;
    private final CustList<CustList<ViewVariable>> variables;

    public StackCallReturnValue(StackCall _s, ArgumentWrapper _r, CustList<CustList<ViewVariable>> _v) {
        this.stack = _s;
        this.retValue = _r;
        this.variables = _v;
    }

    public StackCall getStack() {
        return stack;
    }

    public ArgumentWrapper getRetValue() {
        return retValue;
    }

    public CustList<CustList<ViewVariable>> getVariables() {
        return variables;
    }
}
