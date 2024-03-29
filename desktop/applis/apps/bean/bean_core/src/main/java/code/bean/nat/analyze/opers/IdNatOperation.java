package code.bean.nat.analyze.opers;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.util.CustList;

public final class IdNatOperation extends AbstractUnaryNatOperation {

    public IdNatOperation(int _index,
                          int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(NatAnalyzedCode _page) {
        CustList<NatOperationNode> children_ = getChildrenNodes();
        setResultClass(children_.first().getNames());
    }

}
