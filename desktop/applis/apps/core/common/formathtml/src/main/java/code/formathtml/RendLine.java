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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrValue());
        opExp = RenderExpUtil.getAnalyzedOperations(expression, 0, _anaDoc, _page);
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            RendDeclareVariable declaring_ = (RendDeclareVariable) getPreviousSibling();
            String import_ = declaring_.getImportedClassName();
            String t_ = inferOrObject(import_, _page);
            AffectationOperation.processInfer(t_, _page);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
        _page.setFinalVariable(false);
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
