package code.bean.nat.fwd.opers;

import code.bean.nat.StandardField;
import code.expressionlanguage.common.ClassField;

public final class NatAnaSettableOperationContent {
    private ClassField classField;
    private StandardField field;

    public ClassField getClassField() {
        return classField;
    }

    public void setClassField(ClassField _classField) {
        this.classField = _classField;
    }

    public StandardField getField() {
        return field;
    }

    public void setField(StandardField _field) {
        this.field = _field;
    }
}
