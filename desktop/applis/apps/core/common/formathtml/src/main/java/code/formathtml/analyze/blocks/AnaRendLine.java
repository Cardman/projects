package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendLine extends AnaRendLeaf implements AnaRendBuildEl {

    private final String expression;

    private final int expressionOffset;
    private final ResultExpression resultExpression = new ResultExpression();

    private OperationNode root;
    AnaRendLine(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(resultExpression.getSumOffset());
        _page.zeroOffset();
        root = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpression);
        if (_page.getLineDeclarator() != null) {
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
        _page.setLineDeclarator(null);
        _page.setAcceptCommaInstr(false);
    }

    public OperationNode getRoot() {
        return root;
    }

    public ResultExpression getRes() {
        return resultExpression;
    }

    public String getExpression() {
        return expression;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }
}
