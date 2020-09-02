package code.expressionlanguage.assign.util;

import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.blocks.AssReturnMethod;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<AssBlock, AssignedVariables> finalVariables = new IdMap<AssBlock, AssignedVariables>();
    private IdMap<AssReturnMethod, StringMap<SimpleAssignment>> assignments = new IdMap<AssReturnMethod, StringMap<SimpleAssignment>>();

    private final StringMap<AnaLocalVariable> localVars = new StringMap<AnaLocalVariable>();

    public void putLocalVar(String _key, AnaLocalVariable _var) {
        localVars.put(_key, _var);
    }

    public boolean isFinalLocalVar(String _key) {
        return localVars.getVal(_key).isFinalVariable();
    }

    public StringMap<AnaLocalVariable> getLocalVars() {
        return localVars;
    }

    public AssignedVariables getFinalVariablesGlobal() {
        return finalVariablesGlobal;
    }

    public IdMap<AssBlock, AssignedVariables> getFinalVariables() {
        return finalVariables;
    }

    public IdMap<AssReturnMethod, StringMap<SimpleAssignment>> getAssignments() {
        return assignments;
    }

}
