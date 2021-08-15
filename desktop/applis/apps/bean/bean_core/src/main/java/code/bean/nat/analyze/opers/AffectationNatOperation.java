package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;

import code.maths.litteralcom.StrTypes;

public final class AffectationNatOperation extends MethodNatOperation {

    public AffectationNatOperation(int _index, int _indexChild,
                                   MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        StrTypes ops_ = getOperations().getOperators();
        ops_.firstKey();
        NatSettableElResult elt_ = tryGetSettable(this);
        NatOperationNode settableOp_ = (SettableAbstractFieldNatOperation) elt_;
        setResultClass(settableOp_.getNames());
        elt_.setVariable(true);
    }

    public static NatSettableElResult tryGetSettable(MethodNatOperation _operation) {
        NatOperationNode root_ = getFirstToBeAnalyzed(_operation);
        return castDottedTo(root_);
    }

    public static NatSettableElResult castDottedTo(NatOperationNode _root) {
        NatSettableElResult elt_;
        if (!(_root instanceof AbstractDotNatOperation)) {
            elt_ = castTo(_root);
        } else {
            NatOperationNode beforeLast_ = ((MethodNatOperation)_root).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }

    public static NatOperationNode getFirstToBeAnalyzed(MethodNatOperation _operation) {
        return _operation.getFirstChild();
    }
    public static NatSettableElResult castTo(NatOperationNode _op) {
        return (NatSettableElResult) _op;
    }

}