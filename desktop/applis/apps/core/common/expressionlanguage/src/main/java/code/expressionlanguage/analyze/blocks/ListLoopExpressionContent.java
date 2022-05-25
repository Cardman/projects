package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetClassVariableInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class ListLoopExpressionContent extends LoopExpressionContent {

    private final String className;

    private final int classNameOffset;

    private final String variableName;

    private final int variableNameOffset;

    public ListLoopExpressionContent(OffsetClassVariableInfo _variable,
                                     OffsetStringInfo _exp, OffsetStringInfo _clName) {
        super(_exp, _clName);
        className = _variable.getClassName().getInfo();
        classNameOffset = _variable.getClassName().getOffset();
        variableName = _variable.getVariableName().getInfo();
        variableNameOffset = _variable.getVariableName().getOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

}
