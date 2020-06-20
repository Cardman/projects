package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

public final class BadTernaryOperation extends MethodOperation {
    public BadTernaryOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String fct_ = getOperations().getFctName();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+StringList.getFirstPrintableCharIndex(fct_), _conf);
        LgNames stds_ = _conf.getStandards();
        FoundErrorInterpret badNb_ = new FoundErrorInterpret();
        badNb_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        badNb_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //=> move to BadTernaryOperation (underline key word)
        badNb_.buildError(_conf.getAnalysisMessages().getOperatorNbDiff(),
                Integer.toString(BOOLEAN_ARGS),
                Integer.toString(chidren_.size()),
                _conf.getKeyWords().getKeyWordBool()
        );
        _conf.getAnalyzing().getLocalizer().addError(badNb_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
}
