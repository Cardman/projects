package code.bean.nat.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public final class IdNatOperation extends AbstractUnaryNatOperation {

    public IdNatOperation(int _index,
                          int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        CustList<NatOperationNode> children_ = getChildrenNodes();
        setResultClass(children_.first().getNames());
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }

}
