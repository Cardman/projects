package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendThrowing extends RendLeaf implements RendWithEl, RendReducableOperations {

    private int expressionOffset;

    private CustList<RendDynOperationNode> opThrow;

    public RendThrowing(int _offsetTrim, CustList<RendDynOperationNode> _res, int _offset) {
        super(_offsetTrim);
        expressionOffset = _offset;
        opThrow = _res;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        Argument argument_ = RenderExpUtil.calculateReuse(opThrow, _cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        Struct o_ = argument_.getStruct();
        _ctx.setException(o_);
    }

}
