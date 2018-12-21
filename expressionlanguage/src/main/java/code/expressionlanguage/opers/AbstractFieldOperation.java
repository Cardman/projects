package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.exec.ReductibleOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.text.OperationsSequence;
import code.util.CustList;
import code.util.StringList;

public abstract class AbstractFieldOperation extends VariableLeafOperation implements PossibleIntermediateDotted, ReductibleOperable {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;

    private Argument previousArgument;
    private int off;
    public AbstractFieldOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
    }

    public void setStaticAccess(boolean _staticAccess) {
    }
    public boolean isStaticAccess() {
        return false;
    }

    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
        setStaticAccess(_staticAccess);
    }

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }
    public int getOff() {
        return off;
    }
}
