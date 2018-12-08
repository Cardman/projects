package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.errors.custom.UnassignedFinalField;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
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
            if (parAss_ == null) {
                parAss_ = _an.getAssignedVariables().getFinalVariablesGlobal();
            }
            AssignedVariables assBl_ = buildNewAssignedVariable();
            for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
                assBl_.getFieldsRootBefore().put(e.getKey(), e.getValue().assignBefore());
            }
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
                String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
                ClassField key_ = new ClassField(cl_,f.getKey());
                FieldInfo finfo_ = _an.getFieldInfo(key_);
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (finfo_.isStaticField() != isStaticContext()) {
                    continue;
                }
                SimpleAssignment a_ = f.getValue();
                if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _an.getClasses().addError(un_);
                }
            }
        }
        if (_anEl.canCompleteNormally(this)) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
                ClassField key_ = new ClassField(cl_,f.getKey());
                FieldInfo finfo_ = _an.getFieldInfo(key_);
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (finfo_.isStaticField() != isStaticContext()) {
                    continue;
                }
                SimpleAssignment a_ = f.getValue();
                if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _an.getClasses().addError(un_);
                }
            }
        }
    }
}
