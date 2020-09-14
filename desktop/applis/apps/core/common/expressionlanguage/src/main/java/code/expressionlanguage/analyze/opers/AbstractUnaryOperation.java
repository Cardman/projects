package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        MethodOperation.processEmptyError(getFirstChild(),getErrs());
        if (isFirstKo()) {
            CustList<OperationNode> children_ = getChildrenNodes();
            AnalyzedPageEl page_ = _conf.getAnalyzing();
            LgNames stds_ = page_.getStandards();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //first operator part
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getSplitDiff(),
                    Integer.toString(1),
                    Integer.toString(children_.size()));
            page_.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        analyzeUnary(_conf);
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
    public abstract void analyzeUnary(ContextEl _conf);


    @Override
    public void tryCalculateNode(ContextEl _conf) {
        setArg(_conf, this);
    }

    private static void setArg(ContextEl _conf, AbstractUnaryOperation _current) {
        CustList<OperationNode> children_ = _current.getChildrenNodes();
        if (children_.size() != 1) {
            return;
        }
        for (OperationNode o: children_) {
            if (o.getArgument() == null) {
                return;
            }
        }
        _current.quickCalculate(_conf);
    }
}
