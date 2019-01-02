package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.LeafOperation;
import code.util.CustList;

public abstract class ExecLeafOperation extends ExecOperationNode {

    ExecLeafOperation(LeafOperation _l) {
        super(_l);
    }

    @Override
    public final CustList<Operable> getChildrenOperable() {
        return new CustList<Operable>();
    }
    @Override
    public final ExecOperationNode getFirstChild() {
        return null;
    }
}
