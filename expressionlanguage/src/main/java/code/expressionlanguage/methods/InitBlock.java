package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.UnassignedFinalField;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.*;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitBlock extends MemberCallingsBlock implements AloneBlock {

    public InitBlock(ContextEl _importingPage, BracedBlock _m,
            OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
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
            AssignedVariables assBl_ = buildNewAssignedVariable();
            assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        for (EntryCust<ReturnMehod, StringMap<SimpleAssignment>> r: _anEl.getAssignments().entryList()) {
            for (EntryCust<String, SimpleAssignment> f: r.getValue().entryList()) {
                checkAssignments(_an, f);
            }
        }
        if (_anEl.canCompleteNormally(this)) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                checkAssignments(_an, f);
            }
        }
    }

    private void checkAssignments(Analyzable _an, EntryCust<String, SimpleAssignment> _pair) {
        String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
        ClassField key_ = new ClassField(cl_, _pair.getKey());
        FieldInfo finfo_ = _an.getFieldInfo(key_);
        if (finfo_.isStaticField() != isStaticContext()) {
            return;
        }
        if (!finfo_.isFinalField()) {
            return;
        }
        SimpleAssignment a_ = _pair.getValue();
        if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
            //error
            UnassignedFinalField un_ = new UnassignedFinalField(key_);
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        }
    }
}
