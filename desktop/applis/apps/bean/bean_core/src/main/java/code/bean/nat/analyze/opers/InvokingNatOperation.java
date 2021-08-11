package code.bean.nat.analyze.opers;

import code.expressionlanguage.functionid.*;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.maths.litteralcom.StrTypes;

public abstract class InvokingNatOperation extends MethodNatOperation implements NatPossibleIntermediateDotted {
    private NatAnaClassArgumentMatching previousResultClass;
    private MethodAccessKind staticAccess;
    private boolean intermediate;

    public InvokingNatOperation(int _index, int _indexChild, MethodNatOperation _m,
                                NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = new NatAnaClassArgumentMatching(EMPTY_STRING);
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

    public final NatAnaClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(NatAnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
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
