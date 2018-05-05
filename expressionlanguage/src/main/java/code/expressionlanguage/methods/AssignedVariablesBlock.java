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

    public void setFinalVariablesGlobal(AssignedVariables _finalVariablesGlobal) {
        finalVariablesGlobal = _finalVariablesGlobal;
    }

    public void setFinalVariables(IdMap<Block, AssignedVariables> _finalVariables) {
        finalVariables = _finalVariables;
    }
}
