package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssStdUnaryMethodOperation extends AssMethodOperation {
    AssStdUnaryMethodOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        analyzeStdAssignmentAfter(_conf,_ass,_a);
    }
}
