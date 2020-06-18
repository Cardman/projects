package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecReturnMethod;
import code.expressionlanguage.methods.ReturnMethod;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

public final class AssReturnMethod extends AssAbruptBlock implements AssBuildableElMethod {
    private CustList<AssOperationNode> opNode;
    AssReturnMethod(boolean _completeNormally, boolean _completeNormallyGroup, ExecReturnMethod _empty) {
        super(_completeNormally,_completeNormallyGroup);
        opNode = AssUtil.getExecutableNodes(_empty.getOpRet());
    }

    public boolean isEmpty() {
        return opNode.isEmpty();
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        if (!opNode.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opNode.last(),this,_cont);
        } else {
            buildEmptyEl(_cont,_a);
        }
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<SimpleAssignment> ass_;
        ass_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
            ass_.put(e.getKey(), e.getValue().assign());
        }
        _a.getAssignments().put(this, ass_);
    }
}
