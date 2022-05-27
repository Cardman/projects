package code.expressionlanguage.analyze.assign.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.assign.blocks.AssBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AssSimAffectationOperation extends AssSimMultMethodOperation implements AssOperationNodeSim{
    private AssOperationNode settableOp;
    private final AffectationOperation analyzed;
    AssSimAffectationOperation(AffectationOperation _ex) {
        super(_ex);
        analyzed = _ex;
    }

    public void setup() {
        settableOp = tryGetSettable(this);
    }
    static AssOperationNode tryGetSettable(AssMethodOperation _operation) {
        AssOperationNode root_ = getFirstToBeAnalyzed(_operation);
        if (root_ instanceof AssMethodOperation&&((AssMethodOperation)root_).isCast()) {
            root_ = root_.getFirstChild();
        }
        root_ = loopId(root_);
        return castTo(root_);
    }
    private static AssOperationNode castTo(AssOperationNode _op) {
        if (_op instanceof AssSimStdVariableOperation) {
            return _op;
        }
        return null;
    }
    public static AssOperationNode getFirstToBeAnalyzed(AssMethodOperation _operation) {
        if (_operation instanceof AssSimReadWriteAffectationOperation) {
            int indexVar_ = ((AssSimReadWriteAffectationOperation) _operation).getIndexVar();
            CustList<AssOperationNode> ch_ = _operation.getChildrenNodes();
            if (!ch_.isValidIndex(indexVar_)) {
                return null;
            }
            return loopId(ch_.get(indexVar_));
        }
        AssOperationNode root_ = _operation.getFirstChild();
        return loopId(root_);
    }
    private static AssOperationNode loopId(AssOperationNode _start) {
        AssOperationNode root_ = _start;
        while (root_ instanceof AssSimIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    @Override
    public void analyzeSimAssignmentAfter(AssBlock _ass, AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssOperationNode firstChild_ = settableOp;
        if (firstChild_ instanceof AssSimStdVariableOperation) {
            StringMap<BoolVal> variables_ = _a.getVariables();
            String str_ = ((AssSimStdVariableOperation)firstChild_).getVariableName();
            int deep_ = ((AssSimStdVariableOperation) firstChild_).getDeep();
            AnaLocalVariable localVar_ = _a.getCache().getLocalVar(str_, deep_);
            if (localVar_ == null) {
                for (EntryCust<String, BoolVal> e: variables_.entryList()) {
                    if (StringUtil.quickEq(str_, e.getKey()) && e.getValue() == BoolVal.TRUE && _a.isFinalLocalVar(str_)) {
                        //error
                        errAss(_page, str_);
                    }
                }
            } else if (localVar_.isFinalVariable()){
                errAss(_page, str_);
            }
        }
    }

    private void errAss(AnalyzedPageEl _page, String _str) {
        StrTypes ops_ = analyzed.getOperators();
        this.analyzed.setRelativeOffsetPossibleAnalyzable(this.analyzed.getIndexInEl() + ops_.firstKey(), _page);
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFile(_page.getCurrentFile());
        un_.setIndexFile(_page);
        un_.buildError(_page.getAnalysisMessages().getFinalField(),
                _str);
        _page.getLocalizer().addError(un_);
        if (this.analyzed.getPartOffsetsChildren().isEmpty()) {
            this.analyzed.getPartOffsetsChildren().add(new InfoErrorDto(un_, _page, 1));
        }
    }


}
