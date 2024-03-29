package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;

public final class SettableFieldNatOperation extends
        SettableAbstractFieldNatOperation {

    private final NatStandardFieldOperation interf;

    public SettableFieldNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op, NatStandardFieldOperation _interf) {
        super(_indexInEl, _indexChild, _m, _op);
        interf = _interf;
    }
    @Override
    public void analyze(NatAnalyzedCode _page) {
        String fieldName_ = interf.getFieldName();
        String cl_ = NatStandardFieldOperation.getFrom(_page,this);
        NatFieldResult r_ = resolveDeclaredCustField(cl_, fieldName_, _page);
//        getSettableFieldContent().setAnc(r_.getContent().getAnc());
//        getSettableFieldContent().setFinalField(r_.getContent().isFinalField());
//        getSettableFieldContent().setStaticField(r_.getContent().isStaticField());
        getSettableFieldContent().setField(r_.getContent().getField());
//        getSettableFieldContent().setRealType(r_.getContent().getRealType());
        String c_ = r_.getType();
        setResultClass(c_);
    }

}
