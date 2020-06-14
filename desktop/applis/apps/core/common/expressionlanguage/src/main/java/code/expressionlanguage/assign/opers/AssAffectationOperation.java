package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.opers.ExecAffectationOperation;
import code.expressionlanguage.opers.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssAffectationOperation extends AssMultMethodOperation {
    private AssOperationNode settableOp;
    AssAffectationOperation(ExecAffectationOperation _ex) {
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
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        if (vars_ instanceof AssignedBooleanLoopVariables) {
            ((AssignedBooleanLoopVariables)vars_).add(this, _conf);
        }
        AssOperationNode firstChild_ = settableOp;
        AssOperationNode lastChild_ = getChildrenNodes().last();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        if (firstChild_ instanceof AssVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            String str_ = ((AssVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) && AssUtil.checkFinalVar(_conf, e.getValue(),_ass)) {
                        if (_conf.isFinalLocalVar(str_,index_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(_conf);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                                    str_);
                            _conf.addError(un_);
                        }
                    }
                    sm_.put(e.getKey(),Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
                }
                variablesAfter_.add(sm_);
            }

        } else {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            variablesAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        if (firstChild_ instanceof AssMutableLoopVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            String str_ = ((AssMutableLoopVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) && AssUtil.checkFinalVar(_conf, e.getValue(),_ass)) {
                        if (_conf.isFinalMutableLoopVar(str_,index_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(_conf);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                                    str_);
                            _conf.addError(un_);
                        }
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
                }
                mutableAfter_.add(sm_);
            }

        } else {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            mutableAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getMutableLoop().put(this, mutableAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_conf);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            ClassField cl_ = cst_.getFieldId();
            if (AssUtil.checkFinalField(_conf, _ass,cst_, fieldsAfterLast_)) {
                FieldInfo meta_ = _conf.getFieldInfo(cl_);
                if (meta_.isFinalField()) {
                    //error if final field
                    firstChild_.setRelativeOffsetPossibleAnalyzable(_conf);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                            cl_.getFieldName());
                    _conf.addError(un_);
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
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a, AssOperationNode _nextSibling, AssOperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf,_ass,_a, _nextSibling, _previous);
    }

    public AssOperationNode getSettableOp() {
        return settableOp;
    }
}
