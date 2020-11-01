package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendEmptyText extends AnaRendLeaf {

    private final String expression;

    private boolean add = true;
    AnaRendEmptyText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (getNextSibling() instanceof AnaRendElseIfCondition) {
            add = false;
        } else if (getNextSibling() instanceof AnaRendElseCondition) {
            add = false;
        } else if (getNextSibling() instanceof AnaRendDoWhileCondition){
            add = false;
        } else if (getNextSibling() instanceof AnaRendAbstractCatchEval) {
            add = false;
        } else if (getNextSibling() instanceof AnaRendFinallyEval) {
            add = false;
        } else if (getNextSibling() instanceof AnaRendCaseCondition) {
            add = false;
        } else if (getNextSibling() instanceof AnaRendDefaultCondition) {
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
