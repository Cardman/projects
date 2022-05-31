package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

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
        if (_op instanceof AssSettableFieldOperation || _op instanceof AssStdVariableOperation) {
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
        StringMap<Assignment> fieldsAfterAff_ = new StringMap<Assignment>();
        StringMap<Assignment> variablesAfterAff_ = new StringMap<Assignment>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_page);
        StringMap<Assignment> variablesAfterLastAff_ = vars_.getVariables().getVal(lastChild_);
        if (firstChild_ instanceof AssStdVariableOperation) {
            String str_ = ((AssStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Assignment> e: variablesAfterLastAff_.entryList()) {
                if (StringUtil.quickEq(str_, e.getKey()) && AssUtil.checkFinalVar(e.getValue(), _ass, _page) && _a.isFinalLocalVar(str_)) {
                    //error
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page);
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            str_);
                    _page.getLocalizer().addError(un_);
                }
                variablesAfterAff_.put(e.getKey(),Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
            }

        } else {
            variablesAfterAff_.putAllMap(AssignmentsUtil.assignGene(isBool_,variablesAfterLastAff_));
        }
        vars_.getVariables().put(this, variablesAfterAff_);
        extracted(_ass, _page, vars_, firstChild_, lastChild_, fieldsAfterAff_, isBool_);
    }

    private void extracted(AssBlock _ass, AnalyzedPageEl _page, AssignedVariables _vars, AssOperationNode _chAff, AssOperationNode _lastAff, StringMap<Assignment> _fieldsAfterAff, boolean _isBoolAff) {
        boolean fromCurClassAff_ = false;
        if (_chAff instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) _chAff;
            fromCurClassAff_ = cst_.isFromCurrentClass(_page);
            StringMap<Assignment> fieldsAfterLast_ = _vars.getFields().getVal(_lastAff);
            ClassField cl_ = cst_.getFieldId();
            if (AssUtil.checkFinalField(_ass, cst_, fieldsAfterLast_, _page) && cst_.getFieldMetaInfo().isFinalField()) {
                //error if final field
                _chAff.setRelativeOffsetPossibleAnalyzable(_page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cl_.getFieldName());
                _page.getLocalizer().addError(un_);
            }
        }
        if (fromCurClassAff_) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation) _chAff;
            ClassField cl_ = cst_.getFieldId();
            StringMap<Assignment> fieldsAfterLast_ = _vars.getFields().getVal(_lastAff);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                _fieldsAfterAff.put(e.getKey(), Assignment.assign(cl_.getFieldName(),e.getKey(), _isBoolAff, e.getValue()));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = _vars.getFields().getVal(_lastAff);
            _fieldsAfterAff.putAllMap(AssignmentsUtil.assignGene(_isBoolAff,fieldsAfterLast_));
        }
        _vars.getFields().put(this, _fieldsAfterAff);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_ass,_a, _nextSibling, _previous);
    }

    public AssOperationNode getSettableOp() {
        return settableOp;
    }
}
