package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.util.CustList;

public final class ArgumentListCall {
    private final CustList<Argument> arguments = new CustList<Argument>();
    private final CustList<AbstractWrapper> wrappers = new CustList<AbstractWrapper>();

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public CustList<AbstractWrapper> getWrappers() {
        return wrappers;
    }
}
