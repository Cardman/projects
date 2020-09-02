package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;

public final class AssConstantOperation extends AssLeafOperation {
    AssConstantOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssUtil.setAssignments(this,_ass,_a);
    }
}
