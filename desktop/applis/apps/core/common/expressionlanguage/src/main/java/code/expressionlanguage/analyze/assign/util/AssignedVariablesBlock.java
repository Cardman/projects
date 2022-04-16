package code.expressionlanguage.analyze.assign.util;

import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.blocks.AssReturnMethod;
import code.util.IdMap;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public final class AssignedVariablesBlock {

    private AssignedVariables finalVariablesGlobal = new AssignedVariables();
    private IdMap<AssBlock, AssignedVariables> finalVariables = new IdMap<AssBlock, AssignedVariables>();
    private IdMap<AssReturnMethod, StringMap<SimpleAssignment>> assignments = new IdMap<AssReturnMethod, StringMap<SimpleAssignment>>();

    private final StringMap<BoolVal> localVars = new StringMap<BoolVal>();
    private StringMap<BoolVal> variables = new StringMap<BoolVal>();
    private AnaCache cache = new AnaCache();

    public void putLocalVar(String _key, boolean _final) {
        localVars.put(_key, ComparatorBoolean.of(_final));
    }

    public boolean isFinalLocalVar(String _key) {
        return localVars.getVal(_key) == BoolVal.TRUE;
    }

    public StringMap<BoolVal> getLocalVars() {
        return localVars;
    }

    public StringMap<BoolVal> getVariables() {
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
