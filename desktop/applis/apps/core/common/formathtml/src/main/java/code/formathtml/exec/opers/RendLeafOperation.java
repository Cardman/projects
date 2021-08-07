package code.formathtml.exec.opers;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class RendLeafOperation extends RendDynOperationNode {

    protected RendLeafOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public RendDynOperationNode getFirstChild() {
        return null;
    }
}
