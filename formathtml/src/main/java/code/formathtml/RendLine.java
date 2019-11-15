package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
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

    public String getExpression() {
        return expression;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        MethodAccessKind st_ = _doc.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_VALUE);
        opExp = RenderExpUtil.getAnalyzedOperations(expression,0,_cont, Calculation.staticCalculation(st_));
        if (_cont.isMerged()) {
            StringList vars_ = _cont.getVariablesNames();
            RendDeclareVariable declaring_ = (RendDeclareVariable) getPreviousSibling();
            String import_ = declaring_.getImportedClassName();
            String t_ = inferOrObject(_cont,import_);
            AffectationOperation.processInfer(_cont, t_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        _cont.setMerged(false);
        _cont.setAcceptCommaInstr(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opExp.last();
        opExp = RenderExpUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
        RenderExpUtil.calculateReuse(opExp, _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        processBlock(_cont);
    }
}
