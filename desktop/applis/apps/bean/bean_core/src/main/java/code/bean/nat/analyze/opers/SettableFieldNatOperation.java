package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatOperationsSequence;

public final class SettableFieldNatOperation extends
        SettableAbstractFieldNatOperation {

    private final StandardFieldOperation interf;

    public SettableFieldNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op, StandardFieldOperation _interf) {
        super(_indexInEl, _indexChild, _m, _op);
        interf = _interf;
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        NatOperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String fieldName_ = interf.getFieldName();
        String cl_ = StandardFieldOperation.getFrom(_page,this);
        NatFieldResult r_ = resolveDeclaredCustField(cl_, fieldName_, _page);
//        getSettableFieldContent().setAnc(r_.getContent().getAnc());
//        getSettableFieldContent().setFinalField(r_.getContent().isFinalField());
//        getSettableFieldContent().setStaticField(r_.getContent().isStaticField());
        getSettableFieldContent().setClassField(r_.getContent().getClassField());
//        getSettableFieldContent().setRealType(r_.getContent().getRealType());
        String c_ = r_.getType();
        setResultClass(c_);
    }

}
