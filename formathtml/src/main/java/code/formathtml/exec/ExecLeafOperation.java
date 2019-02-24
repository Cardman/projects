package code.formathtml.exec;

import code.expressionlanguage.opers.LeafOperation;
import code.expressionlanguage.opers.exec.Operable;
import code.util.CustList;

public abstract class ExecLeafOperation extends ExecDynOperationNode {

    ExecLeafOperation(LeafOperation _l) {
        super(_l);
    }
    @Override
    public final ExecDynOperationNode getFirstChild() {
        return null;
    }
}
