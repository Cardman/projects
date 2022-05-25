package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.syntax.ResultExpression;

public abstract class LoopExpressionsContent extends CommonLoopExpressionContent {

    private final String className;
    private final int classNameOffset;

    private final String init;
    private final int initOffset;

    private final String expression;
    private final int expressionOffset;

    private final String step;
    private final int stepOffset;

    private final ResultExpression resInit = new ResultExpression();
    private final ResultExpression resExp = new ResultExpression();
    private final ResultExpression resStep = new ResultExpression();

    protected LoopExpressionsContent(OffsetStringInfo _cl,OffsetStringInfo _from,
                                     OffsetStringInfo _exp, OffsetStringInfo _step, OffsetStringInfo _clName) {
        super(_clName);
        className = _cl.getInfo();
        classNameOffset = _cl.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _exp.getInfo();
        expressionOffset = _exp.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
    }

    public String getClassName() {
        return className;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public String getInit() {
        return init;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public String getExpression() {
        return expression;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getStep() {
        return step;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    public ResultExpression getResInit() {
        return resInit;
    }

    public ResultExpression getResExp() {
        return resExp;
    }

    public ResultExpression getResStep() {
        return resStep;
    }
}
