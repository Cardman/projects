package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.instr.NatOperationsSequence;

public abstract class LeafNatOperation extends NatOperationNode {

    protected LeafNatOperation(int _indexInEl, int _indexChild, MethodNatOperation _m,
                               NatOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public final NatOperationNode getFirstChild() {
        return null;
    }
}
