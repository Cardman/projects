package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendEmptyText extends AnaRendLeaf implements AnaRendBuildEl {

    private final String expression;

    private boolean add = true;
    AnaRendEmptyText(OffsetStringInfo _left, int _offset) {
        super(_offset);
        expression = _left.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (getNextSibling() instanceof AnaRendElseIfCondition || getNextSibling() instanceof AnaRendElseCondition || getNextSibling() instanceof AnaRendDoWhileCondition || getNextSibling() instanceof AnaRendAbstractCatchEval || getNextSibling() instanceof AnaRendFinallyEval || getNextSibling() instanceof AnaRendCaseCondition || getNextSibling() instanceof AnaRendDefaultCondition) {
            add = false;
        }
    }

    public boolean isAdd() {
        return add;
    }

    public String getExpression() {
        return expression;
    }
}
