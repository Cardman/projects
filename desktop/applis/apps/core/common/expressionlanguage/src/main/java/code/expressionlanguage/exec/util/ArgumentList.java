package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ArgumentList {
    private final CustList<Argument> arguments = new CustList<Argument>();
    private final CustList<ExecOperationNode> filter = new CustList<ExecOperationNode>();

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public CustList<ExecOperationNode> getFilter() {
        return filter;
    }
}
