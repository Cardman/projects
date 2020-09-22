package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InnerTypeOrElement;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssElementBlock extends AssLeaf implements AssInfoBlock {
    private String fieldName;
    private CustList<AssOperationNode> opList;

    public AssElementBlock(InnerTypeOrElement _e) {
        super(true, true);
        fieldName = _e.getUniqueFieldName();
        opList = AssUtil.getExecutableNodes(_e.getRoot());
    }

    @Override
    public void setAssignmentBeforeAsLeaf(AssignedVariablesBlock _a, AssBlock _b) {
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
    public void setAssignmentAfterAsLeaf(AssignedVariablesBlock _a, AssBlock _b, AnalyzedPageEl _page) {
        AssignedVariables varsAss_ = _a.getFinalVariables().getVal(this);
        StringMap<SimpleAssignment> as_ = varsAss_.getFieldsRoot();
        as_.putAllMap(AssignmentsUtil.assignAfterClassic(varsAss_.getFieldsRootBefore()));
        as_.put(fieldName, Assignment.assignClassic(true, false));
        _page.getInitFields().add(fieldName);
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this, _page);
    }
}
