package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssDeclareVariable extends AssLeaf implements AssBuildableElMethod {

    AssDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        buildEmptyEl(_a);
    }
}
