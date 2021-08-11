package code.bean.nat.fwd.opers;

import code.expressionlanguage.common.ClassField;

public final class NatExecSettableOperationContent {

    private final ClassField classField;

    public NatExecSettableOperationContent(NatAnaSettableOperationContent _cont) {
        this.classField = _cont.getClassField();
    }

    public ClassField getClassField() {
        return classField;
    }

}
