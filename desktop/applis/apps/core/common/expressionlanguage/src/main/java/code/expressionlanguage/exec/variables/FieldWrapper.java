package code.expressionlanguage.exec.variables;

import code.expressionlanguage.common.ClassField;

public abstract class FieldWrapper implements AbstractWrapper {
    private final String fieldType;
    private final ClassField id;
    protected FieldWrapper(String _fieldType,
                           ClassField _id) {
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
