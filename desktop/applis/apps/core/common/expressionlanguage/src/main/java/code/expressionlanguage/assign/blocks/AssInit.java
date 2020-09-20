package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
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
    public void setAssignmentBeforeCall(AssBlock _prev, AssignedVariablesBlock _anEl) {
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
    public void setAssignmentAfterCall(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        setAssignmentAfter(_anEl, _page);
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        for (EntryCust<AssReturnMethod, StringMap<SimpleAssignment>> r: _anEl.getAssignments().entryList()) {
            for (EntryCust<String, SimpleAssignment> f: r.getValue().entryList()) {
                checkAssignments(f,false, _page);
            }
        }
        if (isCompleteNormally()) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                checkAssignments(f,true, _page);
            }
        } else {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                String name_ = f.getKey();
                SimpleAssignment a_ = f.getValue();
                if (a_.isAssignedAfter()) {
                    _page.getInitFields().add(name_);
                }
            }
        }
    }

    private void checkAssignments(EntryCust<String, SimpleAssignment> _pair, boolean _add, AnalyzedPageEl _page) {
        String cl_ = StringExpUtil.getIdFromAllTypes(_page.getGlobalClass());
        String name_ = _pair.getKey();
        ClassField key_ = new ClassField(cl_, name_);
        if (!ContextUtil.isFinalField(key_, _page)) {
            return;
        }
        SimpleAssignment a_ = _pair.getValue();
        if (!a_.isAssignedAfter() && !a_.isUnassignedAfter()) {
            //error
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getCurrentBlock().getFile().getFileName());
            un_.setIndexFile(_page.getTraceIndex());
            un_.buildError(_page.getAnalysisMessages().getUnassignedFinalField(),
                    name_,cl_);
            _page.addLocError(un_);
        }
        if (a_.isAssignedAfter() && _add) {
            _page.getInitFields().add(name_);
        }
    }

}
