package code.bean.nat.fwd.opers;

import code.bean.nat.StandardField;
import code.expressionlanguage.common.ClassField;

public final class NatExecSettableOperationContent {

    private final ClassField classField;
    private final StandardField field;

    public NatExecSettableOperationContent(NatAnaSettableOperationContent _cont) {
        this.classField = _cont.getClassField();
        field = _cont.getField();
    }

    public ClassField getClassField() {
        return classField;
    }

    public StandardField getField() {
        return field;
    }
}
