package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public interface AssOperationNodeSim {
    void analyzeSimAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page);
}
