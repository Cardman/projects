package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetClassVariableInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class TableLoopExpressionContent extends LoopExpressionContent {

    private final String classNameFirst;

    private final int classNameOffsetFirst;

    private final String classNameSecond;

    private final int classNameOffsetSecond;

    private final String variableNameFirst;

    private final int variableNameOffsetFirst;

    private final String variableNameSecond;

    private final int variableNameOffsetSecond;

    public TableLoopExpressionContent(OffsetClassVariableInfo _variable,
                                      OffsetClassVariableInfo _variableSec, OffsetStringInfo _exp, OffsetStringInfo _clName) {
        super(_exp, _clName);
        classNameFirst = _variable.getClassName().getInfo();
        classNameOffsetFirst = _variable.getClassName().getOffset();
        variableNameFirst = _variable.getVariableName().getInfo();
        variableNameOffsetFirst = _variable.getVariableName().getOffset();
        classNameSecond = _variableSec.getClassName().getInfo();
        classNameOffsetSecond = _variableSec.getClassName().getOffset();
        variableNameSecond = _variableSec.getVariableName().getInfo();
        variableNameOffsetSecond = _variableSec.getVariableName().getOffset();
    }

    public int getClassNameOffsetFirst() {
        return classNameOffsetFirst;
    }

    public int getVariableNameOffsetFirst() {
        return variableNameOffsetFirst;
    }

    public int getClassNameOffsetSecond() {
        return classNameOffsetSecond;
    }

    public int getVariableNameOffsetSecond() {
        return variableNameOffsetSecond;
    }

    public String getClassNameFirst() {
        return classNameFirst;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public String getClassNameSecond() {
        return classNameSecond;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }
}
