package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class NatRendElseIfCondition extends NatRendCondition {

    public NatRendElseIfCondition(CustList<RendDynOperationNode> _op, int _offset) {
        super(_op,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendBlockHelp.processElseIf(_cont,_stds,_ctx,this, _rendStack);
    }

}
