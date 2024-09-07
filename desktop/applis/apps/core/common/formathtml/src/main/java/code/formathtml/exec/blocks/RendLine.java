package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendLine extends RendLeaf implements RendWithEl {

    private final RendOperationNodeListOff exp;

    public RendLine(CustList<RendDynOperationNode> _res, int _offset) {
        exp = new RendOperationNodeListOff(_res,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(exp.getOffset());
        if (RenderExpUtil.getFinalArg(exp.getList(), _ctx, _rendStack) == null) {
            return;
        }
        processBlock(_cont, _stds, _ctx, _rendStack);
    }
}
