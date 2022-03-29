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
        if (firstChild_ instanceof AssStdVariableOperation) {
            StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            String str_ = ((AssStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Assignment> e: variablesAfterLast_.entryList()) {
                if (StringUtil.quickEq(str_, e.getKey())) {
                    if (!e.getValue().isUnassignedAfter()) {
                        if (_a.isFinalLocalVar(str_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFile(_page.getCurrentFile());
                            un_.setIndexFile(_page);
                            un_.buildError(_page.getAnalysisMessages().getFinalField(),
                                    str_);
                            _page.getLocalizer().addError(un_);
                        }
                    }
                }
                variablesAfter_.put(e.getKey(), Assignment.assign(str_, e.getKey(),isBool_, e.getValue()));
            }
        } else {
            StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            variablesAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_page);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            if (AssUtil.checkFinalField(_ass,cst_, fieldsAfterLast_, _page)) {
                ClassField cl_ = cst_.getFieldId();
                if (cst_.getFieldMetaInfo().isFinalField()) {
                    //error if final field
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page);
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            cl_.getFieldName());
                    _page.getLocalizer().addError(un_);
                }
            }
        }
        if (fromCurClass_) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), Assignment.assign(cl_.getFieldName(), e.getKey(),isBool_, e.getValue()));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
}
