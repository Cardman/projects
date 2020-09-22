package code.expressionlanguage.analyze.reach.opers.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.reach.opers.ReachOperationNode;
import code.util.CustList;

public final class AnaArgumentList {
    private final CustList<Argument> arguments = new CustList<Argument>();
    private final CustList<ReachOperationNode> filter = new CustList<ReachOperationNode>();

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public CustList<ReachOperationNode> getFilter() {
        return filter;
    }

}
