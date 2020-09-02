package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class AssFieldBlock extends AssLeaf implements AssInfoBlock {

    private CustList<AssOperationNode> opList;
    public AssFieldBlock(FieldBlock _f) {
        super(true,true);
        opList = AssUtil.getExecutableNodes(_f.getRoot());
    }

    @Override
    public void setAssignmentBeforeAsLeaf(ContextEl _an, AssignedVariablesBlock _a, AssBlock _b) {
        AssignedVariables ass_;
        if (_b == null) {
            ass_ = _a.getFinalVariablesGlobal();
            IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
            id_.put(this, ass_);
        } else {
            IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
            AssignedVariables parAss_ = id_.getVal(_b);
            AssignedVariables assBl_ = buildNewAssignedVariable();
            assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }

    @Override
    public void setAssignmentAfterAsLeaf(ContextEl _an, AssignedVariablesBlock _a, AssBlock _b) {
        StringMap<SimpleAssignment> fieldsRoot_ = _a.getFinalVariables().getVal(this).getFieldsRoot();
        for (EntryCust<String, SimpleAssignment> f: fieldsRoot_.entryList()) {
            String name_ = f.getKey();
            SimpleAssignment a_ = f.getValue();
            if (a_.isAssignedAfter()) {
                _an.getAnalyzing().getInitFields().add(name_);
            }
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this,_cont);
    }
}
