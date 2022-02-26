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

public final class AssSemiAffectationOperation extends AssMethodOperation {
    private AssOperationNode settable;
    AssSemiAffectationOperation(OperationNode _ex) {
        super(_ex);
    }

    public void setup() {
        settable = AssAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        AssOperationNode realFirstChild_ = getFirstChild();
        AssOperationNode firstChild_ = settable;
        if (firstChild_ instanceof AssStdVariableOperation) {
            StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            String str_ = ((AssStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Assignment> e: variablesAfterLast_.entryList()) {
                if (StringUtil.quickEq(str_, e.getKey())) {
                    if (!e.getValue().isUnassignedAfter()) {
                        if (_a.isFinalLocalVar(str_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFile(_page.getCurrentFile());
                            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            un_.buildError(_page.getAnalysisMessages().getFinalField(),
                                    str_);
                            _page.getLocalizer().addError(un_);
                        }
                    }
                }
                variablesAfter_.put(e.getKey(), Assignment.assign(str_, e.getKey(), isBool_, e.getValue()));
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            variablesAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_page);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            if (AssUtil.checkFinalField(_ass, cst_, fieldsAfterLast_, _page)) {
                ClassField cl_ = cst_.getFieldId();
                if (cst_.getFieldMetaInfo().isFinalField()) {
                    //error if final field
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            cl_.getFieldName());
                    _page.getLocalizer().addError(un_);
                }
            }
        }
        if (fromCurClass_) {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), Assignment.assign(cl_.getFieldName(), e.getKey(), isBool_, e.getValue()));
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
}
