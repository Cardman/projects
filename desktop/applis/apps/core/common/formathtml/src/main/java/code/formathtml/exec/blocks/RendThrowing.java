package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendThrowing extends RendLeaf implements RendWithEl {

    private final RendOperationNodeListOff exp;

    public RendThrowing(CustList<RendDynOperationNode> _res, int _offset) {
        exp = new RendOperationNodeListOff(_res,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(exp.getOffset());
        Struct argument_ = RenderExpUtil.getFinalArg(exp.getList(), _ctx, _rendStack);
        if (argument_ == null) {
            return;
        }
        _rendStack.getStackCall().setCallingState(new CustomFoundExc(argument_));
    }

}
