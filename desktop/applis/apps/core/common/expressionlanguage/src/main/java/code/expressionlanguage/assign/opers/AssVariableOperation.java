package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.blocks.AssDeclareVariable;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.opers.ExecVariableOperation;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssVariableOperation extends AssLeafOperation implements AssSettableElResult {

    private String variableName;

    AssVariableOperation(ExecVariableOperation _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (_ass.getPreviousSibling() instanceof AssDeclareVariable &&AssUtil.isDeclaringVariable(this)) {
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(_conf);
            ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
            AssignmentBefore asBe_ = new AssignmentBefore();
            asBe_.setUnassignedBefore(true);
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_conf.getStandards().getAliasObject());
            lv_.setFinalVariable(_conf.getAnalyzing().isFinalVariable());
            _conf.getAnalyzing().putLocalVar(variableName, lv_);
            _conf.getAnalyzing().getInfosVars().put(variableName, lv_);
            ass_.last().put(variableName, asBe_.assignAfter(isBool_));
            assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
            vars_.getVariables().put(this, ass_);
            vars_.getMutableLoop().put(this, assAfM_);
            vars_.getFields().put(this, assA_);
            return;
        }

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        String varName_ = variableName;
        if (getParent() instanceof AssAffectationOperation && getParent().getFirstChild() == this) {
            varName_ = "";
        }

        for (StringMap<AssignmentBefore> s: assB_) {
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                if (StringList.quickEq(e.getKey(), varName_)) {
                    if (!e.getValue().isAssignedBefore()) {
                        //errors
                        setRelativeOffsetPossibleAnalyzable(_conf);
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                        un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                                varName_);
                        _conf.addError(un_);
                    }
                }
            }
        }
        ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
        assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
        vars_.getMutableLoop().put(this, assAfM_);
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    public String getVariableName() {
        return variableName;
    }
}
