package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AbstractUnaryOperation extends MethodOperation {

    protected AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        processEmptyErrorChild();
        if (isFirstKo()) {
            CustList<OperationNode> children_ = getChildrenNodes();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //first operator part
            un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                    Long.toString(1),
                    Long.toString(children_.size()));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        analyzeUnary(_page);
    }
    boolean isFirstKo() {
        CustList<OperationNode> children_ = getChildrenNodes();
        return children_.size() != 1;
    }

    protected OperatorConverter operUse(AnalyzedPageEl _page, String _op, AnaClassArgumentMatching _operand, CustList<OperationNode> _single, CustList<StringList> _gr) {
        OperatorConverter operCust_ = tryGetUnaryWithCust(this, _op, _page, _single, _operand);
        if (operCust_ != null) {
            return operCust_;
        }
        return tryGetUnaryWithVirtual(this, _op, _page, _single, _gr);
    }
    protected void errSymbol(AnalyzedPageEl _page) {
        _page.setOkNumOp(false);
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        String oper_ = getOperators().firstValue();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setIndexFile(_page);
        un_.setFile(_page.getCurrentFile());
        //oper
        un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringUtil.join(clMatch_.getNames(), ExportCst.JOIN_TYPES),
                oper_);
        _page.getLocalizer().addError(un_);
        if (!MethodOperation.isEmptyError(child_)){
            addErr(un_.getBuiltError());
        }
    }
    public abstract void analyzeUnary(AnalyzedPageEl _page);


}
