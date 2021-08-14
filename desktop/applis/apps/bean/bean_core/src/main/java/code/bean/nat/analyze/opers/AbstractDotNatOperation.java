package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public abstract class AbstractDotNatOperation extends MethodNatOperation {
    protected AbstractDotNatOperation(int _index, int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<NatOperationNode> chidren_ = getChildrenNodes();
        setResultClass(chidren_.last().getNames());
    }
    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
}
