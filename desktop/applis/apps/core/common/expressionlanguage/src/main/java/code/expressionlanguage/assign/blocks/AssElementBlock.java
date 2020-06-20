package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecInnerTypeOrElement;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssElementBlock extends AssLeaf implements AssInfoBlock {
    private String fieldName;
    private CustList<AssOperationNode> opList;

    public AssElementBlock(ExecInnerTypeOrElement _e) {
        super(true, true);
        fieldName = _e.getUniqueFieldName();
        opList = AssUtil.getExecutableNodes(_e.getOpValue());
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
        AssignedVariables varsAss_ = _a.getFinalVariables().getVal(this);
        StringMap<SimpleAssignment> as_ = varsAss_.getFieldsRoot();
        as_.putAllMap(AssignmentsUtil.assignAfterClassic(varsAss_.getFieldsRootBefore()));
        as_.put(fieldName, Assignment.assignClassic(true, false));
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.getInitFields().add(fieldName);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this,_cont);
    }
}
