package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.*;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendLine extends AnaRendLeaf {

    private final String expression;

    private int expressionOffset;

    private OperationNode root;
    AnaRendLine(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        root = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            AnaRendDeclareVariable declaring_ = (AnaRendDeclareVariable) getPreviousSibling();
            if (declaring_.isRefVariable()) {
                Line.checkOpers(root,_page);
            }
            String import_ = declaring_.getImportedClassName();
            String t_ = inferOrObject(import_, _page);
            AffectationOperation.processInfer(t_, _page);
            declaring_.getVariableNames().addAllElts(vars_);
        }
        _page.setMerged(false);
        _page.setRefVariable(false);
        _page.setAcceptCommaInstr(false);
        _page.setFinalVariable(false);
    }

    public OperationNode getRoot() {
        return root;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
