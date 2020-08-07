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

    private final CustList<StringMap<AnaLoopVariable>> mutableVars = new CustList<StringMap<AnaLoopVariable>>();

    private final CustList<StringMap<AnaLocalVariable>> localVars = new CustList<StringMap<AnaLocalVariable>>();

    public void initMutableLoopVars() {
        mutableVars.add(new StringMap<AnaLoopVariable>());
    }

    public void removeMutableLoopVars() {
        mutableVars.removeLast();
    }

    public void putMutableLoopVar(String _key, AnaLoopVariable _var) {
        mutableVars.last().put(_key, _var);
    }

    public void initLocalVars() {
        localVars.add(new StringMap<AnaLocalVariable>());
    }

    public void removeLocalVars() {
        localVars.removeLast();
    }

    public void putLocalVar(String _key, AnaLocalVariable _var) {
        localVars.last().put(_key, _var);
    }

    public boolean isFinalMutableLoopVar(String _key, int _index) {
        return mutableVars.get(_index+1).getVal(_key).isFinalVariable();
    }

    public boolean isFinalLocalVar(String _key, int _index) {
        return localVars.get(_index).getVal(_key).isFinalVariable();
    }

    public CustList<StringMap<AnaLocalVariable>> getLocalVars() {
        return localVars;
    }

    public CustList<StringMap<AnaLoopVariable>> getMutableVars() {
        return mutableVars;
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
