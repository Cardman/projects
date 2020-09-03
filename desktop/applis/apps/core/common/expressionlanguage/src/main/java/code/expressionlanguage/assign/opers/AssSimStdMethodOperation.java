package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssSimStdMethodOperation extends AssSimMultMethodOperation {
    AssSimStdMethodOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        //simple assignment
    }
}
