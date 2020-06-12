package code.expressionlanguage.assign.util;

import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.blocks.AssReturnMethod;
import code.util.IdMap;
import code.util.StringMap;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<AssBlock, AssignedVariables> finalVariables = new IdMap<AssBlock, AssignedVariables>();
    private IdMap<AssReturnMethod, StringMap<SimpleAssignment>> assignments = new IdMap<AssReturnMethod, StringMap<SimpleAssignment>>();

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
