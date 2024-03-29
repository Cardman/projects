package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.fwd.opers.NatAnaSettableOperationContent;

public abstract class SettableAbstractFieldNatOperation extends
        AbstractFieldNatOperation implements NatSettableElResult {

    private final NatAnaSettableOperationContent settableFieldContent;

    private boolean variable;

    protected SettableAbstractFieldNatOperation(int _indexInEl, int _indexChild,
                                                MethodNatOperation _m, NatOperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        settableFieldContent = new NatAnaSettableOperationContent();
    }

    @Override
    public final void setVariable(boolean _variable) {
        variable = _variable;
    }


    public boolean isVariable() {
        return variable;
    }

    public NatAnaSettableOperationContent getSettableFieldContent() {
        return settableFieldContent;
    }

}
