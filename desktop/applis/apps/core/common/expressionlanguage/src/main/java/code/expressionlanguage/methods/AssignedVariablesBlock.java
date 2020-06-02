package code.expressionlanguage.methods;

import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.util.IdMap;
import code.util.StringMap;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<Block, AssignedVariables> finalVariables = new IdMap<Block, AssignedVariables>();
    private IdMap<ReturnMethod, StringMap<SimpleAssignment>> assignments = new IdMap<ReturnMethod, StringMap<SimpleAssignment>>();

    public AssignedVariables getFinalVariablesGlobal() {
        return finalVariablesGlobal;
    }

    public IdMap<Block, AssignedVariables> getFinalVariables() {
        return finalVariables;
    }

    public IdMap<ReturnMethod, StringMap<SimpleAssignment>> getAssignments() {
        return assignments;
    }

}
