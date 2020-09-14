package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class AssInit extends AssMemberCallingsBlock {
    AssInit(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }

    @Override
    public void setAssignmentBeforeCall(ContextEl _an, AssBlock _prev,AssignedVariablesBlock _anEl) {
        AssignedVariables ass_;
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        if (_prev == null) {
            ass_ = _anEl.getFinalVariablesGlobal();
            id_.put(this, ass_);
        } else {
            AssignedVariables parAss_ = id_.getVal(_prev);
            AssignedVariables assBl_ = buildNewAssignedVariable();
            assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }

    @Override
    public void setAssignmentAfterCall(ContextEl _an, AssignedVariablesBlock _anEl) {
        setAssignmentAfter(_an,_anEl);
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        for (EntryCust<AssReturnMethod, StringMap<SimpleAssignment>> r: _anEl.getAssignments().entryList()) {
            for (EntryCust<String, SimpleAssignment> f: r.getValue().entryList()) {
                checkAssignments(_an, f,false);
            }
        }
        if (isCompleteNormally()) {
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
        String cl_ = StringExpUtil.getIdFromAllTypes(_an.getAnalyzing().getGlobalClass());
        String name_ = _pair.getKey();
        ClassField key_ = new ClassField(cl_, name_);
        if (!ContextUtil.isFinalField(_an,key_)) {
            return;
        }
        SimpleAssignment a_ = _pair.getValue();
        if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
            //error
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_an.getAnalyzing().getCurrentBlock().getFile().getFileName());
            un_.setIndexFile(_an.getAnalyzing().getTraceIndex());
            un_.buildError(_an.getAnalyzing().getAnalysisMessages().getUnassignedFinalField(),
                    name_,cl_);
            _an.getAnalyzing().addLocError(un_);
        }
        if (a_.isAssignedAfter() && _add) {
            _an.getAnalyzing().getInitFields().add(name_);
        }
    }

}
