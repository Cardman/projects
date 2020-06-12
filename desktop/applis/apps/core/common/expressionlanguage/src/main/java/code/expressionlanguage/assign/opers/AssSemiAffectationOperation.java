package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssSemiAffectationOperation extends AssMethodOperation {
    private AssOperationNode settable;
    AssSemiAffectationOperation(ExecOperationNode _ex) {
        super(_ex);
    }

    public void setup() {
        settable = AssAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        AssOperationNode realFirstChild_ = getFirstChild();
        AssOperationNode firstChild_ = settable;
        if (firstChild_ instanceof AssVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            String str_ = ((AssVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        if (!e.getValue().isUnassignedAfter()) {
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
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_, e.getKey(), isBool_, e.getValue()));
                }
                variablesAfter_.add(sm_);
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            variablesAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        if (firstChild_ instanceof AssMutableLoopVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(firstChild_);
            String str_ = ((AssMutableLoopVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        if (!e.getValue().isUnassignedAfter()) {
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
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_, e.getKey(), isBool_, e.getValue()));
                }
                mutableAfter_.add(sm_);
            }

        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(firstChild_);
            mutableAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getMutableLoop().put(this, mutableAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof AssSettableFieldOperation) {
            AssSettableFieldOperation cst_ = (AssSettableFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_conf);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            if (AssUtil.checkFinalField(_conf,_ass, cst_, fieldsAfterLast_)) {
                ClassField cl_ = cst_.getFieldId();
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
