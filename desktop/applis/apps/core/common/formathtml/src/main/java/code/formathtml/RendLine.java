package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;
import code.util.StringList;

public final class RendLine extends RendLeaf implements RendWithEl, RendReducableOperations {

    private final String expression;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opExp;
    RendLine(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc) {
        AnalyzedPageEl page_ = _cont.getContext().getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        opExp = RenderExpUtil.getAnalyzedOperations(expression,expressionOffset,0,_cont, _anaDoc, _cont.getContext().getAnalyzing());
        if (page_.isMerged()) {
            StringList vars_ = page_.getVariablesNames();
            RendDeclareVariable declaring_ = (RendDeclareVariable) getPreviousSibling();
            String import_ = declaring_.getImportedClassName();
            String t_ = inferOrObject(_cont,import_);
            AffectationOperation.processInfer(_cont.getContext(), t_);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        page_.setMerged(false);
        page_.setAcceptCommaInstr(false);
        page_.setFinalVariable(false);
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
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrValue());
        RenderExpUtil.calculateReuse(opExp, _cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        processBlock(_cont);
    }
}
