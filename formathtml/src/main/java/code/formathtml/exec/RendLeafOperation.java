package code.formathtml.exec;

import code.expressionlanguage.opers.LeafOperation;

public abstract class RendLeafOperation extends RendDynOperationNode {

    RendLeafOperation(LeafOperation _l) {
        super(_l);
    }

    @Override
    public RendDynOperationNode getFirstChild() {
        return null;
    }
}
