package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.MutableLoopVariableOperation;
import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssStdVariableOperation extends AssLeafOperation implements AssSettableElResult {

    private String variableName;
    private boolean declare;
    private boolean finalVariable;

    AssStdVariableOperation(VariableOperation _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = _ex.isDeclare();
        finalVariable = _ex.isFinalVariable();
    }
    AssStdVariableOperation(MutableLoopVariableOperation _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = _ex.isDeclare();
        finalVariable = _ex.isFinalVariable();
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> assB_ = vars_.getVariablesBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        StringMap<Assignment> ass_ = new StringMap<Assignment>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (declare) {
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(page_);
            ass_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assB_));
            AssignmentBefore asBe_ = new AssignmentBefore();
            asBe_.setUnassignedBefore(true);
            _a.putLocalVar(variableName, finalVariable);
            ass_.put(variableName, asBe_.assignAfter(isBool_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
            vars_.getVariables().put(this, ass_);
            vars_.getFields().put(this, assA_);
            return;
        }

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(page_);
        String varName_ = variableName;
        if (getParent() instanceof AssAffectationOperation && getParent().getFirstChild() == this) {
            varName_ = "";
        }

        for (EntryCust<String, AssignmentBefore> e: assB_.entryList()) {
            if (StringList.quickEq(e.getKey(), varName_)) {
                if (!e.getValue().isAssignedBefore()) {
                    //errors
                    setRelativeOffsetPossibleAnalyzable(_conf);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(page_.getLocalizer().getCurrentFileName());
                    un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getFinalField(),
                            varName_);
                    _conf.getAnalyzing().addLocError(un_);
                }
            }
        }
        ass_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assB_));
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    public String getVariableName() {
        return variableName;
    }
}
