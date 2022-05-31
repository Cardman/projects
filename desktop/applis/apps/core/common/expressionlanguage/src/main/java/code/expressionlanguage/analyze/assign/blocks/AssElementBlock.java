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
    private final String fieldName;
    private final CustList<AssOperationNode> opList;

    public AssElementBlock(InnerTypeOrElement _e) {
        super(true, true);
        fieldName = _e.getUniqueFieldName();
        opList = AssUtil.getExecutableNodes(_e.getRoot());
    }

    @Override
    public void setAssignmentBeforeAsLeaf(AssignedVariablesBlock _a, AssBlock _b) {
        AssignedVariables assElt_;
        if (_b == null) {
            assElt_ = _a.getFinalVariablesGlobal();
            IdMap<AssBlock, AssignedVariables> idElt_ = _a.getFinalVariables();
            idElt_.put(this, assElt_);
        } else {
            IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
            AssignedVariables parAssElt_ = id_.getVal(_b);
            AssignedVariables assBlElt_ = buildNewAssignedVariable();
            assBlElt_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAssElt_.getFieldsRoot()));
            assBlElt_.getFieldsRoot().putAllMap(parAssElt_.getFieldsRoot());
            id_.put(this, assBlElt_);
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
