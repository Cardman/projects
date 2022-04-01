package code.bean.nat.fwd.opers;

import code.bean.nat.StandardField;

public final class NatExecSettableOperationContent {

    private final StandardField field;

    public NatExecSettableOperationContent(NatAnaSettableOperationContent _cont) {
        field = _cont.getField();
    }

    public StandardField getField() {
        return field;
    }
}
