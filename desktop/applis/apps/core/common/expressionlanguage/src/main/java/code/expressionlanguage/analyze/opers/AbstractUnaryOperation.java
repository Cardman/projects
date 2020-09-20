package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        MethodOperation.processEmptyError(getFirstChild(),getErrs());
        if (isFirstKo()) {
            CustList<OperationNode> children_ = getChildrenNodes();
            LgNames stds_ = _page.getStandards();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //first operator part
            un_.buildError(_page.getAnalysisMessages().getSplitDiff(),
                    Integer.toString(1),
                    Integer.toString(children_.size()));
            _page.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        analyzeUnary(_page);
    }
    boolean isFirstKo() {
        CustList<OperationNode> children_ = getChildrenNodes();
        return children_.size() != 1;
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    public abstract void analyzeUnary(AnalyzedPageEl _page);


    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        setArg(this, _page);
    }

    private static void setArg(AbstractUnaryOperation _current, AnalyzedPageEl _page) {
        CustList<OperationNode> children_ = _current.getChildrenNodes();
        if (children_.size() != 1) {
            return;
        }
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _current.quickCalculate(_page);
    }
}
