package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.AbstractDotOperation;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.ExecAbstractDotOperation;

public final class AssDotOperation extends AssMultMethodOperation {
    AssDotOperation(AbstractDotOperation _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _ass,_a,_nextSibling, _previous);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        analyzeStdAssignmentAfter(_conf,_ass,_a);
    }
}
