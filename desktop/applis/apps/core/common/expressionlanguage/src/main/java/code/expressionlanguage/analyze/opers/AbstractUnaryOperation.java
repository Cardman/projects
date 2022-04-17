package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;

public abstract class AbstractUnaryOperation extends MethodOperation {

    public AbstractUnaryOperation(int _index, int _indexChild,
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

    public abstract void analyzeUnary(AnalyzedPageEl _page);


}
