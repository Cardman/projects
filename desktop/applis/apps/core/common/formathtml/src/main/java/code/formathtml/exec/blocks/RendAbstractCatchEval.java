package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFilterContent;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendAbstractCatchEval extends RendParentBlock implements RendWithEl {

    private final RendOperationNodeListOff condition;
    private final ExecFilterContent content;

    private final boolean throwIfGuardError;
    private final boolean catchAll;
    public RendAbstractCatchEval(CustList<RendDynOperationNode> _ls, int _off, ExecFilterContent _filter, boolean _thr, boolean _ca) {
        condition = new RendOperationNodeListOff(_ls,_off);
        content = _filter;
        throwIfGuardError = _thr;
        catchAll = _ca;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processCatch(_cont,_stds,_ctx,_rendStack,this);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!content.getVariableName().isEmpty()) {
            _ip.removeRefVar(content.getVariableName());
        }
    }
    public RendOperationNodeListOff getCondition() {
        return condition;
    }

    public ExecFilterContent getContent() {
        return content;
    }

    public boolean isThrowIfGuardError() {
        return throwIfGuardError;
    }

    public boolean isCatchAll() {
        return catchAll;
    }
}
