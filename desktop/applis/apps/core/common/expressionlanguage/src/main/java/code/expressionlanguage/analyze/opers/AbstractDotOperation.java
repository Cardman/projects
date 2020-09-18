package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.util.CustList;
import code.util.IntTreeMap;

public abstract class AbstractDotOperation extends MethodOperation {
    public AbstractDotOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setResultClass(AnaClassArgumentMatching.copy(chidren_.last().getResultClass(),_conf.getAnalyzing().getStandards()));
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
}
