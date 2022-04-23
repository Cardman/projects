package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.functionid.MethodAccessKind;

public abstract class InvokingNatOperation extends MethodNatOperation implements NatPossibleIntermediateDotted {
    private String previousResultClass;
    private boolean intermediate;

    protected InvokingNatOperation(int _index, int _indexChild, MethodNatOperation _m,
                                NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = EMPTY_STRING;
    }

    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public final String getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(String _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

}
