package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendDoWhileCondition extends RendCondition {

    public RendDoWhileCondition(CustList<RendDynOperationNode> _op, int _offset) {
        super(_op,_offset);
    }
    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processDo(_stds,_ctx,this, _rendStack);
    }
}
