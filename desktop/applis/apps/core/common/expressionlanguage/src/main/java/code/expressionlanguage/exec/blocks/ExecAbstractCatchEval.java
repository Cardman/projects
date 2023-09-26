package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class ExecAbstractCatchEval extends ExecBracedBlock implements WithEl,ExecWithFilterContent {

    private final ExecOperationNodeListOff exp;
    private final ExecFilterContent content;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    public ExecAbstractCatchEval(ExecOperationNodeListOff _ex, ExecFilterContent _filterContent, boolean _thr, boolean _ca) {
        exp = _ex;
        content = _filterContent;
        throwIfGuardError = _thr;
        catchAll = _ca;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processCatch(_cont, _stack, this);
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

    public ExecOperationNodeListOff getExp() {
        return exp;
    }

    public boolean isCatchAll() {
        return catchAll;
    }

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }
}
