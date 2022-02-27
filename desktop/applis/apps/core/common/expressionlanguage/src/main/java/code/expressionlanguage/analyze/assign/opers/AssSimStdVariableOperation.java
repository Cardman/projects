package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssSimDeclareVariable;
import code.expressionlanguage.analyze.assign.blocks.AssSimForMutableIterativeLoop;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.opers.VariableOperationUse;

public final class AssSimStdVariableOperation extends AssLeafOperation {

    private String variableName;
    private boolean declare;
    private boolean finalVariable;
    private int deep;
    private OperationNode analyzed;

    AssSimStdVariableOperation(VariableOperation _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = true;
        finalVariable = _ex.isFinalVariable();
        deep = _ex.getDeep();
        analyzed = _ex;
    }

    AssSimStdVariableOperation(VariableOperationUse _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = false;
        finalVariable = _ex.isFinalVariable();
        deep = _ex.getDeep();
        analyzed = _ex;
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssBlock pr_ = _ass.getPreviousSibling();
        if (pr_ instanceof AssSimDeclareVariable) {
            ((AssSimDeclareVariable)pr_).getAssignedVariables().add(variableName);
        }
        if (_ass instanceof AssSimForMutableIterativeLoop) {
            ((AssSimForMutableIterativeLoop)_ass).getAssignedVariables().add(variableName);
        }
        if (declare) {
            _a.getVariables().put(variableName,false);
            _a.putLocalVar(variableName, finalVariable);
        }

    }

    public int getDeep() {
        return deep;
    }

    public String getVariableName() {
        return variableName;
    }

    public OperationNode getAnalyzed() {
        return analyzed;
    }
}
