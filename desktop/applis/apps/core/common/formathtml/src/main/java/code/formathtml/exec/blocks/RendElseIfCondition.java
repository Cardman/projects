package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendElseIfCondition extends RendCondition implements RendWithEl {

    public RendElseIfCondition(int _offsetTrim, CustList<RendDynOperationNode> _op, int _offset) {
        super(_offsetTrim,_op,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        processElseIf(_cont,_stds,_ctx,this, _stack, _rendStack);
    }

}
