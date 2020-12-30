package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendLine extends RendLeaf implements RendWithEl {

    private final int expressionOffset;

    private final CustList<RendDynOperationNode> opExp;

    public RendLine(int _offsetTrim, CustList<RendDynOperationNode> _res, int _offset) {
        super(_offsetTrim);
        expressionOffset = _offset;
        opExp = _res;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        RenderExpUtil.calculateReuse(opExp, _cont, _stds, _ctx, _stack, _rendStack);
        if (_ctx.callsOrException(_stack)) {
            return;
        }
        processBlock(_cont, _stds, _ctx, _stack, _rendStack);
    }
}
