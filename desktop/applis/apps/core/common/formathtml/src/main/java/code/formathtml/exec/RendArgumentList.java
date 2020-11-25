package code.formathtml.exec;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendArgumentList {
    private final ArgumentListCall arguments = new ArgumentListCall();
    private final CustList<RendDynOperationNode> filter = new CustList<RendDynOperationNode>();

    public ArgumentListCall getArguments() {
        return arguments;
    }

    public CustList<RendDynOperationNode> getFilter() {
        return filter;
    }

}
