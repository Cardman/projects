package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitBlock extends MemberCallingsBlock implements AloneBlock {

    public InitBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void setAssignmentBeforeCall(ContextEl _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        while (prev_ != null) {
            if (prev_ instanceof InitBlock) {
                if (((InitBlock)prev_).getStaticContext() == getStaticContext()) {
                    break;
                }
            }
            if (prev_ instanceof InfoBlock) {
                if (((InfoBlock)prev_).isStaticField() == (getStaticContext() == MethodAccessKind.STATIC)) {
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
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
    }

    @Override
    public void setAssignmentAfterCall(ContextEl _an, AnalyzingEl _anEl) {
        setAssignmentAfter(_an,_anEl);
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        for (EntryCust<ReturnMethod, StringMap<SimpleAssignment>> r: _an.getAssignedVariables().getAssignments().entryList()) {
            for (EntryCust<String, SimpleAssignment> f: r.getValue().entryList()) {
                checkAssignments(_an, f,false);
            }
        }
        if (_anEl.canCompleteNormally(this)) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                checkAssignments(_an, f,true);
            }
        } else {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                String name_ = f.getKey();
                SimpleAssignment a_ = f.getValue();
                if (a_.isAssignedAfter()) {
                    _an.getAnalyzing().getInitFields().add(name_);
                }
            }
        }
    }

    private void checkAssignments(ContextEl _an, EntryCust<String, SimpleAssignment> _pair, boolean _add) {
        String cl_ = Templates.getIdFromAllTypes(_an.getAnalyzing().getGlobalClass());
        String name_ = _pair.getKey();
        ClassField key_ = new ClassField(cl_, name_);
        FieldInfo finfo_ = _an.getFieldInfo(key_);
        if (!finfo_.isFinalField()) {
            return;
        }
        SimpleAssignment a_ = _pair.getValue();
        if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
            //error
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_an.getAnalysisMessages().getUnassignedFinalField(),
                    name_,cl_);
            _an.addError(un_);
        }
        if (a_.isAssignedAfter() && _add) {
            _an.getAnalyzing().getInitFields().add(name_);
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

    }
}
