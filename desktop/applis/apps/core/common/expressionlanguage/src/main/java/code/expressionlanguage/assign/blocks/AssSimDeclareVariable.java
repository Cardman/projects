package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.util.StringList;

public final class AssSimDeclareVariable extends AssLeaf implements AssBuildableElMethod {
    private StringList assignedVariables = new StringList();
    AssSimDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        //simple assignment
    }

    public StringList getAssignedVariables() {
        return assignedVariables;
    }
}
