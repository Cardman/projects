package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetClassVariableInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class OneLoopExpressionsContent extends LoopExpressionsContent {

    private final String variableName;
    private final int variableNameOffset;

    private final boolean eq;
    private final int eqOffset;
    public OneLoopExpressionsContent(OffsetClassVariableInfo _variable, OffsetStringInfo _from,
                                     OffsetStringInfo _exp, OffsetStringInfo _step, OffsetBooleanInfo _eq, OffsetStringInfo _clName) {
        super(_variable.getClassName(), _from, _exp, _step, _clName);
        variableName = _variable.getVariableName().getInfo();
        variableNameOffset = _variable.getVariableName().getOffset();
        eq = _eq.isInfo();
        eqOffset = _eq.getOffset();
    }

    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getEqOffset() {
        return eqOffset;
    }

    public boolean isEq() {
        return eq;
    }
}
