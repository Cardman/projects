package code.expressionlanguage.analyze.assign.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public final class AssDeclareVariable extends AssLeaf implements AssBuildableElMethod {

    AssDeclareVariable(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        buildEmptyEl(_a);
    }
}
