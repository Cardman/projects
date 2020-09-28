package code.formathtml.exec;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class RendLeafOperation extends RendDynOperationNode {

    RendLeafOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public RendDynOperationNode getFirstChild() {
        return null;
    }
}
