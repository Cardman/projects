package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public final class RendThrowing extends RendLeaf implements RendBuildableElMethod, RendReducableOperations {

    private final String expression;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opThrow;
    RendThrowing(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_VALUE);
        opThrow = RenderExpUtil.getAnalyzedOperations(expression,0, _cont, Calculation.staticCalculation(_doc.getStaticContext()));

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
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
        Argument argument_ = RenderExpUtil.calculateReuse(opThrow, _cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        Struct o_ = argument_.getStruct();
        _cont.setException(o_);
    }

}
