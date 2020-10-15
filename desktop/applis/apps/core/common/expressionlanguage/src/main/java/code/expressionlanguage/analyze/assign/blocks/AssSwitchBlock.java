package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.SwitchBlock;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssSwitchBlock extends AssBracedStack  implements AssBreakableBlock, AssBuildableElMethod{
    private String label;
    private CustList<AssOperationNode> opList;
    AssSwitchBlock(boolean _completeNormally, boolean _completeNormallyGroup, String _label, SwitchBlock _s) {
        super(_completeNormally,_completeNormallyGroup);
        label = _label;
        opList = AssUtil.getExecutableNodes(_s.getRoot());
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBefore(parAss_.getLastFieldsOrEmpty()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignBefore(parAss_.getLastVariablesOrEmpty()));
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        AssBlock ch_ = getFirstChild();
        if (ch_ == null) {
            super.setAssignmentAfter(_anEl, _page);
            return;
        }
        boolean def_ = hasDefaultCase();
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        StringMap<SimpleAssignment> afterVars_;
        after_ =buildAssFieldsAfterSwitch(def_, ch_, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterSwitch(def_, ch_, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().putAllMap(afterVars_);
    }
    private boolean hasDefaultCase() {
        AssBlock ch_ = getFirstChild();
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (isDef(ch_)) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (isDef(ch_)) {
            def_ = true;
        }
        return def_;
    }

    private boolean isDef(AssBlock _ch) {
        return _ch instanceof AssSwitchPartBlock && ((AssSwitchPartBlock)_ch).isDef();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this, _page);
    }
}
