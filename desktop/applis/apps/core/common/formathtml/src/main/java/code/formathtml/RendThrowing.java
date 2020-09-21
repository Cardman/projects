package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;

public final class RendThrowing extends RendLeaf implements RendWithEl, RendReducableOperations {

    private final String expression;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opThrow;
    RendThrowing(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setOffset(0);
        _page.setGlobalOffset(expressionOffset);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        opThrow = RenderExpUtil.getAnalyzedOperations(expression, 0, _anaDoc, _page);

    }
    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opThrow.last();
        opThrow = RenderExpUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        Argument argument_ = RenderExpUtil.calculateReuse(opThrow, _cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        Struct o_ = argument_.getStruct();
        _cont.setException(o_);
    }

}
