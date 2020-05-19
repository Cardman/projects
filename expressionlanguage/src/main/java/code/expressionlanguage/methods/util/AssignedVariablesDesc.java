package code.expressionlanguage.methods.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.util.EntryCust;
import code.util.IdMap;

public final class AssignedVariablesDesc {
    private final AssignedVariables varsWhile;
    private final IdMap<Block, AssignedVariables> allDesc;

    public AssignedVariablesDesc(ContextEl _an, Block _b) {
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables varsWhile_ = null;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
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

    public IdMap<Block, AssignedVariables> getAllDesc() {
        return allDesc;
    }
}
