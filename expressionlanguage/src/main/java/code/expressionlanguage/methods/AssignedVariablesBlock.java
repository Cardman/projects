package code.expressionlanguage.methods;

import code.expressionlanguage.opers.util.AssignedVariables;
import code.util.IdMap;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<Block, AssignedVariables> finalVariables = new IdMap<Block, AssignedVariables>();

    public AssignedVariables getFinalVariablesGlobal() {
        return finalVariablesGlobal;
    }

    public IdMap<Block, AssignedVariables> getFinalVariables() {
        return finalVariables;
    }

}
