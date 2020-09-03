package code.expressionlanguage.assign.opers;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssSimStdLeafOperation extends AssLeafOperation {

    AssSimStdLeafOperation(OperationNode _ex) {
        super(_ex);
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, boolean _bool) {
        //simple assignement
    }
}
