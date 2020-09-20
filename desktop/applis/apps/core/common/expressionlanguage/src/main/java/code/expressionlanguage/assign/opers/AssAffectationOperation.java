package code.expressionlanguage.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssAffectationOperation extends AssMultMethodOperation {
    private AssOperationNode settableOp;
    AssAffectationOperation(AffectationOperation _ex) {
        super(_ex);
    }

    public void setup() {
        settableOp = tryGetSettable(this);
    }
    static AssOperationNode tryGetSettable(AssMethodOperation _operation) {
        AssOperationNode root_ = getFirstToBeAnalyzed(_operation);
        AssOperationNode elt_;
        if (!(root_ instanceof AssDotOperation)) {
            elt_ = castTo(root_);
        } else {
            AssOperationNode beforeLast_ = ((AssMethodOperation)root_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    private static AssOperationNode castTo(AssOperationNode _op) {
        if (_op instanceof AssSettableElResult) {
            return _op;
        }
        return null;
    }
    public static AssOperationNode getFirstToBeAnalyzed(AssMethodOperation _operation) {
        AssOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof AssIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        if (vars_ instanceof AssignedBooleanLoopVariables) {
            ((AssignedBooleanLoopVariables)vars_).add(this, _page);
        }
        AssOperationNode firstChild_ = settableOp;
        AssOperationNode lastChild_ = getChildrenNodes().last();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfter_ = new StringMap<Assignment>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        if (firstChild_ instanceof AssStdVariableOperation) {
            StringMap<Assignment> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            String str_ = ((AssStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Assignment> e: variablesAfterLast_.entryList()) {
                if (StringList.quickEq(str_, e.getKey()) && AssUtil.checkFinalVar(e.getValue(),_ass, _page)) {
                    if (_a.isFinalLocalVar(str_)) {
                        //error
                        firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_page.getLocalizer().getCurrentFileName());
                        un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        un_.buildError(_page.getAnalysisMessages().getFinalField(),
                                str_);
                        _page.addLocError(un_);
                    }
                }
                variablesAfter_.put(e.getKey(),Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
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
            ClassField cl_ = cst_.getFieldId();
            if (AssUtil.checkFinalField(_ass,cst_, fieldsAfterLast_, _page)) {
                if (cst_.getFieldMetaInfo().isFinalField()) {
                    //error if final field
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            cl_.getFieldName());
                    _page.addLocError(un_);
                }
            }
        }
        if (fromCurClass_) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), Assignment.assign(cl_.getFieldName(),e.getKey(),isBool_, e.getValue()));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        }
        vars_.getFields().put(this, fieldsAfter_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_ass,_a, _nextSibling, _previous);
    }

    public AssOperationNode getSettableOp() {
        return settableOp;
    }
}
