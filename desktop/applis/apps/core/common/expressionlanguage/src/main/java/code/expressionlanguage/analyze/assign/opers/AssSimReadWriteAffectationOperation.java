package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.ExplicitOperatorOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AssSimReadWriteAffectationOperation extends AssMethodOperation implements AssOperationNodeSim{
    private AssOperationNode settable;
    private final OperationNode analyzed;
    private int indexVar;
    AssSimReadWriteAffectationOperation(OperationNode _ex) {
        super(_ex);
        analyzed = _ex;
        if (_ex instanceof ExplicitOperatorOperation) {
            indexVar = ((ExplicitOperatorOperation) _ex).getIndexVar();
        }
    }

    public void setup() {
        settable = AssSimAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void analyzeSimAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssOperationNode firstChild_ = settable;
        if (firstChild_ instanceof AssSimStdVariableOperation) {
            StringMap<BoolVal> variables_ = _a.getVariables();
            String str_ = ((AssSimStdVariableOperation)firstChild_).getVariableName();
            int deep_ = ((AssSimStdVariableOperation) firstChild_).getDeep();
            AnaLocalVariable localVar_ = _a.getCache().getLocalVar(str_, deep_);
            if (localVar_ == null) {
                for (EntryCust<String, BoolVal> e: variables_.entryList()) {
                    if (StringUtil.quickEq(str_, e.getKey()) && _a.isFinalLocalVar(str_)) {
                        //error
                        errAss((AssSimStdVariableOperation) firstChild_, _page);
                    }
                }
            } else if (localVar_.isFinalVariable()){
                errAss((AssSimStdVariableOperation) firstChild_, _page);
            }
        }
    }

    private void errAss(AssSimStdVariableOperation _ch, AnalyzedPageEl _page) {
        //error
        String str_ = _ch.getVariableName();
        this.analyzed.setRelativeOffsetPossibleAnalyzable(_ch.getAnalyzed().getIndexInEl(), _page);
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFile(_page.getCurrentFile());
        un_.setIndexFile(_page);
        un_.buildError(_page.getAnalysisMessages().getFinalField(),
                str_);
        _page.getLocalizer().addError(un_);
        this.analyzed.addErr(un_.getBuiltError());
    }

    public int getIndexVar() {
        return indexVar;
    }
}
