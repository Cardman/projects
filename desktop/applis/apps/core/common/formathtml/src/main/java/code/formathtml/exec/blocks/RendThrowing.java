package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendThrowing extends RendLeaf {

    private final RendOperationNodeListOff exp;

    public RendThrowing(CustList<RendDynOperationNode> _res, int _offset) {
        exp = new RendOperationNodeListOff(_res,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(exp.getOffset());
        Argument argument_ = RenderExpUtil.calculateReuse(exp.getList(), _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        Struct o_ = argument_.getStruct();
        _rendStack.getStackCall().setCallingState(new CustomFoundExc(o_));
    }

}
