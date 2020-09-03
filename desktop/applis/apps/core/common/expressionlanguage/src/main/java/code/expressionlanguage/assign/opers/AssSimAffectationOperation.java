package code.expressionlanguage.assign.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.assign.blocks.AssBlock;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

public final class AssSimAffectationOperation extends AssSimMultMethodOperation {
    private AssOperationNode settableOp;
    private AffectationOperation analyzed;
    AssSimAffectationOperation(AffectationOperation _ex) {
        super(_ex);
        analyzed = _ex;
    }

    public void setup() {
        settableOp = tryGetSettable(this);
    }
    static AssOperationNode tryGetSettable(AssMethodOperation _operation) {
        AssOperationNode root_ = getFirstToBeAnalyzed(_operation);
        return castTo(root_);
    }
    private static AssOperationNode castTo(AssOperationNode _op) {
        if (_op instanceof AssSettableElResult) {
            return _op;
        }
        return null;
    }
    public static AssOperationNode getFirstToBeAnalyzed(AssMethodOperation _operation) {
        AssOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof AssSimIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    @Override
    public void analyzeAssignmentAfter(ContextEl _conf, AssBlock _ass, AssignedVariablesBlock _a) {
        AssOperationNode firstChild_ = settableOp;
        if (firstChild_ instanceof AssSimStdVariableOperation) {
            StringMap<Boolean> variables_ = _a.getVariables();
            String str_ = ((AssSimStdVariableOperation)firstChild_).getVariableName();
            for (EntryCust<String, Boolean> e: variables_.entryList()) {
                if (StringList.quickEq(str_, e.getKey())) {
                    if (e.getValue()) {
                        if (_a.isFinalLocalVar(str_)) {
                            //error
                            analyzed.setRelativeOffsetPossibleAnalyzable(analyzed.getIndexInEl(), _conf);
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                            un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                                    str_);
                            _conf.addError(un_);
                            if (analyzed.getPartOffsetsChildren().isEmpty()) {
                                int opLocat_ = analyzed.getFoundOffset();
                                CustList<PartOffset> err_ = new CustList<PartOffset>();
                                err_.add(new PartOffset("<a title=\"" + LinkageUtil.transform(un_.getBuiltError()) + "\" class=\"e\">", opLocat_));
                                err_.add(new PartOffset("</a>", opLocat_ + 1));
                                analyzed.getPartOffsetsChildren().add(err_);
                            }
                        }
                    }
                }
            }
        }
    }


}
