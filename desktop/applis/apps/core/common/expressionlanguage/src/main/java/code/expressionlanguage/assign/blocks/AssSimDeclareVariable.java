package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.util.StringList;

public final class AssSimDeclareVariable extends AssLeaf implements AssBuildableElMethod {
    private StringList assignedVariables = new StringList();
    AssSimDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        //simple assignment
    }

    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
