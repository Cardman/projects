package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.opers.Calculation;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class RendLine extends RendLeaf implements RendWithEl, RendReducableOperations,RendBuildableElMethod {

    private final String expression;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opExp;
    RendLine(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
    public String getExpression() {
        return expression;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        boolean st_ = _doc.isStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElRenderUtil.getAnalyzedOperations(expression,0,_cont, Calculation.staticCalculation(st_));
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            ((RendDeclareVariable)getPreviousSibling()).getVariableNames().addAllElts(vars_);
        }
        _cont.setMerged(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opExp.last();
        opExp = ElRenderUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();

//        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ElRenderUtil.calculateReuse(opExp, _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        processBlock(_cont);
    }
}
