package code.formathtml.exec;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class AnchorCall {
    private final CustList<RendDynOperationNode> exps;
    private final CustList<Struct> args;

    public AnchorCall(CustList<RendDynOperationNode> _e, CustList<Struct> _a) {
        this.exps = _e;
        this.args = _a;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }

    public CustList<Struct> getArgs() {
        return args;
    }
}
