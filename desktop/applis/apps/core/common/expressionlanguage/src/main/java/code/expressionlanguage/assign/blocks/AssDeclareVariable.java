package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssDeclareVariable extends AssLeaf implements AssBuildableElMethod {

    AssDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        buildEmptyEl(_cont,_a);
    }
}
