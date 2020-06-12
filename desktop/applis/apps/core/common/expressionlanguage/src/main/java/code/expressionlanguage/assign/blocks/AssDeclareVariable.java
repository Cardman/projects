package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.methods.DeclareVariable;

public final class AssDeclareVariable extends AssLeaf implements AssBuildableElMethod {

    private final boolean finalVar;
    AssDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup, DeclareVariable _d) {
        super(_completeNormally,_completeNormallyGroup);
        finalVar = _d.isFinalVariable();
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        buildEmptyEl(_cont,_a);
        _cont.getAnalyzing().setFinalVariable(finalVar);
    }
}
