package code.expressionlanguage.analyze.assign.util;

import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssReturnMethod;
import code.util.IdMap;
import code.util.StringMap;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<AssBlock, AssignedVariables> finalVariables = new IdMap<AssBlock, AssignedVariables>();
    private IdMap<AssReturnMethod, StringMap<SimpleAssignment>> assignments = new IdMap<AssReturnMethod, StringMap<SimpleAssignment>>();

    private final StringMap<Boolean> localVars = new StringMap<Boolean>();
    private StringMap<Boolean> variables = new StringMap<Boolean>();
    private AnaCache cache = new AnaCache();

    public void putLocalVar(String _key, boolean _final) {
        localVars.put(_key, _final);
    }

    public boolean isFinalLocalVar(String _key) {
        return localVars.getVal(_key);
    }

    public StringMap<Boolean> getLocalVars() {
        return localVars;
    }

    public StringMap<Boolean> getVariables() {
        return variables;
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

    public AnaCache getCache() {
        return cache;
    }

    public void setCache(AnaCache _cache) {
        this.cache = _cache;
    }
}
