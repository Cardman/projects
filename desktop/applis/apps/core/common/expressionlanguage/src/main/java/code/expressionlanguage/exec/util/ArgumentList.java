package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ArgumentList {
    private final ArgumentListCall arguments = new ArgumentListCall();
    private final CustList<ExecOperationNode> filter = new CustList<ExecOperationNode>();

    public ArgumentListCall getArguments() {
        return arguments;
    }

    public CustList<ExecOperationNode> getFilter() {
        return filter;
    }
}
