package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class LoopExpressionContent extends CommonLoopExpressionContent {

    private final String expression;

    private final int expressionOffset;

    protected LoopExpressionContent(OffsetStringInfo _exp, OffsetStringInfo _clName) {
        super(_clName);
        expression = _exp.getInfo();
        expressionOffset = _exp.getOffset();
    }

    public String getExpression() {
        return expression;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

}
