package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.AnaFieldOperationContent;

public abstract class AbstractFieldOperation extends LeafOperation implements PossibleIntermediateDotted {

    private AnaClassArgumentMatching previousResultClass;
    private AnaFieldOperationContent fieldContent;
//    private boolean intermediate;
//
//    private int off;
    public AbstractFieldOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        fieldContent = new AnaFieldOperationContent(_op.getOffset());
        previousResultClass = new AnaClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    public final void setIntermediateDotted() {
        fieldContent.setIntermediate(true);
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return fieldContent.isIntermediate();
    }

    public final AnaClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        setPreviousResultClass(_previousResultClass);
    }

    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass) {
        previousResultClass = _previousResultClass;
    }

    public int getOff() {
        return fieldContent.getOff();
    }

    public AnaFieldOperationContent getFieldContent() {
        return fieldContent;
    }
}
