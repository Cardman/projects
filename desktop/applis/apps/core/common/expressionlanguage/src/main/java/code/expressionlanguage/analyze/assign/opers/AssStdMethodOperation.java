package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;

public final class AssStdMethodOperation extends AssMultMethodOperation {
    AssStdMethodOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_ass,_a,_nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        analyzeStdAssignmentAfter(_ass,_a, _page);
    }
}
