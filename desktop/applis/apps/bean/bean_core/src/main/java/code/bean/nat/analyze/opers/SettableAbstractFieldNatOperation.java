package code.bean.nat.analyze.opers;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.bean.nat.analyze.instr.NatOperationsSequence;

import code.bean.nat.fwd.opers.NatAnaSettableOperationContent;

public abstract class SettableAbstractFieldNatOperation extends
        AbstractFieldNatOperation implements NatSettableElResult {

    private final NatAnaSettableOperationContent settableFieldContent;

    protected SettableAbstractFieldNatOperation(int _indexInEl, int _indexChild,
                                                MethodNatOperation _m, NatOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        settableFieldContent = new NatAnaSettableOperationContent();
    }

    @Override
    public final void setVariable(boolean _variable) {
    }

    @Override
    public void setPreviousResultClass(String _previousResultClass, MethodAccessKind _staticAccess) {
        setPreviousResultClass(_previousResultClass);
    }

    public NatAnaSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

}
