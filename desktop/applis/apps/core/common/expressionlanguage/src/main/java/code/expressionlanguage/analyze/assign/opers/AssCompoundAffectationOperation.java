package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.common.ClassField;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AssCompoundAffectationOperation extends AssMultMethodOperation {
    private AssOperationNode settable;
    AssCompoundAffectationOperation(OperationNode _ex) {
        super(_ex);
    }

    public void setup() {
        settable = AssAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_ass,_a,_nextSibling,_previous);
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        AssOperationNode firstChild_ = settable;
        AssOperationNode lastChild_ = getChildrenNodes().last();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
        if (firstChild_ instanceof AssStdVariableOperation) {
            String str_ = ((AssStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Assignment> e: variablesAfterLast_.entryList()) {
                if (StringUtil.quickEq(str_, e.getKey()) && !e.getValue().isUnassignedAfter() && _a.isFinalLocalVar(str_)) {
                    //error
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page);
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            str_);
                    _page.getLocalizer().addError(un_);
                }
                variablesAfter_.put(e.getKey(), Assignment.assign(str_, e.getKey(),isBool_, e.getValue()));
            }
        } else {
            variablesAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        extracted(_ass, _page, vars_, firstChild_, lastChild_, fieldsAfter_, isBool_);
    }

    private void extracted(AssBlock _ass, AnalyzedPageEl _page, AssignedVariables _varComp, AssOperationNode _chComp, AssOperationNode _lastComp, StringMap<Assignment> _lastFieldsComp, boolean _isBoolComp) {
        boolean fromCurClass_ = false;
        if (_chComp instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) _chComp;
            fromCurClass_ = cst_.isFromCurrentClass(_page);
            StringMap<Assignment> fieldsAfterLast_ = _varComp.getFields().getVal(_lastComp);
            if (AssUtil.checkFinalField(_ass, cst_, fieldsAfterLast_, _page) && cst_.getFieldMetaInfo().isFinalField()) {
                //error if final field
                ClassField cl_ = cst_.getFieldId();
                _chComp.setRelativeOffsetPossibleAnalyzable(_page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cl_.getFieldName());
                _page.getLocalizer().addError(un_);
            }
        }
        if (fromCurClass_) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) _chComp;
            ClassField cl_ = cst_.getFieldId();
            StringMap<Assignment> fieldsAfterLast_ = _varComp.getFields().getVal(_lastComp);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                _lastFieldsComp.put(e.getKey(), Assignment.assign(cl_.getFieldName(), e.getKey(), _isBoolComp, e.getValue()));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = _varComp.getFields().getVal(_lastComp);
            _lastFieldsComp.putAllMap(AssignmentsUtil.assignGene(_isBoolComp,fieldsAfterLast_));
        }
        _varComp.getFields().put(this, _lastFieldsComp);
    }
}
