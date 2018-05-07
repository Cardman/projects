package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.sml.Element;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitBlock extends MemberCallingsBlock implements AloneBlock {

    public InitBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public InitBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m,
            OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        while (prev_ != null) {
            if (prev_ instanceof InitBlock) {
                if (((InitBlock)prev_).isStaticContext() == isStaticContext()) {
                    break;
                }
            }
            if (prev_ instanceof InfoBlock) {
                if (((InfoBlock)prev_).isStaticField() == isStaticContext()) {
                    break;
                }
            }
            prev_ = prev_.getPreviousSibling();
        }
        AssignedVariables ass_;
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        if (prev_ == null) {
            ass_ = _an.getAssignedVariables().getFinalVariablesGlobal();
            id_.put(this, ass_);
        } else {
            AssignedVariables parAss_ = id_.getVal(prev_);
            if (parAss_ == null) {
                parAss_ = _an.getAssignedVariables().getFinalVariablesGlobal();
            }
            AssignedVariables assBl_ = buildNewAssignedVariable();
            for (EntryCust<ClassField, Assignment> e: parAss_.getFieldsRoot().entryList()) {
                assBl_.getFieldsRootBefore().put(e.getKey(), e.getValue().assignBefore());
            }
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }
}
