package code.formathtml.exec.opers;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public final class RendConstLeafOperation extends RendLeafOperation {
    private boolean filter;
    public RendConstLeafOperation(boolean _filter, ExecOperationContent _content) {
        super(_content);
        filter = _filter;
    }
    public static boolean isFilter(RendDynOperationNode _op) {
        if (!(_op instanceof RendConstLeafOperation)) {
            return false;
        }
        return ((RendConstLeafOperation)_op).filter;
    }
}
