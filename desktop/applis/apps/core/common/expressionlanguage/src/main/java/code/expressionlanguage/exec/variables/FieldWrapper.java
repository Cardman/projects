package code.expressionlanguage.exec.variables;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public abstract class FieldWrapper extends ValueWrapper {
    private final String fieldType;
    private final ClassField id;
    protected FieldWrapper(Struct _v, String _fieldType,
                           ClassField _id) {
        super(_v);
        fieldType = _fieldType;
        id = _id;
    }

    protected String getFieldType() {
        return fieldType;
    }

    protected ClassField getId() {
        return id;
    }
}
