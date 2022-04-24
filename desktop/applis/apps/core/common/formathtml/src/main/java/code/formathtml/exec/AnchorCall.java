package code.formathtml.exec;

import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class AnchorCall {
    private final CustList<RendDynOperationNode> exps;
    private final CustList<AbstractWrapper> args;

    public AnchorCall(CustList<RendDynOperationNode> _e, CustList<AbstractWrapper> _a) {
        this.exps = _e;
        this.args = _a;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }

    public CustList<AbstractWrapper> getArgs() {
        return args;
    }
}
