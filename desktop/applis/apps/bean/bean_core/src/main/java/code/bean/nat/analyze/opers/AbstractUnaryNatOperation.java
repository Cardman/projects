package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;

public abstract class AbstractUnaryNatOperation extends MethodNatOperation {

    protected AbstractUnaryNatOperation(int _index, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public final void analyze(NatAnalyzedCode _page) {
        analyzeUnary(_page);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }
    public abstract void analyzeUnary(NatAnalyzedCode _page);


}
