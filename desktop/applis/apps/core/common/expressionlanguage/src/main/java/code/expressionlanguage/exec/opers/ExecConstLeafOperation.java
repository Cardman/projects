package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public final class ExecConstLeafOperation extends ExecLeafOperation {
    private final boolean filter;
    public ExecConstLeafOperation(boolean _filter, ExecOperationContent _l) {
        super(_l);
        filter = _filter;
    }

    public static boolean isFilter(ExecOperationNode _op) {
        if (!(_op instanceof ExecConstLeafOperation)) {
            return false;
        }
        return ((ExecConstLeafOperation)_op).filter;
    }
}
