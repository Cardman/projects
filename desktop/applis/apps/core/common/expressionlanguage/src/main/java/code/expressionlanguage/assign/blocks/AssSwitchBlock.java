package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.methods.SwitchBlock;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AssSwitchBlock extends AssBracedStack  implements AssBreakableBlock, AssBuildableElMethod{
    private String label;
    private CustList<AssOperationNode> opList;
    AssSwitchBlock(boolean _completeNormally, boolean _completeNormallyGroup, SwitchBlock _s) {
        super(_completeNormally,_completeNormallyGroup);
        label = _s.getRealLabel();
        opList = AssUtil.getExecutableNodes(_s.getOpValue());
    }

    @Override
    public void setAssignmentBeforeChild(ContextEl _an, AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBefore(parAss_.getLastFieldsOrEmpty()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignBefore(parAss_.getLastVariablesOrEmpty()));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignBefore(parAss_.getLastMutableLoopOrEmpty()));
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock ch_ = getFirstChild();
        if (ch_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            return;
        }
        boolean def_ = hasDefaultCase();
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ =buildAssFieldsAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
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

    private boolean isDef(AssBlock ch_) {
        return ch_ instanceof AssSwitchPartBlock && ((AssSwitchPartBlock)ch_).isDef();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this,_cont);
    }
}
