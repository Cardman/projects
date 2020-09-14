package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class AssSimReadWriteAffectationOperation extends AssMethodOperation {
    private AssOperationNode settable;
    private OperationNode analyzed;
    AssSimReadWriteAffectationOperation(OperationNode _ex) {
        super(_ex);
        analyzed = _ex;
    }

    public void setup() {
        settable = AssSimAffectationOperation.tryGetSettable(this);
    }
    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssOperationNode firstChild_ = settable;
        if (firstChild_ instanceof AssSimStdVariableOperation) {
            StringMap<Boolean> variables_ = _a.getVariables();
            String str_ = ((AssSimStdVariableOperation)firstChild_).getVariableName();
            int deep_ = ((AssSimStdVariableOperation) firstChild_).getDeep();
            AnaLocalVariable localVar_ = _a.getCache().getLocalVar(str_, deep_);
            if (localVar_ == null) {
                for (EntryCust<String, Boolean> e: variables_.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        if (_a.isFinalLocalVar(str_)) {
                            //error
                            analyzed.setRelativeOffsetPossibleAnalyzable(((AssSimStdVariableOperation)firstChild_).getAnalyzed().getIndexInEl(), _conf);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getFinalField(),
                                    str_);
                            _conf.getAnalyzing().addLocError(un_);
                            analyzed.getErrs().add(un_.getBuiltError());
                        }
                    }
                }
            } else if (localVar_.isFinalVariable()){
                //error
                analyzed.setRelativeOffsetPossibleAnalyzable(((AssSimStdVariableOperation)firstChild_).getAnalyzed().getIndexInEl(), _conf);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getFinalField(),
                        str_);
                _conf.getAnalyzing().addLocError(un_);
                analyzed.getErrs().add(un_.getBuiltError());
            }
        }
    }
}
