package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;
import code.util.core.StringUtil;

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
        if (_op instanceof AssSimStdVariableOperation) {
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
    public void analyzeAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssOperationNode firstChild_ = settableOp;
        if (firstChild_ instanceof AssSimStdVariableOperation) {
            StringMap<Boolean> variables_ = _a.getVariables();
            String str_ = ((AssSimStdVariableOperation)firstChild_).getVariableName();
            int deep_ = ((AssSimStdVariableOperation) firstChild_).getDeep();
            AnaLocalVariable localVar_ = _a.getCache().getLocalVar(str_, deep_);
            if (localVar_ == null) {
                for (EntryCust<String, Boolean> e: variables_.entryList()) {
                    if (StringUtil.quickEq(str_, e.getKey())) {
                        if (e.getValue()) {
                            if (_a.isFinalLocalVar(str_)) {
                                //error
                                analyzed.setRelativeOffsetPossibleAnalyzable(analyzed.getIndexInEl(), _page);
                                FoundErrorInterpret un_ = new FoundErrorInterpret();
                                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                                        str_);
                                _page.addLocError(un_);
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
            } else if (localVar_.isFinalVariable()){
                analyzed.setRelativeOffsetPossibleAnalyzable(analyzed.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        str_);
                _page.addLocError(un_);
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
