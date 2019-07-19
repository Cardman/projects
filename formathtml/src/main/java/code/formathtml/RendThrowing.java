package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.FunctionBlock;
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

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        page_.setGlobalOffset(expressionOffset);
        opThrow = ElRenderUtil.getAnalyzedOperations(expression,0, _cont, Calculation.staticCalculation(_doc.isStaticContext()));

    }
    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opThrow.last();
        opThrow = ElRenderUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setOffset(0);
//        ip_.setGlobalOffset(expressionOffset);
        Argument argument_ = ElRenderUtil.calculateReuse(opThrow, _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        Struct o_ = argument_.getStruct();
        _cont.setException(o_);
    }

}
