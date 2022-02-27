package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.VariableOperationUse;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AssStdVariableOperation extends AssLeafOperation {

    private final String variableName;
    private final boolean declare;
    private final boolean finalVariable;

    AssStdVariableOperation(VariableOperation _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = true;
        finalVariable = _ex.isFinalVariable();
    }

    AssStdVariableOperation(VariableOperationUse _ex) {
        super(_ex);
        variableName = _ex.getVariableName();
        declare = false;
        finalVariable = _ex.isFinalVariable();
    }

    @Override
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(_ass);
        StringMap<AssignmentBefore> assB_ = vars_.getVariablesBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        StringMap<Assignment> ass_ = new StringMap<Assignment>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (declare) {
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(_page);
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
        isBool_ = getResultClass().isBoolType(_page);
        String varName_ = variableName;
        if (getParent() instanceof AssAffectationOperation && getParent().getFirstChild() == this) {
            varName_ = "";
        }

        for (EntryCust<String, AssignmentBefore> e: assB_.entryList()) {
            if (StringUtil.quickEq(e.getKey(), varName_)) {
                if (!e.getValue().isAssignedBefore()) {
                    //errors
                    setRelativeOffsetPossibleAnalyzable(_page);
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    un_.buildError(_page.getAnalysisMessages().getFinalField(),
                            varName_);
                    _page.getLocalizer().addError(un_);
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
