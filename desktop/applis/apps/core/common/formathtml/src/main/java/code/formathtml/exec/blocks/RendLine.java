package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendLine extends RendLeaf implements RendWithEl, RendReducableOperations {

    private int expressionOffset;

    private CustList<RendDynOperationNode> opExp;

    public RendLine(int _offsetTrim, CustList<RendDynOperationNode> _res, int _offset) {
        super(_offsetTrim);
        expressionOffset = _offset;
        opExp = _res;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        RenderExpUtil.calculateReuse(opExp, _cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        processBlock(_cont);
    }
}
