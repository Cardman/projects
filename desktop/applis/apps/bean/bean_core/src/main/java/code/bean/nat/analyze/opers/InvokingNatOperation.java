package code.bean.nat.analyze.opers;

import code.expressionlanguage.functionid.*;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;

public abstract class InvokingNatOperation extends MethodNatOperation implements NatPossibleIntermediateDotted {
    private String previousResultClass;
    private MethodAccessKind staticAccess;
    private boolean intermediate;

    protected InvokingNatOperation(int _index, int _indexChild, MethodNatOperation _m,
                                NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = EMPTY_STRING;
    }

    @Override
    final void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
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
        staticAccess = _staticAccess;
    }

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

}
