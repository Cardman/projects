package code.expressionlanguage.analyze.assign.util;

import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.util.EntryCust;
import code.util.IdMap;

public final class AssignedVariablesDesc {
    private final AssignedVariables varsWhile;
    private final IdMap<AssBlock, AssignedVariables> allDesc;

    public AssignedVariablesDesc(AssBlock _b, AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> allDesc_ = new IdMap<AssBlock, AssignedVariables>();
        boolean add_ = false;
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _a.getFinalVariables();
        AssignedVariables varsWhile_ = null;
        for (EntryCust<AssBlock, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == _b) {
                add_ = true;
                varsWhile_ = e.getValue();
            } else if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        varsWhile = varsWhile_;
        allDesc = allDesc_;
    }

    public AssignedVariables getVarsWhile() {
        return varsWhile;
    }

    public IdMap<AssBlock, AssignedVariables> getAllDesc() {
        return allDesc;
    }
}
