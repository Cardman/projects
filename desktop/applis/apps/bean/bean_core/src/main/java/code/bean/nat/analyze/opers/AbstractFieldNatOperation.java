package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.fwd.opers.NatAnaFieldOperationContent;

public abstract class AbstractFieldNatOperation extends LeafNatOperation implements NatPossibleIntermediateDotted {

    private String previousResultClass;
    private final NatAnaFieldOperationContent fieldContent;

    protected AbstractFieldNatOperation(int _indexInEl, int _indexChild, MethodNatOperation _m,
                                     NatOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        fieldContent = new NatAnaFieldOperationContent();
        previousResultClass = EMPTY_STRING;
    }

    @Override
    public final void setIntermediateDotted() {
        fieldContent.setIntermediate(true);
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return fieldContent.isIntermediate();
    }

    public final String getPreviousResultClass() {
        return previousResultClass;
    }

    public void setPreviousResultClass(String _previousResultClass) {
        previousResultClass = _previousResultClass;
    }

    public NatAnaFieldOperationContent getFieldContent() {
        return fieldContent;
    }
}
