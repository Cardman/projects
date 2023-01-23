package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public abstract class RendAbstractCatchEval extends RendParentBlock implements RendWithEl {

    private final RendOperationNodeListOff condition;

    protected RendAbstractCatchEval(CustList<RendDynOperationNode> _ls, int _off) {
        condition = new RendOperationNodeListOff(_ls,_off);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processCatch(_cont,_stds,_ctx,_rendStack,this);
    }

    public RendOperationNodeListOff getCondition() {
        return condition;
    }
}
