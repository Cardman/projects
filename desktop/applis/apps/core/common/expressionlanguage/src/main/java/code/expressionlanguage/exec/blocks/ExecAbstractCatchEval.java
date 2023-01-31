package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecAbstractCatchEval extends ExecBracedBlock implements WithEl {

    private final ExecOperationNodeListOff condition;
    private final ExecFilterContent content;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    public ExecAbstractCatchEval(CustList<ExecOperationNode> _ls, int _offset, ExecFilterContent _filterContent, boolean _thr, boolean _ca) {
        condition = new ExecOperationNodeListOff(_ls,_offset);
        content = _filterContent;
        throwIfGuardError = _thr;
        catchAll = _ca;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processCatch(_cont, _stack, this, condition);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        if (!content.getVariableName().isEmpty()) {
            _ip.removeRefVar(content.getVariableName());
        }
    }
    public ExecFilterContent getContent() {
        return content;
    }

    public boolean isCatchAll() {
        return catchAll;
    }

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }
}
